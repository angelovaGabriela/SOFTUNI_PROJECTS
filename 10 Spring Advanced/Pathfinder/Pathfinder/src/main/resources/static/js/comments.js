const routeId = document.getElementById("routeId").value;
const commentForm = document.getElementById("commentForm");

commentForm.addEventListener("submit", handleFormSubmition)

const csrfHeaderName = document.head.querySelector('[name=_csrf_header]').content;
const csrfHeaderValue = document.head.querySelector('[name=_csrf]').content

const commentContainer = document.getElementById('commentCtnr');

let allComments = [];
let maxComments = 2;

async function handleFormSubmition(event) {
    event.preventDefault();

    const messageValue = document.getElementById("message").value;

    fetch(`http://localhost:8080/api/${routeId}/comments`, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
            'Accept': 'application/json',
            [csrfHeaderName]: csrfHeaderValue
        },
        body:JSON.stringify({
            message: messageValue
        })
    }).then(result => result.json())
        .then(data => {
            allComments.push(data)
            document.getElementById('message').value = "";
           // commentContainer.innerHTML += commentAsHtml(data)
            displayComments();
        });
    }
        function commentAsHtml(comment, visible, order) {
            let commentHtml = `<div ${visible ? "" : style="display: none;"} id="${order}">\n`
            commentHtml += `<h4>${comment.authorName}</h4>\n`
            commentHtml += `<p>${comment.message}</p>\n`
            commentHtml += '</div>\n'

            return commentHtml;

        }
        // GET request is a default request
        fetch(`http://localhost:8080/api/${routeId}/comments`, {
            headers: {
                "Accept": "application/json"
            }
        }).then(result => result.json())
        .then(data => {
            for(let comment of data) {
                allComments.push(comment)
              //  commentContainer.innerHTML += commentAsHtml(comment)
                displayComments();
            }
        })

        function displayComments() {
            
            commentContainer.innerHTML = "";

            for(let i = 0; i < allComments.length; i++) {
                if(i < maxComments) {
                    commentContainer.innerHTML += commentAsHtml(allComments[i], true, i);
                } else {
                    commentContainer.innerHTML += commentAsHtml(allComments[i], false, i);
                }
            }

            if(allComments.length > maxComments) {
                commentContainer.innerHTML += '<button id="showmore_btn" onclick="showmore()"> Show more </button>'
            }
            commentContainer.innerHTML += '<button style="display: none;" id="showless_btn" onclick="showless()"> Show less </button>'

        }

        function showmore() {
            for(let i = maxComments; i < allComments.length; i++) {
                document.getElementById(i).style.display = 'block';
            }

            document.getElementById('showmore_btn').style.display = 'none';
            document.getElementById('showless_btn').style.display = 'block';
            
        }

        
        function showless() {
            for(let i = maxComments; i < allComments.length; i++) {
                document.getElementById(i).style.display = 'none';
            }

            document.getElementById('showmore_btn').style.display = 'block';
            document.getElementById('showless_btn').style.display = 'none';

        }

//TODO: beginning 2 comments and "Show more" button
//TODO: then all comments and "Show less" button