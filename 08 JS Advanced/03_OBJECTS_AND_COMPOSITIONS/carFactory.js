'use strict' 
 
function carFactory (car = {}) { 

    let resultCar = {};
    resultCar.model = car.model;

    let engineEnum = {

        "Small engine": { power: 90, volume: 1800 },
        "Normal engine": { power: 120, volume: 2400 },
        "Monster engine": { power: 200, volume: 3500 }

    }

    let carriageEnum = {

        hatchback: function(color) { 
          return { 
            type: "hatchback", 
            color }
        },
        coupe: function (color) {
            return {
                type: "coupe",
                color }
            }
    }


    if (car.power <= 90) {
        resultCar.engine = engineEnum["Small engine"];
    } else if (car.power <= 120) {
        resultCar.engine = engineEnum["Normal engine"];
    } else {
        resultCar.engine = engineEnum["Monster engine"];
    }

   

    resultCar["carriage"] = carriageEnum[car.carriage](car.color);

    let size;
    if (car.wheelsize % 2 === 0) {
        size = car.wheelsize - 1;
    } else {
        size = car.wheelsize;
    }

    resultCar.wheels = [size, size, size, size];

    return resultCar;

}

console.log(carFactory({ model: 'Opel Vectra',
power: 110,
color: 'grey',
carriage: 'coupe',
wheelsize: 17 }));