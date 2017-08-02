use eif_ftc;
ALTER TABLE `eif_ftc`.`t_amc_fund_detail` ADD COLUMN `product_close_type` int NOT NULL DEFAULT 0 COMMENT '产品类型（封闭-1，半封闭半开放-2，开放-0）';
ALTER TABLE `eif_ftc`.`t_amc_fund_bonus_detail_alteration` ADD COLUMN `bonus_type` int NOT NULL DEFAULT 0 COMMENT '0-活期红利；1-活包定红利；2-定期红利' ;
ALTER TABLE `eif_ftc`.`t_amc_fund_detail` ADD COLUMN `half_open_bonus_amount` decimal(26,6) NOT NULL DEFAULT 0 COMMENT '活包定TA返回的红利' ;

ALTER TABLE eif_ftc.t_ftc_fund_clearing ADD marketing_discount DECIMAL(26,6) NOT NULL COMMENT '营销贴息金额' AFTER groupon_amount;
ALTER TABLE eif_ftc.t_ftc_fund_clearing ADD platform_profit DECIMAL(26,6) NOT NULL DEFAULT 0 COMMENT '平台利润金额（用户投资带来的利润超过预期收益时）' AFTER marketing_discount;
ALTER TABLE eif_ftc.t_ftc_fund_clearing MODIFY dividend_amount DECIMAL(26,6) NOT NULL COMMENT '产品预期收益';
ALTER TABLE eif_ftc.t_ftc_fund_clearing ADD current_dividend_amount DECIMAL(26,6) NOT NULL COMMENT '活期产品收益' AFTER dividend_amount;

ALTER TABLE eif_ftc.t_ftc_fund_dividend ADD INDEX date_serial_no_idx (divident_date, external_serial_no);
