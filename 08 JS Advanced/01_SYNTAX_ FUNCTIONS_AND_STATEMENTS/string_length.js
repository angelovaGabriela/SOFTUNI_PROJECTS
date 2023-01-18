'use strict'

function solve(string1, string2, string3) {
    let length1 = string1.length;
    let length2 = string2.length;
    let length3 = string3.length;

    let sum = length1 + length2 + length3;
    let average = Math.floor(sum / 3);

    console.log(sum);
    console.log(average);
}

solve('chocolate', 'ice cream', 'cake');