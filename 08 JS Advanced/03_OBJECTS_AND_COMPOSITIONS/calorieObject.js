'use stict'

function calorieobject (arrayOfStrings) {

    let object = {};


    for(let i = 0; i < arrayOfStrings.length; i += 2) {

    
        object[arrayOfStrings[i]] = Number(arrayOfStrings[i + 1]);

    }

    console.log(object);
}


calorieobject(['Potato', '93', 'Skyr', '63',
'Cucumber', '18', 'Milk', '42']);