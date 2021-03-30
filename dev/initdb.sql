CREATE DATABASE myrest;
CREATE USER 'myrest'@'%' IDENTIFIED BY 'myrest';
GRANT ALL ON myrest.* TO 'myrest'@'%';

--CREATE USER 'myrestadm'@'%' IDENTIFIED BY 'myrestadm';
--GRANT ALL ON myrest TO 'myrestadm'@'%';

USE myrest;
CREATE TABLE `burger` (
	`id` INT(10) UNSIGNED NOT NULL AUTO_INCREMENT,
	`name` VARCHAR(255) NULL DEFAULT NULL COLLATE 'utf8mb4_0900_ai_ci',
	PRIMARY KEY (`id`) USING BTREE
)
COLLATE='utf8mb4_0900_ai_ci'
ENGINE=InnoDB
;

INSERT INTO `burger` (`id`, `name`) VALUES (1, 'Big Mac');
INSERT INTO `burger` (`id`, `name`) VALUES (2, 'Whopper');
INSERT INTO `burger` (`id`, `name`) VALUES (3, 'Cheesus');
