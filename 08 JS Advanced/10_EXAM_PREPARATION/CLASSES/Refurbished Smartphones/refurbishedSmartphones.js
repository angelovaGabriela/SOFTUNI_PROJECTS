class RefurbishedSmartphones {
    constructor(retailer) {
        this.retailer = retailer;
        this.availableSmartphones = [];
        this.soldSmartphones = [];
        this.revenue = 0;
    }
    addSmartphone (model, storage, price, condition) {
        if(model === "" || typeof model !== "string"
        || typeof storage !== "number" || storage < 0 
        || typeof price !== "number" || price < 0
        || condition === "" || typeof condition !== "string") {
           
            throw new Error ("Invalid smartphone!");
        } else {
            let smartphone = {
                model: model,
                storage: storage,
                price: price,
                condition: condition
            }
            this.availableSmartphones.push(smartphone);
            return `New smartphone added: ${model} / ${storage} GB / ${condition} condition - ${price.toFixed(2)}$`;
        }

    }
    sellSmartphone (model, desiredStorage) {
        let found = this.availableSmartphones.find(p => p.model === model);
        if(!found) {
            throw new Error(`${model} was not found!`);
        } 
            let difference = desiredStorage - found.storage;

            if(found.storage >= desiredStorage) {
                found.price = found.price;
            }
            else if (difference <= 128) {
                found.price = found.price - (found.price * 0.1);
            } else if (difference > 128) {
                found.price = found.price - (found.price * 0.2);
            }

        this.availableSmartphones = this.availableSmartphones.filter(p => p.model !== found.model);
        let sold = {
            model: model,
            storage: found.storage,
            soldPrice: found.price
        }
        this.soldSmartphones.push(sold);
        this.revenue += found.price;
        return `${model} was sold for ${found.price.toFixed(2)}$`
  
  
    }
    upgradePhones () {
        if(this.availableSmartphones.length === 0) {
            throw Error("There are no available smartphones!");
        } 
       
      this.availableSmartphones.forEach(p => p.storage = p.storage * 2);

        let result = "Upgraded Smartphones:\n"
        this.availableSmartphones.forEach(p => result += `${p.model} / ${p.storage} GB / ${p.condition} condition / ${p.price.toFixed(2)}$\n`);
 
        return result.trim();
   
    }
    salesJournal (criteria) {
        if(criteria === "storage") {
            this.soldSmartphones.sort((a,b) => b.storage - a.storage);
        
            let result = `${this.retailer} has a total income of ${this.revenue.toFixed(2)}$\n`;
            result += `${this.soldSmartphones.length} smartphones sold:\n`;

            this.soldSmartphones.forEach(p => result += `${p.model} / ${p.storage} GB / ${p.soldPrice.toFixed(2)}$\n`);

            return result.trim();

        
        } else if (criteria === "model") {
            this.soldSmartphones.sort((a,b) => a.model.localeCompare(b.model));

            let result = `${this.retailer} has a total income of ${this.revenue.toFixed(2)}$\n`;
            result += `${this.soldSmartphones.length} smartphones sold:\n`;

            this.soldSmartphones.forEach(p => result += `${p.model} / ${p.storage} GB / ${p.soldPrice.toFixed(2)}$\n`);

            return result.trim();
        } else {
            throw new Error ("Invalid criteria!");
        }
    }
}

