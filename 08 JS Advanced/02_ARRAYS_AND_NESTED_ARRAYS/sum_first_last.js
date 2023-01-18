'use strict'

function solve(array) {
    let firstNumber = Number(array[0]);
    let lastNumber = Number(array[array.length - 1]);

    let sum = firstNumber + lastNumber;

    console.log(sum);
}

solve(['5', '10']);

