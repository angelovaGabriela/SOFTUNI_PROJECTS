const {log} = require('console');
const http = require('http');
const fs = require('fs/promises')
const querystring = require('querystring')
const {EOL} = require('os');

const siteCss = require('./content/styles/site.css');
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

function readFile(path) {
    return fs.readFile(path, {encoding: 'utf-8'});    
}

function render(html, data) {
const resultHtml = Object
.keys(data)
.reduce((result, key) => result.replaceAll(`{{${key}}}`, data[key]), html);

return resultHtml;
}



async function renderCat(catData) {
    let catHtml = await readFile('./views/cat.html');
   
    return render(catHtml,catData);

}

async function renderHome(cats) {
    let indexHtml = await readFile('./views/home/index.html');
   
    const catsHtmlResult = await Promise.all(cats.map(renderCat));

    return render(indexHtml, {cats: catsHtmlResult.join('\n')});
}

// function parseFile(part) {
//    const namePattern = new RegExp(`name="[^"]+"`, 'm');
//    const valuePattern = new RegExp(`${EOL}${EOL}(.*)`, '');

//    const nameMatch = part.match(namePattern);
//    const valueMatch = part.match(valuePattern);

//    const name = nameMatch[1];
//    const value = part.slice(valueMatch.index).trim();

//    return [name, value];

// }

const server = http.createServer(async(req, res) => {
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
            const indexHtml = await renderHome(cats);
            res.write(indexHtml);
            return res.end();
        case '/cats/add-breed':
            const addBreedHtml = await readFile('./views/addBreed.html');
            res.write(addBreedHtml);
            return res.end();
        case '/cats/add-cat':
            if (req.method === 'GET') {
                   const addCat = await readFile('./views/addCat.html');
                   res.write(addCat); 
            // } else if (req.method === 'POST') {
            //     let body = '';

            //     req.on('data', (chunk) => {
            //       body += chunk;
            //     });

            //     req.on('end', async() => {

            //       const boundary = req.headers['content-type'].split('boundary=').at(1);
            //       const parts = body.split(`--${boundary}`).filter(part => !!part).slice(0, -1);
                  
            //       const [name, value] = parseFile(parts[2]);
            //       console.log(name);
            //       console.log(value);

            //       await fs.writeFile(`./uploads/image.jpg`, value);

            //         res.end();
            //     });
            // }

} else if (req.method === 'POST') {
    const chunks = [];

    req.on('data', chunk => {
        chunks.push(chunk);
    });

    req.on('end', async () => {
        const buffer = Buffer.concat(chunks);

        const boundary = '--' + req.headers['content-type'].split('boundary=')[1];
        const parts = buffer.toString('latin1').split(boundary).slice(1, -1); // skip preamble and final boundary

        for (const part of parts) {
            const [rawHeaders, rawBody] = part.split('\r\n\r\n');

            if (!rawHeaders || !rawBody) continue;

            const isFile = rawHeaders.includes('filename=');
            if (!isFile) continue;

            const filenameMatch = rawHeaders.match(/filename="(.+?)"/);
            const filename = filenameMatch ? filenameMatch[1] : 'upload.bin';

            // Find the real body start index in the full buffer
            const partBuffer = Buffer.from(part, 'latin1'); // preserve raw bytes
            const bodyStartIndex = partBuffer.indexOf('\r\n\r\n') + 4;
            const bodyEndIndex = partBuffer.length - 2; // strip trailing \r\n

            const fileBuffer = partBuffer.slice(bodyStartIndex, bodyEndIndex);

            await fs.writeFile(`./uploads/${filename}`, fileBuffer);
            console.log(`File saved: uploads/${filename}`);
        }

     //   res.writeHead(200, { 'Content-Type': 'text/html' });
        res.end('<h2>Upload completed</h2>');
    });
}
         
            break;
        default:
            res.write(`<h1>Page not found!</h1>`);
            return res.end();
    }
   
});


server.listen(port);

console.log(`Server is listening on port http://localhost:${port}`);
