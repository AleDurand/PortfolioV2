-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema portfolio
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `portfolio` ;

-- -----------------------------------------------------
-- Schema portfolio
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `portfolio` DEFAULT CHARACTER SET utf8 ;
USE `portfolio` ;

-- -----------------------------------------------------
-- Table `portfolio`.`album`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `portfolio`.`album` ;

CREATE TABLE IF NOT EXISTS `portfolio`.`album` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `description` VARCHAR(45) NULL,
  `path` VARCHAR(200) NULL,
  `type` INT NULL DEFAULT 0,
  `read_only` TINYINT(1) NULL,
  `ts_create` TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP,
  `created_by` VARCHAR(50) NULL,
  `ts_update` TIMESTAMP NULL,
  `updated_by` VARCHAR(50) NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `portfolio`.`image`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `portfolio`.`image` ;

CREATE TABLE IF NOT EXISTS `portfolio`.`image` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `path` VARCHAR(200) NULL,
  `album_id` INT NOT NULL,
  `ts_create` TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP,
  `created_by` VARCHAR(50) NULL,
  `ts_update` TIMESTAMP NULL,
  `updated_by` VARCHAR(50) NULL,
  PRIMARY KEY (`id`, `album_id`),
  INDEX `fk_image_album1_idx` (`album_id` ASC),
  CONSTRAINT `fk_image_album1`
    FOREIGN KEY (`album_id`)
    REFERENCES `portfolio`.`album` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `portfolio`.`user`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `portfolio`.`user` ;

CREATE TABLE IF NOT EXISTS `portfolio`.`user` (
  `username` VARCHAR(50) NOT NULL,
  `password` VARCHAR(60) NOT NULL,
  `enabled` TINYINT(1) NOT NULL,
  PRIMARY KEY (`username`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `portfolio`.`authority`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `portfolio`.`authority` ;

CREATE TABLE IF NOT EXISTS `portfolio`.`authority` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(50) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `portfolio`.`user_has_authority`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `portfolio`.`user_has_authority` ;

CREATE TABLE IF NOT EXISTS `portfolio`.`user_has_authority` (
  `user_id` VARCHAR(50) NOT NULL,
  `authority_id` INT NOT NULL,
  PRIMARY KEY (`user_id`, `authority_id`),
  INDEX `fk_user_has_authority_authority1_idx` (`authority_id` ASC),
  INDEX `fk_user_has_authority_user_idx` (`user_id` ASC),
  CONSTRAINT `fk_user_has_authority_user`
    FOREIGN KEY (`user_id`)
    REFERENCES `portfolio`.`user` (`username`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_user_has_authority_authority1`
    FOREIGN KEY (`authority_id`)
    REFERENCES `portfolio`.`authority` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
