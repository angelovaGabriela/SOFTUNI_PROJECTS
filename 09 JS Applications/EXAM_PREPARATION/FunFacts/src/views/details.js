import { deleteFactById, getFactById } from '../api/data.js';
import { html, nothing } from '../api/lib.js'


export async function showDetails(ctx) {
    const id = ctx.params.id;
    const fact = await getFactById(id);

    const hasUser = Boolean(ctx.user);
    const isOwner = Boolean(hasUser && ctx.user._id == fact._ownerId);
   
    ctx.render(detailsTemplate(fact, hasUser, isOwner, onDelete))

    async function onDelete() {
        const choice = confirm("Are you sure?")

        if (choice) {
            await deleteFactById(id);
            ctx.page.redirect('/');
        }
    }

   

}

function factControls(fact, hasUser, isOwner, onDelete) {
    if (hasUser === false) {
        return nothing;
    } 
 
    if (isOwner) {
      return  html`
         <div id="action-buttons">
            <a href="/edit/${fact._id}" id="edit-btn">Edit</a>
            <a @click=${onDelete} href="javascript:void(0)" id="delete-btn">Delete</a>
        </div>`
    }

    

}

function detailsTemplate(fact, hasUser, isOwner, onDelete) {
    return html`
      <section id="details">
          <div id="details-wrapper">
            <img id="details-img" src="${fact.imageUrl}" alt="example1" />
            <p id="details-category">${fact.category}</p>
            <div id="info-wrapper">
              <div id="details-description">
                <p id="description">${fact.description}</p>
                   <p id ="more-info">${fact.moreInfo}</p>
              </div>

              <h3>Likes:<span id="likes">0</span></h3>

               ${factControls(fact, hasUser, isOwner, onDelete)}
            </div>
          </div>
      </section>
    `
}

/*<!--Bonus - Only for logged-in users ( not authors )-->
            <a href="" id="like-btn">Like</a> */