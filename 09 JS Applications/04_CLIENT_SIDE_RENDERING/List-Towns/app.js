import { html, render } from './node_modules/lit-html/lit-html.js'
const form = document.querySelector('form');
form.addEventListener('submit', onSubmit);

const rootElement = document.getElementById('root');

function onSubmit(e) {
    e.preventDefault();

    const formData = new FormData(form);
    const { towns } = Object.fromEntries(formData)
    const townNames = towns.split(", ");

    renderTownList(townNames);
    form.reset();
    
}

function renderTownList(data) {
    const result = createTownList(data);
    render(result, rootElement);
}

function createTownList(data) {

    const ul = html`
    <ul>
        ${data.map(townName => html`<li>${townName}</li>`)}

</ul>`

    return ul;
}