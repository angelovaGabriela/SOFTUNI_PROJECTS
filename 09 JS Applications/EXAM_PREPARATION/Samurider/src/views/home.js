import { html } from '../api/lib.js'

export function showHome(ctx) {
    ctx.render(homeTemplate());
}

function homeTemplate() {
    return html `
    <section id="home">
          <h1>
            Welcome to <span>Samurider</span> moto market, your premier destination for Japanese motorcycles.</h1>
          <img
            src="./images/motorcycle.png"
            alt="home"
          />

        </section>
    `
}