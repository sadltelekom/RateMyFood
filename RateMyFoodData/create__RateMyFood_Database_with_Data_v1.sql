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

-- Exportiere Daten aus Tabelle ratemyfood.category: ~4 rows (ungefähr)
/*!40000 ALTER TABLE `category` DISABLE KEYS */;
REPLACE INTO `category` (`id`, `category`, `course`) VALUES
	(1, 'Pasta', 'Main'),
	(2, 'Soup', 'Starter'),
	(3, 'Pizza', 'Main'),
	(4, 'Cake', 'Sweets');
/*!40000 ALTER TABLE `category` ENABLE KEYS */;

-- Exportiere Daten aus Tabelle ratemyfood.comments: ~4 rows (ungefähr)
/*!40000 ALTER TABLE `comments` DISABLE KEYS */;
REPLACE INTO `comments` (`id`, `comment`) VALUES
	(1, 'good'),
	(2, 'bad'),
	(3, 'easy'),
	(4, 'new taste');
/*!40000 ALTER TABLE `comments` ENABLE KEYS */;

-- Exportiere Daten aus Tabelle ratemyfood.comments_has_user: ~2 rows (ungefähr)
/*!40000 ALTER TABLE `comments_has_user` DISABLE KEYS */;
REPLACE INTO `comments_has_user` (`comments_id`, `user_id`) VALUES
	(1, 3),
	(3, 3);
/*!40000 ALTER TABLE `comments_has_user` ENABLE KEYS */;

-- Exportiere Daten aus Tabelle ratemyfood.ingredients: ~5 rows (ungefähr)
/*!40000 ALTER TABLE `ingredients` DISABLE KEYS */;
REPLACE INTO `ingredients` (`id`, `name`) VALUES
	(1, 'Pasta'),
	(2, 'Potato'),
	(3, 'Eggs'),
	(4, 'Cheese'),
	(5, 'Flour');
/*!40000 ALTER TABLE `ingredients` ENABLE KEYS */;

-- Exportiere Daten aus Tabelle ratemyfood.picture: ~4 rows (ungefähr)
/*!40000 ALTER TABLE `picture` DISABLE KEYS */;
REPLACE INTO `picture` (`id`, `link`) VALUES
	(1, 'dummy'),
	(2, 'dummy'),
	(3, 'dummy'),
	(4, 'dummy');
/*!40000 ALTER TABLE `picture` ENABLE KEYS */;

-- Exportiere Daten aus Tabelle ratemyfood.picture_has_user: ~4 rows (ungefähr)
/*!40000 ALTER TABLE `picture_has_user` DISABLE KEYS */;
REPLACE INTO `picture_has_user` (`picture_id`, `user_id`) VALUES
	(1, 1),
	(2, 1),
	(3, 2),
	(4, 3);
/*!40000 ALTER TABLE `picture_has_user` ENABLE KEYS */;

-- Exportiere Daten aus Tabelle ratemyfood.rating: ~3 rows (ungefähr)
/*!40000 ALTER TABLE `rating` DISABLE KEYS */;
REPLACE INTO `rating` (`id`, `rating`) VALUES
	(1, 1),
	(2, 2),
	(3, 4);
/*!40000 ALTER TABLE `rating` ENABLE KEYS */;

-- Exportiere Daten aus Tabelle ratemyfood.recipes: ~3 rows (ungefähr)
/*!40000 ALTER TABLE `recipes` DISABLE KEYS */;
REPLACE INTO `recipes` (`id`, `name`, `time`, `howto`) VALUES
	(1, 'Potatosoup', 20, 'cook potato'),
	(2, 'Pizza Magatita', 30, 'make pizza'),
	(3, 'Pasta Bolognese', 30, 'cook pasta, cook meat plus tomato');
/*!40000 ALTER TABLE `recipes` ENABLE KEYS */;

-- Exportiere Daten aus Tabelle ratemyfood.recipes_has_category: ~3 rows (ungefähr)
/*!40000 ALTER TABLE `recipes_has_category` DISABLE KEYS */;
REPLACE INTO `recipes_has_category` (`recipes_id`, `category_id`) VALUES
	(1, 2),
	(2, 3),
	(3, 1);
/*!40000 ALTER TABLE `recipes_has_category` ENABLE KEYS */;

-- Exportiere Daten aus Tabelle ratemyfood.recipes_has_comments: ~3 rows (ungefähr)
/*!40000 ALTER TABLE `recipes_has_comments` DISABLE KEYS */;
REPLACE INTO `recipes_has_comments` (`recipes_id`, `comments_id`) VALUES
	(1, 3),
	(2, 1),
	(3, 4);
/*!40000 ALTER TABLE `recipes_has_comments` ENABLE KEYS */;

-- Exportiere Daten aus Tabelle ratemyfood.recipes_has_ingredients: ~4 rows (ungefähr)
/*!40000 ALTER TABLE `recipes_has_ingredients` DISABLE KEYS */;
REPLACE INTO `recipes_has_ingredients` (`recipes_id`, `ingredients_id`, `amount`) VALUES
	(1, 2, '500g'),
	(2, 4, '250g'),
	(2, 5, '500g'),
	(3, 1, '500g');
/*!40000 ALTER TABLE `recipes_has_ingredients` ENABLE KEYS */;

-- Exportiere Daten aus Tabelle ratemyfood.recipes_has_picture: ~4 rows (ungefähr)
/*!40000 ALTER TABLE `recipes_has_picture` DISABLE KEYS */;
REPLACE INTO `recipes_has_picture` (`recipes_id`, `picture_id`) VALUES
	(1, 3),
	(2, 1),
	(2, 2),
	(3, 4);
/*!40000 ALTER TABLE `recipes_has_picture` ENABLE KEYS */;

-- Exportiere Daten aus Tabelle ratemyfood.recipes_has_rating: ~3 rows (ungefähr)
/*!40000 ALTER TABLE `recipes_has_rating` DISABLE KEYS */;
REPLACE INTO `recipes_has_rating` (`recipes_id`, `rating_id`) VALUES
	(1, 1),
	(2, 2),
	(3, 3);
/*!40000 ALTER TABLE `recipes_has_rating` ENABLE KEYS */;

-- Exportiere Daten aus Tabelle ratemyfood.recipes_has_user: ~3 rows (ungefähr)
/*!40000 ALTER TABLE `recipes_has_user` DISABLE KEYS */;
REPLACE INTO `recipes_has_user` (`recipes_id`, `user_id`) VALUES
	(1, 3),
	(2, 1),
	(3, 2);
/*!40000 ALTER TABLE `recipes_has_user` ENABLE KEYS */;

-- Exportiere Daten aus Tabelle ratemyfood.user: ~3 rows (ungefähr)
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
REPLACE INTO `user` (`id`, `name`, `realname`, `status`, `email`, `password`) VALUES
	(1, 'Sebastian', 'Sebastian', 'PowerUser', 'seb@a.com', 'sebastian'),
	(2, 'Henry', 'Henry', 'PowerUser', 'henr@a.com', 'henry'),
	(3, 'Mathias', 'Mathias', 'User', 'mat@b.com', 'mathias');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;

-- Exportiere Daten aus Tabelle ratemyfood.user_has_rating: ~3 rows (ungefähr)
/*!40000 ALTER TABLE `user_has_rating` DISABLE KEYS */;
REPLACE INTO `user_has_rating` (`user_id`, `rating_id`) VALUES
	(1, 1),
	(2, 2),
	(3, 3);
/*!40000 ALTER TABLE `user_has_rating` ENABLE KEYS */;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IFNULL(@OLD_FOREIGN_KEY_CHECKS, 1) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40111 SET SQL_NOTES=IFNULL(@OLD_SQL_NOTES, 1) */;
