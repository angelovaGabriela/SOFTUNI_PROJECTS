// TESTING
// import * as api from "./api/user.js";
// window.api = api

import { page, render } from "./api/lib.js"
import { getUserData } from "./api/utils.js";
import { showHome } from "./views/home.js";

const main = document.querySelector('main');

page(renderMiddleWare);
page('/', showHome)
page('/catalog', () => console.log("catalog"))
page('/details/:id', () => console.log("details"))
page('/edit/:id', () => console.log("edit"))
page('/create', () => console.log("create"))
page('/login', () => console.log("login"))
page('/register', () => console.log("register"))

//updateNav();
page.start();


function renderMiddleWare(ctx, next) {
    ctx.render = (content) => render(content, main);
    //ctx.updateNav = updateNav;

    // saving the sessionStorage data and make it accessible for all the modules
    const user  = getUserData();
    if (user) {
        ctx.user = user;
    }
    next();
}