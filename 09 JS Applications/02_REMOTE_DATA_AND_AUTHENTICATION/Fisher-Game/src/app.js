window.addEventListener("DOMContentLoaded", onLoadHTML);
document.querySelectorAll("a").forEach(x => x.classList.remove("active"));
document.getElementById("register").classList.add("active");
document.getElementById("logout").addEventListener("click", onLogout)
document.getElementsByClassName('load')[0].addEventListener('click', loadAllCatches);
const addButton = document.getElementsByClassName('add')[0];
addButton.addEventListener('click', addCatch);
const catches = document.getElementById('catches');

function onLoadHTML() {
    const token = sessionStorage.getItem("authToken");
    if (token) {
        document.getElementById("guest").style.display = "none";
        document.getElementById("user").style.display = "inline-block";
        document.getElementsByTagName('span')[0].textContent = sessionStorage.getItem('userEmail');
        addButton.disabled = false;
    } else {
        document.getElementById("guest").style.display = "inline-block";
        document.getElementById("user").style.display = "none";
        document.getElementsByTagName('span')[0].textContent = 'guest';
        addButton.disabled = true;
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
    catches.replaceChildren();
    const url = 'http://localhost:3030/data/catches';
    const response = await fetch(url);
    const data = await response.json();

    renderCatches(data);
}

function renderCatches(data) {
     
    let isDisabled = sessionStorage.getItem('userId') == data._ownerId ? false : true; 
    Object.values(data).forEach(c => {

        const newCatchDIV = document.createElement('div');
        newCatchDIV.classList.add("catch");
        

       newCatchDIV.innerHTML = `
        <label>Angler</label>
          <input type="text" class="angler" value="${c.angler}" ${isDisabled}>
        <label>Weight</label>
          <input type="text" class="weight" value="${c.weight}" ${isDisabled}>
        <label>Species</label>
          <input type="text" class="species" value="${c.species}" ${isDisabled}>
        <label>Location</label>
          <input type="text" class="location" value="${c.location}" ${isDisabled}>
        <label>Bait</label>
          <input type="text" class="bait" value="${c.bait}" disabled>
        <label>Capture Time</label>
         <input type="number" class="captureTime" value="${c.captureTime}" ${isDisabled}>
           <button class="update" data-id="${c._id}" ${isDisabled}>Update</button>
           <button class="delete" data-id="${c._id}" ${isDisabled}>Delete</button>
    </div>`;
       
    catches.appendChild(newCatchDIV);
     

         const deleteBtn = newCatchDIV.getElementsByClassName('delete')[0];
         deleteBtn.addEventListener('click', deleteHandler);

        
    })

}


async function addCatch(event) {
    event.preventDefault();
    const form = event.target.parentElement.parentElement;
    const formData = new FormData(form);

    const angler = formData.get('angler');
    const weight = formData.get('weight');
    const species = formData.get('species');
    const location = formData.get('location');
    const bait = formData.get('bait');
    const captureTime = formData.get('captureTime');

    try {
        if (angler == '' || weight == '' || species == '' || location == '' || bait == '' || captureTime == '') {
            return alert('Missing fields!');
        }
        if (Number(angler) || Number(species) || Number(location) || Number(bait)) {
            return alert('Number is not allowed!');
        }
        if (isNaN(weight) || isNaN(captureTime)) {
            return alert('Enter a number!');
        }


        const url = 'http://localhost:3030/data/catches';
        const response = await fetch(url, {
            method: 'POST',
            headers: {
                "Content-Type": "application/json",
                'X-Authorization': sessionStorage.getItem('authToken')
            },
            body: JSON.stringify({
                angler: angler,
                weight: Number(weight),
                species: species,
                location: location,
                bait: bait,
                captureTime: Number(captureTime)
            })
        })


        const data = await response.json();
        if (!response.ok || response.status != 200) {
            throw new Error(data.message);
        }


         form.reset();
        loadAllCatches()
        return data;

    } catch (e) {
        alert(e.message);
    }

}

function deleteHandler(event) {
    const record = event.target.parentElement;
    const id = record.getAttribute("data-id");

    deleteCatch(id);
    record.remove();
}

async function deleteCatch(id) {

}