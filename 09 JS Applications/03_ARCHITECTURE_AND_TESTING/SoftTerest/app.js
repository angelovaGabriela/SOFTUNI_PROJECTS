// TEST API.JS
// import * as api from './src/api/api.js';
// window.myApi = api;
// await myApi.post("users/register", {email: "pug@lord.bg", password: "12345"})

// TEST USER>JS
// import {login, register, logout} from './src/api/user.js'
//  window.login = login;
//  window.register = register;
//  window.logout = logout;
// await login("pug@lord.bg", "12345");


import { showHome } from "./src/views/home.js";
import { showCatalog } from "./src/views/catalog.js";
import { showLogin } from "./src/views/login.js";
import { showRegister } from "./src/views/register.js";
import { showCreate } from "./src/views/create.js";
import { showDetails } from "./src/views/details.js";


const main = document.getElementById("mainView");
//the event is attached to the whole navigation bar
//have to check where the target is
document.querySelector("nav").addEventListener("click", onNavigate);

// const registerView = document.getElementById("registerView");
// const loginView = document.getElementById("loginView");
// const dashboard = document.getElementById("dashboard-holder");
// const detailsView = document.getElementById("detailsView");
// const createView = document.getElementById("createView");

document.getElementById("defSection").remove();

const links = {
    "/": showHome,
    "/catalog": showCatalog,
    "/login": showLogin,
    "/register": showRegister,
    "/details": showDetails,
    "/create": showCreate
}

const context = {
    showSection
}

//TEST LINKS
// window.showHome = showHome
// window.context = context
// showHome(context) -> in the browser



function showSection(section) {
    main.replaceChildren(section);
}

function onNavigate(e) {
    // the <a> tags make rerender so event.preventDefault();
    e.preventDefault();
    let target = e.target;

    if (target.tagName === "IMG") {
        target = target.parentElement
    }
    if(target.tagName === "A") {
        const url = new URL(target.href);
        goTo(url.pathname); 
    }
}

function goTo(name) {
    const handler = links[name];
    if(typeof(handler) === "function") {
        handler(context)
    }
}