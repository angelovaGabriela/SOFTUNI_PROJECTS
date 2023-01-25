function extractText() {
    
    let listItems = document.querySelectorAll('ul li');
    let textArea = document.getElementById('result');
    let converted = Array.from(listItems);

    textArea.textContent = converted.map(element => element.textContent).join('\n');
}