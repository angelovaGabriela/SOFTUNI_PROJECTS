import { get, post} from './api.js'

export async function like(data) {
    return post('/data/likes', data);
}

export async function getTotalLikesCount(factId) {
    return get(`/data/likes?where=factId%3D%22${factId}%22&distinct=_ownerId&count`)
}

export async function getLikesForCurrentUser(factId, userId) {
    return get(`/data/likes?where=factId%3D%22${factId}%22%20and%20_ownerId%3D%22${userId}%22&count`);
}