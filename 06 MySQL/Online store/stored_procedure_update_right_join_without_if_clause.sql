USE online_store;

#Extracts data about the products from the given category 
#reduces the prices by 30% of all products which 
#have reviews with rating less than 4 and are from the given category. 


DELIMITER $$
CREATE PROCEDURE udp_reduce_price(category_name VARCHAR(50)) 
BEGIN

UPDATE products AS p
RIGHT JOIN reviews AS r
ON p.review_id = r.id
RIGHT JOIN categories AS c
ON p.category_id = c.id
SET p.price = p.price - (p.price * 0.3)
WHERE r.rating < 4 AND c.name = category_name;

END $$
DELIMITER ;



