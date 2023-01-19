'use strict'

function cityTaxes(name, population, treasury) {
    const city = {};
    
    city.name = name;
    city.population = population;
    city.treasury = treasury;
    city.taxRate = 10;
    
    city.collectTaxes = function () {
        return Math.floor(this.treasury += this.population * this.taxRate);
    };

    city.applyGrowth = function (percentage) {
        return Math.floor(this.population += this.population * percentage / 100);
    }

    city.applyRecession = function (percentage) {
        return Math.floor(this.treasury -= this.treasury * percentage / 100);
    }
    
    return city;
    
    }
    
    const city =

cityTaxes('Tortuga',
7000,
15000);
city.collectTaxes();
console.log(city.treasury);
city.applyGrowth(5);
console.log(city.population);
    