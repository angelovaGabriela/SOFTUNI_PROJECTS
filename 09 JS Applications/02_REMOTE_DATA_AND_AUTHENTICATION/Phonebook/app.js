function attachEvents() {
    document.getElementById("btnLoad").addEventListener("click", loadRecords);
    document.getElementById("btnCreate").addEventListener("click", handleCreateRecords);
}

async function loadRecords() {

    const url = `http://localhost:3030/jsonstore/phonebook`;
    const response = await fetch(url);
    const data = await response.json();

    renderRecords(data);
}

function renderRecords(data) {
    const phonebook = document.getElementById('phonebook');
    phonebook.replaceChildren();
    Object.values(data).forEach(record => {
        const li = document.createElement('li');
        li.setAttribute('data-id', record._id);
        li.textContent = `${record.person}: ${record.phone}`
        const deleteBtn = document.createElement('button');
        deleteBtn.textContent = 'Delete';
        deleteBtn.addEventListener('click', handleDelete);
        li.appendChild(deleteBtn);
        phonebook.appendChild(li);
    })
}

function handleCreateRecords() {
    const person = document.getElementById("person");
    const phone = document.getElementById("phone");

    createRecords(person.value, phone.value);
    person.value = "";
    phone.value = "";

}

async function createRecords(person, phone) {
    const url = `http://localhost:3030/jsonstore/phonebook`
   
    body = {
        person,
        phone
    }

    const response = await fetch(url, {
        method: 'POST',
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify(body)
    })

    const data = await response.json();
    loadRecords();
    return data;
}

function handleDelete(event) {
const record = event.target.parentElement;
const id = record.getAttribute("data-id");

deleteRecord(id);
record.remove();
}

async function deleteRecord(id) {

    const url = `http://localhost:3030/jsonstore/phonebook/${id}`
    const response = await fetch(url, {
        method: 'DELETE',
        headers:  {
            "Content-Type": "application/json"
        },
        body: JSON.stringify(null)
    })

    const data = await response.json();

    return data;
    
}
attachEvents();