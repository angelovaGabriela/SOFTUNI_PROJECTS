'use strict'

function modifyArray (commands) {

    let initialValue = 1;
    let finalArray = [];
    

    for(let action of commands) {
        action === "add" 
        ? finalArray.push(initialValue)
        : finalArray.pop();

        initialValue++;

    }

    console.log(finalArray.some(e => e) 
    ? finalArray.join("\n") : "Empty");

}


// modifyArray (['add', 'add', 'add', 'add']);
// modifyArray(['add', 'add', 'remove', 'add', 'add']);
modifyArray(['remove', 'remove', 'remove']);