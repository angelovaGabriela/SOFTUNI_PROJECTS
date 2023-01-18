'use strict'

function squareOfStars(input) {

    if (input === undefined) {
        input = 5;
    } 

    const row = "* ".repeat(input).trim();

    for(let i = 0; i < input; i++) {
        console.log(row);
    }
 }

squareOfStars(5);