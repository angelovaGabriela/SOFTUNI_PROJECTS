const routeId = document.getElementById("routeId").value;
const commentForm = document.getElementById("commentForm");

commentForm.addEventListener("submit", handleFormSubmition)

const csrfHeaderName = document.head.querySelector('[name=_csrf_header]').content;
const csrfHeaderValue = document.head.querySelector('[name=_csrf]').content

const commentContainer = document.getElementById('commentCtnr');

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
            document.getElementById('message').value = "";
            commentContainer.innerHTML += commentAsHtml(data)
        });
    }
        function commentAsHtml(comment) {
            let commentHtml = '<div>\n'
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
                commentContainer.innerHTML += commentAsHtml(comment)
            }
        })
