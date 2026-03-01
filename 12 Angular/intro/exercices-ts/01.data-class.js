var Data = /** @class */ (function () {
    function Data(method, url, version, message) {
        this.method = method;
        this.uri = url;
        this.version = version;
        this.message = message;
        this.response = undefined;
        this.fulfilled = false;
    }
    return Data;
}());
var myData = new Data('GET', 'http://google.com', 'HTTP/1.1', '');
console.log(myData);
