// TESTING
// import * as api from "./api/user.js";
// window.api = api
import { updateNav } from './api/nav.js'
import { page, render } from "./api/lib.js"
import { getUserData } from "./api/utils.js";
import { showCatalog } from "./views/catalog.js";
import { showDetails } from "./views/details.js";
import { showHome } from "./views/home.js";
import { showLogin } from "./views/login.js";
import { showRegister } from "./views/register.js";
import { showCreate } from './views/create.js';
import { showEdit } from './views/edit.js';

const main = document.querySelector('main');

page(renderMiddleWare);
page('/', showHome)
page('/catalog', showCatalog)
page('/details/:id', showDetails)
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