USE airlines_db;

# udf_count_flights_from_country(country VARCHAR(50)) 
#that receives a country name and 
# returns the total number of flights departed from that country.
CREATE FUNCTION udf_count_flights_from_country(country VARCHAR(50)) 
RETURNS INT
RETURN 
(SELECT COUNT(f.id) FROM countries AS c 
JOIN flights AS f
ON c.id = f.departure_country
WHERE c.name = country);

DROP FUNCTION udf_count_flights_from_country;


SELECT udf_count_flights_from_country('Brazil') AS 'flights_count';