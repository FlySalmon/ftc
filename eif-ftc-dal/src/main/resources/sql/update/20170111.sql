use eif_ftc;

CREATE TABLE `t_ftc_fund_transferor_order` (
  `id` BIGINT(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键',
  `transfer_mode` INT(11) NOT NULL COMMENT '转让方式（1 - 用户指定转让定价方式; 2 - 用户指定转让收益率方式;）',
  `transfer_transaction_mode` INT(11) NOT NULL COMMENT '转让交易模式：1 - 一口价模式; 2 - 竞价模式;',
  `fund_transferor_order_no` CHAR(32) NOT NULL COMMENT '转让交易订单号',
  `business_order_item_no` CHAR(32) NOT NULL DEFAULT '' COMMENT '业务订单流水号',
  `member_no` CHAR(32) NOT NULL COMMENT '转让发起人会员号',
  `asset_account_no` CHAR(32) NOT NULL COMMENT '发起人资产账户号',
  `trans_account_no` CHAR(17) NOT NULL DEFAULT '' COMMENT '发起人基金交易账号',
  `transfer_agreement_no` CHAR(32) NOT NULL DEFAULT '' COMMENT '转让协议号',
  `product_id` BIGINT(20) NOT NULL COMMENT '资产份额所属产品ID',
  `ref_fund_sub_code` CHAR(6) NOT NULL DEFAULT '' COMMENT '外部基金分组编号',
  `mkt_product_id` BIGINT(20) NOT NULL DEFAULT '-1' COMMENT '二级市场所属产品ID',
  `original_asset_total_value` DECIMAL(26,6) NOT NULL DEFAULT '0.000000' COMMENT '原资产份额总价值，一级市场购入至计息结束后用户实际可得的本息总额（包括券金额等）',
  `original_asset_surplus_value` DECIMAL(26,6) NOT NULL DEFAULT '0.000000' COMMENT '原资产份额残值，一级市场购入时的本金+从发起转让的次日至结息日的利息（只考虑按预期收益率产生的利息）',
  `fund_transfer_amount` DECIMAL(26,6) NOT NULL DEFAULT '0.000000' COMMENT '转让金额，即转让定价价格',
  `fund_transfer_principal` DECIMAL(26,6) NOT NULL DEFAULT '0.000000' COMMENT '转让价格中的本金金额，转让用户在一级或二级市场购买原产品或原份额时的价格',
  `fund_transfer_interest` DECIMAL(26,6) NOT NULL DEFAULT '0.000000' COMMENT '转让价格中的利息金额，转让价格-转让价格中本金金额',
  `discount_amount` DECIMAL(26,6) NOT NULL DEFAULT '0.000000' COMMENT '订单让利金额，原资产份额总价值-原资产份额残值-转让价格中利息金额',
  `annual_yield` DECIMAL(26,6) NOT NULL DEFAULT '0.000000' COMMENT '转让年化收益率，（原资产份额总价值-订单价格）*份额产品年天数/（订单价格*订单份额计息剩余天数）',
  `transferor_fee` DECIMAL(26,6) NOT NULL DEFAULT '0.000000' COMMENT '转让方手续费',
  `transferee_fee` DECIMAL(26,6) NOT NULL DEFAULT '0.000000' COMMENT '受让方手续费',
  `fee_rule` VARCHAR(1024) NOT NULL DEFAULT '' COMMENT '实时手续费规则，保存json字串',
  `biz_channel` INT(11) NOT NULL COMMENT '渠道类型：IOS = 11; ANDROID = 12; H5 = 13; WECHAT = 14; WEB = 20;',
  `status` INT(11) NOT NULL COMMENT '业务单状态：4 - 待转让; 5 - 转让中; 6 - 转让成功; 7 - 转让失败; 17 - 转让撤销; 18 - 过期关闭; 26 - 风控取消;',
  `ext_field` VARCHAR(256) NOT NULL DEFAULT '' COMMENT '业务扩展字段，保存json字串',
  `tracking_no` CHAR(32) NOT NULL COMMENT '防止幂等, 全局跟踪',
  `finish_time` TIMESTAMP NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '业务单整体完成时间',
  `cancel_time` TIMESTAMP NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '业务单撤销时间',
  `expiry_time` TIMESTAMP NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '订单有效期时间',
  `create_time` TIMESTAMP NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '创建时间',
  `update_time` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  `remark` VARCHAR(256) NOT NULL DEFAULT '' COMMENT '备注',
  `operator_no` CHAR(32) NOT NULL DEFAULT '' COMMENT '操作人号',
  PRIMARY KEY (`id`),
  UNIQUE KEY `tracking_no_unique_idx` (`tracking_no`),
  KEY `fund_transferor_order_no_idx` (`fund_transferor_order_no`(20)),
  KEY `biz_order_no_idx` (`business_order_item_no`),
  KEY `member_no_mkt_prod_idx` (`member_no`, `mkt_product_id`),
  KEY `product_id_idx` (`product_id`),
  KEY `mkt_product_id_idx` (`mkt_product_id`),
  KEY `status_expiry_time_idx` (`status`,`expiry_time`),
  KEY `finish_time_idx` (`finish_time`),
  KEY `create_time_idx` (`create_time`)
) ENGINE=INNODB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COMMENT='资产份额转让业务的交易业务单';

CREATE TABLE `t_ftc_fund_transferor_order_status_info` (
  `id` BIGINT(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键',
  `order_id` BIGINT(20) NOT NULL COMMENT '转让交易业务单主表id',
  `status` INT(11) NOT NULL COMMENT '转让交易业务单状态',
  `update_time` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '状态迁移时间',
  `operator_no` CHAR(32) NOT NULL DEFAULT '' COMMENT '操作人',
  `remark` VARCHAR(255) NOT NULL DEFAULT '' COMMENT '备注',
  PRIMARY KEY (`id`),
  KEY `order_status_idx` (`order_id`,`status`)
) ENGINE=INNODB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COMMENT='记录转让交易业务单状态迁移信息';

CREATE TABLE `t_ftc_fund_transferee_order` (
  `id` BIGINT(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键',
  `fund_transferee_order_no` CHAR(32) NOT NULL COMMENT '受让交易订单号',
  `business_order_item_no` CHAR(32) NOT NULL DEFAULT '' COMMENT '业务订单流水号',
  `ref_fund_transferor_order_no` CHAR(32) NOT NULL DEFAULT '' COMMENT '关联转让交易订单号',
  `ref_origin_fund_transferor_order_no` CHAR(32) NOT NULL DEFAULT '' COMMENT '关联最初转让交易订单号，最初发起转让的交易订单号',
  `member_no` CHAR(32) NOT NULL COMMENT '受让发起人会员号',
  `asset_account_no` CHAR(32) NOT NULL COMMENT '受让发起人资产账户号',
  `trans_account_no` CHAR(17) NOT NULL DEFAULT '' COMMENT '受让发起人基金交易账号',
  `transfer_agreement_no` CHAR(32) NOT NULL DEFAULT '' COMMENT '转让协议号',
  `mkt_product_id` BIGINT(20) NOT NULL COMMENT '二级市场所属产品ID',
  `fund_transfer_amount` DECIMAL(26,6) NOT NULL DEFAULT '0.000000' COMMENT '受让价格，即为转让单中的转让定价价格',
  `discount_amount` DECIMAL(26,6) NOT NULL DEFAULT '0.000000' COMMENT '优惠金额',
  `fee` DECIMAL(26,6) NOT NULL DEFAULT '0.000000' COMMENT '手续费',
  `pay_method` VARCHAR(1024) NOT NULL DEFAULT '' COMMENT '支付方式，json表示',
  `pay_method_desc` VARCHAR(512) NOT NULL DEFAULT '' COMMENT '支付方式描述',
  `currency_type` CHAR(3) NOT NULL DEFAULT '' COMMENT '币种',
  `frozen_token` CHAR(32) NOT NULL DEFAULT '' COMMENT '库存冻结号',
  `transcore_trans_no` CHAR(32) NOT NULL DEFAULT '' COMMENT '交易核心受让交易单流水号',
  `transfer_trans_no` CHAR(32) NOT NULL DEFAULT '' COMMENT '交易核心转账交易单流水号',
  `contract_no` CHAR(32) NOT NULL DEFAULT '' COMMENT '合同流水号, 受让成功后自动生成',
  `biz_channel` INT(11) NOT NULL COMMENT '渠道类型：IOS = 11; ANDROID = 12; H5 = 13; WECHAT = 14; WEB = 20;',
  `status` INT(11) NOT NULL COMMENT '业务单状态：4 - 待支付; 5 - 支付中; 6 - 支付成功; 7 - 支付失败; 9 - 转入成功; 10 - 转入失败; 12 - 退款中; 13 - 退款成功; 14 - 退款失败; 18 - 过期关闭; 24 - 等待短信验证; 25 - 风控挂起; 26 - 风控拒绝;',
  `ext_field` VARCHAR(256) NOT NULL DEFAULT '' COMMENT '业务扩展字段，保存json字串',
  `tracking_no` CHAR(32) NOT NULL COMMENT '防止幂等, 全局跟踪',
  `pay_time` TIMESTAMP NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '业务单支付时间',
  `finish_time` TIMESTAMP NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '业务单交易完成时间',
  `expiry_time` TIMESTAMP NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '订单有效期时间',
  `status_expiry_time` TIMESTAMP NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '当前状态过期时间',
  `create_time` TIMESTAMP NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '创建时间',
  `update_time` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  `remark` VARCHAR(256) NOT NULL DEFAULT '' COMMENT '备注',
  `operator_no` CHAR(32) NOT NULL DEFAULT '' COMMENT '操作人号',
  PRIMARY KEY (`id`),
  UNIQUE KEY `tracking_no_unique_idx` (`tracking_no`),
  KEY `fund_transferee_order_no_idx` (`fund_transferee_order_no`(20)),
  KEY `biz_order_no_idx` (`business_order_item_no`),
  KEY `fund_transferor_order_no_idx` (`ref_fund_transferor_order_no`(20)),
  KEY `transcore_trans_no_idx` (`transcore_trans_no`(20)),
  KEY `transfer_trans_no_idx` (`transfer_trans_no`(20)),
  KEY `member_no_status_idx` (`member_no`, `status`),
  KEY `status_expiry_time_idx` (`status`,`status_expiry_time`),
  KEY `finish_time_idx` (`finish_time`),
  KEY `create_time_idx` (`create_time`)
) ENGINE=INNODB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COMMENT='资产份额受让业务的交易业务单';

CREATE TABLE `t_ftc_fund_transferee_order_status_info` (
  `id` BIGINT(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键',
  `order_id` BIGINT(20) NOT NULL COMMENT '受让交易业务单主表id',
  `status` INT(11) NOT NULL COMMENT '受让交易业务单状态',
  `update_time` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '状态迁移时间',
  `operator_no` CHAR(32) NOT NULL DEFAULT '' COMMENT '操作人',
  `remark` VARCHAR(255) NOT NULL DEFAULT '' COMMENT '备注',
  PRIMARY KEY (`id`),
  KEY `order_status_idx` (`order_id`,`status`)
) ENGINE=INNODB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COMMENT='记录受让交易业务单状态迁移信息';



ALTER TABLE eif_ftc.t_ftc_fund_clearing ADD market_level INT(11) NOT NULL DEFAULT 1 COMMENT '市场类别：一级市场 = 1; 二级市场 = 2;' AFTER business_order_item_no;

ALTER TABLE eif_ftc.t_ftc_fund_dividend ADD market_level INT(11) NOT NULL DEFAULT 1 COMMENT '市场类别：一级市场 = 1; 二级市场 = 2;' AFTER business_order_item_no;

ALTER TABLE `eif_ftc`.`t_amc_fund_detail` ADD COLUMN `exchange_status` tinyint NOT NULL DEFAULT 0 COMMENT '转换类型，0-未转换；1-冻结中；2-出让中；3-出让完成。';

ALTER TABLE eif_ftc.t_amc_fund_detail ADD INDEX idx_t_amc_fund_detail_total_id USING BTREE (fund_total_id) comment '解决慢查询';
 
ALTER TABLE eif_ftc.t_amc_fund_total ADD INDEX idx_t_amc_fund_total_acc_id USING BTREE (fund_acc_id) comment '解决慢查询';

ALTER TABLE eif_ftc.t_ftc_fund_clearing ADD INDEX idx_member_no(member_no);

