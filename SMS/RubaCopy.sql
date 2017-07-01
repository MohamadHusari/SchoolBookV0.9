-- MySQL dump 10.13  Distrib 5.7.17, for Win64 (x86_64)
--
-- Host: localhost    Database: sms
-- ------------------------------------------------------
-- Server version	5.7.18-log

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `class_schedule`
--

DROP TABLE IF EXISTS `class_schedule`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `class_schedule` (
  `class_id` varchar(45) NOT NULL,
  `course_id` varchar(5) NOT NULL,
  `teacher_id` int(9) NOT NULL,
  `sem_id` int(11) NOT NULL,
  PRIMARY KEY (`class_id`,`course_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `class_schedule`
--

LOCK TABLES `class_schedule` WRITE;
/*!40000 ALTER TABLE `class_schedule` DISABLE KEYS */;
INSERT INTO `class_schedule` VALUES ('A/1','02112',123456789,4),('A/1','03524',654987246,8);
/*!40000 ALTER TABLE `class_schedule` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `class_students`
--

DROP TABLE IF EXISTS `class_students`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `class_students` (
  `class_id` varchar(30) NOT NULL,
  `student_id` int(11) NOT NULL,
  `sem_id` int(11) NOT NULL,
  PRIMARY KEY (`student_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `class_students`
--

LOCK TABLES `class_students` WRITE;
/*!40000 ALTER TABLE `class_students` DISABLE KEYS */;
INSERT INTO `class_students` VALUES ('m/7',5192289,8),('A/1',789654123,2);
/*!40000 ALTER TABLE `class_students` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `courses`
--

DROP TABLE IF EXISTS `courses`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `courses` (
  `course_id` varchar(5) NOT NULL,
  `course_name` varchar(45) DEFAULT NULL,
  `teachunit_id` varchar(2) DEFAULT NULL,
  `course_studyhours` float DEFAULT NULL,
  PRIMARY KEY (`course_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `courses`
--

LOCK TABLES `courses` WRITE;
/*!40000 ALTER TABLE `courses` DISABLE KEYS */;
INSERT INTO `courses` VALUES ('01448','Algebra 1m','01',25),('02521','Hedva 1m','02',18),('02523','Hedva 2m','02',15),('08578','oolma','08',33),('08635','Klsam','08',23),('09778','lmka','09',22),('09789','sada','09',23),('09932','pplasd','09',12),('22358','sad','22',54),('22487','okmsa','22',99);
/*!40000 ALTER TABLE `courses` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `login_history`
--

DROP TABLE IF EXISTS `login_history`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `login_history` (
  `userid` int(9) NOT NULL,
  `date` datetime NOT NULL,
  PRIMARY KEY (`userid`,`date`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `login_history`
--

LOCK TABLES `login_history` WRITE;
/*!40000 ALTER TABLE `login_history` DISABLE KEYS */;
INSERT INTO `login_history` VALUES (258963147,'2017-05-21 20:00:00'),(258963147,'2017-07-11 15:00:00'),(258963147,'2017-08-11 17:00:00'),(258963147,'2017-11-11 15:55:00'),(258963147,'2017-12-21 11:00:00'),(313410631,'2017-05-21 13:00:00'),(313410631,'2017-06-11 18:00:00'),(313410631,'2017-07-01 14:06:16'),(313410631,'2017-07-01 14:07:04'),(313410631,'2017-07-01 15:24:31'),(313410631,'2017-07-01 15:33:15'),(313410631,'2017-07-01 15:34:35'),(313410631,'2017-07-01 15:37:10'),(313410631,'2017-07-01 15:40:36'),(313410631,'2017-07-01 15:41:40'),(313410631,'2017-07-01 15:42:50'),(313410631,'2017-07-01 15:48:14'),(313410631,'2017-07-01 15:50:12'),(313410631,'2017-07-01 16:15:04'),(313410631,'2017-07-11 15:00:00'),(313410631,'2017-08-11 14:00:00'),(313410631,'2017-09-21 18:00:00'),(313410631,'2017-10-11 19:00:00');
/*!40000 ALTER TABLE `login_history` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `messages`
--

DROP TABLE IF EXISTS `messages`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `messages` (
  `id` int(9) NOT NULL,
  `user_id` int(9) DEFAULT NULL,
  `msg_subject` varchar(45) DEFAULT NULL,
  `msg_context` varchar(2040) DEFAULT NULL,
  `msg_date` datetime DEFAULT NULL,
  `status` tinyint(4) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `messages`
--

LOCK TABLES `messages` WRITE;
/*!40000 ALTER TABLE `messages` DISABLE KEYS */;
/*!40000 ALTER TABLE `messages` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pre_courses`
--

DROP TABLE IF EXISTS `pre_courses`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `pre_courses` (
  `course_id` varchar(5) NOT NULL,
  `pre_course` varchar(5) NOT NULL,
  PRIMARY KEY (`course_id`,`pre_course`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pre_courses`
--

LOCK TABLES `pre_courses` WRITE;
/*!40000 ALTER TABLE `pre_courses` DISABLE KEYS */;
INSERT INTO `pre_courses` VALUES ('02523','01448'),('02523','02521');
/*!40000 ALTER TABLE `pre_courses` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `request_block`
--

DROP TABLE IF EXISTS `request_block`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `request_block` (
  `id_student` int(9) NOT NULL,
  `id_parent` int(9) NOT NULL,
  `reason` varchar(200) DEFAULT NULL,
  `status` tinyint(4) NOT NULL DEFAULT '0',
  `approve` varchar(45) NOT NULL,
  `sem_id` int(11) NOT NULL,
  PRIMARY KEY (`id_student`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `request_block`
--

LOCK TABLES `request_block` WRITE;
/*!40000 ALTER TABLE `request_block` DISABLE KEYS */;
INSERT INTO `request_block` VALUES (123456789,987654321,'decto',0,'Approved',12),(369852147,258741,'KOKO',0,'NYD',66);
/*!40000 ALTER TABLE `request_block` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `requests`
--

DROP TABLE IF EXISTS `requests`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `requests` (
  `idRequests` int(11) NOT NULL,
  `idStudent` int(11) NOT NULL,
  `idCourse` varchar(5) NOT NULL,
  `idClass` int(11) NOT NULL,
  `aprove` varchar(10) NOT NULL DEFAULT 'NYD',
  `type` varchar(45) NOT NULL,
  `sem_id` int(11) NOT NULL,
  `status` tinyint(4) NOT NULL,
  PRIMARY KEY (`idRequests`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `requests`
--

LOCK TABLES `requests` WRITE;
/*!40000 ALTER TABLE `requests` DISABLE KEYS */;
INSERT INTO `requests` VALUES (1,205350275,'02112',12,'denied','Delete',0,0),(2,205351598,'01112',15,'Accept','Add',0,0),(3,312548956,'03115',18,'NYD','Add',0,0);
/*!40000 ALTER TABLE `requests` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `semster`
--

DROP TABLE IF EXISTS `semster`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `semster` (
  `id` int(9) NOT NULL,
  `start_date` datetime DEFAULT NULL,
  `end_date` datetime DEFAULT NULL,
  `semester_letter` varchar(1) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `semster`
--

LOCK TABLES `semster` WRITE;
/*!40000 ALTER TABLE `semster` DISABLE KEYS */;
/*!40000 ALTER TABLE `semster` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `student_parent`
--

DROP TABLE IF EXISTS `student_parent`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `student_parent` (
  `student_id` int(9) NOT NULL,
  `parent_id` int(9) NOT NULL,
  PRIMARY KEY (`student_id`,`parent_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `student_parent`
--

LOCK TABLES `student_parent` WRITE;
/*!40000 ALTER TABLE `student_parent` DISABLE KEYS */;
/*!40000 ALTER TABLE `student_parent` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `studentgrades`
--

DROP TABLE IF EXISTS `studentgrades`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `studentgrades` (
  `idStudent` int(11) NOT NULL,
  `Grade` float DEFAULT NULL,
  `sem_id` varchar(45) DEFAULT NULL,
  `idCourse` varchar(45) NOT NULL,
  PRIMARY KEY (`idStudent`,`idCourse`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `studentgrades`
--

LOCK TABLES `studentgrades` WRITE;
/*!40000 ALTER TABLE `studentgrades` DISABLE KEYS */;
INSERT INTO `studentgrades` VALUES (123456789,89,'m/7','0355'),(159632587,89,'P/9','0335');
/*!40000 ALTER TABLE `studentgrades` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `teacher`
--

DROP TABLE IF EXISTS `teacher`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `teacher` (
  `user_id` int(9) NOT NULL,
  `TU_id` varchar(2) NOT NULL,
  `sem_id` int(11) NOT NULL,
  PRIMARY KEY (`user_id`,`TU_id`,`sem_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `teacher`
--

LOCK TABLES `teacher` WRITE;
/*!40000 ALTER TABLE `teacher` DISABLE KEYS */;
INSERT INTO `teacher` VALUES (2,'2',20);
/*!40000 ALTER TABLE `teacher` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `teacher_hours`
--

DROP TABLE IF EXISTS `teacher_hours`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `teacher_hours` (
  `teacher_id` int(9) NOT NULL,
  `maxhours` float NOT NULL,
  `currenthours` float unsigned NOT NULL DEFAULT '0',
  `sem_id` int(11) NOT NULL,
  PRIMARY KEY (`teacher_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `teacher_hours`
--

LOCK TABLES `teacher_hours` WRITE;
/*!40000 ALTER TABLE `teacher_hours` DISABLE KEYS */;
/*!40000 ALTER TABLE `teacher_hours` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `teacher_requests`
--

DROP TABLE IF EXISTS `teacher_requests`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `teacher_requests` (
  `idClass` varchar(45) NOT NULL,
  `sem_id` varchar(45) DEFAULT NULL,
  `idTecher` int(11) DEFAULT NULL,
  `idCourse` varchar(45) NOT NULL,
  `aprove` varchar(45) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  PRIMARY KEY (`idClass`,`idCourse`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `teacher_requests`
--

LOCK TABLES `teacher_requests` WRITE;
/*!40000 ALTER TABLE `teacher_requests` DISABLE KEYS */;
INSERT INTO `teacher_requests` VALUES ('A/2','2',147258369,'0355','NYD',0),('b/2','2',123456789,'0966','Approved',0);
/*!40000 ALTER TABLE `teacher_requests` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `teaching_unit`
--

DROP TABLE IF EXISTS `teaching_unit`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `teaching_unit` (
  `id` varchar(2) NOT NULL,
  `name` varchar(45) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `teaching_unit`
--

LOCK TABLES `teaching_unit` WRITE;
/*!40000 ALTER TABLE `teaching_unit` DISABLE KEYS */;
INSERT INTO `teaching_unit` VALUES ('01','Algebra'),('02','Mathmatic'),('08','Klamta'),('09','Osmas'),('22','Yalma');
/*!40000 ALTER TABLE `teaching_unit` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `users` (
  `id` int(9) NOT NULL,
  `name` varchar(45) DEFAULT NULL,
  `password` varchar(45) DEFAULT NULL,
  `email` varchar(45) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `access` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (2,'medo','123456','sda@gmail.com',1,3),(123456789,'Capra Obied','123456','Capra@obied.com',1,1),(205350275,'Mohamad Husari','123456','7osary@gmail.com',1,3),(205351778,'Fareed Sokar','123456','the.angle.f@gmail.com',1,5),(258963147,'tmp','123456','tmp@gmail.com',1,3),(313410631,'Ruba Madi','125','ruba@gmail.com',0,4),(315464666,'Frodo Bagins','123456','frodo@shire.com',1,1),(654897123,'Sam Wisiel','123456','Sam@wisiel.com',1,1);
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

-- Dump completed on 2017-07-01 18:00:37
