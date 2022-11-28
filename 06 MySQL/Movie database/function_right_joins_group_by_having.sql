USE softuni_imdb;
CREATE FUNCTION udf_actor_history_movies_count(full_name VARCHAR(50))
RETURNS INT
RETURN
(SELECT COUNT(genre_id) FROM genres_movies AS gm
RIGHT JOIN movies AS m
ON gm.movie_id = m.id
RIGHT JOIN movies_actors AS ma
ON m.id = ma.movie_id
RIGHT JOIN actors AS a
ON a.id = ma.actor_id
WHERE concat(a.first_name,' ', a.last_name) = "Jared Di Batista"
GROUP BY genre_id
HAVING genre_id IN (SELECT id FROM genres WHERE name = "history"));










DROP FUNCTION  udf_actor_history_movies_count;

SELECT udf_actor_history_movies_count('Stephan Lundberg')  AS 'history_movies'; 


SELECT udf_actor_history_movies_count('Jared Di Batista')  AS 'history_movies'; 




