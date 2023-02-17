const { expect } = require('chai');
const { assert } = require('chai');
let mathEnforcer = require('../mathEnforcer');

// const {addFive, subtractTen, sum} =  require('../mathEnforcer');

describe('test mathEnforcer functionalities', function () {

    describe('test addFive functionalities', function() {
        it('if input is different than number must return unefined', function() {
            expect(mathEnforcer.addFive("string")).to.be.equal(undefined);
            expect(mathEnforcer.addFive([2, 3])).to.be.equal(undefined);
            expect(mathEnforcer.addFive({})).to.be.equal(undefined);
            expect(mathEnforcer.addFive(true)).to.be.equal(undefined);
            expect(mathEnforcer.addFive(false)).to.be.equal(undefined);
        });
        it('calculate test with a positive number', function() {
            expect(mathEnforcer.addFive(2)).to.be.equal(7);
        });
        it('calculate test with a negative number', function() {
            expect(mathEnforcer.addFive(-2)).to.be.equal(3);
        });
        it('calculate test with a floating point number', function () {
           assert.closeTo(mathEnforcer.addFive(4.44444), 9.44, 0.01);
        });

    });

    describe('test subtractTen functionalities', function() {
        it('input param different than number must return undefined', function() {
            expect(mathEnforcer.subtractTen("string")).to.be.equal(undefined);
            expect(mathEnforcer.subtractTen([1, 3, 4])).to.be.equal(undefined);
            expect(mathEnforcer.subtractTen({})).to.be.equal(undefined);
            expect(mathEnforcer.subtractTen(true)).to.be.equal(undefined);
        });

        it('calculate test with positive number', function() {
            expect(mathEnforcer.subtractTen(12)).to.be.equal(2);
        });

        it('calculate test with negative number', function() {
            expect(mathEnforcer.subtractTen(-8)).to.be.equal(-18);
        });

        it('calculate test with floating point number', function() {
            assert.closeTo(mathEnforcer.subtractTen(12.5), 2.5, 0.01);
           });

    });


    describe('test sum functionalities', function() {
        it('first param different than number, must return undefined', function() {
            expect(mathEnforcer.sum("string", 7)).to.be.equal(undefined);
            expect(mathEnforcer.sum([], 7)).to.be.equal(undefined);
            expect(mathEnforcer.sum({}, 7)).to.be.equal(undefined);
            expect(mathEnforcer.sum(true, 7)).to.be.equal(undefined);
        });

        it('second param different than number, must return undefined', function() {
            expect(mathEnforcer.sum(7, "string")).to.be.equal(undefined);
            expect(mathEnforcer.sum(7, [2,3])).to.be.equal(undefined);
            expect(mathEnforcer.sum(7,{})).to.be.equal(undefined);
            expect(mathEnforcer.sum(7, false)).to.be.equal(undefined);
        });

        it('calculate test with positive numbers', function() {
            expect(mathEnforcer.sum(2,1)).to.be.equal(3);
        });

        it('calculate test with negative numbers', function() {
            expect(mathEnforcer.sum(-2,-1)).to.be.equal(-3);
        });

        it('calculate test with positive and negative number', function() {
            expect(mathEnforcer.sum(2, -1)).to.be.equal(1);
        });

        it('calculate test with floating point numbers', function() {
            assert.closeTo(mathEnforcer.sum(2.2, 1.2), 3.4, 0.01);
        });
    });

});




