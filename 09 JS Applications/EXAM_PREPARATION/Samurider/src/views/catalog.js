import { getAllMotorcycles } from "../api/data.js";
import { html } from "../api/lib.js";


export async function showCatalog(ctx) {
    const motorcycles = await getAllMotorcycles();
    ctx.render(catalogTemplate(motorcycles));
}

function catalogTemplate(motorcycles) {
    return html`
    <h2>Available Motorcycles</h2>
        <section id="dashboard">
            ${motorcycles.length == 0 
            ? html `<h2 class="no-avaliable">No avaliable motorcycles yet.</h2>`
            : motorcycles.map(m => motoCardTemplate(m))}
        </section>`
}

function motoCardTemplate(motorcycle) {
    return html`
    <div class="motorcycle">
            <img src="${motorcycle.imageUrl}" alt="example1" />
            <h3 class="model">${motorcycle.model}</h3>
            <p class="year">Year: ${motorcycle.year}</p>
            <p class="mileage">Mileage: ${motorcycle.mileage}</p>
            <p class="contact">Contact Number: ${motorcycle.contact}</p>
            <a class="details-btn" href="/details/${motorcycle._id}">More Info</a>
          </div>`
}