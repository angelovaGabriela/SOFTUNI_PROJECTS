const section = document.getElementById('detailsView');

const main = document.getElementsByTagName("main")[0];
const container = document.getElementsByClassName('container')[1];
const form = document.querySelector('#detailsView form');
form.addEventListener('submit', onSubmit);
section.remove();

let id;
export async function showDetails(event) {
    if (!event) {
        return
    }

    if (event.target.tagName === "H2") {
        id = event.target.parentElement.id;
    } else if (event.target.tagName == "A") {
        id = event.target.id;
    }


   const post = await loadPosts(id)
 
    const comments = await loadComments(id);

    const res = topicTemplate(post, comments);
   section.replaceChildren(res);
    main.replaceChildren(section);
    //TODO
}

async function loadPosts(id) {
    const url = `http://localhost:3030/jsonstore/collections/myboard/posts/${id}`
    const response = await fetch(url);
    const data = await response.json();

    return data;
}

async function loadComments(id) {
    const url = `http://localhost:3030/jsonstore/collections/myboard/comments/${id}`
    const response = await fetch(url);
    const data = await response.json();
    
    return data;
}

function onSubmit(e) {
    e.preventDefault();
    const formData = new FormData(form);
    const { postText, username } = Object.fromEntries(formData);
const body = {
    postText,
    username,
    id, 
    date: new Date()
}
    createComment(body);

}

async function createComment(body) {
    const url = `http://localhost:3030/jsonstore/collections/myboard/comments`
    const response = await fetch(url, {
        method: 'POST',
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify(body)
    });

    const data = await response.json();
    form.reset();
    return data;


}

function topicTemplate(post, comments) {
    container.replaceChildren();
    let contentDiv = document.createElement('div');
    contentDiv.classList.add('theme-content');
    contentDiv.innerHTML =
        `<div class="theme-title">
                            <div class="theme-name-wrapper">
                                <div class="theme-name">
                                    <h2>${post.topicName}</h2>

                                </div>

                            </div>
                        </div>
                        <div class="comment">
                            <div class="header">
                                <img src="./static/profile.png" alt="avatar">
                                <p><span>${post.username}</span> posted on <time>${post.date}</time></p>

                                <p class="post-content">${post.postText}</p>
                            </div>


                            <div id="user-comment"></div>

                            <div class="answer-comment">
                                <p><span>currentUser</span> comment:</p>
                                <div class="answer">
                                    <form>
                                        <textarea name="postText" id="comment" cols="30" rows="10"></textarea>
                                        <div>
                                            <label for="username">Username <span class="red">*</span></label>
                                            <input type="text" name="username" id="username">
                                        </div>
                                        <button>Post</button>
                                    </form>
                                </div>
                            </div>
                        </div>`;

    container.appendChild(contentDiv);
  

    const addCommentDiv = document.getElementById('user-comment');
    
    Object.values(comments).map(c => {

    
        const createComment = document.createElement('div');
        createComment.classList.add("topic-name-wrapper")
        createComment.innerHTML = 
        `<div class="topic-name">
        <p><strong>${c.username}</strong> commented on <time>${c.date}</time></p>
        <div class="post-content">
            <p>${c.postText}</p>
        </div>
    </div>` 

    addCommentDiv.appendChild(createComment);
   });
   

   return container;

}