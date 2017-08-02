use `eif_ftc`;

/*==============================================================*/
/* Table: t_ftc_fund_trans_order_exception                      */
/*==============================================================*/
create table t_ftc_fund_trans_order_exception
(
   id                   bigint(20) unsigned not null auto_increment comment '主键',
   fund_trans_order_no  char(32) not null comment '基金交易业务单号',
   err_code             char(11) not null default '' comment '错误码',
   err_msg              text not null comment '错误信息',
   create_time          timestamp not null comment '错误时间',
   primary key (id)
);

alter table t_ftc_fund_trans_order_exception comment '基金交易业务单异常表';

/*==============================================================*/
/* Index: fund_trans_order_no_idx                               */
/*==============================================================*/
create index fund_trans_order_no_idx on t_ftc_fund_trans_order_exception
(
   fund_trans_order_no
);

DROP TABLE IF EXISTS t_ftc_fund_dividend_status_info;

DROP TABLE IF EXISTS t_ftc_fund_trans_order_daily_summary;

/*==============================================================*/
/* Table: t_ftc_fund_dividend_status_info                       */
/*==============================================================*/
CREATE TABLE t_ftc_fund_dividend_status_info
(
   id                   BIGINT(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键',
   order_id             BIGINT(20) NOT NULL COMMENT '基金分红单主表id',
   STATUS               INT NOT NULL COMMENT '基金分红单状态',
   update_time          TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '状态迁移时间',
   operator_no          CHAR(32) NOT NULL DEFAULT "" COMMENT '操作人',
   remark               VARCHAR(255) NOT NULL DEFAULT "" COMMENT '备注',
   PRIMARY KEY (id)
);

ALTER TABLE t_ftc_fund_dividend_status_info COMMENT '基金分红单状态信息表';

/*==============================================================*/
/* Index: order_status_idx                                      */
/*==============================================================*/
create index order_status_idx on t_ftc_fund_dividend_status_info
(
   order_id,
   status
);


/*==============================================================*/
/* Table: t_ftc_fund_trans_order_daily_summary                  */
/*==============================================================*/
CREATE TABLE t_ftc_fund_trans_order_daily_summary
(
   id                   BIGINT(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键',
   batch_no             CHAR(32) NOT NULL COMMENT '批次编号',
   trans_type           INT(4) NOT NULL COMMENT '交易类型：1 - 认购; 2 - 申购; 3 - 赎回; 4 - 红利; 5 - 兑付; ',
   trans_date           TIMESTAMP NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '交易日期',
   order_count          INT(11) NOT NULL DEFAULT 0 COMMENT '有效订单总数',
   succ_count           INT(11) NOT NULL DEFAULT 0 COMMENT '处理成功订单总数',
   fail_count           INT(11) NOT NULL DEFAULT 0 COMMENT '处理失败订单总数',
   pending_count        INT(11) NOT NULL DEFAULT 0 COMMENT '待处理订单总数',
   operator_no          CHAR(32) NOT NULL COMMENT '操作人号',
   create_time          TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
   PRIMARY KEY (id)
);

ALTER TABLE t_ftc_fund_trans_order_daily_summary COMMENT '基金交易单日汇总表';


/*==============================================================*/
/* Index: 日期交易类型索引                                              */
/*==============================================================*/
create index trans_date_type_idx on t_ftc_fund_trans_order_daily_summary
(
   trans_date,
   trans_type
);
