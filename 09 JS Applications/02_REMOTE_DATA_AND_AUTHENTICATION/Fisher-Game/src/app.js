window.addEventListener("DOMContentLoaded", onLoadHTML);
document.querySelectorAll("a").forEach(x => x.classList.remove("active"));
document.getElementById("register").classList.add("active");
document.getElementById("logout").addEventListener("click", onLogout)
document.getElementsByClassName('load')[0].addEventListener('click', loadAllCatches);

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

async function loadAllCatches() {
    const url = 'http://localhost:3030/data/catches';
    const response = await fetch(url);
    const data = await response.json();
 
  renderCatches(data);
}

function renderCatches(data) {

    const catchDIV = document.getElementsByClassName('catch')[0];
    const main = document.getElementById('main');
    main.replaceChildren();
    Object.values(data).forEach(c => {
       const newCatchDIV = catchDIV.cloneNode(true);
        main.appendChild(newCatchDIV);
        const newAngler = newCatchDIV.getElementsByClassName('angler')[0];
        newAngler.value = `${c.angler}`;
        
        const newWeight= newCatchDIV.getElementsByClassName('weight')[0];
        newWeight.value = `${c.weight}`;

        const newSpecies= newCatchDIV.getElementsByClassName('species')[0];
        newSpecies.value = `${c.species}`;

        const newLocation= newCatchDIV.getElementsByClassName('location')[0];
        newLocation.value = `${c.location}`;
        
        const newBait = newCatchDIV.getElementsByClassName('bait')[0];
        newBait.value = `${c.bait}`;
        

        const newCaptureTime = newCatchDIV.getElementsByClassName('captureTime')[0];
        newCaptureTime.value = `${c.captureTime}`;

    })
        
      
    


    
}