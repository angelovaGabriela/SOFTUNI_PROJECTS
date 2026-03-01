class Ticket {
    destination: string;
    price: number;
    status: string;

    constructor(destination: string, price: number, status: string) {
        this.destination = destination;
        this.price = price;
        this.status = status;       
    }
}

function processTickets(ticketsArray: string[], sortedBy: string): Ticket[] {
    const tickets: Ticket[] = ticketsArray.map(str => {
        const parts = str.split('|');

        return new Ticket(
            parts[0], 
            Number(parts[1]),
            parts[2]
        )
    });

    tickets.sort((a,b) => {
        let valueA: string | number;
        let valueB: string | number;

        if (sortedBy === 'destination') {
            valueA = a.destination;
            valueB = b.destination;
        } else if (sortedBy === 'price') {
            valueA = a.price;
            valueB = b.price;
        } else if (sortedBy === 'status') {
            valueA  = a.status;
            valueB = b.status;
        } else {
            valueA = a.destination;
            valueB = b.destination;
        }
        if (typeof valueA === 'string' && typeof valueB === 'string') {
            return valueA.localeCompare(valueB);
        } else {
            return (valueA as number) - (valueB as number)
        }
    })

    return tickets;
}

const byDestination = processTickets(
[
'Philadelphia|94.20|available',
'New York City|95.99|available',
'New York City|95.99|sold',
'Boston|126.20|departed'
],
'destination'
);

console.log(byDestination);

const byStatus = processTickets(
[
'Philadelphia|94.20|available',
'New York City|95.99|available',
'New York City|95.99|sold',
'Boston|126.20|departed'
],
'status');

console.log(byStatus);