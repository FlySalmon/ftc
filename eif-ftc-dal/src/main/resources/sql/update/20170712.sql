
use eif_ftc;

CREATE TABLE `t_ftc_fund_transfer_apply_to_exchange` (
  `id` BIGINT(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键',
  `op_id` CHAR(32) NOT NULL COMMENT '操作流水号',
  `fund_transferor_order_no` CHAR(32) NOT NULL COMMENT '转让交易订单号',
  `fund_transferee_order_no` CHAR(32) NOT NULL COMMENT '受让交易订单号',
  `exchange_transferor_id` VARCHAR(64) NOT NULL DEFAULT '' COMMENT '交易所转让申请Id',
  `exchange_matching_id` VARCHAR(64) NOT NULL DEFAULT '' COMMENT '交易所受让匹配Id',
  `exchange_transferee_id` VARCHAR(64) NOT NULL DEFAULT '' COMMENT '交易所受让交易Id',
  `exchange_prod_no` VARCHAR(64) NOT NULL DEFAULT '' COMMENT '交易所产品代码',
  `product_id` bigint(20) NOT NULL COMMENT '原用户产品ID',
  `mkt_product_id` bigint(20) NOT NULL COMMENT '二级市场所属产品ID',
  `member_no_from` char(32) NOT NULL DEFAULT '' COMMENT '转让发起人会员号',
  `member_no_to` char(32) NOT NULL DEFAULT '' COMMENT '受让发起人会员号',
  `transfer_time` TIMESTAMP NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '平台转让发生时间',
  `exchange_execution_time` TIMESTAMP NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '确认转让执行时间',
  `status` TINYINT(4) NOT NULL COMMENT '交易所转让状态：1 - 转让申请中; 2 - 转让申请成功; 3 - 转让申请失败; 4 - 转让成功; 5 - 转让失败;',
  `create_time` TIMESTAMP NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '创建时间',
  `update_time` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  `remark` VARCHAR(256) NOT NULL DEFAULT '' COMMENT '备注',
  `operator_no` CHAR(32) NOT NULL DEFAULT '' COMMENT '操作人号',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_op_id` (`op_id`),
  KEY `idx_exchange_prod_no_status` (`exchange_prod_no`, `status`),
  KEY `idx_fund_transferor_order_no` (`fund_transferor_order_no`(20)),
  KEY `idx_fund_transferee_order_no` (`fund_transferee_order_no`(20)),
  KEY `idx_transfer_time_status` (`transfer_time`,`status`)
) ENGINE=INNODB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COMMENT='交易所转让记录';

CREATE TABLE `t_ftc_fund_transfer_apply_to_exchange_status_info` (
  `id` BIGINT(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键',
  `order_id` BIGINT(20) NOT NULL COMMENT '交易所转让记录主表id',
  `status` TINYINT(4) NOT NULL COMMENT '交易所转让记录状态',
  `update_time` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '状态迁移时间',
  `operator_no` CHAR(32) NOT NULL DEFAULT '' COMMENT '操作人',
  `remark` VARCHAR(255) NOT NULL DEFAULT '' COMMENT '备注',
  PRIMARY KEY (`id`),
  KEY `idx_order_id` (`order_id`)
) ENGINE=INNODB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COMMENT='交易所转让记录状态迁移';

ALTER TABLE eif_ftc.t_ftc_fund_transferee_order ADD INDEX idx_mkt_product_id (`mkt_product_id`) comment '解决慢查询';

ALTER TABLE eif_ftc.t_ftc_fund_dividend ADD INDEX idx_transaction_account_id (`transaction_account_id`);

ALTER IGNORE TABLE eif_ftc.t_ftc_ta_fund_dividend_order ADD UNIQUE INDEX uk_date_ta_account_id (`divident_date`, `ta_account_id`);
