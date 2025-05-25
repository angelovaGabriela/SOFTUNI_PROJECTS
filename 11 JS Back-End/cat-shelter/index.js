const {log} = require('console');
const http = require('http');
const indexTemplate = require('./views/home/index.html');
const siteCss = require('./content/styles/site.css');
const addBreedHtml = require('./views/addBreed.html');
const addCatHtml = require('./views/addCat.html');
const port = 5000;

const cats = [
    {
        id: 1,
        imageUrl: 'https://s1.qwant.com/thumbr/474x474/9/4/7857638b8dc06f8175d4c9c06b6d592348a5044990ea38579b36afb4deb016/th.jpg?u=https%3A%2F%2Ftse.mm.bing.net%2Fth%3Fid%3DOIP.XmWCtym0T-hmnjVnhWeVRAHaHa%26pid%3DApi&q=0&b=1&p=0&a=0',
        name: 'Joshua',
        breed: 'Bombay cat',
        description: 'Dominant and aggressive to other cats. Will probably eat you in your sleep. Very cute tho.'
    },
     {
        id: 2,
        imageUrl: 'https://s1.qwant.com/thumbr/474x411/b/2/94959d320a27383772d9de6303b333f8edc07c04c041146cc9fecb0466ac8e/OIP.CCr3Sv6ZFiCtHrsuobiwmgHaGb.jpg?u=https%3A%2F%2Ftse.mm.bing.net%2Fth%2Fid%2FOIP.CCr3Sv6ZFiCtHrsuobiwmgHaGb%3Fpid%3DApi&q=0&b=1&p=0&a=0',
        name: 'Nala',
        breed: 'Bombay cat',
        description: 'Dominant and aggressive to other cats. Will probably eat you in your sleep. Very cute tho.'
    },
     {
        id: 3,
        imageUrl: 'https://s2.qwant.com/thumbr/474x355/5/6/79168308993aa410509a6be8b61511d7c8f558848807027bee11c238029919/OIP.W90hjx-9K044lX37C-pDNQHaFj.jpg?u=https%3A%2F%2Ftse.mm.bing.net%2Fth%2Fid%2FOIP.W90hjx-9K044lX37C-pDNQHaFj%3Fpid%3DApi&q=0&b=1&p=0&a=0',
        name: 'Gafrield',
        breed: 'Bombay cat',
        description: 'Dominant and aggressive to other cats. Will probably eat you in your sleep. Very cute tho.'
    },
     {
        id: 4,
        imageUrl: 'https://s2.qwant.com/thumbr/474x355/4/3/122b494dfdf442eb8c14bd71353d7d8a591638bc06df48ca05e3ce1cb23d5f/th.jpg?u=https%3A%2F%2Ftse.mm.bing.net%2Fth%3Fid%3DOIP.fXGRUtMItnHlp_Ifqy2eiAHaFj%26pid%3DApi&q=0&b=1&p=0&a=0',
        name: 'Guizmo',
        breed: 'Bombay cat',
        description: 'Dominant and aggressive to other cats. Will probably eat you in your sleep. Very cute tho.'
    }
];

const server = http.createServer((req, res) => {
    if (req.url === '/styles/site.css') {
         res.writeHead(200, {
        'content-type': 'text/css'
        });
        res.write(siteCss);
        return res.end();
    }
     res.writeHead(200, {
        'content-type': 'text/html'
        });

    switch(req.url) {
        case '/': 
            res.write(indexTemplate(cats));
            break;
        case '/cats/add-breed':
            res.write(addBreedHtml)
            break;
        case '/cats/add-cat':
            res.write(addCatHtml);
        
        default:
            res.write(`<h1>Page not found!</h1>`);
            break;
    }
   
    res.end();
});


server.listen(port);

console.log(`Server is listening on port http://localhost:${port}`);
