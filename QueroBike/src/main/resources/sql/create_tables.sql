-- -----------------------------------------------------
-- Table `customer`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `customer` ;

CREATE TABLE IF NOT EXISTS `customer` (
  `id` INTEGER PRIMARY KEY AUTOINCREMENT,
  `email` VARCHAR(128) NOT NULL,
  `password` VARCHAR(128) NOT NULL,
  `salt` VARCHAR(18) NOT NULL,
  `cpf` VARCHAR(12) NOT NULL,
  `name` VARCHAR(128) NOT NULL,
  `phone` VARCHAR(15) NULL,
  `gender` VARCHAR(64) NULL,
  `birthdate` DATE NOT NULL);

CREATE UNIQUE INDEX `email_customer_UNIQUE` ON `customer` (email);

-- -----------------------------------------------------
-- Table `city`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `city` ;

CREATE TABLE IF NOT EXISTS `city` (
  `name` VARCHAR(64) NOT NULL,
  `state` VARCHAR(2) NOT NULL);

CREATE UNIQUE INDEX `city_UNIQUE` ON `city` (name,state);


-- -----------------------------------------------------
-- Table `rental`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `rental` ;

CREATE TABLE IF NOT EXISTS `rental` (
  `id` INTEGER PRIMARY KEY AUTOINCREMENT,
  `name` VARCHAR(128) NOT NULL,
  `cnpj` VARCHAR(14) NOT NULL,
  `email` VARCHAR(128) NOT NULL,
  `logo` BLOB(65535) NULL,
  `password` VARCHAR(128) NOT NULL,
  `salt` VARCHAR(18) NOT NULL,
  `description` MEDIUMTEXT NULL,
  `postal_code` VARCHAR(8) NOT NULL,
  `street_name` VARCHAR(128) NOT NULL,
  `neighborhood` VARCHAR(128) NOT NULL,
  `complement` VARCHAR(128) NULL,
  `street_number` VARCHAR(32) NOT NULL,
  `fk_city_name` VARCHAR(64) NOT NULL,
  `fk_city_state` VARCHAR(2) NOT NULL,
    FOREIGN KEY (`fk_city_name`, `fk_city_state`)
    REFERENCES `city` (`name`,`state`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);
CREATE UNIQUE INDEX `email_rental_UNIQUE` ON `rental` (email);

-- -----------------------------------------------------
-- Table `admin`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `admin` ;

CREATE TABLE IF NOT EXISTS `admin` (
  `id` INTEGER PRIMARY KEY AUTOINCREMENT,
  `email` VARCHAR(128) NOT NULL,
  `password` VARCHAR(128) NOT NULL,
  `salt` VARCHAR(18) NOT NULL,
  `name` VARCHAR(128) NOT NULL
);
CREATE UNIQUE INDEX `email_admin_UNIQUE` ON `admin` (email);

-- -----------------------------------------------------
-- Table `reserve`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `reserve`;

CREATE TABLE IF NOT EXISTS `reserve` (
  `id` INTEGER PRIMARY KEY AUTOINCREMENT,
  `fk_customer` INTEGER,
  `fk_rental` INTEGER,
  `moment` DATETIME NOT NULL,

  FOREIGN KEY (`fk_rental`)
    REFERENCES `rental` (`id`)
    ON UPDATE NO ACTION,
  FOREIGN KEY (`fk_customer`)
    REFERENCES `customer` (`id`)
    ON UPDATE NO ACTION
);

CREATE UNIQUE INDEX `reserve_UNIQUE` ON `reserve` (fk_customer,fk_rental, moment);
