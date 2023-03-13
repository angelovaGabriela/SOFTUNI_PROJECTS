// describe ("test", () => {
//     it("test settings", () => {
//         assert.equal(1,1);
//     })
// });

const { assert, expect } = require('chai');
let motorcycleRider = require('./Motorcycle Rider');


describe ("motorcycleRider functionalities", () => {
    describe ("licenseRestriction testing", () => {
        it ("Should throw an error if the input is different than <AM,A1,A2,A>", () => {
            let invalidIput = function () { motorcycleRider.licenseRestriction(""); }
            assert.throw(invalidIput, Error, "Invalid Information!");
        });
        it ("Should return a specific message for <AM> category", () => {

            expect(motorcycleRider.licenseRestriction("AM"))
            .to.be.equal("Mopeds with a maximum design speed of 45 km. per hour, engine volume is no more than 50 cubic centimeters, and the minimum age is 16.");
        })
        it ("Should return a specific message for <A1> category", () => {

            expect(motorcycleRider.licenseRestriction("A1"))
            .to.be.equal("Motorcycles with engine volume is no more than 125 cubic centimeters, maximum power of 11KW. and the minimum age is 16.");
        })
        it ("Should return a specific message for <A2> category", () => {

            expect(motorcycleRider.licenseRestriction("A2"))
            .to.be.equal("Motorcycles with maximum power of 35KW. and the minimum age is 18.");
        })
        it ("Should return a specific message for <A> category", () => {

            expect(motorcycleRider.licenseRestriction("A"))
            .to.be.equal("No motorcycle restrictions, and the minimum age is 24.");
        })
    })
    describe("motorcycleShowroom testing", () => {
        it("Should throw an error of the input is invalid", () => {
            let test1 = function () {
                motorcycleRider.motorcycleShowroom(2,3);
            }
            let test2 = function () {
                motorcycleRider.motorcycleShowroom("m",3);
            }
            let test3 = function () {
                motorcycleRider.motorcycleShowroom([],3);
            }
            let test4 = function () {
                motorcycleRider.motorcycleShowroom(2,[]);
            }
            let test5 = function () {
                motorcycleRider.motorcycleShowroom(2,"8");
            }
            let test6 = function () {
                motorcycleRider.motorcycleShowroom(2,0);
            }


            assert.throw(test1, Error, "Invalid Information!" );
            assert.throw(test2, Error, "Invalid Information!" );
            assert.throw(test3, Error, "Invalid Information!" );
            assert.throw(test4, Error, "Invalid Information!" );
            assert.throw(test5, Error, "Invalid Information!" );
            assert.throw(test6, Error, "Invalid Information!" );
          
        })

        it("Should return message with availableBikes length of 4", () => {

            expect(motorcycleRider.motorcycleShowroom([50, 0, 500, 352, 87], 500))
            .to.be.equal(`There are 4 available motorcycles matching your criteria!`);
        })
    })
    describe("otherSpendings testing", () => {
        it("Should throw an error if the input is invalid", () => {
            let test1 = function () {
                motorcycleRider.otherSpendings("3", ["engine oi"], true);
            }
            let test2 = function () {
                motorcycleRider.otherSpendings(["helmet", "jeans"], 2, true);
            }
            let test3 = function () {
                motorcycleRider.otherSpendings(["helmet", "jeans"], ["engine oi"], "true");
            }
            let test4 = function () {
                motorcycleRider.otherSpendings([], [], 0);
            }
            assert.throw(test1,Error, "Invalid Information!");
            assert.throw(test2,Error, "Invalid Information!");
            assert.throw(test3,Error, "Invalid Information!");
            assert.throw(test4,Error, "Invalid Information!");
      
        })

        it("Should return message for total price $770 without discount", () => {
            expect(motorcycleRider.otherSpendings(["helmet", "helmet", "jacked"], ["engine oil", "color", "tires"], false))
            .to.be.equal(`You spend $770.00 for equipment and consumables!`);
        })
        
        it("Should return message for total price $693 without discount", () => {
            expect(motorcycleRider.otherSpendings(["helmet", "helmet", "jacked"], ["engine oil", "color", "tires"], true))
            .to.be.equal(`You spend $693.00 for equipment and consumables with 10% discount!`);
        })
    })
   
})



