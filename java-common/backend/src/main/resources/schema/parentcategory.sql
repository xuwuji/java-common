/*
 Navicat Premium Data Transfer

 Source Server         : mysql
 Source Server Type    : MySQL
 Source Server Version : 80015
 Source Host           : localhost:3306
 Source Schema         : eshop

 Target Server Type    : MySQL
 Target Server Version : 80015
 File Encoding         : 65001

 Date: 05/03/2019 17:50:00
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for parentcategory
-- ----------------------------
DROP TABLE IF EXISTS `parentcategory`;
CREATE TABLE `parentcategory`  (
  `id` int(11) UNSIGNED NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET gb2312 COLLATE gb2312_chinese_ci NULL DEFAULT NULL,
  `state` varchar(255) CHARACTER SET gb2312 COLLATE gb2312_chinese_ci NULL DEFAULT NULL COMMENT '状态，下架0，上架1',
  `lastModifyTime` timestamp(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0),
  `createTime` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 9 CHARACTER SET = gb2312 COLLATE = gb2312_chinese_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of parentcategory
-- ----------------------------
INSERT INTO `parentcategory` VALUES (1, 'd', '0', NULL, '2019-03-05 14:21:28');
INSERT INTO `parentcategory` VALUES (2, 'd', '0', NULL, '2019-03-05 14:21:28');
INSERT INTO `parentcategory` VALUES (3, 'd', '0', NULL, '2019-03-05 14:21:28');
INSERT INTO `parentcategory` VALUES (4, 'dsad', '0', NULL, '2019-03-05 14:21:28');
INSERT INTO `parentcategory` VALUES (5, '美妆', '1', NULL, '2019-03-05 14:21:28');
INSERT INTO `parentcategory` VALUES (6, '护肤', '1', NULL, '2019-03-05 14:21:28');
INSERT INTO `parentcategory` VALUES (7, '个人护理', '1', NULL, '2019-03-05 14:21:28');
INSERT INTO `parentcategory` VALUES (8, '母婴幼儿', '1', NULL, '2019-03-05 14:21:28');

SET FOREIGN_KEY_CHECKS = 1;
