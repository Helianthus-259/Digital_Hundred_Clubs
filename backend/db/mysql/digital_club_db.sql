/*
 Navicat MySQL Data Transfer

 Source Server         : root
 Source Server Type    : MySQL
 Source Server Version : 80032
 Source Host           : localhost:3306
 Source Schema         : digital_club_db

 Target Server Type    : MySQL
 Target Server Version : 80032
 File Encoding         : 65001

 Date: 03/06/2024 17:28:09
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for activity
-- ----------------------------
DROP TABLE IF EXISTS `activity`;
CREATE TABLE `activity`  (
  `activity_id` int(0) NOT NULL AUTO_INCREMENT,
  `club_id` int(0) NULL DEFAULT NULL,
  `activity_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `activity_publish_time` datetime(0) NULL DEFAULT NULL,
  `activity_start_time` datetime(0) NULL DEFAULT NULL,
  `activity_end_time` datetime(0) NULL DEFAULT NULL,
  `activity_location` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `activity_introduction` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL,
  `application_form_attachment` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `activity_status` int(0) NULL DEFAULT NULL,
  `activity_effect` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL,
  `image_url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  PRIMARY KEY (`activity_id`) USING BTREE,
  INDEX `club_id`(`club_id`) USING BTREE,
  CONSTRAINT `activity_ibfk_1` FOREIGN KEY (`club_id`) REFERENCES `club` (`club_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of activity
-- ----------------------------
INSERT INTO `activity` VALUES (2, 1, '1', NULL, NULL, NULL, '1', '1', '1', 1, '1', '1');
INSERT INTO `activity` VALUES (3, 1, '11', NULL, NULL, NULL, '11', '11', NULL, NULL, NULL, NULL);
INSERT INTO `activity` VALUES (4, 2, '2', NULL, NULL, NULL, '2', '2', NULL, NULL, NULL, NULL);

-- ----------------------------
-- Table structure for activitymember
-- ----------------------------
DROP TABLE IF EXISTS `activitymember`;
CREATE TABLE `activitymember`  (
  `activity_member_id` int(0) NOT NULL AUTO_INCREMENT,
  `student_id` int(0) NULL DEFAULT NULL,
  `activity_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `activity_id` int(0) NULL DEFAULT NULL,
  `personal_effect` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL,
  `award_wining_time` datetime(0) NULL DEFAULT NULL,
  PRIMARY KEY (`activity_member_id`) USING BTREE,
  UNIQUE INDEX `student_id`(`student_id`, `activity_id`) USING BTREE,
  INDEX `activity_id`(`activity_id`) USING BTREE,
  CONSTRAINT `activitymember_ibfk_1` FOREIGN KEY (`student_id`) REFERENCES `student` (`student_id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `activitymember_ibfk_2` FOREIGN KEY (`activity_id`) REFERENCES `activity` (`activity_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of activitymember
-- ----------------------------
INSERT INTO `activitymember` VALUES (2, 1, '1', 2, '1', '2024-05-31 19:15:01');

-- ----------------------------
-- Table structure for administrator
-- ----------------------------
DROP TABLE IF EXISTS `administrator`;
CREATE TABLE `administrator`  (
  `admin_id` int(0) NOT NULL AUTO_INCREMENT,
  `account` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `ad_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `authority` int(0) NULL DEFAULT NULL,
  `pwd` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `contact` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `department_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  PRIMARY KEY (`admin_id`) USING BTREE,
  UNIQUE INDEX `account`(`account`) USING BTREE,
  UNIQUE INDEX `ad_name`(`ad_name`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of administrator
-- ----------------------------
INSERT INTO `administrator` VALUES (1, '111', '1111', NULL, 'cac98bac690c486eaff8a958f4d5ee7cc8c9db24b7c8c57d97c921703b51bec7', NULL, NULL);
INSERT INTO `administrator` VALUES (2, '222', NULL, NULL, '2ad495b897b440119aea46d8cb2ce666c886fc0c25251ad8d1b6abfc363444d4', NULL, NULL);

-- ----------------------------
-- Table structure for annualaudit
-- ----------------------------
DROP TABLE IF EXISTS `annualaudit`;
CREATE TABLE `annualaudit`  (
  `declaration_id` int(0) NOT NULL AUTO_INCREMENT,
  `declaration_year` int(0) NULL DEFAULT NULL,
  `club_id` int(0) NULL DEFAULT NULL,
  `review_result` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `meeting_activity_list_attachment` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `external_sponsorship_attachment` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `is_financial_information_public` int(0) NULL DEFAULT NULL,
  `club_constitution_attachment` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `department_opinion` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL,
  `publicity_management_info` json NULL,
  PRIMARY KEY (`declaration_id`) USING BTREE,
  INDEX `club_id`(`club_id`) USING BTREE,
  CONSTRAINT `annualaudit_ibfk_1` FOREIGN KEY (`club_id`) REFERENCES `club` (`club_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of annualaudit
-- ----------------------------
INSERT INTO `annualaudit` VALUES (1, 2023, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `annualaudit` VALUES (2, 2022, 2, NULL, NULL, NULL, NULL, NULL, NULL, NULL);

-- ----------------------------
-- Table structure for backboneevaluation
-- ----------------------------
DROP TABLE IF EXISTS `backboneevaluation`;
CREATE TABLE `backboneevaluation`  (
  `record_id` int(0) NOT NULL AUTO_INCREMENT,
  `declaration_year` int(0) NULL DEFAULT NULL,
  `student_id` int(0) NULL DEFAULT NULL,
  `club_id` int(0) NULL DEFAULT NULL,
  `tenure_period` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `achievements` json NULL,
  `training_participation` json NULL,
  `awards` json NULL,
  `club_work_status` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL,
  PRIMARY KEY (`record_id`) USING BTREE,
  INDEX `student_id`(`student_id`) USING BTREE,
  INDEX `club_id`(`club_id`) USING BTREE,
  CONSTRAINT `backboneevaluation_ibfk_1` FOREIGN KEY (`student_id`) REFERENCES `student` (`student_id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `backboneevaluation_ibfk_2` FOREIGN KEY (`club_id`) REFERENCES `club` (`club_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of backboneevaluation
-- ----------------------------
INSERT INTO `backboneevaluation` VALUES (1, NULL, 1, 1, '2023-2024', '{\"data\": {\"code\": 4}, \"status\": 200}', '{\"data\": {\"code\": 4}, \"status\": 200}', '{\"data\": {\"code\": 4}, \"status\": 200}', '6666');
INSERT INTO `backboneevaluation` VALUES (3, 2024, 2, 2, '2022-2024', '{\"path\": \"/api/club/tt\", \"error\": \"Service Unavailable\", \"status\": 503, \"requestId\": \"ce1d3159-58\", \"timestamp\": \"2024-05-31T12:57:12.844+00:00\"}', '{\"path\": \"/api/club/tt\", \"error\": \"Service Unavailable\", \"status\": 503, \"requestId\": \"ce1d3159-58\", \"timestamp\": \"2024-05-31T12:57:12.844+00:00\"}', '{\"path\": \"/api/club/tt\", \"error\": \"Service Unavailable\", \"status\": 503, \"requestId\": \"ce1d3159-58\", \"timestamp\": \"2024-05-31T12:57:12.844+00:00\"}', '2222');

-- ----------------------------
-- Table structure for club
-- ----------------------------
DROP TABLE IF EXISTS `club`;
CREATE TABLE `club`  (
  `club_id` int(0) NOT NULL AUTO_INCREMENT,
  `club_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `establishment_date` datetime(0) NULL DEFAULT NULL,
  `responsible_department_id` int(0) NULL DEFAULT NULL,
  `club_description` json NULL,
  `club_category` int(0) NULL DEFAULT NULL,
  `administrative_guide_teacher_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `business_guide_teacher_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `contact_person_id` int(0) NULL DEFAULT NULL,
  `club_status` int(0) NULL DEFAULT NULL,
  `main_campus` int(0) NULL DEFAULT NULL,
  `total_members` int(0) NULL DEFAULT NULL,
  `is_financial_information_public` int(0) NULL DEFAULT NULL,
  `publicity_management_info` json NULL,
  `image_url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  PRIMARY KEY (`club_id`) USING BTREE,
  INDEX `contact_person_id`(`contact_person_id`) USING BTREE,
  INDEX `responsible_department_id`(`responsible_department_id`) USING BTREE,
  CONSTRAINT `club_ibfk_1` FOREIGN KEY (`contact_person_id`) REFERENCES `student` (`student_id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `club_ibfk_2` FOREIGN KEY (`responsible_department_id`) REFERENCES `administrator` (`admin_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of club
-- ----------------------------
INSERT INTO `club` VALUES (1, 'Basketball Club', '2023-01-15 00:00:00', 1, '{\"data\": {\"code\": 7, \"clubDescription\": null}, \"status\": 200}', 1, 'John Smith', 'Emily Johnson', 1, 1, 1, 50, 1, '{\"publicity\": true}', 'https://loremflickr.com/320/240');
INSERT INTO `club` VALUES (2, 'Chess Club', '2022-11-20 00:00:00', 2, NULL, 2, 'Michael Brown', 'Sarah Wilson', 2, 1, 2, 30, 1, '{\"events\": [\"weekly tournaments\", \"training sessions\"], \"publicity\": true}', 'https://loremflickr.com/320/240');
INSERT INTO `club` VALUES (3, 'tt', NULL, 1, NULL, 1, 'tt', 'tt', 1, 0, 1, 1, NULL, NULL, NULL);

-- ----------------------------
-- Table structure for clubannouncement
-- ----------------------------
DROP TABLE IF EXISTS `clubannouncement`;
CREATE TABLE `clubannouncement`  (
  `announcement_id` int(0) NOT NULL AUTO_INCREMENT,
  `club_id` int(0) NULL DEFAULT NULL,
  `title` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `content` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL,
  `image_url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `publish_time` datetime(0) NULL DEFAULT NULL,
  `modify_time` datetime(0) NULL DEFAULT NULL,
  PRIMARY KEY (`announcement_id`) USING BTREE,
  INDEX `club_id`(`club_id`) USING BTREE,
  CONSTRAINT `clubannouncement_ibfk_1` FOREIGN KEY (`club_id`) REFERENCES `club` (`club_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of clubannouncement
-- ----------------------------
INSERT INTO `clubannouncement` VALUES (1, 1, '111', '111', '111', '2024-06-03 13:26:28', NULL);

-- ----------------------------
-- Table structure for clubapplicationrecord
-- ----------------------------
DROP TABLE IF EXISTS `clubapplicationrecord`;
CREATE TABLE `clubapplicationrecord`  (
  `record_id` int(0) NOT NULL AUTO_INCREMENT,
  `club_id` int(0) NULL DEFAULT NULL,
  `college_review_status` int(0) NULL DEFAULT NULL,
  `university_student_union_review_status` int(0) NULL DEFAULT NULL,
  `college_review_opinion` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL,
  `university_student_union_review_opinion` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL,
  `attachment_url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `advisor_resume_attachment_url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `student_id` int(0) NULL DEFAULT NULL,
  PRIMARY KEY (`record_id`) USING BTREE,
  INDEX `student_id`(`student_id`) USING BTREE,
  INDEX `club_id`(`club_id`) USING BTREE,
  CONSTRAINT `clubapplicationrecord_ibfk_1` FOREIGN KEY (`student_id`) REFERENCES `student` (`student_id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `clubapplicationrecord_ibfk_2` FOREIGN KEY (`club_id`) REFERENCES `club` (`club_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of clubapplicationrecord
-- ----------------------------
INSERT INTO `clubapplicationrecord` VALUES (1, 1, 1, 11, '1', '11', NULL, NULL, 1);
INSERT INTO `clubapplicationrecord` VALUES (2, 2, 1, 11, '1', '22', NULL, NULL, 2);
INSERT INTO `clubapplicationrecord` VALUES (3, 3, 1, 0, '通过', '校团委不通过', NULL, NULL, 1);

-- ----------------------------
-- Table structure for clubawards
-- ----------------------------
DROP TABLE IF EXISTS `clubawards`;
CREATE TABLE `clubawards`  (
  `award_id` int(0) NOT NULL AUTO_INCREMENT,
  `club_id` int(0) NULL DEFAULT NULL,
  `award_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `award_time` datetime(0) NULL DEFAULT NULL,
  `issuing_authority` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  PRIMARY KEY (`award_id`) USING BTREE,
  INDEX `club_id`(`club_id`) USING BTREE,
  CONSTRAINT `clubawards_ibfk_1` FOREIGN KEY (`club_id`) REFERENCES `club` (`club_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of clubawards
-- ----------------------------

-- ----------------------------
-- Table structure for clubmeeting
-- ----------------------------
DROP TABLE IF EXISTS `clubmeeting`;
CREATE TABLE `clubmeeting`  (
  `meeting_id` int(0) NOT NULL AUTO_INCREMENT,
  `club_id` int(0) NULL DEFAULT NULL,
  `meeting_time` datetime(0) NULL DEFAULT NULL,
  `location` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `category` int(0) NULL DEFAULT NULL,
  `advisor_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  PRIMARY KEY (`meeting_id`) USING BTREE,
  INDEX `club_id`(`club_id`) USING BTREE,
  CONSTRAINT `clubmeeting_ibfk_1` FOREIGN KEY (`club_id`) REFERENCES `club` (`club_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of clubmeeting
-- ----------------------------

-- ----------------------------
-- Table structure for clubmember
-- ----------------------------
DROP TABLE IF EXISTS `clubmember`;
CREATE TABLE `clubmember`  (
  `club_member_id` int(0) NOT NULL AUTO_INCREMENT,
  `student_id` int(0) NULL DEFAULT NULL,
  `club_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `club_id` int(0) NULL DEFAULT NULL,
  `position` int(0) NULL DEFAULT NULL,
  `join_date` datetime(0) NULL DEFAULT NULL,
  PRIMARY KEY (`club_member_id`) USING BTREE,
  UNIQUE INDEX `student_id`(`student_id`, `club_id`) USING BTREE,
  INDEX `club_id`(`club_id`) USING BTREE,
  CONSTRAINT `clubmember_ibfk_1` FOREIGN KEY (`student_id`) REFERENCES `student` (`student_id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `clubmember_ibfk_2` FOREIGN KEY (`club_id`) REFERENCES `club` (`club_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of clubmember
-- ----------------------------
INSERT INTO `clubmember` VALUES (1, 1, '11', 1, 1, '2024-05-31 19:39:20');
INSERT INTO `clubmember` VALUES (2, 1, '22', 2, 2, '2024-05-31 19:39:45');
INSERT INTO `clubmember` VALUES (3, 2, '22', 2, 1, '2024-06-01 00:37:36');

-- ----------------------------
-- Table structure for configuration
-- ----------------------------
DROP TABLE IF EXISTS `configuration`;
CREATE TABLE `configuration`  (
  `config_id` int(0) NOT NULL AUTO_INCREMENT,
  `config_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `config_content` json NULL,
  `detail` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL,
  PRIMARY KEY (`config_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of configuration
-- ----------------------------

-- ----------------------------
-- Table structure for student
-- ----------------------------
DROP TABLE IF EXISTS `student`;
CREATE TABLE `student`  (
  `student_id` int(0) NOT NULL AUTO_INCREMENT,
  `email` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `st_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `student_number` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `gender` int(0) NULL DEFAULT NULL,
  `college` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `grade` int(0) NULL DEFAULT NULL,
  `contact` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `pwd` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `political_status` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '群众',
  `date_of_birth` datetime(0) NULL DEFAULT NULL,
  `image_url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `hobby` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `specialty` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  PRIMARY KEY (`student_id`) USING BTREE,
  UNIQUE INDEX `email`(`email`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of student
-- ----------------------------
INSERT INTO `student` VALUES (1, '111@qq.com', NULL, NULL, NULL, NULL, NULL, '12345678', '47ae865bd1264e2792805cff7e74424848ec8be201c422f56e8014a25dae6241', '群众', NULL, 'image\\avatar\\月亮_045F38C3BE2143D58EB85D54352AC654.jpg', '篮球', '软件工程');
INSERT INTO `student` VALUES (2, '222@qq.com', NULL, NULL, NULL, NULL, NULL, NULL, 'f05b7188fc7a4655ac658060957966ce152f4c895bb9024f558a6237dd374e74', '群众', NULL, NULL, NULL, NULL);
INSERT INTO `student` VALUES (3, '333@qq.com', NULL, NULL, NULL, NULL, NULL, NULL, '01d6d3c5fee648f4beccdd79b15f738cc087e8f87c24f912773b7d515f5fdb1f', '群众', NULL, NULL, NULL, NULL);
INSERT INTO `student` VALUES (4, '444@qq.com', NULL, NULL, NULL, NULL, NULL, NULL, '4c8df69395ac4e3ebd2e8e4cf838d8e9a68a6e732ebe6ef4a008b40d142c7606', '群众', NULL, NULL, NULL, NULL);

-- ----------------------------
-- Table structure for studentclubevaluation
-- ----------------------------
DROP TABLE IF EXISTS `studentclubevaluation`;
CREATE TABLE `studentclubevaluation`  (
  `record_id` int(0) NOT NULL AUTO_INCREMENT,
  `declaration_year` int(0) NULL DEFAULT NULL,
  `club_id` int(0) NULL DEFAULT NULL,
  `handover_method` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `handover_participants_count` int(0) NULL DEFAULT NULL,
  `advisor_participation` int(0) NULL DEFAULT NULL,
  `publicity_management_effectiveness` json NULL,
  `hosted_school_level_activities` json NULL,
  `club_work_introduction` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL,
  `image_url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `club_education_case_attachment` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `meetings` json NULL,
  `association_awards` json NULL,
  PRIMARY KEY (`record_id`) USING BTREE,
  INDEX `club_id`(`club_id`) USING BTREE,
  CONSTRAINT `studentclubevaluation_ibfk_1` FOREIGN KEY (`club_id`) REFERENCES `club` (`club_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of studentclubevaluation
-- ----------------------------
INSERT INTO `studentclubevaluation` VALUES (1, 2023, 1, NULL, NULL, NULL, NULL, NULL, '111666', NULL, NULL, NULL, NULL);
INSERT INTO `studentclubevaluation` VALUES (2, 2022, 2, NULL, NULL, NULL, NULL, NULL, '222666', NULL, NULL, NULL, NULL);

SET FOREIGN_KEY_CHECKS = 1;
