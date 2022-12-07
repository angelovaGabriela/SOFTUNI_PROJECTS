use sgd;
 # name (categories)
 
 # games_count (from each category)

 # avg_budget (rounded to the second digit after the decimal point) (from the category)

 # max_rating (best of all games in this category)
 #Skip categories with max rating lower than 9.5(exclusive). 
 
 # Order the result by count of games in descending order, 
 #then by the name of the category alphabetically.  
 
 SELECT c.name,
 COUNT(gc.game_id) AS games_count,
 ROUND(AVG(g.budget), 2) AS avg_budget,
 MAX(g.rating) AS  max_rating
 FROM categories AS c
 JOIN games_categories AS gc
 ON c.id = gc.category_id
 JOIN games AS g 
 ON gc.game_id = g.id
 GROUP BY c.name
 HAVING MAX(g.rating) >= 9.5
 ORDER BY COUNT(gc.game_id) DESC, c.name ASC;