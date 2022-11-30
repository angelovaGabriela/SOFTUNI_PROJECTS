use online_store;

SELECT CONCAT(first_name, ' ', last_name) AS full_name, address, order_datetime
FROM customers AS c
RIGHT JOIN orders AS o
ON c.id = o.customer_id
WHERE YEAR(order_datetime) <= 2018
ORDER BY full_name DESC;