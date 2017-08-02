
ALTER TABLE `t_amc_fund_detail` ADD groupon_bonus DECIMAL(26,6) DEFAULT 0 NOT NULL COMMENT '团购收益金额';
ALTER TABLE `t_ftc_fund_clearing` ADD dividend_amount DECIMAL(26,6) NOT NULL COMMENT '利息' AFTER transcore_trans_no;
ALTER TABLE `t_ftc_fund_clearing` ADD groupon_amount DECIMAL(26,6) NOT NULL COMMENT '团购奖励金额' AFTER coupon_amount;
ALTER TABLE `t_amc_fund_profit_alteration` ADD COLUMN `profit_type` int NOT NULL DEFAULT 0 COMMENT '0-加息券，1-团购贴息，2-活包定贴息' AFTER `update_time`;
