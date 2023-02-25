window.addEventListener("load", solve);
function solve () {
    let publish = document.getElementById("publish-btn");
    publish.addEventListener("click", createPost);
    let title = document.getElementById("post-title");
    let category = document.getElementById("post-category");
    let content = document.getElementById("post-content");
    let ul = document.getElementById("review-list");
    
    function createPost (event) {
        let titleValue = title.value;
        let categoryValue = category.value;
        let contentValue = content.value;

    if(! titleValue || !categoryValue || !content.value) {
        return;
    }    

    createElements(titleValue, categoryValue, contentValue);
    clearInputFields ();
    
    }


    function createElements (titleValue, categoryValue, contentValue) {
        let li = document.createElement("li");
        li.classList.add("rpost");
    
        let article = document.createElement("article");
        let titleElement = document.createElement("h4");
        let categoryElement = document.createElement("p");
        let contentElement = document.createElement("p");

        let editButton = document.createElement("button");
        editButton.classList.add("action-btn", "edit");

        let approveButton = document.createElement("button");
        approveButton.classList.add("action-btn", "approve");

        appendElements(li, article, titleElement, categoryElement, contentElement, editButton, approveButton);
        addTextContent (titleElement, categoryElement, contentElement, editButton, approveButton);
    }

    function addTextContent (titleElement, categoryElement, contentElement, editButton, approveButton) {

        titleElement.textContent = titleValue;
        categoryElement.textContent = `Category: ` + categoryValue;
        contentElement.textContent = `Content: ` + contentValue;

        editButton.textContent = "Edit";
        approveButton.textContent = "Approve";
    
    }

    function appendElements (titleElement, categoryElement, contentElement, editButton, approveButton) {
       
        ul.appendChild(li);
        li.appendChild(article);
        article.appendChild(titleElement);
        article.appendChild(categoryElement);
        article.appendChild(contentElement);
    
        li.appendChild(editButton);
        li.appendChild(approveButton);

    }

    function clearInputFields () {
        title.value = '';
        category.value = '';
        content.value = '';    
    }
}
