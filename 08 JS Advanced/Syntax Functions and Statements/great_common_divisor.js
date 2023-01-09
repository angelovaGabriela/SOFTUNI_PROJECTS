'use strict'

function GCD(first, second) {
    let result = first % second;

    while(result != 0) {
        first = second;
        second = result;

        result = first % second; // loop iteration 
    }

    console.log(second);
}
GCD(2154, 458);