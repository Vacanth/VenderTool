SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

CREATE SCHEMA IF NOT EXISTS `searchdb` DEFAULT CHARACTER SET utf8 ;
USE `searchdb` ;

-- -----------------------------------------------------
-- Table `searchdb`.`search`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `searchdb`.`search` (
  `search_id` BIGINT(20) NOT NULL ,
  `account_id` BIGINT(20) NOT NULL ,
  `sku` VARCHAR(64) NULL DEFAULT NULL ,
  `title` VARCHAR(256) NULL DEFAULT NULL ,
  `short_description` VARCHAR(1000) NULL DEFAULT NULL ,
  `marketplace_id` INT(11) NULL DEFAULT NULL ,
  `marketplace_item_id` VARCHAR(45) NULL DEFAULT NULL ,
  `usecase_type` INT(11) NOT NULL ,
  `usecase_ref_id` BIGINT(20) NULL DEFAULT NULL ,
  `email_addr` VARCHAR(128) NOT NULL ,
  `listing_start_date` DATETIME NULL DEFAULT NULL ,
  `listing_end_date` DATETIME NULL DEFAULT NULL ,
  `created_date` DATETIME NULL DEFAULT NULL ,
  `last_modified_date` DATETIME NULL DEFAULT NULL ,
  PRIMARY KEY (`search_id`) ,
  INDEX `accountid` (`account_id` ASC) ,
  INDEX `email` (`email_addr` ASC) )
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `searchdb`.`search_sequence`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `searchdb`.`search_sequence` (
  `sequence_id` INT(1) NOT NULL ,
  `increment` INT(11) UNSIGNED NOT NULL DEFAULT '1' ,
  `min_value` INT(11) UNSIGNED NOT NULL DEFAULT '1' ,
  `max_value` BIGINT(20) UNSIGNED NOT NULL DEFAULT '18446744073709551615' ,
  `cur_value` BIGINT(20) UNSIGNED NOT NULL DEFAULT '1' ,
  `is_recycle` TINYINT(4) NOT NULL DEFAULT '0' ,
  `created_date` DATETIME NULL DEFAULT NULL ,
  `last_modified_date` DATETIME NULL DEFAULT NULL ,
  PRIMARY KEY (`sequence_id`) )
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

USE `searchdb` ;

-- -----------------------------------------------------
-- function nextvalForSearch
-- -----------------------------------------------------

DELIMITER $$
USE `searchdb`$$
CREATE DEFINER=`root`@`localhost` FUNCTION `nextvalForSearch`() RETURNS bigint(20)
BEGIN
DECLARE cur_val bigint(20);
SELECT cur_value INTO cur_val FROM `searchdb`.`search_sequence` WHERE sequence_id = 1 FOR UPDATE;
UPDATE `searchdb`.`search_sequence` SET cur_value = cur_value + increment WHERE sequence_id = 1;
RETURN cur_val;
END$$

DELIMITER ;
USE `searchdb`;

DELIMITER $$
USE `searchdb`$$
CREATE
DEFINER=`root`@`localhost`
TRIGGER `searchdb`.`search_created`
BEFORE INSERT ON `searchdb`.`search`
FOR EACH ROW
BEGIN
SET new.created_date := now();
SET new.last_modified_date := now();
END$$

USE `searchdb`$$
CREATE
DEFINER=`root`@`localhost`
TRIGGER `searchdb`.`search_updated`
BEFORE UPDATE ON `searchdb`.`search`
FOR EACH ROW
BEGIN
SET new.last_modified_date := now();
END$$


DELIMITER ;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
