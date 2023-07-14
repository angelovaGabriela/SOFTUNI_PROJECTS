const section = document.getElementById('detailsView');
section.remove();
const main = document.getElementsByTagName("main")[0];
const form = document.querySelector('#detailsView form');

export async function showDetails(e){
// let id;
// if(e.target.tagName = "H2") {
//     id = e.target.parentElement.id;
// } else {
//     id = e.target.id;
// }


// const post = await loadPosts(id)
    main.replaceChildren(section);
    //TODO
}

// async function loadPosts(id) {
//     const url = `http://localhost:3030/jsonstore/collections/myboard/posts/${id}`
//     const response = await fetch(url);
//     const data = await response.json();
 
//     return;
// }