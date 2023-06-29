async function loadCommits() {
	const username = document.getElementById('username').value;
	const repository = document.getElementById('repo').value;


    const response = await fetch(`https://api.github.com/repos/${username}/${repository}/commits`)

    try {
        if (response.status == 404) {
			throw new Error(`Error: ${response.status} (Not Found)`);
		}
		const data = await response.json();

        const list = getElementById('commits');

        const items = data.map(info => {
			const li = document.createElement('li');
			li.textContent = `${info.commit.author.name}: ${info.commit.message}`
			list.appendChild(li);

			return li;
        });

        list.replaceChildren(...items);



    } catch(err) {
        const list = document.getElementById('commits');
		list.textContent = err.message;
        
    }
    
}