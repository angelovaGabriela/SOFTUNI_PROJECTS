'use strict'


function solve(area, vol, input) {

    let cordinates = JSON.parse(input);
    let arrayResult = [];

    for (let info of cordinates) {

        let figure = {
            area: area.call(info),
            volume: vol.call(info)
        };

        arrayResult.push(figure);
    }

    return arrayResult;
}


function area() {

    return Math.abs(this.x * this.y);

};

function vol() {

    return Math.abs(this.x * this.y * this.z);

};


solve(area, vol, `[
    {"x":"1","y":"2","z":"10"},
    {"x":"7","y":"7","z":"10"},
    {"x":"5","y":"2","z":"10"}
    ]`);