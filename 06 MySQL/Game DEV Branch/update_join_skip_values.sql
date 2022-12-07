use sgd;

UPDATE employees AS e
JOIN teams AS t
ON e.id = t.leader_id
SET e.salary = e.salary + 1000
WHERE e.age < 39 AND e.salary < 5000;
