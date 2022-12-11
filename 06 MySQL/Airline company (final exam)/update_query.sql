USE airlines_db;

UPDATE flights AS f
JOIN countries AS c
ON f.depatrure_country = c.id
SET airplane_id = airplane_id + 1
WHERE c.name = "Armenia";