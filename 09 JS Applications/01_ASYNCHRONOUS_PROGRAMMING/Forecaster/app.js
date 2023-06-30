function attachEvents() {
    
    document.getElementById('submit').addEventListener('click', getWeather);

}

async function getWeather() {
    const currentSection = document.getElementById("current");
    const upcomingContainer = document.getElementById("upcoming");
    const forecastContainer = document.getElementById("forecast");
    const townName = document.getElementById('location').value;

    
    try {

        const response = await fetch("http://localhost:3030/jsonstore/forecaster/locations");
        const data = await response.json();

        const info = data.find(l => l.name === townName);

        const [todayData, upcomingData] = await Promise.all([
            getToday(info.code),
            getUpcoming(info.code)
        ]);

        forecastContainer.style.display = "block";

        const todayHtmlTemp = createToday(todayData);
        currentSection.appendChild(todayHtmlTemp);

        const upcomingHtmlTemp = createUpcoming(upcomingData);
        upcomingContainer.appendChild(upcomingHtmlTemp);

    } catch (e) {
        forecastContainer.style.display = "block"
        document.querySelector(".label").textContent = "Error";

        e.message;
    }
}

async function getToday(code) {

    const responseToday = await fetch(`http://localhost:3030/jsonstore/forecaster/today/${code}`);
    const dataToday = await responseToday.json();
    return dataToday;

}

async function getUpcoming(code) {
    
    const responseUpcoming = await fetch(`http://localhost:3030/jsonstore/forecaster/upcoming/${code}`);
    const dataUpcoming = await responseUpcoming.json();
    return dataUpcoming;

}

function createToday(data) {
    const enumIcon = {
        "Sunny": "&#x2600", // ☀
        "Partly sunny": "&#x26C5", // ⛅
        "Overcast": "&#x2601", // ☁
        "Rain": "&#x2614", // ☂
        "Degrees": "&#176"   // °

    }
    const { condition, high, low } = data.forecast;
    const conditionContainer = document.createElement('div');

    conditionContainer.classList.add("forecasts");

    const iconConditionSpan = document.createElement('span');
    iconConditionSpan.classList.add("condition", "symbol");
    iconConditionSpan.innerHTML = enumIcon[condition];

    const conditionSpan = document.createElement('span');
    conditionSpan.classList.add("condition");

    const nameSpan = document.createElement('span');
    nameSpan.classList.add("forecast-data");
    nameSpan.textContent = data.name;


    const temperatureSpan = document.createElement('span');
    temperatureSpan.classList.add("forecast-data");
    temperatureSpan.innerHTML = `${low}${enumIcon["Degrees"]}/${high}${enumIcon["Degrees"]}`;

    const conditionTxtSpan = document.createElement('span');
    conditionTxtSpan.classList.add("forecast-data");
    conditionTxtSpan.textContent = condition;

    conditionSpan.appendChild(nameSpan);
    conditionSpan.appendChild(temperatureSpan);
    conditionSpan.appendChild(conditionTxtSpan);

    conditionContainer.appendChild(iconConditionSpan);
    conditionContainer.appendChild(conditionSpan);

    return conditionContainer;

}
function createUpcoming(data) {
    const container = document.createElement('div');
    container.classList.add("forecast-info");
    data.forecast.forEach(data => {
        const spanHolder = generateSpans(data);
        container.appendChild(spanHolder);
    });

    return container;
}
function generateSpans(data) {
    const enumIcon = {
        "Sunny": "&#x2600", // ☀
        "Partly sunny": "&#x26C5", // ⛅
        "Overcast": "&#x2601", // ☁
        "Rain": "&#x2614", // ☂
        "Degrees": "&#176"   // °

    }
    const { condition, high, low } = data
    const spanHolder = document.createElement("span");
    spanHolder.classList.add("upcoming");

    const iconSpan = document.createElement("span");
    iconSpan.classList.add("symbol");
    iconSpan.innerHTML = enumIcon[condition];

    const tempSpan = document.createElement("span");
    tempSpan.classList.add("forecast-data");
    tempSpan.innerHTML = `${low}${enumIcon["Degrees"]}/${high}${enumIcon["Degrees"]}`

    const conditionSpan = document.createElement("span");
    conditionSpan.classList.add("forecast-data");
    conditionSpan.textContent = condition

    spanHolder.appendChild(iconSpan)
    spanHolder.appendChild(tempSpan)
    spanHolder.appendChild(conditionSpan);

    return spanHolder;
}
attachEvents();