-- MySQL dump 10.13  Distrib 8.0.28, for Win64 (x86_64)
--
-- Host: localhost    Database: reflexologia
-- ------------------------------------------------------
-- Server version	8.0.27

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
-- Table structure for table `paciente`
--

DROP TABLE IF EXISTS `paciente`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `paciente` (
  `codigo` int NOT NULL,
  `nombre` varchar(100) NOT NULL,
  `dnice` int NOT NULL,
  `fecha_nacimiento` date NOT NULL,
  `sexo` char(1) DEFAULT NULL,
  `direccion` varchar(100) DEFAULT NULL,
  `dpto` varchar(100) DEFAULT NULL,
  `prov` varchar(100) DEFAULT NULL,
  `dist` varchar(100) DEFAULT NULL,
  `especial` tinyint(1) NOT NULL,
  `testimonio` varchar(50) DEFAULT NULL,
  `resultado` varchar(100) DEFAULT NULL,
  `observacion` varchar(500) DEFAULT NULL,
  `ocupacion` varchar(50) DEFAULT NULL,
  `telefono` int DEFAULT NULL,
  `email` varchar(64) DEFAULT NULL,
  PRIMARY KEY (`codigo`),
  KEY `codigo` (`codigo`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `paciente`
--

LOCK TABLES `paciente` WRITE;
/*!40000 ALTER TABLE `paciente` DISABLE KEYS */;
INSERT INTO `paciente` VALUES (1,'Leonardo Dario Loaiza Sighuas',73736380,'1998-10-15','M','PSJ. Marin 111 URB. San Juan','Lima','Lima','Chorrillos',0,'No','No','No','Estudiante',954717642,'ldls1998@gmail.com'),(2,'Adriana Balceda',12345678,'1999-05-21','F','Nose','Lima','Lima','Nose',1,'Si','Si','No','Estudiante',123456789,'nose@gmail.com'),(4,'NOMBRE 2',12345679,'2019-01-10','M','---','---','---','---',1,'---','---','---','---',123456789,'---'),(123,'NO',1234567,'2023-01-01','M','asdasd','asdasd','asdasd','asasd',1,'asd','sad','asd','sad',123123,'asd');
/*!40000 ALTER TABLE `paciente` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-02-03 19:29:23
