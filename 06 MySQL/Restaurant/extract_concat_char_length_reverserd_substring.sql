use restaurant_db;


SELECT concat(last_name, first_name, char_length(first_name), "Restaurant") as username, 
reverse(substring(email, 2, 12)) as `password`
FROM waiters
WHERE salary > 0
ORDER BY `password` DESC;



