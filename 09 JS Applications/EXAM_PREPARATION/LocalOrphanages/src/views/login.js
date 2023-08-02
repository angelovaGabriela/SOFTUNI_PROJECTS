import { html } from '../api/lib.js'
import { createSubmitHandler } from '../api/utils.js'
import {login} from '../api/user.js'


export async function showLogin(ctx) {
    ctx.render(loginTemplate(createSubmitHandler(onLogin)));

    async function onLogin({email, password}) {
        if ([email, password].some(e => e == '')) {
            return alert("All fields are required!")
        }
        await login(email, password);
        ctx.updateNav();
        ctx.page.redirect('/');
    }
}

function loginTemplate (onLogin) {
    return html `
    <section id="login-page" class="auth">
            <form @submit=${onLogin} id="login">
                <h1 class="title">Login</h1>

                <article class="input-group">
                    <label for="login-email">Email: </label>
                    <input type="email" id="login-email" name="email">
                </article>

                <article class="input-group">
                    <label for="password">Password: </label>
                    <input type="password" id="password" name="password">
                </article>

                <input type="submit" class="btn submit-btn" value="Log In">
            </form>
        </section>
    `
}