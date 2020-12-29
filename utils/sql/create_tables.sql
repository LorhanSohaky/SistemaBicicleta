-- -----------------------------------------------------
-- Table `city`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `city` ;

CREATE TABLE IF NOT EXISTS `city` (
  `id` INTEGER PRIMARY KEY AUTOINCREMENT,
  `name` VARCHAR(64) NOT NULL,
  `state` VARCHAR(2) NOT NULL);

CREATE UNIQUE INDEX `city_UNIQUE` ON `city` (name,state);