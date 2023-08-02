import { deleteAnimalById, getAnimalById } from '../api/data.js';
import { donate, getOwnDonations, getTotalDonationCount } from '../api/donations.js';
import { html, nothing } from '../api/lib.js'


export async function showDetails(ctx) {
    const id = ctx.params.id;

    const request = [
        getAnimalById(id),
        getTotalDonationCount(id)
    ];

    const hasUser = Boolean(ctx.user);

    if (hasUser) {
        request.push(getOwnDonations(id, ctx.user._id));
    } 

    const [animal, donations, hasDonnation] = await Promise.all(request);

    const isOwner = Boolean(hasUser && ctx.user._id == animal._ownerId);
    const canDonate = !isOwner && hasDonnation == 0;



    ctx.render(detailsTemplate(animal, donations * 100, hasUser, isOwner, canDonate, onDelete, onDonate))

    async function onDelete() {
        const choice = confirm("Are you sure?")

        if (choice) {
            await deleteAnimalById(id);
            ctx.page.redirect('/');
        }
    }

    async function onDonate() {
        await donate(id);
        ctx.page.redirect('/catalog/' + id);
    }

}

function petControls(animal, hasUser, isOwner, canDonate, onDelete, onDonate) {
    if (hasUser === false) {
        return nothing;
    } 
    if(canDonate) {
        return html`
        <div class="actionBtn">
           <a @click=${onDonate} href="javascript:void(0)" class="donate">Donate</a>
       </div>
`
    }
    if (isOwner) {
      return  html`

                    <div class="actionBtn">
                        <!-- Only for registered user and creator of the pets-->
                        <a href="/edit/${animal._id}" class="edit">Edit</a>
                        <a @click=${onDelete} href="javascript:void(0)" class="remove">Delete</a>
                    </div>`
    }

    

}

function detailsTemplate(animal, donations, hasUser, isOwner, canDonate, onDelete, onDonate) {
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
                        <h4 class="donation">Donation: ${donations}$</h4>
                    </div>

                    ${petControls(animal, hasUser, isOwner, canDonate, onDelete, onDonate)}
                  
                </div>
            </div>
        </section>
    `
}

