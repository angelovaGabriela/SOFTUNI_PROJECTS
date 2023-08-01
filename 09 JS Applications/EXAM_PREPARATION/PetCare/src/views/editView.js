import { createAnimalPost, editAnimalPost, getAnimalById } from '../api/data.js';
import { html } from '../api/lib.js'
import { createSubmitHandler } from '../api/utils.js'

export  async function showEdit(ctx) {

    const id = ctx.params.id;
    const animal = await getAnimalById(id)

    ctx.render(createTemplate(animal, createSubmitHandler(onEdit)));

    async function onEdit({name, breed, age, weight, image}, form) {

        if ([name, breed, age, weight, image].some(e => e === "")) {
            return alert("All fields are required!")
        }
        await editAnimalPost(id, {
            name, 
            breed, 
            age,
            weight,
            image
        })
        form.reset();
        ctx.page.redirect('/catalog/' + id);

    }
}

function createTemplate(animal, onEdit) {
    return html `
       <section id="editPage">
        <form @submit="${onEdit}" class="editForm">
                <img src="./images/editpage-dog.jpg">
                <div>
                    <h2>Edit PetPal</h2>
                    <div class="name">
                        <label for="name">Name:</label>
                        <input name="name" id="name" type="text" .value=${animal.name}>
                    </div>
                    <div class="breed">
                        <label for="breed">Breed:</label>
                        <input name="breed" id="breed" type="text" .value=${animal.breed}>
                    </div>
                    <div class="Age">
                        <label for="age">Age:</label>
                        <input name="age" id="age" type="text" .value=${animal.age}>
                    </div>
                    <div class="weight">
                        <label for="weight">Weight:</label>
                        <input name="weight" id="weight" type="text" .value=${animal.weight}>
                    </div>
                    <div class="image">
                        <label for="image">Image:</label>
                        <input name="image" id="image" type="text" .value=${animal.image}>
                    </div>
                    <button class="btn" type="submit">Edit Pet</button>
                </div>
            </form>
        </section>
    `
}