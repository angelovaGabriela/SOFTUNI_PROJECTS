function validate() {
    let input = document.getElementById("email");

    input.addEventListener('change', changed);

    function changed(event) {
        let validEmail = /[a-z]+@[a-z]+\.[a-z]+/g;

        if (validEmail.test(input.value)) {
            event.target.classList = '';
        } else {
            event.target.setAttribute("class", "error");
        }

    }

}