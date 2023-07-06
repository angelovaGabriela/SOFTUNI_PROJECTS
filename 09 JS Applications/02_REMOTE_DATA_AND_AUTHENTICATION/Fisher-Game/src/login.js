document.getElementById('login-form').addEventListener('submit', loginHeandler);
document.querySelectorAll("a").forEach(x => x.classList.remove("active"));
document.getElementById("login").classList.add("active");

function loginHeandler(event) {
    event.preventDefault();
    const formElement = event.target;
    const data = new FormData(formElement);
    const {email, password} = Object.fromEntries(data);

    onLogin(email, password);
}

async function onLogin(email, password) {

    const url = `http://localhost:3030/users/login`;

    const body = {
        email,
        password
    }

    const response = await fetch(url,  {
        method: 'POST',
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify(body)
    })
    const data = await response.json();

    sessionStorage.setItem("email", data.email);
    sessionStorage.setItem("accessToken", data.accessToken);
    

    window.location = "./index.html";
    return data;
   
}