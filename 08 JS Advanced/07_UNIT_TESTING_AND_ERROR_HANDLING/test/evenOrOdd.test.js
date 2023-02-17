const { expect } = require('chai');
const {isOddOrEven} = require('../evenOrOdd');


describe('test isOddOrEven functionality', ()=> {
    it('input different than string must return undefined', ()=> {
        expect(isOddOrEven(5680)).to.be.equal(undefined);
    });

    it('input string with length 4 must return "even"', () => {
        expect(isOddOrEven("Gabi")).to.be.equal("even");
    });

    it('input string with length 3 must return "odd"', () => {
        expect(isOddOrEven("Pug")).to.be.equal("odd");
    });
} )