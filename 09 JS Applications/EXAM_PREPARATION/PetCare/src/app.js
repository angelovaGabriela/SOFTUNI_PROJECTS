
// import * as api from "./api/user.js";
// window.api = api

import { page, render } from "./api/lib.js"
import { updateNav } from "./api/nav.js";
import { showCatalog } from "./views/catalogView.js";
import { showHome } from "./views/homeView.js";
import { showLogin } from "./views/loginView.js";
import { showRegister } from "./views/registerView.js";

const main = document.getElementById("content");

page(renderMiddleWare)
page('/', showHome)
page('/catalog', showCatalog)
page('/catalog/:id', () => console.log('details'))
page('/edit/:id', () => console.log('edit'))
page('/create', () => console.log('create'))
page('/login', showLogin)
page('/register', showRegister)

updateNav();
page.start();

function renderMiddleWare(ctx, next) {
    ctx.render = (content) => render(content, main);
    ctx.updateNav = updateNav;
    next();
}