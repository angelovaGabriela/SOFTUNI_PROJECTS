// describe ("test", () => {
//     it("test", () => {
//         assert.equal(1,1);
//     })
// })

const { assert, expect } = require('chai');
const findNewApartment = require('./findApartment');

describe("testing functionalities of findNewApartment", () => {
    describe("isGoodLocation functionality", () => {
        it("Should throw an error in case of invalid input type", () => {

            let input1 = function () {
                findNewApartment.isGoodLocation(2, true);
            }
            let input2 = function () {
                findNewApartment.isGoodLocation("Sofia", "true");
            }

            assert.throw(input1, Error, "Invalid input!")

            assert.throw(input2, Error, "Invalid input!")
        });
        it("Should return a message for inappropriate city ", () => {
            expect(findNewApartment.isGoodLocation("Pleven", true)).to.be.equal("This location is not suitable for you.");
        });
        it("Should return message for suitable appartment", () => {
            expect(findNewApartment.isGoodLocation("Varna", true)).to.be.equal("You can go on home tour!");
        });
        it("Should return return a message for missing public transport in the area", () => {
            expect(findNewApartment.isGoodLocation("Plovdiv", false)).to.be.equal("There is no public transport in area.");
        })
    })
    describe("isLargeEnough functionality", () => {
        it("Should throw an error if the input type is NOT correct", () => {
            let invalid1 = function () {
                findNewApartment.isLargeEnough([40, 50, 60], "8")
            }
            let invalid2 = function () {
                findNewApartment.isLargeEnough([], 8)
            }
            let invalid3 = function () {
                findNewApartment.isLargeEnough("40, 50, 60", 8)
            }

            assert.throw(invalid1, Error, "Invalid input!");

            assert.throw(invalid2, Error, "Invalid input!");

            assert.throw(invalid3, Error, "Invalid input!");
        })
        it("Should return 40, 50, 60", () => {
            expect(findNewApartment.isLargeEnough([40, 50, 60], 40)).to.be.equal('40, 50, 60');
        })
    })
    describe("isItAffordable functionality", () => {
        it("Should throw an error if the input type is invalid", () => {
            let invalid1 = function () {
                findNewApartment.isItAffordable("number", 100);
            }
            let invalid2 = function () {
                findNewApartment.isItAffordable(3, "100");
            }
            assert.throw(invalid1, Error, "Invalid input!");
            assert.throw(invalid2, Error, "Invalid input!");

        })
        it("Should throw an error if the input value is invalid", () => {
            let invalid1 = function () {
                findNewApartment.isItAffordable(0, 100);
            }
            let invalid2 = function () {
                findNewApartment.isItAffordable(3, 0);
            }
            assert.throw(invalid1, Error, "Invalid input!");
            assert.throw(invalid2, Error, "Invalid input!");

        })
        it("Should return a message for impossibility to buy the house", () => {
            expect(findNewApartment.isItAffordable(100, 50)).to.be.equal("You don't have enough money for this house!");
        })
        it("Should return a message for affordable house", () => {
            expect(findNewApartment.isItAffordable(50, 100)).to.be.equal("You can afford this home!");
        })
    })
})