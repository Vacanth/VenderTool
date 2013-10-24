SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

CREATE SCHEMA IF NOT EXISTS `invdb` DEFAULT CHARACTER SET utf8 ;
USE `invdb` ;

-- -----------------------------------------------------
-- Table `invdb`.`account`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `invdb`.`account` (
  `account_id` BIGINT(20) NOT NULL ,
  `email_addr` VARCHAR(128) NOT NULL ,
  `alternate_email` VARCHAR(128) NULL DEFAULT NULL ,
  `password` VARCHAR(512) NOT NULL ,
  `salt` VARCHAR(128) NOT NULL ,
  `first_name` VARCHAR(64) NOT NULL ,
  `last_name` VARCHAR(64) NULL DEFAULT NULL ,
  `middle_name` VARCHAR(64) NULL DEFAULT NULL ,
  `contact_phone_mobile` VARCHAR(32) NULL DEFAULT NULL ,
  `contact_phone_home` VARCHAR(32) NULL DEFAULT NULL ,
  `contact_phone_work` VARCHAR(32) NULL DEFAULT NULL ,
  `picture` BLOB NULL DEFAULT NULL ,
  `registration_addr_id` BIGINT(20) NULL DEFAULT NULL ,
  `billing_addr_id` BIGINT(20) NULL DEFAULT NULL ,
  `status` TINYINT(4) NULL DEFAULT NULL ,
  `language` VARCHAR(3) NULL DEFAULT NULL ,
  `currency_code_iso3` VARCHAR(3) NULL DEFAULT NULL ,
  `roles` VARCHAR(128) NULL DEFAULT NULL ,
  `last_login_date` DATETIME NULL DEFAULT NULL ,
  `created_date` DATETIME NULL DEFAULT NULL ,
  `last_modified_date` DATETIME NULL DEFAULT NULL ,
  `last_modified_app` TINYINT(4) NULL DEFAULT NULL ,
  PRIMARY KEY (`account_id`) ,
  UNIQUE INDEX `email_addr_unique` (`email_addr` ASC) ,
  INDEX `fk_account_address1_idx` (`registration_addr_id` ASC) ,
  INDEX `fk_account_address2_idx` (`billing_addr_id` ASC) )
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `invdb`.`account_confirmation`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `invdb`.`account_confirmation` (
  `account_confirmation_id` BIGINT(20) NOT NULL AUTO_INCREMENT ,
  `account_id` BIGINT(20) NOT NULL ,
  `email_addr` VARCHAR(128) NULL DEFAULT NULL ,
  `status` TINYINT(4) NULL DEFAULT NULL ,
  `confirmation_code` INT(6) NULL DEFAULT NULL ,
  `number_of_attempts` TINYINT(4) NULL DEFAULT NULL ,
  `session_id` VARCHAR(1024) NULL DEFAULT NULL ,
  `confirmation_date` DATETIME NULL DEFAULT NULL ,
  `expiry_date` DATETIME NOT NULL ,
  `created_date` DATETIME NOT NULL ,
  `last_modified_date` DATETIME NOT NULL ,
  `last_modified_app` TINYINT(4) NULL DEFAULT NULL ,
  PRIMARY KEY (`account_confirmation_id`) ,
  INDEX `accountindex` (`account_id` ASC) )
ENGINE = InnoDB
AUTO_INCREMENT = 142
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `invdb`.`account_security_question`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `invdb`.`account_security_question` (
  `account_security_question_id` BIGINT(20) NOT NULL AUTO_INCREMENT ,
  `account_id` BIGINT(20) NOT NULL ,
  `security_question_code` VARCHAR(64) NULL DEFAULT NULL ,
  `security_answer` VARCHAR(512) NULL DEFAULT NULL ,
  `created_date` DATETIME NULL DEFAULT NULL ,
  `last_modified_date` DATETIME NULL DEFAULT NULL ,
  `last_modified_app` TINYINT(4) NULL DEFAULT NULL ,
  PRIMARY KEY (`account_security_question_id`) ,
  UNIQUE INDEX `unique` (`account_id` ASC, `security_question_code` ASC) )
ENGINE = InnoDB
AUTO_INCREMENT = 206
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `invdb`.`account_sequence`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `invdb`.`account_sequence` (
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
-- Table `invdb`.`address`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `invdb`.`address` (
  `address_id` BIGINT(20) NOT NULL ,
  `account_id` BIGINT(20) NOT NULL ,
  `use_case` TINYINT(4) NOT NULL ,
  `addr_type` TINYINT(4) NULL DEFAULT NULL ,
  `contact_first_name` VARCHAR(64) NULL DEFAULT NULL ,
  `contact_last_name` VARCHAR(64) NULL DEFAULT NULL ,
  `company_name` VARCHAR(64) NULL DEFAULT NULL ,
  `addr_ln1` VARCHAR(128) NULL DEFAULT NULL ,
  `addr_ln2` VARCHAR(128) NULL DEFAULT NULL ,
  `addr_ln3` VARCHAR(128) NULL DEFAULT NULL ,
  `city` VARCHAR(64) NULL DEFAULT NULL ,
  `state` VARCHAR(64) NULL DEFAULT NULL ,
  `country_code_iso3` VARCHAR(3) NULL DEFAULT NULL ,
  `postal_code` VARCHAR(32) NULL DEFAULT NULL ,
  `status` TINYINT(4) NULL DEFAULT NULL ,
  `created_date` DATETIME NULL DEFAULT NULL ,
  `last_modified_date` DATETIME NULL DEFAULT NULL ,
  PRIMARY KEY (`address_id`) ,
  INDEX `accountindex` (`account_id` ASC) )
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `invdb`.`address_sequence`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `invdb`.`address_sequence` (
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
-- Table `invdb`.`forgot_password`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `invdb`.`forgot_password` (
  `forgot_password_id` BIGINT(20) NOT NULL AUTO_INCREMENT ,
  `email_addr` VARCHAR(128) NOT NULL ,
  `account_id` BIGINT(20) NULL DEFAULT NULL ,
  `status` TINYINT(4) NULL DEFAULT NULL ,
  `ip_addr` VARCHAR(64) NULL DEFAULT NULL ,
  `created_date` DATETIME NOT NULL ,
  `last_modified_date` DATETIME NOT NULL ,
  PRIMARY KEY (`forgot_password_id`) )
ENGINE = InnoDB
AUTO_INCREMENT = 7
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `invdb`.`password_history`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `invdb`.`password_history` (
  `password_history_id` BIGINT(20) NOT NULL AUTO_INCREMENT ,
  `account_id` BIGINT(20) NULL DEFAULT NULL ,
  `password` VARCHAR(512) NULL DEFAULT NULL ,
  `salt` VARCHAR(128) NULL DEFAULT NULL ,
  `created_date` DATETIME NULL DEFAULT NULL ,
  `last_modified_date` DATETIME NULL DEFAULT NULL ,
  PRIMARY KEY (`password_history_id`) )
ENGINE = InnoDB
AUTO_INCREMENT = 24
DEFAULT CHARACTER SET = utf8;

USE `invdb` ;

-- -----------------------------------------------------
-- function nextvalForAccount
-- -----------------------------------------------------

DELIMITER $$
USE `invdb`$$
CREATE DEFINER=`root`@`localhost` FUNCTION `nextvalForAccount`() RETURNS bigint(20)
BEGIN
DECLARE cur_val bigint(20);
SELECT cur_value INTO cur_val FROM `invdb`.`account_sequence` WHERE sequence_id = 1 FOR UPDATE;
UPDATE `invdb`.`account_sequence` SET cur_value = cur_value + increment WHERE sequence_id = 1;
RETURN cur_val;
END$$

DELIMITER ;

-- -----------------------------------------------------
-- function nextvalForAddress
-- -----------------------------------------------------

DELIMITER $$
USE `invdb`$$
CREATE DEFINER=`root`@`localhost` FUNCTION `nextvalForAddress`() RETURNS bigint(20)
BEGIN
DECLARE cur_val bigint(20);
SELECT cur_value INTO cur_val FROM `invdb`.`listing_sequence` WHERE sequence_id = 1 FOR UPDATE;
UPDATE `invdb`.`listing_sequence` SET cur_value = cur_value + increment WHERE sequence_id = 1;
RETURN cur_val;
END$$

DELIMITER ;
USE `invdb`;

DELIMITER $$
USE `invdb`$$
CREATE
DEFINER=`root`@`localhost`
TRIGGER `invdb`.`account_created`
BEFORE INSERT ON `invdb`.`account`
FOR EACH ROW
BEGIN
SET new.created_date := now();
SET new.last_modified_date := now();
END$$

USE `invdb`$$
CREATE
DEFINER=`root`@`localhost`
TRIGGER `invdb`.`account_updated`
BEFORE UPDATE ON `invdb`.`account`
FOR EACH ROW
BEGIN
SET new.last_modified_date := now();
END$$


DELIMITER ;

DELIMITER $$
USE `invdb`$$
CREATE
DEFINER=`root`@`localhost`
TRIGGER `invdb`.`account_confirmation_created`
BEFORE INSERT ON `invdb`.`account_confirmation`
FOR EACH ROW
BEGIN
SET new.created_date := now();
SET new.last_modified_date := now();
END$$

USE `invdb`$$
CREATE
DEFINER=`root`@`localhost`
TRIGGER `invdb`.`account_confirmation_updated`
BEFORE UPDATE ON `invdb`.`account_confirmation`
FOR EACH ROW
BEGIN
SET new.last_modified_date := now();
END$$


DELIMITER ;

DELIMITER $$
USE `invdb`$$
CREATE
DEFINER=`root`@`localhost`
TRIGGER `invdb`.`account_security_question_created`
BEFORE INSERT ON `invdb`.`account_security_question`
FOR EACH ROW
BEGIN
SET new.created_date := now();
SET new.last_modified_date := now();
END$$

USE `invdb`$$
CREATE
DEFINER=`root`@`localhost`
TRIGGER `invdb`.`account_security_question_updated`
BEFORE UPDATE ON `invdb`.`account_security_question`
FOR EACH ROW
BEGIN
SET new.last_modified_date := now();
END$$


DELIMITER ;

DELIMITER $$
USE `invdb`$$
CREATE
DEFINER=`root`@`localhost`
TRIGGER `invdb`.`address_created`
BEFORE INSERT ON `invdb`.`address`
FOR EACH ROW
BEGIN
SET new.created_date := now();
SET new.last_modified_date := now();
END$$

USE `invdb`$$
CREATE
DEFINER=`root`@`localhost`
TRIGGER `invdb`.`address_updated`
BEFORE UPDATE ON `invdb`.`address`
FOR EACH ROW
BEGIN
SET new.last_modified_date := now();
END$$


DELIMITER ;

DELIMITER $$
USE `invdb`$$
CREATE
DEFINER=`root`@`localhost`
TRIGGER `invdb`.`forgot_password_created`
BEFORE INSERT ON `invdb`.`forgot_password`
FOR EACH ROW
BEGIN
SET new.created_date := now();
SET new.last_modified_date := now();
END$$

USE `invdb`$$
CREATE
DEFINER=`root`@`localhost`
TRIGGER `invdb`.`forgot_password_updated`
BEFORE UPDATE ON `invdb`.`forgot_password`
FOR EACH ROW
BEGIN
SET new.last_modified_date := now();
END$$


DELIMITER ;

DELIMITER $$
USE `invdb`$$
CREATE
DEFINER=`root`@`localhost`
TRIGGER `invdb`.`password_history_created`
BEFORE INSERT ON `invdb`.`password_history`
FOR EACH ROW
BEGIN
SET new.created_date := now();
SET new.last_modified_date := now();
END$$

USE `invdb`$$
CREATE
DEFINER=`root`@`localhost`
TRIGGER `invdb`.`password_history_updated`
BEFORE UPDATE ON `invdb`.`password_history`
FOR EACH ROW
BEGIN
SET new.last_modified_date := now();
END$$


DELIMITER ;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
