ALTER TABLE `eif_ftc`.`t_amc_fund_bonus_detail_alteration` ADD INDEX `idx_bonus_fdtu_bonus_time` USING BTREE (`fund_detail_uuid`, `bonus_time`) comment '';


ALTER TABLE `eif_ftc`.`t_amc_fund_bonus_detail_alteration` ADD INDEX `idx_bonus_ftu_bouns_time` USING BTREE (`fund_total_uuid`, `bonus_time`) comment '';


ALTER TABLE `eif_ftc`.`t_ftc_fund_clearing` ADD INDEX `query_idx` USING BTREE (`create_time`) comment '';



ALTER TABLE `eif_ftc`.`t_amc_fund_detail` ADD INDEX `idx_product_id` USING BTREE (`product_id`) comment '';



ALTER TABLE `eif_ftc`.`t_amc_fund_profit_alteration` ADD INDEX `idx_query_total` USING BTREE (`fund_total_uuid`, `create_time`) comment '';


ALTER TABLE `eif_ftc`.`t_amc_fund_profit_alteration` ADD INDEX `idx_query_detail` USING BTREE (`fund_detail_uuid`, `create_time`) comment '';


ALTER TABLE `eif_ftc`.`t_amc_mem_fund_acc` ADD INDEX `idx_mem_no` USING BTREE (`member_no`) comment '';


ALTER TABLE `eif_ftc`.`t_amc_mem_fund_acc` ADD INDEX `idx_ftc_order_no` USING BTREE (`ftc_create_acc_no`) comment '';


ALTER TABLE `eif_ftc`.`t_amc_mem_fund_acc` ADD INDEX `idx_trans_acc_ta_acc` USING BTREE (`transaction_account`, `ta_account`) comment '';


