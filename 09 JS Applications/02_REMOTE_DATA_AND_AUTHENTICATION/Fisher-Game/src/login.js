const form = document.getElementById('login-form')
form.addEventListener('submit', onLogin);
document.querySelectorAll("a").forEach(x => x.classList.remove("active"));
document.getElementById("login").classList.add("active");
const showError = document.getElementsByClassName('notification')[0]


document.getElementById("guest").style.display = "inline-block";
document.getElementById("user").style.display = "none";
     

async function onLogin(event) {
    event.preventDefault();
    const formElement = event.target;
    const formData = new FormData(formElement);
    
    let email = formData.get('email');
    let password = formData.get('password');

    if (email == '' && password == '') {
        showError.textContent = "Missing fields!"
        setTimeout(() => {
          showError.textContent = ""
      }, 1000);
      return;
    }

    try {
    const url = `http://localhost:3030/users/login`;

    const response = await fetch(url,  {
        method: 'POST',
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify({email, password})
    })
    const data = await response.json();

    if (!response.ok || response.status != 200) {
        form.reset();
        throw new Error(data.message);
    }

    sessionStorage.setItem('authToken', data.accessToken);
    sessionStorage.setItem('userEmail', data.email);
    sessionStorage.setItem('userId', data._id);
    

    window.location = "./index.html";
    return data;
} catch (error) {
 showError.textContent = error.message;
}
   
}