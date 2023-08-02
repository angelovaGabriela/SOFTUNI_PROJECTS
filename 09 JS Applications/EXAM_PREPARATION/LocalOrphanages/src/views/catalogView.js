import { getAllMaterials } from '../api/data.js';
import { html } from '../api/lib.js'


export async function showCatalog(ctx) {
    const materials = await getAllMaterials();
    ctx.render(catalogTemplate(materials));
}

function catalogTemplate(materials) {
    return html`
     <section id="dashboard-page">
            <h1 class="title">All Posts</h1>
            ${materials.length == 0
            ? html`
            <!-- Display an h1 if there are no posts -->
            <h1 class="title no-posts-title">No posts yet!</h1>`
            : materials.map(m => postCardTemplate(m))};
        </section>
    `
}

function postCardTemplate(material) {
    return html`
         <div class="post">
                    <h2 class="post-title">${material.title}</h2>
                    <img class="post-image" src="${material.imageUrl}" alt="Kids clothes">
                    <div class="btn-wrapper">
                        <a href="/details/${material._id}" class="details-btn btn">Details</a>
                    </div>
                </div>
            </div>
    `
}