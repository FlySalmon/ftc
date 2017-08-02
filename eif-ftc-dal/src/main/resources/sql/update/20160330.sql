drop index cur_status_expiry_time_idx on t_ftc_fund_trans_order;

create index cur_status_expiry_time_status_idx on t_ftc_fund_trans_order
(
   status,
   cur_status_expiry_time
);


ALTER TABLE `eif_ftc`.`t_amc_fund_total` ADD INDEX `idx_t_amc_fund_total_acc_uuid` USING BTREE (`fund_acc_uuid`) comment '索引防止死锁';

ALTER TABLE `eif_ftc`.`t_amc_fund_detail` ADD UNIQUE KEY(fund_total_uuid, product_id);