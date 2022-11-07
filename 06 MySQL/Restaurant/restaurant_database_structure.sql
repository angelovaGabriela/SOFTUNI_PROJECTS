CREATE DATABASE restaurant_db;
USE restaurant_db;

CREATE TABLE products (
id INT PRIMARY KEY AUTO_INCREMENT,
`name` VARCHAR(30) NOT NULL UNIQUE,
`type` VARCHAR(30) NOT NULL,
`price` DECIMAL(10,2) NOT NULL
);



CREATE TABLE clients (
id INT PRIMARY KEY AUTO_INCREMENT,
`first_name` VARCHAR(50) NOT NULL,
`last_name` VARCHAR(50) NOT NULL,
`birthdate` DATE NOT NULL,
`card` VARCHAR(50),
`review` TEXT
);


CREATE TABLE `tables` (
id INT PRIMARY KEY AUTO_INCREMENT,
`floor` INT NOT NULL,
 `reserved` BOOLEAN,
 `capacity` INT NOT NULL
 );
 
 CREATE TABLE waitres (
 id INT PRIMARY KEY AUTO_INCREMENT,
 `first_name` VARCHAR(50) NOT NULL,
 `last_name` VARCHAR(50) NOT NULL,
 `email` VARCHAR(50) NOT NULL,
 `phone` VARCHAR(50),
 `salary` DECIMAL(10,2)
 );
 
 CREATE TABLE orders (
 id INT PRIMARY KEY AUTO_INCREMENT,
 `table_id` INT NOT NULL,
 `waiter_id` INT NOT NULL,
 `order_time` TIME NOT NULL,
 `payed_status` BOOLEAN,
 
CONSTRAINT `fk_table_id`
FOREIGN KEY (`table_id`)
REFERENCES `tables`(`id`),

CONSTRAINT `fk_waiter_id`
FOREIGN KEY (`waiter_id`)
REFERENCES `waitres`(`id`)
);

CREATE TABLE `orders_clients` (
`order_id` INT NOT NULL,
`client_id` INT NOT NULL,

CONSTRAINT fk_orders_clients
FOREIGN KEY (order_id)
REFERENCES `orders`(id),

CONSTRAINT fk_clients_orders
FOREIGN KEY (client_id)
REFERENCES `clients`(id)
);


CREATE TABLE `orders_products` (
`order_id` INT NOT NULL,
`product_id` INT NOT NULL,

CONSTRAINT fk_orders_products
FOREIGN KEY (order_id)
REFERENCES `orders`(id),

CONSTRAINT fk_products_orders
FOREIGN KEY (product_id)
REFERENCES `products`(id)
);




