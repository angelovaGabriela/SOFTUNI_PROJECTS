use stc;

#clients who have ridden more than one car
#second latter of client's FULL_NAME -> 'a'
#full_name / count_of_cars / total_sum  of all courses


SELECT c.full_name,
COUNT(cou.car_id) AS count_of_cars,
SUM(cou.bill) AS total_sum
FROM clients AS c
RIGHT JOIN courses  AS cou
ON c.id = cou.client_id
GROUP BY c.full_name
HAVING COUNT(cou.car_id) > 1 
AND SUBSTRING(c.full_name, 2, 1) = 'a'
ORDER BY c.full_name;