/*
 Navicat Premium Data Transfer

 Source Server         : MySQL
 Source Server Type    : MySQL
 Source Server Version : 50710
 Source Host           : localhost
 Source Database       : eif_ftc

 Target Server Type    : MySQL
 Target Server Version : 50710
 File Encoding         : utf-8

 Date: 05/05/2016 13:22:13 PM
*/

SET NAMES utf8;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
--  Table structure for `t_amc_fund_offline_detail`
-- ----------------------------
DROP TABLE IF EXISTS `t_amc_fund_offline_detail`;
CREATE TABLE `t_amc_fund_offline_detail` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `fund_offline_detail_uuid` char(32)  NOT NULL COMMENT '基金线下资产明细表的UUID',
  `member_no` char(32)  NOT NULL COMMENT '会员NO',
  `product_id` bigint(20) NOT NULL COMMENT '用户产品ID',
  `fund_total_amount` decimal(26,6) NOT NULL COMMENT '该只基金的总金额',
  `total_profit` decimal(26,6) NOT NULL COMMENT '用户累计收益',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  `bonus_total_amount` decimal(26,6) NOT NULL COMMENT '红利总额',
  `profit_total_amount` decimal(26,6) NOT NULL COMMENT '加息券总额',
  `settlement_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '清盘时间',
  `has_settlement` int(4) NOT NULL COMMENT '是否清盘，1-否，2-是',
  `settlement_capital` decimal(26,6) NOT NULL COMMENT '兑付本金',
  `offline_code` char(32)  NOT NULL COMMENT '幂等键',
  `customer_phone` char(32)  NOT NULL,
  `customer_name` char(32)  NOT NULL,
  `customer_cardno` char(32)  NOT NULL,
  `product_name` varchar(32)  NOT NULL,
  `inception_date` datetime NOT NULL,
  `due_date` datetime NOT NULL,
  `offline_mark` int(4) NOT NULL COMMENT '线下资产标记',
  `soft_deleted` int(4) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `offline_code` (`offline_code`),
  KEY `idx_offline_uuid` (`fund_offline_detail_uuid`) USING BTREE,
  KEY `idx_offline_member_no` (`member_no`) USING BTREE
) ENGINE=InnoDB COMMENT='用户线下基金资产明细，存放每个人买了多少的某只基金';

SET FOREIGN_KEY_CHECKS = 1;
