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
