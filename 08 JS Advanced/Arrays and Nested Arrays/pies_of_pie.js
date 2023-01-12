'use strict'

function piesOfPie(pies, flavorOne, flavorTwo) {

    let start = pies.indexOf(flavorOne);
    let end = pies.indexOf(flavorTwo) + 1;

    let result = pies.slice(start, end);

    console.log(result)
}

piesOfPie(['Pumpkin Pie',

'Key Lime Pie',

'Cherry Pie',

'Lemon Meringue Pie',

'Sugar Cream Pie'],

'Key Lime Pie',

'Lemon Meringue Pie');