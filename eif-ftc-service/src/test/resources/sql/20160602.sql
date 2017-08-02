
ALTER TABLE `t_amc_fund_bonus_detail_alteration` ADD INDEX `idx_bonus_fdtu_bonus_time`  (`fund_detail_uuid`, `bonus_time`) ;

ALTER TABLE `t_amc_fund_bonus_detail_alteration` ADD INDEX `idx_bonus_ftu_bouns_time`  (`fund_total_uuid`, `bonus_time`) ;

ALTER TABLE `t_ftc_fund_clearing` ADD INDEX `query_idx`  (`create_time`) ;

ALTER TABLE `t_amc_fund_detail` ADD INDEX `idx_product_id`  (`product_id`) ;

ALTER TABLE `t_amc_fund_profit_alteration` ADD INDEX `idx_query_total`  (`fund_total_uuid`, `create_time`) ;

ALTER TABLE `t_amc_fund_profit_alteration` ADD INDEX `idx_query_detail`  (`fund_detail_uuid`, `create_time`) ;

ALTER TABLE `t_amc_mem_fund_acc` ADD INDEX `idx_mem_no`  (`member_no`) ;

ALTER TABLE `t_amc_mem_fund_acc` ADD INDEX `idx_ftc_order_no`  (`ftc_create_acc_no`) ;

ALTER TABLE `t_amc_mem_fund_acc` ADD INDEX `idx_trans_acc_ta_acc`  (`transaction_account`, `ta_account`) ;

ALTER TABLE `t_ftc_fund_dividend` ADD INDEX `ta_serial_no_idx`  (`divident_date`,`external_serial_no`) ;

ALTER TABLE `t_ftc_fund_dividend` ADD INDEX `idx_fund_div_order_no`  (`fund_dividend_order_no`) ;

ALTER TABLE `t_ftc_fund_dividend` ADD INDEX `idx_cfm_date`  (`transaction_cfm_date`) ;

ALTER TABLE `t_ftc_ta_fund_dividend_order` ADD INDEX `idx_ta_serial_no`  (`ta_serial_no`) ;

ALTER TABLE `t_ftc_fund_clearing` ADD INDEX `idx_fund_clearing_order_no`  (`fund_clearing_order_no`) ;

ALTER TABLE `t_ftc_fund_clearing` ADD INDEX `idx_fund_trans_no`  (`transcore_trans_no`) ;

ALTER TABLE `t_ftc_fund_clearing` ADD INDEX `idx_trans_cfm_date`  (`transaction_cfm_date`) ;
