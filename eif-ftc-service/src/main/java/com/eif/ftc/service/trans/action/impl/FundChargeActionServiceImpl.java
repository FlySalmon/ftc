package com.eif.ftc.service.trans.action.impl;

import com.eif.fis.facade.constant.ProductCloseType;
import com.eif.fis.facade.request.ftc.UnfreezeAndDeductCurrentProdInventoryReq;
import com.eif.fis.facade.request.ftc.UnfreezeAndDeductionProdInventoryReq;
import com.eif.fis.facade.response.ftc.QueryProdTransInfoV2Resp;
import com.eif.framework.common.utils.code.CommonRspCode;
import com.eif.ftc.dal.dao.FundTransOrderMapper;
import com.eif.ftc.dal.model.FundTransOrder;
import com.eif.ftc.dal.model.FundTransOrderExample;
import com.eif.ftc.facade.fund.trans.enumeration.FundTransOrderStatus;
import com.eif.ftc.integration.fis.FisIntService;
import com.eif.ftc.integration.goutong.GoutongIntService;
import com.eif.ftc.integration.market.MarketIntService;
import com.eif.ftc.integration.member.MemberIntService;
import com.eif.ftc.integration.mq.MQMessageSender;
import com.eif.ftc.integration.ofc.OfcIntService;
import com.eif.ftc.integration.setting.SettingIntegrationService;
import com.eif.ftc.service.trans.action.FundChargeActionService;
import com.eif.ftc.service.trans.data.FundTransDataService;
import com.eif.ftc.util.exception.BusinessException;
import com.eif.goutong.facade.bean.SendInboxMsgByBizBean;
import com.eif.goutong.facade.constant.BusinessIDType;
import com.eif.goutong.facade.constant.InboxMsgContentType;
import com.eif.goutong.facade.constant.InboxMsgSendType;
import com.eif.goutong.facade.constant.ParamKeys;
import com.eif.goutong.facade.request.SendInboxMsgByBizReq;
import com.eif.setting.facade.service.dto.CusTransRequestDto;
import com.eif.transcore.facade.dto.enumeration.TransCode;
import com.lts.core.commons.utils.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

import java.util.*;

/**
 * Created by bohan on 9/27/16.
 */
@Service("fundChargeActionService")
public class FundChargeActionServiceImpl implements FundChargeActionService {

    static Logger logger = LoggerFactory.getLogger(FundChargeActionServiceImpl.class);

    @Resource
    GoutongIntService goutongIntService;

    @Autowired
    MQMessageSender mqMessageSender;

    @Autowired
    FundTransOrderMapper fundTransOrderMapper;

    @Autowired
    FundTransDataService fundTransDataService;

    @Resource
    SettingIntegrationService settingIntService;

    @Resource
    OfcIntService ofcIntService;

    @Resource
    FisIntService fisIntService;

    @Resource
    MarketIntService marketIntService;

    @Resource
    MemberIntService memberIntService;

    public void notifyCustomerWhenInvestSuc(FundTransOrder transOrder, Integer closeType,
    		String productName, String mobile) {

        // 异步投资申请提交SMS通知
        Map<String, String> smsParams = new HashMap<>();
//        ArrayList<String> inboxParams = new ArrayList<>();

        //判断定期活期发站内信, 活期支付成功不发短信
//        if (!transOrder.getFundTransType().equals(FundTransType.PURCHASING)) {
        if (!closeType.equals(ProductCloseType.OPEN)) {
        	int businessIDType = 0;
        	if (closeType.equals(ProductCloseType.CLOSE)) {//定期
        		businessIDType = BusinessIDType.INVEST_APPLICATION_SUBMITTED.getId();
        	} else {//活包定
        		businessIDType = BusinessIDType.HBD_INVEST_SUBMIT_SUCCESS.getId();
        	}
        	
            //SMS
            smsParams.put(ParamKeys.PRODUCT_NAME.getName(), productName);
            smsParams.put(ParamKeys.AMOUNT.getName(), String.format("%.2f", transOrder.getFundTransAmount()));
//            QueryMemberInfoResponse memberInfoResponse = memberIntService.queryMembersInfoByMemberNo(transOrder.getMemberNo());
            goutongIntService.sendSmsAsync(transOrder, businessIDType, smsParams, mobile);

            //定期认购站内信
            SendInboxMsgByBizReq sendInboxMsgByBizReq = new SendInboxMsgByBizReq();
            SendInboxMsgByBizBean sendInboxMsgByBizBean = new SendInboxMsgByBizBean();
            sendInboxMsgByBizBean.setBusinessId(businessIDType);
            sendInboxMsgByBizBean.setSendType(InboxMsgSendType.SINGLE_SEND);

            sendInboxMsgByBizBean.setContentType(InboxMsgContentType.TEXT_ONLY_MSG);
            sendInboxMsgByBizBean.setParameters(smsParams);
//            inboxParams.add(productName);
//            inboxParams.add(String.format("%.2f", transOrder.getFundTransAmount()));
//            sendInboxMsgByBizBean.setParams(inboxParams);

            Set<String> memberNos = new HashSet<String>();
            memberNos.add(transOrder.getMemberNo());
            sendInboxMsgByBizReq.setMemberNos(memberNos);

            sendInboxMsgByBizReq.setSendInboxMsgBean(sendInboxMsgByBizBean);
            mqMessageSender.sendInboxMsg(sendInboxMsgByBizReq, transOrder.getBizChannel());
        }
    }

    public void doRefund(FundTransOrder transOrder, String productName, String mobile) {
        Date curDate = DateUtils.now();

        // 业务单更新
        refundFundTransOrder(curDate, transOrder, FundTransOrderStatus.REFUNDING);

        //更新OFC订单状态
        ofcIntService.updateBusinessOrderItemStatusAsync(transOrder, FundTransOrderStatus.REFUNDING, productName);

        //创建退款单
        CusTransRequestDto cusTransRequestDto = new CusTransRequestDto();
        cusTransRequestDto.setOfcOrderNo(transOrder.getBusinessOrderItemNo());
        cusTransRequestDto.setBizOrderNo(transOrder.getFundTransOrderNo());
        cusTransRequestDto.setTransCode(TransCode.REFUND);
        cusTransRequestDto.setBizTransType(transOrder.getFundTransType());
        cusTransRequestDto.setMemNo(transOrder.getMemberNo());
        cusTransRequestDto.setNeedAmount(transOrder.getRefundAmount());
        cusTransRequestDto.setCurrency(transOrder.getCurrencyType());
        cusTransRequestDto.setReTransNo(transOrder.getTranscoreTransNo());
        cusTransRequestDto.setTransTime(curDate);
        cusTransRequestDto.setBizProductNo(transOrder.getProductId());
        cusTransRequestDto.setPaymentMethods(transOrder.getPayMethod());
        cusTransRequestDto.setTrackingNo(UUID.randomUUID().toString().replace("-", ""));
        cusTransRequestDto.setBizChannel(transOrder.getBizChannel());

        settingIntService.createSettingOrderAsync(cusTransRequestDto);
        
        //发送通知短信
        Map<String, String> params = new HashMap<>();
        params.put(ParamKeys.PRODUCT_NAME.getName(), productName);
        params.put(ParamKeys.AMOUNT.getName(), String.format("%.2f", transOrder.getRefundAmount()));
        goutongIntService.sendSmsAsync(transOrder, BusinessIDType.INVEST_REFUND_NOTICE.getId(), params, mobile);
    }

    @Transactional
    public void refundFundTransOrder(Date curDate, FundTransOrder transOrder, int targetStatus) {
        FundTransOrder updateOrder = new FundTransOrder();
        updateOrder.setTranscoreTransNo(transOrder.getTranscoreTransNo());
        updateOrder.setStatus(targetStatus);
        updateOrder.setRefundAmount(transOrder.getFundTransAmount());
        updateOrder.setUpdateTime(curDate);

        //退款成功更新退款成功时间
        if (targetStatus == FundTransOrderStatus.REFUND_SUC) {
            updateOrder.setRefundTime(curDate);
            updateOrder.setFinishTime(curDate);
        }

        FundTransOrderExample example = new FundTransOrderExample();
        FundTransOrderExample.Criteria criteria = example.createCriteria();
        criteria.andIdEqualTo(transOrder.getId());
        List<Integer> prevStatusList = new ArrayList<>();
        prevStatusList.add(FundTransOrderStatus.PAYING);
        prevStatusList.add(FundTransOrderStatus.CLOSE_BY_EXPIRY);
        criteria.andStatusIn(prevStatusList);

        int effectedRow = fundTransOrderMapper.updateByExampleSelective(updateOrder, example);

        if (effectedRow == 0) {
            throw new BusinessException(CommonRspCode.DB_ERROR.getMessage(), CommonRspCode.DB_ERROR.getCode());
        }

        //更新当前订单信息
        transOrder.setStatus(targetStatus);
        transOrder.setRefundAmount(transOrder.getFundTransAmount());
        transOrder.setUpdateTime(curDate);

        fundTransDataService.createFundTransOrderStatusInfo(curDate, targetStatus, transOrder.getId());
    }

    public void compensateProdInventory(int closeType,FundTransOrder transOrder) {
        try {
            if(closeType == ProductCloseType.CLOSE) {
                fisIntService.compensateProdInventory(transOrder.getFrozenCode());
            }else if(closeType == ProductCloseType.HALF_OPEN || closeType == ProductCloseType.CLOSE_OPEN) {
                fisIntService.compensateCurrentProdInventory(transOrder.getFrozenCode());
            }else{
                logger.warn("compensateProdInventory failed! Illegue type : " + String.valueOf(closeType));
            }
        }
        catch (Exception e) {
            logger.warn("compensateProdInventory failed! ", e);
            compensateProdInventoryAsync(closeType, transOrder);
        }
    }

    public void unfreezeAndDeductGrouponInventory(FundTransOrder transOrder) {
        try {
            marketIntService.unfreezeAndDeductGrouponInventory(transOrder.getFrozenCode());
        }
        catch (Exception e) {
            logger.warn("unfreezeAndDeductGrouponInventory failed! ", e);
            marketIntService.unfreezeAndDeductGrouponInventoryAsync(transOrder, transOrder.getFrozenCode());
        }
    }

    public void unfreezeAndDeductProdInventory(int closeType,String frozenCode)
    {
        if(closeType == ProductCloseType.CLOSE) {
            UnfreezeAndDeductionProdInventoryReq request = new UnfreezeAndDeductionProdInventoryReq();
            request.setToken(frozenCode);
            fisIntService.unfreezeAndDeductionProdInventory(request);
        }else if(closeType == ProductCloseType.HALF_OPEN || closeType == ProductCloseType.CLOSE_OPEN) {
            UnfreezeAndDeductCurrentProdInventoryReq request = new UnfreezeAndDeductCurrentProdInventoryReq();
            request.setToken(frozenCode);
            fisIntService.unfreezeAndDeductCurrentProdInventory(request);
        }else {
            logger.warn("unfreezeAndDeductProdInventory failed! CloseType is Illegue : " + closeType);
        }

    }

    private void compensateProdInventoryAsync(int closeType,FundTransOrder transOrder)
    {
        if(closeType == ProductCloseType.CLOSE){
            fisIntService.compensateProdInventoryAsync(transOrder.getFrozenCode(),transOrder.getFundTransOrderNo());
        } else if (closeType == ProductCloseType.HALF_OPEN || closeType == ProductCloseType.CLOSE_OPEN){
            fisIntService.compensateCurrentProdInventoryAsync(transOrder.getFrozenCode(),transOrder.getFundTransOrderNo());
        }else
        {
            logger.warn("compensateProdInventoryAsync failed! CloseType is Illegue : " + closeType);
        }
    }

	@Override
	public void notifyMarketWhenInvestSuc(FundTransOrder transOrder, QueryProdTransInfoV2Resp queryProdTransInfoResp) {
//		if (transOrder.getFundTransType().equals(FundTransType.SUBSCRIBING)) {//定期，球迷夺金
//			marketIntService.sendMemberInvestment(transOrder, queryProdTransInfoResp);
//		}
		
		marketIntService.sendMemberInvestment(transOrder, queryProdTransInfoResp);
	}

}

