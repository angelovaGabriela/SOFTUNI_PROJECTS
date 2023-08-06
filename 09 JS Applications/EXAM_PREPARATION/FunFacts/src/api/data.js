import {get, post, put, del} from './api.js'

export async function getAllFacts() {
    return get('/data/facts?sortBy=_createdOn%20desc');
}

export async function getFactById(id) {
    return get('/data/facts/' + id);
}

export async function deleteFactById(id) {
    return del('/data/facts/' + id)
}

export async function createFactPost(data) {
    return post('/data/facts', data)
}

export async function editFactPost(id, data) {
    return put('/data/facts/' + id, data)
}