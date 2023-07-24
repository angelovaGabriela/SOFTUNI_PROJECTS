import {html, render} from './node_modules/lit-html/lit-html.js'

import {cats} from './catSeeder.js'


const rootSection = document.getElementById("allCats");


  const catTemplate = html `
  <ul>
    ${cats.map(cat => createCat(cat))}
</ul>
  `;


  render(catTemplate, rootSection);


function createCat(cat) {
    return html `
        <li>
                <img src="./images/${cat.imageLocation}.jpg" width="250" height="250" alt="Card image cap">
                <div class="info">
                    <button class="showBtn" @click =${showSatusCode}>Show status code</button>
                    <div class="status" style="display: none" id="200">
                        <h4 class="card-title">Status Code: ${cat.statusCode}</h4>
                        <p class="card-text">${cat.statusMessage}</p>
                    </div>
                </div>
            </li>
        `


}

function showSatusCode(e) {
    e.preventDefault();

    const contentContainer = e.target.parentElement.querySelector('div');
    const currentState = contentContainer.style.display;

    if (currentState === 'none') {
        contentContainer.style.display = "block";
        e.target.textContent = "Hide status code"
    } else {
        contentContainer.style.display = "none";
        e.target.textContent = "Show status code"
    }
}