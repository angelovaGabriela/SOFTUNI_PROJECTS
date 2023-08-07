import { deleteFruitById, getFruitById} from '../api/data.js';
import { html, nothing } from '../api/lib.js'


export async function showDetails(ctx) {
    const id = ctx.params.id;
    const fruit = await getFruitById(id);

    const hasUser = Boolean(ctx.user);
    const isOwner = Boolean(hasUser && ctx.user._id == fruit._ownerId);

    ctx.render(detailsTemplate(fruit, isOwner, hasUser, onDelete))

    async function onDelete() {
        const choice = confirm("Are you sure?")

        if (choice) {
            await deleteFruitById(id);
            ctx.page.redirect('/catalog');
        }
    }


}

function fruitControls(fruit, isOwner, hasUser, onDelete) {
    if (hasUser === false) {
        return nothing;
    } 
    if (isOwner) {
      return  html`
        <div id="action-buttons">
            <a href="/edit/${fruit._id}" id="edit-btn">Edit</a>
            <a @click=${onDelete} href="javascript:void(0)" id="delete-btn">Delete</a>
          </div>`
    }

}

function detailsTemplate(fruit, isOwner, hasUser, onDelete) {
    return html`
       <section id="details">
          <div id="details-wrapper">
            <img id="details-img" src="${fruit.imageUrl}" alt="example1" />
            <p id="details-title">${fruit.name}</p>
            <div id="info-wrapper">
              <div id="details-description">
                <p>${fruit.description}</p>
                    <p id="nutrition">Nutrition</p>
                   <p id = "details-nutrition">${fruit.nutrition}</p>
              </div>
               ${fruitControls(fruit, isOwner, hasUser, onDelete)}
            </div>
        </div>
      </section>
    `
}

