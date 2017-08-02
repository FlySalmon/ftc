
/*==============================================================*/
/* Table: t_ftc_fund_trans_order_status                         */
/*==============================================================*/
CREATE TABLE t_ftc_fund_trans_order_live_status
(
   id                   BIGINT(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键',
   fund_trans_order_no  CHAR(32) NOT NULL COMMENT '基金交易业务单号',
   status               INT NOT NULL COMMENT '业务单状态：4 - 待支付; 5 - 支付中; 6 - 支付成功; 7 - 支付失败; 8 -  基金交易处理中; 9 - 基金交易成功; 10 - 基金交易失败; 11 - 基金交易结果通知成功; 12 - 退款中; 13 - 退款成功; 14 - 退款失败; 15 - 限额风控挂起; 17 - 取消; 18 - 过期关闭; 19 - 部分退款成功; 20 - 提现中; 21 - 提现成功; 22 - 提现失败; 23 - 赎回提交成功; 24 - 等待短信验证; 25 - 风控挂起; 26 - 风控拒绝; 60 - TA处理成功; 61 - TA处理失败; 62 - 更新交易核心申购单状态成功; 63 - 更新交易核心申购单状态失败;64 - 更新交易核心认购单状态成功; 65 - 更新交易核心认购单状态失败;  66 - 签合同成功; 67 - 签合同失败; 68 - 更新赎回单状态成功; 69 - 更新赎回单状态失败; 70 - 更新资产成功; 71 - 更新资产失败; 72 - 创建提现单成功; 73 - 创建提现单失败;',
   create_time          TIMESTAMP NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '创建时间',
   update_time          TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
   operator_no          CHAR(32) NOT NULL DEFAULT "" COMMENT '操作人号',
   remark               VARCHAR(255) NOT NULL DEFAULT "" COMMENT '备注',
   PRIMARY KEY (`id`)
) ENGINE=INNODB COMMENT='记录基金交易单当前明细状态';

/*==============================================================*/
/* Index: fund_trans_order_no_idx                               */
/*==============================================================*/
CREATE UNIQUE INDEX fund_trans_order_no_idx ON t_ftc_fund_trans_order_live_status
(
   fund_trans_order_no
);
