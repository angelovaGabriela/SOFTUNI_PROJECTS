class OnlineShop {
    constructor(warehouseSpace) {
        this.warehouseSpace = warehouseSpace;
        this.products = [];
        this.sales = [];
    }

    loadingStore(product, quantity, spaceRequired) {
        if (this.warehouseSpace < spaceRequired) {
            throw new Error("Not enough space in the warehouse.");
        } else {
            let newProduct  = {
                product: product,
                quantity: quantity
            }

            this.products.push(newProduct);
            this.warehouseSpace -= spaceRequired;

            return `The ${product} has been successfully delivered in the warehouse.`

        }
    }
    quantityCheck(product, minimalQuantity) {
        let found = this.products.find(p => p.product === product);
        if (!found) {
            throw new Error(`There is no ${product} in the warehouse.`)
        } else if (minimalQuantity <= 0) {
            throw new Error(`The quantity cannot be zero or negative.`);
        } else if (minimalQuantity <= found.quantity) {

            return `You have enough from product ${product}.`
        } else {
            let difference = Math.abs(found.quantity - minimalQuantity);
            found.quantity = minimalQuantity;
            return `You added ${difference} more from the ${product} products.`
        }
    }
    sellProduct(product) {
        let found = this.products.find(p => p.product === product);

        if (!found) {
            throw new Error(`There is no ${product} in the warehouse.`);
        }

        found.quantity -= 1;

        let newProduct = {
            product: product,
            quantity: 1
        }
        this.sales.push(newProduct);

        return `The ${product} has been successfully sold.`
    }
    revision() {
        if(this.sales.length === 0) {
            throw new Error("There are no sales today!")
        }
        let result = `You sold ${this.sales.length} products today!\n`
        result += `Products in the warehouse:\n`;

        this.products.forEach(p => result += `${p.product}-${p.quantity} more left\n`);
        return result.trim();
    }
}


const myOnlineShop = new OnlineShop(500)

console.log(myOnlineShop.loadingStore('headphones', 10, 200));

console.log(myOnlineShop.loadingStore('laptop', 5, 200));

console.log(myOnlineShop.quantityCheck('headphones', 10));

console.log(myOnlineShop.quantityCheck('laptop', 10));

console.log(myOnlineShop.sellProduct('headphones'));

console.log(myOnlineShop.sellProduct('laptop'));

console.log(myOnlineShop.revision());