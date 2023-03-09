// describe ("test", () => {
//     it("test settings", () => {
//         assert.equal(1,1)
//     })
// })

let companyAdministration = require('./companyAdministration');
const { assert, expect } = require("chai")

describe("tests for companyAdministration functionalities", () => {
    describe("hiringEmployee", () => {
        it("Should throw an error if the position is not <Programmer>", () => {

            let actual = function () {
                companyAdministration.hiringEmployee("Gabriela", "Journalist", 5);
            }

            assert.throw(actual, Error, `We are not looking for workers for this position.`);
        } )
        it("Should not be hired because the experience is less than 3 years", () => {
            expect(companyAdministration.hiringEmployee("Gabriela", "Programmer", 2))
            .to.be.equal(`Gabriela is not approved for this position.`);
        })
        
        it("Should be hired because the experience is more than 3 years", () => {
            expect(companyAdministration.hiringEmployee("Gabriela", "Programmer", 5))
            .to.be.equal(`Gabriela was successfully hired for the position Programmer.`);

            expect(companyAdministration.hiringEmployee("Gabriela", "Programmer", 3))
            .to.be.equal(`Gabriela was successfully hired for the position Programmer.`);
        })
        it("Should be hired because the experience is 3 years", () => {
        
            expect(companyAdministration.hiringEmployee("Gabriela", "Programmer", 3))
            .to.be.equal(`Gabriela was successfully hired for the position Programmer.`);
        })
    })

    describe("calculateSalary", () => {
      it("Should throw an error if input is different than number", () => {

        let stringType = function () { 
            companyAdministration.calculateSalary("Pug") 
        }
        assert.throw(stringType, Error, "Invalid hours");
     
        let booleanType = function () { 
            companyAdministration.calculateSalary(true) 
        }
        assert.throw(booleanType, Error, "Invalid hours");
    
    })

    it("Should throw an error if hours are less than 0", () => {
        let actual = function () { 
            companyAdministration.calculateSalary(-1) 
        }
        assert.throw(actual, Error, "Invalid hours");
    })

    it("Should return a total amound of 75 BGN", () => {
        expect(companyAdministration.calculateSalary(5))
        .to.be.equal(75);
    })

    it("Should return a total amound of 3 415 BGN", () => {
        expect(companyAdministration.calculateSalary(161))
        .to.be.equal(3415);
    })

    it("Should return a total amound 2 400 BGN", () => {
        expect(companyAdministration.calculateSalary(160))
        .to.be.equal(2400);
    })
        
    })

    describe("firedEmployee", () => {
        it("Should return an error if the input is invalid", () => {
            let notArray = function () {
                companyAdministration.firedEmployee("Gabriela", 0);
            }


            let notInt = function () {
                companyAdministration.firedEmployee(["Gabriela"], "Pug");
            }
            let negative = function () {
                companyAdministration.firedEmployee(["Gabriela"], -2);
            }

            let biggerThanLength = function () {
                companyAdministration.firedEmployee(["Gabriela"], 2);
            }
            let equalTolength = function () {
                companyAdministration.firedEmployee(["Gabriela"], 1);
            }
            let number = function () {
                companyAdministration.firedEmployee([], 0);
            }

            assert.throw(number, Error, `Invalid input`);
            assert.throw(equalTolength, Error, `Invalid input`);
            assert.throw(biggerThanLength, Error, `Invalid input`);
            assert.throw(negative, Error, `Invalid input`);
            assert.throw(notInt, Error, `Invalid input`);
            assert.throw(notArray, Error, `Invalid input`);
        })

        it(`Should return the array ["Gabriela", "Vincent"]`, () => {
            expect(companyAdministration.firedEmployee(["Gabriela", "Vincent", "Vincent"], 2))
            .to.be.equal("Gabriela, Vincent");
        })
    })
})
























