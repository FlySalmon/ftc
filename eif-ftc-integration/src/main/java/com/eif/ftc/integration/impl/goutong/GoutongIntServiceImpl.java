package com.eif.ftc.integration.impl.goutong;

import com.alibaba.fastjson.JSON;
import com.eif.framework.common.utils.constant.BizChannel;
import com.eif.ftc.dal.model.FundTransOrder;
import com.eif.ftc.integration.goutong.GoutongIntService;
import com.eif.ftc.integration.mq.MQMessageSender;
import com.eif.ftc.util.uuid.UUIDGenerator;
import com.eif.goutong.facade.bean.SendInboxMsgByBizBean;
import com.eif.goutong.facade.bean.SendSmsBean;
import com.eif.goutong.facade.constant.InboxMsgContentType;
import com.eif.goutong.facade.constant.InboxMsgSendType;
import com.eif.goutong.facade.constant.RequestChannelType;
import com.eif.goutong.facade.request.SendInboxMsgByBizReq;
import com.eif.goutong.facade.request.SendSmsReq;
import com.eif.goutong.facade.response.SendSmsResp;
import com.eif.goutong.facade.sms.SmsFacade;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Created by bohan on 1/15/16.
 */
@Service("goutongIntService")
public class GoutongIntServiceImpl implements GoutongIntService {

    Logger logger = LoggerFactory.getLogger(GoutongIntServiceImpl.class);

    static String FTC_SYS_NAME = "ftc";

    @Resource(name = "smsFacade")
    SmsFacade smsFacade;


    @Autowired
    MQMessageSender mqMessageSender;

    public void sendSms(SendSmsReq sendSmsReq) {
        SendSmsResp sendSmsResp = smsFacade.sendSms(sendSmsReq);
        if (!sendSmsResp.isSuccess()) {
            logger.warn("send sms failed! Content is %s", JSON.toJSONString(sendSmsReq));
        }
    }

    public void sendSmsAsync(FundTransOrder transOrder, int businessId, Map<String,String> params, String mobileNo) {
    	sendSmsAsync(transOrder.getBizChannel(), businessId, params, mobileNo);
    	
//        SendSmsBean sendSmsBean = new SendSmsBean();
//        sendSmsBean.setRequestId(UUIDGenerator.gen());
//        sendSmsBean.setSubSystem(FTC_SYS_NAME);
//        if (transOrder.getBizChannel().equals(BizChannel.ANDROID) ||
//                transOrder.getBizChannel().equals(BizChannel.IOS)) {
//            sendSmsBean.setRequestChannel(RequestChannelType.APP);
//        }
//        else if (transOrder.getBizChannel().equals(BizChannel.H5) ||
//                transOrder.getBizChannel().equals(BizChannel.WEB)){
//            sendSmsBean.setRequestChannel(RequestChannelType.WEB);
//        }
//        else {
//            sendSmsBean.setRequestChannel(RequestChannelType.DEFAULT);
//        }
//        
//        try {
//            sendSmsBean.setParams(params);
//            sendSmsBean.setMobile(mobileNo);
//            sendSmsBean.setBusinessId(businessId);
//            mqMessageSender.sendSMSMsg(sendSmsBean);
//        }
//        catch (Exception e) {
//            logger.warn("send sms failed! Content is %s", JSON.toJSONString(sendSmsBean));
//        }
    }

	@Override
	public void sendSmsAsync(Integer bizChannel, int businessId, Map<String, String> params, String mobileNo) {
		SendSmsBean sendSmsBean = new SendSmsBean();
        sendSmsBean.setRequestId(UUIDGenerator.gen());
        sendSmsBean.setSubSystem(FTC_SYS_NAME);
        if (bizChannel.equals(BizChannel.ANDROID) ||
        		bizChannel.equals(BizChannel.IOS)) {
            sendSmsBean.setRequestChannel(RequestChannelType.APP);
        } else if (bizChannel.equals(BizChannel.H5) ||
        		bizChannel.equals(BizChannel.WEB)){
            sendSmsBean.setRequestChannel(RequestChannelType.WEB);
        } else {
            sendSmsBean.setRequestChannel(RequestChannelType.DEFAULT);
        }
        
        try {
            sendSmsBean.setParams(params);
            sendSmsBean.setMobile(mobileNo);
            sendSmsBean.setBusinessId(businessId);
            mqMessageSender.sendSMSMsg(sendSmsBean);
        } catch (Exception e) {
            logger.warn("send sms failed! Content is %s", JSON.toJSONString(sendSmsBean));
        }
	}

//	@Override
//	public void sendInboxMessage(Integer bizChannel, int businessId, List<String> inboxParams, String memberNo) {
//		SendInboxMsgByBizReq sendInboxMsgByBizReq = new SendInboxMsgByBizReq();
//        SendInboxMsgByBizBean sendInboxMsgByBizBean = new SendInboxMsgByBizBean();
//        sendInboxMsgByBizBean.setBusinessId(businessId);
//        sendInboxMsgByBizBean.setSendType(InboxMsgSendType.SINGLE_SEND);
//        sendInboxMsgByBizBean.setContentType(InboxMsgContentType.TEXT_ONLY_MSG);
//        sendInboxMsgByBizBean.setParams(inboxParams);
//
//        Set<String> memberNos = new HashSet<String>();
//        memberNos.add(memberNo);
//        sendInboxMsgByBizReq.setMemberNos(memberNos);
//
//        sendInboxMsgByBizReq.setSendInboxMsgBean(sendInboxMsgByBizBean);
//        mqMessageSender.sendInboxMsg(sendInboxMsgByBizReq, bizChannel);
//	}

	@Override
	public void sendInboxMessage(Integer bizChannel, int businessId, Map<String, String> params, String memberNo) {
		SendInboxMsgByBizReq sendInboxMsgByBizReq = new SendInboxMsgByBizReq();
        SendInboxMsgByBizBean sendInboxMsgByBizBean = new SendInboxMsgByBizBean();
        sendInboxMsgByBizBean.setBusinessId(businessId);
        sendInboxMsgByBizBean.setSendType(InboxMsgSendType.SINGLE_SEND);
        sendInboxMsgByBizBean.setContentType(InboxMsgContentType.TEXT_ONLY_MSG);
        sendInboxMsgByBizBean.setParameters(params);

        Set<String> memberNos = new HashSet<String>();
        memberNos.add(memberNo);
        sendInboxMsgByBizReq.setMemberNos(memberNos);

        sendInboxMsgByBizReq.setSendInboxMsgBean(sendInboxMsgByBizBean);
        mqMessageSender.sendInboxMsg(sendInboxMsgByBizReq, bizChannel);
	}
	
}
