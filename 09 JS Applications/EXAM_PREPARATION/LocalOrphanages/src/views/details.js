import { getMaterialById, deleteMaterialById } from '../api/data.js';
import { donate, getTotalDonationCount, getOwnDonations} from '../api/donations.js'
import { html, nothing } from '../api/lib.js'

export async function showDetails(ctx) {
    const id = ctx.params.id;

    let requests = [
        getMaterialById(id),
        getTotalDonationCount(id)
    ];

    
    const hasUser = Boolean(ctx.user);
    if (hasUser) {
        requests.push(getOwnDonations(id, ctx.user._id));
    }

    let [material, donations, hasDonations] = await Promise.all(requests);

    const isOwner = Boolean(hasUser && ctx.user._id == material._ownerId);
    let canDonate = !isOwner && hasDonations == 0;

    ctx.render(detailsTemplate(material, donations, hasUser, isOwner, canDonate, onDelete, onDonate));

    async function onDelete(){
        const choice = confirm("Are you sure?")

        if (choice) {
             await deleteMaterialById(id);
             ctx.page.redirect('/');
        }
    }

    async function onDonate() {
        const data = {
            id,
        }
        await donate(data);
        

        donations = await getTotalDonationCount(id);
        canDonate = await getOwnDonations(id, ctx.user._id)
        ctx.render(detailsTemplate(material, donations, hasUser, isOwner, canDonate, onDelete, onDonate));


    }
}

function materialsControl(material, hasUser, isOwner, canDonate, onDelete, onDonate) {
    if (hasUser === false) {
        return  nothing;
    }
    if (canDonate) {
        return html `
        <div class="btns">
        <a  @click=${onDonate} href="javascript:void(0)" class="donate-btn btn">Donate</a>
    </div>
        `
    }
    if (isOwner) {
        return html `
         <div class="btns">
        <a href="/edit/${material._id}" class="edit-btn btn">Edit</a>
        <a  @click=${onDelete} href="javascript:void(0)" class="delete-btn btn">Delete</a>
        </div>`
    }
}

function detailsTemplate(material, hasUser, donations, isOwner, canDonate, onDelete, onDonate) {
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
                        <p class="donate-Item">Donate Materials: ${donations}</p>

                
                       
                            ${materialsControl(material, hasUser, isOwner, canDonate, onDelete, onDonate)}
                        

                    </div>
                </div>
            </div>
        </section>
    `
}