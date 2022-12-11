USE airlines_db;

SELECT  CONCAT(UPPER(LEFT(last_name, 2)), country_id) AS flight_code,
CONCAT(first_name, " ", last_name) AS full_name,
country_id
FROM passengers
WHERE id NOT IN (SELECT passenger_id FROM flights_passengers)
ORDER BY country_id ASC;