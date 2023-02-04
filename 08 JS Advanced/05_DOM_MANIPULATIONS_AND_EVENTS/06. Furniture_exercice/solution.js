'use strict'
function solve() {

let buttons = document.querySelectorAll("button");
buttons[0].addEventListener("click", generate);
buttons[1].addEventListener("click", buy);

function generate() {

 let currentItems = JSON.parse(document.querySelectorAll("textarea")[0].value);
 let tableBody = document.getElementsByTagName("tbody")[0];
 
 for(let item of currentItems) {
  let tableRow = document.createElement("tr"); // remove the extra lines separators
  tableRow.innerHTML = `<td> 
                          <img src="${item.img}">
                        </td>
                        <td>
                          <p>${item.name}<\p>
                        </td>
                        <td>
                          <p>${item.price}<\p>
                        </td>
                        <td>
                          <p>${item.decFactor}</p>
                        </td>
                        <td>
                          <input type="checkbox">
                        </td>
                      `;

  tableBody.appendChild(tableRow);

 }

}
function buy() {
  let resultArea = document.querySelectorAll("textarea")[1];
let table = Array.from(document.querySelectorAll("tbody tr"));
let result = [];

for(let element of table) {
  if(element.querySelector("input[type=checkbox]:checked")) {

    let tableData = Array.from (element.querySelectorAll("td"));
    let info = {
      name: tableData[1].children[0].textContent,
      price: tableData[2].children[0].textContent,
      decorationFactor: tableData[3].children[0].textContent,
    
    };
  
    result.push(info);
   
  }
}

let names = "";
let totalPrice = 0;
let decFactor = 0;

for(let i = 0; i < result.length; i++) {
  let item = result[i];
  let separator = i == result.length - 1 ? "" : ", ";
  
  names += item.name + ", ",
   totalPrice += Number(item.price);
   decFactor += Number(item.decFactor);
}
decFactor /= result.length; // toCheck -- returns NaN 

resultArea.value = `Bought furniture: ${names}\nTotal price: ${totalPrice.toFixed(2)}\nAverage decoration factor: ${decFactor}`


}
 


}