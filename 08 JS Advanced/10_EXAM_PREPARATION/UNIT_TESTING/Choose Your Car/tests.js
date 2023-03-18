let chooseYourCar = require('./chooseYourCar');
const { assert, expect } = require("chai")

// describe ("test", () => {
//     it("test settings", () => {
//         assert.equal(1,1)
//     })
// })

describe("testing chooseYourCar functionalities", () => {
    describe("choosingType", () => {
        it("Should throw an error if the year is invalid", () => {
           let yearBefore1900 = function () {
            chooseYourCar.choosingType("Sedan", "blue", 1890);
           }
           let yearAfter2022 = function () {
            chooseYourCar.choosingType("Sedan", "blue", 2023);
           }
           assert.throws(yearBefore1900, Error,  "Invalid Year!");
           assert.throws(yearAfter2022, Error,  "Invalid Year!");
        })
        it("Should throw an error if the type of the car is NOT Sedan", () => {
            let typeNotSedan = function () {
                chooseYourCar.choosingType("Mercedes-Benz", "white", 2022);
               }
               assert.throws(typeNotSedan, Error,  "This type of car is not what you are looking for." );
        })
        it("Should return a message which approves the car", () => {
            expect(chooseYourCar.choosingType("Sedan", "blue", 2010)).to.be
            .equal( "This blue Sedan meets the requirements, that you have.");

            expect(chooseYourCar.choosingType("Sedan", "white", 2011)).to.be
            .equal( "This white Sedan meets the requirements, that you have.");
        })
        it("Should return a message which denies the car", () => {
            expect(chooseYourCar.choosingType("Sedan", "black", 2009)).
            to.be.equal("This Sedan is too old for you, especially with that black color.");
        })
    })
    describe("brandName", () => {
        it("Should throw an error if the input is invalid", () => {
            let isNotArray = function () {
                chooseYourCar.brandName("Sedan", 2);
               }
            let isNotANumber = function () {
                chooseYourCar.brandName(["BMW", "Toyota", "Peugeot"], "2");
               }
            let invalidIndex = function () {
                chooseYourCar.brandName(["BMW", "Toyota", "Peugeot"], -1);
               }
               assert.throws(isNotArray, Error, "Invalid Information!");
               assert.throws(isNotANumber, Error, "Invalid Information!");
               assert.throws(invalidIndex, Error, "Invalid Information!");
        })
        it("Should return BMW, Toyota", () => {
            expect(chooseYourCar.brandName(["BMW", "Toyota", "Peugeot"], 2))
            .to.be.equal('BMW, Toyota');
        })
    })
    describe("carFuelConsumption", () => {
        it("Should return an error of the inputs are invalid", () => {
            let notANumber1 = function () {
                chooseYourCar.carFuelConsumption("243",2023);
               }
            let notANumber2 = function () {
                chooseYourCar.carFuelConsumption(2023, "udfhkf");
               }
            let negative1 = function () {
                chooseYourCar.carFuelConsumption(-1, 738);
               }
            let negative2 = function () {
                chooseYourCar.carFuelConsumption(2023, -1);
               }

            let zero1 = function () {
                chooseYourCar.carFuelConsumption(0, 2023);
               }
            let zero2 = function () {
                chooseYourCar.carFuelConsumption(2023, 0);
               }

               assert.throws(zero1, Error,  "Invalid Information!");
               assert.throws(zero2, Error,  "Invalid Information!");
               assert.throws(negative1, Error,  "Invalid Information!");
               assert.throws(negative2, Error,  "Invalid Information!");
               assert.throws(notANumber2, Error,  "Invalid Information!");
               assert.throws(notANumber1, Error,  "Invalid Information!");
        })
        it("Should return a message for efficient car", () => {
            expect(chooseYourCar.carFuelConsumption(29, 1.96))
            .to.be.equal(`The car is efficient enough, it burns 6.76 liters/100 km.`)
       
            expect(chooseYourCar.carFuelConsumption(28, 1.96))
            .to.be.equal(`The car is efficient enough, it burns 7.00 liters/100 km.`)
        })
        it("Should return a message for NOT efficient car", () => {
            expect(chooseYourCar.carFuelConsumption(24, 1.96))
            .to.be.equal(`The car burns too much fuel - 8.17 liters!`)
        })

    })
}) 