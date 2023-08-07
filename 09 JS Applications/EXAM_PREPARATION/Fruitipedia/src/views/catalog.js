import { getAllFruits } from "../api/data.js";
import { html } from "../api/lib.js";


export async function showCatalog(ctx) {
    // test --> const fruits = [];
    const fruits = await getAllFruits();
    ctx.render(catalogTemplate(fruits));
}

function catalogTemplate(fruits) {
    return html`
    <h2>Fruits</h2>
        <section id="dashboard">
         ${fruits.length == 0
            ? html`<h2>No fruit info yet.</h2>`
            : fruits.map(f => fruitCardTemplate(f))}
        </section>`
}

function fruitCardTemplate(fruit) {
    return html`
    <div class="fruit">
            <img src="${fruit.imageUrl}" alt="example1" />
            <h3 class="title">${fruit.name}</h3>
            <p class="description">${fruit.description}</p>
            <a class="details-btn" href="/details/${fruit._id}">More Info</a>
          </div>
    `
}