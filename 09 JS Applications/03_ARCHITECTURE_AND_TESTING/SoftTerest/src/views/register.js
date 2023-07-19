import { register } from '../api/user.js'

const section = document.getElementById("registerView");
const form = section.querySelector("form");
form.addEventListener("submit", onSubmit)

let contextStealer = null;

export function showRegister(context) {
    contextStealer = context;
    context.showSection(section)
}

async function onSubmit(event) {
    event.preventDefault();

    const formData = new FormData(form);
    const { email, password, repeatPassword } = Object.fromEntries(formData);

    if (password !== repeatPassword) {
        alert("Passwords doesn't match!")
    } else {
        await register(email, password);
        contextStealer.goTo("/");
    }
}