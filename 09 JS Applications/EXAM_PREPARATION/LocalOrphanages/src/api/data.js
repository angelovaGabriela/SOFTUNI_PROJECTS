import {get, post, put, del} from './api.js'

export async function getAllMaterials() {
    return get('/data/posts?sortBy=_createdOn%20desc');
}