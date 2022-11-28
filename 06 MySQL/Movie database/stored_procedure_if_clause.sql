## if movie title is in ....

DELIMITER $$
CREATE PROCEDURE udp_award_movie(movie_title VARCHAR(50))
BEGIN
IF movie_title IN
(SELECT title AS movie_title FROM movies AS m
RIGHT JOIN movies_actors AS ma
ON m.id = ma.movie_id
RIGHT JOIN actors AS a
ON a.id = ma.actor_id)
THEN
UPDATE actors 
SET awards = awards + 1;
END IF;
END $$
DELIMITER ;
