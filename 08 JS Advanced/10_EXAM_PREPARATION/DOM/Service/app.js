window.addEventListener("load", solve);

function solve () {

    let description = document.getElementById("description");
    let clientName = document.getElementById("client-name");
    let clientPhone = document.getElementById("client-phone");
    let typeProduct = document.getElementById("type-product");
    let sendFormBtn = document.getElementsByTagName("button")[0];
    sendFormBtn.addEventListener("click", sentForm);

    let receivedOrders = document.getElementById("received-orders");
    let completedOrders = document.getElementById("completed-orders");

    let clearButton = document.getElementsByClassName("clear-btn")[0];
    clearButton.addEventListener("click", clearOrders);



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
        startButton.addEventListener("click", startRepair);

        let finishButton = document.createElement("button");
        finishButton.classList.add("finish-btn");
        finishButton.textContent = "Finish repair";
        finishButton.disabled = true;
        finishButton.addEventListener("click", finishRepair);

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

    function startRepair(event) {
        event.target.disabled = true;
        let finish = document.getElementsByClassName("finish-btn")[0];
        finish.disabled = false;
    }

    function finishRepair(event) {

        let node = event.target.parentElement;
        let copyOrders = node.cloneNode(true);

        let buttons = Array.from(copyOrders.getElementsByTagName("button"));
        buttons.forEach(e => e.remove());

        event.target.parentElement.remove();
        completedOrders.appendChild(copyOrders);
    

    }

    function clearOrders() {
        let toClear = Array.from(completedOrders.getElementsByClassName("container"));
        toClear.forEach(order => order.remove());
    }

}