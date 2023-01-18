'use strict'

function solve(arrayOfNumbers) {

    arrayOfNumbers.sort((a, b) => a - b);

    let smallest = arrayOfNumbers[0];
    let secondSmallest = arrayOfNumbers[1];
   
    console.log(smallest + ' ' + secondSmallest);


}



solve([30, 15, 50, 5]);