
DROP TABLE IF EXISTS `t_ftc_fund_transfer_apply_to_exchange`;
CREATE TABLE `t_ftc_fund_transfer_apply_to_exchange` (
  `id` BIGINT(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '',
  `op_id` CHAR(32) NOT NULL COMMENT '',
  `fund_transferor_order_no` CHAR(32) NOT NULL COMMENT '',
  `fund_transferee_order_no` CHAR(32) NOT NULL COMMENT '',
  `exchange_transferor_id` VARCHAR(64) NOT NULL DEFAULT '' COMMENT '',
  `exchange_matching_id` VARCHAR(64) NOT NULL DEFAULT '' COMMENT '',
  `exchange_transferee_id` VARCHAR(64) NOT NULL DEFAULT '' COMMENT '',
  `exchange_prod_no` VARCHAR(64) NOT NULL DEFAULT '' COMMENT '',
  `product_id` bigint(20) NOT NULL COMMENT '',
  `mkt_product_id` bigint(20) NOT NULL COMMENT '',
  `member_no_from` char(32) NOT NULL DEFAULT '' COMMENT '',
  `member_no_to` char(32) NOT NULL DEFAULT '' COMMENT '',
  `transfer_time` TIMESTAMP NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '',
  `exchange_execution_time` TIMESTAMP NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '',
  `status` TINYINT(4) NOT NULL COMMENT '',
  `create_time` TIMESTAMP NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '',
  `update_time` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '',
  `remark` VARCHAR(256) NOT NULL DEFAULT '' COMMENT '',
  `operator_no` CHAR(32) NOT NULL DEFAULT '' COMMENT '',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_op_id` (`op_id`),
  KEY `idx_exchange_prod_no_status` (`exchange_prod_no`, `status`),
  KEY `idx_fund_transferor_order_no` (`fund_transferor_order_no`),
  KEY `idx_fund_transferee_order_no` (`fund_transferee_order_no`),
  KEY `idx_transfer_time_status` (`transfer_time`,`status`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `t_ftc_fund_transfer_apply_to_exchange_status_info`;
CREATE TABLE `t_ftc_fund_transfer_apply_to_exchange_status_info` (
  `id` BIGINT(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '',
  `order_id` BIGINT(20) NOT NULL COMMENT '',
  `status` TINYINT(4) NOT NULL COMMENT '',
  `update_time` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '',
  `operator_no` CHAR(32) NOT NULL DEFAULT '' COMMENT '',
  `remark` VARCHAR(255) NOT NULL DEFAULT '' COMMENT '',
  PRIMARY KEY (`id`),
  KEY `idx_order_id` (`order_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
