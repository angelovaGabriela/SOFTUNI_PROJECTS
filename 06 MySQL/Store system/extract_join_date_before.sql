USE softuni_stores_system;

# all products with description more than 100 characters long description
#  and a picture that is made before 2019 (exclusive) 
# the product price being more than 20.


SELECT p.name, p.price, p.best_before,
CONCAT(SUBSTRING(p.description, 1, 10), "...") AS short_description,
pic.url 
FROM products AS p 
JOIN  pictures AS pic
ON p.picture_id  = pic.id
WHERE CHAR_LENGTH(p.description) > 100
AND YEAR(pic.added_on) < 2019
AND p.price > 20
ORDER BY p.price DESC;



##name (product) /price  / best_before / url 
   



url  