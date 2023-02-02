'use strict'

function addItem() {
    let input = document.getElementById('newItemText').value;
    let collection = document.querySelector('ul');

    let li = document.createElement('li');
    li.textContent = input;
    collection.appendChild(li);

    input.value = '';
}