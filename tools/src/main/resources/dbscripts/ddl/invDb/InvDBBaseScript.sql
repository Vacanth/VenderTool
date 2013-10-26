SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

CREATE SCHEMA IF NOT EXISTS `invdb` DEFAULT CHARACTER SET utf8 ;
USE `invdb` ;

-- -----------------------------------------------------
-- Table `invdb`.`image`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `invdb`.`image` (
  `image_id` BIGINT(20) NOT NULL AUTO_INCREMENT ,
  `account_id` BIGINT(20) NOT NULL ,
  `image_name` VARCHAR(64) NULL DEFAULT NULL ,
  `sort_order_id` INT NULL DEFAULT NULL ,
  `ref_id` BIGINT(20) NOT NULL ,
  `ref_type` INT NOT NULL ,
  `image_format` INT NOT NULL ,
  `hosted_url` VARCHAR(1028) NOT NULL ,
  `hash` VARCHAR(64) NULL DEFAULT NULL ,
  `size` VARCHAR(32) NULL DEFAULT NULL ,
  `created_date` DATETIME NULL DEFAULT NULL ,
  `last_modified_date` DATETIME NULL DEFAULT NULL ,
  PRIMARY KEY (`image_id`) ,
  INDEX `fk_image_product_variation1_idx` (`ref_id` ASC) ,
  INDEX `fk_image_merchant_product1_idx` (`ref_id` ASC) ,
  INDEX `refid_reftype` (`ref_id` ASC, `ref_type` ASC, `account_id` ASC) ,
  INDEX `accountid` (`account_id` ASC, `last_modified_date` ASC) )
ENGINE = InnoDB
AUTO_INCREMENT = 124
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `invdb`.`listing`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `invdb`.`listing` (
  `listing_id` BIGINT(20) NOT NULL ,
  `product_id` BIGINT(20) NOT NULL ,
  `account_id` BIGINT(20) NOT NULL ,
  `master_template_id` BIGINT(20) NOT NULL ,
  `quantity` INT NOT NULL ,
  `category_id` VARCHAR(64) NOT NULL ,
  `marketplace_id` INT NOT NULL ,
  `marketplace_item_id` VARCHAR(45) NULL DEFAULT NULL ,
  `currency_code_iso3` VARCHAR(3) NULL DEFAULT NULL ,
  `fixed_price` DECIMAL(18,4) NULL DEFAULT NULL ,
  `auction_start_price` DECIMAL(18,4) NULL DEFAULT NULL ,
  `auction_current_price` DECIMAL(18,4) NULL DEFAULT NULL ,
  `auction_reserved_price` DECIMAL(18,4) NULL DEFAULT NULL ,
  `warranty` VARCHAR(2000) NULL DEFAULT NULL ,
  `item_start_time` DATETIME NULL DEFAULT NULL ,
  `item_end_time` DATETIME NULL DEFAULT NULL ,
  `parent_item_id` BIGINT(20) NULL DEFAULT NULL ,
  `condition` VARCHAR(64) NULL DEFAULT NULL ,
  `created_date` DATETIME NULL DEFAULT NULL ,
  `last_modified_date` DATETIME NULL DEFAULT NULL ,
  `last_modified_app` INT NULL ,
  PRIMARY KEY (`listing_id`) ,
  INDEX `fk_listing_merchant_product1_idx` (`product_id` ASC) ,
  INDEX `fk_listing_master_template1_idx` (`master_template_id` ASC) ,
  INDEX `AccountIdIndex` (`account_id` ASC, `listing_id` ASC) ,
  INDEX `AccountIdIndex2` (`account_id` ASC, `product_id` ASC) ,
  INDEX `AccountIdAndMP` (`account_id` ASC, `marketplace_id` ASC, `last_modified_date` ASC) )
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `invdb`.`listing_sequence`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `invdb`.`listing_sequence` (
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
-- Table `invdb`.`listing_variation`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `invdb`.`listing_variation` (
  `listing_variation_id` BIGINT(20) NOT NULL ,
  `listing_id` BIGINT(20) NULL DEFAULT NULL ,
  `product_variation_id` BIGINT(20) NULL DEFAULT NULL ,
  `quantity` INT(11) NULL DEFAULT NULL ,
  `price` DECIMAL(18,2) NULL DEFAULT NULL ,
  `condition` VARCHAR(64) NULL DEFAULT NULL ,
  `last_modified_date` DATETIME NULL DEFAULT NULL ,
  `last_modified_app` TINYINT(4) NULL DEFAULT NULL ,
  `created_date` DATETIME NULL DEFAULT NULL ,
  PRIMARY KEY (`listing_variation_id`) ,
  INDEX `fk_listing_variation_listing1_idx` (`listing_id` ASC) )
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `invdb`.`listing_variation_sequence`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `invdb`.`listing_variation_sequence` (
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
-- Table `invdb`.`product`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `invdb`.`product` (
  `product_id` BIGINT(20) NOT NULL ,
  `title` VARCHAR(256) NOT NULL ,
  `sku` VARCHAR(64) NOT NULL ,
  `price` DECIMAL(18,4) NOT NULL ,
  `currency_code_iso3` VARCHAR(3) NOT NULL ,
  `quantity` INT(11) NOT NULL ,
  `account_id` BIGINT(20) NOT NULL ,
  `product_code` VARCHAR(64) NULL DEFAULT NULL ,
  `product_code_type` INT NULL DEFAULT NULL ,
  `weight` DECIMAL(8,2) NULL DEFAULT NULL ,
  `height` DECIMAL(8,2) NULL DEFAULT NULL ,
  `length` DECIMAL(8,2) NULL DEFAULT NULL ,
  `width` DECIMAL(8,2) NULL DEFAULT NULL ,
  `dimension_unit` INT NULL DEFAULT NULL ,
  `weight_unit` INT NULL DEFAULT NULL ,
  `product_url` VARCHAR(4000) NULL DEFAULT NULL ,
  `created_date` DATETIME NULL DEFAULT NULL ,
  `last_modified_date` DATETIME NULL DEFAULT NULL ,
  `last_modified_app` INT NULL DEFAULT NULL ,
  PRIMARY KEY (`product_id`) ,
  UNIQUE INDEX `sku` (`sku` ASC, `account_id` ASC) ,
  INDEX `AccountAndProduct` (`product_id` ASC, `account_id` ASC) )
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `invdb`.`product_attribute`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `invdb`.`product_attribute` (
  `product_attribute_id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT 'Attr name hold  one single value .' ,
  `ref_id` BIGINT(20) NOT NULL ,
  `ref_type` INT NOT NULL ,
  `attr_name` VARCHAR(64) NULL DEFAULT NULL ,
  `attr_char_value` VARCHAR(512) NULL DEFAULT NULL ,
  `attr_num_value` DECIMAL(18,4) NULL DEFAULT NULL ,
  `attr_date_value` DATETIME NULL DEFAULT NULL ,
  `attr_value_type` INT NULL ,
  `created_date` DATETIME NULL DEFAULT NULL ,
  `last_modified_date` DATETIME NULL DEFAULT NULL ,
  PRIMARY KEY (`product_attribute_id`) ,
  INDEX `fk_product_attribute_merchant_product1_idx` (`ref_id` ASC) ,
  INDEX `fk_product_attribute_product_variation1_idx` (`ref_id` ASC) ,
  INDEX `refIdAndRefType` (`ref_id` ASC, `ref_type` ASC) )
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `invdb`.`product_description`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `invdb`.`product_description` (
  `product_description_id` BIGINT(20) NOT NULL AUTO_INCREMENT ,
  `product_id` BIGINT(20) NOT NULL ,
  `account_id` BIGINT(20) NOT NULL ,
  `product_description_title` VARCHAR(64) NOT NULL ,
  `product_description_text` LONGTEXT NOT NULL ,
  `created_date` DATETIME NULL DEFAULT NULL ,
  `last_modified_date` DATETIME NULL DEFAULT NULL ,
  PRIMARY KEY (`product_description_id`) ,
  INDEX `AccountAndProduct` (`product_id` ASC, `account_id` ASC) )
ENGINE = InnoDB
AUTO_INCREMENT = 8
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `invdb`.`product_sequence`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `invdb`.`product_sequence` (
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
-- Table `invdb`.`product_variation`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `invdb`.`product_variation` (
  `product_variation_id` BIGINT(20) NOT NULL ,
  `title` VARCHAR(256) NULL DEFAULT NULL ,
  `product_id` BIGINT(20) NOT NULL ,
  `sku` VARCHAR(64) NOT NULL ,
  `quantity` INT(11) NULL DEFAULT NULL ,
  `price` DECIMAL(18,4) NULL DEFAULT NULL ,
  `currency_code_iso3` VARCHAR(3) NULL DEFAULT NULL ,
  `url` VARCHAR(4000) NULL DEFAULT NULL ,
  `created_date` DATETIME NULL DEFAULT NULL ,
  `last_modified_date` DATETIME NULL DEFAULT NULL ,
  PRIMARY KEY (`product_variation_id`) ,
  INDEX `fk_product_variation_merchant_product1_idx` (`product_id` ASC) )
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `invdb`.`product_variation_sequence`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `invdb`.`product_variation_sequence` (
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

USE `invdb` ;

-- -----------------------------------------------------
-- function nextvalForListing
-- -----------------------------------------------------

DELIMITER $$
USE `invdb`$$
CREATE DEFINER=`root`@`localhost` FUNCTION `nextvalForListing`() RETURNS bigint(20)
BEGIN
DECLARE cur_val bigint(20);
SELECT cur_value INTO cur_val FROM `invdb`.`listing_sequence` WHERE sequence_id = 1 FOR UPDATE;
UPDATE `invdb`.`listing_sequence` SET cur_value = cur_value + increment WHERE sequence_id = 1;
RETURN cur_val;
END$$

DELIMITER ;

-- -----------------------------------------------------
-- function nextvalForListingVariation
-- -----------------------------------------------------

DELIMITER $$
USE `invdb`$$
CREATE DEFINER=`root`@`localhost` FUNCTION `nextvalForListingVariation`() RETURNS bigint(20)
BEGIN
DECLARE cur_val bigint(20);
SELECT cur_value INTO cur_val FROM `invdb`.`listing_variation_sequence` WHERE sequence_id = 1 FOR UPDATE;
UPDATE `invdb`.`listing_variation_sequence` SET cur_value = cur_value + increment WHERE sequence_id = 1;
RETURN cur_val;
END$$

DELIMITER ;

-- -----------------------------------------------------
-- function nextvalForProduct
-- -----------------------------------------------------

DELIMITER $$
USE `invdb`$$
CREATE DEFINER=`root`@`localhost` FUNCTION `nextvalForProduct`() RETURNS bigint(20)
BEGIN
DECLARE cur_val bigint(20);
SELECT cur_value INTO cur_val FROM `invdb`.`product_sequence` WHERE sequence_id = 1 FOR UPDATE;
UPDATE `invdb`.`product_sequence` SET cur_value = cur_value + increment WHERE sequence_id = 1;
RETURN cur_val;
END$$

DELIMITER ;

-- -----------------------------------------------------
-- function nextvalForProductVariation
-- -----------------------------------------------------

DELIMITER $$
USE `invdb`$$
CREATE DEFINER=`root`@`localhost` FUNCTION `nextvalForProductVariation`() RETURNS bigint(20)
BEGIN
DECLARE cur_val bigint(20);
SELECT cur_value INTO cur_val FROM `invdb`.`product_variation_sequence` WHERE sequence_id = 1 FOR UPDATE;
UPDATE `invdb`.`product_variation_sequence` SET cur_value = cur_value + increment WHERE sequence_id = 1;
RETURN cur_val;
END$$

DELIMITER ;
USE `invdb`;

DELIMITER $$
USE `invdb`$$
CREATE
DEFINER=`root`@`localhost`
TRIGGER `invdb`.`image_created`
BEFORE INSERT ON `invdb`.`image`
FOR EACH ROW
BEGIN
SET new.created_date := now();
SET new.last_modified_date := now();
END$$

USE `invdb`$$
CREATE
DEFINER=`root`@`localhost`
TRIGGER `invdb`.`image_updated`
BEFORE UPDATE ON `invdb`.`image`
FOR EACH ROW
BEGIN
SET new.last_modified_date := now();
END$$


DELIMITER ;

DELIMITER $$
USE `invdb`$$
CREATE
DEFINER=`root`@`localhost`
TRIGGER `invdb`.`listing_created`
BEFORE INSERT ON `invdb`.`listing`
FOR EACH ROW
BEGIN
SET new.created_date := now();
SET new.last_modified_date := now();
END$$

USE `invdb`$$
CREATE
DEFINER=`root`@`localhost`
TRIGGER `invdb`.`listing_updated`
BEFORE UPDATE ON `invdb`.`listing`
FOR EACH ROW
BEGIN
SET new.last_modified_date := now();
END$$


DELIMITER ;

DELIMITER $$
USE `invdb`$$
CREATE
DEFINER=`root`@`localhost`
TRIGGER `invdb`.`listing_variation_created`
BEFORE INSERT ON `invdb`.`listing_variation`
FOR EACH ROW
BEGIN
SET new.created_date := now();
SET new.last_modified_date := now();
END$$

USE `invdb`$$
CREATE
DEFINER=`root`@`localhost`
TRIGGER `invdb`.`listing_variation_updated`
BEFORE UPDATE ON `invdb`.`listing_variation`
FOR EACH ROW
BEGIN
SET new.last_modified_date := now();
END$$


DELIMITER ;

DELIMITER $$
USE `invdb`$$
CREATE
DEFINER=`root`@`localhost`
TRIGGER `invdb`.`product_created`
BEFORE INSERT ON `invdb`.`product`
FOR EACH ROW
BEGIN
SET new.created_date := now();
SET new.last_modified_date := now();
END$$

USE `invdb`$$
CREATE
DEFINER=`root`@`localhost`
TRIGGER `invdb`.`product_updated`
BEFORE UPDATE ON `invdb`.`product`
FOR EACH ROW
BEGIN
SET new.last_modified_date := now();
END$$


DELIMITER ;

DELIMITER $$
USE `invdb`$$
CREATE
DEFINER=`root`@`localhost`
TRIGGER `invdb`.`product_attribute_created`
BEFORE INSERT ON `invdb`.`product_attribute`
FOR EACH ROW
BEGIN
SET new.created_date := now();
SET new.last_modified_date := now();
END$$

USE `invdb`$$
CREATE
DEFINER=`root`@`localhost`
TRIGGER `invdb`.`product_attribute_updated`
BEFORE UPDATE ON `invdb`.`product_attribute`
FOR EACH ROW
BEGIN
SET new.last_modified_date := now();
END$$


DELIMITER ;

DELIMITER $$
USE `invdb`$$
CREATE
DEFINER=`root`@`localhost`
TRIGGER `invdb`.`product_description_created`
BEFORE INSERT ON `invdb`.`product_description`
FOR EACH ROW
BEGIN
SET new.created_date := now();
SET new.last_modified_date := now();
END$$

USE `invdb`$$
CREATE
DEFINER=`root`@`localhost`
TRIGGER `invdb`.`product_description_updated`
BEFORE UPDATE ON `invdb`.`product_description`
FOR EACH ROW
BEGIN
SET new.last_modified_date := now();
END$$


DELIMITER ;

DELIMITER $$
USE `invdb`$$
CREATE
DEFINER=`root`@`localhost`
TRIGGER `invdb`.`product_variation_created`
BEFORE INSERT ON `invdb`.`product_variation`
FOR EACH ROW
BEGIN
SET new.created_date := now();
SET new.last_modified_date := now();
END$$

USE `invdb`$$
CREATE
DEFINER=`root`@`localhost`
TRIGGER `invdb`.`product_variation_updated`
BEFORE UPDATE ON `invdb`.`product_variation`
FOR EACH ROW
BEGIN
SET new.last_modified_date := now();
END$$


DELIMITER ;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
