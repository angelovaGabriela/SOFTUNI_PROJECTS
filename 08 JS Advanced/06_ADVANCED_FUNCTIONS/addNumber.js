'use strict' 

function solution(n1) {
    let sum = 0;

    return function (n2) {
        return sum = n1 + n2;
    }
}

console.log('test');

let add5 = solution(5); 

console.log(add5(2)); 

console.log(add5(3)); 