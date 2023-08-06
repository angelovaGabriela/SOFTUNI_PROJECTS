import {editFactPost, getFactById} from '../api/data.js';
import { html } from '../api/lib.js'
import { createSubmitHandler } from '../api/utils.js'

export  async function showEdit(ctx) {
    const id = ctx.params.id;
    const fact = await getFactById(id);

    ctx.render(editTemplate(fact, createSubmitHandler(onEdit)));

    async function onEdit({category, ["image-url"] : imageUrl, description, ["additional-info"] : moreInfo}, form) {

        if ([category, imageUrl, description, moreInfo].some(e => e === '')) {
            return alert("All fields are required!")
        }

        await editFactPost(id, {
            category,
            imageUrl,
            description,
            moreInfo
        })

        form.reset();

        ctx.page.redirect('/details/' + id)

    }
}

function editTemplate(fact, handler) {
    return html `
          <section id="edit">
          <div class="form">
            <h2>Edit Fact</h2>
            <form @submit=${handler} class="edit-form">
              <input
              type="text"
              name="category"
              id="category"
              placeholder="Category"
              .value=${fact.category}
            />
            <input
              type="text"
              name="image-url"
              id="image-url"
              placeholder="Image URL"
              .value=${fact.imageUrl}
            />
            <textarea
            id="description"
            name="description"
            placeholder="Description"
            rows="10"
            cols="50"
            .value=${fact.description}
          ></textarea>
          <textarea
            id="additional-info"
            name="additional-info"
            placeholder="Additional Info"
            rows="10"
            cols="50"
            .value=${fact.moreInfo}
          ></textarea>
              <button type="submit">Post</button>
            </form>
          </div>
        </section>
    `
}