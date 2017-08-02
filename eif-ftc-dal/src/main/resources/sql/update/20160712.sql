USE `eif_ftc`;

/*==============================================================*/
/* Table: t_ftc_outer_order_rel                                 */
/*==============================================================*/
create table t_ftc_outer_order_rel
(
   id                   bigint(20) not null auto_increment comment '主键',
   outer_sys_no         char(5) not null comment '外部业务系统号',
   outer_order_no       char(32) not null default '0000-00-00 00:00:00' comment '外部订单号',
   biz_sys_no           char(5) not null comment '内部业务系统号',
   biz_order_no         char(32) not null default '0000-00-00 00:00:00' comment '业务单号',
   PRIMARY KEY (`id`),
   KEY `outer_order_sys_idx` (`outer_sys_no`,`outer_order_no`) USING BTREE
   
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='外部订单关联表';
