USE airlines_db;

## countries with 20 or more boocked tickets

SELECT c.name, c.currency, COUNT(f.destination_country) AS booked_tickets
FROM passengers AS p
JOIN flights_passengers AS fp
ON p.id = fp.passenger_id
JOIN flights AS f
ON fp.flight_id = f.id
JOIN countries AS c
ON f.destination_country = c.id
GROUP BY c.name, c.currency
HAVING COUNT(f.destination_country) >= 20 
ORDER BY booked_tickets DESC;

