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
}

