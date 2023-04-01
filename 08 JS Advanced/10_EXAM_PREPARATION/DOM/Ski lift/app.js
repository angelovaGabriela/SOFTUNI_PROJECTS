window.addEventListener('load', solve);

function solve() {
   let firstName = document.getElementById("first-name");
   let lastName = document.getElementById("last-name");
   let peopleCount = document.getElementById("people-count");
   let date = document.getElementById("from-date");
   let days = document.getElementById("days-count");

   let nextButton = document.getElementById("next-btn");
   nextButton.addEventListener('click', nextStep);

   let ticketsInfoUl = document.getElementsByClassName("ticket-info-list")[0];
   let confirmTicketUl = document.getElementsByClassName("confirm-ticket")[0];
   let body = document.getElementById("body");

   function nextStep(event) {
    let firstNameValue = firstName.value;
    let lastNameValue = lastName.value;
    let peopleCountValue = peopleCount.value;
    let dateValue = date.value;
    let daysValue = days.value;

    if(!firstNameValue || !lastNameValue || !peopleCountValue || !dateValue ||!daysValue) {
        return
    }

    createElements(firstNameValue, lastNameValue, peopleCountValue, dateValue, daysValue);

    clearInputFields();

    nextButton.disabled = true;

   }

   function createElements(firstNameValue, lastNameValue, peopleCountValue, dateValue, daysValue) {
    let li = document.createElement("li");
    li.classList.add("ticket");

    let article = document.createElement("article");

    let h3 = document.createElement("h3");
    h3.textContent = "Name: " + firstNameValue + " " + lastNameValue;

    let p1 = document.createElement("p");
    p1.textContent = "From date: " + dateValue;

    let p2 = document.createElement("p");
    p2.textContent = "For " + daysValue + " days";

    let p3 = document.createElement("p");
    p3.textContent = "For " + peopleCountValue + " people"

    let editButton = document.createElement("button");
    editButton.classList.add("edit-btn");
    editButton.textContent = "Edit";
    editButton.addEventListener("click", editTicket);

    let continueButton = document.createElement("button");
    continueButton.classList.add("continue-btn");
    continueButton.textContent = "Continue";
    continueButton.addEventListener("click", confirmTicket);

    appendElements(li, article, h3, p1, p2, p3, editButton, continueButton);
   }

   function appendElements(li, article, h3, p1, p2, p3, editButton, continueButton) {
   
    ticketsInfoUl.appendChild(li);

    li.appendChild(article);
    li.appendChild(editButton);
    li.appendChild(continueButton);

    article.appendChild(h3);
    article.appendChild(p1);
    article.appendChild(p2);
    article.appendChild(p3);
   }
   function clearInputFields() {
    firstName.value = "";
    lastName.value = "";
    date.value = "";
    days.value = "";
    peopleCount.value = "";
   }
   function editTicket(event){
       let article = Array.from(event.target.parentElement.children[0].children);
       
       let name = article[0].textContent.split(": ")[1];
       let fromDate = article[1].textContent.split(": ")[1];
       let forDays = article[2].textContent.split(" ")[1];
       let forPeople = article[3].textContent.split(" ")[1];

       firstName.value = name.split(" ")[0];
       lastName.value = name.split(" ")[1];
       date.value = fromDate;
       days.value = forDays;
       peopleCount.value = forPeople;

       event.target.parentElement.remove();

       nextButton.disabled = false;

    }
function confirmTicket(event) {
    let node = event.target.parentElement;
    let copy = node.cloneNode(true);

    
    let removeElements = copy.getElementsByTagName("button");
    Array.from(removeElements).forEach(b => b.remove());

    let confirmButton = document.createElement("button");
    confirmButton.classList.add("confirm-btn");
    confirmButton.textContent = "Confirm";
    confirmButton.addEventListener('click', buyTicket);

    let cancelButton = document.createElement("button");
    cancelButton.classList.add("cancel-btn");
    cancelButton.textContent = "Cancel";
    cancelButton.addEventListener('click', cancelTicket);

    copy.appendChild(confirmButton);
    copy.appendChild(cancelButton);

    event.target.parentElement.remove();
    confirmTicketUl.appendChild(copy);
}

function cancelTicket(event) {
    event.target.parentElement.remove();
    nextButton.disabled = false;
}
function buyTicket(event) {
    body.children[0].remove();
    
    let doneTitle = document.createElement("h1");
    doneTitle.id = "thank-you";
    doneTitle.textContent = "Thank you, have a nice day!"

    let backButton = document.createElement("button");
    backButton.id = "back-btn";
    backButton.textContent = "Back"
    backButton.addEventListener('click', reload);

    body.appendChild(doneTitle);
    body.appendChild(backButton);
    
}

function reload() {
 location.reload();
}

}