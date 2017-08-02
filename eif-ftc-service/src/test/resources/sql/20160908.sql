ALTER TABLE `t_ftc_fund_dividend` ADD ta_account_id CHAR(12) DEFAULT '' COMMENT '投资人基金账号' AFTER `transaction_account_id`;
ALTER TABLE t_ftc_fund_dividend DROP INDEX ta_serial_no_idx;

