-- MySQL dump 10.13  Distrib 8.0.27, for Win64 (x86_64)
--
-- Host: localhost    Database: quanlydattiec
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
-- Table structure for table `categories`
--

DROP TABLE IF EXISTS `categories`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `categories` (
  `categoryID` int NOT NULL AUTO_INCREMENT,
  `categoryName` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `Description` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  PRIMARY KEY (`categoryID`),
  UNIQUE KEY `categoryName_UNIQUE` (`categoryName`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `categories`
--

LOCK TABLES `categories` WRITE;
/*!40000 ALTER TABLE `categories` DISABLE KEYS */;
INSERT INTO `categories` VALUES (1,'Khai vị',NULL),(2,'Món chính',NULL),(3,'Món chính 2',NULL),(4,'Món chính 3',NULL),(5,'Lẩu',NULL),(6,'Tráng miệng',NULL),(7,'Nước uống',NULL);
/*!40000 ALTER TABLE `categories` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `customers`
--

DROP TABLE IF EXISTS `customers`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `customers` (
  `CustomerID` int NOT NULL AUTO_INCREMENT,
  `PhoneCustomer` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `LastName` varchar(24) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `FirstName` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `BirthDate` date DEFAULT NULL,
  `Address` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `Password` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  PRIMARY KEY (`CustomerID`)
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `customers`
--

LOCK TABLES `customers` WRITE;
/*!40000 ALTER TABLE `customers` DISABLE KEYS */;
INSERT INTO `customers` VALUES (5,'0362348623','Nguyễn Văn','Quốc','2022-01-02','','123456'),(6,'0965350366','Nguyễn Trường','Vũ','2022-01-02','','123456'),(7,'0963547256','Uyển','Nhi','2022-01-07',NULL,'123456'),(8,'0123654789','Anh','Minh','2019-01-08','','123456'),(9,'0362348624','Nguyễn Quỳnh','Giao','2022-01-02','','123456'),(10,'0386634020','Nguyễn ','Du','2000-12-01','','123456'),(11,'0123698547','Võ Văn','Hưng','2022-01-15','Hà Nội','123456'),(17,'0147896325','Mai','Fubuki','2021-05-30','26 Sơn La','123456'),(18,'0321456987','Trần Thuý','Hiền','1997-09-09','Caf Mau','123456'),(19,'1111111111','Công','Hậu','1990-01-01','HCM','123456'),(22,'0147896523','Anh','Vũ','2022-01-13','','123456qQ');
/*!40000 ALTER TABLE `customers` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Temporary view structure for view `doanhthu`
--

DROP TABLE IF EXISTS `doanhthu`;
/*!50001 DROP VIEW IF EXISTS `doanhthu`*/;
SET @saved_cs_client     = @@character_set_client;
/*!50503 SET character_set_client = utf8mb4 */;
/*!50001 CREATE VIEW `doanhthu` AS SELECT 
 1 AS `partyday`,
 1 AS `paid`,
 1 AS `tháng`,
 1 AS `năm`,
 1 AS `số lượng tiệc`,
 1 AS `CURDATE()`,
 1 AS `doanh thu`*/;
SET character_set_client = @saved_cs_client;

--
-- Table structure for table `employees`
--

DROP TABLE IF EXISTS `employees`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `employees` (
  `EmployeeID` int NOT NULL AUTO_INCREMENT,
  `PhoneEmployee` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `LastName` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `FirstName` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `BirthDate` date DEFAULT NULL,
  `Address` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `Password` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  PRIMARY KEY (`EmployeeID`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `employees`
--

LOCK TABLES `employees` WRITE;
/*!40000 ALTER TABLE `employees` DISABLE KEYS */;
INSERT INTO `employees` VALUES (1,'0367948625','Nguyễn Văn ','Tân',NULL,NULL,'123456'),(2,'0377988821','Lê Gia','Hân',NULL,NULL,'123456');
/*!40000 ALTER TABLE `employees` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `food`
--

DROP TABLE IF EXISTS `food`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `food` (
  `FoodID` int NOT NULL AUTO_INCREMENT,
  `FoodName` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `Unitprice` double DEFAULT NULL,
  `categoryID` int DEFAULT NULL,
  `Notes` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`FoodID`),
  UNIQUE KEY `FoodName_UNIQUE` (`FoodName`),
  KEY `fk_categories_dish_idx` (`categoryID`),
  CONSTRAINT `fk_categories_dish` FOREIGN KEY (`categoryID`) REFERENCES `categories` (`categoryID`)
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `food`
--

LOCK TABLES `food` WRITE;
/*!40000 ALTER TABLE `food` DISABLE KEYS */;
INSERT INTO `food` VALUES (1,'Cari gà',400,1,NULL),(4,'Khoai tây + xúc xích chiên',700,3,NULL),(5,'Salad',750,1,'ăn kèm với sốt'),(6,'Dâu tây',150,1,NULL),(8,'Nhãn thái',300,6,'không ăn hột'),(13,'Táo Xanh',100,6,'tặng kèm muối chấm'),(15,'Lâu thái',100,5,NULL),(17,'Lẩu Hải sản',200000,5,'tặng rau miễn phí'),(18,'Bò né',120000,4,''),(19,'Gà hấp bia',120000,2,'tặng 1 lon bia'),(21,'Thạch dừa',65000,7,''),(23,'Gà Ta',10000,2,'luộc chín');
/*!40000 ALTER TABLE `food` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Temporary view structure for view `foodlist`
--

DROP TABLE IF EXISTS `foodlist`;
/*!50001 DROP VIEW IF EXISTS `foodlist`*/;
SET @saved_cs_client     = @@character_set_client;
/*!50503 SET character_set_client = utf8mb4 */;
/*!50001 CREATE VIEW `foodlist` AS SELECT 
 1 AS `id`,
 1 AS `FoodID`,
 1 AS `FoodName`,
 1 AS `Unitprice`,
 1 AS `categoryID`,
 1 AS `Notes`*/;
SET character_set_client = @saved_cs_client;

--
-- Temporary view structure for view `hoadon`
--

DROP TABLE IF EXISTS `hoadon`;
/*!50001 DROP VIEW IF EXISTS `hoadon`*/;
SET @saved_cs_client     = @@character_set_client;
/*!50503 SET character_set_client = utf8mb4 */;
/*!50001 CREATE VIEW `hoadon` AS SELECT 
 1 AS `OrderID`,
 1 AS `sanhcuoiname`,
 1 AS `PartyDay`,
 1 AS `RentalPeriod`,
 1 AS `soBan`,
 1 AS `UnitPrice`,
 1 AS `Discount`*/;
SET character_set_client = @saved_cs_client;

--
-- Table structure for table `orderdetails`
--

DROP TABLE IF EXISTS `orderdetails`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `orderdetails` (
  `OrderID` int NOT NULL,
  `FoodID` int NOT NULL,
  `SanhCuoiID` int NOT NULL,
  `ServiceID` int NOT NULL,
  `PartyDay` date DEFAULT NULL,
  `RentalPeriod` varchar(45) DEFAULT NULL,
  `soBan` int DEFAULT NULL,
  `UnitPrice` double DEFAULT NULL,
  `Discount` double DEFAULT NULL,
  PRIMARY KEY (`OrderID`,`FoodID`,`SanhCuoiID`,`ServiceID`),
  KEY `fk_food_idx` (`FoodID`),
  KEY `fk_sanhcuoi_idx` (`SanhCuoiID`),
  KEY `fk_sercive_idx` (`ServiceID`),
  CONSTRAINT `fk_food` FOREIGN KEY (`FoodID`) REFERENCES `food` (`FoodID`),
  CONSTRAINT `fk_order` FOREIGN KEY (`OrderID`) REFERENCES `orders` (`OrderID`),
  CONSTRAINT `fk_sanhcuoi` FOREIGN KEY (`SanhCuoiID`) REFERENCES `sanhcuoi` (`SanhCuoiID`),
  CONSTRAINT `fk_sercive` FOREIGN KEY (`ServiceID`) REFERENCES `services` (`serviceID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `orderdetails`
--

LOCK TABLES `orderdetails` WRITE;
/*!40000 ALTER TABLE `orderdetails` DISABLE KEYS */;
INSERT INTO `orderdetails` VALUES (23,8,4,4,'2022-01-13','Sáng',70,32950,NULL),(23,13,4,4,'2022-01-13','Sáng',70,32950,NULL),(24,5,3,3,'2022-01-13','Sáng',50,48150,NULL),(24,5,3,4,'2022-01-13','Sáng',50,48150,NULL),(24,6,3,3,'2022-01-13','Sáng',50,48150,NULL),(24,6,3,4,'2022-01-13','Sáng',50,48150,NULL),(25,13,4,4,'2022-01-14','Trưa',70,11950,NULL),(26,13,2,4,'2022-01-13','Trưa',60,9650,NULL),(27,6,3,6,'2022-02-05','Tối',50,10700,NULL),(28,13,2,2,'2022-02-02','Sáng',60,9750,NULL),(28,13,2,4,'2022-02-02','Sáng',60,9750,NULL),(29,1,1,2,'2022-01-17','Tối',100,120150,NULL),(29,1,1,4,'2022-01-17','Tối',100,120150,NULL),(29,4,1,2,'2022-01-17','Tối',100,120150,NULL),(29,4,1,4,'2022-01-17','Tối',100,120150,NULL),(30,1,3,1,'2022-02-03','Trưa',50,58300,NULL),(30,1,3,2,'2022-02-03','Trưa',50,58300,NULL),(30,4,3,1,'2022-02-03','Trưa',50,58300,NULL),(30,4,3,2,'2022-02-03','Trưa',50,58300,NULL),(32,1,4,2,'2022-02-20','Trưa',70,82000,NULL),(32,4,4,2,'2022-02-20','Trưa',70,82000,NULL),(34,4,3,1,'2022-01-19','Tối',50,75800,NULL),(34,4,3,2,'2022-01-19','Tối',50,75800,NULL),(34,5,3,1,'2022-01-19','Tối',50,75800,NULL),(34,5,3,2,'2022-01-19','Tối',50,75800,NULL),(35,1,3,1,'2022-02-01','Sáng',20,17600,NULL),(36,1,4,2,'2022-01-23','Sáng',20,9500,NULL),(37,1,4,4,'2022-01-23','Tối',30,57850,NULL),(37,1,4,6,'2022-01-23','Tối',30,57850,NULL),(37,4,4,4,'2022-01-23','Tối',30,57850,NULL),(37,4,4,6,'2022-01-23','Tối',30,57850,NULL),(37,5,4,4,'2022-01-23','Tối',30,57850,NULL),(37,5,4,6,'2022-01-23','Tối',30,57850,NULL),(38,5,4,6,'2022-01-16','Trưa',20,9500,NULL);
/*!40000 ALTER TABLE `orderdetails` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `orders`
--

DROP TABLE IF EXISTS `orders`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `orders` (
  `OrderID` int NOT NULL AUTO_INCREMENT,
  `CustomerID` int DEFAULT NULL,
  `EmployeeID` int DEFAULT NULL,
  `OrderDate` date DEFAULT NULL,
  `paid` tinyint DEFAULT NULL,
  `paymentID` int DEFAULT NULL,
  PRIMARY KEY (`OrderID`),
  KEY `fk_order_employee_idx` (`EmployeeID`),
  KEY `fk_order_customer_idx` (`CustomerID`),
  KEY `fk_order_payment_idx` (`paymentID`),
  CONSTRAINT `fk_order_customer` FOREIGN KEY (`CustomerID`) REFERENCES `customers` (`CustomerID`),
  CONSTRAINT `fk_order_employee` FOREIGN KEY (`EmployeeID`) REFERENCES `employees` (`EmployeeID`),
  CONSTRAINT `fk_order_payment` FOREIGN KEY (`paymentID`) REFERENCES `paymentmethods` (`paymentID`)
) ENGINE=InnoDB AUTO_INCREMENT=39 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `orders`
--

LOCK TABLES `orders` WRITE;
/*!40000 ALTER TABLE `orders` DISABLE KEYS */;
INSERT INTO `orders` VALUES (23,5,NULL,'2022-01-13',1,1),(24,6,NULL,'2022-01-13',0,2),(25,5,NULL,'2022-01-13',1,2),(26,5,NULL,'2022-01-13',0,2),(27,5,NULL,'2022-01-13',1,2),(28,5,NULL,'2022-01-13',0,2),(29,5,NULL,'2022-01-15',1,2),(30,11,NULL,'2022-01-15',1,2),(32,8,NULL,'2022-01-15',1,2),(34,19,NULL,'2022-01-16',1,2),(35,19,NULL,'2022-01-16',0,2),(36,8,NULL,'2022-01-16',0,2),(37,17,NULL,'2022-01-16',0,2),(38,17,NULL,'2022-01-16',0,2);
/*!40000 ALTER TABLE `orders` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `paymentmethods`
--

DROP TABLE IF EXISTS `paymentmethods`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `paymentmethods` (
  `paymentID` int NOT NULL AUTO_INCREMENT,
  `paymentname` varchar(45) NOT NULL,
  `notes` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  PRIMARY KEY (`paymentID`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `paymentmethods`
--

LOCK TABLES `paymentmethods` WRITE;
/*!40000 ALTER TABLE `paymentmethods` DISABLE KEYS */;
INSERT INTO `paymentmethods` VALUES (1,'Toàn bộ',NULL),(2,'Đặt Cọc','Bạn phải thanh toán toàn bộ trước ngày tổ chức tiệc');
/*!40000 ALTER TABLE `paymentmethods` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sanhcuoi`
--

DROP TABLE IF EXISTS `sanhcuoi`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sanhcuoi` (
  `SanhCuoiID` int NOT NULL AUTO_INCREMENT,
  `SanhCuoiName` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `SoBanToiDa` int DEFAULT NULL,
  `unitPrice` double DEFAULT NULL,
  `Notes` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  PRIMARY KEY (`SanhCuoiID`),
  UNIQUE KEY `SanhCuoiName_UNIQUE` (`SanhCuoiName`)
) ENGINE=InnoDB AUTO_INCREMENT=26 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sanhcuoi`
--

LOCK TABLES `sanhcuoi` WRITE;
/*!40000 ALTER TABLE `sanhcuoi` DISABLE KEYS */;
INSERT INTO `sanhcuoi` VALUES (1,'Gia Linh',100,100,NULL),(2,'Ngọc Anh',60,60,NULL),(3,'Cát Tường',50,60,'có thể gộp sảnh khác'),(4,'Vu Quy',70,70,'');
/*!40000 ALTER TABLE `sanhcuoi` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Temporary view structure for view `servicelist`
--

DROP TABLE IF EXISTS `servicelist`;
/*!50001 DROP VIEW IF EXISTS `servicelist`*/;
SET @saved_cs_client     = @@character_set_client;
/*!50503 SET character_set_client = utf8mb4 */;
/*!50001 CREATE VIEW `servicelist` AS SELECT 
 1 AS `id`,
 1 AS `serviceID`,
 1 AS `serviceName`,
 1 AS `unitPrice`*/;
SET character_set_client = @saved_cs_client;

--
-- Table structure for table `services`
--

DROP TABLE IF EXISTS `services`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `services` (
  `serviceID` int NOT NULL AUTO_INCREMENT,
  `serviceName` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `unitPrice` double DEFAULT NULL,
  PRIMARY KEY (`serviceID`),
  UNIQUE KEY `serviceName_UNIQUE` (`serviceName`)
) ENGINE=InnoDB AUTO_INCREMENT=29 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `services`
--

LOCK TABLES `services` WRITE;
/*!40000 ALTER TABLE `services` DISABLE KEYS */;
INSERT INTO `services` VALUES (1,'MC',210),(2,'Biểu diễn văn nghệ',100),(3,'Ban nhạc',100),(4,'Pháo Sáng đôi',50),(6,'Trang trí cổng cưới',200),(8,'Chụp ảnh cưới',2500000),(10,'Thuê DJ',1000000),(17,'Xiếc',14000);
/*!40000 ALTER TABLE `services` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Final view structure for view `doanhthu`
--

/*!50001 DROP VIEW IF EXISTS `doanhthu`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8mb4 */;
/*!50001 SET character_set_results     = utf8mb4 */;
/*!50001 SET collation_connection      = utf8mb4_0900_ai_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`root`@`localhost` SQL SECURITY DEFINER */
/*!50001 VIEW `doanhthu` AS select `hoadon`.`PartyDay` AS `partyday`,`orders`.`paid` AS `paid`,month(`hoadon`.`PartyDay`) AS `tháng`,year(`hoadon`.`PartyDay`) AS `năm`,count(`hoadon`.`OrderID`) AS `số lượng tiệc`,curdate() AS `CURDATE()`,sum(`hoadon`.`UnitPrice`) AS `doanh thu` from (`hoadon` join `orders` on((`hoadon`.`OrderID` = `orders`.`OrderID`))) */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

--
-- Final view structure for view `foodlist`
--

/*!50001 DROP VIEW IF EXISTS `foodlist`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8mb4 */;
/*!50001 SET character_set_results     = utf8mb4 */;
/*!50001 SET collation_connection      = utf8mb4_0900_ai_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`root`@`localhost` SQL SECURITY DEFINER */
/*!50001 VIEW `foodlist` AS select `orderdetails`.`OrderID` AS `id`,`food`.`FoodID` AS `FoodID`,`food`.`FoodName` AS `FoodName`,`food`.`Unitprice` AS `Unitprice`,`food`.`categoryID` AS `categoryID`,`food`.`Notes` AS `Notes` from (`orderdetails` join `food` on((`orderdetails`.`FoodID` = `food`.`FoodID`))) */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

--
-- Final view structure for view `hoadon`
--

/*!50001 DROP VIEW IF EXISTS `hoadon`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8mb4 */;
/*!50001 SET character_set_results     = utf8mb4 */;
/*!50001 SET collation_connection      = utf8mb4_0900_ai_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`root`@`localhost` SQL SECURITY DEFINER */
/*!50001 VIEW `hoadon` AS select `orderdetails`.`OrderID` AS `OrderID`,`sanhcuoi`.`SanhCuoiName` AS `sanhcuoiname`,`orderdetails`.`PartyDay` AS `PartyDay`,`orderdetails`.`RentalPeriod` AS `RentalPeriod`,`orderdetails`.`soBan` AS `soBan`,`orderdetails`.`UnitPrice` AS `UnitPrice`,`orderdetails`.`Discount` AS `Discount` from (`orderdetails` join `sanhcuoi` on((`orderdetails`.`SanhCuoiID` = `sanhcuoi`.`SanhCuoiID`))) group by `orderdetails`.`OrderID` */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

--
-- Final view structure for view `servicelist`
--

/*!50001 DROP VIEW IF EXISTS `servicelist`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8mb4 */;
/*!50001 SET character_set_results     = utf8mb4 */;
/*!50001 SET collation_connection      = utf8mb4_0900_ai_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`root`@`localhost` SQL SECURITY DEFINER */
/*!50001 VIEW `servicelist` AS select `orderdetails`.`OrderID` AS `id`,`services`.`serviceID` AS `serviceID`,`services`.`serviceName` AS `serviceName`,`services`.`unitPrice` AS `unitPrice` from (`orderdetails` join `services` on((`orderdetails`.`ServiceID` = `services`.`serviceID`))) */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-01-16 22:25:30
