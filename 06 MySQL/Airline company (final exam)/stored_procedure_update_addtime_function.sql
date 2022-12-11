USE airlines_db;

# change the delay status to true 
# the departure time by 30 minutes.

DELIMITER $$
CREATE PROCEDURE udp_delay_flight(code VARCHAR(50))
BEGIN
UPDATE flights 
SET has_delay = 1,
departure = addtime(departure, "00:30:00") 
WHERE flight_code = `code`;

END $$
DELIMITER ;

DROP procedure udp_delay_flight;
CALL udp_delay_flight('ZP-782');


