package com.eif.ftc.service.mq.impl;

import com.alibaba.fastjson.JSON;
import com.eif.fis.facade.constant.ProductCloseType;
import com.eif.fis.facade.request.ftc.UnfreezeAndDeductionProdInventoryReq;
import com.eif.fis.facade.request.ftc.UnfreezeProdInventoryReq;
import com.eif.fis.facade.response.ftc.QueryProdTransInfoV2Resp;
import com.eif.framework.common.utils.code.CommonRspCode;
import com.eif.framework.common.utils.constant.BizChannel;
import com.eif.ftc.dal.dao.FundTransOrderMapper;
import com.eif.ftc.dal.model.FundTransOrder;
import com.eif.ftc.dal.model.FundTransOrderExample;
import com.eif.ftc.facade.code.FTCRespCode;
import com.eif.ftc.facade.fund.trans.enumeration.FundTransOrderStatus;
import com.eif.ftc.facade.fund.trans.enumeration.FundTransType;
import com.eif.ftc.integration.fis.FisIntService;
import com.eif.ftc.integration.goutong.GoutongIntService;
import com.eif.ftc.integration.member.MemberIntService;
import com.eif.ftc.integration.mq.MQMessageSender;
import com.eif.ftc.integration.ofc.OfcIntService;
import com.eif.ftc.service.constant.CheckRuleNameType;
import com.eif.ftc.service.mq.FundMQConsumerService;
import com.eif.ftc.service.trans.FundChargeService;
import com.eif.ftc.service.trans.action.FundBuyingPayActionService;
import com.eif.ftc.service.trans.action.FundSettingsActionService;
import com.eif.ftc.service.transfer.FundTransfereeService;
import com.eif.ftc.util.exception.BusinessException;
import com.eif.goutong.facade.constant.BusinessIDType;
import com.eif.goutong.facade.constant.ParamKeys;
import com.eif.inspection.facade.code.HandleStatus;
import com.eif.inspection.facade.mq.MQInsHandleDataInfo;
import com.eif.inspection.facade.mq.MQInsHandleFeedback;
import com.eif.member.facade.pkg.response.QueryMemberInfoResponse;
import com.eif.setting.facade.code.CashOutTypeCode;
import com.eif.setting.facade.request.CusTransResultRequest;
import com.eif.setting.facade.service.dto.CusTransResponseDto;
import com.eif.transcore.facade.dto.enumeration.TransCode;
import com.eif.transcore.facade.dto.enumeration.TransactionStatus;
import com.eif.transcore.facade.mq.MQTransInfoBean;
import com.eif.transcore.facade.response.CreateTransResponse;
import com.lts.core.commons.utils.DateUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.*;

/**
 * Created by bohan on 9/29/16.
 */
@Service("fundMQConsumerService")
public class FundMQConsumerServiceImpl implements FundMQConsumerService {

    static final Logger logger = LoggerFactory.getLogger(FundMQConsumerServiceImpl.class);

    @Autowired
    FundTransOrderMapper fundTransOrderMapper;

    @Resource
    FisIntService fisIntService;

    @Resource
    MemberIntService memberIntService;

    @Autowired
    FundChargeService fundChargeService;

    @Autowired
    FundSettingsActionService fundSettingsActionService;

    @Autowired
    MQMessageSender mqMessageSender;

    @Autowired
    FundBuyingPayActionService fundBuyingPayActionService;

    @Autowired
    FundTransfereeService fundTransfereeService;
    
    @Resource
    OfcIntService ofcIntService;

    @Resource
    GoutongIntService goutongIntService;

	@Override
	public void consumeTransCoreCreateMessage(CreateTransResponse message) {
		// 基础信息获取
        FundTransOrder queryOrder = new FundTransOrder();
        queryOrder.setFundTransOrderNo(message.getBizOrderNo());
        List<FundTransOrder> targetOrderList = fundTransOrderMapper.select(queryOrder);
        if (targetOrderList.size() == 0) {
            throw new BusinessException(FTCRespCode.ERR_FUND_TRANS_ORDER_NOT_FOUND.getMessage(), FTCRespCode.ERR_FUND_TRANS_ORDER_NOT_FOUND.getCode());
        }
        FundTransOrder targetOrder = targetOrderList.get(0);
        
	   	Integer status = null;
        Integer transType = message.getBizTransType();
        if (message.getTransCode().equals(TransCode.SUBSCRIBE)) {//申购、认购
        	if (transType.equals(FundTransType.PURCHASING)) {
        		status = FundTransOrderStatus.PAY_SUC;
        	} else if (transType.equals(FundTransType.SUBSCRIBING)) {
        		status = FundTransOrderStatus.TA_TRANS_CFM;
        	}
        } else if (message.getTransCode().equals(TransCode.REDEMPTION)) {//赎回
        	if (transType.equals(FundTransType.REDEEMING)) {
        		status = FundTransOrderStatus.REDEEMING_APPLIED;
        	}
        }

        if (status != null) {
        	if (message.getTransactionStatus() == TransactionStatus.CREATED) {
		        //更新交易单
		        FundTransOrder transOrder = new FundTransOrder();
			   	transOrder.setTranscoreTransNo(message.getTransNo());
			   	FundTransOrderExample example = new FundTransOrderExample();
			   	FundTransOrderExample.Criteria criteria = example.createCriteria();
			   	criteria.andFundTransOrderNoEqualTo(targetOrder.getFundTransOrderNo());
			   	criteria.andStatusEqualTo(status);
			   	
			   	if (0 == fundTransOrderMapper.updateByExampleSelective(transOrder, example)) {
			   		throw new BusinessException(CommonRspCode.DB_ERROR.getMessage(), CommonRspCode.DB_ERROR.getCode());
			   	}
        	} else if (message.getTransactionStatus() == TransactionStatus.CREATE_FAILED) {
        		logger.error("create transaction order failed, fundTransOrderNo: " + targetOrder.getFundTransOrderNo());
        	}
        }
	}
	
    public void consumeTransCoreStatusMessage(MQTransInfoBean message) {
        // 基础信息获取
        FundTransOrder queryOrder = new FundTransOrder();
        queryOrder.setFundTransOrderNo(message.getBizOrderNo());
        List<FundTransOrder> targetOrderList = fundTransOrderMapper.select(queryOrder);
        if (targetOrderList.size() == 0) {
            throw new BusinessException(FTCRespCode.ERR_FUND_TRANS_ORDER_NOT_FOUND.getMessage(), FTCRespCode.ERR_FUND_TRANS_ORDER_NOT_FOUND.getCode());
        }
        FundTransOrder targetOrder = targetOrderList.get(0);

        Integer transType = message.getBizTransType();
        if ((transType.equals(FundTransType.PURCHASING) || transType.equals(FundTransType.SUBSCRIBING))) {
             if (message.getTransCode().equals(TransCode.CHARGE)) {//扣款结果
	             if (message.getTransStatus() == TransactionStatus.SETTLED) {
	                 fundChargeService.chargeSuccess(message,targetOrder);
	             } else if (message.getTransStatus() == TransactionStatus.SETTLE_FAILED) {
	                 fundChargeService.chargeFailed(message, targetOrder);
	             }
             }
        }
    }

    // 消费出款中心消息
    public void consumeSettingsMessage(CusTransResultRequest message) {
    	if (message.getTransCode().equals(TransCode.REFUND) &&
    			message.getBizTransType() == FundTransType.TRANSFEREE) { // 二级市场退款
    		consumeTransferRefundMessage(message);
    		return;
    	}
    	
        // 基础信息获取
        FundTransOrder queryOrder = new FundTransOrder();
        queryOrder.setFundTransOrderNo(message.getBizOrderNo());
        List<FundTransOrder> targetOrderList = fundTransOrderMapper.select(queryOrder);
        if (targetOrderList.size() == 0) {
            throw new BusinessException(FTCRespCode.ERR_FUND_TRANS_ORDER_NOT_FOUND.getMessage(), FTCRespCode.ERR_FUND_TRANS_ORDER_NOT_FOUND.getCode());
        }

        FundTransOrder targetOrder = targetOrderList.get(0);

        //退款结果处理
        if (message.getTransCode().equals(TransCode.REFUND)) {
            consumeRefundMessage(targetOrder, message);
        } else {	//赎回出款消息
            consumeWithdrawMessage(targetOrder, message);
        }
    }

    public void consumeSettingCreateOrderResponse(List<CusTransResponseDto> cusTransResponseDtoList) {
        if (CollectionUtils.isEmpty(cusTransResponseDtoList)) {
            return;
        }
        
        CusTransResponseDto cusTransResponseDto = cusTransResponseDtoList.get(0);
    	if (cusTransResponseDto == null) {
    		logger.error("No CusTransResponseDto exist in BatchCusTransResponse.");
    		return;
    	}
		if (cusTransResponseDto.getTransCode().equals(TransCode.REFUND)) {//退款
	        for (CusTransResponseDto resp : cusTransResponseDtoList) {
	            if (!resp.getRespCode().equals(CommonRspCode.SUCCESS.getCode())) {
	                logger.error("create settings order is failed, cusTransResponseDtoList: " 
	                		+ JSON.toJSONString(cusTransResponseDtoList));
	
	                throw new BusinessException(resp.getMsg(), resp.getRespCode());
	            }
	        }
		}
    }

    // 消费退款消息
    private void consumeRefundMessage(FundTransOrder transOrder, CusTransResultRequest message) {
        int status = FundTransOrderStatus.REFUND_FAILED;
        if (message.getTransStatus() == TransactionStatus.SETTLED) {
            status = FundTransOrderStatus.REFUND_SUC;
        }
        transOrder.setStatus(status);
        // 获取需要的用户, 产品信息
        Date curDate = DateUtils.now();
        QueryProdTransInfoV2Resp queryProdTransInfoResp = fisIntService
                .queryProdTransInfoV2(transOrder.getProductId());
        String productName;
        if(queryProdTransInfoResp.getCloseType().equals(ProductCloseType.CLOSE))
        {
            productName = queryProdTransInfoResp.getNormalProdTransInfo().getProductName();
        }else{
            productName = queryProdTransInfoResp.getCurrentProdTransInfo().getProductName();
        }
        fundSettingsActionService.updateRefundFundTransOrder(curDate, transOrder, productName);
    }

    /**
     * 处理受让退款结果
     * @param message
     */
    private void consumeTransferRefundMessage(CusTransResultRequest message) {
    	fundTransfereeService.handleRefundResultMessage(message);
    }
    
    // 消费赎回出款消息
    private void consumeWithdrawMessage(FundTransOrder transOrder, CusTransResultRequest message) {
        // 获取需要的用户, 产品信息
        Date curDate = DateUtils.now();
        QueryProdTransInfoV2Resp queryProdTransInfoResp = fisIntService
                .queryProdTransInfoV2(transOrder.getProductId());
        
        QueryMemberInfoResponse memberInfoResponse = memberIntService.queryMembersInfoByMemberNo(transOrder.getMemberNo());
        
        String productName;
        if(queryProdTransInfoResp.getCloseType().equals(ProductCloseType.CLOSE))
        {
            productName = queryProdTransInfoResp.getNormalProdTransInfo().getProductName();
        }else{
            productName = queryProdTransInfoResp.getCurrentProdTransInfo().getProductName();
        }

        int transStatus = message.getTransStatus();
        // 赎回出款
        if (transStatus == TransactionStatus.SETTLED) {
            transOrder.setStatus(FundTransOrderStatus.WITHDRAW_SUC);
            transOrder.setFinishTime(curDate);
        } else if (transStatus == TransactionStatus.SETTLE_FAILED) {
            transOrder.setStatus(FundTransOrderStatus.WITHDRAW_FAIL);
        }

        // 只处理活期
        if (!queryProdTransInfoResp.getCloseType().equals(ProductCloseType.CLOSE)) {
            // 更新交易单状态
            List<Integer> prevStatusList = new ArrayList<>();
            prevStatusList.add(FundTransOrderStatus.TA_TRANS_SUC);
            fundBuyingPayActionService.updatePayFundTransOrderTransaction(transOrder, prevStatusList, curDate);
            ofcIntService.updateBusinessOrderItemStatusAsync(transOrder, transOrder.getStatus(), productName);

            if (transOrder.getStatus() == FundTransOrderStatus.WITHDRAW_SUC) {
            	int bizId = BusinessIDType.CASH_CUR_BALANCE_SUCCESS.getId();
            	if (message.getCashOutType().equals(CashOutTypeCode.OFFLINE)) {//到银行卡
            		bizId = BusinessIDType.CASH_CUR_CARD_SUCCESS.getId();
            	}
            	
            	String strDate = DateUtils.formatYMD(transOrder.getCreateTime());
            	String strAmount = String.format("%.2f", transOrder.getFundTransAmount());
            	
            	// 短信
            	Map<String, String> smsParamMap = new HashMap<>();
            	smsParamMap.put(ParamKeys.DATE.getName(), strDate);
            	smsParamMap.put(ParamKeys.PRODUCT_NAME.getName(), productName);
            	smsParamMap.put(ParamKeys.AMOUNT.getName(), strAmount);
            	goutongIntService.sendSmsAsync(BizChannel.INNER, bizId, smsParamMap, memberInfoResponse.getUserInfoBean().getVerifiedMobile());
            	
                // 站内信
//                List<String> inboxParams = new ArrayList<>();
//                inboxParams.add(strDate);
//                inboxParams.add(productName);
//                inboxParams.add(strAmount);
            	goutongIntService.sendInboxMessage(BizChannel.INNER, bizId, smsParamMap, transOrder.getMemberNo());
//                SendInboxMsgByBizReq inboxMsg = new SendInboxMsgByBizReq();
//                SendInboxMsgByBizBean inboxMsgBean = new SendInboxMsgByBizBean();
//                inboxMsgBean.setBusinessId(bizId);
//                inboxMsgBean.setContentType(InboxMsgContentType.TEXT_ONLY_MSG);
//                inboxMsgBean.setViewChannel(InboxMsgViewChannel.ALL);
//                List<String> params = new ArrayList<>();
//                params.add(String.format("%.2f", transOrder.getFundTransAmount()));
//                inboxMsgBean.setParams(params);
//                inboxMsgBean.setSendType(InboxMsgSendType.SINGLE_SEND);
//                Set<String> memberNos = new HashSet<String>();
//                memberNos.add(transOrder.getMemberNo());
//                inboxMsg.setMemberNos(memberNos);
//                inboxMsg.setSendInboxMsgBean(inboxMsgBean);
//                mqMessageSender.sendInboxMsg(inboxMsg, BizChannel.INNER);
            }
        }
    }

    /**
     * fis库存差异，处理inspection系统发送的库存差异对账mq消息
     * ftc支付成功（status:6 9 11）----> 调用fis系统解冻并真实扣减接口
     * ftc支付失败（status:7 10 12 13 18 19 26）----> 调用fis系统解冻并归还库存接口
     * @param message
     */
    @Override
    public void consumeInspectionInventoryDiffMessage(MQInsHandleDataInfo message) {
        logger.info("FundMQConsumerServiceImpl.consumeInspectionInventoryDiffMessage message={}", JSON.toJSONString(message));

        if (message == null || StringUtils.isBlank(message.getCheckRuleName())) {
            return;
        }

        String dataMarker = message.getDataMarker();
        String frozenCode = "";
        if (StringUtils.isNotBlank(dataMarker)) {
            frozenCode = dataMarker.substring(dataMarker.indexOf("=")+1);
        }

        //处理结果返回给inspection
        MQInsHandleFeedback mqInsHandleFeedback = new MQInsHandleFeedback();
        mqInsHandleFeedback.setHandleDataNo(message.getHandleDataNo());
        mqInsHandleFeedback.setHandler("002");
        if(CheckRuleNameType.PAY_SUCC_STATUS_CHECK_RULE_NAME.
                equals(message.getCheckRuleName())){//支付成功对账规则
            try {
                UnfreezeAndDeductionProdInventoryReq req = new UnfreezeAndDeductionProdInventoryReq();
                req.setToken(frozenCode);
                fisIntService.unfreezeAndDeductionProdInventory(req);
                mqInsHandleFeedback.setHandleStatus(HandleStatus.handled);
                mqInsHandleFeedback.setHandleMsg("处理成功");
            } catch (Exception e) {
                logger.error("FundMQConsumerServiceImpl.consumeInspectionInventoryDiffMessage unfreezeAndDeductionProdInventory error", e);
                mqInsHandleFeedback.setHandleStatus(HandleStatus.handleFailed);
                if (e instanceof BusinessException) {
                    mqInsHandleFeedback.setHandleMsg(((BusinessException) e).getCode() + ((BusinessException) e).getMsg());
                }
            } finally {
                mqMessageSender.sendInventoryDiffHandleMessage(mqInsHandleFeedback);
            }
        } else if(CheckRuleNameType.PAY_FAIL_STATUS_CHECK_RULE_NAME.
                equals(message.getCheckRuleName())){//支付失败对账规则
            try {
                UnfreezeProdInventoryReq req = new UnfreezeProdInventoryReq();
                req.setToken(frozenCode);
                fisIntService.unfreezeProdInventory(req);
                mqInsHandleFeedback.setHandleStatus(HandleStatus.handled);
                mqInsHandleFeedback.setHandleMsg("处理成功");
            } catch (Exception e) {
                logger.error("FundMQConsumerServiceImpl.consumeInspectionInventoryDiffMessage unfreezeProdInventory error", e);
                mqInsHandleFeedback.setHandleStatus(HandleStatus.handleFailed);
                if (e instanceof BusinessException) {
                    mqInsHandleFeedback.setHandleMsg(((BusinessException) e).getCode() + ((BusinessException) e).getMsg());
                }
            } finally {
                mqMessageSender.sendInventoryDiffHandleMessage(mqInsHandleFeedback);
            }
        }
    }

}
