import page from "../node_modules/page/page.mjs";
import {render} from "../node_modules/lit-html/lit-html.js"
import { logout} from "./api/data.js"
const root = document.querySelector(".container");
import { catalogView } from "./views/catalogView.js"
import { createView } from "./views/createView.js"
import { detailsView } from "./views/detailsView.js"
import { editView } from "./views/editView.js"
import { loginView } from "./views/loginView.js"
import { registerView } from "./views/registerView.js"
import { myFurnitureView } from "./views/my-furnitureView.js"

page("/", renderMiddleWare, catalogView)
page("/catalog",renderMiddleWare, catalogView)
page("/create", renderMiddleWare, createView)
page("/details/:id",renderMiddleWare, detailsView)
page("/edit/:id", renderMiddleWare, editView)
page("/login", renderMiddleWare, loginView)
page("/register", renderMiddleWare, registerView)
page("/my-furniture",renderMiddleWare, myFurnitureView)
page("/*", catalogView)

page.start();
updateNavigation();

const logoutBTN = document.getElementById("logoutBtn");
logoutBTN.addEventListener("click", onClickLogout);

async function onClickLogout() {
    await logout();
    updateNavigation();
    page.redirect("/");
}


function updateNavigation() {
    const userSection = document.getElementById("user");
    const guestSection = document.getElementById("guest");

    const userData = JSON.parse(sessionStorage.getItem("userData"));

    if (userData) {
        userSection.style.display = "inline-block";
        guestSection.style.display = "none";

    } else {
        userSection.style.display = "none";
        guestSection.style.display = "inline-block";
    }
}

function renderMiddleWare(ctx, next) {
    ctx.render = (content) => render(content, root);
    ctx.updateNavigation = updateNavigation;
    next();
}

