SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

CREATE SCHEMA IF NOT EXISTS `acctdb` DEFAULT CHARACTER SET utf8 ;
USE `acctdb` ;

-- -----------------------------------------------------
-- Table `acctdb`.`address`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `acctdb`.`address` ;

CREATE  TABLE IF NOT EXISTS `acctdb`.`address` (
  `address_id` BIGINT NOT NULL ,
  `account_id` BIGINT NOT NULL ,
  `use_case` TINYINT NOT NULL ,
  `addr_type` TINYINT NULL ,
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
  `status` TINYINT NULL DEFAULT NULL ,
  `created_date` DATETIME NULL DEFAULT NULL ,
  `last_modified_date` DATETIME NULL DEFAULT NULL ,
  PRIMARY KEY (`address_id`) ,
  INDEX `accountindex` (`account_id` ASC) )
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `acctdb`.`account`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `acctdb`.`account` ;

CREATE  TABLE IF NOT EXISTS `acctdb`.`account` (
  `account_id` BIGINT NOT NULL ,
  `email_addr` VARCHAR(128) NOT NULL ,
  `alternate_email` VARCHAR(128) NULL ,
  `password` VARCHAR(512) NOT NULL ,
  `salt` VARCHAR(128) NOT NULL ,
  `first_name` VARCHAR(64) NOT NULL ,
  `last_name` VARCHAR(64) NULL ,
  `middle_name` VARCHAR(64) NULL ,
  `contact_phone_mobile` VARCHAR(32) NULL ,
  `contact_phone_home` VARCHAR(32) NULL ,
  `contact_phone_work` VARCHAR(32) NULL ,
  `picture` BLOB NULL ,
  `registration_addr_id` BIGINT NULL ,
  `billing_addr_id` BIGINT NULL ,
  `status` TINYINT NULL ,
  `language` VARCHAR(3) NULL ,
  `currency_code_iso3` VARCHAR(3) NULL ,
  `roles` VARCHAR(128) NULL ,
  `created_date` DATETIME NULL ,
  `last_modified_date` DATETIME NULL ,
  `last_modified_app` TINYINT NULL ,
  PRIMARY KEY (`account_id`) ,
  UNIQUE INDEX `email_addr_unique` (`email_addr` ASC) ,
  INDEX `fk_account_address1_idx` (`registration_addr_id` ASC) ,
  INDEX `fk_account_address2_idx` (`billing_addr_id` ASC) )
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `acctdb`.`account_confirmation`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `acctdb`.`account_confirmation` ;

CREATE  TABLE IF NOT EXISTS `acctdb`.`account_confirmation` (
  `account_confirmation_id` BIGINT NOT NULL AUTO_INCREMENT ,
  `account_id` BIGINT NOT NULL ,
  `confirmation_code` SMALLINT NULL ,
  `number_of_attempts` TINYINT NULL ,
  `session_id` VARCHAR(1024) NULL ,
  `confirmation_date` DATETIME NULL ,
  `expiry_date` DATETIME NULL ,
  `created_date` DATETIME NULL ,
  `last_modified_date` DATETIME NULL ,
  `last_modified_app` TINYINT NULL ,
  PRIMARY KEY (`account_confirmation_id`) ,
  INDEX `accountindex` (`account_id` ASC) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `acctdb`.`password_history`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `acctdb`.`password_history` ;

CREATE  TABLE IF NOT EXISTS `acctdb`.`password_history` (
  `password_history_id` BIGINT NOT NULL AUTO_INCREMENT ,
  `account_id` BIGINT NOT NULL ,
  `password` VARCHAR(512) NULL ,
  `salt` VARCHAR(128) NULL ,
  `created_date` DATETIME NULL ,
  `last_modified_date` DATETIME NULL ,
  PRIMARY KEY (`password_history_id`) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `acctdb`.`accout_security_question`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `acctdb`.`accout_security_question` ;

CREATE  TABLE IF NOT EXISTS `acctdb`.`accout_security_question` (
  `accout_security_question_id` BIGINT NOT NULL ,
  `account_id` BIGINT NOT NULL ,
  `security_question_id` INT NULL ,
  `security_answer` VARCHAR(512) NULL ,
  `created_date` DATETIME NULL ,
  `last_modified_date` DATETIME NULL ,
  `last_modified_app` TINYINT NULL ,
  PRIMARY KEY (`accout_security_question_id`) )
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

USE `acctdb` ;

SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
