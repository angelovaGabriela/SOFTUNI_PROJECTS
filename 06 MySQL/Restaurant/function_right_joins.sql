
set global log_bin_trust_function_creators = 1;
CREATE FUNCTION udf_client_bill(full_name VARCHAR(50))
RETURNS DECIMAL(19,2)
RETURN
(SELECT SUM(price) AS bill 
 FROM products AS p
 RIGHT JOIN orders_products AS op
 ON p.id = product_id
 RIGHT JOIN orders_clients AS oc
 ON oc.order_id = op.order_id
 RIGHT JOIN clients AS c
 ON c.id = oc.client_id
 WHERE concat(c.first_name,' ', c.last_name) = full_name);
 

