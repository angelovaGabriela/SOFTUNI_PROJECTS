window.addEventListener("load", solve);
function solve () {
 

    // getting the references to the DOM elements that I need
    // adding event listener to the button

    let publish = document.getElementById("publish-btn");
    publish.addEventListener("click", addInfo);
    let title = document.getElementById("post-title");
    let category = document.getElementById("post-category");
    let content = document.getElementById("post-content");

    // creating a function to the event
    function addInfo (event) {
 
    // getting the values of the title, category and content values

        let titleValue = title.value;
        let categoryValue = category.value;
        let contentValue = content.value;

    // if they are empty the program must stop
    if(! titleValue || !categoryValue || !content.value) {
        return;
    }    

    // creating the elements    


    let ul = document.getElementById("review-list");

    let li = document.createElement("li");
    li.classList.add("rpost");

    let article = document.createElement("article");
    let titleElement = document.createElement("h4");
    let categoryElement = document.createElement("p");
    let contentElement = document.createElement("p");

    // add them the appropriate values 

    titleElement.textContent = titleValue;
    categoryElement.textContent = `Category: ` + categoryValue;
    contentElement.textContent = `Content: ` + contentValue;

    // append the elements

    ul.appendChild(li);
    li.appendChild(article);
    article.appendChild(titleElement);
    article.appendChild(categoryElement);
    article.appendChild(contentElement);

    let editButton = document.createElement("button");
    editButton.classList.add("action-btn", "edit");
    editButton.textContent = "Edit";

    let approveButton = document.createElement("button");
    approveButton.classList.add("action-btn", "approve");
    approveButton.textContent = "Approve";

    li.appendChild(editButton);
    li.appendChild(approveButton);


    // clear input fields
    title.value = '';
    category.value = '';
    content.value = '';

  
    }

    

}
