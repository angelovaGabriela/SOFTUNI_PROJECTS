window.addEventListener('load', solve);

function solve() {

    let firstName = document.getElementById("first-name");
    let lastName= document.getElementById("last-name");
    let dateIn = document.getElementById("date-in");
    let dateOut = document.getElementById("date-out");
    let guestsNumber = document.getElementById("people-count");
    let infoReservation = document.getElementsByClassName("info-list")[0];
    let confirmReservation = document.getElementsByClassName("confirm-list")[0];
    let verificationField = document.getElementById("verification");


    let nextButton = document.getElementById("next-btn");
    nextButton.addEventListener("click", reservationInfo);

    function reservationInfo(event) {

        event.preventDefault();
    
        let firstNameValue = firstName.value;
        let lastNameValue = lastName.value;
        let dateInValue = dateIn.value;
        let dateOutValue = dateOut.value;
        let guestsNumberValue = guestsNumber.value;
       
        if (!firstNameValue || !lastNameValue || !dateInValue || !dateOutValue || !guestsNumberValue) {
            return;
        }

        createElements(firstNameValue, lastNameValue, dateInValue, dateOutValue, guestsNumberValue);
        clearInputFields();

        event.target.disabled = true;
    } 

   function createElements(firstNameValue, lastNameValue, dateInValue, dateOutValue, guestsNumberValue) {
        let li = document.createElement("li");
        li.classList.add("reservation-content");
        
        let article = document.createElement("article");
        let h3 = document.createElement("h3");
        h3.textContent = "Name: " + firstNameValue + " " + lastNameValue;

        let p1 = document.createElement("p");
        p1.textContent = "From date: " + dateInValue;
        
        let p2 = document.createElement("p");
        p2.textContent = "To date: " + dateOutValue;

        let p3 = document.createElement("p");
        p3.textContent = "For " + guestsNumberValue + " people";

        let editButton = document.createElement("button");
        editButton.classList.add("edit-btn");
        editButton.textContent = "Edit";
        editButton.addEventListener("click", editInfo);
        

        let continueButton = document.createElement("button");
        continueButton.classList.add("continue-btn");
        continueButton.textContent = "Continue";
        continueButton.addEventListener("click", confirmInfo);
   

        appendElements(li, article, h3, p1, p2, p3, editButton, continueButton);

}

    function appendElements(li, article, h3, p1, p2, p3, editButton, continueButton){
        infoReservation.appendChild(li);

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
        dateIn.value = "";
        dateOut.value = "";
        guestsNumber.value = "";
    }

    function editInfo(event) {
        let content = event.target.parentElement;
        let article = content.getElementsByTagName("article")[0].children;

        let name = article[0].textContent.split(": ")[1];
        let checkIn = article[1].textContent.split(": ")[1];
        let checkOut = article[2].textContent.split(": ")[1];
        let guests = article[3].textContent.split(" ")[1];

        firstName.value = name.split(" ")[0];
        lastName.value = name.split(" ")[1];
        dateIn.value = checkIn;
        dateOut.value = checkOut;
        guestsNumber.value = guests;

        nextButton.disabled = false;

        let infoList = event.target.parentElement.parentElement;
        let elements = Array.from(infoList.children);

        elements.forEach(e => e.remove());
    }
    
    function confirmInfo(event) {
        let node = event.target.parentElement;
        let copy = node.cloneNode(true);

        let buttonsToRemove = copy.getElementsByTagName("button");
        Array.from(buttonsToRemove).forEach(btn => btn.remove());

        let confirmButton = document.createElement("button");
        confirmButton.classList.add("confirm-btn");
        confirmButton.textContent = "Confirm";
        confirmButton.addEventListener("click", reservationCompleted);

        let cancelButton = document.createElement("button");
        cancelButton.classList.add("cancel-btn");
        cancelButton.textContent = "Cancel";
        cancelButton.addEventListener("click", reservationCancelled);

        copy.appendChild(confirmButton);
        copy.appendChild(cancelButton);

        confirmReservation.appendChild(copy);

        let infoList = event.target.parentElement.parentElement;
        let elements = Array.from(infoList.children);

        elements.forEach(e => e.remove());

 
    }

    function reservationCompleted(event) {
        event.target.parentElement.remove();
        nextButton.disabled = false;

        verificationField.classList.add("reservation-confirmed");
        verificationField.textContent = "Confirmed."

    }

    function reservationCancelled(event) {
        event.target.parentElement.remove();
        nextButton.disabled = false;

        verificationField.classList.add("reservation-cancelled");
        verificationField.textContent = "Cancelled."

    }
    

    }



    
    
