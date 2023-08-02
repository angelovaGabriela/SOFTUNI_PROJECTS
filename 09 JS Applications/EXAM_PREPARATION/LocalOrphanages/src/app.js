// import * as api from './api/user.js'
// window.api = api;

import { page, render } from './api/lib.js'
import { updateNav } from './api/nav.js';
import { getUserData } from './api/utils.js';
import { showCatalog } from './views/catalogView.js';
import { showLogin } from './views/login.js';
import { showRegister } from './views/register.js';

const main = document.getElementById("main-content");

page(renderMiddleWare);
page('/', showCatalog);
page('/details/:id', () => console.log("details"));
page('/edit/:id', () => console.log("edit"));
page('/create', () => console.log("create"));
page('/login', showLogin);
page('/register', showRegister);
page('/my-posts', () => console.log("my-posts"));

updateNav();
page.start();

function renderMiddleWare(ctx, next) {
    ctx.render  = (content) => render(content, main)
    ctx.updateNav = updateNav;

    const user = getUserData();
    if (user) {
        ctx.user = user;
    }
    
    next();
}