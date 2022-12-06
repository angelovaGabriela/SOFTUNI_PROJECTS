use stc;
#name (address) 

#day_time // courses.start  // 
# -  Day (between 6 and 20(inclusive both)) 
# - Night (between 21 and 5(inclusive both))
# - dateTime format // 2020-01-01 01:26:11 //

#bill 
#full_name (client) 
#make 
#model 
#category_name (category) 

## addresses / courses / clients / categories / cars
 
SELECT a.name, 
CASE
WHEN HOUR(c.start) BETWEEN 6 AND 20 THEN "Day"
WHEN HOUR(c.start) BETWEEN 21 AND 23 THEN "Night"
WHEN HOUR(c.start) BETWEEN 00 AND 5 THEN "Night"
END AS day_time,
c.bill,
ct.full_name,
cr.make,
cr.model,
cat.name AS category_name
FROM addresses AS a 
JOIN courses AS c 
ON a.id = c.from_address_id
JOIN clients AS ct
ON c.client_id = ct.id
JOIN cars AS cr
ON c.car_id = cr.id
JOIN categories AS cat
ON cr.category_id = cat.id
ORDER BY c.id;
