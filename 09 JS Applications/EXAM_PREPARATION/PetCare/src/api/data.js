import {get, post, put, del} from './api.js'

export async function getAllAminamls() {
    return get('/data/pets?sortBy=_createdOn%20desc&distinct=name');
}