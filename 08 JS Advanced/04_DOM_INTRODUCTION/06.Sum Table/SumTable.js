'use strict'

function sumTable() {

    let tableRows = document.querySelectorAll('table tbody tr td');
    let totalField = document.querySelector('#sum');
    let total = 0;

    for(let i = 0; i < tableRows.length - 1; i++) {

        if(parseInt(tableRows[i].textContent)) {

            total += Number(tableRows[i].textContent);
        }
    }

    totalField.textContent = total;
}