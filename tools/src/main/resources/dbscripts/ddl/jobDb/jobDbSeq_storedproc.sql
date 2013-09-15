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
