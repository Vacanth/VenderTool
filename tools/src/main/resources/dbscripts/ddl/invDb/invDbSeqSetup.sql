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

