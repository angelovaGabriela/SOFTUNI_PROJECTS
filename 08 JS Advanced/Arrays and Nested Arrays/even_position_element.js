'use strict'

function solve(array) {

    let result = [];

    for (let i = 0; i < array.length; i++) {
        if (i % 2 === 0) {
            let element = array[i];
            result.push(element);
        }
    }

    console.log(result.join(' '));
}

solve(['5', '10']);