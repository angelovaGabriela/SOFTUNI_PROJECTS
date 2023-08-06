import { html } from '../api/lib.js'
import { getAllFacts } from '../api/data.js'

export async function showCatalog(ctx) {
    const facts = await getAllFacts();
    // const facts = []; --- to test
    ctx.render(catalogTemplate(facts)) 
}


function catalogTemplate(facts) {
    return html `
    <h2>Fun Facts</h2>
        <section id="dashboard">
        ${facts.length == 0 
        ?  html `<h2>No Fun Facts yet.</h2>`
        : facts.map(f => factCard(f))
        }
        </section>`

       
}

function factCard(fact) {
  return html `
  <div class="fact">
            <img src="${fact.imageUrl}" alt="example3" />
            <h3 class="category">Nature</h3>
            <p class="description">${fact.description}</p>
            <a class="details-btn" href="/details/${fact._id}">More Info</a>
          </div>
  `
} 