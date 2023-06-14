/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 50624
 Source Host           : localhost:3306
 Source Schema         : dddaka

 Target Server Type    : MySQL
 Target Server Version : 50624
 File Encoding         : 65001

 Date: 12/06/2023 17:59:37
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for call_in_type
-- ----------------------------
DROP TABLE IF EXISTS `call_in_type`;
CREATE TABLE `call_in_type`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '打卡类型',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for clock_in0
-- ----------------------------
DROP TABLE IF EXISTS `clock_in0`;
CREATE TABLE `clock_in0`  (
  `id` bigint(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `user_name` varchar(254) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户名',
  `clock_date` datetime(0) NULL DEFAULT NULL COMMENT '打卡日期',
  `dept` varchar(254) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '部门',
  `title` varchar(254) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '岗位',
  `job_number` varchar(254) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '工号',
  `location` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '地理位置',
  `clock_in_time` datetime(0) NULL DEFAULT NULL COMMENT '上班打卡时间',
  `knock_off_time` datetime(0) NULL DEFAULT NULL COMMENT '下班打卡时间',
  `work_time_result` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '上班时长',
  `type` tinyint(2) NOT NULL COMMENT '打卡类别',
  `over_time` datetime(0) NULL DEFAULT NULL COMMENT '加班开始时间',
  `over_time_end` datetime(0) NULL DEFAULT NULL COMMENT '加班结束时间',
  `over_time_result` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '加班时长',
  `project` varchar(1024) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '打卡项目',
  `field_location` varchar(254) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '外勤上班打卡位置',
  `field_knock_location` varchar(254) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '外勤下班打卡位置',
  `late_situation` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '迟到情况',
  `early_situation` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '早退情况',
  `note` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '打卡备注',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for clock_in1
-- ----------------------------
DROP TABLE IF EXISTS `clock_in1`;
CREATE TABLE `clock_in1`  (
  `id` bigint(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `user_name` varchar(254) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户名',
  `clock_date` datetime(0) NULL DEFAULT NULL COMMENT '打卡日期',
  `dept` varchar(254) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '部门',
  `title` varchar(254) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '岗位',
  `job_number` varchar(254) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '工号',
  `location` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '地理位置',
  `clock_in_time` datetime(0) NULL DEFAULT NULL COMMENT '上班打卡时间',
  `knock_off_time` datetime(0) NULL DEFAULT NULL COMMENT '下班打卡时间',
  `work_time_result` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '上班时长',
  `type` tinyint(2) NOT NULL COMMENT '打卡类别',
  `over_time` datetime(0) NULL DEFAULT NULL COMMENT '加班开始时间',
  `over_time_end` datetime(0) NULL DEFAULT NULL COMMENT '加班结束时间',
  `over_time_result` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '加班时长',
  `project` varchar(1024) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '打卡项目',
  `field_location` varchar(254) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '外勤上班打卡位置',
  `field_knock_location` varchar(254) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '外勤下班打卡位置',
  `late_situation` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '迟到情况',
  `early_situation` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '早退情况',
  `note` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '打卡备注',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for clock_in2
-- ----------------------------
DROP TABLE IF EXISTS `clock_in2`;
CREATE TABLE `clock_in2`  (
  `id` bigint(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `user_name` varchar(254) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户名',
  `clock_date` datetime(0) NULL DEFAULT NULL COMMENT '打卡日期',
  `dept` varchar(254) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '部门',
  `title` varchar(254) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '岗位',
  `job_number` varchar(254) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '工号',
  `location` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '地理位置',
  `clock_in_time` datetime(0) NULL DEFAULT NULL COMMENT '上班打卡时间',
  `knock_off_time` datetime(0) NULL DEFAULT NULL COMMENT '下班打卡时间',
  `work_time_result` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '上班时长',
  `type` tinyint(2) NOT NULL COMMENT '打卡类别',
  `over_time` datetime(0) NULL DEFAULT NULL COMMENT '加班开始时间',
  `over_time_end` datetime(0) NULL DEFAULT NULL COMMENT '加班结束时间',
  `over_time_result` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '加班时长',
  `project` varchar(1024) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '打卡项目',
  `field_location` varchar(254) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '外勤上班打卡位置',
  `field_knock_location` varchar(254) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '外勤下班打卡位置',
  `late_situation` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '迟到情况',
  `early_situation` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '早退情况',
  `note` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '打卡备注',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1667729794468872195 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for clock_in_5
-- ----------------------------
DROP TABLE IF EXISTS `clock_in_5`;
CREATE TABLE `clock_in_5`  (
  `id` bigint(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `user_name` varchar(254) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户名',
  `clock_date` datetime(0) NULL DEFAULT NULL COMMENT '打卡日期',
  `dept` varchar(254) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '部门',
  `title` varchar(254) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '岗位',
  `job_number` varchar(254) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '工号',
  `location` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '地理位置',
  `clock_in_time` datetime(0) NULL DEFAULT NULL COMMENT '上班打卡时间',
  `knock_off_time` datetime(0) NULL DEFAULT NULL COMMENT '下班打卡时间',
  `type` tinyint(2) NOT NULL COMMENT '打卡类别',
  `over_time` datetime(0) NULL DEFAULT NULL COMMENT '加班开始时间',
  `over_time_end` datetime(0) NULL DEFAULT NULL COMMENT '加班结束时间',
  `project` varchar(1024) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '打卡项目',
  `note` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '打卡备注',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for current_status
-- ----------------------------
DROP TABLE IF EXISTS `current_status`;
CREATE TABLE `current_status`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `status_name` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '状态名称',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for customer_baobiao
-- ----------------------------
DROP TABLE IF EXISTS `customer_baobiao`;
CREATE TABLE `customer_baobiao`  (
  `id` bigint(20) NOT NULL COMMENT '主键ID',
  `customer_id` bigint(20) NOT NULL COMMENT '客户ID',
  `visit_situation` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '拜访情况',
  `price_situation` varchar(254) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '价格洽谈结果',
  `pay_for_situation` varchar(254) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '付款条件情况',
  `existing_problem` varchar(254) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '存在问题',
  `solution` varchar(254) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '解决方案',
  `feedback_user` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '反馈人员',
  `customer_update_time` datetime(0) NULL DEFAULT NULL COMMENT '客户人员反馈时间',
  `research_situation` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '研发情况',
  `research_existing_problem` varchar(254) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '存在问题',
  `research_solution` varchar(254) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '解决方案',
  `research_feedback_user` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '反馈人员',
  `research_update_time` datetime(0) NULL DEFAULT NULL COMMENT '研发人员反馈时间',
  `sampling_time` varchar(64) comment '打样时间',
  `sampling_info` varchar(64) comment '样品信息',
  `coating_scheme` varchar(254) comment '涂层方案',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for customer_info
-- ----------------------------
DROP TABLE IF EXISTS `customer_info`;
CREATE TABLE `customer_info`  (
  `id` bigint(20) NOT NULL COMMENT '主键',
  `customer_code` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '客户代码',
  `customer_name` varchar(254) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '客户名称',
  `customer_type` char(11) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '客户类别',
  `current_status` char(11) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '当前阶段',
  `customer_address` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '客户地址',
  `customer_phone` char(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '客户手机或联系人',
  `production_capacity` varchar(32) comment '生产能力',
  `sale_per_year` int comment '年销售额',
  `device_name` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '预售设备',
  `customer_need` int comment '客户需求',
  `business_competitor` varchar(64) comment '友商竞争情况',
  `is_market` char(4) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '是否上市',
  `operate_user` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '操作客服',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `IDX_cus`(`customer_name`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for customer_record
-- ----------------------------
DROP TABLE IF EXISTS `customer_record`;
CREATE TABLE `customer_record`  (
  `id` bigint(20) NOT NULL COMMENT '主键',
  `customer_id` bigint(20) NOT NULL COMMENT '客户ID',
  `visit_situation` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '拜访情况',
  `price_situation` varchar(254) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '价格洽谈结果',
  `pay_for_situation` varchar(254) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '付款条件情况',
  `existing_problem` varchar(254) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '存在问题',
  `solution` varchar(254) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '解决方案',
  `feedback_user` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '反馈人员',
  `update_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `IDX_CUS_RECORD`(`customer_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for customer_type
-- ----------------------------
DROP TABLE IF EXISTS `customer_type`;
CREATE TABLE `customer_type`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '客户类别',
  `type_name` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '类别名称',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for project_call_in
-- ----------------------------
DROP TABLE IF EXISTS `project_call_in`;
CREATE TABLE `project_call_in`  (
  `id` bigint(20) NOT NULL COMMENT '主键',
  `project_name` varchar(254) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '打卡项目名称',
  `project_id` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '打卡项目编号',
  `project_status` tinyint(4) NULL DEFAULT 1 COMMENT '打卡项目状态',
  `create_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for research_record
-- ----------------------------
DROP TABLE IF EXISTS `research_record`;
CREATE TABLE `research_record`  (
  `id` bigint(20) NOT NULL COMMENT '主键',
  `customer_id` bigint(20) NOT NULL COMMENT '客户ID',
  `sampling_time` varchar(64) comment '打样时间',
  `sampling_info` varchar(64) comment '样品信息',
  `coating_scheme` varchar(254) comment '涂层方案',
  `research_situation` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '工况信息',
  `existing_problem` varchar(254) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '存在问题',
  `solution` varchar(254) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '解决方案',
  `feedback_user` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '反馈人员',
  `update_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `IDX_RESEARCH_RECORD`(`customer_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user`  (
  `id` int(11) NOT NULL,
  `user_name` varchar(254) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `password` varchar(254) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `status` int(11) NULL DEFAULT 0,
  `note` varchar(254) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

SET FOREIGN_KEY_CHECKS = 1;
