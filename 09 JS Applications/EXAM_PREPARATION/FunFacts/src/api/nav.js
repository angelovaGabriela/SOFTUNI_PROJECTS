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
       <!-- Navigation -->
       <a id="logo" href="/"
          ><img id="logo-img" src="./images/logo.png" alt=""
        /></a>

        <nav>
        <div>
            <a href="/catalog">Fun Facts</a>
          </div>

${hasUser
            ? html`
 <!-- Logged-in users -->
 <div class="user">
            <a href="/create">Add Fact</a>
            <a @click=${onLogout}href="javascript:void(0)">Logout</a>
          </div>`
            : html`
<!-- Guest users -->
<div class="guest">
            <a href="/login">Login</a>
            <a href="/register">Register</a>
            
          </div>`}

</nav>
`
}

function onLogout() {
    logout();
    updateNav();
    page.redirect('/')
}