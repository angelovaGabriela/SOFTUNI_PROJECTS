'use strict'

function argumentInfo(...params) {

    let result = {};

    for (let element of params) {
       let type = typeof(element);
        console.log(`${type}: ${element}`)
    
        if(!result.hasOwnProperty(type)) {
            result[type] = 0; 
        } 
        result[type] = result[type] + 1;
    
    }

    let buffer = "";

    for(let [key, value] of Object.entries(result)) {
        buffer += `${key} = ${value}\n`
    }

    console.log(buffer);

}


argumentInfo('cat', 42, function () { console.log('Hello world!'); });