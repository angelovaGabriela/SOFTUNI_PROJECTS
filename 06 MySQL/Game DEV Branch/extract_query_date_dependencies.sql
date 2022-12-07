use sgd;


SELECT g.name,
g.release_date,
CONCAT(SUBSTRING(g.description, 1, 10), '...') AS summary,
CASE
 WHEN MONTH(g.release_date) BETWEEN 1 AND 3 THEN "Q1"
 WHEN MONTH(g.release_date) BETWEEN 4 AND 5  THEN "Q2"
 WHEN MONTH(g.release_date) BETWEEN 6 and 8  THEN "Q3"
 WHEN MONTH(g.release_date) BETWEEN 9 AND 12  THEN "Q4"
END AS `quarter`,
t.name AS team_name
FROM games AS g 
JOIN teams AS t
ON g.team_id = t.id
WHERE RIGHT(g.name , 1) IN ('2') AND MONTH(g.release_date) % 2 = 0
GROUP BY g.name, g.release_date
HAVING YEAR(release_date) = 2022
ORDER BY `quarter`;

