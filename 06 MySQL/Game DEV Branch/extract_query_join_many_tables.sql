use sgd;

 #name (of the game) 
 # budget_level 
 # team_name 
 # address_name  
 
 # budget is less than 50000 - 'Normal budget'. 
 # else - 'Insufficient budget'
 
 SELECT g.name, 
 CASE
 WHEN g.budget < 50000 THEN "Normal budget"
 ELSE "Insufficient budget"
 END AS budget_level,
 t.name AS team_name, a.name AS address_name
 FROM games AS g
 JOIN teams AS t
 ON g.team_id = t.id
 JOIN offices AS o
 ON t.office_id = o.id
 JOIN addresses AS a
 ON o.address_id = a.id
 WHERE g.id NOT IN (SELECT game_id FROM games_categories)
 AND g.release_date IS NULL
 ORDER BY g.name;