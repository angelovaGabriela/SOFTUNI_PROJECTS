window.addEventListener("load", solve);

function solve() {
  let firstName = document.getElementById("first-name");
  let lastName = document.getElementById("last-name");
  let age = document.getElementById("age");
  let storyTitle = document.getElementById("story-title");
  let genre = document.getElementById("genre");
  let storyTextArea = document.getElementById("story");
  
  let publishButton = document.getElementById("form-btn");
  publishButton.addEventListener("click", publish);

  let preview = document.getElementById("preview-list");

  function publish (event) {

    let firstNameValue = firstName.value;
    let lastNameValue = lastName.value;
    let ageValue = age.value;
    let storyTitleValue = storyTitle.value;
    let genreValue = genre.value;
    let storyValue = storyTextArea.value;

    if (!firstNameValue || !lastNameValue || !ageValue || !storyTitleValue || !genreValue || !storyValue) {
      return;
    }

    createElements(firstNameValue, lastNameValue, ageValue, storyTitleValue, genreValue, storyValue);
    clearInputFields();

    publishButton.disabled = true;
  }

  function createElements(firstNameValue, lastNameValue, ageValue, storyTitleValue, genreValue, storyValue) {
    
    let li = document.createElement("li");
    li.classList.add("story-info");

    let article = document.createElement("article");

    let h4 = document.createElement("h4");
    h4.textContent = "Name: " + firstNameValue + " " + lastNameValue;

    let p1 = document.createElement("p");
    p1.textContent = "Age: " + ageValue;
    
    let p2 = document.createElement("p");
    p2.textContent = "Title: " + storyTitleValue;

    let p3 = document.createElement("p");
    p3.textContent = "Genre: " + genreValue;

    let p4 = document.createElement("p");
    p4.textContent = storyValue;

    let saveButton = document.createElement("button");
    saveButton.classList.add("save-btn");
    saveButton.textContent = "Save Story";
    saveButton.addEventListener("click", saveStory);

    let editButton = document.createElement("button");
    editButton.classList.add("edit-btn");
    editButton.textContent = "Edit Story";
    editButton.addEventListener("click", editStory);
    
    let deleteButton = document.createElement("button");
    deleteButton.classList.add("delete-btn");
    deleteButton.textContent = "Delete Story";
    deleteButton.addEventListener("click", deleteStory)

    appendElements(li, article, h4, p1, p2, p3, p4, saveButton, editButton, deleteButton);
  }

  
   function appendElements(li, article, h4, p1, p2, p3, p4, saveButton, editButton, deleteButton) {
    preview.appendChild(li);
    li.appendChild(article);
    article.appendChild(h4);
    article.appendChild(p1);
    article.appendChild(p2);
    article.appendChild(p3);
    article.appendChild(p4);

    li.appendChild(saveButton);
    li.appendChild(editButton);
    li.appendChild(deleteButton);

  }
  function clearInputFields() {
    firstName.value = "";
    lastName.value = "";
    age.value = "";
    storyTitle.value = "";
    genre.value = "";
    storyTextArea.value = "";
  }
  function editStory (event) {
    let storyInfo = Array.from(event.target.parentElement.getElementsByTagName("article")[0].children)


    let nameValue = storyInfo[0].textContent.split(": ")[1]; //First Last
    let ageValue = storyInfo[1].textContent.split(": ")[1]; 
    let titleValue = storyInfo[2].textContent.split(": ")[1];
    let genreValue = storyInfo[3].textContent.split(": ")[1];
    let storyValue = storyInfo[4].textContent;
    
    firstName.value = nameValue.split(" ")[0];
    lastName.value = nameValue.split(" ")[1];

    age.value = ageValue;
    storyTitle.value = titleValue;
    genre.value = genreValue;
    storyTextArea.value = storyValue;
    
    publishButton.disabled = false;

    let storyPreview = event.target.parentElement.remove();

  }
  function saveStory (event) {
    let mainDiv = event.target.parentElement.parentElement.parentElement.parentElement;
    let collection = Array.from(mainDiv.children);
    collection.forEach(e => e.remove());
    
    let h1 = document.createElement("h1");
    h1.textContent = "Your scary story is saved!" 

    mainDiv.appendChild(h1);
  }
  function deleteStory(event) {
    event.target.parentElement.remove();
    publishButton.disabled = false;
  }


}
