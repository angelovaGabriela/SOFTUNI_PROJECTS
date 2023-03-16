class footballTeam {

    constructor(clubName, country) {

        this.clubName = clubName;
        this.country = country;
        this.invitedPlayers = [];
    }

    newAdditions(footballPlayers) {
        let players = new Set();
        for(let i = 0; i < footballPlayers.length; i++) {
            let currentPlayer = footballPlayers[i].split("/");
            let playerName = currentPlayer[0];
            let playerAge = currentPlayer[1];
            let currentValue = currentPlayer[2];
        
            let found = this.invitedPlayers.find(p => p.name === playerName);
            if(!found) {
                let player = {
                    name: playerName, 
                    age: playerAge,
                    playerValue: currentValue
                }
                this.invitedPlayers.push(player);
                players.add(player);
            } else {

                if(found.playerValue < currentValue ) {
                    found.playerValue = currentValue;
                }
            }
         
        }

        let result = "You successfully invite "; 
        players.forEach(p => result += `${p.name}, `)
        result = result.substring(0, result.length - 2);

 
        return result + ".";
    }

    signContract(selectedPlayer) {
        // "{name}/{playerOffer}" 

        let tokens = selectedPlayer.split("/");
        let playerName = tokens[0];
        let playerOffer = Number(tokens[1]);

        let found = this.invitedPlayers.find(p => p.name === playerName);
        

        if(!found) {
            throw new Error(`${playerName} is not invited to the selection list!`)
        } else if (playerOffer <  Number(found.playerValue)) {

            let recordedValue = Number(found.playerValue)
            let priceDifference = Math.abs(playerOffer - recordedValue)
            
            throw new Error(`The manager's offer is not enough to sign a contract with ${playerName}, ${priceDifference} million more are needed to sign the contract!`);
     
        } else {
            found.playerValue = "Bought"
            return `Congratulations! You sign a contract with ${playerName} for ${playerOffer} million dollars.`
        }

    }
    ageLimit(name, age) {
        // string, number

        let found = this.invitedPlayers.find(p => p.name === name);

        if(!found) {
            throw new Error(`${name} is not invited to the selection list!`);
        } else if (found.age < age) {
            let ageDifference = Math.abs(found.age - age)
            if (ageDifference < 5) {
                return `${name} will sign a contract for ${ageDifference} years with ${this.clubName} in ${this.country}!`;
            } else if (ageDifference > 5) {
                return `${name} will sign a full 5 years contract for ${this.clubName} in ${this.country}!`;
            } 
        }   else if (found.age >= age){
            return  `${name} is above age limit!`
        }

    }
    transferWindowResult() {
        let result = "Players list:\n"
        this.invitedPlayers.sort((a,b) => a.name.localeCompare(b.name)).forEach(p => result += `Player ${p.name}-${p.playerValue}\n` );
  
        return result;
    }
}


let fTeam = new footballTeam("Barcelona", "Spain"); 

console.log(fTeam.newAdditions(["Kylian Mbappé/23/160", "Lionel Messi/35/50", "Pau Torres/25/52"])); 

console.log(fTeam.signContract("Kylian Mbappé/240")); 

console.log(fTeam.ageLimit("Kylian Mbappé", 30)); 

console.log(fTeam.transferWindowResult()); 