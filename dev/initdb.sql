CREATE DATABASE myrest;
CREATE USER 'myrest'@'%' IDENTIFIED BY 'myrest';
GRANT ALL ON myrest.* TO 'myrest'@'%';

CREATE USER 'root'@'%' IDENTIFIED WITH mysql_native_password BY 'root';
GRANT ALL ON *.* TO 'root'@'%';

USE myrest;
CREATE TABLE `burger` (
	`id` INT(10) UNSIGNED NOT NULL AUTO_INCREMENT,
	`name` VARCHAR(255) NULL DEFAULT NULL COLLATE 'utf8mb4_0900_ai_ci',
	PRIMARY KEY (`id`) USING BTREE
)
COLLATE='utf8mb4_0900_ai_ci'
ENGINE=InnoDB
;

