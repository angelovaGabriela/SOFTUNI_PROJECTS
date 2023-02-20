(function arrayExtention () {
// let myArray = [1, 3, 4]
    Array.prototype.last = function () {
        return this[this.length - 1];
    }

    Array.prototype.skip = function (n) {
        return this.slice(n);
    }

    Array.prototype.take = function (n) {
        return this.slice(0, n);
    }

    Array.prototype.sum = function () {
        return this.reduce((v1, v2) => v1 + v2);
    }

    Array.prototype.average = function () {
         return this.sum() / this.length;
    }

    // console.log(myArray.average());
})()

arrayExtention();