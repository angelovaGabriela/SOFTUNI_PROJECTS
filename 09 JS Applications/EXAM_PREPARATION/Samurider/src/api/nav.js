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
      <a id="logo" href="/"
          ><img id="logo-img" src="./images/logo.png" alt=""
        /></a>

        <nav>
            
          <div>
            <a href="/catalog">Motorcycles</a>
            <a href="/search">Search</a>
          </div>

${hasUser
            ? html`
          <div class="user">
            <a href="/create">Add Motorcycle</a>
            <a @click=${onLogout} href="javascript:void(0)">Logout</a>
          </div>`
            : html`
          <div class="guest">
            <a href="/login">Login</a>
            <a href="/register">Register</a>
          </div>`
        }
          </nav>
    `
}

function onLogout() {
    logout();
    updateNav();
    page.redirect('/')
}