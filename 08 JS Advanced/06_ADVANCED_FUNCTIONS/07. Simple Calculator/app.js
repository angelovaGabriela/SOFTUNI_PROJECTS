function calculator() {
    let firstNumber;
    let secondNumber;
    let resultField;

    let commands = {
        init: function(selector1, selector2, selector3) {
            firstNumber = document.querySelector(selector1);
            secondNumber = document.querySelector(selector2);
            resultField = document.querySelector(selector3);
        }, 
        add: function() {
            resultField.value = Number(firstNumber.value) + Number(secondNumber.value);
        },
        subtract: function() {
            resultField.value = Number(firstNumber.value) - Number(secondNumber.value);
        }
    }

    return commands;
}


const calculate = calculator();
calculate.init('#num1', '#num2', '#result'); 




