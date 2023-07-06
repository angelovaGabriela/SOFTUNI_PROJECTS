window.addEventListener("DOMContentLoader", onLoadHTML);

function onLoadHTML() {
    const token = localStorage.getItem("accessToken");
     if(token) {
        document.getElementById("guest").style.display = "none";
     }
}