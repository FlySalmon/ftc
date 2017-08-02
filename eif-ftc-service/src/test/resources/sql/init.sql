-- MySQL dump 10.13  Distrib 5.7.10, for linux-glibc2.5 (x86_64)
--
-- Host: localhost    Database: eif_ftc
-- ------------------------------------------------------
-- Server version	5.7.10-log

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
-- Current Database: `eif_ftc`
--

--
-- Table structure for table `t_amc_fund_bonus_detail_alteration`
--

DROP TABLE IF EXISTS `t_amc_fund_bonus_detail_alteration`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_amc_fund_bonus_detail_alteration` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `bonus_alteration_uuid` char(32) NOT NULL,
  `fund_detail_id` bigint(20) NOT NULL COMMENT '基金资产明细id',
  `fund_detail_uuid` char(32) NOT NULL COMMENT '基金资产明细表的UUID',
  `fund_total_id` bigint(20) NOT NULL COMMENT '基金资产汇总表的ID',
  `fund_total_uuid` char(32) NOT NULL COMMENT '基金资产汇总表的uuid',
  `ftc_order_no` char(32) NOT NULL COMMENT 'FTC红利单号',
  `fund_bonus_amount` decimal(26,6) NOT NULL COMMENT '该只基金的红利金额',
  `bonus_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '红利发放日期',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`),
  KEY `idx_t_amc_fund_bon_dtl_alt_collection` (`fund_detail_uuid`,`create_time`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `t_amc_fund_detail`
--

DROP TABLE IF EXISTS `t_amc_fund_detail`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_amc_fund_detail` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `fund_detail_uuid` char(32) NOT NULL COMMENT '基金资产明细表的UUID',
  `fund_total_id` bigint(20) NOT NULL COMMENT '基金资产汇总表的ID',
  `fund_total_uuid` char(32) NOT NULL COMMENT '基金资产汇总表的uuid',
  `product_id` bigint(20) NOT NULL COMMENT '用户产品ID',
  `fund_total_share` decimal(26,6) NOT NULL COMMENT '该只基金的总份额',
  `fund_total_amount` decimal(26,6) NOT NULL COMMENT '该只基金的总金额',
  `yesterday_profit` decimal(26,6) NOT NULL COMMENT '用户昨日收益',
  `total_profit` decimal(26,6) NOT NULL COMMENT '用户累计收益',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  `frozen_amount` decimal(26,6) NOT NULL COMMENT '冻结金额',
  `frozen_share` decimal(26,6) NOT NULL COMMENT '冻结份额',
  `confirmed_add_amount` decimal(26,6) NOT NULL COMMENT '确认增加金额',
  `confirmed_add_share` decimal(26,6) NOT NULL COMMENT '确认增加份额',
  `unconfirmed_add_amount` decimal(26,6) NOT NULL COMMENT '未确认增加金额',
  `unconfirmed_add_share` decimal(26,6) NOT NULL COMMENT '未确认增加份额',
  `confirmed_sub_amount` decimal(26,6) NOT NULL COMMENT '确认减少金额',
  `confirmed_sub_share` decimal(26,6) NOT NULL COMMENT '确认减少份额',
  `unconfirmed_sub_amount` decimal(26,6) NOT NULL COMMENT '未确认减少金额',
  `unconfirmed_sub_share` decimal(26,6) NOT NULL COMMENT '未确认减少份额',
  `bonus_total_amount` decimal(26,6) NOT NULL COMMENT '红利总额',
  `profit_total_amount` decimal(26,6) NOT NULL COMMENT '加息券总额',
  `expect_bonus_amount` decimal(26,6) NOT NULL COMMENT '预期红利',
  `settlement_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '清盘时间',
  `expect_profit_amount` decimal(26,6) NOT NULL COMMENT '预期加息券',
  `has_settlement` int(4) NOT NULL COMMENT '是否清盘,1-否,2-是',
  `settlement_capital` decimal(26,6) NOT NULL COMMENT '兑付本金',
  PRIMARY KEY (`id`),
  UNIQUE KEY `fund_total_uuid` (`fund_total_uuid`,`product_id`),
  KEY `idx_t_amc_fund_dtl_uuid` (`fund_detail_uuid`),
  KEY `idx_t_amc_fund_dtl_prodid` (`product_id`)
);
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `t_amc_fund_detail_alteration`
--

DROP TABLE IF EXISTS `t_amc_fund_detail_alteration`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_amc_fund_detail_alteration` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `detail_alteration_uuid` char(32) NOT NULL COMMENT '基金资产明细变动UUID',
  `fund_detail_id` bigint(20) NOT NULL COMMENT '基金资产明细ID',
  `fund_detail_uuid` char(32) NOT NULL COMMENT '基金资产明细表的UUID',
  `fund_total_id` bigint(20) NOT NULL COMMENT '基金资产汇总表的ID',
  `fund_total_uuid` char(32) NOT NULL COMMENT '基金资产汇总表的uuid',
  `fund_share` decimal(26,6) NOT NULL COMMENT '该只基金的总份额',
  `fund_amount` decimal(26,6) NOT NULL COMMENT '该只基金的总金额',
  `alteration_status` int(4) NOT NULL COMMENT '基金的变动状态,0-TA未确认,1-TA已确认,2-TA取消',
  `alteration_type` int(4) NOT NULL COMMENT '基金变动类型,0-增加,1-减少',
  `ftc_order_no` char(32) NOT NULL COMMENT '金融交易系统单号',
  `ftc_order_create_time` timestamp NOT NULL DEFAULT '1970-01-01 00:00:00' COMMENT 'FTC单号创建时间',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`),
  KEY `idx_t_amc_fund_dtl_alt` (`ftc_order_no`) ,
  KEY `idx_t_ct_20160321` (`create_time`) ,
  KEY `idx_t_amc_fund_dtl_alt_dtuuid` (`fund_detail_uuid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `t_amc_fund_offline_detail`
--

DROP TABLE IF EXISTS `t_amc_fund_offline_detail`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_amc_fund_offline_detail` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `fund_offline_detail_uuid` char(32) NOT NULL COMMENT '基金线下资产明细表的UUID',
  `member_no` char(32) NOT NULL COMMENT '会员NO',
  `product_id` bigint(20) NOT NULL COMMENT '用户产品ID',
  `fund_total_amount` decimal(26,6) NOT NULL COMMENT '该只基金的总金额',
  `total_profit` decimal(26,6) NOT NULL COMMENT '用户累计收益',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  `bonus_total_amount` decimal(26,6) NOT NULL COMMENT '红利总额',
  `profit_total_amount` decimal(26,6) NOT NULL COMMENT '加息券总额',
  `settlement_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '清盘时间',
  `has_settlement` int(4) NOT NULL COMMENT '是否清盘,1-否,2-是',
  `settlement_capital` decimal(26,6) NOT NULL COMMENT '兑付本金',
  `offline_code` char(32) NOT NULL COMMENT '幂等键',
  `customer_phone` char(32) NOT NULL,
  `customer_name` char(32) NOT NULL,
  `customer_cardno` char(32) NOT NULL,
  `product_name` varchar(32) NOT NULL,
  `inception_date` datetime NOT NULL,
  `due_date` datetime NOT NULL,
  `offline_mark` int(4) NOT NULL COMMENT '线下资产标记',
  `soft_deleted` int(4) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `offline_code` (`offline_code`),
  KEY `idx_offline_uuid` (`fund_offline_detail_uuid`) ,
  KEY `idx_offline_member_no` (`member_no`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `t_amc_fund_profit_alteration`
--

DROP TABLE IF EXISTS `t_amc_fund_profit_alteration`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_amc_fund_profit_alteration` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `profit_alteration_uuid` char(32) NOT NULL COMMENT '基金资产明细变动UUID',
  `fund_detail_id` bigint(20) NOT NULL COMMENT '基金资产明细ID',
  `fund_detail_uuid` char(32) NOT NULL COMMENT '基金资产明细表的UUID',
  `fund_total_id` bigint(20) NOT NULL COMMENT '基金资产汇总表的ID',
  `fund_total_uuid` char(32) NOT NULL COMMENT '基金资产汇总表的uuid',
  `profit_amount` decimal(26,6) NOT NULL COMMENT '该只基金加息的金额',
  `ftc_order_no` char(32) NOT NULL COMMENT '金融交易系统单号',
  `profit_add_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '加息时间',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `t_amc_fund_total`
--

DROP TABLE IF EXISTS `t_amc_fund_total`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_amc_fund_total` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `fund_total_uuid` char(32) NOT NULL COMMENT '基金资产汇总UUID',
  `member_asset_id` bigint(20) NOT NULL,
  `member_asset_uuid` char(32) NOT NULL,
  `fund_acc_id` bigint(20) NOT NULL COMMENT '用户资产账户id',
  `fund_acc_uuid` char(32) NOT NULL COMMENT '用户资产账户uuid',
  `total_asset` decimal(26,6) NOT NULL COMMENT '用户总资产',
  `yesterday_profit` decimal(26,6) NOT NULL COMMENT '用户昨日收益',
  `total_profit` decimal(26,6) NOT NULL COMMENT '用户累计收益',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  `frozen_amount` decimal(26,6) NOT NULL COMMENT '冻结金额',
  `frozen_share` decimal(26,6) NOT NULL COMMENT '冻结份额',
  `confirmed_add_amount` decimal(26,6) NOT NULL COMMENT '确认增加金额',
  `confirmed_add_share` decimal(26,6) NOT NULL COMMENT '确认增加份额',
  `unconfirmed_add_amount` decimal(26,6) NOT NULL COMMENT '未确认增加金额',
  `unconfirmed_add_share` decimal(26,6) NOT NULL COMMENT '未确认增加份额',
  `confirmed_sub_amount` decimal(26,6) NOT NULL COMMENT '确认减少金额',
  `confirmed_sub_share` decimal(26,6) NOT NULL COMMENT '确认减少份额',
  `unconfirmed_sub_amount` decimal(26,6) NOT NULL COMMENT '未确认减少金额',
  `unconfirmed_sub_share` decimal(26,6) NOT NULL COMMENT '未确认减少份额',
  PRIMARY KEY (`id`),
  KEY `idx_t_amc_fund_total_uuid` (`fund_total_uuid`),
  KEY `idx_t_amc_fund_total_acc_uuid` (`fund_acc_uuid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `t_amc_mem_asset`
--

DROP TABLE IF EXISTS `t_amc_mem_asset`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_amc_mem_asset` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `member_asset_uuid` char(32) NOT NULL COMMENT '用户资产uuid',
  `member_no` char(32) NOT NULL COMMENT '会员号',
  `channel_no` int(4) NOT NULL COMMENT '交易渠道号',
  `total_asset` decimal(26,6) NOT NULL COMMENT '用户总资产',
  `yesterday_profit` decimal(26,6) NOT NULL COMMENT '用户昨日收益',
  `total_profit` decimal(26,6) NOT NULL COMMENT '用户累计收益',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  `frozen_amount` decimal(26,6) NOT NULL COMMENT '冻结金额',
  `frozen_share` decimal(26,6) NOT NULL COMMENT '冻结份额',
  `confirmed_add_amount` decimal(26,6) NOT NULL COMMENT '确认增加金额',
  `confirmed_add_share` decimal(26,6) NOT NULL COMMENT '确认增加份额',
  `unconfirmed_add_amount` decimal(26,6) NOT NULL COMMENT '未确认增加金额',
  `unconfirmed_add_share` decimal(26,6) NOT NULL COMMENT '未确认增加份额',
  `confirmed_sub_amount` decimal(26,6) NOT NULL COMMENT '确认扣减金额',
  `confirmed_sub_share` decimal(26,6) NOT NULL COMMENT '确认扣减份额',
  `unconfirmed_sub_amount` decimal(26,6) NOT NULL COMMENT '未确认扣减金额',
  `unconfirmed_sub_share` decimal(26,6) NOT NULL COMMENT '未确认扣减份额',
  PRIMARY KEY (`id`),
  KEY `idx_t_amc_mem_asset_collection` (`member_no`,`channel_no`) ,
  KEY `idx_t_amc_mem_asset_uuid` (`member_asset_uuid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `t_amc_mem_fund_acc`
--

DROP TABLE IF EXISTS `t_amc_mem_fund_acc`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_amc_mem_fund_acc` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `fund_acc_uuid` char(32) NOT NULL COMMENT '用户交易账户UUID',
  `ftc_create_acc_no` char(32) NOT NULL COMMENT 'ftc的开户业务单',
  `member_no` char(32) NOT NULL COMMENT '会员号',
  `channel_no` int(4) NOT NULL COMMENT '交易渠道号',
  `transaction_account` char(32) NOT NULL COMMENT '基金交易账号',
  `ta_company_no` bigint(20) NOT NULL COMMENT '基金公司号,根据购买的产品确定（我们平台对TA的编号）',
  `account_status` int(4) NOT NULL COMMENT '账户状态,1-创建中,2-开户成功,3-开户失败',
  `ta_account` char(32) NOT NULL COMMENT 'TA账户号',
  `ftc_create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT 'FTC创建时间,跑批处理用',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  `account_risk_status` int(4) NOT NULL DEFAULT '1' COMMENT '风控字段,1-正常,2-禁止交易',
  PRIMARY KEY (`id`),
  KEY `idx_t_amc_fund_acc_acc_uuid` (`fund_acc_uuid`) ,
  KEY `idx_t_amc_fund_acc_mem_no` (`member_no`) ,
  KEY `idx_t_amc_fund_acc_ftc_order_no` (`ftc_create_acc_no`) ,
  KEY `idx_t_amc_fund_acc_trans_acc_ta_acc_collection` (`transaction_account`,`ta_account`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `t_ftc_batch_apply_failure_detail`
--

DROP TABLE IF EXISTS `t_ftc_batch_apply_failure_detail`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_ftc_batch_apply_failure_detail` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `batch_id` bigint(20) NOT NULL COMMENT '批次Id',
  `order_no` char(32) NOT NULL COMMENT '订单号',
  `business_code` char(3) NOT NULL COMMENT '业务代码',
  `error_code` char(11) NOT NULL DEFAULT '""' COMMENT '错误代码',
  `error_msg` varchar(1024) NOT NULL DEFAULT '""' COMMENT '错误信息',
  `status` smallint(1) NOT NULL COMMENT '状态：1 - 待处理,2 - 已处理,3 - 无效',
  `create_time` timestamp NOT NULL DEFAULT '1970-01-01 00:00:00' COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  `operator` char(32) DEFAULT '""' COMMENT '操作人',
  `remark` varchar(255) DEFAULT '' COMMENT '备注',
  PRIMARY KEY (`id`),
  KEY `batch_business_order_idx` (`batch_id`,`business_code`,`order_no`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `t_ftc_batch_confirm_failure_detail`
--

DROP TABLE IF EXISTS `t_ftc_batch_confirm_failure_detail`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_ftc_batch_confirm_failure_detail` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `batch_id` bigint(20) NOT NULL COMMENT '批次Id',
  `serial_no` char(32) NOT NULL COMMENT '序列号',
  `business_code` char(3) NOT NULL COMMENT '业务代码',
  `error_code` char(11) NOT NULL DEFAULT '""' COMMENT '错误代码',
  `error_msg` varchar(1024) NOT NULL DEFAULT '""' COMMENT '错误信息',
  `status` smallint(1) NOT NULL COMMENT '状态：1 - 待处理,2 - 已处理,3 - 无效',
  `create_time` timestamp NOT NULL DEFAULT '1970-01-01 00:00:00' COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  `operator` char(32) DEFAULT '""' COMMENT '操作人',
  `remark` varchar(255) DEFAULT '' COMMENT '备注',
  PRIMARY KEY (`id`),
  KEY `batch_business_serialno_idx` (`batch_id`,`business_code`,`serial_no`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `t_ftc_batch_task_log`
--

DROP TABLE IF EXISTS `t_ftc_batch_task_log`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_ftc_batch_task_log` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `business_code` char(3) NOT NULL COMMENT '业务代码',
  `start_time` timestamp NOT NULL DEFAULT '1970-01-01 00:00:00' COMMENT '起始时间',
  `end_time` timestamp NOT NULL DEFAULT '1970-01-01 00:00:00' COMMENT '终止时间',
  `total_count` int(4) NOT NULL DEFAULT '0' COMMENT '处理记录总数',
  `success_count` int(4) NOT NULL DEFAULT '0' COMMENT '订单处理成功数',
  `failure_count` int(4) NOT NULL DEFAULT '0' COMMENT '订单处理失败数',
  `invalid_count` int(4) NOT NULL DEFAULT '0' COMMENT '无效订单数',
  PRIMARY KEY (`id`),
  KEY `business_time_idx` (`business_code`,`start_time`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `t_ftc_fund_acct_order`
--

DROP TABLE IF EXISTS `t_ftc_fund_acct_order`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_ftc_fund_acct_order` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `member_no` char(32) NOT NULL COMMENT '会员号',
  `product_id` bigint(20) NOT NULL COMMENT '用户产品ID',
  `asset_account_no` char(32) NOT NULL DEFAULT '' COMMENT '资产账户号',
  `fund_acct_order_no` char(32) NOT NULL COMMENT '基金账户业务单号',
  `fund_trans_acct_no` char(17) NOT NULL COMMENT '基金交易账户号',
  `ref_fund_trans_order_no` char(32) NOT NULL DEFAULT '' COMMENT '关联基金交易业务单号',
  `fund_acct_oper_type` int(11) NOT NULL COMMENT '基金账户操作类型: 0 - 开户',
  `tracking_no` char(32) NOT NULL COMMENT '防止幂等,追踪',
  `ref_fund_business_code` char(3) NOT NULL DEFAULT '' COMMENT 'ta业务代码',
  `biz_channel` int(11) NOT NULL COMMENT '渠道类型：IOS = 11; ANDROID = 12; H5 = 13; WECHAT = 14; WEB = 20;',
  `fund_interact_type` int(11) NOT NULL COMMENT '基金提交方式：1 - 实时; 2 - 文件',
  `status` int(11) NOT NULL DEFAULT '-1' COMMENT '状态: 1 - 资产账户开户中; 2 - 资产账户开户成功; 3 - 资产账户开户失败; 4 - 基金公司开户中; 5 - 基金公司开户成功;  6 - 基金公司开户失败',
  `total_expiry_time` timestamp NOT NULL DEFAULT '1970-01-01 00:00:00' COMMENT '总有效期',
  `cur_status_expiry_time` timestamp NOT NULL DEFAULT '1970-01-01 00:00:00' COMMENT '当前状态过期时间',
  `ref_app_sheet_serial_no` char(24) NOT NULL DEFAULT '' COMMENT 'ta申请单号, 可关联请求,确认,结果',
  `ext_info_order_no` char(32) NOT NULL DEFAULT '' COMMENT 'reserved',
  `remark` varchar(255) NOT NULL DEFAULT '' COMMENT '备注',
  `ext_field` varchar(255) NOT NULL DEFAULT '' COMMENT '业务扩展字段,保存json字串',
  `trans_time` timestamp NOT NULL DEFAULT '1970-01-01 00:00:00' COMMENT '处理时间',
  `create_time` timestamp NOT NULL DEFAULT '1970-01-01 00:00:00' COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  `operator_no` char(32) NOT NULL DEFAULT '' COMMENT '操作人号',
  PRIMARY KEY (`id`),
  UNIQUE KEY `unique_tracking_no` (`tracking_no`),
  KEY `acc_serial_no_idx` (`ref_app_sheet_serial_no`),
  KEY `acct_trans_time_idx` (`trans_time`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `t_ftc_fund_acct_order_status_info`
--

DROP TABLE IF EXISTS `t_ftc_fund_acct_order_status_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_ftc_fund_acct_order_status_info` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `order_id` bigint(20) NOT NULL COMMENT '基金账户业务单主表id',
  `status` int(11) NOT NULL COMMENT '基金业务单状态',
  `update_time` timestamp NOT NULL COMMENT '状态迁移时间',
  `operator_no` char(32) NOT NULL DEFAULT '' COMMENT '操作人',
  `remark` varchar(255) NOT NULL DEFAULT '' COMMENT '备注',
  PRIMARY KEY (`id`),
  KEY `acc_order_status_idx` (`order_id`,`status`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `t_ftc_fund_buying_member`
--

DROP TABLE IF EXISTS `t_ftc_fund_buying_member`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_ftc_fund_buying_member` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `product_id` bigint(20) NOT NULL COMMENT '产品id',
  `member_id` char(32) NOT NULL COMMENT '购买会员号',
  `record_time` timestamp NOT NULL COMMENT '记录日期',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `t_ftc_fund_clearing`
--

DROP TABLE IF EXISTS `t_ftc_fund_clearing`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_ftc_fund_clearing` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `fund_clearing_order_no` char(32) NOT NULL COMMENT '基金清盘业务单号',
  `business_order_item_no` char(32) NOT NULL COMMENT '业务订单项流水号',
  `product_id` bigint(20) NOT NULL COMMENT '产品ID',
  `member_no` char(32) NOT NULL COMMENT '会员ID',
  `transaction_account_id` char(17) NOT NULL COMMENT '投资人基金交易账号',
  `transaction_cfm_date` char(8) NOT NULL COMMENT 'ta交易确认日期,格式为：YYYYMMDD\n            ',
  `currency_type` char(3) NOT NULL COMMENT '结算币种,具体编码依GB/T 12406-2008\n            ',
  `total_frozen_vol` decimal(26,6) NOT NULL DEFAULT '0.000000' COMMENT '基金冻结总份数',
  `fund_vol_balance` decimal(26,6) NOT NULL DEFAULT '0.000000' COMMENT '基金份数余额',
  `achievement_pay` decimal(26,6) NOT NULL DEFAULT '0.000000' COMMENT '业绩报酬',
  `achievement_compen` decimal(26,6) NOT NULL DEFAULT '0.000000' COMMENT '业绩补偿',
  `frozen_balance` decimal(26,6) NOT NULL DEFAULT '0.000000' COMMENT '冻结金额',
  `confirmed_vol` decimal(26,6) NOT NULL DEFAULT '0.000000' COMMENT '基金账户交易确认份数',
  `confirmed_amount` decimal(26,6) NOT NULL DEFAULT '0.000000' COMMENT '每笔交易确认金额,含所有费用的总金额',
  `nav` decimal(7,4) NOT NULL COMMENT '基金单位净值',
  `share_class` char(1) NOT NULL COMMENT '收费方式:0-前收费,1-后收费,表明基金是前收费或后收费基金',
  `transcore_trans_no` char(32) NOT NULL DEFAULT '' COMMENT '交易核心交易单流水号',
  `coupon_amount` decimal(26,6) NOT NULL DEFAULT '0.000000' COMMENT '额外加息金额',
  `external_serial_no` varchar(32) NOT NULL DEFAULT '',
  `status` int(11) NOT NULL COMMENT '记录单处理状态',
  `create_time` timestamp NOT NULL DEFAULT '1970-01-01 00:00:00' COMMENT '创建时间',
  `update_time` timestamp NOT NULL COMMENT '更新时间',
  `operator_no` char(32) NOT NULL DEFAULT '' COMMENT '操作人号',
  PRIMARY KEY (`id`),
  UNIQUE KEY `prod_mem_idx` (`product_id`,`member_no`,`transaction_account_id`),
  KEY `serial_no_idx` (`external_serial_no`),
  KEY `clearing_biz_order_no_idx` (`business_order_item_no`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `t_ftc_fund_clearing_status_info`
--

DROP TABLE IF EXISTS `t_ftc_fund_clearing_status_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_ftc_fund_clearing_status_info` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `order_id` bigint(20) NOT NULL COMMENT '基金清盘单主表id',
  `status` int(11) NOT NULL COMMENT '基金清盘单状态',
  `update_time` timestamp NOT NULL COMMENT '状态迁移时间',
  `operator_no` char(32) NOT NULL DEFAULT '' COMMENT '操作人',
  `remark` varchar(255) NOT NULL DEFAULT '' COMMENT '备注',
  PRIMARY KEY (`id`),
  KEY `clearing_order_status_idx` (`order_id`,`status`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `t_ftc_fund_dividend`
--

DROP TABLE IF EXISTS `t_ftc_fund_dividend`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_ftc_fund_dividend` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `fund_dividend_order_no` char(32) NOT NULL COMMENT '基金分红业务单号',
  `business_order_item_no` char(32) NOT NULL COMMENT '业务订单项流水号',
  `product_id` bigint(20) NOT NULL COMMENT '产品ID',
  `member_no` char(32) NOT NULL COMMENT '会员号',
  `transaction_account_id` char(17) NOT NULL COMMENT '投资人基金交易账号',
  `transaction_cfm_date` char(8) NOT NULL COMMENT '交易确认日期,格式为：YYYYMMDD\n            ',
  `confirmed_amount` decimal(16,2) NOT NULL COMMENT '每笔交易确认金额,含所有费用的总金额',
  `charge` decimal(10,2) NOT NULL DEFAULT '0.00' COMMENT '手续费,投资人应付总手续费',
  `agency_fee` decimal(10,2) NOT NULL DEFAULT '0.00' COMMENT '代理费, 手续费中划归销售人的部分\n            ',
  `transfer_fee` decimal(10,2) NOT NULL DEFAULT '0.00' COMMENT '过户费,',
  `share_class` char(1) NOT NULL COMMENT '收费方式:0-前收费,1-后收费,表明基金是前收费或后收费基金',
  `draw_bonus_unit` decimal(10,0) NOT NULL COMMENT '分红单位, 举例：每千份分多少,则分红单位就为一千',
  `dividend_type` char(1) NOT NULL COMMENT '分红类型: 0-普通分红,1-质押基金分红,2-货币基金收益结转,3-保本基金赔付,4-专户到期处理',
  `achievement_pay` decimal(16,2) NOT NULL DEFAULT '0.00' COMMENT '业绩报酬',
  `achievement_compen` decimal(16,2) NOT NULL DEFAULT '0.00' COMMENT '业绩补偿',
  `currency_type` char(3) NOT NULL COMMENT '结算币种,具体编码依GB/T 12406-2008\n            ',
  `basisfor_calculating_dividend` decimal(16,2) NOT NULL COMMENT '红利/红利再投资基数,登记日基金持有人的基金份数',
  `divident_date` char(8) NOT NULL COMMENT '分红日/发放日',
  `vol_of_dividend_for_reinvestment` decimal(16,2) NOT NULL COMMENT '基金账户红利再投资基金份数,投资人实得红股,含被续冻的红股',
  `dividend_amount` decimal(16,2) NOT NULL COMMENT '基金账户红利资金,红利总金额,含冻结红利及再投资的红利',
  `xr_rate` char(8) NOT NULL DEFAULT '' COMMENT '除权日',
  `registration_date` char(8) NOT NULL DEFAULT '' COMMENT '权益登记日期, 格式为：YYYYMMDD',
  `dividend_per_unit` decimal(16,2) NOT NULL COMMENT '单位基金分红金额（含税）,举例：每千份分两元,则此处填2。',
  `def_dividend_method` char(1) NOT NULL COMMENT '默认分红方式: 0-红利转投,1-现金分红,投资人本次分红的方式',
  `transcore_trans_no` char(32) NOT NULL DEFAULT '' COMMENT '交易核心交易单流水号',
  `external_serial_no` varchar(32) NOT NULL DEFAULT '',
  `status` int(11) NOT NULL COMMENT '记录单处理状态',
  `create_time` timestamp NOT NULL DEFAULT '1970-01-01 00:00:00' COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  `operator_no` char(32) NOT NULL DEFAULT '' COMMENT '操作人号',
  PRIMARY KEY (`id`),
  UNIQUE KEY `prod_mem_date_idx` (`product_id`,`member_no`,`transaction_account_id`,`divident_date`),
  KEY `biz_order_no_idx` (`business_order_item_no`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `t_ftc_fund_dividend_status_info`
--

DROP TABLE IF EXISTS `t_ftc_fund_dividend_status_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_ftc_fund_dividend_status_info` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `order_id` bigint(20) NOT NULL COMMENT '基金分红单主表id',
  `STATUS` int(11) NOT NULL COMMENT '基金分红单状态',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '状态迁移时间',
  `operator_no` char(32) NOT NULL DEFAULT '' COMMENT '操作人',
  `remark` varchar(255) NOT NULL DEFAULT '' COMMENT '备注',
  PRIMARY KEY (`id`),
  KEY `dividend_order_status_idx` (`order_id`,`STATUS`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `t_ftc_fund_personal_purchase_daily_vol`
--

DROP TABLE IF EXISTS `t_ftc_fund_personal_purchase_daily_vol`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_ftc_fund_personal_purchase_daily_vol` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `member_no` char(32) NOT NULL DEFAULT '' COMMENT '会员号',
  `product_id` bigint(20) NOT NULL DEFAULT '0' COMMENT '产品id',
  `amount` decimal(26,6) NOT NULL DEFAULT '0.000000' COMMENT '购买金额',
  `shares` decimal(26,6) NOT NULL DEFAULT '0.000000' COMMENT '购买份额',
  `record_time` timestamp NOT NULL DEFAULT '1970-01-01 00:00:00' COMMENT '记录日期',
  PRIMARY KEY (`id`),
  UNIQUE KEY `purchase_mem_prod_time_unique_idx` (`member_no`,`product_id`,`record_time`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `t_ftc_fund_personal_purchase_vol`
--

DROP TABLE IF EXISTS `t_ftc_fund_personal_purchase_vol`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_ftc_fund_personal_purchase_vol` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `product_id` bigint(20) NOT NULL DEFAULT '0' COMMENT '产品id',
  `amount` decimal(26,6) NOT NULL DEFAULT '0.000000' COMMENT '购买金额',
  `shares` decimal(26,6) NOT NULL DEFAULT '0.000000' COMMENT '购买份额',
  `member_no` char(32) NOT NULL COMMENT '会员号',
  PRIMARY KEY (`id`),
  UNIQUE KEY `prod_mem_unique_idx` (`product_id`,`member_no`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `t_ftc_fund_personal_redeem_daily_vol`
--

DROP TABLE IF EXISTS `t_ftc_fund_personal_redeem_daily_vol`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_ftc_fund_personal_redeem_daily_vol` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `member_no` char(32) NOT NULL DEFAULT '' COMMENT '会员号',
  `product_id` bigint(20) NOT NULL DEFAULT '0' COMMENT '产品id',
  `amount` decimal(26,6) NOT NULL DEFAULT '0.000000' COMMENT '赎回金额',
  `shares` decimal(26,6) NOT NULL DEFAULT '0.000000' COMMENT '赎回份额',
  `record_time` timestamp NOT NULL DEFAULT '1970-01-01 00:00:00' COMMENT '记录日期',
  PRIMARY KEY (`id`),
  UNIQUE KEY `redeem_mem_prod_time_unique_idx` (`member_no`,`product_id`,`record_time`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `t_ftc_fund_purchase_daily_vol`
--

DROP TABLE IF EXISTS `t_ftc_fund_purchase_daily_vol`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_ftc_fund_purchase_daily_vol` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `product_id` bigint(20) NOT NULL DEFAULT '0' COMMENT '产品id',
  `amount` decimal(26,6) NOT NULL DEFAULT '0.000000' COMMENT '购买金额',
  `shares` decimal(26,6) NOT NULL DEFAULT '0.000000' COMMENT '购买份额',
  `record_time` timestamp NOT NULL DEFAULT '1970-01-01 00:00:00' COMMENT '记录日期',
  PRIMARY KEY (`id`),
  UNIQUE KEY `prod_time_unique_idx` (`product_id`,`record_time`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `t_ftc_fund_trans_order`
--

DROP TABLE IF EXISTS `t_ftc_fund_trans_order`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_ftc_fund_trans_order` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `fund_trans_order_no` char(32) NOT NULL COMMENT '基金交易业务单号',
  `member_no` char(32) NOT NULL COMMENT '会员号',
  `asset_account_no` char(32) NOT NULL COMMENT '资产账户号',
  `ref_fund_account_no` char(12) NOT NULL DEFAULT '' COMMENT '对应TA投资人基金账号',
  `trans_account_no` char(17) NOT NULL DEFAULT '' COMMENT '投资人基金交易账号',
  `parent_fund_trans_order_no` char(32) NOT NULL DEFAULT '' COMMENT '当此单为组合赎回的子单时对应的母单',
  `ref_fund_trans_order_no` char(32) NOT NULL DEFAULT '' COMMENT '当交易类型为退款,当日赎回时,关联的原始基金交易业务单号;',
  `transcore_trans_no` char(32) NOT NULL DEFAULT '' COMMENT '交易核心交易单流水号',
  `is_adjusted` bit(1) NOT NULL COMMENT '是否为补单',
  `trigger_reason` int(11) NOT NULL COMMENT '0 - 正常; 1 - 充值后触发',
  `business_order_item_no` char(32) NOT NULL DEFAULT '' COMMENT '业务订单项流水号',
  `order_no` char(32) NOT NULL DEFAULT '' COMMENT '用户订单号',
  `tracking_no` char(32) NOT NULL COMMENT '防止幂等, 全局跟踪',
  `product_id` bigint(20) NOT NULL COMMENT '用户产品ID',
  `mer_member_no` char(32) NOT NULL COMMENT '商户号',
  `contract_no` char(32) NOT NULL DEFAULT '' COMMENT '合同流水号, 申购后自动生成',
  `fund_trans_type` int(11) NOT NULL COMMENT '基金交易类型：1 - 认购; 2 - 申购; 3 - 正常赎回; 4 - 申购退款; 5 - 撤销; 6 - 当日赎回; 7 - 认购退款; 8 - 组合赎回; 9 - 无需确认的赎回',
  `fund_trans_amount` decimal(26,6) NOT NULL DEFAULT '0.000000' COMMENT '交易金额',
  `fund_trans_shares` decimal(26,6) NOT NULL DEFAULT '0.000000' COMMENT '交易份额',
  `discount_amount` decimal(26,6) NOT NULL DEFAULT '0.000000' COMMENT '优惠金额',
  `currency_type` char(3) NOT NULL DEFAULT '' COMMENT '币种',
  `ref_fund_code` char(6) NOT NULL DEFAULT '' COMMENT 'ta基金代码',
  `ref_fund_sub_code` char(6) NOT NULL DEFAULT '' COMMENT '外部基金分组编号',
  `ref_fund_business_code` char(3) NOT NULL DEFAULT '' COMMENT 'ta基金业务代码',
  `biz_channel` int(11) NOT NULL COMMENT '渠道类型：IOS = 11; ANDROID = 12; H5 = 13; WECHAT = 14; WEB = 20;',
  `status` int(11) NOT NULL COMMENT '业务单状态：4 - 待支付; 5 - 支付中; 6 - 支付成功; 7 - 支付失败; 8 -  基金交易处理中; 9 - 基金交易成功; 10 - 基金交易失败; 11 - 基金交易结果通知成功; 12 - 退款中; 13 - 退款成功; 14 - 退款失败; 15 - 限额风控挂起; 17 - 取消; 18 - 过期关闭； 19 - 部分退款成功； 20 - 提现中； 21 - 提现成功； 22 - 提现失败; 23 - 赎回提交成功; 24 - 等待短信验证;  25 - 风控挂起; 26 - 风控拒绝',
  `fund_interact_type` int(11) NOT NULL COMMENT '基金提交方式：1 - 实时; 2 - 文件',
  `pay_method` varchar(768) NOT NULL DEFAULT '' COMMENT '支付方式,json表示',
  `pay_method_desc` varchar(512) NOT NULL DEFAULT '' COMMENT '支付方式描述',
  `frozen_code` char(32) NOT NULL DEFAULT '' COMMENT '库存冻结号',
  `total_expiry_time` timestamp NOT NULL DEFAULT '1970-01-01 00:00:00' COMMENT '总有效期',
  `cur_status_expiry_time` timestamp NOT NULL DEFAULT '1970-01-01 00:00:00' COMMENT '当前状态过期时间',
  `ref_app_sheet_serial_no` char(24) NOT NULL DEFAULT '' COMMENT 'ta申请单号, 可关联请求,确认,结果',
  `ext_info_order_no` char(32) NOT NULL DEFAULT '' COMMENT 'reserved',
  `remark` varchar(255) NOT NULL DEFAULT '' COMMENT '备注',
  `ext_field` varchar(255) NOT NULL DEFAULT '' COMMENT '业务扩展字段,保存json字串',
  `trans_time` timestamp NOT NULL DEFAULT '1970-01-01 00:00:00' COMMENT '交易时间',
  `create_time` timestamp NOT NULL DEFAULT '1970-01-01 00:00:00' COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  `finish_time` timestamp NOT NULL DEFAULT '1970-01-01 00:00:00' COMMENT '业务单整体完成时间',
  `refund_time` timestamp NOT NULL DEFAULT '1970-01-01 00:00:00' COMMENT '退款时间,退款发生在已经下单支付但是TA还未处理的时候',
  `cancel_time` timestamp NOT NULL DEFAULT '1970-01-01 00:00:00' COMMENT '业务单撤销时间,撤销发生在已经下单但是还未支付的时候',
  `refund_amount_limit` decimal(26,6) NOT NULL DEFAULT '0.000000' COMMENT '可退款金额',
  `refund_amount` decimal(26,6) NOT NULL DEFAULT '0.000000' COMMENT '已退款金额',
  `refund_fee_amount` decimal(26,6) NOT NULL DEFAULT '0.000000' COMMENT '已退款手续费',
  `to_fee` decimal(26,6) NOT NULL DEFAULT '0.000000' COMMENT '付款方手续费',
  `from_fee` decimal(26,6) NOT NULL DEFAULT '0.000000' COMMENT '收款方手续费',
  `fee_method` int(11) NOT NULL DEFAULT '-1' COMMENT 'reserved',
  `operator_no` char(32) NOT NULL DEFAULT '' COMMENT '操作人号',
  PRIMARY KEY (`id`),
  UNIQUE KEY `tracking_no_unique_idx` (`tracking_no`),
  KEY `fund_trans_order_idx` (`fund_trans_order_no`),
  KEY `app_sheet_serial_no_idx` (`ref_app_sheet_serial_no`),
  KEY `prod_mem_trans_craete_idx` (`product_id`,`member_no`,`trans_time`,`create_time`),
  KEY `prod_trans_acct_no_idx` (`trans_account_no`,`product_id`),
  KEY `trans_time_idx` (`trans_time`),
  KEY `finish_time_idx` (`finish_time`),
  KEY `idx_20160312` (`business_order_item_no`),
  KEY `idxtranscore_trans_no` (`transcore_trans_no`),
  KEY `cur_status_expiry_time_status_idx` (`status`,`cur_status_expiry_time`),
  KEY `idx_member_no` (`member_no`),
  KEY `idx_createtime` (`create_time`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `t_ftc_fund_trans_order_daily_summary`
--

DROP TABLE IF EXISTS `t_ftc_fund_trans_order_daily_summary`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_ftc_fund_trans_order_daily_summary` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `batch_no` char(32) NOT NULL COMMENT '批次编号',
  `trans_type` int(4) NOT NULL COMMENT '交易类型：1 - 认购; 2 - 申购; 3 - 赎回; 4 - 红利; 5 - 兑付; ',
  `trans_date` timestamp NOT NULL DEFAULT '1970-01-01 00:00:00' COMMENT '交易日期',
  `order_count` int(11) NOT NULL DEFAULT '0' COMMENT '有效订单总数',
  `succ_count` int(11) NOT NULL DEFAULT '0' COMMENT '处理成功订单总数',
  `fail_count` int(11) NOT NULL DEFAULT '0' COMMENT '处理失败订单总数',
  `pending_count` int(11) NOT NULL DEFAULT '0' COMMENT '待处理订单总数',
  `operator_no` char(32) NOT NULL COMMENT '操作人号',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`),
  KEY `trans_date_type_idx` (`trans_date`,`trans_type`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `t_ftc_fund_trans_order_exception`
--

DROP TABLE IF EXISTS `t_ftc_fund_trans_order_exception`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_ftc_fund_trans_order_exception` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `fund_trans_order_no` char(32) NOT NULL COMMENT '基金交易业务单号',
  `err_code` char(11) NOT NULL DEFAULT '' COMMENT '错误码',
  `err_msg` text NOT NULL COMMENT '错误信息',
  `create_time` timestamp NOT NULL COMMENT '错误时间',
  PRIMARY KEY (`id`),
  KEY `fund_trans_order_no_idx` (`fund_trans_order_no`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `t_ftc_fund_trans_order_live_status`
--

DROP TABLE IF EXISTS `t_ftc_fund_trans_order_live_status`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_ftc_fund_trans_order_live_status` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `fund_trans_order_no` char(32) NOT NULL COMMENT '基金交易业务单号',
  `status` int(11) NOT NULL COMMENT '业务单状态：4 - 待支付; 5 - 支付中; 6 - 支付成功; 7 - 支付失败; 8 -  基金交易处理中; 9 - 基金交易成功; 10 - 基金交易失败; 11 - 基金交易结果通知成功; 12 - 退款中; 13 - 退款成功; 14 - 退款失败; 15 - 限额风控挂起; 17 - 取消; 18 - 过期关闭; 19 - 部分退款成功; 20 - 提现中; 21 - 提现成功; 22 - 提现失败; 23 - 赎回提交成功; 24 - 等待短信验证; 25 - 风控挂起; 26 - 风控拒绝; 60 - TA处理成功; 61 - TA处理失败; 62 - 更新交易核心申购单状态成功; 63 - 更新交易核心申购单状态失败;64 - 更新交易核心认购单状态成功; 65 - 更新交易核心认购单状态失败;  66 - 签合同成功; 67 - 签合同失败; 68 - 更新赎回单状态成功; 69 - 更新赎回单状态失败; 70 - 更新资产成功; 71 - 更新资产失败; 72 - 创建提现单成功; 73 - 创建提现单失败;',
  `create_time` timestamp NOT NULL DEFAULT '1970-01-01 00:00:00' COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  `operator_no` char(32) NOT NULL DEFAULT '' COMMENT '操作人号',
  `remark` varchar(255) NOT NULL DEFAULT '' COMMENT '备注',
  PRIMARY KEY (`id`),
  UNIQUE KEY `fund_trans_order_no_idx` (`fund_trans_order_no`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `t_ftc_fund_trans_order_status_info`
--

DROP TABLE IF EXISTS `t_ftc_fund_trans_order_status_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_ftc_fund_trans_order_status_info` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `order_id` bigint(20) NOT NULL COMMENT '基金交易业务单主表id',
  `status` int(11) NOT NULL COMMENT '基金业务单状态',
  `update_time` timestamp NOT NULL COMMENT '状态迁移时间',
  `operator_no` char(32) NOT NULL DEFAULT '' COMMENT '操作人',
  `remark` varchar(255) NOT NULL DEFAULT '' COMMENT '备注',
  PRIMARY KEY (`id`),
  KEY `order_status_idx` (`order_id`,`status`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `t_ftc_ta_fund_checking_acct_order`
--

DROP TABLE IF EXISTS `t_ftc_ta_fund_checking_acct_order`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_ftc_ta_fund_checking_acct_order` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `available_vol` decimal(16,2) NOT NULL COMMENT '持有人可用基金份数',
  `total_vol_of_dist_in_ta` decimal(16,2) NOT NULL COMMENT '基金总份数（含冻结）',
  `transaction_account_id` char(17) NOT NULL COMMENT 'ta投资人基金交易账号',
  `distributor_code` char(9) NOT NULL COMMENT 'ta销售人代码',
  `branch_code` char(9) NOT NULL COMMENT '网点号码, 同销售人代码',
  `fund_code` char(6) NOT NULL COMMENT '基金代码',
  `ta_account_id` char(12) NOT NULL COMMENT '投资人基金账号',
  `transaction_cfm_date` char(8) NOT NULL COMMENT 'ta交易确认日期,格式为：YYYYMMDD\n            ',
  `share_class` char(1) NOT NULL COMMENT '收费方式:0-前收费,1-后收费,表明基金是前收费或后收费基金',
  `detail_flag` char(1) NOT NULL COMMENT '明细标志, 0-非明细,1-明细,非明细指针对基金账户的对账,明细指针对基金账户具体过户日或TA确认流水号的对账\n            ',
  `undistribute_monetary_income` decimal(16,2) DEFAULT NULL COMMENT '货币基金未付收益金额, 对货币基金,明细标志为0时必填',
  `undistribute_monetary_income_flag` char(1) DEFAULT NULL COMMENT '货币基金未付收益金额正负,0-正  1-负\n            对货币基金,明细标志为0时必填',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `t_ftc_ta_fund_clearing_order`
--

DROP TABLE IF EXISTS `t_ftc_ta_fund_clearing_order`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_ftc_ta_fund_clearing_order` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `total_frozen_vol` decimal(16,2) DEFAULT NULL COMMENT '基金冻结总份数',
  `fund_vol_balance` decimal(16,2) DEFAULT NULL COMMENT '基金份数余额',
  `achievement_pay` decimal(16,2) NOT NULL COMMENT '业绩报酬',
  `achievement_compen` decimal(16,2) NOT NULL COMMENT '业绩补偿',
  `frozen_balance` decimal(16,2) DEFAULT NULL COMMENT '冻结金额',
  `transaction_account_id` char(17) NOT NULL COMMENT 'ta投资人基金交易账号',
  `distributor_code` char(9) NOT NULL COMMENT 'ta销售人代码',
  `business_code` char(3) NOT NULL COMMENT 'ta业务代码',
  `currency_type` char(3) NOT NULL COMMENT '结算币种,具体编码依GB/T 12406-2008\n            ',
  `fund_code` char(6) NOT NULL COMMENT '基金代码',
  `ta_account_id` char(12) NOT NULL COMMENT '投资人基金账号',
  `transaction_cfm_date` char(8) NOT NULL COMMENT 'ta交易确认日期,格式为：YYYYMMDD\n            ',
  `confirmed_vol` decimal(16,2) NOT NULL COMMENT '基金账户交易确认份数',
  `confirmed_amount` decimal(16,2) NOT NULL COMMENT '每笔交易确认金额,含所有费用的总金额',
  `download_date` char(8) NOT NULL COMMENT '交易数据下传日期,指发送日期\n            ',
  `nav` decimal(7,4) NOT NULL COMMENT '基金单位净值',
  `share_class` char(1) NOT NULL COMMENT '收费方式:0-前收费,1-后收费,表明基金是前收费或后收费基金',
  `ta_serial_no` char(20) DEFAULT '""' COMMENT 'TA确认交易流水号,TA对每笔确认的唯一标识,同一日不能重复,与交易确认日期TransactionCfmDate一起组成TA中一笔确认的唯一键\n            ',
  PRIMARY KEY (`id`),
  UNIQUE KEY `ta_serial_no_idx` (`ta_serial_no`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `t_ftc_ta_fund_create_acct_cfm_order`
--

DROP TABLE IF EXISTS `t_ftc_ta_fund_create_acct_cfm_order`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_ftc_ta_fund_create_acct_cfm_order` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `app_sheet_serial_no` char(24) NOT NULL COMMENT '申请单编号',
  `transaction_cfm_date` char(8) NOT NULL COMMENT 'ta交易确认日期,格式为：YYYYMMDD\n            ',
  `return_code` char(4) NOT NULL COMMENT 'ta交易处理返回代码',
  `transaction_account_id` char(17) NOT NULL COMMENT '投资人基金交易账号, 投资人在销售机构内开设的用于交易的账号\n            ',
  `distributor_code` char(9) NOT NULL COMMENT '销售人代码',
  `ta_account_id` char(12) NOT NULL COMMENT '投资人基金账号',
  `transaction_date` char(8) NOT NULL COMMENT 'ta交易发生日期',
  `business_code` char(3) NOT NULL COMMENT 'ta业务代码',
  `branch_code` char(9) NOT NULL COMMENT '网点号码, 同销售人代码',
  `transaction_time` char(6) NOT NULL COMMENT '交易发生时间',
  `ta_serial_no` char(20) NOT NULL COMMENT 'TA确认交易流水号,TA对每笔确认的唯一标识,同一日不能重复,与交易确认日期TransactionCfmDate一起组成TA中一笔确认的唯一键\n            ',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `t_ftc_ta_fund_create_acct_order`
--

DROP TABLE IF EXISTS `t_ftc_ta_fund_create_acct_order`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_ftc_ta_fund_create_acct_order` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `app_sheet_serial_no` char(24) NOT NULL COMMENT '申请单编号,同一销售机构不能重复',
  `certificate_type` char(1) NOT NULL COMMENT '个人证件类型\n            0-身份证,1-护照\n            2-军官证,3-士兵证\n            4-港澳居民来往内地通行证,5-户口本\n            6-外国护照,7-其它\n            8-文职证,9-警官证\n            A-台胞证\n\n            机构证件类型\n            0-组织机构代码证\n            1-营业执照,2-行政机关\n            3-社会团体,4-军队\n            5-武警\n            6-下属机构（具有主管单位批文号）\n            7-基金会,8-其它',
  `certificate_no` char(30) NOT NULL COMMENT '投资人证件号码',
  `investor_name` varchar(120) NOT NULL COMMENT 'ta投资人户名',
  `transaction_date` char(8) NOT NULL COMMENT 'ta交易发生日期',
  `individual_or_institution` char(1) NOT NULL COMMENT '个人/机构标志',
  `transaction_account_id` char(17) NOT NULL COMMENT 'ta投资人基金交易账号',
  `distributor_code` char(9) NOT NULL COMMENT 'ta销售人代码',
  `business_code` char(3) NOT NULL COMMENT 'ta业务代码',
  `branch_code` char(9) NOT NULL COMMENT '网点号码, 同销售人代码',
  `transaction_time` char(6) NOT NULL COMMENT '交易发生时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `unique_fund_trans_acc_no` (`transaction_account_id`),
  KEY `acct_app_sheet_serial_no_idx` (`app_sheet_serial_no`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `t_ftc_ta_fund_dividend_order`
--

DROP TABLE IF EXISTS `t_ftc_ta_fund_dividend_order`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_ftc_ta_fund_dividend_order` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `basisfor_calculating_dividend` decimal(16,2) NOT NULL COMMENT '红利/红利再投资基数,登记日基金持有人的基金份数',
  `divident_date` char(8) NOT NULL COMMENT '分红日/发放日',
  `vol_of_dividend_for_reinvestment` decimal(16,2) NOT NULL COMMENT '基金账户红利再投资基金份数,投资人实得红股,含被续冻的红股',
  `dividend_amount` decimal(16,2) NOT NULL COMMENT '基金账户红利资金,红利总金额,含冻结红利及再投资的红利',
  `xr_rate` char(8) NOT NULL COMMENT '除权日',
  `registration_date` char(8) NOT NULL COMMENT '权益登记日期, 格式为：YYYYMMDD',
  `dividend_per_unit` decimal(16,2) NOT NULL COMMENT '单位基金分红金额（含税）,举例：每千份分两元,则此处填2。',
  `def_dividend_method` char(1) NOT NULL COMMENT '默认分红方式: 0-红利转投,1-现金分红,投资人本次分红的方式',
  `original_app_sheet_no` char(24) DEFAULT NULL COMMENT '原申请单编号,对质押基金分红为Y项, 表示原质押业务的申请单编号',
  `transaction_account_id` char(17) NOT NULL COMMENT 'ta投资人基金交易账号',
  `distributor_code` char(9) NOT NULL COMMENT 'ta销售人代码',
  `business_code` char(3) NOT NULL COMMENT 'ta业务代码',
  `branch_code` char(9) NOT NULL COMMENT '网点号码, 同销售人代码',
  `achievement_compen` decimal(16,2) NOT NULL COMMENT '业绩补偿',
  `currency_type` char(3) NOT NULL COMMENT '结算币种,具体编码依GB/T 12406-2008\n            ',
  `fund_code` char(6) NOT NULL COMMENT '基金代码',
  `ta_account_id` char(12) NOT NULL COMMENT '投资人基金账号',
  `transaction_cfm_date` char(8) NOT NULL COMMENT 'ta交易确认日期,格式为：YYYYMMDD\n            ',
  `return_code` char(4) NOT NULL COMMENT 'ta交易处理返回代码',
  `ta_serial_no` char(20) NOT NULL COMMENT 'TA确认交易流水号,TA对每笔确认的唯一标识,同一日不能重复,与交易确认日期TransactionCfmDate一起组成TA中一笔确认的唯一键\n            ',
  `confirmed_amount` decimal(16,2) NOT NULL COMMENT '每笔交易确认金额,含所有费用的总金额',
  `download_date` char(8) NOT NULL COMMENT '交易数据下传日期,指发送日期\n            ',
  `charge` decimal(10,2) NOT NULL COMMENT '手续费,投资人应付总手续费',
  `agency_fee` decimal(10,2) NOT NULL COMMENT '代理费, 手续费中划归销售人的部分\n            ',
  `transfer_fee` decimal(10,2) NOT NULL COMMENT '过户费,',
  `share_class` char(1) NOT NULL COMMENT '收费方式:0-前收费,1-后收费,表明基金是前收费或后收费基金',
  `draw_bonus_unit` decimal(10,0) NOT NULL COMMENT '分红单位, 举例：每千份分多少,则分红单位就为一千',
  `dividend_type` char(1) NOT NULL COMMENT '分红类型: 0-普通分红,1-质押基金分红,2-货币基金收益结转,3-保本基金赔付,4-专户到期处理',
  `achievement_pay` decimal(16,2) NOT NULL COMMENT '业绩报酬',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `t_ftc_ta_fund_purchasing_cfm_order`
--

DROP TABLE IF EXISTS `t_ftc_ta_fund_purchasing_cfm_order`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_ftc_ta_fund_purchasing_cfm_order` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `app_sheet_serial_no` char(24) NOT NULL COMMENT '申请单编号,同一销售机构不能重复',
  `transaction_date` char(8) NOT NULL COMMENT 'ta交易发生日期',
  `transaction_account_id` char(17) NOT NULL COMMENT 'ta投资人基金交易账号',
  `distributor_code` char(9) NOT NULL COMMENT 'ta销售人代码',
  `business_code` char(3) NOT NULL COMMENT 'ta业务代码',
  `branch_code` char(9) NOT NULL COMMENT '网点号码, 同销售人代码',
  `transaction_time` char(6) NOT NULL COMMENT '交易发生时间',
  `currency_type` char(3) NOT NULL COMMENT '结算币种,具体编码依GB/T 12406-2008\n            ',
  `fund_code` char(6) NOT NULL COMMENT '基金代码',
  `combine_num` char(6) DEFAULT NULL COMMENT '组合编号',
  `ta_account_id` char(12) NOT NULL COMMENT '投资人基金账号',
  `application_amount` decimal(16,2) NOT NULL COMMENT '申请金额',
  `transaction_cfm_date` char(8) NOT NULL COMMENT 'ta交易确认日期,格式为：YYYYMMDD\n            ',
  `return_code` char(4) NOT NULL COMMENT 'ta交易处理返回代码',
  `ta_serial_no` char(20) NOT NULL COMMENT 'TA确认交易流水号,TA对每笔确认的唯一标识,同一日不能重复,与交易确认日期TransactionCfmDate一起组成TA中一笔确认的唯一键\n            ',
  `confirmed_vol` decimal(16,2) NOT NULL COMMENT '基金账户交易确认份数',
  `confirmed_amount` decimal(16,2) NOT NULL COMMENT '每笔交易确认金额,含所有费用的总金额',
  `download_date` char(8) NOT NULL COMMENT '交易数据下传日期,指发送日期\n            ',
  `charge` decimal(10,2) NOT NULL COMMENT '手续费,投资人应付总手续费',
  `agency_fee` decimal(10,2) NOT NULL COMMENT '代理费, 手续费中划归销售人的部分\n            ',
  `nav` decimal(7,4) NOT NULL COMMENT '基金单位净值',
  `transfer_fee` decimal(10,2) NOT NULL COMMENT '过户费,',
  `share_class` char(1) NOT NULL COMMENT '收费方式:0-前收费,1-后收费,表明基金是前收费或后收费基金',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `t_ftc_ta_fund_purchasing_order`
--

DROP TABLE IF EXISTS `t_ftc_ta_fund_purchasing_order`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_ftc_ta_fund_purchasing_order` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `app_sheet_serial_no` char(24) NOT NULL COMMENT '申请单编号,同一销售机构不能重复',
  `transaction_date` char(8) NOT NULL COMMENT 'ta交易发生日期',
  `transaction_account_id` char(17) NOT NULL COMMENT 'ta投资人基金交易账号',
  `distributor_code` char(9) NOT NULL COMMENT 'ta销售人代码',
  `business_code` char(3) NOT NULL COMMENT 'ta业务代码',
  `branch_code` char(9) NOT NULL COMMENT '网点号码, 同销售人代码',
  `transaction_time` char(6) NOT NULL COMMENT '交易发生时间',
  `currency_type` char(3) NOT NULL COMMENT '结算币种,具体编码依GB/T 12406-2008\n            ',
  `fund_code` char(6) NOT NULL COMMENT '基金代码',
  `ta_account_id` char(12) NOT NULL COMMENT '投资人基金账号',
  `application_amount` decimal(16,2) NOT NULL COMMENT '申请金额',
  `share_class` char(1) NOT NULL COMMENT '收费方式:0-前收费,1-后收费,表明基金是前收费或后收费基金',
  `charge_type` char(1) NOT NULL COMMENT '收费类型:0-折扣率方式 1-指定费率,2-指定费用',
  PRIMARY KEY (`id`),
  UNIQUE KEY `ta_purchasing_app_sheet_serial_no_idx` (`app_sheet_serial_no`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `t_ftc_ta_fund_redeeming_cfm_order`
--

DROP TABLE IF EXISTS `t_ftc_ta_fund_redeeming_cfm_order`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_ftc_ta_fund_redeeming_cfm_order` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `app_sheet_serial_no` char(24) NOT NULL COMMENT '申请单编号,同一销售机构不能重复',
  `business_finish_flag` char(1) NOT NULL COMMENT '业务过程完全结束标识, 0-中间过程,1-业务过程结束,比如因巨额赎回导致顺延,存在中间过程。',
  `large_redemption_flag` char(1) NOT NULL COMMENT '巨额赎回处理标志, 0-取消,1-顺延, 对申请的回执字段。',
  `transaction_date` char(8) NOT NULL COMMENT 'ta交易发生日期',
  `transaction_account_id` char(17) NOT NULL COMMENT 'ta投资人基金交易账号',
  `distributor_code` char(9) NOT NULL COMMENT 'ta销售人代码',
  `business_code` char(3) NOT NULL COMMENT 'ta业务代码',
  `branch_code` char(9) NOT NULL COMMENT '网点号码, 同销售人代码',
  `transaction_time` char(6) NOT NULL COMMENT '交易发生时间',
  `currency_type` char(3) NOT NULL COMMENT '结算币种,具体编码依GB/T 12406-2008\n            ',
  `fund_code` char(6) NOT NULL COMMENT '基金代码',
  `ta_account_id` char(12) NOT NULL COMMENT '投资人基金账号',
  `application_vol` decimal(16,2) NOT NULL COMMENT '申请金额',
  `transaction_cfm_date` char(8) NOT NULL COMMENT 'ta交易确认日期,格式为：YYYYMMDD\n            ',
  `return_code` char(4) NOT NULL COMMENT 'ta交易处理返回代码',
  `ta_serial_no` char(20) NOT NULL COMMENT 'TA确认交易流水号,TA对每笔确认的唯一标识,同一日不能重复,与交易确认日期TransactionCfmDate一起组成TA中一笔确认的唯一键\n            ',
  `confirmed_vol` decimal(16,2) NOT NULL COMMENT '基金账户交易确认份数',
  `confirmed_amount` decimal(16,2) NOT NULL COMMENT '每笔交易确认金额,含所有费用的总金额',
  `download_date` char(8) NOT NULL COMMENT '交易数据下传日期,指发送日期\n            ',
  `charge` decimal(10,2) NOT NULL COMMENT '手续费,投资人应付总手续费',
  `agency_fee` decimal(10,2) NOT NULL COMMENT '代理费, 手续费中划归销售人的部分\n            ',
  `nav` decimal(7,4) NOT NULL COMMENT '基金单位净值',
  `transfer_fee` decimal(10,2) NOT NULL COMMENT '过户费,',
  `other_fee_1` decimal(10,2) NOT NULL COMMENT '其他费用1, 赎回手续费中划归基金资产的部分',
  `share_class` char(1) NOT NULL COMMENT '收费方式:0-前收费,1-后收费,表明基金是前收费或后收费基金',
  `breach_fee` decimal(16,2) NOT NULL COMMENT '违约金',
  `breach_fee_back_to_fund` decimal(16,2) NOT NULL COMMENT '违约金归基金资产金额',
  `punish_fee` decimal(16,2) NOT NULL COMMENT '惩罚性费用',
  `achievement_compen` decimal(16,2) NOT NULL COMMENT '业绩补偿',
  `achievement_pay` decimal(16,2) NOT NULL COMMENT '业绩报酬',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `t_ftc_ta_fund_redeeming_order`
--

DROP TABLE IF EXISTS `t_ftc_ta_fund_redeeming_order`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_ftc_ta_fund_redeeming_order` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `app_sheet_serial_no` char(24) NOT NULL COMMENT '申请单编号,同一销售机构不能重复',
  `large_redemption_flag` char(1) NOT NULL COMMENT '巨额赎回处理标志, 0-取消,1-顺延',
  `application_vol` decimal(16,2) NOT NULL COMMENT '申请基金份数',
  `transaction_date` char(8) NOT NULL COMMENT 'ta交易发生日期',
  `transaction_account_id` char(17) NOT NULL COMMENT 'ta投资人基金交易账号',
  `distributor_code` char(9) NOT NULL COMMENT 'ta销售人代码',
  `business_code` char(3) NOT NULL COMMENT 'ta业务代码',
  `branch_code` char(9) NOT NULL COMMENT '网点号码, 同销售人代码',
  `transaction_time` char(6) NOT NULL COMMENT '交易发生时间',
  `fund_code` char(6) NOT NULL COMMENT '基金代码',
  `ta_account_id` char(12) NOT NULL COMMENT '投资人基金账号',
  `share_class` char(1) NOT NULL COMMENT '收费方式:0-前收费,1-后收费,表明基金是前收费或后收费基金',
  `charge_type` char(1) NOT NULL COMMENT '收费类型:0-折扣率方式 1-指定费率,2-指定费用',
  PRIMARY KEY (`id`),
  UNIQUE KEY `ta_redeeming_app_sheet_serial_no_idx` (`app_sheet_serial_no`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `t_ftc_ta_fund_subscribing_cfm_order`
--

DROP TABLE IF EXISTS `t_ftc_ta_fund_subscribing_cfm_order`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_ftc_ta_fund_subscribing_cfm_order` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `app_sheet_serial_no` char(24) NOT NULL COMMENT '申请单编号,同一销售机构不能重复',
  `transaction_date` char(8) NOT NULL COMMENT 'ta交易发生日期',
  `transaction_account_id` char(17) NOT NULL COMMENT 'ta投资人基金交易账号',
  `distributor_code` char(9) NOT NULL COMMENT 'ta销售人代码',
  `business_code` char(3) NOT NULL COMMENT 'ta业务代码',
  `branch_code` char(9) NOT NULL COMMENT '网点号码, 同销售人代码',
  `transaction_time` char(6) NOT NULL COMMENT '交易发生时间',
  `fund_code` char(6) NOT NULL COMMENT '基金代码',
  `combine_num` char(6) DEFAULT NULL COMMENT '组合编号',
  `ta_account_id` char(12) NOT NULL COMMENT '投资人基金账号',
  `application_amount` decimal(16,2) NOT NULL COMMENT '申请金额',
  `transaction_cfm_date` char(8) NOT NULL COMMENT 'ta交易确认日期,格式为：YYYYMMDD\n            ',
  `return_code` char(4) NOT NULL COMMENT 'ta交易处理返回代码',
  `ta_serial_no` char(20) NOT NULL COMMENT 'TA确认交易流水号,TA对每笔确认的唯一标识,同一日不能重复,与交易确认日期TransactionCfmDate一起组成TA中一笔确认的唯一键\n            ',
  `confirmed_amount` decimal(16,2) NOT NULL COMMENT '每笔交易确认金额,含所有费用的总金额',
  `download_date` char(8) NOT NULL COMMENT '交易数据下传日期,指发送日期\n            ',
  `nav` decimal(7,4) NOT NULL COMMENT '基金单位净值',
  `share_class` char(1) NOT NULL COMMENT '收费方式:0-前收费,1-后收费,表明基金是前收费或后收费基金',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `t_ftc_ta_fund_subscribing_order`
--

DROP TABLE IF EXISTS `t_ftc_ta_fund_subscribing_order`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_ftc_ta_fund_subscribing_order` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `app_sheet_serial_no` char(24) NOT NULL COMMENT '申请单编号,同一销售机构不能重复',
  `transaction_date` char(8) NOT NULL COMMENT 'ta交易发生日期',
  `transaction_account_id` char(17) NOT NULL COMMENT 'ta投资人基金交易账号',
  `distributor_code` char(9) NOT NULL COMMENT 'ta销售人代码',
  `business_code` char(3) NOT NULL COMMENT 'ta业务代码',
  `branch_code` char(9) NOT NULL COMMENT '网点号码, 同销售人代码',
  `transaction_time` char(6) NOT NULL COMMENT '交易发生时间',
  `fund_code` char(6) NOT NULL COMMENT '基金代码',
  `ta_account_id` char(12) NOT NULL COMMENT '投资人基金账号',
  `application_amount` decimal(16,2) NOT NULL COMMENT '申请金额',
  `share_class` char(1) NOT NULL COMMENT '收费方式:0-前收费,1-后收费,表明基金是前收费或后收费基金',
  `currency_type` char(3) NOT NULL COMMENT '结算币种,具体编码依GB/T 12406-2008\n            ',
  PRIMARY KEY (`id`),
  UNIQUE KEY `ta_sub_app_sheet_serial_no_idx` (`app_sheet_serial_no`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `t_ftc_ta_fund_subscribing_result_order`
--

DROP TABLE IF EXISTS `t_ftc_ta_fund_subscribing_result_order`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_ftc_ta_fund_subscribing_result_order` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `app_sheet_serial_no` char(24) NOT NULL COMMENT '申请单编号,同一销售机构不能重复',
  `transaction_date` char(8) NOT NULL COMMENT 'ta交易发生日期',
  `transaction_account_id` char(17) NOT NULL COMMENT 'ta投资人基金交易账号',
  `distributor_code` char(9) NOT NULL COMMENT 'ta销售人代码',
  `business_code` char(3) NOT NULL COMMENT 'ta业务代码',
  `branch_code` char(9) NOT NULL COMMENT '网点号码, 同销售人代码',
  `transaction_time` char(6) NOT NULL COMMENT '交易发生时间',
  `currency_type` char(3) NOT NULL COMMENT '结算币种,具体编码依GB/T 12406-2008\n            ',
  `fund_code` char(6) NOT NULL COMMENT '基金代码',
  `combine_num` char(6) DEFAULT NULL COMMENT '组合编号',
  `ta_account_id` char(12) NOT NULL COMMENT '投资人基金账号',
  `application_amount` decimal(16,2) NOT NULL COMMENT '申请金额',
  `transaction_cfm_date` char(8) NOT NULL COMMENT 'ta交易确认日期,格式为：YYYYMMDD\n            ',
  `return_code` char(4) NOT NULL COMMENT 'ta交易处理返回代码',
  `ta_serial_no` char(20) NOT NULL COMMENT 'TA确认交易流水号,TA对每笔确认的唯一标识,同一日不能重复,与交易确认日期TransactionCfmDate一起组成TA中一笔确认的唯一键\n            ',
  `confirmed_vol` decimal(16,2) NOT NULL COMMENT '基金账户交易确认份数',
  `confirmed_amount` decimal(16,2) NOT NULL COMMENT '每笔交易确认金额,含所有费用的总金额',
  `download_date` char(8) NOT NULL COMMENT '交易数据下传日期,指发送日期\n            ',
  `charge` decimal(10,2) NOT NULL COMMENT '手续费,投资人应付总手续费',
  `agency_fee` decimal(10,2) NOT NULL COMMENT '代理费, 手续费中划归销售人的部分\n            ',
  `nav` decimal(7,4) NOT NULL COMMENT '基金单位净值',
  `transfer_fee` decimal(10,2) NOT NULL COMMENT '过户费,',
  `share_class` char(1) NOT NULL COMMENT '收费方式:0-前收费,1-后收费,表明基金是前收费或后收费基金',
  `interest` decimal(10,2) NOT NULL COMMENT '基金账户利息金额,认购一次确认的金额在整个计息周期中产生的利息',
  `raise_interest` decimal(16,2) NOT NULL COMMENT '认购期间利息,因认购二次确认失败而退还给投资人的利息',
  `interest_tax` decimal(16,2) NOT NULL COMMENT '利息税',
  `volume_by_interest` decimal(16,2) NOT NULL COMMENT '利息产生的基金份数',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2016-05-12 14:16:19
