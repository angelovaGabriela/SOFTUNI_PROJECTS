'use strict'

function listProcessor(inputArr) {

    let collection = [];

    let operations = {
        add: function (string) {
            collection.push(string);
        },  
        remove: function (string) {
            collection = collection.filter(x => x !== string);
          },
         print: function() {
            console.log(collection.join(","));
         }

    }

     for(let info of inputArr) {
        let [command, string] = info.split(" ");
        operations[command](string);
     }
}


listProcessor(['add pesho', 'add george', 'add peter', 'remove peter','print']);