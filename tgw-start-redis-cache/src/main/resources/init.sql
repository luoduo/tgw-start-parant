/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 50723
 Source Host           : localhost:3306
 Source Schema         : loansms

 Target Server Type    : MySQL
 Target Server Version : 50723
 File Encoding         : 65001

 Date: 29/11/2019 18:08:53
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for loan_api_channel
-- ----------------------------
DROP TABLE IF EXISTS `loan_api_channel`;
CREATE TABLE `loan_api_channel`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `api_code` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '渠道编码',
  `api_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '渠道名称',
  `is_delete` tinyint(2) NULL DEFAULT NULL COMMENT '逻辑删除字段',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of loan_api_channel
-- ----------------------------
INSERT INTO `loan_api_channel` VALUES (1, 'gnh', '给你花', 1, '2019-11-29 18:09:58');
INSERT INTO `loan_api_channel` VALUES (2, 'jdq', '借点钱', 1, '2019-11-29 18:09:58');
INSERT INTO `loan_api_channel` VALUES (3, 'dwd', '融泽财富', 1, '2019-11-29 18:09:58');
INSERT INTO `loan_api_channel` VALUES (4, 'app', '超级丽丽', 0, '2019-11-29 18:09:58');

SET FOREIGN_KEY_CHECKS = 1;
