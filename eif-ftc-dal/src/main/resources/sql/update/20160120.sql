
CREATE UNIQUE INDEX prod_mem_idx ON t_ftc_fund_clearing
(
   product_id,
   member_no,
   transaction_account_id
);

CREATE INDEX trans_time_idx ON t_ftc_fund_acct_order
(
   trans_time
);

CREATE UNIQUE INDEX prod_mem_date_idx ON t_ftc_fund_dividend
(
   product_id,
   member_no,
   transaction_account_id,
   divident_date
);

CREATE INDEX ta_serial_no_idx ON t_ftc_fund_dividend
(
   external_serial_no
);

CREATE INDEX biz_order_no_idx ON t_ftc_fund_dividend
(
   business_order_item_no
);

create index app_sheet_serial_no_idx on t_ftc_ta_fund_create_acct_order
(
   app_sheet_serial_no
);
CREATE UNIQUE INDEX unique_fund_trans_acc_no ON t_ftc_ta_fund_create_acct_order
(
   transaction_account_id
);

CREATE UNIQUE INDEX app_sheet_serial_no_idx ON t_ftc_ta_fund_purchasing_order
(
   app_sheet_serial_no
);

CREATE UNIQUE INDEX app_sheet_serial_no_idx ON t_ftc_ta_fund_subscribing_order
(
   app_sheet_serial_no
);

CREATE UNIQUE INDEX app_sheet_serial_no_idx ON t_ftc_ta_fund_redeeming_order
(
   app_sheet_serial_no
);
