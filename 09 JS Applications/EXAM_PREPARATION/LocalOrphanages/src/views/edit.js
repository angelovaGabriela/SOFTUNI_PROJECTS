import { editPost, getMaterialById } from '../api/data.js';
import { html } from '../api/lib.js'
import  { createSubmitHandler } from '../api/utils.js'


export async function showEdit(ctx) {
    const id = ctx.params.id;
    const material  = await getMaterialById(id)
    ctx.render(editTemplate(material, createSubmitHandler(onEdit)));

    async function onEdit({title, description, imageUrl, address, phone}, form) {
       
        if ([title, description, imageUrl, address, phone].some(e => e == '')) {
            return alert("All fields are required!")
        }

        await editPost(id, {
            title, 
            description, 
            imageUrl, 
            address, 
            phone
        });
        form.reset();

        ctx.page.redirect('/details/' + id);
    }
}
function editTemplate(material, onEdit) {
    
    return html `
        <section id="edit-page" class="auth">
            <form @submit=${onEdit} id="edit">
                <h1 class="title">Edit Post</h1>

                <article class="input-group">
                    <label for="title">Post Title</label>
                    <input type="title" name="title" id="title" .value=${material.title}>
                </article>

                <article class="input-group">
                    <label for="description">Description of the needs </label>
                    <input type="text" name="description" id="description" .value=${material.description}>
                </article>

                <article class="input-group">
                    <label for="imageUrl"> Needed materials image </label>
                    <input type="text" name="imageUrl" id="imageUrl" .value=${material.imageUrl}>
                </article>

                <article class="input-group">
                    <label for="address">Address of the orphanage</label>
                    <input type="text" name="address" id="address" .value=${material.address}>
                </article>

                <article class="input-group">
                    <label for="phone">Phone number of orphanage employee</label>
                    <input type="text" name="phone" id="phone" .value=${material.phone}>
                </article>

                <input type="submit" class="btn submit" value="Edit Post">
            </form>
        </section>
    `
}