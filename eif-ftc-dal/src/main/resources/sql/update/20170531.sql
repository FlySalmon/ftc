
use eif_ftc;

CREATE TABLE `t_ftc_fund_trends` (
  `id` BIGINT(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键',
  `fund_code` CHAR(6) NOT NULL COMMENT '基金代码',
  `fund_nav` DECIMAL(7,4) NOT NULL DEFAULT '0.0000' COMMENT '基金单位净值',
  `fund_nav_date` TIMESTAMP NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '基金净值日期',
  `registration_date` TIMESTAMP NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '权益登记日期',
  `fund_size` DECIMAL(16,2) NOT NULL DEFAULT '0.00' COMMENT '基金的金额规模',
  `fund_income_per_million` DECIMAL(8,5) NOT NULL DEFAULT '0.00000' COMMENT '基金万份收益',
  `fund_annual_yield` DECIMAL(8,5) NOT NULL DEFAULT '0.00000' COMMENT '基金年收益率',
  `fund_week_annual_yield` DECIMAL(8,5) NOT NULL DEFAULT '0.00000' COMMENT '基金七日年化收益率',
  `create_time` TIMESTAMP NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '创建时间',
  `update_time` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  `operator_no` CHAR(32) NOT NULL DEFAULT '' COMMENT '操作人号',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_fund_nav_date_idx` (`fund_nav_date`, `fund_code`)
) ENGINE=INNODB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COMMENT='基金自然日动态';

CREATE TABLE `t_ftc_ta_fund_trends` (
  `id` BIGINT(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键',
  `fund_code` CHAR(6) NOT NULL COMMENT '基金代码',
  `total_fund_vol` DECIMAL(16,2) NOT NULL DEFAULT '0.00' COMMENT '基金总份数',
  `net_value_type` CHAR(1) NOT NULL COMMENT '净值类型, 0-普通净值; 1-申购净值; 2-赎回净值;',
  `nav` DECIMAL(7,4) NOT NULL DEFAULT '0.0000' COMMENT '基金单位净值',
  `update_date` CHAR(8) NOT NULL COMMENT '基金净值日期',
  `divident_date` CHAR(8) NOT NULL COMMENT '分红日/发放日',
  `registration_date` CHAR(8) NOT NULL COMMENT '权益登记日期',
  `xr_date` CHAR(8) NOT NULL COMMENT '除权日',
  `accumulative_nav` DECIMAL(7,4) NOT NULL DEFAULT '0.0000' COMMENT '累计基金单位净值',
  `total_divident` DECIMAL(8,5) NOT NULL DEFAULT '0.00000' COMMENT '累计单位分红',
  `fund_size` DECIMAL(16,2) NOT NULL DEFAULT '0.00' COMMENT '基金的金额规模',
  `fund_day_income` DECIMAL(16,2) NOT NULL DEFAULT '0.00' COMMENT '基金当日总收益',
  `fund_income` DECIMAL(8,5) NOT NULL DEFAULT '0.00000' COMMENT '基金万份收益',
  `fund_year_income_rate` DECIMAL(8,5) NOT NULL DEFAULT '0.00000' COMMENT '基金年收益率',
  `yield` DECIMAL(8,5) NOT NULL DEFAULT '0.00000' COMMENT '基金七日年化收益率',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_fund_navtype_date_idx` (`net_value_type`,`update_date`, `fund_code`)
) ENGINE=INNODB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COMMENT='TA基金交易日动态';

ALTER TABLE `t_ftc_fund_dividend` ADD discount_interest DECIMAL(16,2) DEFAULT '0.00' COMMENT '贴息金额' AFTER `dividend_amount`;
ALTER TABLE `t_ftc_fund_dividend` ADD ext_field VARCHAR(256) DEFAULT '' COMMENT '扩展字段' AFTER `external_serial_no`;

ALTER TABLE `t_ftc_fund_clearing` ADD contract_no CHAR(32) DEFAULT '' COMMENT '合同编号（仅限活期封闭期产品解封用）' AFTER `external_serial_no`;
ALTER TABLE `t_ftc_fund_clearing` ADD ext_field VARCHAR(256) DEFAULT '' COMMENT '扩展字段' AFTER `contract_no`;
