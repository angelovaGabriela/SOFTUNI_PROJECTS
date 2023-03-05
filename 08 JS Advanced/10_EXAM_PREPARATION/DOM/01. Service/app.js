window.addEventListener("load", solve);

function solve () {

    // getting the elements 
    //description, client name, client phone
    // if one of them empty the program should not do anything

    let description = document.getElementById("description");
    let clientName = document.getElementById("client-name");
    let clientPhone = document.getElementById("client-phone");
    let typeProduct = document.getElementById("type-product");
    let sendFormBtn = document.getElementsByTagName("button")[0];
    let receivedOrders = document.getElementById("received-orders");

    sendFormBtn.addEventListener("click", sentForm);

    function sentForm (event) {
        event.preventDefault();
        let descriptionValue = description.value;
        let nameValue = clientName.value;
        let phoneValue = clientPhone.value;
        let product =  typeProduct.value;

        if(!descriptionValue || !nameValue || !phoneValue || !product) {
            return;
        }
        createElements(descriptionValue, nameValue, phoneValue, product);
        clearInputFields();
    }

    function createElements(descriptionValue, nameValue, phoneValue, product) {
        let div = document.createElement("div");
        div.classList.add("container");

        let h2 = document.createElement("h2");
        h2.textContent = "Product type for repair: " + product;

        let h3 = document.createElement("h3");
        h3.textContent = "Client information: " + nameValue + ", " + phoneValue;

        let h4 = document.createElement("h4");
        h4.textContent = "Description of the problem: " + descriptionValue;

        let startButton = document.createElement("button");
        startButton.classList.add("start-btn");
        startButton.textContent = "Start repair";

        let finishButton = document.createElement("button");
        finishButton.classList.add("finish-btn");
        finishButton.textContent = "Finish repair";

        appendElements(div, h2, h3, h4, startButton, finishButton);
    }

    function clearInputFields () {

        description.value = "";
        clientName.value = "";
        clientPhone.value = "";
        typeProduct.value = "";

    }

    function appendElements(div, h2, h3, h4, startButton, finishButton) {
        receivedOrders.appendChild(div);
        div.appendChild(h2);
        div.appendChild(h3);
        div.appendChild(h4);
        div.appendChild(startButton);
        div.appendChild(finishButton);
    }


}