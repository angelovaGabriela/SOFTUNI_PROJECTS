use restaurant_db;

SELECT products.id, products.name,  
(SELECT COUNT(orders_products.product_id) 
FROM orders_products 
WHERE orders_products.product_id = products.id) as product_count
FROM products
WHERE (SELECT COUNT(orders_products.product_id) 
FROM orders_products 
WHERE orders_products.product_id = products.id) >= 5
GROUP BY products.id
ORDER BY product_count DESC, name ASC;

