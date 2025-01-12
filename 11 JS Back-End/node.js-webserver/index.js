const http = require('http');

const server = http.createServer((req, res) => {
    console.log(`Incomming HTTP Request -> ${req.url}`);

    res.writeHead(200, {
        'content-type': 'text/html',
    })
   
    if(req.url == '/pug') {
        res.write('<h1>Pug!</h1>')
    } else { 
        res.write("<h1>Hello from Node.js Web Server!</h1>");
    }
   
    
    res.end();
});

server.listen(5000);
console.log('Server is running on http://localhost:5000...');