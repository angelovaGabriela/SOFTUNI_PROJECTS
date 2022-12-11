USE airlines_db;

INSERT INTO airplanes (model, passengers_capacity, tank_capacity, cost)

SELECT CONCAT(REVERSE(first_name), "797") AS model,
CHAR_LENGTH(last_name) * 17 AS passengers_capacity,
id * 790 AS tank_capacity,
CHAR_LENGTH(first_name) * 50.6 AS cost
 FROM passengers 
WHERE id <= 5;