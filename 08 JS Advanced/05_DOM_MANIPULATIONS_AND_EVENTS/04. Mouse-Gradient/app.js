function attachGradientEvents() {
    let result = document.getElementById('result');
    let gradient = document.getElementById('gradient');

    gradient.addEventListener('mousemove', getPercentage);
      function getPercentage(event) {
        result.textContent = `${Math.floor((event.offsetX / gradient.clientWidth) * 100)}%`; 
      }
    }
