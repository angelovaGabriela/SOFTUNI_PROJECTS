use stc;

# udf_courses_by_client (phone_num VARCHAR (20)) 

#receives client phone number and returns the number of courses


CREATE FUNCTION udf_courses_by_client (phone_num VARCHAR (20)) 
RETURNS INT
RETURN
(SELECT COUNT(co.id) FROM clients AS cl
JOIN courses AS co
ON cl.id = co.client_id
WHERE cl.phone_number = phone_num);

SELECT udf_courses_by_client ('(803) 6386812') as `count`;  
SELECT udf_courses_by_client ('(831) 1391236') as `count`; 
SELECT udf_courses_by_client ('(704) 2502909') as `count`; 