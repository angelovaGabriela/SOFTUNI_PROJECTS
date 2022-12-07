use sgd;

# team names and their addresses. 
# Also display the count of the characters of the address names. 

#Skip those teams whose office does not have a website.  

# Order the results by team names, then by the address names.  

#team_name 

#address_name 

#count_of_characters(of the address name) 

SELECT t.name AS team_name, 
a.name AS address_name,  
CHAR_LENGTH(a.name) AS  count_of_characters
FROM teams AS t
JOIN offices AS o
ON t.office_id = o.id
JOIN addresses AS a
ON o.address_id = a.id
WHERE o.website is not NULL
GROUP BY t.name, a.name
ORDER BY t.name, a.name;