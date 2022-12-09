use sgd;

# increase budged by 100,000 
# add one year to the release_date

# to games that don't have category
# rating is more than NOT EQUAL to the given one
# relese date is NOT NULL

DELIMITER $$
CREATE PROCEDURE udp_update_budget (min_game_rating FLOAT)  
BEGIN
UPDATE games AS g
LEFT JOIN games_categories AS gc
ON g.id = gc.game_id
SET g.budget = g.budget + 100.000,
g.release_date = DATE_ADD(release_date, INTERVAL 1 YEAR)
WHERE gc.game_id IS NULL 
AND g.rating BETWEEN  min_game_rating AND g.rating
AND g.release_date IS NOT NULL;
END $$
DELIMITER $$ ;