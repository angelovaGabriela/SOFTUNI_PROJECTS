class Company {
    constructor() {
        this.departments = {};
    }

    addEmployee(name, salary, position, department) {

        if (!name || !salary || salary < 0 || !position || !department) {
            throw new Error("Invalid input!");
        }

        if(!this.departments[department]) {
            this.departments[department] = {
                avgSalary: 0,
                sumSalary: 0,
                employees: []
            }
        }
        this.departments[department].employees.push({name, salary, position});
        this._calculateValues(this.departments[department], salary);
        return `New employee is hired. Name: ${name}. Position: ${position}`;
    }

    _calculateValues(department, salary) {
        department.sumSalary += Number(salary);
        department.avgSalary = department.sumSalary / department.employees.length;
    }

    bestDepartment () {
        let best = Object.entries(this.departments)
        .sort(([departOne, infoOne], [departTwo, infoTwo]) => {
           return infoTwo.avgSalary - infoOne.avgSalary
        })[0];

    
        let employees = best[1].employees.sort((a,b) => {
            return b.salary - a.salary || a.name.localeCompare(b.name);
        })


        let bufferReader = `Best Department is: ${best[0]}\n`;
        bufferReader += `Average salary: ${best[1].avgSalary.toFixed(2)}\n`

        for(let i = 0; i < employees.length; i++) {
            let empl = employees[i];
            bufferReader += `${empl.name} ${empl.salary} ${empl.position}`

            bufferReader += i === employees.length - 1 ? "" : `\n`
        }

        return bufferReader;
    }
}

let c = new Company();

c.addEmployee("Stanimir", 2000, "engineer", "Construction");

c.addEmployee("Pesho", 1500, "electrical engineer", "Construction");

c.addEmployee("Slavi", 500, "dyer", "Construction");

c.addEmployee("Stan", 2000, "architect", "Construction");

c.addEmployee("Stanimir", 1200, "digital marketing manager", "Marketing");

c.addEmployee("Pesho", 1000, "graphical designer", "Marketing");

c.addEmployee("Gosho", 1350, "HR", "Human resources");

console.log(c.bestDepartment());