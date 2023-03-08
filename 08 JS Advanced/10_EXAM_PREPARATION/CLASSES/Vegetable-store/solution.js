class VegetableStore {
    constructor (owner, location){
        this.owner = owner;
        this.location = location,
        this.availableProducts = [];
    }


    loadingVegetables(vegetables) {

        //"{type} {quantity} {price}"
        // quantity and price par kg
        
        let result = "";
        result += "Successfully added "

        for(let i = 0; i < vegetables.length; i ++) {


            let current = vegetables[i];

            let parts = current.split(" ");
           
            let type = parts[0];
            let quantity = Number(parts[1]);
            let price = Number(parts[2]);
            
            let found = this.availableProducts.find(v => v.type === type);
       
            if(!found) {

                let vegetable = {
                    type: type,
                    quantity: quantity,
                    price: price
                }
               
                

                result = result + type + ", "
                this.availableProducts.push(vegetable);
               
            } else {
                found.quantity += quantity;
                if(found.price < price) {
                    found.price = price;
                }
            }
       
           
            
        }

        result = result.substring(0, result.length - 2);
        
        return result;

    }

    buyingVegetables (selectedProducts) {
       // "{type} {quantity}"

       let totalPrice = 0;

       
       for(let i = 0; i < selectedProducts.length; i++) {
        let current = selectedProducts[i];
        let parts = current.split(" ");
        let type = parts[0];
        let quantity = Number(parts[1]);

        

        let found = this.availableProducts.find(v => v.type === type);

        
        if(!found) {
            throw new Error(`${type} is not available in the store, your current bill is $${totalPrice.toFixed(2)}.`)
       }

       if(found.quantity < quantity) {
       throw new Error  (`The quantity ${quantity} for the vegetable ${type} is not available in the store, your current bill is $${totalPrice.toFixed(2)}.`)
       }

       let price = found.price;
       let currentProductPrice = quantity * price;
       found.quantity -= quantity;

       totalPrice += currentProductPrice;

    }

    return `Great choice! You must pay the following amount $${totalPrice.toFixed(2)}.`;
   

}

    rottingVegetable (type, quantity) {
        let found = this.availableProducts.find(v => v.type === type);

        if(!found) {
            throw new Error (`${type} is not available in the store.`);
        }

        found.quantity -= quantity;

        if(found.quantity < 0) {
            found.quantity = 0;
            return "The entire quantity of the Okra has been removed."
        }
       
         return `Some quantity of the ${type} has been removed.`
    }

    revision () {
        let buffer = "";
        buffer += "Available vegetables:\n"

        this.availableProducts.sort((a,b) => a.price - b.price).forEach(v => buffer += `${v.type}-${v.quantity}-$${v.price}\n`);
  
        buffer += `The owner of the store is ${this.owner}, and the location is ${this.location}.`
        
        return buffer.trim();
  
    }

}


let vegStore = new VegetableStore("Jerrie Munro", "1463 Pette Kyosheta, Sofia");

console.log(vegStore.loadingVegetables(["Okra 2.5 3.5", "Beans 10 2.8",

"Celery 5.5 2.2", "Celery 0.5 2.5"]));

console.log(vegStore.rottingVegetable("Okra", 1));

console.log(vegStore.rottingVegetable("Okra", 2.5));

console.log(vegStore.buyingVegetables(["Beans 8", "Celery 1.5"]));

console.log(vegStore.revision());



