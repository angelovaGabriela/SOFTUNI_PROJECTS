function attachEvents() {
    document.getElementById("refresh").addEventListener("click", getAllMessages);
    document.getElementById("submit").addEventListener("click", sendMessages);

}

async function getAllMessages() {
    const url = `http://localhost:3030/jsonstore/messenger`;
    const response = await fetch(url);
    const data = await response.json();

    renderMessage(data);
}

function renderMessage(data) {
    const textArea = document.getElementById("messages");
    const messages = Object.values(data).map(entry => `${entry.author}: ${entry.content}`).join('\n');
    textArea.textContent = messages;
}

function sendMessages() {
    const author = document.querySelector('input[name="author"]');
    const content = document.querySelector('input[name="content"]');

    const body = {
        author: author.value,
        content: content.value
    }

    author.value = "";
    content.value = "";

    createMessages(body)
}

async function createMessages(body) {
    const response = await fetch('http://localhost:3030/jsonstore/messenger', {
        method: 'POST',
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify(body)
    })
    const data = await response.json();
    getAllMessages();
  
}

attachEvents();