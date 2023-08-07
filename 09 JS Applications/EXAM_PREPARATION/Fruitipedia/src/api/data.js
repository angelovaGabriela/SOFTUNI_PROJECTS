import {get, post, put, del} from './api.js'

export async function getAllFruits() {
    return get('/data/fruits?sortBy=_createdOn%20desc');
}

export async function getFruitById(id) {
    return get('/data/fruits/' + id);
}

export async function deleteFruitById(id) {
    return del('/data/fruits/' + id)
}

export async function createFruitPost(data) {
    return post('/data/fruits', data)
}

export async function editFruitPost(id, data) {
    return put('/data/fruits/' + id, data)
}