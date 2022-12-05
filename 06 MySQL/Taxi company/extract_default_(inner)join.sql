use stc;

# Extract the driver’s first and last name from the drivers table 
# and the make, the model and the mileage from the cars table.
# Order the result by the mileage in descending order, 
# then by the first name alphabetically.  
# Skip all cars that have NULL as a value for the mileage. 

SELECT d.first_name, d.last_name, c.make, c.model, c.mileage
FROM drivers AS d
JOIN cars_drivers AS cd
ON d.id = cd.driver_id
JOIN cars AS c
ON cd.car_id = c.id
WHERE c.mileage IS NOT NULL
GROUP BY d.first_name, d.last_name, c.make, c.model, c.mileage
ORDER BY c.mileage DESC, d.first_name ASC;


