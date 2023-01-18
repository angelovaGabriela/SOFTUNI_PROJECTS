'use strict'
// first solution
function largestNumber (num1, num2, num3) {
    let result;

    if(num1 >= num2 && num1 >= num3) {
        result = num1
    } else if (num2 >= num1 && num2 >= num3) {
        result = num2
    } else {
        result = num3
    }

    console.log(`The largest number is ${result}.`);
}

largestNumber(5, -3, 16);
largestNumber(-3, -5, -22.5);

// secound solution
function biggestNumber (...param) {
    console.log(`The largest number is ${Math.max(...param)}.`);
}

biggestNumber(5, -3, 16);
biggestNumber(-3, -5, -22.5);

