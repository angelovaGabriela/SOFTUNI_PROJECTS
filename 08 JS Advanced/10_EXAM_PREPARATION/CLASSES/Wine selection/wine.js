class WineSelection {
    constructor(space) {
        this.space = space;
        this.wines = [];
        this.bill = 0;
    }

    reserveABottle(wineName, wineType, price) {
        if (this.space === 0) {
            throw new Error("Not enough space in the cellar.");
        } else {
            let wine = {
                wineName: wineName,
                wineType: wineType,
                price: price,
                paid: false
            }
            this.wines.push(wine);
            this.space -= 1;
            return `You reserved a bottle of ${wineName} ${wineType} wine.`;
        }
    }
    payWineBottle(wineName, price) {
        let found = this.wines.find(w => w.wineName === wineName);

        if (!found) {
            throw new Error(`${wineName} is not in the cellar.`);
        } else if (found.paid === true) {
            return `${wineName} has already been paid.`
        } else {
            found.paid = true;
            this.bill += price;

            return `You bought a ${wineName} for a ${price}$.`
        }

    }
    openBottle(wineName) {
        let found = this.wines.find(w => w.wineName === wineName);

        if (!found) {
            throw new Error("The wine, you're looking for, is not found.");
        } else if (found.paid === false) {
            throw new Error(`${wineName} need to be paid before open the bottle.`);
        } else {
            this.wines = this.wines.filter(w => w.wineName !== wineName);
            return `You drank a bottle of ${wineName}.`
        }
    }
    cellarRevision(wineType) {
        let result = "";
        if (!wineType) {

            result += `You have space for ${this.space} bottles more.\n`
            result += `You paid ${this.bill}$ for the wine.\n`

            this.wines
                .sort((a, b) => a.wineName.localeCompare(b.wineName))
                .forEach(w => result += `${w.wineName} > ${w.wineType} - ${w.paid === true ? 'Has Paid' : 'Not Paid'}.\n`);

            return result.trim();

        } else {

            let found = this.wines.find(w => w.wineType === wineType);
            if (!found) {
                throw new Error(`There is no ${wineType} in the cellar.`);
            } else {
                result += `${found.wineName} > ${found.wineType} - ${found.paid === true ? 'Has Paid' : 'Not Paid'}.`
                return result;
            }
        }
    }
}


