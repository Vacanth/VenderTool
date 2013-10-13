SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

CREATE SCHEMA IF NOT EXISTS `invdb` DEFAULT CHARACTER SET utf8 ;
USE `invdb` ;

CREATE  TABLE IF NOT EXISTS `invdb`.`product_sequence` (
  `sequence_id` INT(1) NOT NULL ,
  `increment` INT(11) UNSIGNED NOT NULL DEFAULT 1 ,
  `min_value` INT(11) UNSIGNED NOT NULL DEFAULT 1 ,
  `max_value` BIGINT UNSIGNED NOT NULL DEFAULT 18446744073709551615 ,
  `cur_value` BIGINT UNSIGNED NOT NULL DEFAULT 1 ,
  `is_recycle` TINYINT NOT NULL DEFAULT 0 ,
  `created_date` DATETIME NULL ,
  `last_modified_date` DATETIME NULL ,
  PRIMARY KEY (`sequence_id`) )
ENGINE = InnoDB;



CREATE  TABLE IF NOT EXISTS `invdb`.`product_variation_sequence` (
  `sequence_id` INT(1) NOT NULL ,
  `increment` INT(11) UNSIGNED NOT NULL DEFAULT 1 ,
  `min_value` INT(11) UNSIGNED NOT NULL DEFAULT 1 ,
  `max_value` BIGINT UNSIGNED NOT NULL DEFAULT 18446744073709551615 ,
  `cur_value` BIGINT UNSIGNED NOT NULL DEFAULT 1 ,
  `is_recycle` TINYINT NOT NULL DEFAULT 0 ,
  `created_date` DATETIME NULL ,
  `last_modified_date` DATETIME NULL ,
  PRIMARY KEY (`sequence_id`) )
ENGINE = InnoDB;


CREATE  TABLE IF NOT EXISTS `invdb`.`listing_sequence` (
  `sequence_id` INT(1) NOT NULL ,
  `increment` INT(11) UNSIGNED NOT NULL DEFAULT 1 ,
  `min_value` INT(11) UNSIGNED NOT NULL DEFAULT 1 ,
  `max_value` BIGINT UNSIGNED NOT NULL DEFAULT 18446744073709551615 ,
  `cur_value` BIGINT UNSIGNED NOT NULL DEFAULT 1 ,
  `is_recycle` TINYINT NOT NULL DEFAULT 0 ,
  `created_date` DATETIME NULL ,
  `last_modified_date` DATETIME NULL ,
  PRIMARY KEY (`sequence_id`) )
ENGINE = InnoDB;


CREATE  TABLE IF NOT EXISTS `invdb`.`listing_variation_sequence` (
  `sequence_id` INT(1) NOT NULL ,
  `increment` INT(11) UNSIGNED NOT NULL DEFAULT 1 ,
  `min_value` INT(11) UNSIGNED NOT NULL DEFAULT 1 ,
  `max_value` BIGINT UNSIGNED NOT NULL DEFAULT 18446744073709551615 ,
  `cur_value` BIGINT UNSIGNED NOT NULL DEFAULT 1 ,
  `is_recycle` TINYINT NOT NULL DEFAULT 0 ,
  `created_date` DATETIME NULL ,
  `last_modified_date` DATETIME NULL ,
  PRIMARY KEY (`sequence_id`) )
ENGINE = InnoDB;


USE `invdb` ;

insert into `invdb`.`product_sequence`  values (1,1,1,18446744073709551615,1,0,sysdate(),sysdate());
insert into `invdb`.`product_variation_sequence`  values (1,1,1,18446744073709551615,1,0,sysdate(),sysdate());
insert into `invdb`.`listing_sequence`  values (1,1,1,18446744073709551615,1,0,sysdate(),sysdate());
insert into `invdb`.`listing_variation_sequence`  values (1,1,1,18446744073709551615,1,0,sysdate(),sysdate());

commit;

DELIMITER $$
DROP FUNCTION IF EXISTS `invdb`.`nextvalForProduct` $$
CREATE FUNCTION `invdb`.`nextvalForProduct` () RETURNS bigint(20) NOT DETERMINISTIC
BEGIN
DECLARE cur_val bigint(20);
SELECT cur_value INTO cur_val FROM `invdb`.`product_sequence` WHERE sequence_id = 1 FOR UPDATE;
UPDATE `invdb`.`product_sequence` SET cur_value = cur_value + increment WHERE sequence_id = 1;
RETURN cur_val;
END $$
DELIMITER ;



DELIMITER $$
DROP FUNCTION IF EXISTS `invdb`.`nextvalForProductVariation` $$
CREATE FUNCTION `invdb`.`nextvalForProductVariation` () RETURNS bigint(20) NOT DETERMINISTIC
BEGIN
DECLARE cur_val bigint(20);
SELECT cur_value INTO cur_val FROM `invdb`.`product_variation_sequence` WHERE sequence_id = 1 FOR UPDATE;
UPDATE `invdb`.`product_variation_sequence` SET cur_value = cur_value + increment WHERE sequence_id = 1;
RETURN cur_val;
END $$
DELIMITER ;


DELIMITER $$
DROP FUNCTION IF EXISTS `invdb`.`nextvalForListing` $$
CREATE FUNCTION `invdb`.`nextvalForListing` () RETURNS bigint(20) NOT DETERMINISTIC
BEGIN
DECLARE cur_val bigint(20);
SELECT cur_value INTO cur_val FROM `invdb`.`listing_sequence` WHERE sequence_id = 1 FOR UPDATE;
UPDATE `invdb`.`listing_sequence` SET cur_value = cur_value + increment WHERE sequence_id = 1;
RETURN cur_val;
END $$
DELIMITER ;




DELIMITER $$
DROP FUNCTION IF EXISTS `invdb`.`nextvalForListingVariation` $$
CREATE FUNCTION `invdb`.`nextvalForListingVariation` () RETURNS bigint(20) NOT DETERMINISTIC
BEGIN
DECLARE cur_val bigint(20);
SELECT cur_value INTO cur_val FROM `invdb`.`listing_variation_sequence` WHERE sequence_id = 1 FOR UPDATE;
UPDATE `invdb`.`listing_variation_sequence` SET cur_value = cur_value + increment WHERE sequence_id = 1;
RETURN cur_val;
END $$
DELIMITER ;
