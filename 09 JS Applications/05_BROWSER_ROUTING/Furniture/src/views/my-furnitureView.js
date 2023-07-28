import { html } from "../../node_modules/lit-html/lit-html.js"
import { getMyFurnitures } from "../api/data.js"



export async function myFurnitureView(ctx) {

    const userData = JSON.parse(sessionStorage.getItem("userData"));
    const ownerId = userData._id;
    const allFurnitures = await getMyFurnitures(ownerId);

    ctx.render(myFurnitureTemplate(allFurnitures));

}

function myFurnitureTemplate(data) {
    return html`
         <div class="row space-top">
            <div class="col-md-12">
                <h1>My Furniture</h1>
                <p>This is a list of your publications.</p>
            </div>
        </div>
        <div class="row space-top">
          ${Object.values(data).map(f => furnitureCard(f))} 
        </div>
    </div>
    `
}


function furnitureCard(furniture) {
    return html`
      <div class="col-md-4">
                <div class="card text-white bg-primary">
                    <div class="card-body">
                            <img src="${furniture.img}" />
                            <p>${furniture.description}</p>
                            <footer>
                                <p>Price: <span>${furniture.price} $</span></p>
                            </footer>
                            <div>
                                <a href="details/${furniture._id}" class="btn btn-info">Details</a>
                            </div>
                    </div>
                </div>
            </div>
    `
}