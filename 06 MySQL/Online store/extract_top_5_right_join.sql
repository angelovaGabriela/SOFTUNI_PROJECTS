use online_store;

SELECT COUNT(c.id) AS items_count, c.name, SUM(p.quantity_in_stock) AS total_quantity
FROM products AS p
RIGHT JOIN categories AS c
ON p.category_id = c.id
GROUP BY c.id
ORDER BY items_count DESC, total_quantity ASC LIMIT 5;