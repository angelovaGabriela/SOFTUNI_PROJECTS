// describe ("test", () => {
//     it("test settings", () => {
//         assert.equal(1,1)
//     })
// })


let bookSelection = require('./solution');
const { assert, expect } = require("chai")


describe("bookSelection functionalities tests", () => {
    describe("isGenreSuitable", () => {
        it("Should NOT be suitable if the genre is Horror and age is less than 12", () => {

            expect(bookSelection.isGenreSuitable("Horror", 11))
                .to.be.equal(`Books with Horror genre are not suitable for kids at 11 age`);
        });
        it("Should NOT be suitable if the genre is Thriller and age equals 12 ", () => {

            expect(bookSelection.isGenreSuitable("Thriller", 12))
                .to.be.equal(`Books with Thriller genre are not suitable for kids at 12 age`);
        });

        it("Comedy genre is suitable for age under 12", () => {
            expect(bookSelection.isGenreSuitable("Comedy", 9))
                .to.be.equal(`Those books are suitable`);
        });

        it("Documentary genre is suitable for age of 12", () => {
            expect(bookSelection.isGenreSuitable("Documentary", 12))
                .to.be.equal(`Those books are suitable`);
        })

        it("Horror ganre is suitable for age above 12", () => {
            expect(bookSelection.isGenreSuitable("Horror", 13))
                .to.be.equal(`Those books are suitable`);
        });

        it("Thriller ganre is suitable for age above 12", () => {
            expect(bookSelection.isGenreSuitable("Thriller", 17))
                .to.be.equal(`Those books are suitable`);
        })
    })

    describe("isItAffordable", () => {
        it("Should throw an error if the input is invalid", () => {
            let stringInput = function () { bookSelection.isItAffordable("2", "2"); }
            let arrayInput = function () { bookSelection.isItAffordable(2, []); }
            let booleanInput = function () { bookSelection.isItAffordable(2, true); }

            assert(stringInput, Error, "Invalid input");
            assert(arrayInput, Error, "Invalid input");
            assert(booleanInput, Error, "Invalid input");

        })
        it("Should return <You don't have enough money> if the result is less than 0", () => {
            expect(bookSelection.isItAffordable(5, 4)).to.be.equal("You don't have enough money");
        })
        it("Should return the remaining money", () => {
            expect(bookSelection.isItAffordable(4, 5)).to.be.equal(`Book bought. You have 1$ left`)
        })

        it("Should return 0 if the budget and the money are equal", () => {
            expect(bookSelection.isItAffordable(5, 5)).to.be.equal(`Book bought. You have 0$ left`)
        })

    })

    describe("suitableTitles", () => {
        it("Should return error if inputs are invalid", () => {
            let invalidInput = function () { bookSelection.suitableTitles(string, 2) }
            assert(invalidInput, Error, "Invalid input");
        })

        it("Should return an array with <The Da Vinci Code> and <The Da Vinci Code1>", () => {
            let input = [
                { title: "The Da Vinci Code", genre: "Comedy" },
                { title: "The Da Vinci Code1", genre: "Comedy" },
                { title: "The Da Vinci Code2", genre: "Horror" }
            ];

            let result = ["The Da Vinci Code", "The Da Vinci Code1"];
            expect(bookSelection.suitableTitles(input, "Comedy").join(" ")).to.be.equal(result.join(" "));
        });
    })
})

