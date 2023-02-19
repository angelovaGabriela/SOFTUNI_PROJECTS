const { expect, assert } = require('chai');
const { describe } = require('mocha');
const PaymentPackage = require('../paymentPackage');


describe("test PaymentPackage functionalities", () => {
    let object = new PaymentPackage("Marketing", 7889);

    describe("test name property", () => {
        it("if name is empty string, must throw an error", () => {
            let emptyName = function () { new PaymentPackage("", 20) };
            assert.throws(emptyName, Error, 'Name must be a non-empty string');
        });
        it("if name type is different than string, must throw an error", () => {
            let number = function () { new PaymentPackage(2, 5) };
            let array = function () { new PaymentPackage(["Gabi"], 8) };
            let object = function () { new PaymentPackage({}, 8) };
            let boolean = function () { new PaymentPackage(true, 6) };

            assert.throws(number, Error, 'Name must be a non-empty string');
            assert.throws(array, Error, 'Name must be a non-empty string');
            assert.throws(object, Error, 'Name must be a non-empty string');
            assert.throws(boolean, Error, 'Name must be a non-empty string');

        });

        it("if name value is a string, must return Marketing", () => {
            expect(object.name).to.be.equal("Marketing");
        });

        it("must work when I change the name", () => {
            object.name = "Denis";
            expect(object.name).to.be.equal("Denis");
        })
    });

    describe("test value property", () => {
        it("if value is a negative number, must throw an error", () => {
            let negative = function () { new PaymentPackage("Gabi", -1) }
            assert.throws(negative, Error, 'Value must be a non-negative number');
        });


        it("if value is different than number, must throw an error", () => {
            let string = function () { new PaymentPackage("Gabi", "Angelova") };
            let array = function () { new PaymentPackage("Gabi", [7]) };
            let object = function () { new PaymentPackage("Gabi", {}) };
            let boolean = function () { new PaymentPackage("Gabi", true) };

            assert.throw(string, Error, 'Value must be a non-negative number');
            assert.throw(array, Error, 'Value must be a non-negative number');
            assert.throw(object, Error, 'Value must be a non-negative number');
            assert.throw(boolean, Error, 'Value must be a non-negative number');
        });

        it("if value is valid, must return 7889", () => {
            expect(object.value).to.be.equal(7889);
            expect(object.value = 0).to.be.equal(0);
        });

        it("if I change the value, must work", () => {
            object.value = 10;
            expect(object.value).to.be.equal(10);
        })
    });


    describe("test VAT property", () => {
        it("if VAT is differnt than number, must throw an error", () => {

            let string = function () { object.VAT = "string" };
            let boolean = function () { object.VAT = true };
            let array = function () { object.VAT = ["array"] };
            let objects = function () { object.VAT = {}, {} };


            assert.throws(string, Error, 'VAT must be a non-negative number');
            assert.throws(boolean, Error, 'VAT must be a non-negative number');
            assert.throws(array, Error, 'VAT must be a non-negative number');
            assert.throws(objects, Error, 'VAT must be a non-negative number');
        });

        it("if VAT is a negative value, must throw error", () => {
            let negative = function () { object.VAT = -1 };
            assert.throws(negative, Error, 'VAT must be a non-negative number');
        });

        it("VAT defaut", () => {
            expect(object.VAT).to.be.equal(20);
        });

        it("if VAT has valid newValue, must return 9", () => {
            expect(object.VAT = 9).to.be.equal(9);
        })
    });

    describe("test active status property", () => {
        it("if active value is different than boolean, must return error", () => {
            let string = function () { object.active = "Gabi" };
            let number = function () { object.active = 8 };
            let array = function () { object.active = [false] };
            let objects = function () { object.active = {} };

            assert.throws(string, Error, "Active status must be a boolean");
            assert.throws(number, Error, "Active status must be a boolean");
            assert.throws(array, Error, "Active status must be a boolean");
            assert.throws(objects, Error, "Active status must be a boolean");
        });

        it("if active has a boolean value must return false", () => {
            expect(object.active = false).to.be.equal(false);
        });

        it("active must return default value true", () => {
            let defaultObj = new PaymentPackage("Gabi", 7);
            expect(defaultObj.active).to.be.equal(true);
        });

    });

    describe("test toString method", () => {
        it("if the status is false, must return a specific message ", function () {
            let inactivePackage = new PaymentPackage('Consultation', 800);
            inactivePackage.active = false;
            let expectedMessage =
                [`Package: Consultation` + ' (inactive)',
                    `- Value (excl. VAT): 800`,
                    `- Value (VAT 20%): 960`];

            assert.equal(inactivePackage.toString(), expectedMessage.join('\n'));
        });

        it("if the status is true, must return a specific message ", () => {
            let package = new PaymentPackage('HR Services', 1500);
            let expectedMessage =
                [`Package: HR Services`,
                    `- Value (excl. VAT): 1500`,
                    `- Value (VAT 20%): 1800`];

            assert.equal(package.toString(), expectedMessage.join('\n'));
        });

        it("if the values are 0, must return a specific message ", () => {
            let obj = new PaymentPackage('HR Services', 0);
            obj.VAT = 0;
            let expectedMessage =
                [`Package: HR Services`,
                    `- Value (excl. VAT): 0`,
                    `- Value (VAT 0%): 0`];

            assert.equal(obj.toString(), expectedMessage.join('\n'));
        });
    });

})