SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

CREATE SCHEMA IF NOT EXISTS `invdb` DEFAULT CHARACTER SET utf8 ;
USE `invdb` ;

-- -----------------------------------------------------
-- Table `invdb`.`product`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `invdb`.`product` ;

CREATE  TABLE IF NOT EXISTS `invdb`.`product` (
  `product_id` BIGINT NOT NULL ,
  `title` VARCHAR(256) NOT NULL ,
  `sku` VARCHAR(64) NOT NULL ,
  `price` DECIMAL(18,4) NOT NULL ,
  `currency_code_iso3` VARCHAR(3) NOT NULL ,
  `quantity` INT NOT NULL ,
  `account_id` BIGINT NOT NULL ,
  `product_code` VARCHAR(64) NULL DEFAULT NULL ,
  `product_code_type` TINYINT NULL DEFAULT NULL ,
  `weight` DECIMAL(8,2) NULL DEFAULT NULL ,
  `height` DECIMAL(8,2) NULL DEFAULT NULL ,
  `length` DECIMAL(8,2) NULL DEFAULT NULL ,
  `width` DECIMAL(8,2) NULL DEFAULT NULL ,
  `dimension_unit` TINYINT NULL DEFAULT NULL ,
  `weight_unit` TINYINT NULL DEFAULT NULL ,
  `product_url` VARCHAR(4000) NULL ,
  `created_date` DATETIME NULL DEFAULT NULL ,
  `last_modified_date` DATETIME NULL DEFAULT NULL ,
  `last_modified_app` TINYINT NULL DEFAULT NULL ,
  PRIMARY KEY (`product_id`) ,
  UNIQUE INDEX `sku` (`sku` ASC, `account_id` ASC) ,
  INDEX `AccountAndProduct` (`product_id` ASC, `account_id` ASC) )
  DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `invdb`.`product_variation`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `invdb`.`product_variation` ;

CREATE  TABLE IF NOT EXISTS `invdb`.`product_variation` (
  `product_variation_id` BIGINT NOT NULL ,
  `title` VARCHAR(256) NULL ,
  `product_id` BIGINT NOT NULL ,
  `sku` VARCHAR(64) NOT NULL ,
  `quantity` INT NULL ,
  `price` DECIMAL(18,4) NULL ,
  `currency_code_iso3` VARCHAR(3) NULL ,
  `url` VARCHAR(4000) NULL ,
  `created_date` DATETIME NULL ,
  `last_modified_date` DATETIME NULL ,
  PRIMARY KEY (`product_variation_id`) ,
  INDEX `fk_product_variation_merchant_product1_idx` (`product_id` ASC)  )
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `invdb`.`image`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `invdb`.`image` ;

CREATE  TABLE IF NOT EXISTS `invdb`.`image` (
  `image_id` BIGINT NOT NULL AUTO_INCREMENT ,
  `account_id` BIGINT NOT NULL ,
  `image_name` VARCHAR(64) NULL ,
  `sort_order_id` TINYINT NULL ,
  `ref_id` BIGINT NOT NULL ,
  `ref_type` TINYINT NOT NULL ,
  `image_format` TINYINT NOT NULL ,
  `hosted_url` VARCHAR(1028) NOT NULL ,
  `hash` VARCHAR(64) NULL ,
  `size` VARCHAR(32) NULL ,
  `created_date` DATETIME NULL ,
  `last_modified_date` DATETIME NULL ,
  PRIMARY KEY (`image_id`) ,
  INDEX `fk_image_product_variation1_idx` (`ref_id` ASC) ,
  INDEX `fk_image_merchant_product1_idx` (`ref_id` ASC) ,
  INDEX `refid_reftype` (`ref_id` ASC, `ref_type` ASC, `account_id` ASC) ,
  INDEX `accountid` (`account_id` ASC, `last_modified_date` ASC))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `invdb`.`product_attribute`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `invdb`.`product_attribute` ;

CREATE  TABLE IF NOT EXISTS `invdb`.`product_attribute` (
  `product_attribute_id` BIGINT NOT NULL AUTO_INCREMENT COMMENT 'Attr name hold  one single value .' ,
  `ref_id` BIGINT NOT NULL ,
  `ref_type` TINYINT NOT NULL ,
  `attr_name` VARCHAR(64) NULL ,
  `attr_char_value` VARCHAR(512) NULL ,
  `attr_num_value` DECIMAL(18,4) NULL ,
  `attr_date_value` DATETIME NULL ,
  `attr_value_type` TINYINT NULL ,
  `created_date` DATETIME NULL ,
  `last_modified_date` DATETIME NULL ,
  PRIMARY KEY (`product_attribute_id`) ,
  INDEX `fk_product_attribute_merchant_product1_idx` (`ref_id` ASC) ,
  INDEX `fk_product_attribute_product_variation1_idx` (`ref_id` ASC) ,
  INDEX `refIdAndRefType` (`ref_id` ASC, `ref_type` ASC))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `invdb`.`listing`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `invdb`.`listing` ;

CREATE  TABLE IF NOT EXISTS `invdb`.`listing` (
  `listing_id` BIGINT NOT NULL ,
  `product_id` BIGINT NOT NULL ,
  `account_id` BIGINT NOT NULL ,
  `master_template_id` BIGINT NOT NULL ,
  `quantity` INT NOT NULL ,
  `category_id` VARCHAR(64) NOT NULL ,
  `marketplace_id` TINYINT NOT NULL ,
  `marketplace_item_id` VARCHAR(45) NULL ,
  `currency_code_iso3` VARCHAR(3) NULL ,
  `fixed_price` DECIMAL(18,4) NULL ,
  `auction_start_price` DECIMAL(18,4) NULL ,
  `auction_current_price` DECIMAL(18,4) NULL ,
  `auction_reserved_price` DECIMAL(18,4) NULL ,
  `warranty` VARCHAR(2000) NULL ,
  `item_start_time` DATETIME NULL ,
  `item_end_time` DATETIME NULL ,
  `parent_item_id` BIGINT NULL ,
  `condition` VARCHAR(64) NULL ,
  `created_date` DATETIME NULL ,
  `last_modified_date` DATETIME NULL ,
  `last_modified_app` TINYINT NULL ,
  PRIMARY KEY (`listing_id`) ,
  INDEX `fk_listing_merchant_product1_idx` (`product_id` ASC) ,
  INDEX `fk_listing_master_template1_idx` (`master_template_id` ASC) ,
  INDEX `AccountIdIndex` (`account_id` ASC, `listing_id` ASC) ,
  INDEX `AccountIdIndex2` (`account_id` ASC, `product_id` ASC) ,
  INDEX `AccountIdAndMP` (`account_id` ASC, `marketplace_id` ASC, `last_modified_date` ASC))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `invdb`.`listing_variation`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `invdb`.`listing_variation` ;

CREATE  TABLE IF NOT EXISTS `invdb`.`listing_variation` (
  `listing_variation_id` BIGINT NOT NULL ,
  `listing_id` BIGINT NULL ,
  `product_variation_id` BIGINT NULL ,
  `quantity` INT NULL ,
  `price` DECIMAL(18,2) NULL ,
  `condition` VARCHAR(64) NULL ,
  `last_modified_date` DATETIME NULL ,
  `last_modified_app` TINYINT NULL ,
  `created_date` DATETIME NULL ,
  PRIMARY KEY (`listing_variation_id`) ,
  INDEX `fk_listing_variation_listing1_idx` (`listing_id` ASC))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `invdb`.`product_description`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `invdb`.`product_description` ;

CREATE  TABLE IF NOT EXISTS `invdb`.`product_description` (
  `product_description_id` BIGINT NOT NULL AUTO_INCREMENT ,
  `product_id` BIGINT NOT NULL ,
  `account_id` BIGINT NOT NULL ,
  `product_description_title` VARCHAR(64) NOT NULL ,
  `product_description_text` LONGTEXT NOT NULL ,
  `created_date` DATETIME NULL ,
  `last_modified_date` DATETIME NULL ,
  PRIMARY KEY (`product_description_id`) ,
  INDEX `AccountAndProduct` (`product_id` ASC, `account_id` ASC) )
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

USE `invdb` ;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
