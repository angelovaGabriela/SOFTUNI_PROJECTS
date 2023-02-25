window.addEventListener("load", solve);
function solve() {
    let publish = document.getElementById("publish-btn");
    publish.addEventListener("click", createPost);

    let clear = document.getElementById("clear-btn");
    clear.addEventListener("click", clearPosts);

    let title = document.getElementById("post-title");
    let category = document.getElementById("post-category");
    let content = document.getElementById("post-content");
    let reviewList = document.getElementById("review-list");
    let publishedList = document.getElementById("published-list");


    function createPost(event) {
        let titleValue = title.value;
        let categoryValue = category.value;
        let contentValue = content.value;

        if (!titleValue || !categoryValue || !content.value) {
            return;
        }

        createElements(titleValue, categoryValue, contentValue);
        clearInputFields();

    }



    function createElements(titleValue, categoryValue, contentValue) {
        let li = document.createElement("li");
        li.classList.add("rpost");

        let article = document.createElement("article");
        let titleElement = document.createElement("h4");
        let categoryElement = document.createElement("p");
        let contentElement = document.createElement("p");

        let editButton = document.createElement("button");
        editButton.classList.add("action-btn", "edit");
        editButton.addEventListener("click", editPost);

        let approveButton = document.createElement("button");
        approveButton.classList.add("action-btn", "approve");
        approveButton.addEventListener("click", approvePost);

        appendElements(li, article, titleElement, categoryElement, contentElement, editButton, approveButton);
        addTextContent(titleElement, categoryElement, contentElement, editButton, approveButton, titleValue, categoryValue, contentValue);
    }

    function addTextContent(titleElement, categoryElement, contentElement, editButton, approveButton, titleValue, categoryValue, contentValue) {

        titleElement.textContent = titleValue;
        categoryElement.textContent = `Category: ` + categoryValue;
        contentElement.textContent = `Content: ` + contentValue;

        editButton.textContent = "Edit";
        approveButton.textContent = "Approve";

    }

    function appendElements(li, article, titleElement, categoryElement, contentElement, editButton, approveButton) {

        reviewList.appendChild(li);
        li.appendChild(article);
        article.appendChild(titleElement);
        article.appendChild(categoryElement);
        article.appendChild(contentElement);

        li.appendChild(editButton);
        li.appendChild(approveButton);

    }

    function clearInputFields() {
        title.value = '';
        category.value = '';
        content.value = '';
    }

    function editPost(event) {

        let currentPost = event.target.parentElement;
        let articleContent = currentPost.getElementsByTagName("article")[0].children;

        let titleValue = articleContent[0].textContent;
        let categoryValue = articleContent[1].textContent;
        let contentValue = articleContent[2].textContent;


        title.value = titleValue;
        category.value = categoryValue.split(": ")[1];
        content.value = contentValue.split(": ")[1];


        currentPost.remove();

    }

    function approvePost(event) {

        let node = event.target.parentElement;
        let postCopy = node.cloneNode(true);

        let removeEditApprove = postCopy.getElementsByTagName("button");
        Array.from(removeEditApprove).forEach(b => b.remove());

        event.target.parentElement.remove();
        publishedList.appendChild(postCopy);
    }

    function clearPosts(event) {
        Array.from(publishedList.children).forEach(p => p.remove());

    }
}
