package com.eif.ftc.integration.goutong;

import com.eif.ftc.dal.model.FundTransOrder;
import com.eif.goutong.facade.request.SendSmsReq;

import java.util.Map;

/**
 * Created by bohan on 1/15/16.
 */
public interface GoutongIntService {
    void sendSms(SendSmsReq sendSmsReq);
    void sendSmsAsync(FundTransOrder transOrder, int businessId, Map<String,String> params,String mobileNo);
    
    /**
     * 发送短信
     * @param bizChannel
     * @param businessId
     * @param params
     * @param mobileNo
     */
    public void sendSmsAsync(Integer bizChannel, int businessId, Map<String,String> params,String mobileNo);
    
//    /**
//     * @deprecated 发送站内信
//     * @param bizChannel
//     * @param businessId
//     * @param inboxParams
//     * @param memberNo
//     */
    
//    public void sendInboxMessage(Integer bizChannel, int businessId, List<String> inboxParams, String memberNo);
    
    /**
     * 发送站内信
     * @param bizChannel
     * @param businessId
     * @param params
     * @param memberNo
     */
    public void sendInboxMessage(Integer bizChannel, int businessId, Map<String,String> params, String memberNo);
    
}
