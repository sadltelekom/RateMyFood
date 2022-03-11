-- --------------------------------------------------------
-- Host:                         127.0.0.1
-- Server Version:               10.6.5-MariaDB - mariadb.org binary distribution
-- Server Betriebssystem:        Win64
-- HeidiSQL Version:             11.3.0.6295
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


-- Exportiere Datenbank Struktur für ratemyfood
DROP DATABASE IF EXISTS `ratemyfood`;
CREATE DATABASE IF NOT EXISTS `ratemyfood` /*!40100 DEFAULT CHARACTER SET utf8mb4 */;
USE `ratemyfood`;

-- Exportiere Struktur von Tabelle ratemyfood.category
DROP TABLE IF EXISTS `category`;
CREATE TABLE IF NOT EXISTS `category` (
  `id` int(11) NOT NULL,
  `category` varchar(45) DEFAULT NULL,
  `course` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- Exportiere Daten aus Tabelle ratemyfood.category: ~4 rows (ungefähr)
DELETE FROM `category`;
/*!40000 ALTER TABLE `category` DISABLE KEYS */;
INSERT INTO `category` (`id`, `category`, `course`) VALUES
	(1, 'Pasta', 'Main'),
	(2, 'Soup', 'Starter'),
	(3, 'Pizza', 'Main'),
	(4, 'Cake', 'Sweets');
/*!40000 ALTER TABLE `category` ENABLE KEYS */;

-- Exportiere Struktur von Tabelle ratemyfood.comments
DROP TABLE IF EXISTS `comments`;
CREATE TABLE IF NOT EXISTS `comments` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `comment` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4;

-- Exportiere Daten aus Tabelle ratemyfood.comments: ~4 rows (ungefähr)
DELETE FROM `comments`;
/*!40000 ALTER TABLE `comments` DISABLE KEYS */;
INSERT INTO `comments` (`id`, `comment`) VALUES
	(1, 'good'),
	(2, 'bad'),
	(3, 'easy'),
	(4, 'new taste');
/*!40000 ALTER TABLE `comments` ENABLE KEYS */;

-- Exportiere Struktur von Tabelle ratemyfood.comments_has_user
DROP TABLE IF EXISTS `comments_has_user`;
CREATE TABLE IF NOT EXISTS `comments_has_user` (
  `comments_id` int(10) unsigned NOT NULL,
  `user_id` int(10) unsigned NOT NULL,
  PRIMARY KEY (`comments_id`,`user_id`),
  KEY `fk_comments_has_user_user1_idx` (`user_id`),
  KEY `fk_comments_has_user_comments1_idx` (`comments_id`),
  CONSTRAINT `fk_comments_has_user_comments1` FOREIGN KEY (`comments_id`) REFERENCES `comments` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_comments_has_user_user1` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- Exportiere Daten aus Tabelle ratemyfood.comments_has_user: ~2 rows (ungefähr)
DELETE FROM `comments_has_user`;
/*!40000 ALTER TABLE `comments_has_user` DISABLE KEYS */;
INSERT INTO `comments_has_user` (`comments_id`, `user_id`) VALUES
	(1, 3),
	(3, 3);
/*!40000 ALTER TABLE `comments_has_user` ENABLE KEYS */;

-- Exportiere Struktur von Tabelle ratemyfood.ingredients
DROP TABLE IF EXISTS `ingredients`;
CREATE TABLE IF NOT EXISTS `ingredients` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4;

-- Exportiere Daten aus Tabelle ratemyfood.ingredients: ~5 rows (ungefähr)
DELETE FROM `ingredients`;
/*!40000 ALTER TABLE `ingredients` DISABLE KEYS */;
INSERT INTO `ingredients` (`id`, `name`) VALUES
	(1, 'Pasta'),
	(2, 'Potato'),
	(3, 'Eggs'),
	(4, 'Cheese'),
	(5, 'Flour');
/*!40000 ALTER TABLE `ingredients` ENABLE KEYS */;

-- Exportiere Struktur von Tabelle ratemyfood.picture
DROP TABLE IF EXISTS `picture`;
CREATE TABLE IF NOT EXISTS `picture` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `link` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4;

-- Exportiere Daten aus Tabelle ratemyfood.picture: ~4 rows (ungefähr)
DELETE FROM `picture`;
/*!40000 ALTER TABLE `picture` DISABLE KEYS */;
INSERT INTO `picture` (`id`, `link`) VALUES
	(1, 'dummy'),
	(2, 'dummy'),
	(3, 'dummy'),
	(4, 'dummy');
/*!40000 ALTER TABLE `picture` ENABLE KEYS */;

-- Exportiere Struktur von Tabelle ratemyfood.picture_has_user
DROP TABLE IF EXISTS `picture_has_user`;
CREATE TABLE IF NOT EXISTS `picture_has_user` (
  `picture_id` int(10) unsigned NOT NULL,
  `user_id` int(10) unsigned NOT NULL,
  PRIMARY KEY (`picture_id`,`user_id`),
  KEY `fk_picture_has_user_user1_idx` (`user_id`),
  KEY `fk_picture_has_user_picture1_idx` (`picture_id`),
  CONSTRAINT `fk_picture_has_user_picture1` FOREIGN KEY (`picture_id`) REFERENCES `picture` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_picture_has_user_user1` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- Exportiere Daten aus Tabelle ratemyfood.picture_has_user: ~4 rows (ungefähr)
DELETE FROM `picture_has_user`;
/*!40000 ALTER TABLE `picture_has_user` DISABLE KEYS */;
INSERT INTO `picture_has_user` (`picture_id`, `user_id`) VALUES
	(1, 1),
	(2, 1),
	(3, 2),
	(4, 3);
/*!40000 ALTER TABLE `picture_has_user` ENABLE KEYS */;

-- Exportiere Struktur von Tabelle ratemyfood.rating
DROP TABLE IF EXISTS `rating`;
CREATE TABLE IF NOT EXISTS `rating` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `rating` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4;

-- Exportiere Daten aus Tabelle ratemyfood.rating: ~3 rows (ungefähr)
DELETE FROM `rating`;
/*!40000 ALTER TABLE `rating` DISABLE KEYS */;
INSERT INTO `rating` (`id`, `rating`) VALUES
	(1, 1),
	(2, 2),
	(3, 4);
/*!40000 ALTER TABLE `rating` ENABLE KEYS */;

-- Exportiere Struktur von Tabelle ratemyfood.recipes
DROP TABLE IF EXISTS `recipes`;
CREATE TABLE IF NOT EXISTS `recipes` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(45) DEFAULT NULL,
  `time` int(11) DEFAULT NULL,
  `howto` varchar(1000) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4;

-- Exportiere Daten aus Tabelle ratemyfood.recipes: ~3 rows (ungefähr)
DELETE FROM `recipes`;
/*!40000 ALTER TABLE `recipes` DISABLE KEYS */;
INSERT INTO `recipes` (`id`, `name`, `time`, `howto`) VALUES
	(1, 'Potatosoup', 20, 'cook potato'),
	(2, 'Pizza Magatita', 30, 'make pizza'),
	(3, 'Pasta Bolognese', 30, 'cook pasta, cook meat plus tomato');
/*!40000 ALTER TABLE `recipes` ENABLE KEYS */;

-- Exportiere Struktur von Tabelle ratemyfood.recipes_has_category
DROP TABLE IF EXISTS `recipes_has_category`;
CREATE TABLE IF NOT EXISTS `recipes_has_category` (
  `recipes_id` int(10) unsigned NOT NULL,
  `category_id` int(11) NOT NULL,
  PRIMARY KEY (`recipes_id`,`category_id`),
  KEY `fk_recipes_has_category_category1_idx` (`category_id`),
  KEY `fk_recipes_has_category_recipes1_idx` (`recipes_id`),
  CONSTRAINT `fk_recipes_has_category_category1` FOREIGN KEY (`category_id`) REFERENCES `category` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_recipes_has_category_recipes1` FOREIGN KEY (`recipes_id`) REFERENCES `recipes` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- Exportiere Daten aus Tabelle ratemyfood.recipes_has_category: ~3 rows (ungefähr)
DELETE FROM `recipes_has_category`;
/*!40000 ALTER TABLE `recipes_has_category` DISABLE KEYS */;
INSERT INTO `recipes_has_category` (`recipes_id`, `category_id`) VALUES
	(1, 2),
	(2, 3),
	(3, 1);
/*!40000 ALTER TABLE `recipes_has_category` ENABLE KEYS */;

-- Exportiere Struktur von Tabelle ratemyfood.recipes_has_comments
DROP TABLE IF EXISTS `recipes_has_comments`;
CREATE TABLE IF NOT EXISTS `recipes_has_comments` (
  `recipes_id` int(10) unsigned NOT NULL,
  `comments_id` int(10) unsigned NOT NULL,
  PRIMARY KEY (`recipes_id`,`comments_id`),
  KEY `fk_recipes_has_comments_comments1_idx` (`comments_id`),
  KEY `fk_recipes_has_comments_recipes1_idx` (`recipes_id`),
  CONSTRAINT `fk_recipes_has_comments_comments1` FOREIGN KEY (`comments_id`) REFERENCES `comments` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_recipes_has_comments_recipes1` FOREIGN KEY (`recipes_id`) REFERENCES `recipes` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- Exportiere Daten aus Tabelle ratemyfood.recipes_has_comments: ~3 rows (ungefähr)
DELETE FROM `recipes_has_comments`;
/*!40000 ALTER TABLE `recipes_has_comments` DISABLE KEYS */;
INSERT INTO `recipes_has_comments` (`recipes_id`, `comments_id`) VALUES
	(1, 3),
	(2, 1),
	(3, 4);
/*!40000 ALTER TABLE `recipes_has_comments` ENABLE KEYS */;

-- Exportiere Struktur von Tabelle ratemyfood.recipes_has_ingredients
DROP TABLE IF EXISTS `recipes_has_ingredients`;
CREATE TABLE IF NOT EXISTS `recipes_has_ingredients` (
  `recipes_id` int(10) unsigned NOT NULL,
  `ingredients_id` int(10) unsigned NOT NULL,
  `amount` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`recipes_id`,`ingredients_id`),
  KEY `fk_recipes_has_ingredients_ingredients1_idx` (`ingredients_id`),
  KEY `fk_recipes_has_ingredients_recipes_idx` (`recipes_id`),
  CONSTRAINT `fk_recipes_has_ingredients_ingredients1` FOREIGN KEY (`ingredients_id`) REFERENCES `ingredients` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_recipes_has_ingredients_recipes` FOREIGN KEY (`recipes_id`) REFERENCES `recipes` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- Exportiere Daten aus Tabelle ratemyfood.recipes_has_ingredients: ~4 rows (ungefähr)
DELETE FROM `recipes_has_ingredients`;
/*!40000 ALTER TABLE `recipes_has_ingredients` DISABLE KEYS */;
INSERT INTO `recipes_has_ingredients` (`recipes_id`, `ingredients_id`, `amount`) VALUES
	(1, 2, '500g'),
	(2, 4, '250g'),
	(2, 5, '500g'),
	(3, 1, '500g');
/*!40000 ALTER TABLE `recipes_has_ingredients` ENABLE KEYS */;

-- Exportiere Struktur von Tabelle ratemyfood.recipes_has_picture
DROP TABLE IF EXISTS `recipes_has_picture`;
CREATE TABLE IF NOT EXISTS `recipes_has_picture` (
  `recipes_id` int(10) unsigned NOT NULL,
  `picture_id` int(10) unsigned NOT NULL,
  PRIMARY KEY (`recipes_id`,`picture_id`),
  KEY `fk_recipes_has_picture_picture1_idx` (`picture_id`),
  KEY `fk_recipes_has_picture_recipes1_idx` (`recipes_id`),
  CONSTRAINT `fk_recipes_has_picture_picture1` FOREIGN KEY (`picture_id`) REFERENCES `picture` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_recipes_has_picture_recipes1` FOREIGN KEY (`recipes_id`) REFERENCES `recipes` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- Exportiere Daten aus Tabelle ratemyfood.recipes_has_picture: ~4 rows (ungefähr)
DELETE FROM `recipes_has_picture`;
/*!40000 ALTER TABLE `recipes_has_picture` DISABLE KEYS */;
INSERT INTO `recipes_has_picture` (`recipes_id`, `picture_id`) VALUES
	(1, 3),
	(2, 1),
	(2, 2),
	(3, 4);
/*!40000 ALTER TABLE `recipes_has_picture` ENABLE KEYS */;

-- Exportiere Struktur von Tabelle ratemyfood.recipes_has_rating
DROP TABLE IF EXISTS `recipes_has_rating`;
CREATE TABLE IF NOT EXISTS `recipes_has_rating` (
  `recipes_id` int(10) unsigned NOT NULL,
  `rating_id` int(10) unsigned NOT NULL,
  PRIMARY KEY (`recipes_id`,`rating_id`),
  KEY `fk_recipes_has_rating_rating1_idx` (`rating_id`),
  KEY `fk_recipes_has_rating_recipes1_idx` (`recipes_id`),
  CONSTRAINT `fk_recipes_has_rating_rating1` FOREIGN KEY (`rating_id`) REFERENCES `rating` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_recipes_has_rating_recipes1` FOREIGN KEY (`recipes_id`) REFERENCES `recipes` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- Exportiere Daten aus Tabelle ratemyfood.recipes_has_rating: ~3 rows (ungefähr)
DELETE FROM `recipes_has_rating`;
/*!40000 ALTER TABLE `recipes_has_rating` DISABLE KEYS */;
INSERT INTO `recipes_has_rating` (`recipes_id`, `rating_id`) VALUES
	(1, 1),
	(2, 2),
	(3, 3);
/*!40000 ALTER TABLE `recipes_has_rating` ENABLE KEYS */;

-- Exportiere Struktur von Tabelle ratemyfood.recipes_has_user
DROP TABLE IF EXISTS `recipes_has_user`;
CREATE TABLE IF NOT EXISTS `recipes_has_user` (
  `recipes_id` int(10) unsigned NOT NULL,
  `user_id` int(10) unsigned NOT NULL,
  PRIMARY KEY (`recipes_id`,`user_id`),
  KEY `fk_recipes_has_user_user1_idx` (`user_id`),
  KEY `fk_recipes_has_user_recipes1_idx` (`recipes_id`),
  CONSTRAINT `fk_recipes_has_user_recipes1` FOREIGN KEY (`recipes_id`) REFERENCES `recipes` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_recipes_has_user_user1` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- Exportiere Daten aus Tabelle ratemyfood.recipes_has_user: ~3 rows (ungefähr)
DELETE FROM `recipes_has_user`;
/*!40000 ALTER TABLE `recipes_has_user` DISABLE KEYS */;
INSERT INTO `recipes_has_user` (`recipes_id`, `user_id`) VALUES
	(1, 3),
	(2, 1),
	(3, 2);
/*!40000 ALTER TABLE `recipes_has_user` ENABLE KEYS */;

-- Exportiere Struktur von Tabelle ratemyfood.user
DROP TABLE IF EXISTS `user`;
CREATE TABLE IF NOT EXISTS `user` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(45) DEFAULT NULL,
  `realname` varchar(45) DEFAULT NULL,
  `status` varchar(45) DEFAULT NULL,
  `email` varchar(90) DEFAULT NULL,
  `password` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4;

-- Exportiere Daten aus Tabelle ratemyfood.user: ~3 rows (ungefähr)
DELETE FROM `user`;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` (`id`, `name`, `realname`, `status`, `email`, `password`) VALUES
	(1, 'Sebastian', 'Sebastian', 'PowerUser', 'seb@a.com', 'sebastian'),
	(2, 'Henry', 'Henry', 'PowerUser', 'henr@a.com', 'henry'),
	(3, 'Mathias', 'Mathias', 'User', 'mat@b.com', 'mathias');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;

-- Exportiere Struktur von Tabelle ratemyfood.user_has_rating
DROP TABLE IF EXISTS `user_has_rating`;
CREATE TABLE IF NOT EXISTS `user_has_rating` (
  `user_id` int(10) unsigned NOT NULL,
  `rating_id` int(10) unsigned NOT NULL,
  PRIMARY KEY (`user_id`,`rating_id`),
  KEY `fk_user_has_rating_rating1_idx` (`rating_id`),
  KEY `fk_user_has_rating_user1_idx` (`user_id`),
  CONSTRAINT `fk_user_has_rating_rating1` FOREIGN KEY (`rating_id`) REFERENCES `rating` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_user_has_rating_user1` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- Exportiere Daten aus Tabelle ratemyfood.user_has_rating: ~3 rows (ungefähr)
DELETE FROM `user_has_rating`;
/*!40000 ALTER TABLE `user_has_rating` DISABLE KEYS */;
INSERT INTO `user_has_rating` (`user_id`, `rating_id`) VALUES
	(1, 1),
	(2, 2),
	(3, 3);
/*!40000 ALTER TABLE `user_has_rating` ENABLE KEYS */;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IFNULL(@OLD_FOREIGN_KEY_CHECKS, 1) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40111 SET SQL_NOTES=IFNULL(@OLD_SQL_NOTES, 1) */;
