import { deleteFactById, getFactById } from '../api/data.js';
import { html, nothing } from '../api/lib.js'
import { getLikesForCurrentUser, getTotalLikesCount, like } from '../api/likes.js';


export async function showDetails(ctx) {
    const id = ctx.params.id;


    let request = [
        getFactById(id),
        getTotalLikesCount(id)
    ];

    const hasUser = Boolean(ctx.user);

    if (hasUser) {
    request.push(getLikesForCurrentUser(id, ctx.user._id))
    }

    let [fact, totalLikes, likes] = await Promise.all(request);

    const isOwner = Boolean(hasUser && ctx.user._id == fact._ownerId);
  
  
    const canLike = !isOwner && likes == 0;

    ctx.render(detailsTemplate(fact, hasUser, isOwner, canLike, onDelete, onLike, totalLikes))

    async function onDelete() {
        const choice = confirm("Are you sure?")

        if (choice) {
            await deleteFactById(id);
            ctx.page.redirect('/catalog');
        }
    }

    async function onLike() {
        
        await like({id});
        const likeBtn = document.getElementById('like-btn');
        likeBtn.style.display = 'none';
        

        ctx.page.redirect('/details/' + id)
    }

   

}

function factControls(fact, hasUser, isOwner, canLike, onDelete, onLike) {
    if (hasUser === false) {
        return nothing;
    } 

    if (canLike) {
        return html `
        <div id="action-buttons">
        <a @click=${onLike} href="javascript:void(0)" id="like-btn">Like</a>
        </div>`
     }

    if (isOwner) {
      return  html`
         <div id="action-buttons">
            <a href="/edit/${fact._id}" id="edit-btn">Edit</a>
            <a @click=${onDelete} href="javascript:void(0)" id="delete-btn">Delete</a>
        </div>`
    }
   
    
    }

function detailsTemplate(fact, hasUser, isOwner, canLike, onDelete, onLike, totalLikes) {
    return html`
      <section id="details">
          <div id="details-wrapper">
            <img id="details-img" src="${fact.imageUrl}" alt="example1" />
            <p id="details-category">${fact.category}</p>
            <div id="info-wrapper">
              <div id="details-description">
                <p id="description">${fact.description}</p>
                   <p id ="more-info">${fact.moreInfo}</p>
              </div>

              <h3>Likes:<span id="likes">${totalLikes}</span></h3>

               ${factControls(fact, hasUser, isOwner, canLike, onDelete, onLike)}
            </div>
          </div>
      </section>
    `
}

    