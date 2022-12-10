USE softuni_stores_system;

INSERT INTO products_stores (product_id, store_id)

SELECT id AS product_id, 1 FROM products 
WHERE id NOT IN 
(SELECT product_id FROM products_stores);