USE softuni_stores_system;

DELETE FROM employees 
WHERE manager_id IS NOT NULL
AND salary > 6000;