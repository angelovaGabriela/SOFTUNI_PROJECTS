'use strict'

function townPopulation(collection) {

const towns = {};

for (let townAsString of collection) {

    let [name, population] = townAsString.split(' <-> ');
    population = Number(population);
    
    if (towns[name] != undefined) {
        population += towns[name];
    } else {
        towns[name] = population;
    }
}

    for (let town in towns) {
        console.log(`${town} : ${towns[town]}`);
    }

 }

townPopulation(['Istanbul <-> 100000',
'Honk Kong <-> 2100004',
'Jerusalem <-> 2352344',
'Mexico City <-> 23401925',
'Istanbul <-> 1000']);