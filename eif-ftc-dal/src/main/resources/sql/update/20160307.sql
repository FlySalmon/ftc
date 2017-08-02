ALTER TABLE `eif_ftc`.`t_amc_fund_total` ADD INDEX `idx_t_amc_fund_total_uuid` USING BTREE (`fund_total_uuid`) comment '行锁增加索引';
ALTER TABLE `eif_ftc`.`t_amc_fund_detail` ADD INDEX `idx_t_amc_fund_dtl_uuid` USING BTREE (`fund_detail_uuid`) comment '行锁增加索引';
ALTER TABLE `eif_ftc`.`t_amc_mem_asset` ADD INDEX `idx_t_amc_mem_asset_uuid` USING BTREE (`member_asset_uuid`) comment '行锁增加索引';