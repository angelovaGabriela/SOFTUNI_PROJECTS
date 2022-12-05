use stc;

#make 
#model 
#condition 

SELECT c.make, c.model, c.condition 
FROM cars AS c
ORDER BY id;