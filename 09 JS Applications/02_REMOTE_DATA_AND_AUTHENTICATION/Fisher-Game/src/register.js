document.getElementById('register-form').addEventListener('submit', registerHandler);
document.querySelectorAll("a").forEach(x => x.classList.remove("active"));
document.getElementById("register").classList.add("active");

const showError = document.getElementsByClassName('notification')[0];

function registerHandler(event) {
    event.preventDefault();
    const formElement = event.target;
    const data = new FormData(formElement);
    const { email, password, rePass } = Object.fromEntries(data);

    if (password !== rePass) {
        showError.textContent = "ERROR"
        setTimeout(() => {
            showError.textContent = ""
        }, 1000);
        
    }

    
    onRegister(email, password);
}

async function onRegister(email, password) {
    const body = {
        email,
        password
    }
    try {
        const url = 'http://localhost:3030/users/register';
        const response = await fetch(url, {
            method: 'POST',
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify(body)
        })

        const data = await response.json();

        if (data.code !== 200) {
            throw new Error(data.message)
        }

        sessionStorage.setItem("email", data.email);
        sessionStorage.setItem("accessToken", data.accessToken);
        return data;
    } catch (e) {
        showError.textContent = e;
        setTimeout(() => {
            showError.textContent = "";
        }, 1000);
    }
}
