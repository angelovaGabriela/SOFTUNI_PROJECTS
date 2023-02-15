'use strict'

function requestValidator(object) {
    let validMethod = ["GET", "POST", "DELETE", "CONNECT"];
    let uriPattern = /^[\w.]+$/g;
    let validVersion = ["HTTP/0.9", "HTTP/1.0", "HTTP/1.1", "HTTP/2.0"];
    let speciqlChars = [`<`, `>`, `\\`, `&`, `'`,`"`];


    if (!validMethod.includes(object.method)) {
        throw new Error("Invalid request header: Invalid Method");
    }

    if(!object.hasOwnProperty("uri")) {
        throw new Error("Invalid request header: Invalid URI");
    }

    if(object.uri !== "*" && !object.uri.match(uriPattern)) {

        throw new Error("Invalid request header: Invalid URI");
    }

    if (!validVersion.includes(object.version)) {
        throw new Error("Invalid request header: Invalid Version");
    }

    if(!object.hasOwnProperty("message")) {
        throw new Error("Invalid request header: Invalid Message");
    }

    for(let char of object.message) {
        if(speciqlChars.includes(char)) {
            throw new Error("Invalid request header: Invalid Message");
        }
    }

    return object;
}