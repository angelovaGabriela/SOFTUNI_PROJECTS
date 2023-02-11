'use strict'

function solution(array, type) {
return type === "asc" 
? array.sort((a,b) => a - b)
: array.sort((a,b) => b - a);
}
