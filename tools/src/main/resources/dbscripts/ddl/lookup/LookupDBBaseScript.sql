SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

CREATE SCHEMA IF NOT EXISTS `lookupdb` DEFAULT CHARACTER SET utf8 ;
USE `lookupdb` ;

-- -----------------------------------------------------
-- Table `lookupdb`.`mercado_category_group`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `lookupdb`.`mercado_category_group` (
  `mercado_category_group_id` INT NOT NULL ,
  `group_date` DATETIME NULL ,
  `site_id` INT NULL ,
  `created_date` DATETIME NULL ,
  `last_modified_date` DATETIME NULL ,
  PRIMARY KEY (`mercado_category_group_id`) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `lookupdb`.`mercado_category`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `lookupdb`.`mercado_category` (
  `mercado_category_id` BIGINT NOT NULL ,
  `category_id` VARCHAR(64) NOT NULL ,
  `parent_category_id` VARCHAR(64) NULL ,
  `status` INT NULL ,
  `attributes` BLOB NULL ,
  `group_id` INT NULL ,
  `created_date` DATETIME NULL ,
  `last_modified_date` DATETIME NULL ,
  PRIMARY KEY (`mercado_category_id`) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `lookupdb`.`state`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `lookupdb`.`state` (
  `state_id` BIGINT NOT NULL ,
  `state_code` VARCHAR(45) NOT NULL ,
  `display_name` VARCHAR(256) NULL ,
  `iso3_country_code` VARCHAR(6) NULL ,
  `created_date` DATETIME NULL ,
  `last_modified_date` DATETIME NULL ,
  PRIMARY KEY (`state_id`) ,
  INDEX `country` (`iso3_country_code` ASC) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `lookupdb`.`city`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `lookupdb`.`city` (
  `city_id` BIGINT NOT NULL ,
  `city_name` VARCHAR(256) NULL ,
  `state` BIGINT NULL ,
  `iso3_country_code` VARCHAR(6) NULL ,
  `created_date` DATETIME NULL ,
  `last_modified_date` DATETIME NULL ,
  PRIMARY KEY (`city_id`) ,
  INDEX `country` (`iso3_country_code` ASC) ,
  INDEX `state` (`state` ASC) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `lookupdb`.`postal_code`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `lookupdb`.`postal_code` (
  `postal_code_id` INT NOT NULL ,
  `postal_code` VARCHAR(45) NULL ,
  `iso3_country_code` VARCHAR(6) NULL ,
  `state` BIGINT NULL ,
  `city` BIGINT NULL ,
  `created_date` DATETIME NULL ,
  `last_modified_date` DATETIME NULL ,
  PRIMARY KEY (`postal_code_id`) ,
  INDEX `country` (`iso3_country_code` ASC) ,
  INDEX `state` (`state` ASC) ,
  INDEX `city` (`city` ASC) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `lookupdb`.`city_sequence`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `lookupdb`.`city_sequence` (
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


-- -----------------------------------------------------
-- Table `lookupdb`.`mercado_category_group_sequence`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `lookupdb`.`mercado_category_group_sequence` (
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


-- -----------------------------------------------------
-- Table `lookupdb`.`mercado_category_sequence`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `lookupdb`.`mercado_category_sequence` (
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


-- -----------------------------------------------------
-- Table `lookupdb`.`postal_code_sequence`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `lookupdb`.`postal_code_sequence` (
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


-- -----------------------------------------------------
-- Table `lookupdb`.`state_sequence`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `lookupdb`.`state_sequence` (
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

USE `lookupdb` ;

-- -----------------------------------------------------
-- function nextvalForCity
-- -----------------------------------------------------

DELIMITER $$
USE `lookupdb`$$
CREATE DEFINER=`root`@`localhost` FUNCTION `nextvalForCity`() RETURNS bigint(20)
BEGIN
DECLARE cur_val bigint(20);
SELECT cur_value INTO cur_val FROM `lookupdb`.`city_sequence` WHERE sequence_id = 1 FOR UPDATE;
UPDATE `lookupdb`.`city_sequence` SET cur_value = cur_value + increment WHERE sequence_id = 1;
RETURN cur_val;
END$$

DELIMITER ;

-- -----------------------------------------------------
-- function nextvalForMercadoCategory
-- -----------------------------------------------------

DELIMITER $$
USE `lookupdb`$$
CREATE DEFINER=`root`@`localhost` FUNCTION `nextvalForMercadoCategory`() RETURNS bigint(20)
BEGIN
DECLARE cur_val bigint(20);
SELECT cur_value INTO cur_val FROM `lookupdb`.`mercado_category_sequence` WHERE sequence_id = 1 FOR UPDATE;
UPDATE `lookupdb`.`mercado_category_sequence` SET cur_value = cur_value + increment WHERE sequence_id = 1;
RETURN cur_val;
END$$

DELIMITER ;

-- -----------------------------------------------------
-- function nextvalForMercadoCategoryGroup
-- -----------------------------------------------------

DELIMITER $$
USE `lookupdb`$$
CREATE DEFINER=`root`@`localhost` FUNCTION `nextvalForMercadoCategoryGroup`() RETURNS bigint(20)
BEGIN
DECLARE cur_val bigint(20);
SELECT cur_value INTO cur_val FROM `lookupdb`.`mercado_category_group_sequence` WHERE sequence_id = 1 FOR UPDATE;
UPDATE `lookupdb`.`mercado_category_group_sequence` SET cur_value = cur_value + increment WHERE sequence_id = 1;
RETURN cur_val;
END$$

DELIMITER ;

-- -----------------------------------------------------
-- function nextvalForPostalCode
-- -----------------------------------------------------

DELIMITER $$
USE `lookupdb`$$
CREATE DEFINER=`root`@`localhost` FUNCTION `nextvalForPostalCode`() RETURNS bigint(20)
BEGIN
DECLARE cur_val bigint(20);
SELECT cur_value INTO cur_val FROM `lookupdb`.`postal_code_sequence` WHERE sequence_id = 1 FOR UPDATE;
UPDATE `lookupdb`.`postal_code_sequence` SET cur_value = cur_value + increment WHERE sequence_id = 1;
RETURN cur_val;
END$$

DELIMITER ;

-- -----------------------------------------------------
-- function nextvalForState
-- -----------------------------------------------------

DELIMITER $$
USE `lookupdb`$$
CREATE DEFINER=`root`@`localhost` FUNCTION `nextvalForState`() RETURNS bigint(20)
BEGIN
DECLARE cur_val bigint(20);
SELECT cur_value INTO cur_val FROM `lookupdb`.`state_sequence` WHERE sequence_id = 1 FOR UPDATE;
UPDATE `lookupdb`.`state_sequence` SET cur_value = cur_value + increment WHERE sequence_id = 1;
RETURN cur_val;
END$$

DELIMITER ;
USE `lookupdb`;

DELIMITER $$
USE `lookupdb`$$
CREATE
DEFINER=`root`@`localhost`
TRIGGER `lookupdb`.`mercado_category_group_created`
BEFORE INSERT ON `lookupdb`.`mercado_category_group`
FOR EACH ROW
BEGIN
SET new.created_date := now();
SET new.last_modified_date := now();
END$$

USE `lookupdb`$$
CREATE
DEFINER=`root`@`localhost`
TRIGGER `lookupdb`.`mercado_category_group_updated`
BEFORE UPDATE ON `lookupdb`.`mercado_category_group`
FOR EACH ROW
BEGIN
SET new.last_modified_date := now();
END$$


DELIMITER ;

DELIMITER $$
USE `lookupdb`$$
CREATE
DEFINER=`root`@`localhost`
TRIGGER `lookupdb`.`mercado_category_created`
BEFORE INSERT ON `lookupdb`.`mercado_category`
FOR EACH ROW
BEGIN
SET new.created_date := now();
SET new.last_modified_date := now();
END$$

USE `lookupdb`$$
CREATE
DEFINER=`root`@`localhost`
TRIGGER `lookupdb`.`mercado_category_updated`
BEFORE UPDATE ON `lookupdb`.`mercado_category`
FOR EACH ROW
BEGIN
SET new.last_modified_date := now();
END$$


DELIMITER ;

DELIMITER $$
USE `lookupdb`$$
CREATE
DEFINER=`root`@`localhost`
TRIGGER `lookupdb`.`state_created`
BEFORE INSERT ON `lookupdb`.`state`
FOR EACH ROW
BEGIN
SET new.created_date := now();
SET new.last_modified_date := now();
END$$

USE `lookupdb`$$
CREATE
DEFINER=`root`@`localhost`
TRIGGER `lookupdb`.`state_updated`
BEFORE UPDATE ON `lookupdb`.`state`
FOR EACH ROW
BEGIN
SET new.last_modified_date := now();
END$$


DELIMITER ;

DELIMITER $$
USE `lookupdb`$$
CREATE
DEFINER=`root`@`localhost`
TRIGGER `lookupdb`.`city_created`
BEFORE INSERT ON `lookupdb`.`city`
FOR EACH ROW
BEGIN
SET new.created_date := now();
SET new.last_modified_date := now();
END$$

USE `lookupdb`$$
CREATE
DEFINER=`root`@`localhost`
TRIGGER `lookupdb`.`city_updated`
BEFORE UPDATE ON `lookupdb`.`city`
FOR EACH ROW
BEGIN
SET new.last_modified_date := now();
END$$


DELIMITER ;

DELIMITER $$
USE `lookupdb`$$
CREATE
DEFINER=`root`@`localhost`
TRIGGER `lookupdb`.`postal_code_created`
BEFORE INSERT ON `lookupdb`.`postal_code`
FOR EACH ROW
BEGIN
SET new.created_date := now();
SET new.last_modified_date := now();
END$$

USE `lookupdb`$$
CREATE
DEFINER=`root`@`localhost`
TRIGGER `lookupdb`.`postal_code_updated`
BEFORE UPDATE ON `lookupdb`.`postal_code`
FOR EACH ROW
BEGIN
SET new.last_modified_date := now();
END$$


DELIMITER ;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
