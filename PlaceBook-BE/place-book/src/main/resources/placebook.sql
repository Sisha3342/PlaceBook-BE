CREATE DATABASE placebook;
USE placebook;
CREATE TABLE `user` (
	`id` INT NOT NULL AUTO_INCREMENT UNIQUE,
	`email` varchar(255) NOT NULL,
	`password` varchar(255) NOT NULL,
	`name` varchar(255) NOT NULL,
	`surname` varchar(255) NOT NULL,
	`role_id` INT NOT NULL,
	`id_hr` INT(255) NOT NULL,
	`photo_url` varchar(255) NOT NULL,
	PRIMARY KEY (`id`)
);

CREATE TABLE `place` (
	`id` INT NOT NULL AUTO_INCREMENT,
	`id_office` INT NOT NULL,
	`floor` INT NOT NULL,
	PRIMARY KEY (`id`)
);

CREATE TABLE `country` (
	`id` INT NOT NULL AUTO_INCREMENT,
	`country` varchar(255) NOT NULL UNIQUE,
	PRIMARY KEY (`id`)
);

CREATE TABLE `сity` (
	`id` INT NOT NULL AUTO_INCREMENT,
	`city` varchar(255) NOT NULL UNIQUE,
	PRIMARY KEY (`id`)
);

CREATE TABLE `address` (
	`id` INT NOT NULL AUTO_INCREMENT,
	`address` varchar(255) NOT NULL,
	PRIMARY KEY (`id`)
);

CREATE TABLE `booking` (
	`id` INT NOT NULL AUTO_INCREMENT,
	`id_user` INT NOT NULL,
	`id_place` INT NOT NULL,
	`time_start` DATETIME NOT NULL,
	`time_send` DATETIME NOT NULL,
	`status` BOOLEAN NOT NULL,
	PRIMARY KEY (`id`)
);

CREATE TABLE `roles` (
	`id` INT NOT NULL AUTO_INCREMENT,
	`role_name` varchar(255) NOT NULL UNIQUE,
	PRIMARY KEY (`id`)
);

CREATE TABLE `office` (
	`id` INT NOT NULL AUTO_INCREMENT,
	`id_country` INT NOT NULL,
	`id_city` INT NOT NULL,
	`id_addres` INT NOT NULL,
	`worktime_start` INT NOT NULL,
	`worktime_end` INT NOT NULL,
	`floor_number` INT NOT NULL,
	PRIMARY KEY (`id`)
);

CREATE TABLE `place_rate` (
	`id` INT NOT NULL AUTO_INCREMENT,
	`id_booking` INT NOT NULL,
	`mark_lightning` INT NOT NULL,
	`mark_air` INT NOT NULL,
	`mark_volume` INT NOT NULL,
	`mark_cleaning` INT NOT NULL,
	`mark_location` INT NOT NULL,
	`feedback` varchar(255) NOT NULL,
	PRIMARY KEY (`id`)
);

CREATE TABLE `subscribe_to_place` (
	`id` INT NOT NULL AUTO_INCREMENT,
	`id_place` INT NOT NULL,
	`id_user` INT NOT NULL,
	PRIMARY KEY (`id`)
);

CREATE TABLE `floors` (
	`id` INT NOT NULL AUTO_INCREMENT,
	`office_id` INT NOT NULL,
	`floor_configuration` varchar(255) NOT NULL,
	PRIMARY KEY (`id`)
);

ALTER TABLE `user` ADD CONSTRAINT `user_fk0` FOREIGN KEY (`role_id`) REFERENCES `roles`(`id`);

ALTER TABLE `place` ADD CONSTRAINT `place_fk0` FOREIGN KEY (`id_office`) REFERENCES `office`(`id`);

ALTER TABLE `booking` ADD CONSTRAINT `booking_fk0` FOREIGN KEY (`id_user`) REFERENCES `user`(`id`);

ALTER TABLE `booking` ADD CONSTRAINT `booking_fk1` FOREIGN KEY (`id_place`) REFERENCES `place`(`id`);

ALTER TABLE `office` ADD CONSTRAINT `office_fk0` FOREIGN KEY (`id_country`) REFERENCES `country`(`id`);

ALTER TABLE `office` ADD CONSTRAINT `office_fk1` FOREIGN KEY (`id_city`) REFERENCES `сity`(`id`);

ALTER TABLE `office` ADD CONSTRAINT `office_fk2` FOREIGN KEY (`id_addres`) REFERENCES `address`(`id`);

ALTER TABLE `place_rate` ADD CONSTRAINT `place_rate_fk0` FOREIGN KEY (`id_booking`) REFERENCES `booking`(`id`);

ALTER TABLE `subscribe_to_place` ADD CONSTRAINT `subscribe_to_place_fk0` FOREIGN KEY (`id_place`) REFERENCES `place`(`id`);

ALTER TABLE `subscribe_to_place` ADD CONSTRAINT `subscribe_to_place_fk1` FOREIGN KEY (`id_user`) REFERENCES `user`(`id`);

ALTER TABLE `floors` ADD CONSTRAINT `floors_fk0` FOREIGN KEY (`office_id`) REFERENCES `office`(`id`);
