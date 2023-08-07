import { editFruitPost, getFruitById } from '../api/data.js';
import { html } from '../api/lib.js'
import { createSubmitHandler } from '../api/utils.js'

export  async function showEdit(ctx) {

    const id = ctx.params.id;
    const fruit = await getFruitById(id);


    ctx.render(createTemplate(fruit, createSubmitHandler(onEdit)));

    async function onEdit({name, imageUrl, description, nutrition}, form) {

        if ([name, imageUrl, description, nutrition].some(e => e === "")) {
            return alert("All fields are required!")
        }

        await editFruitPost(id, {
            name, 
            imageUrl, 
            description, 
            nutrition
        })
        
        form.reset();
        ctx.page.redirect('/details/' + id)

    }
}

function createTemplate(fruit, onEdit) {
    return html `
        <section id="edit">
          <div class="form">
            <h2>Edit Fruit</h2>
            <form @submit=${onEdit} class="edit-form">
              <input
                type="text"
                name="name"
                id="name"
                placeholder="Fruit Name"
                .value=${fruit.name}
              />
              <input
                type="text"
                name="imageUrl"
                id="Fruit-image"
                placeholder="Fruit Image URL"
                .value=${fruit.imageUrl}
              />
              <textarea
                id="fruit-description"
                name="description"
                placeholder="Description"
                rows="10"
                cols="50"
              >${fruit.description}</textarea>
              <textarea
                id="fruit-nutrition"
                name="nutrition"
                placeholder="Nutrition"
                rows="10"
                cols="50"
              >${fruit.nutrition}</textarea>
              <button type="submit">post</button>
            </form>
          </div>
        </section>
        
        `
}