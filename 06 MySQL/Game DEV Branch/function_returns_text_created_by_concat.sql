use sgd;

CREATE FUNCTION  udf_game_info_by_name (game_name VARCHAR (20))
RETURNS TEXT
RETURN
(SELECT 
CONCAT("The ", g.name, " is developed by a ", t.name, " in an office with an address ", a.name)
FROM games AS g
JOIN teams AS t
ON g.team_id = t.id
JOIN offices AS o
ON t.office_id = o.id
JOIN addresses AS a
ON o.address_id = a.id
WHERE g.name = game_name);

#The "game_name" is developed by a "team_name" in an office  
#with an address "address_text" 

#game_name 
#team_name
#adddress_name

SELECT udf_game_info_by_name('Bitwolf') AS info; 