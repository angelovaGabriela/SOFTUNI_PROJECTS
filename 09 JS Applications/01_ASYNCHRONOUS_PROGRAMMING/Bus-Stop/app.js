async function getInfo() {

    
    const busStopId = document.getElementById('stopId').value;
    const busesList = document.getElementById('buses');

    busStopId.innerHtml = "";
    busesList.innerHTML = "";

    try {

        const response = await fetch(`http://localhost:3030/jsonstore/bus/businfo/${busStopId}`);

        if (response.ok === false) {
            throw new Error();
        }

        const data = await response.json();

        const stopName = document.getElementById('stopName');
        stopName.textContent = data.name;

        Object.entries(data.buses).forEach(([busId, arrivalTime]) => {
            const li = document.createElement('li');
            li.textContent = `Bus ${busId} arrives in ${arrivalTime} minutes`;
            busesList.appendChild(li)
        });

    } catch (err) {
        stopName.textContent = "Error";
    }

}