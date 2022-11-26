USE softuni_imdb;

SELECT c.name, COUNT(m.title) AS movies_count
FROM movies AS m
RIGHT JOIN countries AS c
ON m.country_id = c.id
GROUP BY c.name
HAVING COUNT(m.title) >= 7
ORDER BY c.name DESC;


