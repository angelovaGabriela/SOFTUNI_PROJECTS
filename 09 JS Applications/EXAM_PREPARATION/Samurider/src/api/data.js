import {get, post, put, del} from './api.js'

export async function getAllMotorcycles() {
    return get('/data/motorcycles?sortBy=_createdOn%20desc');
}

export async function getMotorcycleById(id) {
    return get('/data/motorcycles/' + id);
}

export async function deleteMotorcycleById(id) {
    return del('/data/motorcycles/' + id)
}

export async function createMotorcyclePost(data) {
    return post('/data/motorcycles', data)
}

export async function editMotorcyclePost(id, data) {
    return put('/data/motorcycles/' + id, data)
}