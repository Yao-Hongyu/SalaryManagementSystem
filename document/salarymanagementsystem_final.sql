/*
 Navicat Premium Data Transfer

 Source Server         : MySQL5.7
 Source Server Type    : MySQL
 Source Server Version : 50732
 Source Host           : localhost:13306
 Source Schema         : salarymanagementsystem

 Target Server Type    : MySQL
 Target Server Version : 50732
 File Encoding         : 65001

 Date: 02/06/2022 21:45:59
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for admin
-- ----------------------------
DROP TABLE IF EXISTS `admin`;
CREATE TABLE `admin`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of admin
-- ----------------------------
INSERT INTO `admin` VALUES (1, '123', '202cb962ac59075b964b07152d234b70');
INSERT INTO `admin` VALUES (2, 'test', '098f6bcd4621d373cade4e832627b4f6');

-- ----------------------------
-- Table structure for allowance
-- ----------------------------
DROP TABLE IF EXISTS `allowance`;
CREATE TABLE `allowance`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `employee_id` int(11) NULL DEFAULT NULL,
  `date` bigint(14) NULL DEFAULT NULL,
  `duration` int(11) NULL DEFAULT NULL,
  `bonus` int(11) NULL DEFAULT NULL,
  `description` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `employee_id`(`employee_id`) USING BTREE,
  CONSTRAINT `allowance_ibfk_1` FOREIGN KEY (`employee_id`) REFERENCES `employee` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of allowance
-- ----------------------------
INSERT INTO `allowance` VALUES (1, 1, 1653062400000, 4, 100, '节假日出勤');

-- ----------------------------
-- Table structure for employee
-- ----------------------------
DROP TABLE IF EXISTS `employee`;
CREATE TABLE `employee`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `age` int(11) NULL DEFAULT NULL,
  `employee_type_id` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `employee_type_id`(`employee_type_id`) USING BTREE,
  CONSTRAINT `employee_ibfk_1` FOREIGN KEY (`employee_type_id`) REFERENCES `employee_type` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of employee
-- ----------------------------
INSERT INTO `employee` VALUES (1, '张三', 20, 1);

-- ----------------------------
-- Table structure for employee_type
-- ----------------------------
DROP TABLE IF EXISTS `employee_type`;
CREATE TABLE `employee_type`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `level` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `base_salary` decimal(10, 2) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of employee_type
-- ----------------------------
INSERT INTO `employee_type` VALUES (1, '一级员工', 'p1', 300.00);

-- ----------------------------
-- Table structure for month_salary
-- ----------------------------
DROP TABLE IF EXISTS `month_salary`;
CREATE TABLE `month_salary`  (
  `employee_id` int(11) NOT NULL,
  `salary` decimal(10, 2) NULL DEFAULT NULL,
  PRIMARY KEY (`employee_id`) USING BTREE,
  CONSTRAINT `month_salary_ibfk_1` FOREIGN KEY (`employee_id`) REFERENCES `employee` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of month_salary
-- ----------------------------
INSERT INTO `month_salary` VALUES (1, 3000.00);

-- ----------------------------
-- Table structure for work_record
-- ----------------------------
DROP TABLE IF EXISTS `work_record`;
CREATE TABLE `work_record`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `employee_id` int(11) NULL DEFAULT NULL,
  `date` bigint(14) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `employee_id`(`employee_id`) USING BTREE,
  CONSTRAINT `work_record_ibfk_1` FOREIGN KEY (`employee_id`) REFERENCES `employee` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of work_record
-- ----------------------------
INSERT INTO `work_record` VALUES (1, 1, 1653062400000);
INSERT INTO `work_record` VALUES (2, 1, 1653148800000);
INSERT INTO `work_record` VALUES (3, 1, 1653235200000);

-- ----------------------------
-- View structure for year_bonus
-- ----------------------------
DROP VIEW IF EXISTS `year_bonus`;
CREATE ALGORITHM = UNDEFINED SQL SECURITY DEFINER VIEW `year_bonus` AS select `e`.`id` AS `id`,`e`.`name` AS `name`,(((`m`.`salary` * 12) + `b`.`sum_bonus`) / 12) AS `bonus` from ((`salarymanagementsystem`.`employee` `e` left join (select `salarymanagementsystem`.`allowance`.`employee_id` AS `employee_id`,sum(`salarymanagementsystem`.`allowance`.`bonus`) AS `sum_bonus` from `salarymanagementsystem`.`allowance` group by `salarymanagementsystem`.`allowance`.`employee_id`) `b` on((`e`.`id` = `b`.`employee_id`))) left join `salarymanagementsystem`.`month_salary` `m` on((`e`.`id` = `m`.`employee_id`)));

SET FOREIGN_KEY_CHECKS = 1;
