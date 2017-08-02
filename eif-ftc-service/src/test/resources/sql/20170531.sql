DROP TABLE IF EXISTS `t_ftc_fund_trends`;
CREATE TABLE `t_ftc_fund_trends` (
  `id` BIGINT(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '',
  `fund_code` CHAR(6) NOT NULL COMMENT '',
  `fund_nav` DECIMAL(7,4) NOT NULL DEFAULT '0.0000' COMMENT '',
  `fund_nav_date` TIMESTAMP NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '',
  `registration_date` TIMESTAMP NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '',
  `fund_size` DECIMAL(16,2) NOT NULL DEFAULT '0.00' COMMENT '',
  `fund_income_per_million` DECIMAL(8,5) NOT NULL DEFAULT '0.00000' COMMENT '',
  `fund_annual_yield` DECIMAL(8,5) NOT NULL DEFAULT '0.00000' COMMENT '',
  `fund_week_annual_yield` DECIMAL(8,5) NOT NULL DEFAULT '0.00000' COMMENT '',
  `create_time` TIMESTAMP NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '',
  `update_time` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '',
  `operator_no` CHAR(32) NOT NULL DEFAULT '' COMMENT '',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_fund_nav_date_idx` (`fund_nav_date`, `fund_code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `t_ftc_ta_fund_trends`;
CREATE TABLE `t_ftc_ta_fund_trends` (
  `id` BIGINT(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '',
  `fund_code` CHAR(6) NOT NULL COMMENT '',
  `total_fund_vol` DECIMAL(16,2) NOT NULL DEFAULT '0.00' COMMENT '',
  `net_value_type` CHAR(1) NOT NULL COMMENT '',
  `nav` DECIMAL(7,4) NOT NULL DEFAULT '0.0000' COMMENT '',
  `update_date` CHAR(8) NOT NULL COMMENT '',
  `divident_date` CHAR(8) NOT NULL COMMENT '',
  `registration_date` CHAR(8) NOT NULL COMMENT '',
  `xr_date` CHAR(8) NOT NULL COMMENT '',
  `accumulative_nav` DECIMAL(7,4) NOT NULL DEFAULT '0.0000' COMMENT '',
  `total_divident` DECIMAL(8,5) NOT NULL DEFAULT '0.00000' COMMENT '',
  `fund_size` DECIMAL(16,2) NOT NULL DEFAULT '0.00' COMMENT '',
  `fund_day_income` DECIMAL(16,2) NOT NULL DEFAULT '0.00' COMMENT '',
  `fund_income` DECIMAL(8,5) NOT NULL DEFAULT '0.00000' COMMENT '',
  `fund_year_income_rate` DECIMAL(8,5) NOT NULL DEFAULT '0.00000' COMMENT '',
  `yield` DECIMAL(8,5) NOT NULL DEFAULT '0.00000' COMMENT '',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_fund_navtype_date_idx` (`net_value_type`,`update_date`, `fund_code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

ALTER TABLE `t_ftc_fund_dividend` ADD discount_interest DECIMAL(16,2) DEFAULT '0.00' COMMENT '' AFTER `dividend_amount`;
ALTER TABLE `t_ftc_fund_dividend` ADD ext_field VARCHAR(256) DEFAULT '' COMMENT '' AFTER `external_serial_no`;

ALTER TABLE `t_ftc_fund_clearing` ADD contract_no CHAR(32) DEFAULT '' COMMENT '' AFTER `external_serial_no`;
ALTER TABLE `t_ftc_fund_clearing` ADD ext_field VARCHAR(256) DEFAULT '' COMMENT '' AFTER `contract_no`;
