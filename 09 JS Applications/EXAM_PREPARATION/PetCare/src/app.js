
// import * as api from "./api/user.js";
// window.api = api

import { page, render } from "./api/lib.js"
import { updateNav } from "./api/nav.js";
import { getUserData } from "./api/utils.js";
import { showCatalog } from "./views/catalogView.js";
import { showCreate } from "./views/createView.js";
import { showDetails } from "./views/detailsView.js";
import { showEdit } from "./views/editView.js";
import { showHome } from "./views/homeView.js";
import { showLogin } from "./views/loginView.js";
import { showRegister } from "./views/registerView.js";

const main = document.getElementById("content");

page(renderMiddleWare)
page('/', showHome)
page('/catalog', showCatalog)
page('/catalog/:id', showDetails)
page('/edit/:id', showEdit)
page('/create', showCreate)
page('/login', showLogin)
page('/register', showRegister)

updateNav();
page.start();

function renderMiddleWare(ctx, next) {
    ctx.render = (content) => render(content, main);
    ctx.updateNav = updateNav;

    // saving the sessionStorage data and make it accessible for all the modules
    const user  = getUserData();
    if (user) {
        ctx.user = user;
    }
    next();
}