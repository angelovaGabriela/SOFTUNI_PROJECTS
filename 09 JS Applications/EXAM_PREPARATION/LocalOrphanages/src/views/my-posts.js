import { getPostsOfCurrentUser } from '../api/data.js';
import { html } from '../api/lib.js'

export async function showMyPosts(ctx) {
    const id = ctx.user._id;
    const posts = await getPostsOfCurrentUser(id);
    ctx.render(myPostsTemplate(posts));
}

function myPostsTemplate (posts) {
    return html `
       <section id="my-posts-page">
            <h1 class="title">My Posts</h1>

            ${ posts.length == 0 
            ? html `<h1 class="title no-posts-title">You have no posts yet!</h1>`
            : html `
            <div class="my-posts">
                ${posts.map(p => postCard(p))}
            </div>`}
        </section>`
}

function postCard(post) {
    return html `
    <div class="post">
                    <h2 class="post-title">${post.title}</h2>
                    <img class="post-image" src="${post.imageUrl}" alt="Material Image">
                    <div class="btn-wrapper">
                        <a href="/details/${post._id}" class="details-btn btn">Details</a>
                    </div>
                </div>
    `
}