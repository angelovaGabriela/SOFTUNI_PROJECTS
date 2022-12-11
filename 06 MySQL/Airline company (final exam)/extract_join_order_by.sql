USE airlines_db;

SELECT c.id, a.model, a.passengers_capacity, a.tank_capacity, a.cost
FROM airplanes AS a
JOIN flights AS f
ON a.id = f.airplane_id
JOIN countries AS c
ON f.departure_country = c.id
ORDER BY a.cost DESC, c.id DESC;


SELECT a.id, a.model, a.passengers_capacity, a.tank_capacity, a.cost
FROM airplanes AS a
ORDER BY a.cost DESC, a.id DESC;
