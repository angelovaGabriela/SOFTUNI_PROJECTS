use softuni_imdb;


DELETE FROM `countries`
WHERE `id` 
NOT IN (SELECT `country_id` FROM `movies` WHERE `country_id` > 0);