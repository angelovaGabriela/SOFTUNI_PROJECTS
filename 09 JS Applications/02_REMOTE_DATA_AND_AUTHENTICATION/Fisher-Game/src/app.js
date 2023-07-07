window.addEventListener("DOMContentLoaded", onLoadHTML);
document.querySelectorAll("a").forEach(x => x.classList.remove("active"));
document.getElementById("register").classList.add("active");
document.getElementById("logout").addEventListener("click", onLogout)


function onLoadHTML() {
    const token = sessionStorage.getItem("authToken");
     if(token) {
        document.getElementById("guest").style.display = "none";
        document.getElementById("user").style.display = "inline-block";
        document.getElementsByTagName('span')[0].textContent = sessionStorage.getItem('userEmail');
     } else {
        document.getElementById("guest").style.display = "inline-block";
        document.getElementById("user").style.display = "none";
        document.getElementsByTagName('span')[0].textContent = 'guest';
     }
}

async function onLogout(e) {
   e.preventDefault();
    await fetch('http://localhost:3030/users/logout', {
        method: 'GET',
        headers: {
            'X-Authorization': sessionStorage.getItem('authToken'),
        },
    });

    sessionStorage.clear(); 
    window.location.href = './index.html';
}