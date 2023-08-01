import { html, render, page } from './lib.js'
import { logout } from './user.js';
import { getUserData } from './utils.js';

const navRoot = document.querySelector('header');

export function updateNav() {
    const user = getUserData();
    render(navTemplate(user), navRoot)
}

function navTemplate(hasUser) {
    return html`
     <nav>
            <section class="logo">
                <img src="./images/logo.png" alt="logo">
            </section>
            <ul>
                <li><a href="/">Home</a></li>
                <li><a href="/catalog">Dashboard</a></li>
${hasUser
            ? html`
<li><a href="/create">Create Postcard</a></li>
<li><a @click=${onLogout} href="javascript:void(0)">Logout</a></li>`
            : html`
<li><a href="/login">Login</a></li>
<li><a href="/register">Register</a></li>`}
</ul>
</nav>
`
}

function onLogout() {
    logout();
    updateNav();
    page.redirect('/')
}