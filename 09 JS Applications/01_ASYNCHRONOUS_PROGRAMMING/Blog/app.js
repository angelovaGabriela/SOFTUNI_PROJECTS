function attachEvents() {
    const postButton = document.getElementById('btnLoadPosts');
    postButton.addEventListener('click', getPosts);

    const viewButton = document.getElementById('btnViewPost');
    viewButton.addEventListener('click', getComments);
}


async function getPosts() {
    const dropDownMenu = document.getElementById('posts');
    dropDownMenu.replaceChildren();


    const response = await fetch('http://localhost:3030/jsonstore/blog/posts');

    const data = await response.json();

    Object.values(data).forEach(post => {
        const newOption = document.createElement('option');
        newOption.value = post.id;
        newOption.textContent = post.title;
        dropDownMenu.appendChild(newOption);
    });





}

async function getComments() {

    const selectedPost = document.getElementById('posts').selectedOptions[0].value;
    const postTitle = document.getElementById('post-title');
    const postBody = document.getElementById('post-body');
    const postComments = document.getElementById('post-comments');
    postComments.replaceChildren();

    postTitle.textContent = "";

    const response = await fetch(`http://localhost:3030/jsonstore/blog/posts/${selectedPost}`);
    const postData = await response.json();

    postTitle.textContent = postData.title.toUpperCase();
    postBody.textContent = postData.body;




    const urlComments = 'http://localhost:3030/jsonstore/blog/comments';
    const commentsResponse = await fetch(urlComments);



    const commentsData = await commentsResponse.json();

    const commentsOfThePost = Object.values(commentsData).filter(comment => comment.postId === selectedPost);
    commentsOfThePost.forEach(comment => {
        let li = document.createElement('li');
        li.id = comment.id;
        li.textContent = comment.text;
        postComments.appendChild(li);
    })

}
attachEvents();