'use strict'

function filter (data, criteria) {

    let employees = JSON.parse(data);
    let count = 0;

    let tokens = criteria.split('-');
    let key = tokens[0];
    let value = tokens[1];



    for(let employee of employees) {

        if(criteria === 'all') {
            console.log(`${count++}. ${employee.first_name} ${employee.last_name} - ${employee.email}`); 
        } else if (employee[key] === value) {
            console.log(`${count++}. ${employee.first_name} ${employee.last_name} - ${employee.email}`);
        }
    } 
}


filter(`[{
    "id": "1",
    "first_name": "Ardine",
    "last_name": "Bassam",
    "email": "abassam0@cnn.com",
    "gender": "Female"
  }, {
    "id": "2",
    "first_name": "Kizzee",
    "last_name": "Jost",
    "email": "kjost1@forbes.com",
    "gender": "Female"
  },  
{
    "id": "3",
    "first_name": "Evanne",
    "last_name": "Maldin",
    "email": "emaldin2@hostgator.com",
    "gender": "Male"
}]`,
    'gender-Female');




    // second solution 
    //function solve(data, criteria) {
//     let parsed = JSON.parse(data);
//     let count = 0;
//     let key = criteria.substring(0, criteria.indexOf('-'));
//     let value = criteria.substring(criteria.indexOf('-') + 1);
    
//     if (criteria === 'all') {
//         parsed.forEach(person =>
//             console.log(`${count++}. ${person.first_name} ${person.last_name} - ${person.email}`)
//         );
//     }
//     else {
//         parsed
//         .filter(person => person[key] === value)
//         .forEach(person =>
//             console.log(`${count++}. ${person.first_name} ${person.last_name} - ${person.email}`)
//         );
//     }
// }