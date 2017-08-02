package com.eif.ftc.facade.mq.dto.topic;

/**
 * Created by bohan on 1/6/16.
 */
public final class MQTopic {
    
	/**
	 * OFC订单通知
	 */
    public final static String TOPIC_NOTIFY_BUSINESS_ORDER_ITEM = "q_ftc_notify_business_order_item";
    /**
     * OFC订单批量通知
     */
    public final static String TOPIC_NOTIFY_BATCH_BUSINESS_ORDER_ITEM = "q_ftc_notify_batch_business_order_item";

//    /**
//     * SPV到帐清盘通知
//     */
//    public final static String TOPIC_NOTIFY_SPV_WINDUP = "q_ftc_notify_spv_windup";

    /**
     * 签订合同通知
     */
    public final static String TOPIC_NOTIFY_SIGN_CONTRACT = "q_ftc_notify_sign_contract";

    /**
     * 签订合同结果通知
     */
    public final static String TOPIC_NOTIFY_SIGN_CONTRACT_RESULT = "q_ftc_notify_sign_contract_result";

    public final static String TOPIC_SETTING_TRANS_UNFIX_RESULT= "eif-setting-trans-unfix-result";

    /**
     * 通知job发送消息
     */

    public final static String TOPIC_SENDING_MSG_WHEN_SUBSCRIPTION_CONFIRM= "eif-ftc-job-send-msg-when-subscription-confirm";

    /**
     * 机构开户、申购、赎回消息
     */
    public final static String TOPIC_DIRECTLY_OPEN_ACCOUNT_REQ = "eif-ftc-job-directly-open-account";
    public final static String TOPIC_DIRECTLY_TRANSCTION_REQ = "eif-ftc-job-directly-transaction";
    
    /**
     * 签订转让者转让协议通知
     */
    public final static String TOPIC_NOTIFY_SIGN_TRANSFEROR_AGREEMENT = "q_ftc_notify_sign_transferor_agreement";
    public final static String TOPIC_NOTIFY_SIGN_TRANSFEROR_AGREEMENT_RESULT = "q_ftc_notify_sign_transferor_agreement_result";

    /**
     * 签订受让者转让协议通知
     */
    public final static String TOPIC_NOTIFY_SIGN_TRANSFEREE_AGREEMENT = "q_ftc_notify_sign_transferee_agreement";
    public final static String TOPIC_NOTIFY_SIGN_TRANSFEREE_AGREEMENT_RESULT = "q_ftc_notify_sign_transferee_agreement_result";

    /**
     * ftc对接ins系统，处理fis库存差异
     */
    public final static String  TOPIC_FTC_FIS_INVENTORY_DIFF_HANDLER = "q_ftc_fis_inventory_diff_handler";
    
}
