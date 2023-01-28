function subtract() {
    let firstNumber = Number(document.getElementById("firstNumber").value);
    let secondNumber = Number(document.getElementById("secondNumber").value);

    let result = firstNumber - secondNumber;

    document.getElementsByName("result").innerText = result;

}