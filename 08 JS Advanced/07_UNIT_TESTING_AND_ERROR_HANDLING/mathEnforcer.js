let mathEnforcer = {

    addFive: function (num) {

        if (typeof (num) !== 'number') {

            return undefined;

        } return num + 5;
    },
    subtractTen: function (num) {
        if (typeof (num) !== 'number') {
            return undefined;
        }
        return num - 10;
    },
    sum: function (num1, num2) {
        if (typeof (num1) !== 'number' || typeof (num2) !== 'number') {
            return undefined;
        }
        return num1 + num2;
    }
};

// module.exports = {
//     addFive: mathEnforcer.addFive,
//     subtractTen: mathEnforcer.subtractTen,
//     sum: mathEnforcer.sum,

// }

module.exports = mathEnforcer;
