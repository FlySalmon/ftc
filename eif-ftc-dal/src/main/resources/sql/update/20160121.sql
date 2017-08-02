
DROP INDEX order_status_idx ON t_ftc_fund_acct_order_status_info;
CREATE UNIQUE INDEX order_status_idx ON t_ftc_fund_acct_order_status_info
(
   order_id,
   status
);
drop index order_status_idx on t_ftc_fund_trans_order_status_info;
create unique index order_status_idx on t_ftc_fund_trans_order_status_info
(
   order_id,
   status
);
