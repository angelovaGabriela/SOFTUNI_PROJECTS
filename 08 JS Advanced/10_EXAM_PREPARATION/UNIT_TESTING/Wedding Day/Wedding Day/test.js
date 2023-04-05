const { assert, expect } = require("chai")

// describe ("test", () => {
// it("test settings", () => {
//         assert.equal(1,1)
//     })
// })

let wedding = require('./weddingDay');
const weddingDay = require("./weddingDay");

describe("test for wedding day functionalities", () => {
    describe("pickVenue functionality", () => {

        it("Should throw an error in case of invalid input", () => {
            let invalid1 = function() {
                weddingDay.pickVenue("20", 590, "Sofia");
            }
            let invalid2 = function() {
                weddingDay.pickVenue(20, "590", "Sofia");
            }
            let invalid3 = function() {
                weddingDay.pickVenue(20, 590, 6);
            }
            let invalid4 = function() {
                weddingDay.pickVenue(20, 590, "");
            }
            assert.throw(invalid1, Error, "Invalid Information!");
            assert.throw(invalid2, Error, "Invalid Information!");
            assert.throw(invalid3, Error, "Invalid Information!");
            assert.throw(invalid4, Error, "Invalid Information!");


        })
        it("Sould throw an error for wrong location", () => {
            let invalid = function() {
                weddingDay.pickVenue(20, 590, "Sofia");
            }
            assert.throw(invalid, Error, "The location of this venue is not in the correct area!");
        });
        it("Sould return a message for suitable venue", () => {
            expect(weddingDay.pickVenue(150, 120, "Varna")).to.be.equal("This venue meets the requirements, with capacity of 150 guests and 120$ cover.")
            expect(weddingDay.pickVenue(160, 110, "Varna")).to.be.equal("This venue meets the requirements, with capacity of 160 guests and 110$ cover.")
            
        });
        it("Should return a message for not suitable venue", () => {
            expect(weddingDay.pickVenue(149, 120, "Varna")).to.be.equal("This venue does not meet your requirements!");
            expect(weddingDay.pickVenue(150, 125, "Varna")).to.be.equal("This venue does not meet your requirements!");


        })

    })
    describe("otherSpendings functionality", () => {
        it("Should throw an error in case of invalid input", () => {
            let invalid1 = function() {
                weddingDay.otherSpendings("array", ["pictures"], true);
            }
            let invalid2 = function() {
                weddingDay.otherSpendings(["flowers", "Fabric drapes and curtains"], "Hortensia", true);
            }
            let invalid3 = function() {
                weddingDay.otherSpendings(["flowers", "Fabric drapes and curtains"], ["pictures"], "true");
            }
          
            
            assert.throw(invalid1, Error, "Invalid Information!");
            assert.throw(invalid2, Error, "Invalid Information!");
            assert.throw(invalid3, Error, "Invalid Information!");
        
        });
        it("Sould return price with discount", () => {
            expect(weddingDay.otherSpendings(["flowers", "Fabric drapes and curtains"], ["pictures", "video"], true))
            .to.be.equal("You spend 2465$ for wedding decoration and photography with 15% discount!");
        });
        it("Sould return the price without discount", () => {
            expect(weddingDay.otherSpendings(["flowers", "Fabric drapes and curtains"], ["pictures", "video"], false))
            .to.be.equal("You spend 2900$ for wedding decoration and photography!");
        });
    });
    describe("tableDistribution functionality", () => {
        it("Sould throw an error in case of invalid input", () => {
            let invalid1 = function() {
                weddingDay.tableDistribution("seven", 200);
            }
            
            let invalid2 = function() {
                weddingDay.tableDistribution(-1, 200);
            }
            
            let invalid3 = function() {
                weddingDay.tableDistribution(7, "two hundred");
            }
            let invalid4 = function() {
                weddingDay.tableDistribution(7, -9);
            }

            let invalid5 = function() {
                weddingDay.tableDistribution(7, 0);
            }
            let invalid6 = function() {
                weddingDay.tableDistribution(0, 90);
            }
            
            assert.throw(invalid6, Error, "Invalid Information!");
            
            assert.throw(invalid5, Error, "Invalid Information!");
            
            assert.throw(invalid1, Error, "Invalid Information!");
          
            assert.throw(invalid2, Error, "Invalid Information!");
        
            assert.throw(invalid3, Error, "Invalid Information!");
            
            assert.throw(invalid4, Error, "Invalid Information!");
        });
        it("Should return a message for less than 6 people on a table", () => {
            expect(weddingDay.tableDistribution(15, 3)).to.be.equal("There is only 5 people on every table, you can join some tables.")
        });
        it("Should return a message for 6 people on a table", () => {
            expect(weddingDay.tableDistribution(30, 5)).to.be.equal("You have 5 tables with 6 guests on table.");
        });
        it("Should return a message for more than 6 people on a table", () => {
            expect(weddingDay.tableDistribution(42, 6)).to.be.equal("You have 6 tables with 7 guests on table.");
        });
    });

})
