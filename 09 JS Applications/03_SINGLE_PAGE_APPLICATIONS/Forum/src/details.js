const section = document.getElementById('detailsView');
section.remove();
const main = document.getElementsByTagName("main")[0];
const form = document.querySelector('#detailsView form');

export function showDetails(){
    main.replaceChildren(section);
}
