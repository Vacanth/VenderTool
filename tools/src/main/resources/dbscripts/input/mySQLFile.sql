
USE `invdb` ;


-- -----------------------------------------------------
-- Table `invdb`.`listing`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `invdb`.`listing_bkp` (
  `listing_id` BIGINT(20) NOT NULL ,
  `product_id` BIGINT(20) NOT NULL ,
  `account_id` BIGINT(20) NOT NULL ,
  `master_template_id` BIGINT(20) NOT NULL ,
  `quantity` INT(11) NOT NULL ,
  `category_id` VARCHAR(64) NOT NULL ,
  `marketplace_id` TINYINT(4) NOT NULL ,
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
  `last_modified_app` TINYINT(4) NULL DEFAULT NULL ,
  PRIMARY KEY (`listing_id`) ,
  INDEX `fk_listing_merchant_product1_idx` (`product_id` ASC) ,
  INDEX `fk_listing_master_template1_idx` (`master_template_id` ASC) ,
  INDEX `AccountIdIndex` (`account_id` ASC, `listing_id` ASC) ,
  INDEX `AccountIdIndex2` (`account_id` ASC, `product_id` ASC) ,
  INDEX `AccountIdAndMP` (`account_id` ASC, `marketplace_id` ASC, `last_modified_date` ASC) )
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;
