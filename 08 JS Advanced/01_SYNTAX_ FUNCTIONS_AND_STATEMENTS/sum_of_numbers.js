'use strict'

function solve(m, n) {
 
    let num1 = Number(m);
    let num2 = Number(n)

    let result = 0;
    for(let i = num1; i <= num2; i++) {
        result += i;
    }

    console.log(result);
}

solve('1', '5');









