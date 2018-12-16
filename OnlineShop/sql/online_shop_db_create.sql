DROP
	DATABASE IF EXISTS online_shop_db;

CREATE 
	DATABASE IF NOT EXISTS online_shop_db;

USE
	online_shop_db;

DROP
	TABLE IF EXISTS users;

CREATE
	TABLE users (
		login VARCHAR(20) NOT NULL PRIMARY KEY UNIQUE,
        email VARCHAR(45) NOT NULL UNIQUE,
        first_name VARCHAR(20) NOT NULL,
        last_name VARCHAR(20) NOT NULL,
        password VARCHAR(20) NOT NULL UNIQUE
	);
    
DROP
	TABLE IF EXISTS products;
    
CREATE
	TABLE products (
		id_product INT(11) UNSIGNED NOT NULL PRIMARY KEY UNIQUE AUTO_INCREMENT,
		name VARCHAR(30) NOT NULL,
        category VARCHAR(40) NOT NULL,
        brand VARCHAR(40) NOT NULL,
        price DOUBLE UNSIGNED NOT NULL
	);

INSERT
	INTO users VALUES
		('User0', 'user0@gmail.com', 'Egor', 'Tyurin', '0'),
        ('User1', 'user1@gmail.com', 'Ivan', 'Ivanov', '1');

INSERT
	INTO products VALUES
		(0, 'Vans Chilli Pepper Red', 'Shoes', 'Vans', 100.0),
        (0, 'Nike Classic Cortez', 'Shoes', 'Nike', 120.0),
        (0, 'White Blank T', 'T-Shirt', 'Reebok', 50.0);
