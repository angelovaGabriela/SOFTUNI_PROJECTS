import { html } from "../api/lib.js";
import { createSubmitHandler } from "../api/utils.js";
import { register } from "../api/user.js"

export function showRegister(ctx) {
    ctx.render(registerTemplate(createSubmitHandler(onRegister)));

    async function onRegister({email, password, ["re-password"] : repeatPassword}) {
        if ([email, password, repeatPassword].some(e => e == "")) {
            return alert("All fields are required!");
        }
        if (password !== repeatPassword) {
            return alert("Passwords don't match!");
        }

        await register(email, password);
        ctx.updateNav();
        ctx.page.redirect("/");
    }
}

function registerTemplate(handler) {
    return html ` <section id="register">
    <div class="form">
      <h2>Register</h2>
      <form @submit=${handler} class="register-form">
        <input
          type="text"
          name="email"
          id="register-email"
          placeholder="email"
        />
        <input
          type="password"
          name="password"
          id="register-password"
          placeholder="password"
        />
        <input
          type="password"
          name="re-password"
          id="repeat-password"
          placeholder="repeat password"
        />
        <button type="submit">register</button>
        <p class="message">Already registered? <a href="/login">Login</a></p>
      </form>
    </div>
  </section>
    `
}