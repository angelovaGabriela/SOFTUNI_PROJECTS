USE softuni_imdb;

SELECT `m`.`title`,
(CASE
WHEN `info`.`rating` <= 4 THEN "poor"
WHEN `info`.`rating` <= 7 THEN "good"
ELSE "excellent"
END) AS `rating`,
IF(`has_subtitles`, "english", "-") AS `subtitles`,
`info`.`budget`
FROM movies_additional_info AS `info`
RIGHT JOIN movies AS `m`
ON `info`.`id` = `m`.`movie_info_id`
ORDER BY `info`.`budget` DESC;