use sgd;

# increase budged by 100,000 
# add one year to the release_date

# to games that don't have category
# rating is more than NOT EQUAL to the given one
# relese date is NOT NULL
DELIMITER $$
CREATE PROCEDURE udp_update_budget (min_game_rating FLOAT)  
BEGIN
UPDATE games
SET budget = budget + 10 
AND YEAR(release_date)  = YEAR(release_date) + 1
WHERE id NOT IN (SELECT game_id FROM games_categories WHERE game_id IS NOT NULL) 
AND rating > min_game_rating  
AND release_date IS NOT NULL;
END $$
DELIMITER $$ ;
CALL udp_update_budget (8);

SELECT SUM(`budget`) FROM  `games`;
