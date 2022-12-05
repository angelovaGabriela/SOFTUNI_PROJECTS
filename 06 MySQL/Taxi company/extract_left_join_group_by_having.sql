use stc;

# Extract from the database all the cars and the count of their courses. 
# Also display the average bill of each course by the car, rounded to the second digit. 

# Order the results descending by the count of courses, then by the car’s id.  

# Skip the cars with exactly 2 courses. 

# car_id / make / mileage /count_of_courses / avg_bill 

SELECT c.id,
c.make,
c.mileage, 
COUNT(co.car_id) AS count_of_courses,
ROUND(AVG(co.bill), 2) AS avg_bill
FROM cars AS c
LEFT JOIN courses AS co
ON c.id = co.car_id 
GROUP BY c.id, c.make, c.mileage
HAVING COUNT(co.car_id) > 2 OR COUNT(co.car_id) < 2
ORDER BY COUNT(co.car_id) DESC, c.id;


