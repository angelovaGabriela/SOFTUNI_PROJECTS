import {get, post, put, del} from './api.js'

export async function getAllAminamls() {
    return get('/data/pets?sortBy=_createdOn%20desc&distinct=name');
}

export async function getAnimalById(id) {
    return get('/data/pets/' + id);
}

export async function deleteAnimalById(id) {
    return del('/data/pets/' + id)
}

export async function createAnimalPost(data) {
    return post('/data/pets', data)
}