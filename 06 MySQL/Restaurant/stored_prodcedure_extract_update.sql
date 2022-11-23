use restaurant_db;

DELIMITER $$
CREATE PROCEDURE udp_happy_hour(type VARCHAR(50))
BEGIN
IF type IN (SELECT p.type 
FROM products as p WHERE p.type = type)
THEN UPDATE products as p 
SET p.price = p.price - (p.price * 0.2)
WHERE p.price >= 10.00 AND type = type; 
END IF;
  END $$
  DELIMITER ;
  
  DROP PROCEDURE udp_happy_hour;
  

  CALL udp_happy_hour('Cognac');
  
  SELECT * FROM products 
  WHERE price >= 10.00 AND type = 'Cognac';
  
  

#Extracts data about the products from the given type
#and reduces the prices by 20% 
#of all products which have price higher or equal to 10.00 
#and are from the given type. 
