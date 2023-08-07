import { post, get } from "./api.js"
import { clearUserData, setUserData } from "./utils.js";

const endpoints = {
    "loginUser": "/users/login",
    "registerUser": "/users/register",
    "logoutUser": "/users/logout"
}

export async function login(email, password) {
    const {_id, email: resultEmail, accessToken} = await post(endpoints.loginUser, {email, password});
    setUserData ({
        _id,
        email: resultEmail,
        accessToken
    })
}

export async function register(email, password) {
    const {_id, email: resultEmail, accessToken} = await post(endpoints.registerUser, {email, password});
    setUserData ({
        _id,
        email: resultEmail,
        accessToken
    })
}

export async function logout() {
   get(endpoints.logoutUser);
   clearUserData();

}