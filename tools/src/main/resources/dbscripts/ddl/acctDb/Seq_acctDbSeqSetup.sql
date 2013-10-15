SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

CREATE SCHEMA IF NOT EXISTS `acctdb` DEFAULT CHARACTER SET utf8 ;
USE `acctdb` 
CREATE  TABLE IF NOT EXISTS `acctdb`.`account_sequence` (
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




CREATE  TABLE IF NOT EXISTS `acctdb`.`address_sequence` (
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





USE `acctdb` ;

insert into `acctdb`.`account_sequence`  values (1,1,1,18446744073709551615,1,0,sysdate(),sysdate());
insert into `acctdb`.`address_sequence`  values (1,1,1,18446744073709551615,1,0,sysdate(),sysdate());
insert into `acctdb`.`address_sequence`  values (1,1,1,18446744073709551615,1,0,sysdate(),sysdate());
commit;



SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

CREATE SCHEMA IF NOT EXISTS `acctdb` DEFAULT CHARACTER SET utf8 ;
USE `acctdb` 




DELIMITER $$
DROP FUNCTION IF EXISTS `acctdb`.`nextvalForAccount` $$
CREATE FUNCTION `acctdb`.`nextvalForAccount` () RETURNS bigint(20) NOT DETERMINISTIC
BEGIN
DECLARE cur_val bigint(20);
SELECT cur_value INTO cur_val FROM `acctdb`.`account_sequence` WHERE sequence_id = 1 FOR UPDATE;
UPDATE `acctdb`.`account_sequence` SET cur_value = cur_value + increment WHERE sequence_id = 1;
RETURN cur_val;
END $$
DELIMITER ;



DELIMITER $$
DROP FUNCTION IF EXISTS `acctdb`.`nextvalForAddress` $$
CREATE FUNCTION `acctdb`.`nextvalForAddress` () RETURNS bigint(20) NOT DETERMINISTIC
BEGIN
DECLARE cur_val bigint(20);
SELECT cur_value INTO cur_val FROM `acctdb`.`listing_sequence` WHERE sequence_id = 1 FOR UPDATE;
UPDATE `acctdb`.`listing_sequence` SET cur_value = cur_value + increment WHERE sequence_id = 1;
RETURN cur_val;
END $$
DELIMITER ;

USE `acctdb` ;

