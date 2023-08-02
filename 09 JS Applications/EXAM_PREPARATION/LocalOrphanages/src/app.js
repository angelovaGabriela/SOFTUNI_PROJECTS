// import * as api from './api/user.js'
// window.api = api;

import { page, render } from './api/lib.js'
import { showCatalog } from './views/catalogView.js';

const main = document.getElementById("main-content");

page(renderMiddleWare);
page('/', showCatalog);
page('/catalog', showCatalog);
page('/catalog/:id', () => console.log("details"));
page('/edit/:id', () => console.log("edit"));
page('/create', () => console.log("create"));
page('/login', () => console.log("login"));
page('/register', () => console.log("register"));
page('/my-posts', () => console.log("my-posts"));

page.start();

function renderMiddleWare(ctx, next) {
    ctx.render  = (content) => render(content, main)
    next();
}