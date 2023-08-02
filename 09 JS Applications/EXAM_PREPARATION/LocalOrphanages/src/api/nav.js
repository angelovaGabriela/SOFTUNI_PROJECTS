import { html, render, page } from './lib.js'
import { logout } from './user.js';
import { getUserData } from './utils.js';
const nav = document.querySelector('header');

export function updateNav() {

    const user = getUserData();
    render(navTemplate(user), nav);
}

function navTemplate(user) {
    return html`
    <!-- Navigation -->
    <h1><a href="/">Orphelp</a></h1>

<nav>
    <a href="/">Dashboard</a>

    ${user
            ? html` 
    <!-- Logged-in users -->
    <div id="user">
        <a href="/my-posts">My Posts</a>
        <a href="/create">Create Post</a>
        <a @click=${onLogout} href="javascript:void(0)">Logout</a>
    </div>`
            : html`
    <!-- Guest users -->
    <div id="guest">
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
    page.redirect('/');
}