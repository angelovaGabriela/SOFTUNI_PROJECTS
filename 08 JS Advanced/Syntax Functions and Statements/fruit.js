'use strict'

function fruit(fruit, grams, price) {
    let weightInKg = grams / 1000;

    let money = price * weightInKg

    console.log(`I need $${money.toFixed(2)} to buy ${weightInKg.toFixed(2)} kilograms ${fruit}.`);
}

fruit('orange', 2500, 1.80);