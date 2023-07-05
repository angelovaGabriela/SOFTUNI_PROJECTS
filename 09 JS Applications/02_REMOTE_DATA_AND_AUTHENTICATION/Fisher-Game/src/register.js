document.getElementById('register-form').addEventListener('submit', registerHandler);
const showError =  document.getElementsByClassName('notification')[0];

function registerHandler(event) {
    event.preventDefault();
    const formElement = event.target;
    const data = new FormData(formElement);
    const {email, password, rePassword} = Object.fromEntries(data);
   
    if (password !== rePassword) {
       showError.textContent = "ERROR"
        setTimeout(()=> {
            showError.textContent = ""
        }, 1000);
    }
   
   
    onRegister(email, password);

}

async function onRegister(email, password) {

    const url = 'http://localhost:3030/users/register';
    
   const body = {
        email,
        password
    }
    try {
    const response = await fetch(url, {
        method: 'POST',
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify(body)
    })

    const data = await response.json();
    
    if(data.code !== 200) {
        throw new Error(data.message)
    }
    return data;
} catch(e) {
    showError.textContent = e;
    setTimeout(() => {
        showError.textContent = "";
    }, 1000);
}
}
