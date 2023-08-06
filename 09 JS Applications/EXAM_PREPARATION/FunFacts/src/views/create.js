import { createFactPost} from '../api/data.js';
import { html } from '../api/lib.js'
import { createSubmitHandler } from '../api/utils.js'

export  async function showCreate(ctx) {
    ctx.render(createTemplate(createSubmitHandler(onCreate)));

    async function onCreate({category, ["image-url"]: imageUrl, description, ["additional-info"] : moreInfo}) {

        if ([category, imageUrl, description, moreInfo].some(e => e === "")) {
            return alert("All fields are required!")
        }

        await createFactPost({
                category,
                imageUrl,
                description,
                moreInfo
        })

        ctx.page.redirect('/catalog')

    }
}

function createTemplate(handler) {
    return html `
        <section id="create">
          <div class="form">
            <h2>Add Fact</h2>
            <form @submit=${handler} class="create-form">
              <input
                type="text"
                name="category"
                id="category"
                placeholder="Category"
              />
              <input
                type="text"
                name="image-url"
                id="image-url"
                placeholder="Image URL"
              />
              <textarea
              id="description"
              name="description"
              placeholder="Description"
              rows="10"
              cols="50"
            ></textarea>
            <textarea
              id="additional-info"
              name="additional-info"
              placeholder="Additional Info"
              rows="10"
              cols="50"
            ></textarea>
              <button type="submit">Add Fact</button>
            </form>
          </div>
        </section>
    `
}