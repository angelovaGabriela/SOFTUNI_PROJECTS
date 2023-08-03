// import * as api from './api/user.js'
// window.api = api;

import { page, render } from './api/lib.js'
import { updateNav } from './api/nav.js';
import { getUserData } from './api/utils.js';
import { showCatalog } from './views/catalogView.js';
import { showCreate } from './views/create.js';
import { showDetails } from './views/details.js';
import { showEdit } from './views/edit.js';
import { showLogin } from './views/login.js';
import { showMyPosts } from './views/my-posts.js';
import { showRegister } from './views/register.js';

const main = document.getElementById("main-content");

page(renderMiddleWare);
page('/', showCatalog);
page('/details/:id', showDetails);
page('/edit/:id', showEdit);
page('/create', showCreate);
page('/login', showLogin);
page('/register', showRegister);
page('/my-posts', showMyPosts);

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