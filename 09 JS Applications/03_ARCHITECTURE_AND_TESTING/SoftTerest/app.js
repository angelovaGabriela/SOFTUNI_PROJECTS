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


const main = document.getElementById("mainView");


const registerView = document.getElementById("registerView");
const loginView = document.getElementById("loginView");
const dashboard = document.getElementById("dashboard-holder");
const detailsView = document.getElementById("detailsView");
const createView = document.getElementById("createView");

const defSection = document.getElementById("defSection").remove();

const links = {
    "/": showHome,
    "/catalog": dashboard,
    "/login": loginView,
    "/register": registerView,
    "/details": detailsView,
    "/create": createView
}

const context = {
    showSection
}

window.showHome = showHome
window.context = context
// showHome(context) -> in the browser


function showSection(section) {
    main.appendChild(section);
}