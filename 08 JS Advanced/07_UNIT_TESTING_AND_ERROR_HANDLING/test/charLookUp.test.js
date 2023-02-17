const { expect } = require('chai');
const { lookupChar } = require('../charLookUp');


describe('test of lookupChar finctionalities', () => {
    it('if the first param is NOT a string must return undefined', () => {
        expect(lookupChar(9874, 2)).to.be.equal(undefined);
        expect(lookupChar([89, 7, 8], 2)).to.be.equal(undefined);
        expect(lookupChar({}, 2)).to.be.equal(undefined);
    });

    it('if the second param is NOT a number must return undefined', () => {

        expect(lookupChar("Gabriela", "Angelova")).to.be.equal(undefined);
        expect(lookupChar("Gabriela", [9, 9, 0])).to.be.equal(undefined);
        expect(lookupChar("Gabriela", {})).to.be.equal(undefined);
        expect(lookupChar("Gabriela", 2.4)).to.be.equal(undefined);
    })

    it('if both params are the correct type, but the index has a negative value must return "Incorrect index"', () => {
        expect(lookupChar("Pug", -1)).to.be.equal("Incorrect index");
    });

    it('if both params are the correct type, but the index value is bigger than the string length must return "Incorrect index"', () => {
        expect(lookupChar("Pug", 4)).to.be.equal("Incorrect index");
    });

    it('if both params are the correct type, but the index value is equal to the string length must return "Incorrect index"', () => {
        expect(lookupChar("Pug", 3)).to.be.equal("Incorrect index");
    });

    it('if everything is fine must return the character "g"', ()=> {
        expect(lookupChar("Pug", 2)).to.be.equal("g");
    });
})