drop index trans_acct_no_idx on t_ftc_fund_trans_order;

drop index time_prod_mem_idx on t_ftc_fund_trans_order;

/*==============================================================*/
/* Index: prod_mem_trans_craete_idx                             */
/*==============================================================*/
create index prod_mem_trans_craete_idx on t_ftc_fund_trans_order
(
   product_id,
   member_no,
   trans_time,
   create_time
);

/*==============================================================*/
/* Index: prod_trans_acct_no_idx                                */
/*==============================================================*/
create index prod_trans_acct_no_idx on t_ftc_fund_trans_order
(
   trans_account_no,
   product_id
);

/*==============================================================*/
/* Index: cur_status_expiry_time_idx                            */
/*==============================================================*/
create index cur_status_expiry_time_idx on t_ftc_fund_trans_order
(
   cur_status_expiry_time
);

/*==============================================================*/
/* Index: trans_time_idx                                        */
/*==============================================================*/
create index trans_time_idx on t_ftc_fund_trans_order
(
   trans_time
);

/*==============================================================*/
/* Index: finish_time_idx                                        */
/*==============================================================*/
create index finish_time_idx on t_ftc_fund_trans_order
(
   finish_time
);