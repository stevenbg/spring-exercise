CREATE TABLE `burger_ingredient` (
	`burger_id` INT UNSIGNED NOT NULL,
	`ingredient_id` INT UNSIGNED NOT NULL,
	PRIMARY KEY (`burger_id`, `ingredient_id`) USING BTREE,
	INDEX `FK_ingredient` (`ingredient_id`) USING BTREE,
	CONSTRAINT `FK_burger_id` FOREIGN KEY (`burger_id`) REFERENCES `myrest`.`burger` (`id`) ON UPDATE NO ACTION ON DELETE NO ACTION,
	CONSTRAINT `FK_ingredient_id` FOREIGN KEY (`ingredient_id`) REFERENCES `myrest`.`ingredient` (`id`) ON UPDATE NO ACTION ON DELETE NO ACTION
)
COLLATE='utf8mb4_0900_ai_ci'
ENGINE=InnoDB
;
