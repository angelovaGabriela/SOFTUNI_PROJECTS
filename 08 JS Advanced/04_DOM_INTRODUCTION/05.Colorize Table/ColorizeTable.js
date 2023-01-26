'use strict'

function colorize() {
    let tableRows = Array.from(document.querySelectorAll('table tr'));

    for(let i = 1; i < tableRows.length; i++) {
        if(i % 2 !== 0) {
            tableRows[i].style.backgroundColor = "Teal";
        }
    }
}