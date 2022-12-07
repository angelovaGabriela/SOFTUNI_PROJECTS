use sgd;

# Delete all games from table games,
# which do not have a category and release date.  
 DELETE FROM games
 WHERE release_date IS NULL 
 AND id NOT IN (SELECT game_id FROM games_categories);