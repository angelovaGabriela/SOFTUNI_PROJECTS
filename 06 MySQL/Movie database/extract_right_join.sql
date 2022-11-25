USE softuni_imdb;

SELECT `info`.`id`,`m`.`title`, `info`.`runtime`, `info`.`budget`, `info`.`release_date`
FROM `movies` AS `m`
RIGHT JOIN `movies_additional_info` AS `info`
ON `m`.`movie_info_id` = `info`.`id`
WHERE `release_date` BETWEEN '1996-01-01' AND '1999-12-31'
ORDER BY `runtime` ASC, `id` ASC
LIMIT 20;






SELECT * FROM `movies_additional_info`;