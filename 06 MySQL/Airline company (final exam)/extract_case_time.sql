USE airlines_db;

SELECT flight_code, 
departure, (CASE 
WHEN HOUR(departure) >= 5 AND HOUR(departure) < 12 THEN "Morning"
WHEN HOUR(departure) >= 12 AND HOUR(departure) < 17 THEN "Afternoon"
WHEN HOUR(departure) >= 17 AND HOUR(departure) < 21 THEN "Evening"
WHEN HOUR(departure) >= 21 THEN "Night"
WHEN HOUR(departure) >= 0 AND HOUR(departure) < 5 THEN "Night"

END) AS day_part
FROM flights
ORDER BY flight_code DESC;

