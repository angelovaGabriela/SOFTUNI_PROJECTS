console.log('Hello Node.js!');

const sum = require('./calculator');

const executeCalculation = require('./third-party-modules')

require('./core-modules');

console.log(sum(1,6));

executeCalculation();


// run
// npm install - to get the node_modules folder
// npm start 