import { html, nothing } from '../api/lib.js'
import { search } from '../api/data.js'
import { getUserData } from '../api/utils.js';
 
export async function showSearch(ctx) {
  let user = getUserData(ctx.user);
  console.log(user);
  let moto = undefined;

const name = ctx.querystring.split('=')[1];
    if(name !== undefined) {
      moto = await search(name);
    }
    console.log(moto);
    ctx.render(searchTemplate(moto, user, onSearch));

    async function onSearch() {
      const query = document.getElementById('search-input').value;
      if (query !== '') {
          ctx.page.redirect(`/search?query=${query}`);
      } else {
          return alert('All fields are required!');
      }
  }


}

function searchTemplate(moto, user, onSearch) {
    return html `
      <section id="search">

<div class="form">
  <h4>Search</h4>
  <form class="search-form">
    <input
      type="text"
      name="search"
      id="search-input"
    />
    <button @click=${onSearch} class="button-list">Search</button>
  </form>
</div>
<h4 id="result-heading">Results:</h4>
${moto != undefined
  ? html `<div class="search-result">
     ${moto.length == 0 ? html`
     <h2 class="no-avaliable">No result.</h2>`
 : moto.map(m => html `
                <div class="motorcycle">
                 <img src="${m.imageUrl}" alt="example1" />
                 <h3 class="model">${m.model}</h3>
                 <a class="details-btn" href="/details/${m._id}">More Info</a>
                </div>`)
}

  </div>` : nothing } 
        </section>`
}
