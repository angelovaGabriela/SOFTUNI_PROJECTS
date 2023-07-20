import {createIdea} from '../api/data.js'

const section = document.getElementById("createView");
const form = section.querySelector("form");
form.addEventListener("submit", onSubmit);

let contextStealer = null;

export function showCreate(context) {
    contextStealer = context;
    context.showSection(section)
} 

async function onSubmit(event) {
    event.preventDefault();

    const formData = new FormData(form);
    const {title, description, imageURL} = Object.fromEntries(formData);

    await createIdea({title, description, img: imageURL});
    form.reset();
    contextStealer.goTo("/catalog");
}