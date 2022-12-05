use stc;

# clients table, that do not have any courses 
# the count of the characters in the full_name is more than 3 characters. 
DELETE FROM clients 
WHERE id NOT IN (SELECT client_id FROM courses) 
AND CHAR_LENGTH(full_name) > 3;