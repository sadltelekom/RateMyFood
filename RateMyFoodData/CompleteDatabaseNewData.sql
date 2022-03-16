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
CREATE DATABASE IF NOT EXISTS `ratemyfood` /*!40100 DEFAULT CHARACTER SET utf8mb4 */;
USE `ratemyfood`;

-- Exportiere Struktur von Tabelle ratemyfood.category
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
	(4, 'Cake', 'Sweets'),
	(5, 'Meat', 'Main');
/*!40000 ALTER TABLE `category` ENABLE KEYS */;

-- Exportiere Struktur von Tabelle ratemyfood.comments
CREATE TABLE IF NOT EXISTS `comments` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `comment` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4;

-- Exportiere Daten aus Tabelle ratemyfood.comments: ~4 rows (ungefähr)
DELETE FROM `comments`;
/*!40000 ALTER TABLE `comments` DISABLE KEYS */;
INSERT INTO `comments` (`id`, `comment`) VALUES
	(1, 'good'),
	(2, 'bad'),
	(3, 'easy'),
	(4, 'new taste'),
	(5, 'nice'),
	(6, 'special');
/*!40000 ALTER TABLE `comments` ENABLE KEYS */;

-- Exportiere Struktur von Tabelle ratemyfood.comments_has_user
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
CREATE TABLE IF NOT EXISTS `ingredients` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8mb4;

-- Exportiere Daten aus Tabelle ratemyfood.ingredients: ~4 rows (ungefähr)
DELETE FROM `ingredients`;
/*!40000 ALTER TABLE `ingredients` DISABLE KEYS */;
INSERT INTO `ingredients` (`id`, `name`) VALUES
	(1, 'Pasta'),
	(2, 'Potato'),
	(3, 'Eggs'),
	(4, 'Cheese'),
	(5, 'Flour'),
	(6, 'sugar'),
	(7, 'meat'),
	(8, 'Tomatos'),
	(9, 'Milk'),
	(10, 'Butter'),
	(11, 'Blueberry'),
	(12, 'Bacon'),
	(13, 'Onion');
/*!40000 ALTER TABLE `ingredients` ENABLE KEYS */;

-- Exportiere Struktur von Tabelle ratemyfood.picture
CREATE TABLE IF NOT EXISTS `picture` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `link` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4;

-- Exportiere Daten aus Tabelle ratemyfood.picture: ~3 rows (ungefähr)
DELETE FROM `picture`;
/*!40000 ALTER TABLE `picture` DISABLE KEYS */;
INSERT INTO `picture` (`id`, `link`) VALUES
	(1, 'dummy'),
	(2, 'dummy'),
	(3, 'dummy'),
	(4, 'dummy');
/*!40000 ALTER TABLE `picture` ENABLE KEYS */;

-- Exportiere Struktur von Tabelle ratemyfood.picture_has_user
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
CREATE TABLE IF NOT EXISTS `recipes` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(80) DEFAULT NULL,
  `time` int(11) DEFAULT NULL,
  `howto` varchar(3000) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4;

-- Exportiere Daten aus Tabelle ratemyfood.recipes: ~12 rows (ungefähr)
DELETE FROM `recipes`;
/*!40000 ALTER TABLE `recipes` DISABLE KEYS */;
INSERT INTO `recipes` (`id`, `name`, `time`, `howto`) VALUES
	(1, 'Potato soup', 20, 'Cook the bacon. Dice and then sauté it in a large stockpot until it’s nice and crispy. Then transfer the bacon to a plate lined with paper towels, and reserve a few tablespoons of leftover grease in the stockpot (discarding the rest.) Sauté the veggies. Sauté the onion and garlic in the bacon grease until softened. Then stir in some flour, to create your roux. Add broth and potatoes. Then add the stock, milk and potatoes. Cook until the potatoes until softened, being sure to stir the soup regularly so that the bottom does not burn. Add remaining ingredients. Then stir in the cheese, Greek yogurt (or sour cream), and crispy bacon bits. Taste and season the soup with salt and pepper. Serve warm. Then serve it up, loaded up with all of your favorite toppings!'),
	(2, 'Pizza Magarita', 60, 'Dissolve yeast in tepid milk or water, stir with your hands and let it do its job. You can also dissolve 1 spoon of sugar to help the process if you want.\r\nPut salt and flour in a bowl and stir.\r\nIn a large bowl (not metallic), pour water, add yeast, oil, and flour, using the other hand to knead.\r\nKeep kneading using circular movements.\r\nWhen the dough is relatively compact but still sticky, sprinkle some flour over a wooden board.\r\n\r\nAnd here begins the love waltz that gives birth to the perfect pizza.\r\nHomemade pizza dough recipe, how to make pizza dough, pizza recipe You have to feel it, massage the dough, and massage it passionately, crushing and pushing with your fists and then folding.\r\nDon’t be hurry: 15 minutes of work is needed.\r\nWhen you have finished, sprinkle the loaf with a little bit of flour and cover with a towel, which must be strictly odorless, washed with boiling water and baking soda, but never with soap. Each olfactory contamination would ruin all this good work. The same is true for drafts of cold air or sudden temperature changes.\r\nAfter 6 hours, knead the dough again and divide it into 8 loaves.\r\nCover and let stand in a warm, dry place for 6 more hours.\r\n\r\nNow the seasonings: we wrote tomato puree, but it must be real homemade tomato stuff. Tomato is the binder and must be perfect, not too dry, not too liquid, with the right balance between sweetness and acidity.\r\nSeason with salt and basil, cover, and refrigerate. When you are about to make pizza, pull it out 20 minutes before.\r\nCome on, keep up the excellent work. We are almost done! Sprinkle the board with flour and roll out the first loaf.\r\nYou can use a rolling pin if you want pizza alla Romana, thin and crunchy.\r\nHow to make pizza Margherita at home: the perfect recipe But if you want a Neapolitan pizza, put your fists in the center of the dough start pushing, using a circular motion. In this way, the air inside the dough will be pushed to the edges and with the heat, it will swell, creating the typical chubby crust.\r\nBe careful to work dough without deflating it; otherwise, it becomes a cracker…\r\nPreparation of the Margherita pizza, how to make pizza at home, tomato seasoning When pizza is stretched out, seasoned with tomato (pummarola).\r\n\r\nHomemade pizza, Margherita homemade pizza recipe perfect pizza Top with chopped mozzarella.\r\nPlace a sheet of parchment paper on a baking sheet.\r\nPut pizza Margherita on it and season with a sprinkle of extra virgin oil and bake in the preheated oven to max heat, usually around 240 degrees.\r\nAfter 10 minutes of cooking, your delicious pizza is ready.'),
	(3, 'Pasta Bolognese', 20, 'Finely chop garlic. Peel and dice onion and carrots.\r\nHeat some vegetable oil in a large frying pan and sauté onions, garlic, and carrots. Add ground beef and cook over medium-high heat until meat is browned, approx. 6 - 10 min. While cooking, break into pieces with a cooking spoon. Season with salt and pepper.\r\nAdd crushed tomatoes and season again with salt, pepper, and oregano. Simmer without a lid over medium heat for approx. 15 - 20 min.\r\nIn the meantime, cook pasta in plenty of salted boiling water according to package instructions for approx. 10 - 12 min. until al dente. Drain, top with sauce, and serve with grated Parmesan.'),
	(4, 'Tomato Soup', 15, 'Chop onion and mince garlic. Cut sundried tomatoes into small pieces. Add olive oil to a small pot over medium heat, then add onion and garlic. Cook until fragrant, approx. 2 min.\r\nAdd chopped tomatoes, sundried tomatoes, vegetable broth, and thyme to pot. Simmer for approx. 15 min. Purée until smooth. Season with salt and pepper.\r\nTransfer to a serving bowl and dollop with yogurt alternative. If you like, garnish with pumpkin seed oil. Serve with slices of whole wheat bread. Enjoy!'),
	(5, 'Fluffy buttermilk pancakes', 15, 'Whisk egg in a mixing bowl until foamy.\r\nAdd oil, maple syrup, and butter milk and continue whisking.\r\nMix flour, baking powder, and a pinch of salt in a separate bowl and then gently fold into the liquid butter milk mixture. Let stand for approx. 15 – 20 min.\r\nDrop batter into skillet and cook on medium heat until golden-brown (approx. 2 – 3 min. per side, with a diameter of approx. 7cm). Serve with homemade jam or powdered sugar.'),
	(6, 'Blueberry smoothie', 5, 'Juice the orange. Peel and quarter the banana.\r\nPut all ingredients into a blender.\r\nBlend on the highest setting. Enjoy straight away.'),
	(7, 'Königsberger Klopse (German meatballs in cream and caper sauce)', 30, 'Finely chop two thirds of the shallots, one third of the capers, and all of the anchovies. Heat some oil in a frying pan and sauté shallots for approx. 1 – 2 min. Add milk to a mixing bowl and soak bread rolls for approx. 10 Min., until soft. Then, squeeze out the bread rolls to drain excess liquid.\r\nBring a large pot filled with water to a simmer. Stud remaining shallot with bay leaf and cloves and add to pot. In a large bowl, combine ground meat, egg, soaked bread rolls, sautéed onions, mustard, chopped anchovies, and capers. Season with salt and pepper and mix everything together thoroughly. Then, form mixture into 2-in./5-cm meatballs and carefully add them to the pot. Let meatballs simmer on low heat for approx. 15 – 18 min., until done. Remove with a slotted spoon, set aside, and keep warm. Set stock aside.\r\nIn the meantime, melt butter in a pot over medium heat. Whisk in flour and deglaze with white wine and two thirds of the meatball stock. Add cream and let simmer for approx. 5 min. Add remaining capers along with a little of their brine, lemon zest and juice. Season to taste with salt, pepper and a pinch of sugar. Serve meatballs with creamy caper sauce and boiled potatoes as a side.'),
	(8, 'Pasta Carbonara', 30, 'In a large serving bowl, whisk together egg yolks, a few grinds of black pepper, and cheese. Bring large pot of generously salted water to a boil. Add spaghetti and cook until al dente, approx. 7 – 9 min., or according to package instructions. Reserve about 120 ml/ 1/2 cup pasta water before draining and setting aside.\r\nFry diced guanciale until it\'s crisp and the fat has rendered. Add peeled and crushed garlic, if desired.\r\nAdd pasta to frying pan and toss until coated with the fat, then remove the pan from the heat.\r\nTransfer pasta from the frying pan along with half of the reserved pasta water to a serving bowl with the egg mixture, mixing everything together quickly so that the eggs don’t scramble. Toss until the mixture turns creamy and thick, adding more pasta water as needed.\r\nSeason to taste with salt and black pepper and serve garnished with more cheese. Enjoy!'),
	(9, 'Sauerkraut with kassler and mashed potatoes', 30, 'Preheat oven to 80°C/175°F. Pat kassler dry with a towel. Add sauerkraut to a small saucepan with veal stock and kassler and bring to a boil. Cover and transfer to preheated oven and bake at 80°C/175°F for approx. 25 – 30 min.\r\nMeanwhile, peel potatoes and cut into large pieces. In a large saucepan, bring potatoes to a boil in salted water and cook for approx. 15 – 20 min. Drain and set aside.\r\nIn a small saucepan, slowly warm milk and butter over medium heat. Add potatoes and mash. Season to taste with salt, pepper, and nutmeg.\r\nSlice kassler into finger-width strips and serve on a plate with mashed potatoes and sauerkraut. Enjoy!');
/*!40000 ALTER TABLE `recipes` ENABLE KEYS */;

-- Exportiere Struktur von Tabelle ratemyfood.recipes_has_category
CREATE TABLE IF NOT EXISTS `recipes_has_category` (
  `recipes_id` int(10) unsigned NOT NULL,
  `category_id` int(11) NOT NULL,
  PRIMARY KEY (`recipes_id`,`category_id`),
  KEY `fk_recipes_has_category_category1_idx` (`category_id`),
  KEY `fk_recipes_has_category_recipes1_idx` (`recipes_id`),
  CONSTRAINT `fk_recipes_has_category_category1` FOREIGN KEY (`category_id`) REFERENCES `category` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_recipes_has_category_recipes1` FOREIGN KEY (`recipes_id`) REFERENCES `recipes` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- Exportiere Daten aus Tabelle ratemyfood.recipes_has_category: ~2 rows (ungefähr)
DELETE FROM `recipes_has_category`;
/*!40000 ALTER TABLE `recipes_has_category` DISABLE KEYS */;
INSERT INTO `recipes_has_category` (`recipes_id`, `category_id`) VALUES
	(1, 2),
	(2, 3),
	(3, 1),
	(4, 2),
	(5, 4),
	(6, 4),
	(7, 5),
	(8, 1),
	(9, 5);
/*!40000 ALTER TABLE `recipes_has_category` ENABLE KEYS */;

-- Exportiere Struktur von Tabelle ratemyfood.recipes_has_comments
CREATE TABLE IF NOT EXISTS `recipes_has_comments` (
  `recipes_id` int(10) unsigned NOT NULL,
  `comments_id` int(10) unsigned NOT NULL,
  PRIMARY KEY (`recipes_id`,`comments_id`),
  KEY `fk_recipes_has_comments_comments1_idx` (`comments_id`),
  KEY `fk_recipes_has_comments_recipes1_idx` (`recipes_id`),
  CONSTRAINT `fk_recipes_has_comments_comments1` FOREIGN KEY (`comments_id`) REFERENCES `comments` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_recipes_has_comments_recipes1` FOREIGN KEY (`recipes_id`) REFERENCES `recipes` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- Exportiere Daten aus Tabelle ratemyfood.recipes_has_comments: ~2 rows (ungefähr)
DELETE FROM `recipes_has_comments`;
/*!40000 ALTER TABLE `recipes_has_comments` DISABLE KEYS */;
INSERT INTO `recipes_has_comments` (`recipes_id`, `comments_id`) VALUES
	(1, 3),
	(2, 1),
	(3, 4);
/*!40000 ALTER TABLE `recipes_has_comments` ENABLE KEYS */;

-- Exportiere Struktur von Tabelle ratemyfood.recipes_has_ingredients
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

-- Exportiere Daten aus Tabelle ratemyfood.recipes_has_ingredients: ~3 rows (ungefähr)
DELETE FROM `recipes_has_ingredients`;
/*!40000 ALTER TABLE `recipes_has_ingredients` DISABLE KEYS */;
INSERT INTO `recipes_has_ingredients` (`recipes_id`, `ingredients_id`, `amount`) VALUES
	(1, 2, '500g'),
	(1, 12, '200g'),
	(1, 13, '20g'),
	(2, 4, '250g'),
	(2, 5, '500g'),
	(2, 8, '500g'),
	(3, 1, '500g');
/*!40000 ALTER TABLE `recipes_has_ingredients` ENABLE KEYS */;

-- Exportiere Struktur von Tabelle ratemyfood.recipes_has_picture
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
CREATE TABLE IF NOT EXISTS `recipes_has_rating` (
  `recipes_id` int(10) unsigned NOT NULL,
  `rating_id` int(10) unsigned NOT NULL,
  PRIMARY KEY (`recipes_id`,`rating_id`),
  KEY `fk_recipes_has_rating_rating1_idx` (`rating_id`),
  KEY `fk_recipes_has_rating_recipes1_idx` (`recipes_id`),
  CONSTRAINT `fk_recipes_has_rating_rating1` FOREIGN KEY (`rating_id`) REFERENCES `rating` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_recipes_has_rating_recipes1` FOREIGN KEY (`recipes_id`) REFERENCES `recipes` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- Exportiere Daten aus Tabelle ratemyfood.recipes_has_rating: ~2 rows (ungefähr)
DELETE FROM `recipes_has_rating`;
/*!40000 ALTER TABLE `recipes_has_rating` DISABLE KEYS */;
INSERT INTO `recipes_has_rating` (`recipes_id`, `rating_id`) VALUES
	(1, 1),
	(2, 2),
	(3, 3);
/*!40000 ALTER TABLE `recipes_has_rating` ENABLE KEYS */;

-- Exportiere Struktur von Tabelle ratemyfood.recipes_has_user
CREATE TABLE IF NOT EXISTS `recipes_has_user` (
  `recipes_id` int(10) unsigned NOT NULL,
  `user_id` int(10) unsigned NOT NULL,
  PRIMARY KEY (`recipes_id`,`user_id`),
  KEY `fk_recipes_has_user_user1_idx` (`user_id`),
  KEY `fk_recipes_has_user_recipes1_idx` (`recipes_id`),
  CONSTRAINT `fk_recipes_has_user_recipes1` FOREIGN KEY (`recipes_id`) REFERENCES `recipes` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_recipes_has_user_user1` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- Exportiere Daten aus Tabelle ratemyfood.recipes_has_user: ~2 rows (ungefähr)
DELETE FROM `recipes_has_user`;
/*!40000 ALTER TABLE `recipes_has_user` DISABLE KEYS */;
INSERT INTO `recipes_has_user` (`recipes_id`, `user_id`) VALUES
	(1, 3),
	(2, 1),
	(3, 2);
/*!40000 ALTER TABLE `recipes_has_user` ENABLE KEYS */;

-- Exportiere Struktur von Tabelle ratemyfood.user
CREATE TABLE IF NOT EXISTS `user` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(45) DEFAULT NULL,
  `realname` varchar(45) DEFAULT NULL,
  `status` varchar(45) DEFAULT NULL,
  `email` varchar(90) DEFAULT NULL,
  `password` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4;

-- Exportiere Daten aus Tabelle ratemyfood.user: ~2 rows (ungefähr)
DELETE FROM `user`;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` (`id`, `name`, `realname`, `status`, `email`, `password`) VALUES
	(1, 'Sebastian', 'Sebastian', 'PowerUser', 'seb@a.com', 'sebastian'),
	(2, 'Henry', 'Henry', 'PowerUser', 'henr@a.com', 'henry'),
	(3, 'Mathias', 'Mathias', 'User', 'mat@b.com', 'mathias');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;

-- Exportiere Struktur von Tabelle ratemyfood.user_has_rating
CREATE TABLE IF NOT EXISTS `user_has_rating` (
  `user_id` int(10) unsigned NOT NULL,
  `rating_id` int(10) unsigned NOT NULL,
  PRIMARY KEY (`user_id`,`rating_id`),
  KEY `fk_user_has_rating_rating1_idx` (`rating_id`),
  KEY `fk_user_has_rating_user1_idx` (`user_id`),
  CONSTRAINT `fk_user_has_rating_rating1` FOREIGN KEY (`rating_id`) REFERENCES `rating` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_user_has_rating_user1` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- Exportiere Daten aus Tabelle ratemyfood.user_has_rating: ~2 rows (ungefähr)
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
