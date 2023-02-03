function addItem() {
    let inputElement = document.getElementById('newItemText').value;
    let listElements = document.getElementById('items');

    let newElement = document.createElement('li');
    newElement.textContent = inputElement;
    listElements.appendChild(newElement);

    let deleteElement = document.createElement('a');
    deleteElement.setAttribute('href', '#');
    deleteElement.textContent = "[Delete]";
   

    newElement.appendChild(deleteElement);

    deleteElement.addEventListener('click', deleteClick);


    function deleteClick(event) {
        event.currentTarget.parentNode.remove();
    }

    inputElement.value = '';
}