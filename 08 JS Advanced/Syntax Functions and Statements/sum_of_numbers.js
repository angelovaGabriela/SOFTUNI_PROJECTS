'use strict'

function solve(m, n) {
 
    let num1 = Number(m);
    let num2 = Number(n)

    let result;
    for(let i = num1; i < num2; i++) {
        let number = [i];
        result += number;
    }

    return console.log(result);
}

solve('1', '5');









