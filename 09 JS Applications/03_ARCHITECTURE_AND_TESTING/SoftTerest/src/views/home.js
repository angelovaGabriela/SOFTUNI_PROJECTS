// controller - menaging the home view (dysplay/hide)

const section = document.getElementById("homeView");


export function showHome(context) {
    context.showSection(section)
}