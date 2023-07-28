import { html } from "../../node_modules/lit-html/lit-html.js"
import {getFurnitureDetails} from "../api/data.js"

export async function detailsView(ctx) {
    const furnitureId = ctx.params.id;
    const details = await getFurnitureDetails(furnitureId);
    const userData = JSON.parse(sessionStorage.getItem("userData"));
    const isOwner = userData._id === details._ownerId;
   ctx.render(detailsTemplate(details, isOwner));
}


function detailsTemplate(data, isOwner) {
    const furnitureImgNameArr = data.img.split("/");
    return html`
      <div class="row space-top">
            <div class="col-md-12">
                <h1>Furniture Details</h1>
            </div>
        </div>
        <div class="row space-top">
            <div class="col-md-4">
                <div class="card text-white bg-primary">
                    <div class="card-body">
                        <img src=${"/images/" + furnitureImgNameArr[furnitureImgNameArr.length - 1]}/>
                    </div>
                </div>
            </div>
            <div class="col-md-4">
                <p>Make: <span>${data.make}</span></p>
                <p>Model: <span>${data.model}</span></p>
                <p>Year: <span>${Number(data.year)}</span></p>
                <p>Description: <span>${data.description}</span></p>
                <p>Price: <span>${data.price} $</span></p>
                <p>Material: <span>${data.material}</span></p>
                <div>
                    ${isOwner ? html `

                    <a href=”#” class="btn btn-info">Edit</a>
                    <a href=”#” class="btn btn-red">Delete</a>
                    `
                    : ""
                    }
                </div>
            </div>
        </div>
    `
}