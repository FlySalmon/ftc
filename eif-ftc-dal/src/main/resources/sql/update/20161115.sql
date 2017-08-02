use eif_ftc;
ALTER TABLE `eif_ftc`.`t_amc_fund_detail` ADD COLUMN `groupon_type` tinyint(4) NOT NULL DEFAULT 0 COMMENT '是否团购产品；0-非团购，1-团购' AFTER `groupon_bonus`;