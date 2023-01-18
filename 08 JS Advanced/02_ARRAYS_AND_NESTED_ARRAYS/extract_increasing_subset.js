'use strict'

function extractIncreasingSubset(numbersArray) {
    let biggest = 0;
    let finalArray = [];

    for(let i = 0; i < numbersArray.length; i++) {
        let currentNumber = Number(numbersArray[i]);
    
        if(currentNumber >= biggest) {
            finalArray.push(currentNumber);
            biggest = currentNumber;
    }

    
}

    return finalArray;


}

console.log(extractIncreasingSubset([1, 3, 8, 4, 10, 12, 3, 2, 24]));