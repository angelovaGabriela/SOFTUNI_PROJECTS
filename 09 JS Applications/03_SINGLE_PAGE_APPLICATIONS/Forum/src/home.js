const section = document.getElementById('homeView');
import { showDetails } from "./details.js";
const main = document.getElementsByTagName("main")[0];
const form = document.querySelector('#homeView form');
const submit = document.getElementsByClassName('public')[0];
submit.addEventListener("click", onSubmit);
const cancel = document.getElementsByClassName('cancel')[0];
cancel.addEventListener('click', cancelPost);
section.remove();

export async function showHome() {
    main.replaceChildren(section);
    const posts = await loadTopics();
}

async  function  loadTopics() {
    const url = `http://localhost:3030/jsonstore/collections/myboard/posts`;
    const response = await fetch(url);
    const data = await response.json();

    renderTopics(data)
}
function renderTopics(data) {
    const topicsContainer = document.getElementsByClassName('topic-title')[0];
    topicsContainer.replaceChildren();
     Object.values(data).forEach(p => {
        const newTopic = document.createElement('div');
        newTopic.classList.add('topic-container');

        newTopic.innerHTML =
            `<div class="topic-name-wrapper">
                <div class="topic-name">
                    <a href="#" class="normal" id="${p._id}">
                        <h2>${p.topicName}</h2>
                    </a>
                    <div class="columns">
                        <div>
                            <p>Date: <time>${p.date}</time></p>
                            <div class="nick-name">
                                <p>Username: <span>${p.username}</span></p>
                            </div>
                        </div>
                    </div>
                </div>
            </div>`
        topicsContainer.appendChild(newTopic);
        newTopic.querySelector('a').addEventListener('click', showDetails);
    });

    main.replaceChildren(section);
}

function onSubmit(e) {
    e.preventDefault();
    const formData = new FormData(form);
    const { topicName, username, postText,} = Object.fromEntries(formData);
    const body = {
        topicName,
        username,
        postText,
        date: new Date()
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

     loadTopics();
    return data;

}

function cancelPost(e) {
    e.preventDefault();
    form.reset();
}

showDetails();