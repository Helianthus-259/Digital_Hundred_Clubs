-- MySQL dump 10.13  Distrib 8.0.34, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: digitalclub
-- ------------------------------------------------------
-- Server version	8.1.0

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
-- Table structure for table `activity`
--

DROP TABLE IF EXISTS `activity`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `activity` (
  `activity_id` int NOT NULL AUTO_INCREMENT,
  `club_id` int DEFAULT NULL,
  `activity_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `activity_publish_time` datetime DEFAULT CURRENT_TIMESTAMP,
  `activity_start_time` datetime NOT NULL,
  `activity_end_time` datetime NOT NULL,
  `activity_location` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `activity_introduction` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci,
  `application_form_attachment` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `activity_status` int DEFAULT NULL,
  `activity_effect` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci,
  `image_url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `opinion` text,
  PRIMARY KEY (`activity_id`) USING BTREE,
  KEY `club_id` (`club_id`) USING BTREE,
  CONSTRAINT `activity_ibfk_1` FOREIGN KEY (`club_id`) REFERENCES `club` (`club_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci ROW_FORMAT=DYNAMIC;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `activity`
--

LOCK TABLES `activity` WRITE;
/*!40000 ALTER TABLE `activity` DISABLE KEYS */;
INSERT INTO `activity` VALUES (1,1,'比赛','2024-06-08 18:38:40','2024-06-10 18:38:42','2024-06-11 18:38:44','体育馆','比赛',NULL,NULL,NULL,NULL,NULL),(2,2,'比赛','2024-06-08 18:38:40','2024-06-08 18:38:42','2024-06-08 18:38:44','体育馆','比赛',NULL,NULL,NULL,NULL,NULL),(3,3,'比赛','2024-06-08 18:38:40','2024-06-08 18:38:42','2024-06-08 18:38:44','体育馆','比赛',NULL,NULL,NULL,NULL,NULL),(4,4,'比赛','2024-06-08 18:38:40','2024-06-08 18:38:42','2024-06-08 18:38:44','体育馆','比赛',NULL,NULL,NULL,NULL,NULL),(5,5,'比赛','2024-06-08 18:38:40','2024-06-08 18:38:42','2024-06-08 18:38:44','体育馆','比赛',NULL,NULL,NULL,NULL,NULL),(6,1,'比赛','2024-06-08 18:38:40','2024-06-13 18:38:42','2024-06-15 18:38:44','体育馆','比赛',NULL,NULL,NULL,NULL,NULL),(7,1,'撒谎大数据肯德基','2024-06-10 16:04:57','2024-06-10 16:04:56','2024-06-10 16:04:56','体育馆','<p>打撒大厦十九可能就昆卡省的</p>','',NULL,NULL,'',NULL);
/*!40000 ALTER TABLE `activity` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `activitymember`
--

DROP TABLE IF EXISTS `activitymember`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `activitymember` (
  `activity_member_id` int NOT NULL AUTO_INCREMENT,
  `student_id` int DEFAULT NULL,
  `activity_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `activity_id` int DEFAULT NULL,
  `personal_effect` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci,
  `award_wining_time` datetime DEFAULT NULL,
  PRIMARY KEY (`activity_member_id`) USING BTREE,
  UNIQUE KEY `student_id` (`student_id`,`activity_id`) USING BTREE,
  KEY `activity_id` (`activity_id`) USING BTREE,
  CONSTRAINT `activitymember_ibfk_1` FOREIGN KEY (`student_id`) REFERENCES `student` (`student_id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `activitymember_ibfk_2` FOREIGN KEY (`activity_id`) REFERENCES `activity` (`activity_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci ROW_FORMAT=DYNAMIC;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `activitymember`
--

LOCK TABLES `activitymember` WRITE;
/*!40000 ALTER TABLE `activitymember` DISABLE KEYS */;
/*!40000 ALTER TABLE `activitymember` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `administrator`
--

DROP TABLE IF EXISTS `administrator`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `administrator` (
  `admin_id` int NOT NULL AUTO_INCREMENT,
  `account` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `ad_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `authority` int DEFAULT NULL,
  `pwd` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `contact` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `department_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  PRIMARY KEY (`admin_id`) USING BTREE,
  UNIQUE KEY `account` (`account`) USING BTREE,
  UNIQUE KEY `ad_name` (`ad_name`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci ROW_FORMAT=DYNAMIC;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `administrator`
--

LOCK TABLES `administrator` WRITE;
/*!40000 ALTER TABLE `administrator` DISABLE KEYS */;
INSERT INTO `administrator` VALUES (2,'administer','',0,'d1c396b49c9c4a8b84986b41d1dfb1b093946f2f67b43896308848ec955f0b6e','157489632',NULL);
/*!40000 ALTER TABLE `administrator` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `annualaudit`
--

DROP TABLE IF EXISTS `annualaudit`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `annualaudit` (
  `declaration_id` int NOT NULL AUTO_INCREMENT,
  `declaration_year` int NOT NULL,
  `club_id` int DEFAULT NULL,
  `review_result` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `meeting_activity_list_attachment` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `external_sponsorship_attachment` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `is_financial_information_public` int DEFAULT NULL,
  `club_constitution_attachment` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `department_opinion` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci,
  `publicity_management_info` json DEFAULT NULL,
  `status` int DEFAULT NULL,
  `review_opinion` text,
  PRIMARY KEY (`declaration_id`) USING BTREE,
  KEY `club_id` (`club_id`) USING BTREE,
  CONSTRAINT `annualaudit_ibfk_1` FOREIGN KEY (`club_id`) REFERENCES `club` (`club_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci ROW_FORMAT=DYNAMIC;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `annualaudit`
--

LOCK TABLES `annualaudit` WRITE;
/*!40000 ALTER TABLE `annualaudit` DISABLE KEYS */;
INSERT INTO `annualaudit` VALUES (1,2024,1,NULL,'','',1,'',NULL,'{\"Blog\": {\"has\": \"0\", \"name\": \"\"}, \"Other\": {\"has\": \"0\", \"name\": \"\"}, \"TikTok\": {\"has\": \"0\", \"name\": \"\"}, \"WebSite\": {\"has\": \"0\", \"name\": \"\"}, \"BiliBili\": {\"has\": \"0\", \"name\": \"\"}, \"KuaiShou\": {\"has\": \"0\", \"name\": \"\"}, \"Publication\": {\"has\": \"0\", \"name\": \"\"}, \"WeChatVideoAccount\": {\"has\": \"0\", \"name\": \"\"}, \"WeChatPublicAccount\": {\"name\": \"\", \"Auditors\": {\"name1\": \"\", \"name2\": \"\", \"name3\": \"\"}, \"submitCount\": \"\"}}',NULL,NULL);
/*!40000 ALTER TABLE `annualaudit` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `backboneevaluation`
--

DROP TABLE IF EXISTS `backboneevaluation`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `backboneevaluation` (
  `record_id` int NOT NULL AUTO_INCREMENT,
  `declaration_year` int NOT NULL,
  `student_id` int DEFAULT NULL,
  `club_id` int DEFAULT NULL,
  `tenure_period` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `achievements` json DEFAULT NULL,
  `training_participation` json DEFAULT NULL,
  `awards` json DEFAULT NULL,
  `club_work_status` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci,
  `status` int DEFAULT NULL,
  `review_opinion` text,
  PRIMARY KEY (`record_id`) USING BTREE,
  KEY `student_id` (`student_id`) USING BTREE,
  KEY `club_id` (`club_id`) USING BTREE,
  CONSTRAINT `backboneevaluation_ibfk_1` FOREIGN KEY (`student_id`) REFERENCES `student` (`student_id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `backboneevaluation_ibfk_2` FOREIGN KEY (`club_id`) REFERENCES `club` (`club_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci ROW_FORMAT=DYNAMIC;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `backboneevaluation`
--

LOCK TABLES `backboneevaluation` WRITE;
/*!40000 ALTER TABLE `backboneevaluation` DISABLE KEYS */;
INSERT INTO `backboneevaluation` VALUES (1,2021,1,1,'220','\"\"','\"\"','\"\"','工作成效',NULL,NULL),(2,2024,15,1,'2024-06-01 至 2024-07-31','{\"gpa\": \"5.0\", \"rank\": \"1\", \"rankRatio\": \"1\"}','[{\"time\": \"2024-05-01\", \"location\": \"学院\", \"organization\": \"学院\"}]','[{\"name\": \"二等奖\", \"time\": \"2024-05-01\", \"organization\": \"学院\"}]','撒大苏打实打实大苏打实打实的',NULL,NULL);
/*!40000 ALTER TABLE `backboneevaluation` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `club`
--

DROP TABLE IF EXISTS `club`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `club` (
  `club_id` int NOT NULL AUTO_INCREMENT,
  `club_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `establishment_date` datetime DEFAULT NULL,
  `responsible_department_id` int DEFAULT NULL,
  `club_description` json DEFAULT NULL,
  `club_category` int NOT NULL,
  `administrative_guide_teacher_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `business_guide_teacher_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `contact_person_id` int DEFAULT NULL,
  `club_status` int DEFAULT NULL,
  `main_campus` int NOT NULL,
  `total_members` int DEFAULT '1',
  `is_financial_information_public` int DEFAULT NULL,
  `publicity_management_info` json DEFAULT NULL,
  `image_url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  PRIMARY KEY (`club_id`) USING BTREE,
  KEY `contact_person_id` (`contact_person_id`) USING BTREE,
  KEY `responsible_department_id` (`responsible_department_id`) USING BTREE,
  CONSTRAINT `club_ibfk_1` FOREIGN KEY (`contact_person_id`) REFERENCES `student` (`student_id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `club_ibfk_2` FOREIGN KEY (`responsible_department_id`) REFERENCES `administrator` (`admin_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci ROW_FORMAT=DYNAMIC;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `club`
--

LOCK TABLES `club` WRITE;
/*!40000 ALTER TABLE `club` DISABLE KEYS */;
INSERT INTO `club` VALUES (1,'篮球社','2024-06-11 15:43:36',NULL,'[{\"title\": \"社团简介\", \"content\": \"<p>篮球社是一个打篮球的社团</p><p>这句话是个废号</p>\"}, {\"title\": \"社团描述\", \"content\": \"<p>篮球社由xxx组建</p><p>牢大</p>\"}, {\"title\": \"社团获奖\", \"content\": \"<p>一等奖</p><p>二等奖</p><p>三等奖</p><p>优秀奖</p>\"}]',3,'林老师','李老师',1,1,4,2,0,NULL,'/src/assets/loginBg.jpg'),(2,'xx社','2024-06-04 16:53:08',NULL,'\"\"',2,'张老师','李老师',1,0,1,10,1,NULL,'url'),(3,'tt社','2024-06-04 16:53:08',NULL,'[{\"title\": \"社团简介\", \"content\": \"<p>篮球社是一个打篮球的社团</p><p>这句话是个废号</p>\"}, {\"title\": \"社团描述\", \"content\": \"<p>篮球社由xxx组建</p><p>牢大</p>\"}, {\"title\": \"社团获奖\", \"content\": \"<p>一等奖</p><p>二等奖</p><p>三等奖</p><p>优秀奖</p>\"}]',3,'张老师','李老师',1,NULL,0,5,1,NULL,'url'),(4,'hh社','2024-06-04 16:53:08',NULL,'\"\"',4,'张老师','李老师',1,0,2,8,1,NULL,'url'),(5,'羽毛球社','2024-06-04 16:53:08',NULL,'\"\"',5,'张老师','李老师',1,1,3,4,1,NULL,'url'),(6,'网球社','2024-06-04 16:53:08',NULL,'\"\"',2,'张老师','李老师',1,0,1,3,1,NULL,'url'),(7,'排球社','2024-06-04 16:53:08',NULL,'\"\"',3,'张老师','李老师',1,NULL,4,8,1,NULL,'url'),(8,'街舞社','2024-06-04 16:53:08',NULL,'\"\"',4,'张老师','李老师',1,NULL,2,115,1,NULL,'url'),(9,'美术社','2024-06-04 16:53:08',NULL,'\"\"',0,'张老师','李老师',1,NULL,0,9,1,NULL,'url'),(10,'二次元社','2024-06-04 16:53:08',NULL,'\"\"',1,'张老师','李老师',1,1,3,96,1,NULL,'url'),(11,'ll社','2024-06-04 16:53:08',NULL,'\"\"',4,'张老师','李老师',1,0,1,4,1,NULL,'url');
/*!40000 ALTER TABLE `club` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `club_membership_application`
--

DROP TABLE IF EXISTS `club_membership_application`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `club_membership_application` (
  `application_id` int NOT NULL AUTO_INCREMENT,
  `student_id` int DEFAULT NULL,
  `club_id` int DEFAULT NULL,
  `status` int DEFAULT NULL,
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP,
  `reason` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `reject_reason` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci,
  PRIMARY KEY (`application_id`) USING BTREE,
  KEY `student_id` (`student_id`) USING BTREE,
  KEY `club_id` (`club_id`) USING BTREE,
  CONSTRAINT `club_membership_application_ibfk_1` FOREIGN KEY (`student_id`) REFERENCES `student` (`student_id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `club_membership_application_ibfk_2` FOREIGN KEY (`club_id`) REFERENCES `club` (`club_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci ROW_FORMAT=DYNAMIC;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `club_membership_application`
--

LOCK TABLES `club_membership_application` WRITE;
/*!40000 ALTER TABLE `club_membership_application` DISABLE KEYS */;
INSERT INTO `club_membership_application` VALUES (1,1,1,0,'2024-06-04 17:03:36','不知道','不合适'),(2,2,1,0,'2024-06-04 17:06:38','不知道','不适合'),(3,15,1,0,'2024-06-08 22:08:20','我希望加入篮球社',NULL),(4,15,2,NULL,'2024-06-10 16:12:37','没什么理由',NULL),(5,16,1,1,'2024-06-10 16:14:55','我是牢大',NULL);
/*!40000 ALTER TABLE `club_membership_application` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `clubannouncement`
--

DROP TABLE IF EXISTS `clubannouncement`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `clubannouncement` (
  `announcement_id` int NOT NULL AUTO_INCREMENT,
  `club_id` int DEFAULT NULL,
  `title` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `content` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci,
  `image_url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `publish_time` datetime DEFAULT CURRENT_TIMESTAMP,
  `modify_time` datetime DEFAULT NULL,
  PRIMARY KEY (`announcement_id`) USING BTREE,
  KEY `club_id` (`club_id`) USING BTREE,
  CONSTRAINT `clubannouncement_ibfk_1` FOREIGN KEY (`club_id`) REFERENCES `club` (`club_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci ROW_FORMAT=DYNAMIC;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `clubannouncement`
--

LOCK TABLES `clubannouncement` WRITE;
/*!40000 ALTER TABLE `clubannouncement` DISABLE KEYS */;
INSERT INTO `clubannouncement` VALUES (1,1,'标题','内容','url','2024-06-04 19:41:06','2024-06-04 19:41:07'),(2,1,'标题1','1','url','2024-06-04 19:41:06','2024-06-04 19:41:07'),(3,1,'2','内容22','url','2024-06-04 19:41:06','2024-06-04 19:41:07'),(4,1,'标题3','内容55','url','2024-06-04 19:41:06','2024-06-04 19:41:07'),(5,1,'标题4','内容','url','2024-06-04 19:41:06','2024-06-04 19:41:07'),(6,1,'提交材料','通知内容','','2024-06-10 11:54:14',NULL),(8,1,'21届全员大会','会议类型:全员大会\r\n会议地点:学院楼A313\r\n会议时间:2024-06-29 13:00:00\r\n指导教师:张老师','','2024-06-11 16:00:06',NULL);
/*!40000 ALTER TABLE `clubannouncement` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `clubapplicationrecord`
--

DROP TABLE IF EXISTS `clubapplicationrecord`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `clubapplicationrecord` (
  `record_id` int NOT NULL AUTO_INCREMENT,
  `club_id` int DEFAULT NULL,
  `college_review_status` int DEFAULT NULL,
  `university_student_union_review_status` int DEFAULT NULL,
  `college_review_opinion` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci,
  `university_student_union_review_opinion` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci,
  `attachment_url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `advisor_resume_attachment_url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `student_id` int DEFAULT NULL,
  PRIMARY KEY (`record_id`) USING BTREE,
  KEY `student_id` (`student_id`) USING BTREE,
  KEY `club_id` (`club_id`) USING BTREE,
  CONSTRAINT `clubapplicationrecord_ibfk_1` FOREIGN KEY (`student_id`) REFERENCES `student` (`student_id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `clubapplicationrecord_ibfk_2` FOREIGN KEY (`club_id`) REFERENCES `club` (`club_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci ROW_FORMAT=DYNAMIC;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `clubapplicationrecord`
--

LOCK TABLES `clubapplicationrecord` WRITE;
/*!40000 ALTER TABLE `clubapplicationrecord` DISABLE KEYS */;
/*!40000 ALTER TABLE `clubapplicationrecord` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `clubawards`
--

DROP TABLE IF EXISTS `clubawards`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `clubawards` (
  `award_id` int NOT NULL AUTO_INCREMENT,
  `club_id` int DEFAULT NULL,
  `award_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `award_time` datetime NOT NULL,
  `issuing_authority` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  PRIMARY KEY (`award_id`) USING BTREE,
  KEY `club_id` (`club_id`) USING BTREE,
  CONSTRAINT `clubawards_ibfk_1` FOREIGN KEY (`club_id`) REFERENCES `club` (`club_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci ROW_FORMAT=DYNAMIC;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `clubawards`
--

LOCK TABLES `clubawards` WRITE;
/*!40000 ALTER TABLE `clubawards` DISABLE KEYS */;
INSERT INTO `clubawards` VALUES (1,1,'好奖','2024-06-04 17:40:39','学院'),(2,1,'好奖','2024-06-04 17:40:39','学院'),(3,1,'好奖','2024-06-04 17:40:39','学院'),(4,1,'好奖','2024-06-04 17:40:39','学院');
/*!40000 ALTER TABLE `clubawards` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `clubmeeting`
--

DROP TABLE IF EXISTS `clubmeeting`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `clubmeeting` (
  `meeting_id` int NOT NULL AUTO_INCREMENT,
  `club_id` int DEFAULT NULL,
  `meeting_time` datetime NOT NULL,
  `location` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `category` int NOT NULL,
  `advisor_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  PRIMARY KEY (`meeting_id`) USING BTREE,
  KEY `club_id` (`club_id`) USING BTREE,
  CONSTRAINT `clubmeeting_ibfk_1` FOREIGN KEY (`club_id`) REFERENCES `club` (`club_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci ROW_FORMAT=DYNAMIC;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `clubmeeting`
--

LOCK TABLES `clubmeeting` WRITE;
/*!40000 ALTER TABLE `clubmeeting` DISABLE KEYS */;
INSERT INTO `clubmeeting` VALUES (2,1,'2021-07-07 11:11:00','school',0,'张老师'),(3,1,'2021-07-07 11:11:11','school',1,'张老师'),(4,1,'2021-07-09 11:11:11','school',0,'李老师'),(5,1,'2021-07-20 11:11:11','school',0,'李老师'),(6,1,'2024-06-11 16:00:05','学院楼A313',1,'张老师');
/*!40000 ALTER TABLE `clubmeeting` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `clubmember`
--

DROP TABLE IF EXISTS `clubmember`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `clubmember` (
  `club_member_id` int NOT NULL AUTO_INCREMENT,
  `student_id` int DEFAULT NULL,
  `club_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `club_id` int DEFAULT NULL,
  `position` int DEFAULT NULL,
  `join_date` datetime DEFAULT NULL,
  PRIMARY KEY (`club_member_id`) USING BTREE,
  UNIQUE KEY `student_id` (`student_id`,`club_id`) USING BTREE,
  KEY `club_id` (`club_id`) USING BTREE,
  CONSTRAINT `clubmember_ibfk_1` FOREIGN KEY (`student_id`) REFERENCES `student` (`student_id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `clubmember_ibfk_2` FOREIGN KEY (`club_id`) REFERENCES `club` (`club_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci ROW_FORMAT=DYNAMIC;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `clubmember`
--

LOCK TABLES `clubmember` WRITE;
/*!40000 ALTER TABLE `clubmember` DISABLE KEYS */;
INSERT INTO `clubmember` VALUES (1,15,'篮球社',1,0,'2024-06-04 17:07:16'),(7,16,'篮球社',1,3,'2024-06-10 16:33:39');
/*!40000 ALTER TABLE `clubmember` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `configuration`
--

DROP TABLE IF EXISTS `configuration`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `configuration` (
  `config_id` int NOT NULL AUTO_INCREMENT,
  `config_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `config_content` json DEFAULT NULL,
  `detail` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci,
  PRIMARY KEY (`config_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci ROW_FORMAT=DYNAMIC;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `configuration`
--

LOCK TABLES `configuration` WRITE;
/*!40000 ALTER TABLE `configuration` DISABLE KEYS */;
/*!40000 ALTER TABLE `configuration` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `student`
--

DROP TABLE IF EXISTS `student`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `student` (
  `student_id` int NOT NULL AUTO_INCREMENT,
  `email` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `st_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `student_number` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `gender` int DEFAULT NULL,
  `college` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `grade` int DEFAULT NULL,
  `contact` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `pwd` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `political_status` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT '群众',
  `date_of_birth` datetime DEFAULT NULL,
  `image_url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `hobby` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `specialty` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  PRIMARY KEY (`student_id`) USING BTREE,
  UNIQUE KEY `email` (`email`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci ROW_FORMAT=DYNAMIC;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `student`
--

LOCK TABLES `student` WRITE;
/*!40000 ALTER TABLE `student` DISABLE KEYS */;
INSERT INTO `student` VALUES (1,'c99xx@mail.sysu.edu.cn','小兰','2154',0,'软空',2021,'185468','11111111','共青团员','2024-06-04 16:59:35','url','打球','？'),(2,'x222@163.com','小年','2154',0,'软空',2021,'185468','111','共青团员','2024-06-04 16:59:35','url','打球','？'),(3,'x333@163.com','效应','2154',0,'软空',2021,'185468','111','共青团员','2024-06-04 16:59:35','url','打球','？'),(4,'x443@163.com','小红','2154',0,'软空',2021,'185468','111','共青团员','2024-06-04 16:59:35','url','打球','？'),(5,'x5545@163.com','小明','2154',0,'软空',2021,'185468','111','共青团员','2024-06-04 16:59:35','url','打球','？'),(6,'x366@163.com','小康','2154',0,'软空',2021,'185468','111','共青团员','2024-06-04 16:59:35','url','打球','？'),(11,'caixj27@mail2.sysu.edu.cn',NULL,NULL,NULL,NULL,NULL,NULL,'cdeb0a260807444797ff2da6d7829438c7487586364772ca3cdf77dac108ce31','群众',NULL,NULL,NULL,NULL),(12,'55sxxx@mail2.sysu.edu.cn',NULL,NULL,NULL,NULL,NULL,NULL,'c93a0044579842e6889202b3c501b96a527f1a40543348ddc9cb4491b3363041','群众',NULL,NULL,NULL,NULL),(13,'cxxxx@mail.sysu.edu.cn',NULL,NULL,NULL,NULL,NULL,NULL,'cebfbbf6c8b046fb9e4e8a9b7b8f9e0a711d43f771fca515e37a2c3eac5977ae','群众',NULL,NULL,NULL,NULL),(14,'c9999xx@mail.sysu.edu.cn',NULL,NULL,NULL,NULL,NULL,NULL,'10815ea8d7c4478ca7ed04ccaedda8e6410afe2a60f13d225720914cd2d6b25c','群众',NULL,NULL,NULL,NULL),(15,'dongxy28@mail2.sysu.edu.cn',NULL,NULL,NULL,NULL,NULL,'1875423456','72996ce6118d4f4e81ed576686497c481b1342da8536dcd4bf5456877bb41ba7','共青团员',NULL,NULL,'打球','Python'),(16,'123456@mail2.sysu.edu.cn',NULL,NULL,NULL,NULL,NULL,NULL,'ce47d93c3423459cbfde636c9ce9a616c273b7ca150ebef037bd6549118af263','群众',NULL,NULL,NULL,NULL);
/*!40000 ALTER TABLE `student` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `studentclubevaluation`
--

DROP TABLE IF EXISTS `studentclubevaluation`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `studentclubevaluation` (
  `record_id` int NOT NULL AUTO_INCREMENT,
  `declaration_year` int NOT NULL,
  `club_id` int DEFAULT NULL,
  `handover_method` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `handover_participants_count` int DEFAULT NULL,
  `advisor_participation` int DEFAULT NULL,
  `meetings` json DEFAULT NULL,
  `association_awards` json DEFAULT NULL,
  `publicity_management_effectiveness` json DEFAULT NULL,
  `hosted_school_level_activities` json DEFAULT NULL,
  `club_work_introduction` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci,
  `image_url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `club_education_case_attachment` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `communist_related_back_bone_number` int DEFAULT NULL,
  `backbone_number` int DEFAULT NULL,
  `status` int DEFAULT NULL,
  `review_opinion` text,
  PRIMARY KEY (`record_id`) USING BTREE,
  KEY `club_id` (`club_id`) USING BTREE,
  CONSTRAINT `studentclubevaluation_ibfk_1` FOREIGN KEY (`club_id`) REFERENCES `club` (`club_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci ROW_FORMAT=DYNAMIC;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `studentclubevaluation`
--

LOCK TABLES `studentclubevaluation` WRITE;
/*!40000 ALTER TABLE `studentclubevaluation` DISABLE KEYS */;
/*!40000 ALTER TABLE `studentclubevaluation` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping events for database 'digitalclub'
--

--
-- Dumping routines for database 'digitalclub'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-06-11 16:33:46
