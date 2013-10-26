SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

CREATE SCHEMA IF NOT EXISTS `batchdb` DEFAULT CHARACTER SET utf8 ;
USE `batchdb` ;

-- -----------------------------------------------------
-- Table `batchdb`.`file`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `batchdb`.`file` (
  `file_id` BIGINT(20) NOT NULL ,
  `file_name` VARCHAR(128) NOT NULL ,
  `file_group_id` VARCHAR(64) NOT NULL COMMENT 'time stamp ' ,
  `files_count_in_group` TINYINT(4) NULL DEFAULT NULL ,
  `account_id` BIGINT(20) NOT NULL ,
  `ref_url` VARCHAR(512) NULL DEFAULT NULL ,
  `storage_source` TINYINT(4) NULL DEFAULT NULL ,
  `created_date` DATETIME NULL DEFAULT NULL ,
  `last_modified_date` DATETIME NULL DEFAULT NULL ,
  `use_case` TINYINT(4) NULL DEFAULT NULL ,
  `status` TINYINT(4) NULL DEFAULT NULL ,
  PRIMARY KEY (`file_id`) ,
  UNIQUE INDEX `file_unique` (`file_name` ASC, `file_group_id` ASC, `account_id` ASC) ,
  INDEX `filegroupindex` (`file_group_id` ASC, `account_id` ASC) ,
  INDEX `accountIndex` (`account_id` ASC) )
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `batchdb`.`file_sequence`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `batchdb`.`file_sequence` (
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
-- Table `batchdb`.`job`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `batchdb`.`job` (
  `job_id` BIGINT(20) UNSIGNED NOT NULL ,
  `account_id` BIGINT(20) NOT NULL ,
  `request_file_group_id` VARCHAR(64) NOT NULL ,
  `response_file_group_id` VARCHAR(64) NULL DEFAULT NULL ,
  `iso_country_code` VARCHAR(3) NULL DEFAULT NULL ,
  `status` TINYINT(4) NOT NULL ,
  `title` VARCHAR(64) NULL DEFAULT NULL ,
  `usecase` TINYINT(4) NULL DEFAULT NULL ,
  `error` VARCHAR(4000) NULL DEFAULT NULL ,
  `total_request_file_size` BIGINT(20) NULL DEFAULT NULL ,
  `total_response_file_size` BIGINT(20) NULL DEFAULT NULL ,
  `created_date` DATETIME NOT NULL ,
  `last_modified_date` DATETIME NOT NULL ,
  PRIMARY KEY (`job_id`) )
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `batchdb`.`job_sequence`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `batchdb`.`job_sequence` (
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
-- Table `batchdb`.`task`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `batchdb`.`task` (
  `task_id` BIGINT(20) NOT NULL ,
  `job_id` BIGINT(20) NOT NULL ,
  `request_group_id` VARCHAR(64) NOT NULL ,
  `account_id` BIGINT(20) NULL DEFAULT NULL ,
  `request_file_id` BIGINT(20) NULL DEFAULT NULL ,
  `record_id` BIGINT(20) NULL DEFAULT NULL ,
  `request` BLOB NOT NULL ,
  `response` BLOB NULL DEFAULT NULL ,
  `status` TINYINT(4) NULL DEFAULT NULL ,
  `iso_country_code` VARCHAR(3) NULL DEFAULT NULL ,
  `created_date` DATETIME NOT NULL ,
  `last_modified_date` DATETIME NOT NULL ,
  PRIMARY KEY (`task_id`) ,
  INDEX `jobindex` (`job_id` ASC) )
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `batchdb`.`task_sequence`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `batchdb`.`task_sequence` (
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

USE `batchdb` ;

-- -----------------------------------------------------
-- function nextvalForFile
-- -----------------------------------------------------

DELIMITER $$
USE `batchdb`$$
CREATE DEFINER=`root`@`localhost` FUNCTION `nextvalForFile`() RETURNS bigint(20)
BEGIN
DECLARE cur_val bigint(20);
SELECT cur_value INTO cur_val FROM `batchdb`.`file_sequence` WHERE sequence_id = 1 FOR UPDATE;
UPDATE `batchdb`.`file_sequence` SET cur_value = cur_value + increment WHERE sequence_id = 1;
RETURN cur_val;
END$$

DELIMITER ;

-- -----------------------------------------------------
-- function nextvalForJob
-- -----------------------------------------------------

DELIMITER $$
USE `batchdb`$$
CREATE DEFINER=`root`@`localhost` FUNCTION `nextvalForJob`() RETURNS bigint(20)
BEGIN
DECLARE cur_val bigint(20);
SELECT cur_value INTO cur_val FROM `batchdb`.`job_sequence` WHERE sequence_id = 1 FOR UPDATE;
UPDATE `batchdb`.`job_sequence` SET cur_value = cur_value + increment WHERE sequence_id = 1;
RETURN cur_val;
END$$

DELIMITER ;

-- -----------------------------------------------------
-- function nextvalForTask
-- -----------------------------------------------------

DELIMITER $$
USE `batchdb`$$
CREATE DEFINER=`root`@`localhost` FUNCTION `nextvalForTask`() RETURNS bigint(20)
BEGIN
DECLARE cur_val bigint(20);
SELECT cur_value INTO cur_val FROM `batchdb`.`task_sequence` WHERE sequence_id = 1 FOR UPDATE;
UPDATE `batchdb`.`task_sequence` SET cur_value = cur_value + increment WHERE sequence_id = 1;
RETURN cur_val;
END$$

DELIMITER ;
USE `batchdb`;

DELIMITER $$
USE `batchdb`$$
CREATE
DEFINER=`root`@`localhost`
TRIGGER `batchdb`.`file_created`
BEFORE INSERT ON `batchdb`.`file`
FOR EACH ROW
BEGIN
SET new.created_date := now();
SET new.last_modified_date := now();
END$$

USE `batchdb`$$
CREATE
DEFINER=`root`@`localhost`
TRIGGER `batchdb`.`file_updated`
BEFORE UPDATE ON `batchdb`.`file`
FOR EACH ROW
BEGIN
SET new.last_modified_date := now();
END$$


DELIMITER ;

DELIMITER $$
USE `batchdb`$$
CREATE
DEFINER=`root`@`localhost`
TRIGGER `batchdb`.`job_created`
BEFORE INSERT ON `batchdb`.`job`
FOR EACH ROW
BEGIN
SET new.created_date := now();
SET new.last_modified_date := now();
END$$

USE `batchdb`$$
CREATE
DEFINER=`root`@`localhost`
TRIGGER `batchdb`.`job_updated`
BEFORE UPDATE ON `batchdb`.`job`
FOR EACH ROW
BEGIN
SET new.last_modified_date := now();
END$$


DELIMITER ;

DELIMITER $$
USE `batchdb`$$
CREATE
DEFINER=`root`@`localhost`
TRIGGER `batchdb`.`task_created`
BEFORE INSERT ON `batchdb`.`task`
FOR EACH ROW
BEGIN
SET new.created_date := now();
SET new.last_modified_date := now();
END$$

USE `batchdb`$$
CREATE
DEFINER=`root`@`localhost`
TRIGGER `batchdb`.`task_updated`
BEFORE UPDATE ON `batchdb`.`task`
FOR EACH ROW
BEGIN
SET new.last_modified_date := now();
END$$


DELIMITER ;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
