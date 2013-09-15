DELIMITER $$
DROP FUNCTION IF EXISTS `invdb`.`nextvalForProduct` $$
CREATE FUNCTION `invdb`.`nextvalForProduct` () RETURNS bigint(20) NOT DETERMINISTIC
BEGIN
DECLARE cur_val bigint(20);
SELECT cur_value INTO cur_val FROM `invdb`.`product_sequence` WHERE sequence_id = 1 FOR UPDATE;
UPDATE `invdb`.`product_sequence` SET cur_value = cur_value + increment WHERE sequence_id = 1;
RETURN cur_val;
END $$
DELIMITER ;



DELIMITER $$
DROP FUNCTION IF EXISTS `invdb`.`nextvalForProductVariation` $$
CREATE FUNCTION `invdb`.`nextvalForProductVariation` () RETURNS bigint(20) NOT DETERMINISTIC
BEGIN
DECLARE cur_val bigint(20);
SELECT cur_value INTO cur_val FROM `invdb`.`product_variation_sequence` WHERE sequence_id = 1 FOR UPDATE;
UPDATE `invdb`.`product_variation_sequence` SET cur_value = cur_value + increment WHERE sequence_id = 1;
RETURN cur_val;
END $$
DELIMITER ;


DELIMITER $$
DROP FUNCTION IF EXISTS `invdb`.`nextvalForListing` $$
CREATE FUNCTION `invdb`.`nextvalForListing` () RETURNS bigint(20) NOT DETERMINISTIC
BEGIN
DECLARE cur_val bigint(20);
SELECT cur_value INTO cur_val FROM `invdb`.`listing_sequence` WHERE sequence_id = 1 FOR UPDATE;
UPDATE `invdb`.`listing_sequence` SET cur_value = cur_value + increment WHERE sequence_id = 1;
RETURN cur_val;
END $$
DELIMITER ;




DELIMITER $$
DROP FUNCTION IF EXISTS `invdb`.`nextvalForListingVariation` $$
CREATE FUNCTION `invdb`.`nextvalForListingVariation` () RETURNS bigint(20) NOT DETERMINISTIC
BEGIN
DECLARE cur_val bigint(20);
SELECT cur_value INTO cur_val FROM `invdb`.`listing_variation_sequence` WHERE sequence_id = 1 FOR UPDATE;
UPDATE `invdb`.`listing_variation_sequence` SET cur_value = cur_value + increment WHERE sequence_id = 1;
RETURN cur_val;
END $$
DELIMITER ;
