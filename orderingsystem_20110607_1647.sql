-- MySQL Administrator dump 1.4
--
-- ------------------------------------------------------
-- Server version	5.1.54-1ubuntu4


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;


--
-- Create schema orderingsystem
--

CREATE DATABASE IF NOT EXISTS orderingsystem;
USE orderingsystem;

--
-- Definition of table `orderingsystem`.`Customer`
--

DROP TABLE IF EXISTS `orderingsystem`.`Customer`;
CREATE TABLE  `orderingsystem`.`Customer` (
  `ID` int(11) NOT NULL,
  `Last_Name` varchar(45) DEFAULT NULL,
  `First_Name` varchar(45) DEFAULT NULL,
  `Middle_Name` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `orderingsystem`.`Customer`
--

/*!40000 ALTER TABLE `Customer` DISABLE KEYS */;
LOCK TABLES `Customer` WRITE;
UNLOCK TABLES;
/*!40000 ALTER TABLE `Customer` ENABLE KEYS */;


--
-- Definition of table `orderingsystem`.`Inventory`
--

DROP TABLE IF EXISTS `orderingsystem`.`Inventory`;
CREATE TABLE  `orderingsystem`.`Inventory` (
  `SKU_Number` int(11) NOT NULL,
  `Quantity` int(11) DEFAULT NULL,
  PRIMARY KEY (`SKU_Number`),
  CONSTRAINT `new_fk_constraint` FOREIGN KEY (`SKU_Number`) REFERENCES `Product` (`SKU_Number`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `orderingsystem`.`Inventory`
--

/*!40000 ALTER TABLE `Inventory` DISABLE KEYS */;
LOCK TABLES `Inventory` WRITE;
UNLOCK TABLES;
/*!40000 ALTER TABLE `Inventory` ENABLE KEYS */;


--
-- Definition of table `orderingsystem`.`Order`
--

DROP TABLE IF EXISTS `orderingsystem`.`Order`;
CREATE TABLE  `orderingsystem`.`Order` (
  `Order_Number` int(11) NOT NULL AUTO_INCREMENT,
  `Customer_ID` int(11) NOT NULL,
  `Date` date NOT NULL,
  `Status` boolean NOT NULL,
  PRIMARY KEY (`Order_Number`),
  KEY `customer_id` (`Customer_ID`),
  CONSTRAINT `customer_id` FOREIGN KEY (`Customer_ID`) REFERENCES `Customer` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `orderingsystem`.`Order`
--

/*!40000 ALTER TABLE `Order` DISABLE KEYS */;
LOCK TABLES `Order` WRITE;
UNLOCK TABLES;
/*!40000 ALTER TABLE `Order` ENABLE KEYS */;


--
-- Definition of table `orderingsystem`.`OrderItem`
--

DROP TABLE IF EXISTS `orderingsystem`.`OrderItem`;
CREATE TABLE  `orderingsystem`.`OrderItem` (
  `Order_Number` int(11) NOT NULL,
  `SKU_Number` int(11) NOT NULL,
  `Quantity` int(11) NOT NULL,
  `Price` double NOT NULL,
  PRIMARY KEY (`Order_Number`),
  KEY `Product` (`SKU_Number`),
  CONSTRAINT `Order` FOREIGN KEY (`Order_Number`) REFERENCES `Order` (`Order_Number`),
  CONSTRAINT `Product` FOREIGN KEY (`SKU_Number`) REFERENCES `Product` (`SKU_Number`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `orderingsystem`.`OrderItem`
--

/*!40000 ALTER TABLE `OrderItem` DISABLE KEYS */;
LOCK TABLES `OrderItem` WRITE;
UNLOCK TABLES;
/*!40000 ALTER TABLE `OrderItem` ENABLE KEYS */;


--
-- Definition of table `orderingsystem`.`Product`
--

DROP TABLE IF EXISTS `orderingsystem`.`Product`;
CREATE TABLE  `orderingsystem`.`Product` (
  `SKU_Number` int(11) NOT NULL,
  `Name` varchar(50) NOT NULL,
  `PRICE` double NOT NULL,
  PRIMARY KEY (`SKU_Number`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `orderingsystem`.`Product`
--

/*!40000 ALTER TABLE `Product` DISABLE KEYS */;
LOCK TABLES `Product` WRITE;
UNLOCK TABLES;
/*!40000 ALTER TABLE `Product` ENABLE KEYS */;




/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
