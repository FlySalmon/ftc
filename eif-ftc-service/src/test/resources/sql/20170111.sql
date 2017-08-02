DROP TABLE IF EXISTS `t_ftc_fund_transferor_order`;
CREATE TABLE t_ftc_fund_transferor_order (
  id BIGINT(20) UNSIGNED NOT NULL AUTO_INCREMENT,
  transfer_mode INT(11) NOT NULL,
  transfer_transaction_mode INT(11) NOT NULL,
  fund_transferor_order_no CHAR(32) NOT NULL,
  business_order_item_no CHAR(32) NOT NULL DEFAULT '',
  member_no CHAR(32) NOT NULL,
  asset_account_no CHAR(32) NOT NULL,
  trans_account_no CHAR(17) NOT NULL DEFAULT '',
  transfer_agreement_no CHAR(32) NOT NULL DEFAULT '',
  product_id BIGINT(20) NOT NULL,
  ref_fund_sub_code CHAR(6) NOT NULL DEFAULT '',
  mkt_product_id BIGINT(20) NOT NULL DEFAULT -1,
  original_asset_total_value DECIMAL(26,6) NOT NULL DEFAULT '0.000000',
  original_asset_surplus_value DECIMAL(26,6) NOT NULL DEFAULT '0.000000',
  fund_transfer_amount DECIMAL(26,6) NOT NULL DEFAULT '0.000000',
  fund_transfer_principal DECIMAL(26,6) NOT NULL DEFAULT '0.000000',
  fund_transfer_interest DECIMAL(26,6) NOT NULL DEFAULT '0.000000',
  discount_amount DECIMAL(26,6) NOT NULL DEFAULT '0.000000',
  annual_yield DECIMAL(26,6) NOT NULL DEFAULT '0.000000',
  transferor_fee DECIMAL(26,6) NOT NULL DEFAULT '0.000000',
  transferee_fee DECIMAL(26,6) NOT NULL DEFAULT '0.000000',
  fee_rule VARCHAR(1024) NOT NULL DEFAULT '',
  biz_channel INT(11) NOT NULL,
  status INT(11) NOT NULL,
  ext_field VARCHAR(256) NOT NULL DEFAULT '',
  tracking_no CHAR(32) NOT NULL,
  finish_time TIMESTAMP,
  cancel_time TIMESTAMP,
  expiry_time TIMESTAMP,
  create_time TIMESTAMP,
  update_time TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  remark VARCHAR(256) NOT NULL DEFAULT '',
  operator_no CHAR(32) NOT NULL DEFAULT '',
  PRIMARY KEY (`id`),
  KEY `transferor_tracking_no_unique_idx` (`tracking_no`),
  KEY `fund_transferor_order_no_idx` (`fund_transferor_order_no`),
  KEY `transferor_biz_order_no_idx` (`business_order_item_no`),
  KEY `member_no_mkt_prod_idx` (`member_no`, `mkt_product_id`),
  KEY `product_id_idx` (`product_id`),
  KEY `mkt_product_id_idx` (`mkt_product_id`),
  KEY `status_expiry_time_idx` (`status`,`expiry_time`),
  KEY `transferor_finish_time_idx` (`finish_time`),
  KEY `transferor_create_time_idx` (`create_time`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `t_ftc_fund_transferor_order_status_info`;
CREATE TABLE t_ftc_fund_transferor_order_status_info (
  id BIGINT(20) UNSIGNED NOT NULL AUTO_INCREMENT,
  order_id BIGINT(20) NOT NULL,
  status INT(11) NOT NULL,
  update_time TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  operator_no CHAR(32) NOT NULL DEFAULT '',
  remark VARCHAR(255) NOT NULL DEFAULT '',
  PRIMARY KEY (`id`),
  KEY `transferor_order_status_idx` (`order_id`,`status`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `t_ftc_fund_transferee_order`;
CREATE TABLE t_ftc_fund_transferee_order (
  id BIGINT(20) UNSIGNED NOT NULL AUTO_INCREMENT,
  fund_transferee_order_no CHAR(32) NOT NULL,
  business_order_item_no CHAR(32) NOT NULL DEFAULT '',
  ref_fund_transferor_order_no CHAR(32) NOT NULL DEFAULT '',
  ref_origin_fund_transferor_order_no CHAR(32) NOT NULL DEFAULT '',
  member_no CHAR(32) NOT NULL,
  asset_account_no CHAR(32) NOT NULL,
  trans_account_no CHAR(17) NOT NULL DEFAULT '',
  transfer_agreement_no CHAR(32) NOT NULL DEFAULT '',
  mkt_product_id BIGINT(20) NOT NULL,
  fund_transfer_amount DECIMAL(26,6) NOT NULL DEFAULT '0.000000',
  discount_amount DECIMAL(26,6) NOT NULL DEFAULT '0.000000',
  fee DECIMAL(26,6) NOT NULL DEFAULT '0.000000',
  pay_method VARCHAR(1024) NOT NULL DEFAULT '',
  pay_method_desc VARCHAR(512) NOT NULL DEFAULT '',
  currency_type CHAR(3) NOT NULL DEFAULT '',
  frozen_token CHAR(32) NOT NULL DEFAULT '',
  transcore_trans_no CHAR(32) NOT NULL DEFAULT '',
  transfer_trans_no CHAR(32) NOT NULL DEFAULT '',
  contract_no CHAR(32) NOT NULL DEFAULT '',
  biz_channel INT(11) NOT NULL,
  status INT(11) NOT NULL,
  ext_field VARCHAR(256) NOT NULL DEFAULT '',
  tracking_no CHAR(32) NOT NULL,
  pay_time TIMESTAMP ,
  finish_time TIMESTAMP ,
  expiry_time TIMESTAMP ,
  status_expiry_time TIMESTAMP ,
  create_time TIMESTAMP NOT NULL ,
  update_time TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  remark VARCHAR(256) NOT NULL DEFAULT '',
  operator_no CHAR(32) NOT NULL DEFAULT '',
  PRIMARY KEY (id),
  UNIQUE KEY `transferee_tracking_no_unique_idx` (`tracking_no`),
  KEY `fund_transferee_order_no_idx` (`fund_transferee_order_no`),
  KEY `transferee_biz_order_no_idx` (`business_order_item_no`),
  KEY `transferee_fund_transferor_order_no_idx` (`ref_fund_transferor_order_no`),
  KEY `transcore_trans_no_idx` (`transcore_trans_no`),
  KEY `transfer_trans_no_idx` (`transfer_trans_no`),
  KEY `member_no_status_idx` (`member_no`, `status`),
  KEY `transferee_status_expiry_time_idx` (`status`,`status_expiry_time`),
  KEY `transferee_finish_time_idx` (`finish_time`),
  KEY `transferee_create_time_idx` (`create_time`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `t_ftc_fund_transferee_order_status_info`;
CREATE TABLE t_ftc_fund_transferee_order_status_info (
  id BIGINT(20) UNSIGNED NOT NULL AUTO_INCREMENT,
  order_id BIGINT(20) NOT NULL,
  status INT(11) NOT NULL,
  update_time TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  operator_no CHAR(32) NOT NULL DEFAULT '',
  remark VARCHAR(255) NOT NULL DEFAULT '',
  PRIMARY KEY (`id`),
  KEY `transferee_order_status_idx` (`order_id`,`status`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


ALTER TABLE t_ftc_fund_clearing ADD market_level INT(11) NOT NULL DEFAULT 1 AFTER business_order_item_no;

ALTER TABLE t_ftc_fund_dividend ADD market_level INT(11) NOT NULL DEFAULT 1 AFTER business_order_item_no;

ALTER TABLE t_amc_fund_detail ADD exchange_status tinyint NOT NULL DEFAULT 0;
