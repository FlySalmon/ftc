ALTER TABLE `t_amc_fund_detail` ADD COLUMN `product_close_type` int NOT NULL DEFAULT 0 COMMENT '产品类型（封闭-1，半封闭半开放-2，开放-0）' AFTER `groupon_bonus`;
ALTER TABLE `t_amc_fund_bonus_detail_alteration` ADD COLUMN `bonus_type` int NOT NULL DEFAULT 0 COMMENT '0-活期红利；1-活包定红利；2-定期红利' AFTER `update_time`;
ALTER TABLE `t_amc_fund_detail` ADD COLUMN `half_open_bonus_amount` decimal(26,6) NOT NULL DEFAULT 0 COMMENT '活包定TA返回的红利' ;
ALTER TABLE `t_ftc_fund_clearing` ADD `marketing_discount` DECIMAL(26,6) NOT NULL COMMENT '营销贴息金额' AFTER groupon_amount;