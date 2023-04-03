window.addEventListener("load", solve);

function solve() {
  let make = document.getElementById("make");
  let model = document.getElementById("model");
  let productionYear = document.getElementById("year");
  let fuelType = document.getElementById("fuel");
  let originalCost = document.getElementById("original-cost");
  let sellingPrice = document.getElementById("selling-price");

  let publishButton = document.getElementById("publish");
  publishButton.addEventListener("click", publishInfo);

  let formInfoSection = document.getElementById("table-body");
  let soldCarsSection = document.getElementById("cars-list");
 
  let totalPrice = document.getElementById("profit");

  let finalPrice = 0;
 
  function publishInfo(event) {

    event.preventDefault(); 

    let makeValue = make.value;
    let modelValue = model.value;
    let productionYearValue = productionYear.value;
    let fuelTypeValue = fuelType.value;
    let originalCostValue = originalCost.value;
    let sellingPriceValue = sellingPrice.value;

    if (!makeValue || !modelValue || !productionYearValue || !fuelTypeValue || !originalCostValue || !sellingPriceValue) {
      return;
    }

    createElements(makeValue, modelValue, productionYearValue, fuelTypeValue, originalCostValue, sellingPriceValue);
    clearInputFields();

  }


  function createElements(makeValue, modelValue, productionYearValue, fuelTypeValue, originalCostValue, sellingPriceValue) {

    let tr = document.createElement("tr");
    tr.classList.add("row");

    let td1 = document.createElement("td");
    td1.textContent = makeValue;

    let td2 = document.createElement("td");
    td2.textContent = modelValue;

    let td3 = document.createElement("td");
    td3.textContent = productionYearValue;

    let td4 = document.createElement("td");
    td4.textContent = fuelTypeValue;

    let td5 = document.createElement("td");
    td5.textContent = originalCostValue;

    let td6 = document.createElement("td");
    td6.textContent = sellingPriceValue;

    let buttonsBox = document.createElement("td");

    let editButton = document.createElement("button");
    editButton.classList.add("action-btn", "edit");
    editButton.textContent = "Edit";
    editButton.addEventListener('click', editInfo);

    let sellButton = document.createElement("button");
    sellButton.classList.add("action-btn", "sell");
    sellButton.textContent = "Sell";
    sellButton.addEventListener('click', soldCars);

    appendElements(tr, td1, td2, td3, td4, td5, td6, buttonsBox, editButton, sellButton);

  }
  function appendElements(tr, td1, td2, td3, td4, td5, td6, buttonsBox, editButton, sellButton) {
    formInfoSection.appendChild(tr);

    tr.appendChild(td1);
    tr.appendChild(td2);
    tr.appendChild(td3);
    tr.appendChild(td4);
    tr.appendChild(td5);
    tr.appendChild(td6);
    tr.appendChild(buttonsBox);

    buttonsBox.appendChild(editButton);
    buttonsBox.appendChild(sellButton);


  }
  function clearInputFields() {
    make.value = "";
    model.value = "";
    productionYear.value = "";
    fuelType.value = "";
    originalCost.value = "";
    sellingPrice.value = ""; 
  }
  function editInfo(event) {

    let collection = Array.from(event.target.parentElement.parentElement.children);
    let makeEdit = collection[0].textContent;
    let modelEdit = collection[1].textContent;
    let yearEdit = collection[2].textContent;
    let fuelEdit = collection[3].textContent;
    let originalPriceEdit = collection[4].textContent;
    let sellingPriceEdit = collection[5].textContent;

    make.value = makeEdit;
    model.value = modelEdit;
    productionYear.value = yearEdit;
    fuelType.value = fuelEdit;
    originalCost.value = originalPriceEdit;
    sellingPrice.value = sellingPriceEdit;

    event.target.parentElement.parentElement.remove();
  }
  function soldCars(event) {
    let collection = Array.from(event.target.parentElement.parentElement.children);
    let makeEdit = collection[0].textContent;
    let modelEdit = collection[1].textContent;
    let yearEdit = collection[2].textContent;
    let originalPriceEdit = parseFloat(collection[4].innerHTML);
    let sellingPriceEdit = parseFloat(collection[5].innerHTML);

    let li = document.createElement("li");
    li.classList.add("each-list");

    let span1 = document.createElement("span");
    span1.textContent = makeEdit + " " + modelEdit;

    let span2 = document.createElement("span");
    span2.textContent = yearEdit;

    let span3 = document.createElement("span");
    let profit = parseFloat(sellingPriceEdit) - parseFloat(originalPriceEdit);
    span3.textContent = Math.abs(profit);

    soldCarsSection.appendChild(li);
    li.appendChild(span1);
    li.appendChild(span2);
    li.appendChild(span3);

    event.target.parentElement.parentElement.remove();

    finalPrice += profit;
  
    totalPrice.innerHTML = "";
    totalPrice.innerHTML = finalPrice.toFixed(2);

  }
}
