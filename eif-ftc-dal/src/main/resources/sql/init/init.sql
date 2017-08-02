/*==============================================================*/
/* DBMS name:      MySQL 5.0                                    */
/* Created on:     2016-1-27 15:17:59                           */
/*==============================================================*/

SET NAMES utf8;
SET FOREIGN_KEY_CHECKS = 0;
USE eif_ftc;


drop table if exists t_ftc_batch_apply_failure_detail;

drop table if exists t_ftc_batch_confirm_failure_detail;

drop table if exists t_ftc_batch_task_log;

drop table if exists t_ftc_fund_acct_order;

drop table if exists t_ftc_fund_acct_order_status_info;

drop table if exists t_ftc_fund_buying_member;

drop table if exists t_ftc_fund_clearing;

drop table if exists t_ftc_fund_clearing_status_info;

drop table if exists t_ftc_fund_dividend;

drop table if exists t_ftc_fund_personal_purchase_daily_vol;

drop table if exists t_ftc_fund_personal_purchase_vol;

drop table if exists t_ftc_fund_personal_redeem_daily_vol;

drop table if exists t_ftc_fund_purchase_daily_vol;

drop table if exists t_ftc_fund_trans_order;

drop table if exists t_ftc_fund_trans_order_status_info;

drop table if exists t_ftc_ta_fund_checking_acct_order;

drop table if exists t_ftc_ta_fund_clearing_order;

drop table if exists t_ftc_ta_fund_create_acct_cfm_order;

drop table if exists t_ftc_ta_fund_create_acct_order;

drop table if exists t_ftc_ta_fund_dividend_order;

drop table if exists t_ftc_ta_fund_purchasing_cfm_order;

drop table if exists t_ftc_ta_fund_purchasing_order;

drop table if exists t_ftc_ta_fund_redeeming_cfm_order;

drop table if exists t_ftc_ta_fund_redeeming_order;

drop table if exists t_ftc_ta_fund_subscribing_cfm_order;

drop table if exists t_ftc_ta_fund_subscribing_order;

drop table if exists t_ftc_ta_fund_subscribing_result_order;

/*==============================================================*/
/* Table: t_ftc_batch_apply_failure_detail                      */
/*==============================================================*/
create table t_ftc_batch_apply_failure_detail
(
   id                   bigint(20) not null auto_increment comment '主键',
   batch_id             bigint(20) not null comment '批次Id',
   order_no             char(32) not null comment '订单号',
   business_code        char(3) not null comment '业务代码',
   error_code           char(11) not null default '“”' comment '错误代码',
   error_msg            varchar(1024) not null default '“”' comment '错误信息',
   status               smallint(1) not null comment '状态：1 - 待处理，2 - 已处理，3 - 无效',
   create_time          timestamp not null default '0000-00-00 00:00:00' comment '创建时间',
   update_time          timestamp not null default CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP comment '更新时间',
   operator             char(32) default '“”' comment '操作人',
   remark               varchar(255) default "" comment '备注',
   primary key (id)
);

alter table t_ftc_batch_apply_failure_detail comment '任务批次申请失败明细';

/*==============================================================*/
/* Index: batch_business_order_idx                              */
/*==============================================================*/
create index batch_business_order_idx on t_ftc_batch_apply_failure_detail
(
   batch_id,
   business_code,
   order_no
);

/*==============================================================*/
/* Table: t_ftc_batch_confirm_failure_detail                    */
/*==============================================================*/
create table t_ftc_batch_confirm_failure_detail
(
   id                   bigint(20) not null auto_increment comment '主键',
   batch_id             bigint(20) not null comment '批次Id',
   serial_no            char(32) not null comment '序列号',
   business_code        char(3) not null comment '业务代码',
   error_code           char(11) not null default '“”' comment '错误代码',
   error_msg            varchar(1024) not null default '“”' comment '错误信息',
   status               smallint(1) not null comment '状态：1 - 待处理，2 - 已处理，3 - 无效',
   create_time          timestamp not null default '0000-00-00 00:00:00' comment '创建时间',
   update_time          timestamp not null default CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP comment '更新时间',
   operator             char(32) default '“”' comment '操作人',
   remark               varchar(255) default "" comment '备注',
   primary key (id)
);

alter table t_ftc_batch_confirm_failure_detail comment '任务批次确认失败明细';

/*==============================================================*/
/* Index: batch_business_serialno_idx                           */
/*==============================================================*/
create index batch_business_serialno_idx on t_ftc_batch_confirm_failure_detail
(
   batch_id,
   business_code,
   serial_no
);

/*==============================================================*/
/* Table: t_ftc_batch_task_log                                  */
/*==============================================================*/
create table t_ftc_batch_task_log
(
   id                   bigint(20) not null auto_increment comment '主键',
   business_code        char(3) not null comment '业务代码',
   start_time           timestamp not null default '0000-00-00 00:00:00' comment '起始时间',
   end_time             timestamp not null default '0000-00-00 00:00:00' comment '终止时间',
   total_count          int(4) not null default 0 comment '处理记录总数',
   success_count        int(4) not null default 0 comment '订单处理成功数',
   failure_count        int(4) not null default 0 comment '订单处理失败数',
   invalid_count        int(4) not null default 0 comment '无效订单数',
   primary key (id)
);

alter table t_ftc_batch_task_log comment '任务批次日志';

/*==============================================================*/
/* Index: business_time_idx                                     */
/*==============================================================*/
create index business_time_idx on t_ftc_batch_task_log
(
   business_code,
   start_time
);

/*==============================================================*/
/* Table: t_ftc_fund_acct_order                                 */
/*==============================================================*/
create table t_ftc_fund_acct_order
(
   id                   bigint(20) unsigned not null auto_increment comment '主键',
   member_no            char(32) not null comment '会员号',
   product_id           bigint(20) not null comment '用户产品ID',
   asset_account_no     char(32) not null default "" comment '资产账户号',
   fund_acct_order_no   char(32) not null comment '基金账户业务单号',
   fund_trans_acct_no   char(17) not null comment '基金交易账户号',
   ref_fund_trans_order_no char(32) not null default "" comment '关联基金交易业务单号',
   fund_acct_oper_type  int(11) not null comment '基金账户操作类型: 0 - 开户',
   tracking_no          char(32) not null comment '防止幂等,追踪',
   ref_fund_business_code char(3) not null default "" comment 'ta业务代码',
   biz_channel          int(11) not null comment '渠道类型：IOS = 11; ANDROID = 12; H5 = 13; WECHAT = 14; WEB = 20;',
   fund_interact_type   int(11) not null comment '基金提交方式：1 - 实时; 2 - 文件',
   status               int not null default -1 comment '状态: 1 - 资产账户开户中; 2 - 资产账户开户成功; 3 - 资产账户开户失败; 4 - 基金公司开户中; 5 - 基金公司开户成功;  6 - 基金公司开户失败',
   total_expiry_time    timestamp not null default '0000-00-00 00:00:00' comment '总有效期',
   cur_status_expiry_time timestamp not null default '0000-00-00 00:00:00' comment '当前状态过期时间',
   ref_app_sheet_serial_no char(24) not null default "" comment 'ta申请单号, 可关联请求，确认，结果',
   ext_info_order_no    char(32) not null default "" comment 'reserved',
   remark               varchar(255) not null default "" comment '备注',
   ext_field            varchar(255) not null default "" comment '业务扩展字段，保存json字串',
   trans_time           timestamp not null default '0000-00-00 00:00:00' comment '处理时间',
   create_time          timestamp not null default '0000-00-00 00:00:00' comment '创建时间',
   update_time          timestamp not null default CURRENT_TIMESTAMP comment '更新时间',
   operator_no          char(32) not null default "" comment '操作人号',
   primary key (id)
);

alter table t_ftc_fund_acct_order comment '基金账户业务单';

/*==============================================================*/
/* Index: unique_tracking_no                                    */
/*==============================================================*/
create unique index unique_tracking_no on t_ftc_fund_acct_order
(
   tracking_no
);

/*==============================================================*/
/* Index: serial_no_idx                                         */
/*==============================================================*/
create index serial_no_idx on t_ftc_fund_acct_order
(
   ref_app_sheet_serial_no
);

/*==============================================================*/
/* Index: trans_time_idx                                        */
/*==============================================================*/
create index trans_time_idx on t_ftc_fund_acct_order
(
   trans_time
);

/*==============================================================*/
/* Table: t_ftc_fund_acct_order_status_info                     */
/*==============================================================*/
create table t_ftc_fund_acct_order_status_info
(
   id                   bigint(20) unsigned not null auto_increment comment '主键',
   order_id             bigint(20) not null comment '基金账户业务单主表id',
   status               int not null comment '基金业务单状态',
   update_time          timestamp not null comment '状态迁移时间',
   operator_no          char(32) not null default "" comment '操作人',
   remark               varchar(255) not null default "" comment '备注',
   primary key (id)
);

alter table t_ftc_fund_acct_order_status_info comment '记录基金账户业务单状态时间信息';

/*==============================================================*/
/* Index: order_status_idx                                      */
/*==============================================================*/
create index order_status_idx on t_ftc_fund_acct_order_status_info
(
   order_id,
   status
);

/*==============================================================*/
/* Table: t_ftc_fund_buying_member                              */
/*==============================================================*/
create table t_ftc_fund_buying_member
(
   id                   bigint(20) unsigned not null auto_increment comment '主键',
   product_id           bigint(20) not null comment '产品id',
   member_id            char(32) not null comment '购买会员号',
   record_time          timestamp not null comment '记录日期',
   primary key (id)
);

alter table t_ftc_fund_buying_member comment '基金购买人员表';

/*==============================================================*/
/* Table: t_ftc_fund_clearing                                   */
/*==============================================================*/
create table t_ftc_fund_clearing
(
   id                   bigint(20) unsigned not null auto_increment comment '主键',
   fund_clearing_order_no char(32) not null comment '基金清盘业务单号',
   business_order_item_no char(32) not null comment '业务订单项流水号',
   product_id           bigint(20) not null comment '产品ID',
   member_no            char(32) not null comment '会员ID',
   transaction_account_id char(17) not null comment '投资人基金交易账号',
   transaction_cfm_date char(8) not null comment 'ta交易确认日期,格式为：YYYYMMDD
            ',
   currency_type        char(3) not null comment '结算币种,具体编码依GB/T 12406-2008
            ',
   total_frozen_vol     decimal(16,2) not null default 0 comment '基金冻结总份数',
   fund_vol_balance     decimal(16,2) not null default 0 comment '基金份数余额',
   achievement_pay      decimal(16,2) not null default 0 comment '业绩报酬',
   achievement_compen   decimal(16,2) not null default 0 comment '业绩补偿',
   frozen_balance       decimal(16,2) not null default 0 comment '冻结金额',
   confirmed_vol        decimal(16,2) not null comment '基金账户交易确认份数',
   confirmed_amount     decimal(16,2) not null comment '每笔交易确认金额,含所有费用的总金额',
   nav                  decimal(7,4) not null comment '基金单位净值',
   share_class          char(1) not null comment '收费方式:0-前收费，1-后收费，表明基金是前收费或后收费基金',
   transcore_trans_no   char(32) not null default "" comment '交易核心交易单流水号',
   coupon_amount        decimal(16,2) not null default 0 comment '额外加息金额',
   external_serial_no   varchar(32) not null default "",
   status               int not null comment '记录单处理状态',
   create_time          timestamp not null default '0000-00-00 00:00:00' comment '创建时间',
   update_time          timestamp not null comment '更新时间',
   operator_no          char(32) not null default "" comment '操作人号',
   primary key (id)
);

alter table t_ftc_fund_clearing comment '基金清盘单';

/*==============================================================*/
/* Index: prod_mem_idx                                          */
/*==============================================================*/
create unique index prod_mem_idx on t_ftc_fund_clearing
(
   product_id,
   member_no,
   transaction_account_id
);

/*==============================================================*/
/* Index: serial_no_idx                                         */
/*==============================================================*/
create index serial_no_idx on t_ftc_fund_clearing
(
   external_serial_no
);

/*==============================================================*/
/* Index: biz_order_no_idx                                      */
/*==============================================================*/
create index biz_order_no_idx on t_ftc_fund_clearing
(
   business_order_item_no
);

/*==============================================================*/
/* Table: t_ftc_fund_clearing_status_info                       */
/*==============================================================*/
create table t_ftc_fund_clearing_status_info
(
   id                   bigint(20) unsigned not null auto_increment comment '主键',
   order_id             bigint(20) not null comment '基金清盘单主表id',
   status               int not null comment '基金清盘单状态',
   update_time          timestamp not null comment '状态迁移时间',
   operator_no          char(32) not null default "" comment '操作人',
   remark               varchar(255) not null default "" comment '备注',
   primary key (id)
);

alter table t_ftc_fund_clearing_status_info comment '基金清盘单状态信息表';

/*==============================================================*/
/* Index: order_status_idx                                      */
/*==============================================================*/
create index order_status_idx on t_ftc_fund_clearing_status_info
(
   order_id,
   status
);

/*==============================================================*/
/* Table: t_ftc_fund_dividend                                   */
/*==============================================================*/
create table t_ftc_fund_dividend
(
   id                   bigint(20) unsigned not null auto_increment comment '主键',
   fund_dividend_order_no char(32) not null comment '基金分红业务单号',
   business_order_item_no char(32) not null comment '业务订单项流水号',
   product_id           bigint(20) not null comment '产品ID',
   member_no            char(32) not null comment '会员号',
   transaction_account_id char(17) not null comment '投资人基金交易账号',
   transaction_cfm_date char(8) not null comment '交易确认日期,格式为：YYYYMMDD
            ',
   confirmed_amount     decimal(16,2) not null comment '每笔交易确认金额,含所有费用的总金额',
   charge               decimal(10,2) not null default 0 comment '手续费,投资人应付总手续费',
   agency_fee           decimal(10,2) not null default 0 comment '代理费, 手续费中划归销售人的部分
            ',
   transfer_fee         decimal(10,2) not null default 0 comment '过户费,',
   share_class          char(1) not null comment '收费方式:0-前收费，1-后收费，表明基金是前收费或后收费基金',
   draw_bonus_unit      decimal(10) not null comment '分红单位, 举例：每千份分多少，则分红单位就为一千',
   dividend_type        char(1) not null comment '分红类型: 0-普通分红，1-质押基金分红，2-货币基金收益结转，3-保本基金赔付，4-专户到期处理',
   achievement_pay      decimal(16,2) not null default 0 comment '业绩报酬',
   achievement_compen   decimal(16,2) not null default 0 comment '业绩补偿',
   currency_type        char(3) not null comment '结算币种,具体编码依GB/T 12406-2008
            ',
   basisfor_calculating_dividend decimal(16,2) not null comment '红利/红利再投资基数,登记日基金持有人的基金份数',
   divident_date        char(8) not null comment '分红日/发放日',
   vol_of_dividend_for_reinvestment decimal(16,2) not null comment '基金账户红利再投资基金份数,投资人实得红股，含被续冻的红股',
   dividend_amount      decimal(16,2) not null comment '基金账户红利资金,红利总金额,含冻结红利及再投资的红利',
   xr_rate              char(8) not null default "" comment '除权日',
   registration_date    char(8) not null default "" comment '权益登记日期, 格式为：YYYYMMDD',
   dividend_per_unit    decimal(16,2) not null comment '单位基金分红金额（含税）,举例：每千份分两元，则此处填2。',
   def_dividend_method  char(1) not null comment '默认分红方式: 0-红利转投，1-现金分红，投资人本次分红的方式',
   transcore_trans_no   char(32) not null default "" comment '交易核心交易单流水号',
   external_serial_no   varchar(32) not null default "",
   status               int not null comment '记录单处理状态',
   create_time          timestamp not null default '0000-00-00 00:00:00' comment '创建时间',
   update_time          timestamp not null default CURRENT_TIMESTAMP comment '更新时间',
   operator_no          char(32) not null default "" comment '操作人号',
   primary key (id)
);

alter table t_ftc_fund_dividend comment '基金分红单';

/*==============================================================*/
/* Index: prod_mem_date_idx                                     */
/*==============================================================*/
create unique index prod_mem_date_idx on t_ftc_fund_dividend
(
   product_id,
   member_no,
   transaction_account_id,
   divident_date
);

/*==============================================================*/
/* Index: ta_serial_no_idx                                      */
/*==============================================================*/
create index ta_serial_no_idx on t_ftc_fund_dividend
(
   external_serial_no
);

/*==============================================================*/
/* Index: biz_order_no_idx                                      */
/*==============================================================*/
create index biz_order_no_idx on t_ftc_fund_dividend
(
   business_order_item_no
);

/*==============================================================*/
/* Table: t_ftc_fund_personal_purchase_daily_vol                */
/*==============================================================*/
create table t_ftc_fund_personal_purchase_daily_vol
(
   id                   bigint(20) unsigned not null auto_increment comment '主键',
   member_no            char(32) not null default "" comment '会员号',
   product_id           bigint(20) not null default 0 comment '产品id',
   amount               decimal(26,6) not null default 0 comment '购买金额',
   shares               decimal(26,6) not null default 0 comment '购买份额',
   record_time          timestamp not null default '0000-00-00' comment '记录日期',
   primary key (id)
);

alter table t_ftc_fund_personal_purchase_daily_vol comment '基金产品会员单日购买表';

/*==============================================================*/
/* Index: mem_prod_time_unique_idx                              */
/*==============================================================*/
create unique index mem_prod_time_unique_idx on t_ftc_fund_personal_purchase_daily_vol
(
   member_no,
   product_id,
   record_time
);

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

/*==============================================================*/
/* Table: t_ftc_fund_personal_redeem_daily_vol                  */
/*==============================================================*/
create table t_ftc_fund_personal_redeem_daily_vol
(
   id                   bigint(20) unsigned not null auto_increment comment '主键',
   member_no            char(32) not null default "" comment '会员号',
   product_id           bigint(20) not null default 0 comment '产品id',
   amount               decimal(26,6) not null default 0 comment '赎回金额',
   shares               decimal(26,6) not null default 0 comment '赎回份额',
   record_time          timestamp not null default '0000-00-00' comment '记录日期',
   primary key (id)
);

alter table t_ftc_fund_personal_redeem_daily_vol comment '基金产品会员单日赎回表';

/*==============================================================*/
/* Index: mem_prod_time_unique_idx                              */
/*==============================================================*/
create unique index mem_prod_time_unique_idx on t_ftc_fund_personal_redeem_daily_vol
(
   member_no,
   product_id,
   record_time
);

/*==============================================================*/
/* Table: t_ftc_fund_purchase_daily_vol                         */
/*==============================================================*/
create table t_ftc_fund_purchase_daily_vol
(
   id                   bigint(20) unsigned not null auto_increment comment '主键',
   product_id           bigint(20) not null default 0 comment '产品id',
   amount               decimal(26,6) not null default 0 comment '购买金额',
   shares               decimal(26,6) not null default 0 comment '购买份额',
   record_time          timestamp not null default '0000-00-00' comment '记录日期',
   primary key (id)
);

alter table t_ftc_fund_purchase_daily_vol comment '基金产品单日购买表';

/*==============================================================*/
/* Index: prod_time_unique_idx                                  */
/*==============================================================*/
create unique index prod_time_unique_idx on t_ftc_fund_purchase_daily_vol
(
   product_id,
   record_time
);

/*==============================================================*/
/* Table: t_ftc_fund_trans_order                                */
/*==============================================================*/
create table t_ftc_fund_trans_order
(
   id                   bigint(20) unsigned not null auto_increment comment '主键',
   fund_trans_order_no  char(32) not null comment '基金交易业务单号',
   member_no            char(32) not null comment '会员号',
   asset_account_no     char(32) not null comment '资产账户号',
   ref_fund_account_no  char(12) not null default "" comment '对应TA投资人基金账号',
   trans_account_no     char(17) not null default "" comment '投资人基金交易账号',
   parent_fund_trans_order_no char(32) not null default "" comment '当此单为组合赎回的子单时对应的母单',
   ref_fund_trans_order_no char(32) not null default "" comment '当交易类型为退款,当日赎回时,关联的原始基金交易业务单号;',
   transcore_trans_no   char(32) not null default "" comment '交易核心交易单流水号',
   is_adjusted          bit(1) not null comment '是否为补单',
   trigger_reason       int(11) not null comment '0 - 正常; 1 - 充值后触发',
   business_order_item_no char(32) not null default '' comment '业务订单项流水号',
   order_no             char(32) not null default "" comment '用户订单号',
   tracking_no          char(32) not null comment '防止幂等, 全局跟踪',
   product_id           bigint(20) not null comment '用户产品ID',
   mer_member_no        char(32) not null comment '商户号',
   contract_no          char(32) not null default "" comment '合同流水号, 申购后自动生成',
   fund_trans_type      int(11) not null comment '基金交易类型：1 - 认购; 2 - 申购; 3 - 正常赎回; 4 - 申购退款; 5 - 撤销; 6 - 当日赎回; 7 - 认购退款; 8 - 组合赎回; 9 - 无需确认的赎回',
   fund_trans_amount    decimal(26,6) not null default 0 comment '交易金额',
   fund_trans_shares    decimal(26,6) not null default 0 comment '交易份额',
   discount_amount      decimal(26,6) not null default 0 comment '优惠金额',
   currency_type        char(3) not null default "" comment '币种',
   ref_fund_code        char(6) not null default "" comment 'ta基金代码',
   ref_fund_sub_code    char(6) not null default "" comment '外部基金分组编号',
   ref_fund_business_code char(3) not null default "" comment 'ta基金业务代码',
   biz_channel          int(11) not null comment '渠道类型：IOS = 11; ANDROID = 12; H5 = 13; WECHAT = 14; WEB = 20;',
   status               int not null comment '业务单状态：4 - 待支付; 5 - 支付中; 6 - 支付成功; 7 - 支付失败; 8 -  基金交易处理中; 9 - 基金交易成功; 10 - 基金交易失败; 11 - 基金交易结果通知成功; 12 - 退款中; 13 - 退款成功; 14 - 退款失败; 15 - 限额风控挂起; 17 - 取消; 18 - 过期关闭； 19 - 部分退款成功； 20 - 提现中； 21 - 提现成功； 22 - 提现失败; 23 - 赎回提交成功; 24 - 等待短信验证;  25 - 风控挂起; 26 - 风控拒绝',
   fund_interact_type   int(11) not null comment '基金提交方式：1 - 实时; 2 - 文件',
   pay_method           varchar(768) not null default "" comment '支付方式，json表示',
   pay_method_desc      varchar(512) not null default "" comment '支付方式描述',
   frozen_code          char(32) not null default "" comment '库存冻结号',
   total_expiry_time    timestamp not null default '0000-00-00 00:00:00' comment '总有效期',
   cur_status_expiry_time timestamp not null default '0000-00-00 00:00:00' comment '当前状态过期时间',
   ref_app_sheet_serial_no char(24) not null default "" comment 'ta申请单号, 可关联请求，确认，结果',
   ext_info_order_no    char(32) not null default "" comment 'reserved',
   remark               varchar(255) not null default "" comment '备注',
   ext_field            varchar(255) not null default "" comment '业务扩展字段，保存json字串',
   trans_time           timestamp not null default '0000-00-00 00:00:00' comment '交易时间',
   create_time          timestamp not null default '0000-00-00 00:00:00' comment '创建时间',
   update_time          timestamp not null default CURRENT_TIMESTAMP comment '更新时间',
   finish_time          timestamp not null default '0000-00-00 00:00:00' comment '业务单整体完成时间',
   refund_time          timestamp not null default '0000-00-00 00:00:00' comment '退款时间，退款发生在已经下单支付但是TA还未处理的时候',
   cancel_time          timestamp not null default '0000-00-00 00:00:00' comment '业务单撤销时间，撤销发生在已经下单但是还未支付的时候',
   refund_amount_limit  decimal(26,6) not null default 0 comment '可退款金额',
   refund_amount        decimal(26,6) not null default 0 comment '已退款金额',
   refund_fee_amount    decimal(26,6) not null default 0 comment '已退款手续费',
   to_fee               decimal(26,6) not null default 0 comment '付款方手续费',
   from_fee             decimal(26,6) not null default 0 comment '收款方手续费',
   fee_method           int(11) not null default -1 comment 'reserved',
   operator_no          char(32) not null default "" comment '操作人号',
   primary key (id)
);

alter table t_ftc_fund_trans_order comment '基金业务的交易业务单';

/*==============================================================*/
/* Index: tracking_no_unique_idx                                */
/*==============================================================*/
create unique index tracking_no_unique_idx on t_ftc_fund_trans_order
(
   tracking_no
);

/*==============================================================*/
/* Index: fund_trans_order_idx                                  */
/*==============================================================*/
create index fund_trans_order_idx on t_ftc_fund_trans_order
(
   fund_trans_order_no(20)
);

/*==============================================================*/
/* Index: app_sheet_serial_no_idx                               */
/*==============================================================*/
create index app_sheet_serial_no_idx on t_ftc_fund_trans_order
(
   ref_app_sheet_serial_no
);

/*==============================================================*/
/* Index: time_prod_mem_idx                                     */
/*==============================================================*/
create index time_prod_mem_idx on t_ftc_fund_trans_order
(
   trans_time,
   product_id,
   member_no
);

/*==============================================================*/
/* Index: trans_acct_no_idx                                     */
/*==============================================================*/
create index trans_acct_no_idx on t_ftc_fund_trans_order
(
   trans_account_no
);

/*==============================================================*/
/* Table: t_ftc_fund_trans_order_status_info                    */
/*==============================================================*/
create table t_ftc_fund_trans_order_status_info
(
   id                   bigint(20) unsigned not null auto_increment comment '主键',
   order_id             bigint(20) not null comment '基金交易业务单主表id',
   status               int not null comment '基金业务单状态',
   update_time          timestamp not null comment '状态迁移时间',
   operator_no          char(32) not null default "" comment '操作人',
   remark               varchar(255) not null default "" comment '备注',
   primary key (id)
);

alter table t_ftc_fund_trans_order_status_info comment '记录基金交易业务单状态时间信息';

/*==============================================================*/
/* Index: order_status_idx                                      */
/*==============================================================*/
create index order_status_idx on t_ftc_fund_trans_order_status_info
(
   order_id,
   status
);

/*==============================================================*/
/* Table: t_ftc_ta_fund_checking_acct_order                     */
/*==============================================================*/
create table t_ftc_ta_fund_checking_acct_order
(
   id                   bigint(20) unsigned not null auto_increment comment '主键',
   available_vol        decimal(16,2) not null comment '持有人可用基金份数',
   total_vol_of_dist_in_ta decimal(16,2) not null comment '基金总份数（含冻结）',
   transaction_account_id char(17) not null comment 'ta投资人基金交易账号',
   distributor_code     char(9) not null comment 'ta销售人代码',
   branch_code          char(9) not null comment '网点号码, 同销售人代码',
   fund_code            char(6) not null comment '基金代码',
   ta_account_id        char(12) not null comment '投资人基金账号',
   transaction_cfm_date char(8) not null comment 'ta交易确认日期,格式为：YYYYMMDD
            ',
   share_class          char(1) not null comment '收费方式:0-前收费，1-后收费，表明基金是前收费或后收费基金',
   detail_flag          char(1) not null comment '明细标志, 0-非明细，1-明细，非明细指针对基金账户的对账，明细指针对基金账户具体过户日或TA确认流水号的对账
            ',
   undistribute_monetary_income decimal(16,2) comment '货币基金未付收益金额, 对货币基金，明细标志为0时必填',
   undistribute_monetary_income_flag char(1) comment '货币基金未付收益金额正负,0-正  1-负
            对货币基金，明细标志为0时必填',
   primary key (id)
);

alter table t_ftc_ta_fund_checking_acct_order comment 'TA基金账户对账单';

/*==============================================================*/
/* Table: t_ftc_ta_fund_clearing_order                          */
/*==============================================================*/
create table t_ftc_ta_fund_clearing_order
(
   id                   bigint(20) unsigned not null auto_increment comment '主键',
   total_frozen_vol     decimal(16,2) comment '基金冻结总份数',
   fund_vol_balance     decimal(16,2) comment '基金份数余额',
   achievement_pay      decimal(16,2) not null comment '业绩报酬',
   achievement_compen   decimal(16,2) not null comment '业绩补偿',
   frozen_balance       decimal(16,2) comment '冻结金额',
   transaction_account_id char(17) not null comment 'ta投资人基金交易账号',
   distributor_code     char(9) not null comment 'ta销售人代码',
   business_code        char(3) not null comment 'ta业务代码',
   currency_type        char(3) not null comment '结算币种,具体编码依GB/T 12406-2008
            ',
   fund_code            char(6) not null comment '基金代码',
   ta_account_id        char(12) not null comment '投资人基金账号',
   transaction_cfm_date char(8) not null comment 'ta交易确认日期,格式为：YYYYMMDD
            ',
   confirmed_vol        decimal(16,2) not null comment '基金账户交易确认份数',
   confirmed_amount     decimal(16,2) not null comment '每笔交易确认金额,含所有费用的总金额',
   download_date        char(8) not null comment '交易数据下传日期,指发送日期
            ',
   nav                  decimal(7,4) not null comment '基金单位净值',
   share_class          char(1) not null comment '收费方式:0-前收费，1-后收费，表明基金是前收费或后收费基金',
   ta_serial_no         char(20) default '“”' comment 'TA确认交易流水号,TA对每笔确认的唯一标识，同一日不能重复，与交易确认日期TransactionCfmDate一起组成TA中一笔确认的唯一键
            ',
   primary key (id)
);

alter table t_ftc_ta_fund_clearing_order comment 'TA基金清盘记录单';

/*==============================================================*/
/* Index: ta_serial_no_idx                                      */
/*==============================================================*/
create unique index ta_serial_no_idx on t_ftc_ta_fund_clearing_order
(
   ta_serial_no
);

/*==============================================================*/
/* Table: t_ftc_ta_fund_create_acct_cfm_order                   */
/*==============================================================*/
create table t_ftc_ta_fund_create_acct_cfm_order
(
   id                   bigint(20) unsigned not null auto_increment,
   app_sheet_serial_no  char(24) not null comment '申请单编号',
   transaction_cfm_date char(8) not null comment 'ta交易确认日期,格式为：YYYYMMDD
            ',
   return_code          char(4) not null comment 'ta交易处理返回代码',
   transaction_account_id char(17) not null comment '投资人基金交易账号, 投资人在销售机构内开设的用于交易的账号
            ',
   distributor_code     char(9) not null comment '销售人代码',
   ta_account_id        char(12) not null comment '投资人基金账号',
   transaction_date     char(8) not null comment 'ta交易发生日期',
   business_code        char(3) not null comment 'ta业务代码',
   branch_code          char(9) not null comment '网点号码, 同销售人代码',
   transaction_time     char(6) not null comment '交易发生时间',
   ta_serial_no         char(20) not null comment 'TA确认交易流水号,TA对每笔确认的唯一标识，同一日不能重复，与交易确认日期TransactionCfmDate一起组成TA中一笔确认的唯一键
            ',
   primary key (id)
);

alter table t_ftc_ta_fund_create_acct_cfm_order comment 'TA基金开户确认单';

/*==============================================================*/
/* Table: t_ftc_ta_fund_create_acct_order                       */
/*==============================================================*/
create table t_ftc_ta_fund_create_acct_order
(
   id                   bigint(20) unsigned not null auto_increment,
   app_sheet_serial_no  char(24) not null comment '申请单编号，同一销售机构不能重复',
   certificate_type     char(1) not null comment '个人证件类型
            0-身份证，1-护照
            2-军官证，3-士兵证
            4-港澳居民来往内地通行证，5-户口本
            6-外国护照，7-其它
            8-文职证，9-警官证
            A-台胞证

            机构证件类型
            0-组织机构代码证
            1-营业执照，2-行政机关
            3-社会团体，4-军队
            5-武警
            6-下属机构（具有主管单位批文号）
            7-基金会，8-其它',
   certificate_no       char(30) not null comment '投资人证件号码',
   investor_name        varchar(120) not null comment 'ta投资人户名',
   transaction_date     char(8) not null comment 'ta交易发生日期',
   individual_or_institution char(1) not null comment '个人/机构标志',
   transaction_account_id char(17) not null comment 'ta投资人基金交易账号',
   distributor_code     char(9) not null comment 'ta销售人代码',
   business_code        char(3) not null comment 'ta业务代码',
   branch_code          char(9) not null comment '网点号码, 同销售人代码',
   transaction_time     char(6) not null comment '交易发生时间',
   primary key (id)
);

alter table t_ftc_ta_fund_create_acct_order comment 'TA基金开户单表';

/*==============================================================*/
/* Index: app_sheet_serial_no_idx                               */
/*==============================================================*/
create index app_sheet_serial_no_idx on t_ftc_ta_fund_create_acct_order
(
   app_sheet_serial_no
);

/*==============================================================*/
/* Index: unique_fund_trans_acc_no                              */
/*==============================================================*/
create unique index unique_fund_trans_acc_no on t_ftc_ta_fund_create_acct_order
(
   transaction_account_id
);

/*==============================================================*/
/* Table: t_ftc_ta_fund_dividend_order                          */
/*==============================================================*/
create table t_ftc_ta_fund_dividend_order
(
   id                   bigint(20) unsigned not null auto_increment comment '主键',
   basisfor_calculating_dividend decimal(16,2) not null comment '红利/红利再投资基数,登记日基金持有人的基金份数',
   divident_date        char(8) not null comment '分红日/发放日',
   vol_of_dividend_for_reinvestment decimal(16,2) not null comment '基金账户红利再投资基金份数,投资人实得红股，含被续冻的红股',
   dividend_amount      decimal(16,2) not null comment '基金账户红利资金,红利总金额,含冻结红利及再投资的红利',
   xr_rate              char(8) not null comment '除权日',
   registration_date    char(8) not null comment '权益登记日期, 格式为：YYYYMMDD',
   dividend_per_unit    decimal(16,2) not null comment '单位基金分红金额（含税）,举例：每千份分两元，则此处填2。',
   def_dividend_method  char(1) not null comment '默认分红方式: 0-红利转投，1-现金分红，投资人本次分红的方式',
   original_app_sheet_no char(24) comment '原申请单编号,对质押基金分红为Y项, 表示原质押业务的申请单编号',
   transaction_account_id char(17) not null comment 'ta投资人基金交易账号',
   distributor_code     char(9) not null comment 'ta销售人代码',
   business_code        char(3) not null comment 'ta业务代码',
   branch_code          char(9) not null comment '网点号码, 同销售人代码',
   achievement_compen   decimal(16,2) not null comment '业绩补偿',
   currency_type        char(3) not null comment '结算币种,具体编码依GB/T 12406-2008
            ',
   fund_code            char(6) not null comment '基金代码',
   ta_account_id        char(12) not null comment '投资人基金账号',
   transaction_cfm_date char(8) not null comment 'ta交易确认日期,格式为：YYYYMMDD
            ',
   return_code          char(4) not null comment 'ta交易处理返回代码',
   ta_serial_no         char(20) not null comment 'TA确认交易流水号,TA对每笔确认的唯一标识，同一日不能重复，与交易确认日期TransactionCfmDate一起组成TA中一笔确认的唯一键
            ',
   confirmed_amount     decimal(16,2) not null comment '每笔交易确认金额,含所有费用的总金额',
   download_date        char(8) not null comment '交易数据下传日期,指发送日期
            ',
   charge               decimal(10,2) not null comment '手续费,投资人应付总手续费',
   agency_fee           decimal(10,2) not null comment '代理费, 手续费中划归销售人的部分
            ',
   transfer_fee         decimal(10,2) not null comment '过户费,',
   share_class          char(1) not null comment '收费方式:0-前收费，1-后收费，表明基金是前收费或后收费基金',
   draw_bonus_unit      decimal(10) not null comment '分红单位, 举例：每千份分多少，则分红单位就为一千',
   dividend_type        char(1) not null comment '分红类型: 0-普通分红，1-质押基金分红，2-货币基金收益结转，3-保本基金赔付，4-专户到期处理',
   achievement_pay      decimal(16,2) not null comment '业绩报酬',
   primary key (id)
);

alter table t_ftc_ta_fund_dividend_order comment 'TA基金分红记录单';

/*==============================================================*/
/* Table: t_ftc_ta_fund_purchasing_cfm_order                    */
/*==============================================================*/
create table t_ftc_ta_fund_purchasing_cfm_order
(
   id                   bigint(20) unsigned not null auto_increment comment '主键',
   app_sheet_serial_no  char(24) not null comment '申请单编号，同一销售机构不能重复',
   transaction_date     char(8) not null comment 'ta交易发生日期',
   transaction_account_id char(17) not null comment 'ta投资人基金交易账号',
   distributor_code     char(9) not null comment 'ta销售人代码',
   business_code        char(3) not null comment 'ta业务代码',
   branch_code          char(9) not null comment '网点号码, 同销售人代码',
   transaction_time     char(6) not null comment '交易发生时间',
   currency_type        char(3) not null comment '结算币种,具体编码依GB/T 12406-2008
            ',
   fund_code            char(6) not null comment '基金代码',
   combine_num          char(6) comment '组合编号',
   ta_account_id        char(12) not null comment '投资人基金账号',
   application_amount   decimal(16,2) not null comment '申请金额',
   transaction_cfm_date char(8) not null comment 'ta交易确认日期,格式为：YYYYMMDD
            ',
   return_code          char(4) not null comment 'ta交易处理返回代码',
   ta_serial_no         char(20) not null comment 'TA确认交易流水号,TA对每笔确认的唯一标识，同一日不能重复，与交易确认日期TransactionCfmDate一起组成TA中一笔确认的唯一键
            ',
   confirmed_vol        decimal(16,2) not null comment '基金账户交易确认份数',
   confirmed_amount     decimal(16,2) not null comment '每笔交易确认金额,含所有费用的总金额',
   download_date        char(8) not null comment '交易数据下传日期,指发送日期
            ',
   charge               decimal(10,2) not null comment '手续费,投资人应付总手续费',
   agency_fee           decimal(10,2) not null comment '代理费, 手续费中划归销售人的部分
            ',
   nav                  decimal(7,4) not null comment '基金单位净值',
   transfer_fee         decimal(10,2) not null comment '过户费,',
   share_class          char(1) not null comment '收费方式:0-前收费，1-后收费，表明基金是前收费或后收费基金',
   primary key (id)
);

alter table t_ftc_ta_fund_purchasing_cfm_order comment 'TA基金申购确认单';

/*==============================================================*/
/* Table: t_ftc_ta_fund_purchasing_order                        */
/*==============================================================*/
create table t_ftc_ta_fund_purchasing_order
(
   id                   bigint(20) unsigned not null auto_increment comment '主键',
   app_sheet_serial_no  char(24) not null comment '申请单编号，同一销售机构不能重复',
   transaction_date     char(8) not null comment 'ta交易发生日期',
   transaction_account_id char(17) not null comment 'ta投资人基金交易账号',
   distributor_code     char(9) not null comment 'ta销售人代码',
   business_code        char(3) not null comment 'ta业务代码',
   branch_code          char(9) not null comment '网点号码, 同销售人代码',
   transaction_time     char(6) not null comment '交易发生时间',
   currency_type        char(3) not null comment '结算币种,具体编码依GB/T 12406-2008
            ',
   fund_code            char(6) not null comment '基金代码',
   ta_account_id        char(12) not null comment '投资人基金账号',
   application_amount   decimal(16,2) not null comment '申请金额',
   share_class          char(1) not null comment '收费方式:0-前收费，1-后收费，表明基金是前收费或后收费基金',
   charge_type          char(1) not null comment '收费类型:0-折扣率方式 1-指定费率，2-指定费用',
   primary key (id)
);

alter table t_ftc_ta_fund_purchasing_order comment 'TA基金申购单';

/*==============================================================*/
/* Index: app_sheet_serial_no_idx                               */
/*==============================================================*/
create unique index app_sheet_serial_no_idx on t_ftc_ta_fund_purchasing_order
(
   app_sheet_serial_no
);

/*==============================================================*/
/* Table: t_ftc_ta_fund_redeeming_cfm_order                     */
/*==============================================================*/
create table t_ftc_ta_fund_redeeming_cfm_order
(
   id                   bigint(20) unsigned not null auto_increment comment '主键',
   app_sheet_serial_no  char(24) not null comment '申请单编号，同一销售机构不能重复',
   business_finish_flag char(1) not null comment '业务过程完全结束标识, 0-中间过程,1-业务过程结束,比如因巨额赎回导致顺延，存在中间过程。',
   large_redemption_flag char(1) not null comment '巨额赎回处理标志, 0-取消，1-顺延, 对申请的回执字段。',
   transaction_date     char(8) not null comment 'ta交易发生日期',
   transaction_account_id char(17) not null comment 'ta投资人基金交易账号',
   distributor_code     char(9) not null comment 'ta销售人代码',
   business_code        char(3) not null comment 'ta业务代码',
   branch_code          char(9) not null comment '网点号码, 同销售人代码',
   transaction_time     char(6) not null comment '交易发生时间',
   currency_type        char(3) not null comment '结算币种,具体编码依GB/T 12406-2008
            ',
   fund_code            char(6) not null comment '基金代码',
   ta_account_id        char(12) not null comment '投资人基金账号',
   application_vol      decimal(16,2) not null comment '申请金额',
   transaction_cfm_date char(8) not null comment 'ta交易确认日期,格式为：YYYYMMDD
            ',
   return_code          char(4) not null comment 'ta交易处理返回代码',
   ta_serial_no         char(20) not null comment 'TA确认交易流水号,TA对每笔确认的唯一标识，同一日不能重复，与交易确认日期TransactionCfmDate一起组成TA中一笔确认的唯一键
            ',
   confirmed_vol        decimal(16,2) not null comment '基金账户交易确认份数',
   confirmed_amount     decimal(16,2) not null comment '每笔交易确认金额,含所有费用的总金额',
   download_date        char(8) not null comment '交易数据下传日期,指发送日期
            ',
   charge               decimal(10,2) not null comment '手续费,投资人应付总手续费',
   agency_fee           decimal(10,2) not null comment '代理费, 手续费中划归销售人的部分
            ',
   nav                  decimal(7,4) not null comment '基金单位净值',
   transfer_fee         decimal(10,2) not null comment '过户费,',
   other_fee_1          decimal(10,2) not null comment '其他费用1, 赎回手续费中划归基金资产的部分',
   share_class          char(1) not null comment '收费方式:0-前收费，1-后收费，表明基金是前收费或后收费基金',
   breach_fee           decimal(16,2) not null comment '违约金',
   breach_fee_back_to_fund decimal(16,2) not null comment '违约金归基金资产金额',
   punish_fee           decimal(16,2) not null comment '惩罚性费用',
   achievement_compen   decimal(16,2) not null comment '业绩补偿',
   achievement_pay      decimal(16,2) not null comment '业绩报酬',
   primary key (id)
);

alter table t_ftc_ta_fund_redeeming_cfm_order comment 'TA基金赎回确认单';

/*==============================================================*/
/* Table: t_ftc_ta_fund_redeeming_order                         */
/*==============================================================*/
create table t_ftc_ta_fund_redeeming_order
(
   id                   bigint(20) unsigned not null auto_increment comment '主键',
   app_sheet_serial_no  char(24) not null comment '申请单编号，同一销售机构不能重复',
   large_redemption_flag char(1) not null comment '巨额赎回处理标志, 0-取消，1-顺延',
   application_vol      decimal(16,2) not null comment '申请基金份数',
   transaction_date     char(8) not null comment 'ta交易发生日期',
   transaction_account_id char(17) not null comment 'ta投资人基金交易账号',
   distributor_code     char(9) not null comment 'ta销售人代码',
   business_code        char(3) not null comment 'ta业务代码',
   branch_code          char(9) not null comment '网点号码, 同销售人代码',
   transaction_time     char(6) not null comment '交易发生时间',
   fund_code            char(6) not null comment '基金代码',
   ta_account_id        char(12) not null comment '投资人基金账号',
   share_class          char(1) not null comment '收费方式:0-前收费，1-后收费，表明基金是前收费或后收费基金',
   charge_type          char(1) not null comment '收费类型:0-折扣率方式 1-指定费率，2-指定费用',
   primary key (id)
);

alter table t_ftc_ta_fund_redeeming_order comment 'TA基金赎回申请单';

/*==============================================================*/
/* Index: app_sheet_serial_no_idx                               */
/*==============================================================*/
create unique index app_sheet_serial_no_idx on t_ftc_ta_fund_redeeming_order
(
   app_sheet_serial_no
);

/*==============================================================*/
/* Table: t_ftc_ta_fund_subscribing_cfm_order                   */
/*==============================================================*/
create table t_ftc_ta_fund_subscribing_cfm_order
(
   id                   bigint(20) unsigned not null auto_increment comment '主键',
   app_sheet_serial_no  char(24) not null comment '申请单编号，同一销售机构不能重复',
   transaction_date     char(8) not null comment 'ta交易发生日期',
   transaction_account_id char(17) not null comment 'ta投资人基金交易账号',
   distributor_code     char(9) not null comment 'ta销售人代码',
   business_code        char(3) not null comment 'ta业务代码',
   branch_code          char(9) not null comment '网点号码, 同销售人代码',
   transaction_time     char(6) not null comment '交易发生时间',
   fund_code            char(6) not null comment '基金代码',
   combine_num          char(6) comment '组合编号',
   ta_account_id        char(12) not null comment '投资人基金账号',
   application_amount   decimal(16,2) not null comment '申请金额',
   transaction_cfm_date char(8) not null comment 'ta交易确认日期,格式为：YYYYMMDD
            ',
   return_code          char(4) not null comment 'ta交易处理返回代码',
   ta_serial_no         char(20) not null comment 'TA确认交易流水号,TA对每笔确认的唯一标识，同一日不能重复，与交易确认日期TransactionCfmDate一起组成TA中一笔确认的唯一键
            ',
   confirmed_amount     decimal(16,2) not null comment '每笔交易确认金额,含所有费用的总金额',
   download_date        char(8) not null comment '交易数据下传日期,指发送日期
            ',
   nav                  decimal(7,4) not null comment '基金单位净值',
   share_class          char(1) not null comment '收费方式:0-前收费，1-后收费，表明基金是前收费或后收费基金',
   primary key (id)
);

alter table t_ftc_ta_fund_subscribing_cfm_order comment 'TA基金认购确认单';

/*==============================================================*/
/* Table: t_ftc_ta_fund_subscribing_order                       */
/*==============================================================*/
create table t_ftc_ta_fund_subscribing_order
(
   id                   bigint(20) unsigned not null auto_increment comment '主键',
   app_sheet_serial_no  char(24) not null comment '申请单编号，同一销售机构不能重复',
   transaction_date     char(8) not null comment 'ta交易发生日期',
   transaction_account_id char(17) not null comment 'ta投资人基金交易账号',
   distributor_code     char(9) not null comment 'ta销售人代码',
   business_code        char(3) not null comment 'ta业务代码',
   branch_code          char(9) not null comment '网点号码, 同销售人代码',
   transaction_time     char(6) not null comment '交易发生时间',
   fund_code            char(6) not null comment '基金代码',
   ta_account_id        char(12) not null comment '投资人基金账号',
   application_amount   decimal(16,2) not null comment '申请金额',
   share_class          char(1) not null comment '收费方式:0-前收费，1-后收费，表明基金是前收费或后收费基金',
   currency_type        char(3) not null comment '结算币种,具体编码依GB/T 12406-2008
            ',
   primary key (id)
);

alter table t_ftc_ta_fund_subscribing_order comment 'TA基金认购单';

/*==============================================================*/
/* Index: app_sheet_serial_no_idx                               */
/*==============================================================*/
create unique index app_sheet_serial_no_idx on t_ftc_ta_fund_subscribing_order
(
   app_sheet_serial_no
);

/*==============================================================*/
/* Table: t_ftc_ta_fund_subscribing_result_order                */
/*==============================================================*/
create table t_ftc_ta_fund_subscribing_result_order
(
   id                   bigint(20) unsigned not null auto_increment comment '主键',
   app_sheet_serial_no  char(24) not null comment '申请单编号，同一销售机构不能重复',
   transaction_date     char(8) not null comment 'ta交易发生日期',
   transaction_account_id char(17) not null comment 'ta投资人基金交易账号',
   distributor_code     char(9) not null comment 'ta销售人代码',
   business_code        char(3) not null comment 'ta业务代码',
   branch_code          char(9) not null comment '网点号码, 同销售人代码',
   transaction_time     char(6) not null comment '交易发生时间',
   currency_type        char(3) not null comment '结算币种,具体编码依GB/T 12406-2008
            ',
   fund_code            char(6) not null comment '基金代码',
   combine_num          char(6) comment '组合编号',
   ta_account_id        char(12) not null comment '投资人基金账号',
   application_amount   decimal(16,2) not null comment '申请金额',
   transaction_cfm_date char(8) not null comment 'ta交易确认日期,格式为：YYYYMMDD
            ',
   return_code          char(4) not null comment 'ta交易处理返回代码',
   ta_serial_no         char(20) not null comment 'TA确认交易流水号,TA对每笔确认的唯一标识，同一日不能重复，与交易确认日期TransactionCfmDate一起组成TA中一笔确认的唯一键
            ',
   confirmed_vol        decimal(16,2) not null comment '基金账户交易确认份数',
   confirmed_amount     decimal(16,2) not null comment '每笔交易确认金额,含所有费用的总金额',
   download_date        char(8) not null comment '交易数据下传日期,指发送日期
            ',
   charge               decimal(10,2) not null comment '手续费,投资人应付总手续费',
   agency_fee           decimal(10,2) not null comment '代理费, 手续费中划归销售人的部分
            ',
   nav                  decimal(7,4) not null comment '基金单位净值',
   transfer_fee         decimal(10,2) not null comment '过户费,',
   share_class          char(1) not null comment '收费方式:0-前收费，1-后收费，表明基金是前收费或后收费基金',
   interest             decimal(10,2) not null comment '基金账户利息金额,认购一次确认的金额在整个计息周期中产生的利息',
   raise_interest       decimal(16,2) not null comment '认购期间利息,因认购二次确认失败而退还给投资人的利息',
   interest_tax         decimal(16,2) not null comment '利息税',
   volume_by_interest   decimal(16,2) not null comment '利息产生的基金份数',
   primary key (id)
);

alter table t_ftc_ta_fund_subscribing_result_order comment 'TA基金认购结果单';

-- ----------------------------
--  Table structure for `t_amc_fund_bonus_detail_alteration`
-- ----------------------------
DROP TABLE IF EXISTS `t_amc_fund_bonus_detail_alteration`;
CREATE TABLE `t_amc_fund_bonus_detail_alteration` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `bonus_alteration_uuid` char(32)  NOT NULL,
  `fund_detail_id` bigint(20) NOT NULL COMMENT '基金资产明细id',
  `fund_detail_uuid` char(32)  NOT NULL COMMENT '基金资产明细表的UUID',
  `fund_total_id` bigint(20) NOT NULL COMMENT '基金资产汇总表的ID',
  `fund_total_uuid` char(32)  NOT NULL COMMENT '基金资产汇总表的uuid',
  `ftc_order_no` char(32)  NOT NULL COMMENT 'FTC红利单号',
  `fund_bonus_amount` decimal(26,6) NOT NULL COMMENT '该只基金的红利金额',
  `bonus_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '红利发放日期',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`),
  KEY `idx_t_amc_fund_bon_dtl_alt_collection` (`fund_detail_uuid`,`create_time`) USING BTREE
) ENGINE=InnoDB  COMMENT='用户基金资产明细，存放每个人买了多少的某只基金';

-- ----------------------------
--  Table structure for `t_amc_fund_detail`
-- ----------------------------
DROP TABLE IF EXISTS `t_amc_fund_detail`;
CREATE TABLE `t_amc_fund_detail` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `fund_detail_uuid` char(32)  NOT NULL COMMENT '基金资产明细表的UUID',
  `fund_total_id` bigint(20) NOT NULL COMMENT '基金资产汇总表的ID',
  `fund_total_uuid` char(32)  NOT NULL COMMENT '基金资产汇总表的uuid',
  `product_id` bigint(20) NOT NULL COMMENT '用户产品ID',
  `fund_total_share` decimal(26,6) NOT NULL COMMENT '该只基金的总份额',
  `fund_total_amount` decimal(26,6) NOT NULL COMMENT '该只基金的总金额',
  `yesterday_profit` decimal(26,6) NOT NULL COMMENT '用户昨日收益',
  `total_profit` decimal(26,6) NOT NULL COMMENT '用户累计收益',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  `frozen_amount` decimal(26,6) NOT NULL COMMENT '冻结金额',
  `frozen_share` decimal(26,6) NOT NULL COMMENT '冻结份额',
  `confirmed_add_amount` decimal(26,6) NOT NULL COMMENT '确认增加金额',
  `confirmed_add_share` decimal(26,6) NOT NULL COMMENT '确认增加份额',
  `unconfirmed_add_amount` decimal(26,6) NOT NULL COMMENT '未确认增加金额',
  `unconfirmed_add_share` decimal(26,6) NOT NULL COMMENT '未确认增加份额',
  `confirmed_sub_amount` decimal(26,6) NOT NULL COMMENT '确认减少金额',
  `confirmed_sub_share` decimal(26,6) NOT NULL COMMENT '确认减少份额',
  `unconfirmed_sub_amount` decimal(26,6) NOT NULL COMMENT '未确认减少金额',
  `unconfirmed_sub_share` decimal(26,6) NOT NULL COMMENT '未确认减少份额',
  `bonus_total_amount` decimal(26,6) NOT NULL COMMENT '红利总额',
  `profit_total_amount` decimal(26,6) NOT NULL COMMENT '加息券总额',
  `expect_bonus_amount` decimal(26,6) NOT NULL COMMENT '预期红利',
  `settlement_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '清盘时间',
  `expect_profit_amount` decimal(26,6) NOT NULL COMMENT '预期加息券',
  `has_settlement` int(4) NOT NULL COMMENT '是否清盘，1-否，2-是',
  `settlement_capital` decimal(26,6) NOT NULL COMMENT '兑付本金',
  PRIMARY KEY (`id`),
  KEY `idx_t_amc_fund_dtl_fund_total_product_collection` (`fund_total_uuid`,`product_id`) USING BTREE
) ENGINE=InnoDB  COMMENT='用户基金资产明细，存放每个人买了多少的某只基金';

-- ----------------------------
--  Table structure for `t_amc_fund_detail_alteration`
-- ----------------------------
DROP TABLE IF EXISTS `t_amc_fund_detail_alteration`;
CREATE TABLE `t_amc_fund_detail_alteration` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `detail_alteration_uuid` char(32)  NOT NULL COMMENT '基金资产明细变动UUID',
  `fund_detail_id` bigint(20) NOT NULL COMMENT '基金资产明细ID',
  `fund_detail_uuid` char(32)  NOT NULL COMMENT '基金资产明细表的UUID',
  `fund_total_id` bigint(20) NOT NULL COMMENT '基金资产汇总表的ID',
  `fund_total_uuid` char(32)  NOT NULL COMMENT '基金资产汇总表的uuid',
  `fund_share` decimal(26,6) NOT NULL COMMENT '该只基金的总份额',
  `fund_amount` decimal(26,6) NOT NULL COMMENT '该只基金的总金额',
  `alteration_status` int(4) NOT NULL COMMENT '基金的变动状态，0-TA未确认，1-TA已确认，2-TA取消',
  `alteration_type` int(4) NOT NULL COMMENT '基金变动类型，0-增加，1-减少',
  `ftc_order_no` char(32)  NOT NULL COMMENT '金融交易系统单号',
  `ftc_order_create_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT 'FTC单号创建时间',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`),
  KEY `idx_t_amc_fund_dtl_alt` (`ftc_order_no`) USING BTREE
) ENGINE=InnoDB  COMMENT='用户基金资产明细变动，存放每个人申购赎回变动记录';

-- ----------------------------
--  Table structure for `t_amc_fund_profit_alteration`
-- ----------------------------
DROP TABLE IF EXISTS `t_amc_fund_profit_alteration`;
CREATE TABLE `t_amc_fund_profit_alteration` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `profit_alteration_uuid` char(32)  NOT NULL COMMENT '基金资产明细变动UUID',
  `fund_detail_id` bigint(20) NOT NULL COMMENT '基金资产明细ID',
  `fund_detail_uuid` char(32)  NOT NULL COMMENT '基金资产明细表的UUID',
  `fund_total_id` bigint(20) NOT NULL COMMENT '基金资产汇总表的ID',
  `fund_total_uuid` char(32)  NOT NULL COMMENT '基金资产汇总表的uuid',
  `profit_amount` decimal(26,6) NOT NULL COMMENT '该只基金加息的金额',
  `ftc_order_no` char(32)  NOT NULL COMMENT '金融交易系统单号',
  `profit_add_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '加息时间',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  COMMENT='用户基金加息明细变动表，存放每只基金加息变动明细';

-- ----------------------------
--  Table structure for `t_amc_fund_total`
-- ----------------------------
DROP TABLE IF EXISTS `t_amc_fund_total`;
CREATE TABLE `t_amc_fund_total` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `fund_total_uuid` char(32)  NOT NULL COMMENT '基金资产汇总UUID',
  `member_asset_id` bigint(20) NOT NULL,
  `member_asset_uuid` char(32)  NOT NULL,
  `fund_acc_id` bigint(20) NOT NULL COMMENT '用户资产账户id',
  `fund_acc_uuid` char(32)  NOT NULL COMMENT '用户资产账户uuid',
  `total_asset` decimal(26,6) NOT NULL COMMENT '用户总资产',
  `yesterday_profit` decimal(26,6) NOT NULL COMMENT '用户昨日收益',
  `total_profit` decimal(26,6) NOT NULL COMMENT '用户累计收益',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  `frozen_amount` decimal(26,6) NOT NULL COMMENT '冻结金额',
  `frozen_share` decimal(26,6) NOT NULL COMMENT '冻结份额',
  `confirmed_add_amount` decimal(26,6) NOT NULL COMMENT '确认增加金额',
  `confirmed_add_share` decimal(26,6) NOT NULL COMMENT '确认增加份额',
  `unconfirmed_add_amount` decimal(26,6) NOT NULL COMMENT '未确认增加金额',
  `unconfirmed_add_share` decimal(26,6) NOT NULL COMMENT '未确认增加份额',
  `confirmed_sub_amount` decimal(26,6) NOT NULL COMMENT '确认减少金额',
  `confirmed_sub_share` decimal(26,6) NOT NULL COMMENT '确认减少份额',
  `unconfirmed_sub_amount` decimal(26,6) NOT NULL COMMENT '未确认减少金额',
  `unconfirmed_sub_share` decimal(26,6) NOT NULL COMMENT '未确认减少份额',
  PRIMARY KEY (`id`),
  KEY `idx_t_amc_fund_total_acc` (`fund_acc_uuid`) USING BTREE
) ENGINE=InnoDB  COMMENT='用户基金资产汇总表，汇总用户所有TA平台的所有资金资产';

-- ----------------------------
--  Table structure for `t_amc_mem_asset`
-- ----------------------------
DROP TABLE IF EXISTS `t_amc_mem_asset`;
CREATE TABLE `t_amc_mem_asset` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `member_asset_uuid` char(32)  NOT NULL COMMENT '用户资产uuid',
  `member_no` char(32)  NOT NULL COMMENT '会员号',
  `channel_no` int(4) NOT NULL COMMENT '交易渠道号',
  `total_asset` decimal(26,6) NOT NULL COMMENT '用户总资产',
  `yesterday_profit` decimal(26,6) NOT NULL COMMENT '用户昨日收益',
  `total_profit` decimal(26,6) NOT NULL COMMENT '用户累计收益',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  `frozen_amount` decimal(26,6) NOT NULL COMMENT '冻结金额',
  `frozen_share` decimal(26,6) NOT NULL COMMENT '冻结份额',
  `confirmed_add_amount` decimal(26,6) NOT NULL COMMENT '确认增加金额',
  `confirmed_add_share` decimal(26,6) NOT NULL COMMENT '确认增加份额',
  `unconfirmed_add_amount` decimal(26,6) NOT NULL COMMENT '未确认增加金额',
  `unconfirmed_add_share` decimal(26,6) NOT NULL COMMENT '未确认增加份额',
  `confirmed_sub_amount` decimal(26,6) NOT NULL COMMENT '确认扣减金额',
  `confirmed_sub_share` decimal(26,6) NOT NULL COMMENT '确认扣减份额',
  `unconfirmed_sub_amount` decimal(26,6) NOT NULL COMMENT '未确认扣减金额',
  `unconfirmed_sub_share` decimal(26,6) NOT NULL COMMENT '未确认扣减份额',
  PRIMARY KEY (`id`),
  KEY `idx_t_amc_mem_asset_collection` (`member_no`,`channel_no`) USING BTREE
) ENGINE=InnoDB  COMMENT='用户资产汇总信息，同一个渠道的基金资产+同一个渠道的非基金资产';

-- ----------------------------
--  Table structure for `t_amc_mem_fund_acc`
-- ----------------------------
DROP TABLE IF EXISTS `t_amc_mem_fund_acc`;
CREATE TABLE `t_amc_mem_fund_acc` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `fund_acc_uuid` char(32)  NOT NULL COMMENT '用户交易账户UUID',
  `ftc_create_acc_no` char(32)  NOT NULL COMMENT 'ftc的开户业务单',
  `member_no` char(32)  NOT NULL COMMENT '会员号',
  `channel_no` int(4) NOT NULL COMMENT '交易渠道号',
  `transaction_account` char(32)  NOT NULL COMMENT '基金交易账号',
  `ta_company_no` bigint(20) NOT NULL COMMENT '基金公司号，根据购买的产品确定（我们平台对TA的编号）',
  `account_status` int(4) NOT NULL COMMENT '账户状态，1-创建中，2-开户成功，3-开户失败',
  `ta_account` char(32)  NOT NULL COMMENT 'TA账户号',
  `ftc_create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT 'FTC创建时间，跑批处理用',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  `account_risk_status` int(4) NOT NULL DEFAULT '1' COMMENT '风控字段，1-正常，2-禁止交易',
  PRIMARY KEY (`id`),
  KEY `idx_t_amc_fund_acc_acc_uuid` (`fund_acc_uuid`) USING BTREE,
  KEY `idx_t_amc_fund_acc_mem_no` (`member_no`) USING BTREE,
  KEY `idx_t_amc_fund_acc_ftc_order_no` (`ftc_create_acc_no`) USING BTREE,
  KEY `idx_t_amc_fund_acc_trans_acc_ta_acc_collection` (`transaction_account`,`ta_account`) USING BTREE
) ENGINE=InnoDB  COMMENT='用户交易账户表，记录用户的某个渠道的基金交易账号与TA账户信息';
