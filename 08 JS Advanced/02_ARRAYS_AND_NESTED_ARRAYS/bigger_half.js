'use strict'

function biggerHalf(array) {

    array.sort((a,b) => a - b);

    array.splice(0, array.length / 2);

    console.log(array);
    
}

biggerHalf([4, 7, 2, 5]);