CREATE DATABASE  IF NOT EXISTS `edited_car_park` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `edited_car_park`;
-- MySQL dump 10.13  Distrib 8.0.13, for Win64 (x86_64)
--
-- Host: localhost    Database: edited_car_park
-- ------------------------------------------------------
-- Server version	8.0.13

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
 SET NAMES utf8 ;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `assignment`
--

DROP TABLE IF EXISTS `assignment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `assignment` (
  `assignment_id` int(11) NOT NULL AUTO_INCREMENT,
  `date_start` datetime DEFAULT NULL,
  `status` varchar(45) NOT NULL DEFAULT 'assigned',
  `route_id` int(11) NOT NULL,
  `person_to_car_id` int(11) NOT NULL,
  PRIMARY KEY (`assignment_id`),
  KEY `fk_assignment_route_idx` (`route_id`),
  KEY `fk_assignment_person_to_car1_idx` (`person_to_car_id`),
  CONSTRAINT `fk_assignment_person_to_car1` FOREIGN KEY (`person_to_car_id`) REFERENCES `person_to_car` (`id`),
  CONSTRAINT `fk_assignment_route` FOREIGN KEY (`route_id`) REFERENCES `route` (`route_id`)
) ENGINE=InnoDB AUTO_INCREMENT=32 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `assignment`
--

LOCK TABLES `assignment` WRITE;
/*!40000 ALTER TABLE `assignment` DISABLE KEYS */;
INSERT INTO `assignment` VALUES (1,'2018-12-26 00:00:00','applied',1,1),(2,'2018-12-28 00:00:00','applied',2,2),(3,'2018-12-29 00:00:00','applied',1,4),(4,'2019-01-03 00:00:00','applied',1,13),(5,'2019-02-15 00:00:00','applied',3,3),(6,'2019-02-16 00:00:00','applied',3,4),(7,'2019-02-21 00:00:00','applied',4,7),(8,'2019-01-04 00:00:00','applied',2,7),(9,'2019-02-14 00:00:00','applied',3,13),(10,'2019-02-14 00:00:00','applied',3,43),(11,'2019-01-18 00:00:00','applied',1,44),(14,'2019-04-18 00:00:00','applied',4,4),(15,'2019-03-21 00:00:00','applied',5,7),(19,'2019-02-08 00:00:00','applied',4,14),(20,'2019-03-21 00:00:00','applied',4,3),(21,'2019-03-30 00:00:00','applied',3,2),(26,'2019-03-26 00:00:00','assigned',4,38),(28,'2019-03-08 00:00:00','applied',5,45),(30,'2019-01-14 00:00:00','assigned',1,43),(31,'2019-01-13 00:00:00','applied',4,55);
/*!40000 ALTER TABLE `assignment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `car`
--

DROP TABLE IF EXISTS `car`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `car` (
  `car_id` int(11) NOT NULL AUTO_INCREMENT,
  `model` varchar(100) DEFAULT NULL,
  `year` date DEFAULT NULL,
  `type` varchar(45) NOT NULL,
  PRIMARY KEY (`car_id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `car`
--

LOCK TABLES `car` WRITE;
/*!40000 ALTER TABLE `car` DISABLE KEYS */;
INSERT INTO `car` VALUES (1,'Mercedez','2013-10-28','A'),(2,'Volvo','2010-06-05','A'),(3,'Wolkswagen','2013-07-04','A'),(4,'Ford','2013-08-04','A'),(5,'Honda','2013-08-01','A'),(6,'Mercedez','2013-10-30','A'),(7,'Mercedez','2012-06-08','B'),(8,'Peugeot','2009-03-10','A');
/*!40000 ALTER TABLE `car` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `person`
--

DROP TABLE IF EXISTS `person`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `person` (
  `person_id` int(11) NOT NULL AUTO_INCREMENT,
  `login` varchar(45) DEFAULT NULL,
  `password` varchar(45) DEFAULT NULL,
  `first_name` varchar(50) DEFAULT NULL,
  `second_name` varchar(50) DEFAULT NULL,
  `role` varchar(45) DEFAULT 'UNDEFINED',
  `license` varchar(45) NOT NULL,
  PRIMARY KEY (`person_id`),
  UNIQUE KEY `person_login_uindex` (`login`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `person`
--

LOCK TABLES `person` WRITE;
/*!40000 ALTER TABLE `person` DISABLE KEYS */;
INSERT INTO `person` VALUES (1,'Admin','Admin','Slavick','Vinnitskyi','admin',''),(2,'User','User','Pasha','Solyanikov','driver','A'),(3,'yuradovgo','yuradovgo','Юра ','Довгополий','driver','B'),(4,'advanced','advanced','Юля','Котяй','driver','A'),(5,'anna123','anna123','Anna','Vinnytska','driver','A'),(6,'hoooly','hoooly','G','asf','driver','A'),(7,'rootU123','rootU123','jena','kolb','driver','A'),(8,'fostascj','fostascj','And','Sol','driver','B'),(9,'sasha123','sasha123','Sasha','Konovalets','driver','B'),(11,'edward','edward','Edward','Howard','driver','B'),(14,'mykola','mykola','Николай','Николай','driver','B'),(15,'ooomelchenko','a44n73','Oeksandr','Omelchenko','driver','B'),(16,'sstepanov','qwerty1','Степан','Степанов','driver','A'),(17,'omelchenko','a44n73','Oleksandr','Omelchenko','driver','A');
/*!40000 ALTER TABLE `person` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `person_to_car`
--

DROP TABLE IF EXISTS `person_to_car`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `person_to_car` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `fk_person_id` int(11) NOT NULL,
  `fk_car_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_person_to_car_person1_idx` (`fk_person_id`),
  KEY `fk_person_to_car_car1_idx` (`fk_car_id`),
  CONSTRAINT `fk_person_to_car_car1` FOREIGN KEY (`fk_car_id`) REFERENCES `car` (`car_id`),
  CONSTRAINT `fk_person_to_car_person1` FOREIGN KEY (`fk_person_id`) REFERENCES `person` (`person_id`)
) ENGINE=InnoDB AUTO_INCREMENT=59 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `person_to_car`
--

LOCK TABLES `person_to_car` WRITE;
/*!40000 ALTER TABLE `person_to_car` DISABLE KEYS */;
INSERT INTO `person_to_car` VALUES (1,2,1),(2,2,2),(3,2,3),(4,2,4),(5,2,5),(6,2,6),(7,3,7),(8,4,1),(9,4,2),(10,4,3),(11,4,4),(12,4,5),(13,4,6),(14,2,8),(15,4,8),(16,5,1),(17,5,2),(18,5,3),(19,5,4),(20,5,5),(21,5,6),(22,5,8),(23,6,1),(24,6,2),(25,6,3),(26,6,4),(27,6,5),(28,6,6),(29,6,8),(30,7,1),(31,7,2),(32,7,3),(33,7,4),(34,7,5),(35,7,6),(36,7,8),(37,8,7),(38,9,7),(40,11,7),(43,14,7),(44,15,7),(45,16,1),(46,16,2),(47,16,3),(48,16,4),(49,16,5),(50,16,6),(51,16,8),(52,17,1),(53,17,2),(54,17,3),(55,17,4),(56,17,5),(57,17,6),(58,17,8);
/*!40000 ALTER TABLE `person_to_car` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `route`
--

DROP TABLE IF EXISTS `route`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `route` (
  `route_id` int(11) NOT NULL AUTO_INCREMENT,
  `start` varchar(90) DEFAULT NULL,
  `finish` varchar(90) DEFAULT NULL,
  `start_ua` varchar(90) DEFAULT NULL,
  `finish_ua` varchar(90) DEFAULT NULL,
  PRIMARY KEY (`route_id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `route`
--

LOCK TABLES `route` WRITE;
/*!40000 ALTER TABLE `route` DISABLE KEYS */;
INSERT INTO `route` VALUES (1,'Kyiv','Kharkiv','Київ','Харків'),(2,'Lviv','Odessa','Львів','Одеса'),(3,'Warsaw','Ivano-Frankivsk','Варшава','Івано-Франківськ'),(4,'Vyshneve','Bila Tserkva','Вишневе','Біла Церква'),(5,'Kyiv','Minsk','Київ','Мінськ');
/*!40000 ALTER TABLE `route` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping routines for database 'edited_car_park'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-01-14  2:15:18
