'use strict'
function focused() {
    
    let inputsFields = Array.from(document.querySelectorAll("div input"));

    for(let input of inputsFields) {

        input.addEventListener('focus', onClick);

        function onClick(event) {
            event.target.parentNode.setAttribute("class", "focused");
        };

        input.addEventListener('blur', onBlur)
       
        function onBlur(event){
            event.target.parentNode.removeAttribute("class", "focused");
        };
    }

}