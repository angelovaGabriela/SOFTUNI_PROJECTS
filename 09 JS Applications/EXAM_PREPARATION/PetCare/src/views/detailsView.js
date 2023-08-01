import { deleteAnimalById, getAnimalById } from '../api/data.js';
import { html } from '../api/lib.js'


export async function showDetails(ctx) {
    const id = ctx.params.id;
    const animal = await getAnimalById(id)

    const hasUser = Boolean(ctx.user);
    const isOwner = Boolean(hasUser && ctx.user._id == animal._ownerId);

    ctx.render(detailsTemplate(animal, hasUser, isOwner, onDelete))

    async function onDelete() {
        const choice = confirm("Are you sure?")

        if(choice) {
            await deleteAnimalById(id);
            ctx.page.redirect('/');
        }
    }

}



function detailsTemplate(animal, hasUser, isOwner, onDelete) {
    return html`
     <section id="detailsPage">
            <div class="details">
                <div class="animalPic">
                    <img src="${animal.image}">
                </div>
                <div>
                    <div class="animalInfo">
                        <h1>Name: ${animal.name}</h1>
                        <h3>Breed: ${animal.breed}</h3>
                        <h4>Age: ${animal.age}</h4>
                        <h4>Weight: ${animal.weight}</h4>
                        <h4 class="donation">Donation: 0$</h4>
                    </div>
                    ${!hasUser
            ? ""
            : html`
                    <!-- if there is no registered user, do not display div-->

                    ${isOwner ? html`

                    <div class="actionBtn">
                        <!-- Only for registered user and creator of the pets-->
                        <a href="/edit/${animal._id}" class="edit">Edit</a>
                        <a @click=${onDelete} href="javascript:void(0)" class="remove">Delete</a>
                        <!--(Bonus Part) Only for no creator and user-->
                        <a href="#" class="donate">Donate</a>
                    </div>`
                    : ""
                }`
        } 
                </div>
            </div>
        </section>
    `
}