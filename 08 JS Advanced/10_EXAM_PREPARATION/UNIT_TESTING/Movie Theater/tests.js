// describe ("test", () => {
//     it("test settings", () => {
//         assert.equal(1,1)
//     })
// })


let movieTheater = require('./movieTheater');
const { assert, expect } = require("chai");


describe("Tests for movieTheater functionalities", () => {
    describe("ageRestrictions functionality", () => {
        it("Should return the correct message depending on the input", () => {
            expect(movieTheater.ageRestrictions("G")).to.be.equal(`All ages admitted to watch the movie`);
            expect(movieTheater.ageRestrictions("PG")).to.be.equal(`Parental guidance suggested! Some material may not be suitable for pre-teenagers`);
            expect(movieTheater.ageRestrictions("R")).to.be.equal(`Restricted! Under 17 requires accompanying parent or adult guardian`);
            expect(movieTheater.ageRestrictions("NC-17")).to.be.equal(`No one under 17 admitted to watch the movie`);
            expect(movieTheater.ageRestrictions("COOL")).to.be.equal(`There are no age restrictions for this movie`);

        })
    })
    describe("moneySpent functionality", () => {
        it("Should throw an error in case of invalid input", () => {
            let test1 = function () {
                movieTheater.moneySpent("2", [], [])
            }
            let test2 = function () {
                movieTheater.moneySpent(2, {}, [])
            }
            let test3 = function () {
                movieTheater.moneySpent(2, [], "Gabriela")
            }
            assert.throw(test1, Error, "Invalid input");
            assert.throw(test2, Error, "Invalid input");
            assert.throw(test3, Error, "Invalid input");
        })
        it("Should return a message whit a price after discount", () => {
            let priceWithDiscount = movieTheater.moneySpent(3, ["Nachos", "Nachos"], ["Water", "Water"]);
            expect(priceWithDiscount).to.be.equal(`The total cost for the purchase with applied discount is 48.00` )
        })
        it("Should return a message whit a price without discount", () => {
            let priceWithDiscount = movieTheater.moneySpent(2, ["Nachos"], ["Water", "Water"]);
            expect(priceWithDiscount).to.be.equal(`The total cost for the purchase is 39.00`);
        })
    })
    describe("reservation functionalities", () => {
        it("Should throw an error in case of invalid input", () => {
            let test1 = function () {
                movieTheater.reservation({}, 2);
            }
            let test2 = function () {
                movieTheater.reservation([], "2");
            }
            assert.throw(test1, Error, "Invalid input");
            assert.throw(test2, Error, "Invalid input");
        })
        it("Should return row number 2", () => {
            let result = movieTheater.reservation([{ rowNumber: 1, freeSeats: 7 }, { rowNumber: 2, freeSeats: 5 }], 2);
            expect(result).to.be.equal(2);
        }); 
        
    })
})


