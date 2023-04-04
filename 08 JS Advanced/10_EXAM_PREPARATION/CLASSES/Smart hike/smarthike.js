class SmartHike {
    constructor(username) {
        this.username = username;
        this.goals = {}; 
        // key-value pair of a peak’s name and its altitude. 
        this.listOfHikes = [];
        this.resources = 100;
    }

    addGoal (peak, altitude) {

        if(!this.goals[peak]) {
            this.goals[peak] = altitude;
            return `You have successfully added a new goal - ${peak}`;
        } else {
            return `${peak} has already been added to your goals`;
        }
    }
    hike (peak, time, difficultyLevel) {
        let difference = this.resources - (time * 10);
        if(!this.goals[peak]) {
            throw new Error(`${peak} is not in your current goals` );
        } else if (this.resources === 0) {
            throw new Error("You don't have enough resources to start the hike");
        } else if(difference < 0) {
            return "You don't have enough resources to complete the hike";
        } else {
            this.resources = difference;
            let newGoal = {
                peak: peak,
                time: time,
                difficultyLevel: difficultyLevel
            }
            this.listOfHikes.push(newGoal);
            return `You hiked ${peak} peak for ${time} hours and you have ${this.resources}% resources left`; 
        }
    }
    rest (time) {
        this.resources += (time * 10);

        if (this.resources >= 100) {
            this.resources = 100;
            return `Your resources are fully recharged. Time for hiking!`;
        } else {
            return `You have rested for ${time} hours and gained ${time*10}% resources` 
        }
    }
    showRecord (criteria) {
        if (this.listOfHikes.length === 0) {
          return  `${this.username} has not done any hiking yet` 
        } else if(criteria === "easy" || criteria === "hard") {
         
            let record = this.listOfHikes.filter(h => h.difficultyLevel === criteria).sort((a,b) => a.time - b.time)[0];
        
          if(record) {

            return `${this.username}'s best ${criteria} hike is ${record.peak} peak, for ${record.time} hours`;
          

        } else {
            return `${this.username} has not done any ${criteria} hiking yet` 
        
        } 
         
        } else if (criteria === "all") {
            let result = "All hiking records:\n";
             this.listOfHikes.forEach(h => result += `${this.username} hiked ${h.peak} for ${h.time} hours\n`);
       
             return result.trim();
             
        }
    }
}
 

