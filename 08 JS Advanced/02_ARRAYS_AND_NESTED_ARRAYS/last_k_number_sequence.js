'use strict'

function arrayOfNumbers(n, k) {
    let result = [1, 1];

    for (let i = 2; i < n; i++) {
       
        let element = 0;
        let reversed = JSON.parse(JSON.stringify(result)).reverse();
        
        for(let j = 0; j < reversed.length; j++) {
            let current = Number(reversed[j]);
         if(k === j) {
            break;
         }
    
        element += current;
     }
        result.push(element);
    
    }
    return result;
}

