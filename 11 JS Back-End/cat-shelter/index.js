const {log} = require('console');
const http = require('http');
const port = 5000;


const server = http.createServer((req, res) => {
    res.write('It works..');
    res.end();
});

server.listen(port);

console.log(`Server is listening on port http://localhost:${port}`);
