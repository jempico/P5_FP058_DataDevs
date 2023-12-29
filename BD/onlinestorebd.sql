-- MySQL Workbench Forward Engineering
-- Store original values
SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS;
SET @OLD_SQL_MODE=@@SQL_MODE;

-- Disable checks and modes during schema creation
SET UNIQUE_CHECKS=0;
SET FOREIGN_KEY_CHECKS=0;
SET SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Schema onlinestore
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema onlinestore
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `onlinestore` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci ;
USE `onlinestore` ;

-- -----------------------------------------------------
-- Table `onlinestore`.`articulos`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `onlinestore`.`articulos` (
  `id_articulo` INT NOT NULL AUTO_INCREMENT,
  `descripcion` VARCHAR(100) NULL DEFAULT NULL,
  `pvp` FLOAT NULL DEFAULT NULL,
  `gastosenvio` FLOAT NULL DEFAULT NULL,
  `preparacion` INT NULL DEFAULT NULL,
  PRIMARY KEY (`id_articulo`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `onlinestore`.`clientes`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `onlinestore`.`clientes` (
  `nif` VARCHAR(50) NOT NULL,
  `nombre` VARCHAR(50) NULL DEFAULT NULL,
  `domicilio` VARCHAR(100) NULL DEFAULT NULL,
  `email` VARCHAR(100) NULL DEFAULT NULL,
  `tipoCliente` VARCHAR(45) NULL DEFAULT NULL,
  `calcAnual` FLOAT NULL DEFAULT NULL,
  `descuentoEnv` FLOAT NULL DEFAULT NULL,
  PRIMARY KEY (`nif`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `onlinestore`.`pedidos`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `onlinestore`.`pedidos` (
  `id_pedido` INT NOT NULL AUTO_INCREMENT,
  `cantidad` INT NULL DEFAULT NULL,
  `fecha` DATE NULL DEFAULT NULL,
  `id_cliente` VARCHAR(50) NULL DEFAULT NULL,
  `id_articulo` INT NULL DEFAULT NULL,
  PRIMARY KEY (`id_pedido`),
  INDEX `fk2_Pedidos_Clientes` (`id_cliente` ASC) VISIBLE,
  INDEX `fk1_Pedidos_Articulos` (`id_articulo` ASC) VISIBLE,
  CONSTRAINT `fk1_Pedidos_Articulos`
    FOREIGN KEY (`id_articulo`)
    REFERENCES `onlinestore`.`articulos` (`id_articulo`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;