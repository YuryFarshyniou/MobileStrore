-- MySQL dump 10.13  Distrib 8.0.25, for Win64 (x86_64)
--
-- Host: localhost    Database: phone_store
-- ------------------------------------------------------
-- Server version	8.0.23

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `phones`
--

DROP TABLE IF EXISTS `phones`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `phones` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `name` varchar(100) DEFAULT NULL,
  `price` double NOT NULL,
  `processor` varchar(100) NOT NULL,
  `img` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `phones_id_uindex` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=31 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `phones`
--

LOCK TABLES `phones` WRITE;
/*!40000 ALTER TABLE `phones` DISABLE KEYS */;
INSERT INTO `phones` VALUES (20,'Realme 8 Pro',500,'Snapdragon 320G','https://cdn.alzashop.com/ImgW.ashx?fd=f16&cd=REAM018b2'),(22,'Xiaomi Mi 10 Ultra',1000,'Snapdragon 865','https://www.giztop.com/media/catalog/product/cache/dc206057cdd42d7e34b9d36e347785ca/x/i/xiaomi_mi_10_ultra_silver_4.jpg'),(23,'Xiaomi Mi 11 Ultra',1200,'Snapdragon 888','https://i.gadgets360cdn.com/products/large/mi-11-ultra-db-800x800-1617265287.jpg'),(24,'Samsung galaxy s20 ultra',800,'Exynos 990','https://www.mytrendyphone.eu/images/Samsung-Galaxy-S20-Ultra-5G-Duos-128GB-Cosmic-Black-8806090311123-18022020-01-p.jpg'),(25,'Xiaomi mi 11 lite',320,'Snapdragon 732G','https://cdn.alzashop.com/ImgW.ashx?fd=f16&cd=SKXI232b3'),(26,'Poco x3 Pro',330,'Snapdragon 860','https://i01.appmifile.com/webfile/globalimg/Anna/x3-pro-gold.png'),(27,'Iphone 12 Pro Max',1300,'Apple a14 Bionic','https://www.mytrendyphone.eu/images/iPhone-12-Pro-Max-128GB-Graphite-0194252021200-25102020-1-p.jpg'),(28,'Sansung Galaxy A12',190,' Mediatek MT6765 Helio P35','https://www.mytrendyphone.eu/images/Samsung-Galaxy-A12-64GB-Black-8806090828126-25012021-01-p.jpg');
/*!40000 ALTER TABLE `phones` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `users` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `user_name` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `email` varchar(255) NOT NULL,
  `status` varchar(25) NOT NULL,
  `role` varchar(20) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `users_email_uindex` (`email`),
  UNIQUE KEY `users_userName_uindex` (`user_name`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (3,'admin','$2y$12$/84Xn4ZqijUnGpsoLQouW.5I.HMXvEKWaINhGLrMbjXOXmT0fXZAK','admin@mail.ru','ACTIVE','ADMIN'),(4,'user','$2a$10$gHcksXE5S1aBZTRh2SAMzOMHO1bmy6AhVRd7yzG7uurOwyyUObgwy','user@mail.ru','ACTIVE','USER'),(5,'Yurachel','$2y$12$8KaxhPG0XC8U1fWDReMy9eh2iy4xQ0GaLhT/7T0WBR0A0Js0j1Apq','etton@mail.ru','ACTIVE','USER'),(6,'Marsy','$2a$12$p6ZgGxJ12lxDJbDWe1aK/O9ZZ9psfLWhG9WJ7dZpNifLd6WNtjNsS','marsy@mail.com','ACTIVE','USER'),(9,'Vika','$2a$12$ZrS99BwbPz2eV.1X98pQeuHebo9kG1n3/mwJWFldVvUilHG.MhhEu','vika@gopa.ru','ACTIVE','USER');
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users_orders`
--

DROP TABLE IF EXISTS `users_orders`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `users_orders` (
  `user_id` bigint NOT NULL,
  `phone_id` bigint NOT NULL,
  KEY `phone_id` (`phone_id`),
  KEY `user_id` (`user_id`),
  CONSTRAINT `phone_id` FOREIGN KEY (`phone_id`) REFERENCES `phones` (`id`),
  CONSTRAINT `user_id` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`) ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users_orders`
--

LOCK TABLES `users_orders` WRITE;
/*!40000 ALTER TABLE `users_orders` DISABLE KEYS */;
INSERT INTO `users_orders` VALUES (4,20),(5,23),(5,20);
/*!40000 ALTER TABLE `users_orders` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-07-14 17:31:04
