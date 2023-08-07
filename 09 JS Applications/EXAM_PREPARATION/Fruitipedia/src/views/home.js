import { html } from '../api/lib.js'

export function showHome(ctx) {
    ctx.render(homeTemplate());
}

function homeTemplate() {
    return html `
    <section id="home">
          <h1>Learn more about your favorite fruits</h1>
          <img
            src="./images/pexels-pixabay-161559-dImkWBDHz-transformed (1).png"
            alt="home"
          />

        </section>
    `
}