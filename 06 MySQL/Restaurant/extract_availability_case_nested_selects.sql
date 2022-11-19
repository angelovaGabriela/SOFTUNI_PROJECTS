

SELECT (SELECT table_id FROM orders WHERE table_id = tables.id LIMIT 1) as table_id, capacity, (SELECT COUNT(distinct client_id) FROM orders_clients WHERE order_id IN (SELECT id FROM orders WHERE table_id = tables.id)) as count_clients, 
CASE
WHEN capacity > (SELECT COUNT(client_id) FROM orders_clients WHERE order_id IN (SELECT id FROM orders WHERE table_id = tables.id)) THEN "Free seats"
WHEN capacity < (SELECT COUNT(client_id) FROM orders_clients WHERE order_id IN (SELECT id FROM orders WHERE table_id = tables.id)) THEN "Extra seats"
WHEN capacity = (SELECT COUNT(client_id) FROM orders_clients WHERE order_id IN (SELECT id FROM orders WHERE table_id = tables.id)) THEN "Full"
END as availability
FROM tables

WHERE tables.floor = 1 
GROUP BY table_id, count_clients
ORDER BY table_id  DESC;

