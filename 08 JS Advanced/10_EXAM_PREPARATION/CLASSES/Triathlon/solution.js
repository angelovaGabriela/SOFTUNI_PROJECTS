class Triathlon {
    constructor(competitionName) {
        this.competitionName = competitionName;
        this.participants = {};
        this.listOfFinalists = [];
    }

    addParticipant(participantName, participantGender) {


        if (!this.participants[participantName]) {
            this.participants[participantName] = participantGender;
            return `A new participant has been added - ${participantName}`;

        } else {

            return `${participantName} has already been added to the list`;

        }




    }
    completeness(participantName, condition) {
        if (!this.participants[participantName]) {
            throw new Error(`${participantName} is not in the current participants list`);
        } else if (this.participants[participantName] && condition < 30) {
            throw new Error(`${participantName} is not well prepared and cannot finish any discipline`);
        }

        let completedCount = Math.floor(condition / 30);

        if (completedCount >= 1 && completedCount <= 2) {
            return `${participantName} could only complete ${completedCount} of the disciplines`;
        } else {

            let participantGender = this.participants[participantName];
            this.listOfFinalists.push({
                name: participantName,
                gender: participantGender
            });

            return `Congratulations, ${participantName} finished the whole competition`;
        }
    }
    rewarding(participantName) {
        let found = this.listOfFinalists.find(p => p.name === participantName);
        if (!found) {
            return `${participantName} is not in the current finalists list`
        }
        return `${participantName} was rewarded with a trophy for his performance`
    }
    showRecord(criteria) {
        let found = this.listOfFinalists.find(p => p.gender === criteria && p.gender)
        if (this.listOfFinalists.length === 0) {
            return `There are no finalists in this competition`;
        } else if (criteria === "all") {

            let result = `List of all ${this.competitionName} finalists:\n`
            this.listOfFinalists
                .sort((a, b) => a.name.localeCompare(b.name))
                .forEach(p => result += `${p.name}\n`);

            result = result.trim();
            return result;
        } else if (!found) {
            return `There are no ${criteria}'s that finished the competition`
        } else if (found) {
            return `${found.name} is the first ${criteria} that finished the ${this.competitionName} triathlon`;
        }
    }
}

