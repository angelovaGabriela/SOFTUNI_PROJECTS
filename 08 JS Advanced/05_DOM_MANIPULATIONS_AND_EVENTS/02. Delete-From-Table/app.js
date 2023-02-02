'use strict'
function deleteByEmail() {
   let input = document.querySelector('input').value;
   let resultElement = document.getElementById('result');
   let customerList = document.querySelectorAll('td:nth-child(2)');

   for (let item of customerList) {
    
    if(item.textContent === input) {
        item.parentNode.remove();
        resultElement.textContent = "Deleted."
        return;
    }
    resultElement.textContent = "Not found.";

   }
}
