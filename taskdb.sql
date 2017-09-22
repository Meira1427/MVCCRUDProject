-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema taskdb
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `taskdb` ;

-- -----------------------------------------------------
-- Schema taskdb
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `taskdb` DEFAULT CHARACTER SET utf8 ;
USE `taskdb` ;

-- -----------------------------------------------------
-- Table `category`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `category` ;

CREATE TABLE IF NOT EXISTS `category` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(30) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `task`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `task` ;

CREATE TABLE IF NOT EXISTS `task` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `item` VARCHAR(30) NOT NULL,
  `description` VARCHAR(100) NULL,
  `category_id` INT UNSIGNED NOT NULL,
  `priority` INT UNSIGNED NOT NULL,
  `image_link` VARCHAR(300) NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_category_id_category_idx` (`category_id` ASC),
  CONSTRAINT `fk_category_id_category`
    FOREIGN KEY (`category_id`)
    REFERENCES `category` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

SET SQL_MODE = '';
GRANT USAGE ON *.* TO user@localhost;
 DROP USER user@localhost;
SET SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';
CREATE USER 'user'@'localhost' IDENTIFIED BY 'taskuser';

GRANT SELECT, INSERT, TRIGGER, UPDATE, DELETE ON TABLE * TO 'user'@'localhost';

SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;

-- -----------------------------------------------------
-- Data for table `category`
-- -----------------------------------------------------
START TRANSACTION;
USE `taskdb`;
INSERT INTO `category` (`id`, `name`) VALUES (1, 'school');
INSERT INTO `category` (`id`, `name`) VALUES (2, 'work');
INSERT INTO `category` (`id`, `name`) VALUES (3, 'spiritual');
INSERT INTO `category` (`id`, `name`) VALUES (4, 'household');
INSERT INTO `category` (`id`, `name`) VALUES (5, 'health');

COMMIT;


-- -----------------------------------------------------
-- Data for table `task`
-- -----------------------------------------------------
START TRANSACTION;
USE `taskdb`;
INSERT INTO `task` (`id`, `item`, `description`, `category_id`, `priority`, `image_link`) VALUES (1, 'Personal Site', 'Finish Personal Site', 1, 5, '\'https://pbs.twimg.com/profile_images/720847195860127744/K4Ld3JCd.jpg\'');
INSERT INTO `task` (`id`, `item`, `description`, `category_id`, `priority`, `image_link`) VALUES (2, 'Laundry', 'Four laundries to do', 4, 3, '\'http://drwzpk38qkpfb.cloudfront.net/www.universityandstudentservices.com/uploaded/images/New_Temp/New_images/\'');
INSERT INTO `task` (`id`, `item`, `description`, `category_id`, `priority`, `image_link`) VALUES (3, 'Church', 'Volunteer at Church', 3, 6, '\'http://www.publicdomainpictures.net/pictures/120000/velka/church-cross-1433688789bzO.jpg\'');
INSERT INTO `task` (`id`, `item`, `description`, `category_id`, `priority`, `image_link`) VALUES (4, 'MVCCrud', 'Refactor MVCCrud to use Database', 1, 1, '\'https://cdn2.iconfinder.com/data/icons/business-office-icons/256/To-do_List-256.png\'');
INSERT INTO `task` (`id`, `item`, `description`, `category_id`, `priority`, `image_link`) VALUES (5, 'Groceries', 'Stock up for week', 4, 2, '\'http://i.telegraph.co.uk/multimedia/archive/03323/supermarket_3323456b.jpg\'');
INSERT INTO `task` (`id`, `item`, `description`, `category_id`, `priority`, `image_link`) VALUES (6, 'Chiropractor', 'goto chiropractor', 5, 4, '\'http://chiropractorbackpain.com/wp-content/uploads/2014/12/back-pain-right.png\'');
INSERT INTO `task` (`id`, `item`, `description`, `category_id`, `priority`, `image_link`) VALUES (7, 'Find Job', 'finish school; find a job', 2, 99, '\'http://idealistcareers.org/wp-content/uploads/2013/05/Dream-Job-e1368817045431.jpg\'');

COMMIT;

