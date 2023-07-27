import * as api from "./api.js"

const endpoints = {
    "login": "users/login",
    "register": "users/register",
    "logout": "users/logout",
    "createFurniture": "data/catalog",
    "allFurniture": "data/catalog",
    "getElementById": "data/catalog/",
    "getMyFurnitures": "data/catalog?where=_ownerId%3D%22"
}
// Login User (POST): http://localhost:3030/users/login ·
export async function login(email, password)  {
  const result = await api.post(endpoints.login, {email, password})
  sessionStorage.setItem("userData", JSON.stringify(result));

  return result;
} 
// Register User (POST): http://localhost:3030/users/register
export async function register(email, password) {
    const result = await api.post(endpoints.register, {email, password});
    sessionStorage.setItem("userData", JSON.stringify(result));
    return result; 
}
//  Logout User (GET): http://localhost:3030/users/logout
export async function logout() {
    const result = await api.get(endpoints.logout);
    sessionStorage.removeItem("userData");
    return result;
}


//"{\"email\":\"lord_denis@pug.fr\",\"password\":\"12345\
//  Create Furniture (POST): http://localhost:3030/data/catalog
export async function createFurniture(data) {
    const result = await api.post(endpoints.createFurniture, data)
    return result
}
//  All Furniture (GET): http://localhost:3030/data/catalog
export async function allFurniture() {
    const result = await api.get(endpoints.allFurniture);
    return result;
}
// Furniture Details (GET): http://localhost:3030/data/catalog/:id · 
export async function getFurnitureDetails(id) {
    const result = await api.get(endpoints.getElementById + id);
    return result;
}
// Update Furniture (PUT): http://localhost:3030/data/catalog/:id
export async function updateFurnitureDetails(id, data) {
    const result = await api.put(endpoints.getElementById + id, data);
    return result;
}
// Delete Furniture (DELETE): http://localhost:3030/data/catalog/:id
export async function deleteFurnitureDetails(id) {
    const result = await api.del(endpoints.getElementById + id);
    return result;
}

// My Furniture (GET): http://localhost:3030/data/catalog?where=_ownerId%3D%22{userId}%22
export async function getMyFurnitures() {
    const userData = JSON.parse(sessionStorage.getItem("userData"));
    const userId = userData && userData._id;

    const id = `${userId}%22`;

    const result = await api.get(endpoints.getMyFurnitures + id);
    return result;
}







