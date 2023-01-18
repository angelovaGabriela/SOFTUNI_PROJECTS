'use strict'

function sameNumbers (number) {
    let numberAsString = number.toString();
    let firstDigit = numberAsString[0];
    let isSame = true;
    let sum = 0;
    for(let i = 0; i < numberAsString.length; i++) {

        if(firstDigit !== numberAsString[i]) {
            isSame = false;
        } 

        sum += Number(numberAsString[i]);
       
    }
    console.log(isSame);
    console.log(sum);

}

sameNumbers(1234);