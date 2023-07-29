import { html } from "../../node_modules/lit-html/lit-html.js"
import { updateFurnitureDetails } from  "../api/data.js"
import { getFurnitureDetails } from  "../api/data.js"

let context = null;

export async function editView(ctx) {
    context = ctx;
    const furnitureId = ctx.params.id;
    const furniture = await getFurnitureDetails(furnitureId); 
   ctx.render(editTemplate(furniture))
}

async function onSubmit(event) {
     event.preventDefault();

    const formData = new FormData(event.target);
    const {make, model, year, description, price, img, material} = Object.fromEntries(formData);
    const id = event.target.dataset.id;
 
    await updateFurnitureDetails(id, {make, model, year, description, price, img, material});
    context.page.redirect("/")
}

function editTemplate(furniture, stateForm = {}) {
    return html `
         <div class="row space-top">
            <div class="col-md-12">
                <h1>Edit Furniture</h1>
                <p>Please fill all fields.</p>
            </div>
        </div>
        <form @submit=${onSubmit} data-id=${furniture._id}>
            <div class="row space-top">
                <div class="col-md-4">
                    <div class="form-group">
                        <label class="form-control-label" for="new-make">Make</label>
                        <input class="form-control" id="new-make" type="text" name="make" .value="${furniture.make}">
                    </div>
                    <div class="form-group has-success">
                        <label class="form-control-label" for="new-model">Model</label>
                        <input class="form-control" id="new-model" type="text" name="model" .value="${furniture.model}">
                    </div>
                    <div class="form-group has-danger">
                        <label class="form-control-label" for="new-year">Year</label>
                        <input class="form-control" id="new-year" type="number" name="year" .value="${Number(furniture.year)}}">
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" for="new-description">Description</label>
                        <input class="form-control" id="new-description" type="text" name="description" .value="${furniture.description}">
                    </div>
                </div>
                <div class="col-md-4">
                    <div class="form-group">
                        <label class="form-control-label" for="new-price">Price</label>
                        <input class="form-control" id="new-price" type="number" name="price" .value="${Number(furniture.price)}">
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" for="new-image">Image</label>
                        <input class="form-control ${stateForm.hasImg}" id="new-image" type="text" name="img" .value="${furniture.img}">
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" for="new-material">Material (optional)</label>
                        <input class="form-control" id="new-material" type="text" name="material" .value="${furniture.material}">
                    </div>
                    <input type="submit" class="btn btn-info" value="Edit" />
                </div>
            </div>
        </form>
    `
}