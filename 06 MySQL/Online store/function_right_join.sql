use online_store;

CREATE FUNCTION udf_customer_products_count(name VARCHAR(30))
RETURNS INT 
RETURN
(SELECT COUNT(op.product_id) AS total_products
FROM orders_products AS op
RIGHT JOIN orders AS o
ON op.order_id = o.id
RIGHT JOIN customers AS c
ON o.customer_id = c.id
WHERE c.first_name = name);

SELECT COUNT(op.product_id) AS total_products
FROM orders_products AS op
RIGHT JOIN orders AS o
ON op.order_id = o.id
RIGHT JOIN customers AS c
ON o.customer_id = c.id
WHERE c.first_name = 'Shirley';