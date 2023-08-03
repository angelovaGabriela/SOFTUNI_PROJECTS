// BONUS
//<!--Bonus - Only for logged-in users ( not authors )-->
//<a href="#" class="donate-btn btn">Donate</a>


import { getMaterialById } from '../api/data.js';
import { html, nothing } from '../api/lib.js'

export async function showDetails(ctx) {
    const id = ctx.params.id;
    const material = await getMaterialById(id)
    const hasUser = Boolean(ctx.user);
    const isOwner = Boolean(hasUser && ctx.user._id == material._ownerId);

    ctx.render(detailsTemplate(material, hasUser, isOwner));
}

function materialsControl(material , hasUser, isOwner) {
    if (hasUser === false) {
        return nothing
    }
    if (isOwner) {
        return html `
        <a href="/edit/${material._id}" class="edit-btn btn">Edit</a>
        <a href="javascript:void(0)" class="delete-btn btn">Delete</a>`
    }
}

function detailsTemplate(material, hasUser, isOwner) {
    return html `
      <section id="details-page">
            <h1 class="title">Post Details</h1>

            <div id="container">
                <div id="details">
                    <div class="image-wrapper">
                        <img src="${material.imageUrl}" alt="Material Image" class="post-image">
                    </div>
                    <div class="info">
                        <h2 class="title post-title">${material.title}</h2>
                        <p class="post-description">Description: ${material.description}</p>
                        <p class="post-address">Address: ${material.address}</p>
                        <p class="post-number">Phone number: ${material.phone}</p>
                        <p class="donate-Item">Donate Materials: 0</p>

                
                        <div class="btns">
                            ${materialsControl(material, hasUser, isOwner)}
                        </div>

                    </div>
                </div>
            </div>
        </section>
    `
}