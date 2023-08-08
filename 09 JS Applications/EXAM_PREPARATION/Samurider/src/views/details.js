import { deleteMotorcycleById, getMotorcycleById } from '../api/data.js';

import { html, nothing } from '../api/lib.js'


export async function showDetails(ctx) {
    const id = ctx.params.id;
    const motorcycle = await getMotorcycleById(id);

    const hasUser = Boolean(ctx.user);
    const isOwner = Boolean(hasUser && ctx.user._id == motorcycle._ownerId);

    ctx.render(detailsTemplate(motorcycle, isOwner, hasUser, onDelete))

    async function onDelete() {
        const choice = confirm("Are you sure?")

        if (choice) {
            await deleteMotorcycleById(id);
            ctx.page.redirect('/catalog');
        }
    }
}

function motoControls(motorcycle, hasUser, isOwner, onDelete) {
    if (hasUser === false) {
        return nothing;
    } 
 
    if (isOwner) {
      return  html`

        <div id="action-buttons">
            <a href="/edit/${motorcycle._id}" id="edit-btn">Edit</a>
            <a @click=${onDelete} href="javascript:void(0)" id="delete-btn">Delete</a>
        </div>`
    }

    

}

function detailsTemplate(motorcycle, hasUser, isOwner, onDelete) {
    return html`
      <section id="details">
          <div id="details-wrapper">
            <img id="details-img" src="${motorcycle.imageUrl}" alt="example1" />
            <p id="details-title">${motorcycle.model}</p>
            <div id="info-wrapper">
              <div id="details-description">
                <p class="year">Year: ${motorcycle.year}</p>
                <p class="mileage">Mileage: ${motorcycle.mileage} km.</p>
                <p class="contact">Contact Number: ${motorcycle.contact}</p>
                   <p id = "motorcycle-description">${motorcycle.about}</p>
              </div>
               ${motoControls(motorcycle, hasUser, isOwner, onDelete)}
            </div>
        </div>
      </section>
    `
}

