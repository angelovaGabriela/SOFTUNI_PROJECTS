USE softuni_stores_system;

# stores (with or without products) 
# the count of the products that they have.
# average price of all products (rounded to the second digit after decimal point)

# descending by count of products in store, 
# then by average price in descending order 
# and finally by store id.  


# name (store) / product_count / avg

SELECT s.name,
COUNT(ps.product_id) AS product_count,
ROUND(AVG(p.price), 2) AS `avg`
FROM stores AS s
LEFT JOIN products_stores AS ps
ON s.id = ps.store_id
LEFT JOIN products AS p
ON ps.product_id = p.id
GROUP BY s.id
ORDER BY product_count DESC, `avg` DESC, s.id ASC;