window.addEventListener("load", solve);

function solve() {
  let firstName = document.getElementById("first-name");
  let lastName = document.getElementById("last-name");
  let age = document.getElementById("age");
  let gender = document.getElementById("genderSelect");
  let description = document.getElementById("task");
  let counter = document.getElementById("progress-count");
  let clearButton = document.getElementById("clear-btn")
  clearButton.addEventListener("click", clearDish);


  let progressSection = document.getElementById("in-progress");
  let finishedSection = document.getElementById("finished"); 

  let submitButton = document.getElementById("form-btn");
  submitButton.addEventListener("click", submit);

  function submit(event) {

    event.preventDefault();
    let firstNameValue = firstName.value
    let lastNameValue = lastName.value;
    let ageValue = age.value;
    let genderValue = gender.value;
    let descriptionValue = description.value;

    if(!firstNameValue || !lastNameValue || !ageValue || !descriptionValue) {
      return;
    }

    createElements(firstNameValue, lastNameValue, ageValue, genderValue, descriptionValue);
    clearFields();

    if (counter.textContent == 0) {
          counter.textContent = "";
          counter.textContent = Number(counter.textContent) + 1;
    } else {
             counter.textContent = Number(counter.textContent) + 1;
    }

  }

  function createElements(firstNameValue, lastNameValue, ageValue, genderValue, descriptionValue) {

    let li = document.createElement("li");
    li.classList.add("each-line");

    let article = document.createElement("article");

    let h4 = document.createElement("h4");
    h4.textContent = firstNameValue + " " + lastNameValue;

    let p1 = document.createElement("p");
    p1.textContent = genderValue + ", " + ageValue;

    let p2 = document.createElement("p");
    p2.textContent = "Dish description: " + descriptionValue;

    let editButton = document.createElement("button");
    editButton.classList.add("edit-btn");
    editButton.textContent = "Edit"
    editButton.addEventListener("click", editDish)

    let completeButton = document.createElement("button");
    completeButton.classList.add("complete-btn");
    completeButton.textContent = "Mark as complete"
    completeButton.addEventListener("click", completeDish);

    appendElements(li, article, h4, p1, p2, editButton, completeButton);
  }

  function appendElements(li, article, h4, p1, p2, editButton, completeButton) {
    progressSection.appendChild(li);
    li.appendChild(article);
    li.appendChild(editButton);
    li.appendChild(completeButton);

    article.appendChild(h4);
    article.appendChild(p1);
    article.appendChild(p2);

  }
  function clearFields() {
    firstName.value = "";
    lastName.value = "";
    age.value = "";
    gender.value = "";
    description.value = "";
  }
  function editDish(event) {
    let content = event.target.parentElement;
    let article = content.getElementsByTagName("article")[0].children;

    let firstNameDOM = article[0].textContent.split(" ")[0];
    let lastNameDOM = article[0].textContent.split(" ")[1];

    let genderDOM = article[1].textContent.split(", ")[0];
    let ageDOM = article[1].textContent.split(", ")[1];

    let descriptionDOM = article[2].textContent.split(": ")[1];

    firstName.value = firstNameDOM;
    lastName.value = lastNameDOM;

    gender.value = genderDOM;
    age.value = ageDOM;
    description.value = descriptionDOM;

    event.target.parentElement.remove();

    counter.textContent = Number(counter.textContent) - 1;
  
  }
  function completeDish(event) {
    let node = event.target.parentElement;
    let copy = node.cloneNode(true);
    let buttonsToRemove = copy.getElementsByTagName("button");
    Array.from(buttonsToRemove).forEach(btn => btn.remove());

    finishedSection.appendChild(copy);

    event.target.parentElement.remove();
    counter.textContent = Number(counter.textContent) - 1;

  }
  function clearDish(event) {
    let div = event.target.parentElement;
    let elements = Array.from(div.getElementsByTagName("ul")[0].children);
    elements.forEach(e => e.remove());
  }

}
