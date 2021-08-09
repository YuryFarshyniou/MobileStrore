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
-- Table structure for table `orders`
--

DROP TABLE IF EXISTS `orders`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `orders` (
  `id` bigint NOT NULL,
  `creation_date` date DEFAULT NULL,
  `state` varchar(255) DEFAULT NULL,
  `user_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK32ql8ubntj5uh44ph9659tiih` (`user_id`),
  CONSTRAINT `FK32ql8ubntj5uh44ph9659tiih` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `orders`
--

LOCK TABLES `orders` WRITE;
/*!40000 ALTER TABLE `orders` DISABLE KEYS */;
INSERT INTO `orders` VALUES (1,'2021-08-06','DELETED',3);
/*!40000 ALTER TABLE `orders` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `orders_phones`
--

DROP TABLE IF EXISTS `orders_phones`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `orders_phones` (
  `orders_id` bigint NOT NULL,
  `phones_id` bigint NOT NULL,
  KEY `FKerkbon3lnc91b604h54ybnvws` (`phones_id`),
  KEY `FKmnbj2ulmfjl2mewjw19g26vqk` (`orders_id`),
  CONSTRAINT `FKerkbon3lnc91b604h54ybnvws` FOREIGN KEY (`phones_id`) REFERENCES `phones` (`id`),
  CONSTRAINT `FKmnbj2ulmfjl2mewjw19g26vqk` FOREIGN KEY (`orders_id`) REFERENCES `orders` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `orders_phones`
--

LOCK TABLES `orders_phones` WRITE;
/*!40000 ALTER TABLE `orders_phones` DISABLE KEYS */;
INSERT INTO `orders_phones` VALUES (1,2);
/*!40000 ALTER TABLE `orders_phones` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `phone_images`
--

DROP TABLE IF EXISTS `phone_images`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `phone_images` (
  `phone_id` bigint NOT NULL,
  `images` varchar(255) DEFAULT NULL,
  KEY `FKsrwuvu5jn5uvpry6ha07f8p08` (`phone_id`),
  CONSTRAINT `FKsrwuvu5jn5uvpry6ha07f8p08` FOREIGN KEY (`phone_id`) REFERENCES `phones` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `phone_images`
--

LOCK TABLES `phone_images` WRITE;
/*!40000 ALTER TABLE `phone_images` DISABLE KEYS */;
INSERT INTO `phone_images` VALUES (8,'https://s.yimg.com/os/creatr-uploaded-images/2020-10/75b8c040-0d7c-11eb-bcb6-329ef15230da'),(8,'https://cdn57.androidauthority.net/wp-content/uploads/2020/12/Apple-iPhone-12-Pro-vs-iPhone-12-Max-camera-2.jpg'),(7,'https://www.notebookcheck-ru.com/uploads/tx_nbc2/4_zu_3_Teaser_Google_Pixel_4a.jpg'),(7,'https://www.ixbt.com/img/n1/news/2020/4/5/Google_Pixel_4a_XL_cover_2048x2048_large.jpg'),(6,'https://mobile-review.com/review/image/xiaomi/mi-10t-pro/pic/31.jpg'),(6,'https://www.ixbt.com/img/n1/news/2021/6/6/DSCF7380.0_large_large_large_0_large.jpg'),(5,'https://cdn.alzashop.com/ImgW.ashx?fd=f16&cd=HU3145b4'),(5,'https://www.mytrendyphone.eu/images/Huawei-P40-lite-128GB-Black-6901443375769-28042020-01-p.jpg'),(4,'https://www.techadvisor.com/cmsdata/reviews/3804862/poco_x3_pro_review_thumb800.jpg'),(4,'https://i.ytimg.com/vi/sB0suKK3vXQ/maxresdefault.jpg'),(3,'https://www.ixbt.com/img/n1/news/2021/1/5/gsmarena_002_large.jpg'),(3,'https://images.laurentwillen.be/sites/21/2021/04/samsung-galaxy-a52-test-avis-review-recensione-bewertung-analisis-top-scaled.jpg'),(2,'https://store.storeimages.cdn-apple.com/4982/as-images.apple.com/is/iphone11-select-2019-family?wid=882&hei=1058&fmt=jpeg&qlt=80&.v=1567022175704'),(2,'https://store.storeimages.cdn-apple.com/4982/as-images.apple.com/is/iphone11-yellow-select-2019?wid=834&hei=1000&fmt=jpeg&qlt=95&.v=1568141245782'),(1,'https://www.ixbt.com/img/n1/news/2021/5/3/samsung-galaxy-s21_large_large_large.jpeg');
/*!40000 ALTER TABLE `phone_images` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `phones`
--

DROP TABLE IF EXISTS `phones`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `phones` (
  `id` bigint NOT NULL,
  `date_of_added` date DEFAULT NULL,
  `img` varchar(255) DEFAULT NULL,
  `name` varchar(50) DEFAULT NULL,
  `price` double NOT NULL,
  `processor` varchar(255) DEFAULT NULL,
  `os` varchar(255) DEFAULT NULL,
  `random_access_memory` int NOT NULL,
  `screen_size` double NOT NULL,
  `number_of_main_cameras` int NOT NULL,
  `number_of_matrix_points` int NOT NULL,
  `number_of_sim_cards` int NOT NULL,
  `cpu_clock_speed` int NOT NULL,
  `graphics_accelerator` varchar(255) NOT NULL,
  `back_cover_material` varchar(255) NOT NULL,
  `body_color` varchar(255) NOT NULL,
  `body_material` varchar(255) NOT NULL,
  `accumulator_capacity` int NOT NULL,
  `accumulator_type` varchar(255) NOT NULL,
  `connection_connector` varchar(255) NOT NULL,
  `length` double NOT NULL,
  `screen_refresh_rate` int NOT NULL,
  `screen_technology` varchar(255) NOT NULL,
  `thickness` double NOT NULL,
  `weight` int NOT NULL,
  `width` double NOT NULL,
  `wifi` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `phones`
--

LOCK TABLES `phones` WRITE;
/*!40000 ALTER TABLE `phones` DISABLE KEYS */;
INSERT INTO `phones` VALUES (1,'2021-08-04','https://img.router-switch.com/media/catalog/product/cache/1/image/9df78eab33525d08d6e5fb8d27136e95/s/a/samsung-galaxy-s21-5g-sm-g9910.jpg','Samsung Galaxy S21',850,'Qualcomm Snapdragon 888','ANDROID',8,6.2,3,64,2,2840,'Adreno 660','Plastic','Purple','Metall',4000,'Li-ion','USB Type-C',151.7,120,'AMOLED',7.9,169,71.2,'5.0'),(2,'2021-08-04','https://med.csmobiles.com/192086-large_default/apple-iphone-11-128gb-rojo.jpg','Apple iPhone 11',650,'Apple A13 Bionic','IOS',4,6.1,2,12,1,2650,'Apple A13 GPU','glass','black','metall',3046,'Li-ion','lightning',150.9,60,'IPS',8.3,194,75.7,'5.0'),(3,'2021-08-04','https://alldeviceprice.com/wp-content/uploads/2021/05/Samsung-Galaxy-A52-5G.jpg','Samsung Galaxy A52',370,'Qualcomm Snapdragon 720G','ANDROID',4,6.5,4,64,2,2300,'Adreno 618','plastic','black','plastic',4500,'Li-ion','USB Type-C',159.9,90,'AMOLED',8.4,189,75.1,'5.0'),(4,'2021-08-04','https://i01.appmifile.com/webfile/globalimg/Anna/x3-pro-gold.png','POCO X3 Pro',350,'Qualcomm Snapdragon 860','ANDROID',8,6.67,4,48,2,2960,'Adreno 640','plastic','black','plastic',5160,'Li-ion','USB Type-C',165.3,120,'IPS',9.4,215,76.8,'5.0'),(5,'2021-08-04','https://www.gizmochina.com/wp-content/uploads/2019/12/Huawei-nova-6-SE-500x500.jpg','Huawei P40 lite',245,'HiSilicon Kirin 810','ANDROID',6,6.4,4,48,2,2270,'Mali-G52 MP6','Plastic','pink','plastic',4200,'Li-ion','USB Type-C',159.2,60,'IPS',8.7,183,76.3,'5.0'),(6,'2021-08-04','https://sc04.alicdn.com/kf/H802e1e5773024501863f9baedaf11d65X.jpg','Xiaomi Mi 10T',460,'Qualcomm Snapdragon 865','ANDROID',8,6.67,3,64,2,2840,'Adreno 650','glass','white','Metall',5000,'Li-ion','USB Type-C',165.1,144,'IPS',9.33,216,76.4,'5.0'),(7,'2021-08-04','https://cdn.alzashop.com/ImgW.ashx?fd=f16&cd=GPX1065b1','Google Pixel 4a',500,'Qualcomm Snapdragon 730G','ANDROID',6,5.8,1,12,1,2200,'Adreno 618','plastic','black','plastic',3140,'Li-ion','USB Type-C',144,60,'OLED',8.2,143,69.4,'5.0'),(8,'2021-08-04','https://www.mytrendyphone.eu/images/iPhone-12-Pro-Max-128GB-Graphite-0194252021200-25102020-1-p.jpg','Apple iPhone 12 Pro Max',1300,'Apple A14 Bionic','IOS',6,6.7,3,12,1,2900,'Apple A14 GPU','glass','black','metall',3687,'Li-ion','lightning',160.8,60,'OLED',7.4,228,78.1,'5.0');
/*!40000 ALTER TABLE `phones` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `users` (
  `id` bigint NOT NULL,
  `address` varchar(255) DEFAULT NULL,
  `avatar` longblob,
  `email` varchar(255) DEFAULT NULL,
  `first_name` varchar(255) DEFAULT NULL,
  `last_name` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `registration_date` date DEFAULT NULL,
  `role` varchar(255) DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  `user_name` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (2,'Belarus,Mogilev',_binary 'âPNG\r\n\Z\n\0\0\0\rIHDR\0\0∞\0\0X\0\0\0H(çY\0\0\0gAMA\0\0±è¸a\0\0\0 cHRM\0\0z&\0\0ÄÑ\0\0˙\0\0\0Ä\Ë\0\0u0\0\0\Í`\0\0:ò\0\0pú∫Q<\0\0\0PLTEˇˇˇ\Ã\0\0\ÕwºZ\0\0\0bKGD\0àH\0\0\0tIME\‚\'(¶$\0\0\0\ÃIDATx\⁄\Ì\Œ1\0\0†ı/≠˜\Ë	H\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0(Ûíñññññññññññññññññññññññññññññññññññññññññññññññññññññññññññññññññññññññññññññññññññññññññññññññññññññññññññññññññññ\÷]\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0Ä≤ã¡ùi!ìn\0\0\0%tEXtdate:create\02018-06-15T12:39:39+00:00 ™?O\0\0\0%tEXtdate:modify\02018-06-15T12:39:39+00:00Q˜áÛ\0\0\0\0IENDÆB`Ç','admin@mail.ru','Admin','ADmin','$2a$12$BHjyb52MVmQtAWRTKjxgSeJ7OFeSlJeRZxLIw.y2Tx.esVHsAMa4C','2021-07-27','ADMIN','ACTIVE','admin'),(3,'Belarus,Mogilev',_binary 'âPNG\r\n\Z\n\0\0\0\rIHDR\0\0∞\0\0X\0\0\0H(çY\0\0\0gAMA\0\0±è¸a\0\0\0 cHRM\0\0z&\0\0ÄÑ\0\0˙\0\0\0Ä\Ë\0\0u0\0\0\Í`\0\0:ò\0\0pú∫Q<\0\0\0PLTEˇˇˇ\Ã\0\0\ÕwºZ\0\0\0bKGD\0àH\0\0\0tIME\‚\'(¶$\0\0\0\ÃIDATx\⁄\Ì\Œ1\0\0†ı/≠˜\Ë	H\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0(Ûíñññññññññññññññññññññññññññññññññññññññññññññññññññññññññññññññññññññññññññññññññññññññññññññññññññññññññññññññññññ\÷]\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0Ä≤ã¡ùi!ìn\0\0\0%tEXtdate:create\02018-06-15T12:39:39+00:00 ™?O\0\0\0%tEXtdate:modify\02018-06-15T12:39:39+00:00Q˜áÛ\0\0\0\0IENDÆB`Ç','user@mail.ru','Yura','Farshyniou','$2a$12$gYG8cED3cIDcA5cLIVaoje4c5KH.LN3MRZXMcDzJdWbbhtIyirfXW','2021-07-28','USER','ACTIVE','user');
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-08-09 19:08:07
