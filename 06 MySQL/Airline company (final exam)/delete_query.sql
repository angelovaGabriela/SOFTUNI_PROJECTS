USE airlines_db;

DELETE FROM flights 
WHERE id NOT IN (SELECT flight_id FROM flights_passengers); 