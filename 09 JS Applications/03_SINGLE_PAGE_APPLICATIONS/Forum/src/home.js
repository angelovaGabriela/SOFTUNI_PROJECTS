const section = document.getElementById('homeView');
import { showDetails } from "./details.js";
const main = document.getElementsByTagName("main")[0];
const form = document.querySelector('#homeView form');
const submit = document.getElementsByClassName('public')[0];
submit.addEventListener("click", onSubmit);
const cancel = document.getElementsByClassName('cancel')[0];
cancel.addEventListener('click', cancelPost);
section.remove();

export function showHome() {
    main.replaceChildren(section);

}

function onSubmit(e) {
    e.preventDefault();
    const formData = new FormData(form);
    const { topicName, username, postText } = Object.fromEntries(formData);
    const body = {
        topicName,
        username,
        postText
    }
    createPost(body);
    form.reset();
}

async function createPost(body) {

    const url = `http://localhost:3030/jsonstore/collections/myboard/posts`;
    const response = await fetch(url, {
        method: 'POST',
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify(body)
    })
    const data = await response.json();


    return data;

}

function cancelPost(e) {
    e.preventDefault();
    form.reset();
}

showDetails();