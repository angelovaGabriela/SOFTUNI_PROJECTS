'use strict'

function calc() {

    let firstNumber = Number(document.getElementById('num1').value);
    let secondNumber = Number(document.getElementById('num2').value);

    let sum = document.getElementById('sum');

    sum.value = firstNumber + secondNumber;
}
