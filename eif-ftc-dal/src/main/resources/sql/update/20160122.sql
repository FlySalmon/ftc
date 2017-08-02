
drop index order_status_idx on t_ftc_fund_trans_order_status_info;
create index order_status_idx on t_ftc_fund_trans_order_status_info
(
   order_id,
   status
);

drop index order_status_idx on t_ftc_fund_acct_order_status_info;
create index order_status_idx on t_ftc_fund_acct_order_status_info
(
   order_id,
   status
);

drop index prod_mem_unique_idx on t_ftc_fund_personal_purchase_vol;

drop table if exists t_ftc_fund_personal_purchase_vol;

/*==============================================================*/
/* Table: t_ftc_fund_personal_purchase_vol                      */
/*==============================================================*/
create table t_ftc_fund_personal_purchase_vol
(
   id                   bigint(20) unsigned not null auto_increment comment '主键',
   product_id           bigint(20) not null default 0 comment '产品id',
   amount               decimal(26,6) not null default 0 comment '购买金额',
   shares               decimal(26,6) not null default 0 comment '购买份额',
   member_no            char(32) not null comment '会员号',
   primary key (id)
);

alter table t_ftc_fund_personal_purchase_vol comment '基金产品个人购买表';

/*==============================================================*/
/* Index: prod_mem_unique_idx                                   */
/*==============================================================*/
create unique index prod_mem_unique_idx on t_ftc_fund_personal_purchase_vol
(
   product_id,
   member_no
);
