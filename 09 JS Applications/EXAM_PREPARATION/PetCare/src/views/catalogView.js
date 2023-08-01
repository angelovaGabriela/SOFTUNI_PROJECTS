import { getAllAminamls } from "../api/data.js";
import { html } from "../api/lib.js";


export async function showCatalog(ctx) {
    const animals = await getAllAminamls();
    ctx.render(catalogTemplate(animals));
}

function catalogTemplate(animals) {
    return html`
      <section id="dashboard">
            <h2 class="dashboard-title">Services for every animal</h2>
            <div class="animals-dashboard">
               ${animals.length == 0
            ? html`
                <div>
                    <p class="no-pets">No pets in dashboard</p>
                </div> `
            : animals.map(a => animalCardTemplate(a))}
            </div>
        </section>
    `
}

function animalCardTemplate(data) {
    return html`
    <div class="animals-board">
                    <article class="service-img">
                        <img class="animal-image-cover" src="${data.image}">
                    </article>
                    <h2 class="name">${data.name}</h2>
                    <h3 class="breed">${data.breed}</h3>
                    <div class="action">
                        <a class="btn" href="/catalog/${data._id}">Details</a>
                    </div>
                </div>
    `
}