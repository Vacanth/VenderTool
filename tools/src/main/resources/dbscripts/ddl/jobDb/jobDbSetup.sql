SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

CREATE SCHEMA IF NOT EXISTS `jobdb` DEFAULT CHARACTER SET utf8 ;
USE `jobdb` ;

-- -----------------------------------------------------
-- Table `jobdb`.`job`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `jobdb`.`job` ;

CREATE  TABLE IF NOT EXISTS `jobdb`.`job` (
  `job_id` BIGINT UNSIGNED NOT NULL ,
  `account_id` BIGINT NOT NULL ,
  `request_file_group_id` VARCHAR(64) NOT NULL ,
  `response_file_group_id` VARCHAR(64) NULL ,
  `iso_country_code` VARCHAR(3) NULL DEFAULT NULL ,
  `status` TINYINT NOT NULL ,
  `title` VARCHAR(64) NULL DEFAULT NULL ,
  `usecase` TINYINT NULL DEFAULT NULL ,
  `error` VARCHAR(4000) NULL DEFAULT NULL ,
  `total_request_file_size` BIGINT NULL ,
  `total_response_file_size` BIGINT NULL ,
  `created_date` DATETIME NOT NULL ,
  `last_modified_date` DATETIME NOT NULL ,
  PRIMARY KEY (`job_id`) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `jobdb`.`task`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `jobdb`.`task` ;

CREATE  TABLE IF NOT EXISTS `jobdb`.`task` (
  `task_id` BIGINT NOT NULL ,
  `job_id` BIGINT NOT NULL ,
  `request_group_id` VARCHAR(64) NOT NULL ,
  `account_id` BIGINT NULL DEFAULT NULL ,
  `request_file_id` BIGINT NULL ,
  `record_id` BIGINT NULL DEFAULT NULL ,
  `request` BLOB NOT NULL ,
  `response` BLOB NULL DEFAULT NULL ,
  `status` TINYINT NULL DEFAULT NULL ,
  `iso_country_code` VARCHAR(3) NULL DEFAULT NULL ,
  `created_date` DATETIME NOT NULL ,
  `last_modified_date` DATETIME NOT NULL ,
  PRIMARY KEY (`task_id`) ,
  INDEX `jobindex` (`job_id` ASC) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `jobdb`.`file`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `jobdb`.`file` ;

CREATE  TABLE IF NOT EXISTS `jobdb`.`file` (
  `file_id` BIGINT NOT NULL ,
  `file_group_id` VARCHAR(64) NOT NULL COMMENT 'time stamp ' ,
  `files_count_in_group` TINYINT NULL ,
  `account_id` BIGINT NOT NULL ,
  `ref_url` VARCHAR(512) NULL ,
  `storage_source` TINYINT NULL ,
  `created_date` DATETIME NULL ,
  `last_modified_date` DATETIME NULL ,
  `use_case` TINYINT NULL ,
  `status` TINYINT NULL ,
  PRIMARY KEY (`file_id`) ,
  INDEX `filegroupindex` (`file_group_id` ASC) ,
  INDEX `accountIndex` (`account_id` ASC) )
ENGINE = InnoDB;

USE `jobdb` ;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
