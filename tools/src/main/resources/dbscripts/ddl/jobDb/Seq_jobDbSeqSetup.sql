SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

CREATE SCHEMA IF NOT EXISTS `jobdb` DEFAULT CHARACTER SET utf8 ;
USE `jobdb` ;

CREATE  TABLE IF NOT EXISTS `jobdb`.`file_sequence` (
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





CREATE  TABLE IF NOT EXISTS `jobdb`.`job_sequence` (
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






CREATE  TABLE IF NOT EXISTS `jobdb`.`task_sequence` (
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




USE `jobdb` ;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;

insert into `jobdb`.`file_sequence`  values (1,1,1,18446744073709551615,1,0,sysdate(),sysdate());
insert into `jobdb`.`job_sequence`  values (1,1,1,18446744073709551615,1,0,sysdate(),sysdate());
insert into `jobdb`.`task_sequence`  values (1,1,1,18446744073709551615,1,0,sysdate(),sysdate());
commit;


SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

CREATE SCHEMA IF NOT EXISTS `jobdb` DEFAULT CHARACTER SET utf8 ;
USE `jobdb` ;

DELIMITER $$
DROP FUNCTION IF EXISTS `jobdb`.`nextvalForFile` $$
CREATE FUNCTION `jobdb`.`nextvalForFile` () RETURNS bigint(20) NOT DETERMINISTIC
BEGIN
DECLARE cur_val bigint(20);
SELECT cur_value INTO cur_val FROM `jobdb`.`file_sequence` WHERE sequence_id = 1 FOR UPDATE;
UPDATE `jobdb`.`file_sequence` SET cur_value = cur_value + increment WHERE sequence_id = 1;
RETURN cur_val;
END $$
DELIMITER ;




DELIMITER $$
DROP FUNCTION IF EXISTS `jobdb`.`nextvalForJob` $$
CREATE FUNCTION `jobdb`.`nextvalForJob` () RETURNS bigint(20) NOT DETERMINISTIC
BEGIN
DECLARE cur_val bigint(20);
SELECT cur_value INTO cur_val FROM `jobdb`.`job_sequence` WHERE sequence_id = 1 FOR UPDATE;
UPDATE `jobdb`.`job_sequence` SET cur_value = cur_value + increment WHERE sequence_id = 1;
RETURN cur_val;
END $$
DELIMITER ;



DELIMITER $$
DROP FUNCTION IF EXISTS `jobdb`.`nextvalForTask` $$
CREATE FUNCTION `jobdb`.`nextvalForTask` () RETURNS bigint(20) NOT DETERMINISTIC
BEGIN
DECLARE cur_val bigint(20);
SELECT cur_value INTO cur_val FROM `jobdb`.`task_sequence` WHERE sequence_id = 1 FOR UPDATE;
UPDATE `jobdb`.`task_sequence` SET cur_value = cur_value + increment WHERE sequence_id = 1;
RETURN cur_val;
END $$
DELIMITER ;

USE `jobdb` ;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
