// interface of the application (all the functions are called here - but how they work we don't care)

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
//TEST LINKS
// window.showHome = showHome
// window.context = context
// showHome(context) -> in the browser


import { showHome } from "./src/views/home.js";
import { showCatalog } from "./src/views/catalog.js";
import { showLogin } from "./src/views/login.js";
import { showRegister } from "./src/views/register.js";
import { showCreate } from "./src/views/create.js";
import { showDetails } from "./src/views/details.js";
import { initialize } from "./src/router.js";
import { logout } from "./src/api/user.js";



document.getElementById("defSection").remove();

const links = {
    "/": showHome,
    "/catalog": showCatalog,
    "/login": showLogin,
    "/register": showRegister,
    "/details": showDetails,
    "/create": showCreate,
    "/logout": async function() {
        await logout();
        router.goTo("/");
        router.updateNavigate();
    }
}


const router = initialize(links)

//starting point (on refresh will redirect us home)
router.updateNavigate();
router.goTo("/");







