import {html, render} from './node_modules/lit-html/lit-html.js'

const form = document.querySelector('form');
form.addEventListener('submit', onSubmit)
const rootSection = document.getElementById('menu');
const url = `http://localhost:3030/jsonstore/advanced/dropdown`;

loadContent();
async function loadContent() {
    const response = await fetch(url);
    const data = await response.json();

    const result = Object.values(data).map(x => createOptionTemplate(x));
    render(result, rootSection);

}

function createOptionTemplate(option) {
    return html `
    <option value=${option._id}>${option.text}</option>
    `
}

function onSubmit(e) {
    e.preventDefault();
    const input = document.getElementById('itemText').value;
    input && addItem(input) // prevent to submit empty option
    form.reset();
}

async function addItem(data) {
  const response = await fetch(url, {
    method: 'POST',
    headers: {
        "Content-Type": "Application/json"
    },
    body: JSON.stringify({text: data})
  })

  loadContent();
}