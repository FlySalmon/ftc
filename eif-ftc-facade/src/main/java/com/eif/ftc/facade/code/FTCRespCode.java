package com.eif.ftc.facade.code;

import com.eif.framework.common.utils.code.RspCode;

public enum FTCRespCode implements RspCode {

    /** 基金购买错误 */
	ERR_FUND_TRANS_BUY_TIME_EXCEEDED("非购买时间购买", "00200010001"), 
	ERR_FUND_TRANS_MAX_BUY_AMT_EXCEEDED("超过单笔购买上限", "00200010002"), 
	ERR_FUND_TRANS_MIN_BUY_AMT_EXCEEDED("未到起投下限", "00200010003"), 
	ERR_FUND_TRANS_BUY_INC_AMT("产品不符合级差购买标准", "00200010004"), 
	ERR_FUND_TRANS_BUY_PER_UDB_LIMIT_AMT("超过用户购买产品的单日最高限额", "00200010005"), 
	ERR_FUND_TRANS_BUY_LIMIT_AMT("超过产品可购买的总共最高限额", "00200010006"), 
	ERR_FUND_TRANS_BUY_TRANS_TYPE_MISMATCH("在募集期无法申购", "00200010007"), 
	ERR_FUND_TRANS_BUY_ROOKIE_LIMIT("新手专享无法购买", "00200010008"), 
	ERR_FUND_TRANS_BUY_STAFF_LIMIT("员工专享无法购买", "00200010009"),
	ERR_FUND_TRANS_WHITE_GROUP_LIMIT("非白名单用户无法购买", "00200010010"),
	ERR_FUND_TRANS_ORDER_NOT_FOUND("基金交易单找不到", "00200010011"),
	ERR_FUND_TRANS_TC_STATUS_MISMATCH("交易核心状态不匹配", "00200010012"),
	ERR_FUND_TRANS_BUY_TOTAL_DAY_LIMIT_AMT("超过产品可购买的当日总限额", "00200010013"),
	ERR_FUND_TRANS_BUY_OFFLINE_TOKEN_DUPLICATE("重复线下购买记录插入", "00200010014"),
	ERR_FUND_TRANS_OFFLINE_SETTLEMENT_NOT_FUND("待清盘资产不存在", "00200010015"),
	ERR_FUND_TRANS_BUY_RULE_LIMIT_AMT("超过产品规则范围内的可购买总共最高限额", "00200010016"),

	/** 基金赎回错误 */
	ERR_FUND_TRANS_MAX_REDEEM_AMT_EXCEEDED("超过单笔赎回上限", "00200020001"), 
	ERR_FUND_TRANS_REDEEM_PER_UDS_LIMIT_AMT("超过用户赎回产品的单日最高限额", "00200020002"), 
	ERR_FUND_TRANS_MIN_REDEEM_AMT_EXCEEDED("未到最低赎回金额", "00200020003"), 
	ERR_FUND_TRANS_REDEEM_TIME_EXCEEDED("非赎回时间赎回", "00200020004"),
	ERR_FUND_TRANS_REDEEM_CLOSED("兑付时间未到无法赎回", "00200020005"),
	ERR_FUND_TRANS_REDEEM_AMT_NOT_ENOUGH("超额赎回", "00200020006"),
	ERR_FUND_TRANS_REDEEM_AMT_HUGE("触发巨额赎回", "00200020007"),
	ERR_FUND_TRANS_REDEEM_ORIGIN_NOT_FOUND("原始申购单找不到", "00200020008"),
	ERR_FUND_TRANS_REDEEM_AMT_HUGE_REFUSE("巨额赎回拒绝", "00200020009"),

	/** 依赖系统错误 - 会员错误*/
	ERR_FUND_QUERY_MEMBER_NOT_EXIST("查询的会员不存在", "00200030001"),
	ERR_FUND_UPDATE_MEMBER_ROOKIE("更新新手标示失败", "00200030002"),
	ERR_MEM_NOT_EXIST("会员不存在", "00200030003"),
	ERR_MEM_MODIFY_FAIL("修改用户信息失败", "00200030004"),


	/** 依赖系统错误 - 产品错误*/
	ERR_FUND_QUERY_PRODUCT_NOT_EXIST("查询的产品不存在", "00200040001"),
	ERR_FUND_QUERY_BY_NAME("根据基金名字查询基金失败", "00200040006"),
	ERR_INCORRECT_BIZ_CHANNEL("无效的渠道号", "00200040009"),
	ERR_INCORRECT_FROZEN_AMOUNT("冻结金额超过余额或小于等于零", "00200040010"),
	ERR_FROZEN_TOKEN_EXIST("冻结令牌已存在，指令重复", "00200040011"),
	ERR_FROZEN_TOKEN_NOT_EXIST("令牌不存在，无法解冻", "00200040012"),
	ERR_INCORRECT_UNFREEZE_AMOUNT("解冻金额大于剩余冻结金额", "00200040013"),
	ERR_NOT_ENOUGH_BALANCE("货币工具余额不足", "00200040014"),
	ERR_PRODUCT_NOT_ON_SALE("产品已不是销售状态", "00200040015"),
	ERR_PRODUCT_ID_CAN_NOT_BE_NULL("产品ID不能为空", "00200040016"),
	ERR_INCORRECT_CLOSE_TYPE("定期产品无法扣减货币工具余额", "00200040017"),
	ERR_HANG_UP("挂起", "00200040018"),
	ERR_BALANCE_OTHERS("货币工具其他错误", "00200040019"),
	ERR_FIS_CALL_FAILED("fis调用失败", "00200040020"),
	ERR_UNFREEZE_AS_FAIL_ALREADY("库存已经归还，无法扣减", "00200040021"),
	ERR_UNFREEZE_AS_SUCCESS_ALREADY("库存已经扣减，无法归还", "00200040022"),
	ERR_FIS_UNKNOWN_PRODUCT_CLOSE_TYPE("未知的产品封闭类型", "00200040023"),


    /** 依赖系统错误 - 基金账户错误 */
	ERR_FUND_ACCT_CREATE("创建资产账号失败", "00200050001"),
	ERR_FUND_ACCT_STATUS("基金资产账号状态错误", "00200050002"),
	ERR_FUND_ACCT("调用资产中心错误", "00200050003"),
	ERR_FUND_ACCT_PURCHASE("资产中心申购失败", "00200050004"),
	ERR_FUND_ACCT_SUBSCRIBE("资产中心认购失败", "00200050005"),
	ERR_FUND_ACCT_NOT_FOUND("资产账户查询不到", "00200050006"),
	ERR_FUND_ACCT_TRANS_FORBIDDEN("资产账户禁止交易", "00200050007"),

    /** 依赖系统错误 - 资产中心错误 */
	ERR_FUND_AMC_CALL_CUT_DIFF("资产当日赎回失败", "00200060001"),
	ERR_FUND_AMC_CALL_QUERY("资产获取失败", "00200060002"),
	ERR_FUND_AMC_CALL_REDEEM("资产赎回失败", "00200060003"),
	ERR_FUND_AMC_CALL_NETWORTH("获取净值失败","00200060004"),
	ERR_FUND_AMC_CALL_TOTAL_FUND_NOT_ENOUGTH("基金总值不足","00200060005"),
	ERR_FUND_AMC_CALL_UNCONFIRMED_FUND_NOT_ENOUGTH("未确认份额不足","00200060006"),
	ERR_FUND_AMC_PURCHASE_RECORD_CREATE_FAILURE("申购记录插入失败","00200060007"),
	ERR_FUND_AMC_FTC_ORDER_NO_ISNULL("FTC单号为空","00200060008"),
	ERR_FUND_AMC_ALTERATION_NOT_EXIST("基金明细变动记录不存在","00200060009"),
	ERR_FUND_AMC_ALTERATION_ALREADY_CONFIRMED("基金明细变动已被确认","00200060010"),
	ERR_FUND_AMC_FUND_SHARE_NOT_COMPIREABLE("基金份额不匹配","00200060011"),
	ERR_FUND_OPERATION_NOT_ILLEGUE("操作指令不合法","00200060012"),
	ERR_FUND_AMC_NOT_FOUND("未购买该基金","00200060013"),
	ERR_FUND_AMC_SUBSCRIPTION_RECORD_CREATE_FAILURE("认购记录插入失败","002000600014"),
	ERR_FUND_INPUT_OBJ_IS_NULL("传入参数不合法","00200060015"),
	ERR_FUND_AMC_FUND_SETTLEMENT_FAILED("清盘处理失败!","00200060016"),
	ERR_FUND_AMC_FUND_ACC_CONFIRM_FAILED("账户开户确认失败!","00200060017"),
	ERR_FUND_AMC_SUBSCRIPTION_CONFIRM_FAILURE("认购确认失败","002000600018"),
	ERR_FUND_AMC_REDEMPTION_CONFIRM_FAILURE("赎回确认失败","002000600019"),
	ERR_FUND_AMC_PURCHASE_CONFIRM_FAILURE("申购确认失败","00200060020"),
	ERR_FUND_ACC_STATUS_ILLEGUE("账户状态不可操作","00200060021"),
	ERR_FUND_AMC_BONUS_CONFIRM_FAILURE("红利确认失败","00200060022"),
	
    /** 依赖系统错误 - 交易核心错误 */
	ERR_FUND_TRANSCORE_CALL_CREATE("创建交易单失败", "00200070001"),
	ERR_FUND_TRANSCORE_CALL_REPORT_EVENT("更新交易单状态失败", "00200070002"),
	ERR_FUND_TRANSCORE_STATUS("交易单状态错误", "00200070003"),
	ERR_FUND_BATCH_TRANSCORE_CALL_CREATE("批量创建交易单失败", "00200070004"),
	ERR_FUND_BATCH_TRANSCORE_CALL_REPORT_EVENT("批量更新交易单失败", "00200070005"),
	ERR_FUND_TRANSCORE_CLOSE("关闭交易单失败", "00200070006"),
	ERR_FUND_TRANSCORE_RESUME_PAY("重新支付交易单失败", "00200070007"),
	ERR_FUND_TRANSCORE_GET("获取交易单失败", "00200070008"),
	ERR_PAYMENT_AMOUNT_EXCEED_LIMITATION("支付金额超过限额", "00200070009"),
	ERR_INSTRUMENT_INVALID_CARD_NO("无效银行卡号", "00200070010"),
	ERR_INSTRUMENT_NOT_SUPPORTED_CARD_TYPE("不支持的银行卡类型", "00200070011"),
	ERR_INSTRUMENT_NOT_SUPPORTED_CARD_BANK("不支持的银行", "00200070012"),
	ERR_PAYMENT_UNEXPECTED_STATUS("支付单状态不符", "00200070013"),
	ERR_PAYMENT_PROVIDER_NOT_FOUND("无法获得支付服务商", "00200070014"),
	ERR_PAYMENT_MISSING_DCPBINDING_INFO("代收付并卡信息丢失", "00200070015"),
	ERR_PAYMENT_PAYMENT_NOT_FOUND("找不到支付单", "00200070016"),
	ERR_PAYMENT_REF_PAYMENT_NOT_FOUND("找不到关联支付单", "00200070017"),
	ERR_PAYMENT_NOT_SUPPORTED_PAYMENT_TYPE("不支持的支付类型", "00200070018"),
	ERR_PAYMENT_PROVIDER_PAYMENT_NO_NOT_FOUND("无法获得支付服务商支付流水号", "00200070019"),
	ERR_PAYMENT_DUPLICATED_PAYMENT_REQUEST("重复的支付请求", "00200070020"),
	ERR_PAYMENT_PROVINCE_CODE_NOT_FOUND("找不到对应的省份编码信息","00200070021"),
	ERR_PAYMENT_CITY_CODE_NOT_FOUND("找不到对应的城市编码信息","00200070022"),
	ERR_PAYMENT_INVALID_INSTRUMENT("使用无效的支付凭证","00200070023"),
	ERR_WITHDRAW_FAILURE("提现失败","00200070024"),
	ERR_WITHDRAW_QUERY_FAILURE("提现查询失败","00200070025"),
	ERR_PAYMENT_INVALID_CURRENCY("无效的币种", "00200070026"),
	ERR_PAYMENT_MISSING_PAYMENT_TIME("支付单支付时间丢失", "00200070027"),
	ERR_PAYMENT_INSUFFICIENT_FUND("余额不足", "00200070028"),
	ERR_PAYMENT_INCORRECT_VALIDATE_CODE("支付短信验证码校验错误", "00200070029"),
	ERR_PAYMENT_AMOUNT_EXCEED_ONE_TIME_LIMITATION("支付金额超过单笔限额","00200070030"),
	ERR_PAYMENT_AMOUNT_EXCEED_ONE_DAY_LIMITATION("支付金额超过单日限额","00200070031"),
	ERR_PAYMENT_AMOUNT_EXCEED_ONE_MONTH_LIMITATION("支付金额超过单月限额","00200070032"),
	ERR_PAYMENT_AMOUNT_EXCEED_MERCHANT_LIMITATION("支付金额超过商户限额","00200070033"),
	ERR_PAYMENT_TIME_EXCEED_ONE_DAY_LIMITATION("支付次数超过单日限制","00200070034"),
	ERR_PAYMENT_TIME_EXCEED_ONE_MONTH_LIMITATION("支付次数超过单月限制","00200070035"),
	ERR_INSTRUMENT_PROVIDER_NOT_FOUND("无法获得支付凭证服务商", "00200070036"),
	ERR_INSTRUMENT_NOT_SUPPORTED_INSTRUMENT_TYPE("不支持的支付凭证类型", "00200070037"),
	ERR_INSTRUMENT_NOT_SUPPORTED_GLOBAL_INSTRUMENT("不支持添加全局支付凭证", "00200070038"),
	ERR_INSTRUMENT_INSTRUMENT_NOT_FOUND("找不到支付凭证", "00200070039"),
	ERR_INSTRUMENT_INSTRUMENT_ALREADY_ADDED("支付凭证已绑定", "00200070040"),
	ERR_INSTRUMENT_SEND_ACTIVATION_CODE_FAILED("重发绑卡验证码失败", "00200070041"),
	ERR_INSTRUMENT_NOT_SUPPORTED_CARD_HOLDER_ID_TYPE("不支持的持卡人身份类型", "00200070042"),
	ERR_INSTRUMENT_UNEXPECTED_STATUS("支付凭证状态不符合预期", "00200070043"),
	ERR_INSTRUMENT_INCORRECT_INSTRUMENT_INFO("支付凭证信息不正确", "00200070044"),
	ERR_INSTRUMENT_INCORRECT_PHONE("支付凭证对应手机号不正确", "00200070045"),
	ERR_INSTRUMENT_INCORRECT_VALIDATE_CODE("绑卡短信验证码校验错误", "00200070046"),
	ERR_PROVIDER_MERCHANT_INFO_NOT_FOUND("无法获得支付服务商商户信息", "00200070047"),
	ERR_PROVIDER_NOT_SUPPORTED_OPERATION("支付服务商不支持该服务", "00200070048"),
	ERR_PROVIDER_MERCHANT_MAP_NOT_FOUND("无法找到支付服务商商户映射信息", "00200070049"),
	ERR_PROVIDER_MERCHANT_MAP_CORRUPT_DATA("支付服务商商户映射信息数据损坏", "00200070050"),
	ERR_PROVIDER_RETURN_UNEXPECTED_STATUS("支付服务商返回异常状态", "00200070051"),
	ERR_PROVIDER_IP_NOT_IN_WHITE_LIST("IP地址不在支付服务商的白名单中", "00200070052"),
	ERR_PROVIDER_SEND_VALIDATE_CODE_TOO_OFTEN("生成验证码过于频繁", "00200070053"),
	ERR_PROVIDER_DUP_REQUEST_ID("调用支付服务商接口请求号重复", "00200070054"),
	ERR_PROVIDER_SYSTEM_ERROR("支付服务商系统异常", "00200070055"),
	ERR_PROVIDER_CARD_NOT_BIND("银行卡未在支付服务商处绑定", "00200070056"),
	ERR_PROVIDER_CARD_ALREADY_BIND("银行卡已经在支付服务商处绑定", "00200070057"),
	ERR_PROVIDER_RISK_REJECT("支付服务商风控系统拒绝", "00200070058"),
	ERR_PROVIDER_BANK_SYSTEM_ERROR("支付服务商银行系统异常", "00200070059"),
	ERR_PROVIDER_INVOCATION_TIMEOUT("支付服务商调用超时", "00200070060"),
	ERR_PROVIDER_PHONE_NOT_IN_WHITE_LIST("手机号不在白名单中", "00200070061"),
	ERR_PROVIDER_SYSTEM_BUSY("支付服务商系统繁忙", "00200070062"),
	ERR_PROVIDER_CONTENT_TEMP_NOT_MATCH("短信发送内容与模板不符", "00200070063"),
	ERR_PROVIDER_ACCOUNT_NOT_EXIST("支付服务商支付账户不存在", "00200070064"),
	ERR_PROVIDER_SYSTEM_TIMEOUT("支付服务商内部系统超时", "00200070065"),

	ERR_COUPON_MORE_THAN_MAX("超过最大值", "00200070066"),
	ERR_COUPON_LESS_THAN_MIN("小于最小值", "00200070067"),
	ERR_COUPON_PASS_DUE("优惠券已过期", "00200070068"),
	ERR_COUPON_NOT_TO_USE("优惠券未到使用期", "00200070069"),
	ERR_COUPON_NOT_BELONG_USE("该用户没有此优惠券", "00200070070"),
	ERR_COUPON_NOT_USE_STATUS("该优惠券非等待使用状态", "00200070071"),
	ERR_COUPON_CHECK_IN_FAIL("优惠券使用核销失败", "00200070072"),
	ERR_COUPON_NOT_SUPPORT("不支持该产品", "00200070073"),
	ERR_COUPON_REFUND_EXCEPTION("优惠券回滚优惠券失败", "00200070074"),

	ERR_PROVIDER_WRONG_PARAMETER("参数错误", "00200070075"),
	ERR_PROVIDER_SEND_VALIDATE_CODE_TIME_EXCEED("发送验证码次数超限", "00200070076"),
	ERR_PROVIDER_VALIDATE_CODE_EXPIRED("验证码过期", "00200070077"),
	ERR_PROVIDER_VALIDATE_SIGN_FAILED("校验支付服务商应答错误", "00200070078"),
	ERR_PROVIDER_GENERAL_ERROR("支付服务商通用错误", "00200070079"),
	ERR_INSTRUMENT_INCORRECT_IDENTITY_ID("身份证号不正确", "00200070080"),


	ERR_INSTRUMENT_PHONE_NOT_BIND("银行卡未绑定手机号", "00200070081"),
	ERR_INSTRUMENT_INVALID_BANK_CARD("无效的银行卡", "00200070082"),
	ERR_INSTRUMENT_INCORRECT_BANK("银行不正确", "00200070083"),
	ERR_INSTRUMENT_INCORRECT_IDENTITY_TYPE("身份证件类型不正确", "00200070084"),
	ERR_PROVIDER_CHANNEL_UNAVAILABLE("服务商支付渠道不可用", "00200070085"),
	ERR_PROVIDER_CHANNEL_NOT_FOUND("服务商找不到可用的支付渠道", "00200070086"),
	ERR_INSTRUMENT_CARD_IS_BIND_BY_OTHERS("此卡已被其他用户绑定", "00200070087"),
	ERR_INSTRUMENT_CARD_DISABLED("此卡被禁用", "00200070088"),

	ERR_PAYMENT_ROUTER_MAINTENANCE_TYPE_NOT_SUPPORTED("不支持的路由维护类型", "00200070089"),
	ERR_PAYMENT_BANK_IS_UNDER_MAINTENANCE("该银行正在维护，不可用", "00200070090"),
	ERR_PROVIDER_COMMISSION_FEE_FORMULA_NOT_FOUND("服务商找不到可用的手续费费率公式", "00200070091"),
	ERR_PROVIDER_FEE_CHARGE_MODE_NOT_SUPPORTED("不支持的服务商手续费计算类型", "00200070092"),


	ERR_PAYMENT_NO_AVAILABLE_PAYMENT_PROVIDER("找不到可用支付渠道", "00200070093"),

	ERR_INSTRUMENT_NO_AVAILABLE_AUTH_PROVIDER("找不到可用鉴权渠道", "00200070094"),

	ERR_PAYMENT_RECHARGE_FAILED("交易处理失败", "00200070095"),
	ERR_PROVIDER_UNAUTHORIZED("支付交易无权限", "00200070096"),
	ERR_PROVIDER_ACCOUNT_UNEXPECTED_STATUS("支付账户状态异常", "00200070097"),
	ERR_PROVIDER_CERTIFICATE_INVALID("平台证书无效或未绑定证书", "00200070098"),
	ERR_PROVIDER_CERTIFICATE_CHECK_FAILED("平台证书验签失败", "00200070099"),
	ERR_ACCOUNT_BALANCE_NOT_ENOUGTH("会员余额不足", "00200070100"),

	ERR_PROVIDER_SEND_VALIDATE_CODE_TOO_OFTEN_ONE_MINUTE("生成验证码过于频繁，请于一分钟后尝试", "00200070101"),
	
	ERR_PAYMENT_AMOUNT_LESS_THAN_THE_MINIMUM("支付金额小于支付渠道单笔最低限额", "00200070102"),
	
//
//	#ERR_INSTRUMENT_PHONE_NOT_BIND("银行卡未绑定手机号", "00950020017"),//new
//	00200070081=00950020017
//			#ERR_INSTRUMENT_INVALID_BANK_CARD("无效的银行卡", "00950020018"),//new
//	00200070082=00950020018
//			#ERR_INSTRUMENT_INCORRECT_BANK("银行不正确", "00950020019"),//new
//	00200070083=00950020019
//			#ERR_INSTRUMENT_INCORRECT_IDENTITY_TYPE("身份证件类型不正确", "00950020020"), //new
//	00200070084=00950020020
//			#ERR_PROVIDER_CHANNEL_UNAVAILABLE("服务商支付渠道不可用", "00950030024"),//new
//	00200070085=00950020024
//			#ERR_PROVIDER_CHANNEL_NOT_FOUND("服务商找不到可用的支付渠道", "00950030025"),//new
//	00200070086=00950020025




	/** 依赖系统错误 - OFC错误 */
	ERR_FUND_OFC_CALL_CREATE("创建业务订单项失败", "00200080001"),

    /** TA系统错误  */
	ERR_FUND_TA_INVAILD_ORDER("无效的订单", "00200090001"),
	ERR_FUND_TA_INVALID_SERIRAL_NO("无效的订单号", "00200090002"),
	ERR_FUND_TA_INVAILD_TRANS_ACCOUNT("无效的交易账号", "00200090003"),
	ERR_FUND_TA_INVAILD_FUND_CODE("无效的基金代码", "00200090004"),
	ERR_FUND_TA_INVAILD_CONTRACT("无效的合同号", "00200090005"),
	ERR_FUND_TA_INVAILD_MEMBER("无效的会员信息", "00200090006"),
	ERR_FUND_TA_DIVIDEND_FAILED("红利处理失败", "00200090007"),

	/** 依赖系统错误 - 支付核心错误 */
	ERR_FUND_PC_GET_DCP("获取DCP支付方式失败", "00200100001"),


	/** 依赖系统错误 - 风控错误 */
	ERR_FUND_RISK_REJECTED("风控交易拒绝", "00200110001"),
	ERR_FUND_RISK_QUOTA_STAT_EXCEED_LIMIT("超出限额限次", "00200110002"),

	/** 基金通用错误 */
	ERR_FUND_ORDER_QUERY_NOT_FOUND("基金订单查询无结果", "00200120001"),
	ERR_FUND_COMMON_LOCK_CONFLICT("并发冲突", "00200120002"),
	ERR_FUND_ORDER_STATUS_MISMATCH("基金状态错误", "00200120003"),

	/** 依赖系统错误 - 营销核心错误 */
	ERR_FUND_MKT_BATCH_UNFREEZE_GROUPON("调用批量解冻团购份额失败", "00200130001"),
	ERR_FUND_MKT_UNFREEZE_GROUPON("解冻团购份额失败", "00200130002"),
	ERR_FUND_MKT_DEDUCT_GROUPON("扣减团购份额失败", "00200130003"),
	ERR_FUND_MKT_BATCH_DEDUCT_GROUPON("调用批量扣减团购份额失败", "00200130004"),
	ERR_FUND_MKT_USER_NOT_JOINED_GROUP("用户未参团", "00200130005"),
	ERR_FUND_MKT_FREEZE_GROUPON("冻结团购份额失败", "00200130006"),

	/** 二级市场错误码 */
	ERR_FUND_TRANSFEROR_DEAL_SUCC("转让单已成交", "00200140001"),
	ERR_FUND_TRANSFEROR_CANCEL_BY_USER("转让单被用户撤销", "00200140002"),
	ERR_FUND_TRANSFEROR_CLOSE_BY_EXPIRED("转让单超时撤销", "00200140003"),
	ERR_FUND_TRANSFEROR_CANCEL_BY_RISK("转让单被风控撤销", "00200140004"),
	ERR_FUND_TRANSFEROR_PROCESSING("转让单正在处理中", "00200140005"),
	ERR_FUND_TRANSFER_EXPIRED_NOT_ENOUGH("份额离到期天数不足", "00200140006"),
	ERR_FUND_TRANSFER_HOLD_NOT_ENOUGH("份额持有天数不足", "00200140007"),
	ERR_FUND_TRANSFER_AMOUNT_DIFFERENT("转让金额信息发生变更", "00200140008"),
	ERR_FUND_TRANSFER_FREEZE_ASSET("冻结资产份额失败", "00200140009"),
	ERR_FUND_TRANSFER_UNFREEZE_ASSET("解冻资产份额失败", "00200140010"),
	ERR_FUND_TRANSFER_DEDUCT_ASSET("扣减资产份额失败", "00200140011"),
	ERR_FUND_TRANSFER_CREATE_PRODUCT("创建二级市场产品失败", "00200140012"),
	ERR_FUND_TRANSFER_RULE_NOT_FUND("找不到产品交易规则", "00200140013"),
	ERR_FUND_TRANSFER_SIGN_AGREEMENT("签订转让协议失败", "00200140014"),
	ERR_FUND_TRANSFER_SIGN_CONTRACT("签订合同失败", "00200140015"),
	ERR_FUND_TRANSFER_ACCOUNT("转账交易失败", "00200140016"),
	ERR_FUND_TRANSFEREE_CLOSE_BY_EXPIRED("受让单超时撤销", "00200140017"),
	ERR_FUND_TRANSFEROR_ORDER_NOT_EXIST("找不到转让单", "00200140018"),
	ERR_FUND_TRANSFEREE_ORDER_NOT_EXIST("找不到受让单", "00200140019"),
	ERR_FUND_TRANSFER_PRODUCT_CANNOT_TRANSFER("产品不支持转让", "00200140020"),
	ERR_FUND_TRANSFER_RATE_TOO_LOW("转让预期收益率过低", "00200140021"),
	ERR_FUND_TRANSFER_RATE_TOO_HIGH("转让预期收益率过高", "00200140022"),
	ERR_FUND_TRANSFER_SAME_MEMBER("转让者和受让者是同一人", "00200140023"),
	ERR_FUND_TRANSFER_RECEIVED_AMOUNT("转让者到帐金额为负", "00200140024"),
	ERR_FUND_TRANSFER_EXPIRED_DATE("转让有效日期超过产品到期日期", "00200140025"),
	ERR_FUND_TRANSFER_NO_PRODUCT_SHARE("不存在可转让的用户产品资产份额", "00200140026"),
	ERR_FUND_TRANSFER_EXCHANGE_TIMEOUT("交易所受让请求超时", "00200140027"),
	ERR_FUND_TRANSFER_EXCHANGE_FAIL("交易所受让请求失败", "00200140028");

	
	private String code;

    private String message;

    private FTCRespCode(String message, String code) {
        this.code = code;
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public static FTCRespCode getEnum(String value) {
    	FTCRespCode[] crc = FTCRespCode.values();
        for (int i = 0; i < crc.length; i++) {
            if (crc[i].getCode().equals(value)) {
                return crc[i];
            }
        }
        return null;
    }

	public static FTCRespCode getEnumByName(String name) {
		FTCRespCode[] crc = FTCRespCode.values();
		for (int i = 0; i < crc.length; i++) {
			if (crc[i].name().equals(name)) {
				return crc[i];
			}
		}
		return null;
	}
}
