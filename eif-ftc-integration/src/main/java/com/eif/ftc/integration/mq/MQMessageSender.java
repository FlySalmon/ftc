package com.eif.ftc.integration.mq;

import com.eif.framework.mq.DefaultRMQProducer;
import com.eif.framework.mq.common.MessageWrapper;
import com.eif.ftc.facade.mq.dto.MQBusinessOrderItemMessage;
import com.eif.ftc.facade.mq.dto.topic.MQTopic;
import com.eif.goutong.facade.bean.SendSmsBean;
import com.eif.goutong.facade.constant.InboxMsgViewChannel;
import com.eif.goutong.facade.constant.MQTagsName;
import com.eif.goutong.facade.constant.MQTopicName;
import com.eif.goutong.facade.request.SendInboxMsgByBizReq;
import com.eif.inspection.facade.mq.MQInsConstants;
import com.eif.inspection.facade.mq.MQInsHandleFeedback;
import com.eif.risk.facade.bean.FinTransBean;
import com.eif.setting.facade.constant.MQTopicConst;
import com.eif.setting.facade.request.BatchCusTransRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

@Component
public class MQMessageSender {

    private final static Logger logger = LoggerFactory.getLogger(MQMessageSender.class);

    @Resource(name = "defaultRMQProducer")
    private DefaultRMQProducer defaultRMQProducer;

    public void sendBusinessOrderItemMsg(MQBusinessOrderItemMessage mqBusinessOrderItemMessage) {
        MessageWrapper msg = new MessageWrapper(MQTopic.TOPIC_NOTIFY_BUSINESS_ORDER_ITEM, null, mqBusinessOrderItemMessage.getBizOrderNo(), mqBusinessOrderItemMessage);

        try {
            defaultRMQProducer.sendWithRetry(msg); //TODO: send result判断
        } catch (Exception e) {
            logger.error("send message err, exception is", e);
        }
    }

    public void sendBusinessOrderItemMsg(List<MQBusinessOrderItemMessage> messageList) {
        for (MQBusinessOrderItemMessage message : messageList) {
            sendBusinessOrderItemMsg(message);
        }
    }

    public void sendSMSMsg(SendSmsBean sendSmsBean) {

        MessageWrapper msg = new MessageWrapper(MQTopicName.SEND_SMS_TOPIC, null, null, sendSmsBean);
        try {
            defaultRMQProducer.sendWithRetry(msg);
        } catch (Exception e) {
            logger.error("send message err, exception is", e);
        }
    }

    public void sendRiskCPMsg(FinTransBean finTransBean) {
        MessageWrapper msg = new MessageWrapper(com.eif.risk.facade.constant.MQTopicName.FIN_TRANS_NOTIFY, null, finTransBean.getFundTransOrderNo(), finTransBean);
        try {
            defaultRMQProducer.sendWithRetry(msg);
        } catch (Exception e) {
            logger.error("send message err, exception is", e);
        }
    }

    /**
     * 发送站内信消息
     *
     * @param inboxMsg
     */
    public void sendInboxMsg(SendInboxMsgByBizReq inboxMsg, int bizChannel) {
//        if (bizChannel == BizChannel.WEB || bizChannel == BizChannel.H5 || bizChannel == BizChannel.WECHAT) {
//            inboxMsg.getSendInboxMsgBean().setViewChannel(InboxMsgViewChannel.WEB);
//        }
//        else if (bizChannel == BizChannel.ANDROID || bizChannel == BizChannel.IOS){
//            inboxMsg.getSendInboxMsgBean().setViewChannel(InboxMsgViewChannel.APP);
//        }
    	inboxMsg.getSendInboxMsgBean().setViewChannel(InboxMsgViewChannel.ALL);
        MessageWrapper msg = new MessageWrapper(MQTopicName.SEND_INBOX_MSG_TOPIC, MQTagsName.SEND_INBOX_MSG_BIZ_TAGS, null, inboxMsg);
        try {
            defaultRMQProducer.sendWithRetry(msg);
        } catch (Exception e) {
            logger.error("send inbox message err, exception is", e);
        }
    }

    /**
     * 异步创建结算单
     * @param settingRequest
     */
    public void asyncCreateSettingOrder(BatchCusTransRequest settingRequest) {
        MessageWrapper msg = new MessageWrapper(MQTopicConst.TOPIC_SETTING_CUS_TRANS_COLLECTOR,// topic
                null,// tag
                null,// key
                settingRequest);
        try {
            defaultRMQProducer.sendWithRetry(msg);
        } catch (Exception e) {
            logger.error("send setting create order message err, exception is", e);
        }
    }

    /**
     * 发送库存差异对账结果处理mq给inpection
     * @param mqInsHandleFeedback
     */
    public void sendInventoryDiffHandleMessage(MQInsHandleFeedback mqInsHandleFeedback){
        MessageWrapper msg = new MessageWrapper(MQInsConstants.TOPIC_HANDLE_FEEDBACK, mqInsHandleFeedback);
        try {
            defaultRMQProducer.sendWithRetry(msg);
        } catch (Exception e) {
            logger.error("send check inventory diff handle result error, exception is", e);
        }
    }
    
}
