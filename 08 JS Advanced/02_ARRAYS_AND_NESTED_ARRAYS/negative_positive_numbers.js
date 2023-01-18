'use strict'

function solve(inputArray) {
    let outputArray = [];

    for (let i = 0; i < inputArray.length; i ++) {

        let currentNumber = inputArray[i];

        if (currentNumber < 0) {
            outputArray.unshift(currentNumber);
        } else {
            outputArray.push(currentNumber);
        }
    }

    console.log(outputArray.join('\n'));
}

solve([3, -2, 0, -1]);