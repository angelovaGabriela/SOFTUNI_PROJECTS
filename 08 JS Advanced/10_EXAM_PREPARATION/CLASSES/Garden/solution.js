
class Garden {

    constructor(spaceAvailable) {
        this.spaceAvailable = spaceAvailable;
        this.plants = [];
        this.storage = [];
    }
    
    addPlant (plantName, spaceRequired) {
        
        if (spaceRequired > this.spaceAvailable) {
            throw new Error("Not enough space in the garden.");
        } else {
            let plant = {
                plantName,
                spaceRequired, 
                ripe: false,
                quantity: 0
            }
        this.plants.push(plant);
        this.spaceAvailable -= spaceRequired;
        return `The ${plantName} has been successfully planted in the garden.`
    
    }
    }

    ripenPlant(plantName, quantity) {
        let currentPlant = this.plants.find(plant => plant.plantName === plantName);
        
        if(!currentPlant) {
            throw new Error(`There is no ${plantName} in the garden.`);
        } else if(currentPlant.ripe === true) {
           throw new Error (`The ${plantName} is already ripe.`);
        } else if (quantity <= 0) {
            throw new Error(`The quantity cannot be zero or negative.`);
        } else { 
            currentPlant.ripe = true;
            currentPlant.quantity = quantity;
           return quantity === 1
           ? `${quantity} ${plantName} has successfully ripened.` 
           : `${quantity} ${plantName}s have successfully ripened.`
        }
    }

    harvestPlant(plantName) {
        let currentPlant = this.plants.find(plant => plant.plantName === plantName);

        if (!currentPlant) {
            throw new Error(`There is no ${plantName} in the garden.`);
        } else if (currentPlant.ripe === false) {
            throw new Error(`The ${plantName} cannot be harvested before it is ripe.`);
        } else {
           this.plants = this.plants.filter(plant => plant.plantName !== plantName);

           let plant = {
            plantName,
            quantity: currentPlant.quantity
           }
        this.storage.push(plant);
        this.spaceAvailable += currentPlant.spaceRequired;

        return `The ${plantName} has been successfully harvested.`;
        }
    }


    generateReport() {
        let buffer = "";

        buffer += (`The garden has ${this.spaceAvailable} free space left.\n`)
      
        buffer += (`Plants in the garden: `);
        this.plants.sort((a,b) => a.plantName.localeCompare(b.plantName)).forEach(plant => buffer += `${plant.plantName}, `);
        buffer = buffer.substring(0, buffer.length - 2);
    
    
       if(!this.storage.length) {
        buffer += `\nPlants in storage: The storage is empty.`
       } else {
        buffer += `\nPlants in storage: `
        this.storage.forEach(plant => buffer += `${plant.plantName} (${plant.quantity}), `);
        buffer = buffer.substring(0, buffer.length - 2);
       }  
        
        return buffer;
    }
}










