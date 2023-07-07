document.querySelectorAll("a").forEach(x => x.classList.remove("active"));
document.getElementById("register").classList.add("active");

const showError = document.getElementsByClassName('notification')[0];

document.getElementById("guest").style.display = "inline-block";
document.getElementById("user").style.display = "none";

const form = document.getElementById("register-form");

form.addEventListener('submit', registerUser)

async function registerUser(event) {
    event.preventDefault();

    const formData = new FormData(event.target);

    let email = formData.get('email');
    let password = formData.get('password');
    let repass = formData.get('rePass');

    if (email == '' || password == '' || repass == '') {
      showError.textContent = "Missing fields!"
      setTimeout(() => {
        showError.textContent = ""
    }, 1000);
      
        // alert('Please fill the required fields!');
        return;
    } else if (password !== repass) {
        showError.textContent = "Passwords don't match!"
        setTimeout(() => {
          showError.textContent = ""
      }, 1000);
        
        // alert('Passwords don\'t match!');
        return;
    }

    try {
        const response = await fetch('http://localhost:3030/users/register', {
            method: 'POST',
            headers: { 'Content-type': 'aplication/json' },
            body: JSON.stringify({ email, password })
        });
        const data = await response.json();

        if (!response.ok || response.status != 200) {
            form.reset();
            throw new Error(data.message);
        }

        sessionStorage.setItem('authToken', data.accessToken);
        sessionStorage.setItem('userEmail', data.email);
        sessionStorage.setItem('userId', data._id);
        window.location.href = './login.html';

    } catch (error) {
        showError.textContent = error.message;
        // alert(error.message);
    }
}