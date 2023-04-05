window.addEventListener("load", solve);

function solve() {

    let gemName = document.getElementById("gem-name");
    let color = document.getElementById("color");
    let carats = document.getElementById("carats");
    let price = document.getElementById("price");
    let type = document.getElementById("type");

    let addButton = document.getElementById("add-btn");
    addButton.addEventListener("click", addGem);

    let previewSection = document.getElementById("preview-list");
    let collectionSection = document.getElementById("collection");

    function addGem(event) {
        event.preventDefault();

        let gemNameValue = gemName.value;
        let colorValue = color.value;
        let caratsValue = carats.value;
        let priceValue = price.value;
        let typeValue = type.value;

        if (!gemNameValue || !colorValue || !caratsValue || !priceValue || !typeValue) {
            return;
        }


        createElements(gemNameValue, colorValue, caratsValue, priceValue, typeValue);
        clearInputFields();

        addButton.disabled = true;
    }

    function clearInputFields() {
        gemName.value = "";
        color.value = "";
        carats.value = "";
        price.value = "";
        type.value = "";
    }
    function createElements(gemNameValue, colorValue, caratsValue, priceValue, typeValue) {

        let li = document.createElement("li");
        li.classList.add("gem-info");

        let article = document.createElement("article");

        let h4 = document.createElement("h4");
        h4.textContent = gemNameValue;

        let p1 = document.createElement("p");
        p1.textContent = "Color: " + colorValue;

        let p2 = document.createElement("p");
        p2.textContent = "Carats: " + caratsValue;

        let p3 = document.createElement("p");
        p3.textContent = "Price: " + priceValue + " $"

        let p4 = document.createElement("p");
        p4.textContent = "Type: " + typeValue;

        let saveButton = document.createElement("button");
        saveButton.classList.add("save-btn");
        saveButton.textContent = "Save to Collection";
        saveButton.addEventListener("click", saveInformation);

        let editButton = document.createElement("button");
        editButton.classList.add("edit-btn");
        editButton.textContent = "Edit Information";
        editButton.addEventListener("click", editInformation);

        let cancelButton = document.createElement("button");
        cancelButton.classList.add("cancel-btn");
        cancelButton.textContent = "Cancel";
        cancelButton.addEventListener("click", cancelInformation);

        appendElements(li, article, h4, p1, p2, p3, p4, saveButton, editButton, cancelButton);
    }

    function appendElements(li, article, h4, p1, p2, p3, p4, saveButton, editButton, cancelButton) {
        previewSection.appendChild(li);

        li.appendChild(article);
        li.appendChild(saveButton);
        li.appendChild(editButton);
        li.appendChild(cancelButton);

        article.appendChild(h4);
        article.appendChild(p1);
        article.appendChild(p2);
        article.appendChild(p3);
        article.appendChild(p4);

    }
    function editInformation(event) {
        let article = Array.from(event.target.parentElement.children[0].children);

        let gemNameEdit = article[0].textContent;
        let colorEdit = article[1].textContent.split(": ")[1];
        let caratsEdit = article[2].textContent.split(": ")[1];
        let priceEdit = article[3].textContent.split(": ")[1].split(" ")[0];
        let typeEdit = article[4].textContent.split(": ")[1];

        gemName.value = gemNameEdit;
        color.value = colorEdit;
        carats.value = caratsEdit;
        price.value = priceEdit;
        type.value = typeEdit;

        addButton.disabled = false;

        event.target.parentElement.remove();


    }
    function saveInformation(event) {

        let article = Array.from(event.target.parentElement.children[0].children);

        let gemNameSave = article[0].textContent;
        let colorSave = article[1].textContent.split(": ")[1];
        let caratsSave = article[2].textContent.split(": ")[1];
        let priceSave = article[3].textContent.split(": ")[1].split(" ")[0];
        let typeSave = article[4].textContent.split(": ")[1];

        let li =  document.createElement("li");
        let p = document.createElement("p");
        p.classList.add("collection-item");
        p.textContent = gemNameSave + " - Color: " + colorSave + "/ Carats: " + caratsSave + "/ Price: " + priceSave + "$/ Type: " + typeSave;

        collectionSection.appendChild(li);
        li.appendChild(p);

        event.target.parentElement.remove();
        addButton.disabled = false;
    }
    function cancelInformation(event) {
        event.target.parentElement.remove();
        addButton.disabled = false;
    }

}

