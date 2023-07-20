import { login } from '../api/user.js'
const section = document.getElementById("loginView");
const form = section.querySelector("form");
form.addEventListener("submit", onSubmit)

let contextStealer = null;
export function showLogin(context) {
    contextStealer = context;
    context.showSection(section);
}

async function onSubmit(event) {
    event.preventDefault();
    const formData = new FormData(form);

    const { email, password } = Object.fromEntries(formData);
    await login(email, password);

    contextStealer.updateNavigate();
    contextStealer.goTo("/catalog");
}