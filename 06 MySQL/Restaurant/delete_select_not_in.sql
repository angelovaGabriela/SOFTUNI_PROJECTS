use restaurant_db;
DELETE FROM waiters
WHERE id NOT IN (SELECT waiter_id FROM orders WHERE waiter_id > 0);

