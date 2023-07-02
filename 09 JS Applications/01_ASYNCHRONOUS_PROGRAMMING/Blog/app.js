function attachEvents() {
     const postButton = document.getElementById('btnLoadPosts');
     postButton.addEventListener('click', getPosts);
  //  document.getElementById('btnViewPost').addEventListener('click', getComments);
}


async function getPosts() {
    const dropDownMenu = document.getElementById('posts');
    dropDownMenu.replaceChildren();

    try {
        const response = await fetch('http://localhost:3030/jsonstore/blog/posts');

        if (!response.ok || response.status != 200) {
            throw new Error('Invalid request');
        }
        const data = await response.json();

        Object.values(data).forEach(post => {
            const newOption = document.createElement('option');
            newOption.value = post.id;
            newOption.textContent = post.title;
            dropDownMenu.appendChild(newOption);
        });

    } catch (error) {
        alert(error.message);
    }


  
}

// async function getComments() {
//     const response = await fetch('http://localhost:3030/jsonstore/blog/comments');
//     const data = await response.json();


// }
attachEvents();