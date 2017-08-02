package com.eif.ftc.integration.impl.ofc;

import com.alibaba.fastjson.JSON;
import com.eif.framework.common.utils.code.CommonRspCode;
import com.eif.framework.common.utils.constant.BizSysCode;
import com.eif.framework.common.utils.constant.CurrencyISOCode;
import com.eif.ftc.dal.model.FundTransOrder;
import com.eif.ftc.dal.model.FundTransfereeOrder;
import com.eif.ftc.dal.model.FundTransferorOrder;
import com.eif.ftc.facade.code.FTCRespCode;
import com.eif.ftc.facade.fund.trans.enumeration.FundTransType;
import com.eif.ftc.facade.mq.dto.MQBusinessOrderItemMessage;
import com.eif.ftc.integration.mq.MQMessageSender;
import com.eif.ftc.integration.ofc.OfcIntService;
import com.eif.ftc.util.constant.TransCoreConstant;
import com.eif.ftc.util.exception.BusinessException;
import com.eif.ftc.util.uuid.UUIDGenerator;
import com.eif.ofc.facade.OrderManagementFacade;
import com.eif.ofc.facade.dto.bean.CreateBusinessOrderItemBean;
import com.eif.ofc.facade.dto.enumeration.BusinessOrderItemType;
import com.eif.ofc.facade.request.BatchCreateOrderItemRequest;
import com.eif.ofc.facade.request.CreateOrderItemRequest;
import com.eif.ofc.facade.response.BatchCreateOrderItemResponse;
import com.eif.ofc.facade.response.CreateOrderItemResponse;
import com.eif.transcore.facade.dto.bean.TransactionPaymentOptionBean;

import tk.mybatis.mapper.util.StringUtil;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by bohan on 1/11/16.
 */
@Service("ofcIntService")
public class OfcIntServiceImpl implements OfcIntService {


    @Resource
    OrderManagementFacade orderManagementFacade;

    @Autowired
    MQMessageSender mqMessageSender;

    private static Logger logger = LoggerFactory.getLogger(OfcIntServiceImpl.class);

    public CreateOrderItemResponse createBusinessOrderItem(FundTransOrder fundTransOrder,
    		int businessOrderItemType, String extField, String marketField, String productName) {
        CreateOrderItemRequest createOrderItemRequest = new CreateOrderItemRequest();
        CreateBusinessOrderItemBean createBusinessOrderItemBean = new CreateBusinessOrderItemBean();
        createBusinessOrderItemBean.setTrackingNo(UUIDGenerator.gen());
        createBusinessOrderItemBean.setAmount(fundTransOrder.getFundTransAmount());
        createBusinessOrderItemBean.setBizChannel(fundTransOrder.getBizChannel());
        createBusinessOrderItemBean.setBizOrderNo(fundTransOrder.getFundTransOrderNo());
        createBusinessOrderItemBean.setBizProductId(fundTransOrder.getProductId());
        createBusinessOrderItemBean.setBizSysCode(BizSysCode.FTC_SYSTEM);
        createBusinessOrderItemBean.setBusinessOrderItemType(businessOrderItemType);
        createBusinessOrderItemBean.setRegulated(fundTransOrder.getIsAdjusted());
        createBusinessOrderItemBean.setPaymentMethods(fundTransOrder.getPayMethod());
        createBusinessOrderItemBean.setCurrency(fundTransOrder.getCurrencyType());
        createBusinessOrderItemBean.setMerMemberNo(fundTransOrder.getMerMemberNo());
        createBusinessOrderItemBean.setMemberNo(fundTransOrder.getMemberNo());
        createBusinessOrderItemBean.setBizProductName(productName);
        createBusinessOrderItemBean.setExtField(extField);
        createBusinessOrderItemBean.setDiscountAmount(fundTransOrder.getDiscountAmount());
        createOrderItemRequest.setCreateBusinessOrderItem(createBusinessOrderItemBean);
        createBusinessOrderItemBean.setBizOrderStatus(fundTransOrder.getStatus());
        createBusinessOrderItemBean.setMarketField(marketField);
        
        // 赎回追加回款方式,此时参数extField为空
        if (businessOrderItemType == BusinessOrderItemType.REDEEM) {
        	Map<String, Object> extFieldMap = JSON.parseObject(fundTransOrder.getExtField());
        	
        	List<TransactionPaymentOptionBean> paymentOptionList = new ArrayList<>();
        	TransactionPaymentOptionBean paymentOption = new TransactionPaymentOptionBean();
        	paymentOption.setAmount(fundTransOrder.getFundTransAmount());
        	paymentOption.setPaymentInstrumentType(Integer.parseInt(extFieldMap.get(TransCoreConstant.PAYMENT_INSTRMENT_TYPE).toString()));
        	if (extFieldMap.containsKey(TransCoreConstant.PAYMENT_INSTRMENT_NO)) {
        		paymentOption.setPaymentInstrumentNo(extFieldMap.get(TransCoreConstant.PAYMENT_INSTRMENT_NO).toString());
        	}
        	paymentOptionList.add(paymentOption);
        	
        	createBusinessOrderItemBean.setPaymentMethods(JSON.toJSONString(paymentOptionList));
        }

        CreateOrderItemResponse createOrderItemResponse = orderManagementFacade.createBusinessOrderItem(createOrderItemRequest);

        if (createOrderItemResponse.isSuccess()) {
            return createOrderItemResponse;
        } else {
            throw new BusinessException(FTCRespCode.ERR_FUND_OFC_CALL_CREATE.getMessage(), FTCRespCode.ERR_FUND_OFC_CALL_CREATE.getCode());
        }

    }

    public void updateBusinessOrderItem(FundTransOrder targetOrder, int businessOrderItemType, int status, String productName) {
        updateBusinessOrderItem(targetOrder, businessOrderItemType, status, productName, null);
    }

    public void updateBusinessOrderItem(FundTransOrder targetOrder, int businessOrderItemType, int status, String productName, String reasonCode) {
        MQBusinessOrderItemMessage mqBusinessOrderItemMessage = new MQBusinessOrderItemMessage();
        mqBusinessOrderItemMessage.setBizChannel(targetOrder.getBizChannel());
        mqBusinessOrderItemMessage.setAmount(targetOrder.getFundTransAmount());
        mqBusinessOrderItemMessage.setTrackingNo(UUIDGenerator.gen());
        mqBusinessOrderItemMessage.setBizOrderNo(targetOrder.getFundTransOrderNo());
        mqBusinessOrderItemMessage.setBizOrderStatus(status);
        mqBusinessOrderItemMessage.setBizProductId(targetOrder.getProductId());
        String prodName = productName;
        if (!StringUtil.isEmpty(targetOrder.getRefFundSubCode())) {
        	prodName = prodName + targetOrder.getRefFundSubCode() + "号";
        }
        mqBusinessOrderItemMessage.setBizProductName(prodName);
        mqBusinessOrderItemMessage.setBizSysCode(BizSysCode.FTC_SYSTEM);
        mqBusinessOrderItemMessage.setBusinessOrderItemType(businessOrderItemType);
        mqBusinessOrderItemMessage.setMemberNo(targetOrder.getMemberNo());
        mqBusinessOrderItemMessage.setPaymentMethods(targetOrder.getPayMethod());
        mqBusinessOrderItemMessage.setCurrency(targetOrder.getCurrencyType());
        mqBusinessOrderItemMessage.setMerMemberNo(targetOrder.getMerMemberNo());
        mqBusinessOrderItemMessage.setRegulated(targetOrder.getIsAdjusted());
        mqBusinessOrderItemMessage.setBusinessOrderItemNo(targetOrder.getBusinessOrderItemNo());
        mqBusinessOrderItemMessage.setReasonCode(reasonCode);

        mqMessageSender.sendBusinessOrderItemMsg(mqBusinessOrderItemMessage);
    }

    public void updateBusinessOrderItemStatusAsync(FundTransOrder targetOrder, int fundTransOrderStatus, String productName) {
        updateBusinessOrderItemStatusAsync(targetOrder, fundTransOrderStatus, productName, null);
    }

    public void updateBusinessOrderItemStatusAsync(FundTransOrder targetOrder, int fundTransOrderStatus, String productName, String reasonCode) {
        int businessOrderItemType;
        if (targetOrder.getFundTransType() == FundTransType.PURCHASING) {
            businessOrderItemType = BusinessOrderItemType.PURCHASE;
        } else {
            businessOrderItemType = BusinessOrderItemType.SUBSCRIBE;
        }

        // OFC
        updateBusinessOrderItem(targetOrder, businessOrderItemType, fundTransOrderStatus, productName, reasonCode);
    }


    public BatchCreateOrderItemResponse batchCreateBusinessOrderItems(List<CreateBusinessOrderItemBean> createBusinessOrderItemBeanList) {
        BatchCreateOrderItemRequest request = new BatchCreateOrderItemRequest();
        request.setCreateBusinessOrderItemBeanList(createBusinessOrderItemBeanList);
        BatchCreateOrderItemResponse resp = orderManagementFacade.batchCreateBusinessOrderItems(request);
        if (!resp.getRespCode().equals(CommonRspCode.SUCCESS.getCode())) {
            logger.error("place ofc dividend business order failed, errmsg: " + resp.getMsg());
            throw new BusinessException(resp.getMsg(), resp.getRespCode());
        }

        return resp;
    }

	@Override
	public String createBusinessOrderItem(FundTransferorOrder fundTransferorOder, String productName) {
        CreateOrderItemRequest request = new CreateOrderItemRequest();
        CreateBusinessOrderItemBean createBusinessOrderItemBean = new CreateBusinessOrderItemBean();
        createBusinessOrderItemBean.setTrackingNo(UUIDGenerator.gen());
        createBusinessOrderItemBean.setAmount(fundTransferorOder.getFundTransferAmount());
        createBusinessOrderItemBean.setCurrency(CurrencyISOCode.CHINA);
        createBusinessOrderItemBean.setBizChannel(fundTransferorOder.getBizChannel());
        createBusinessOrderItemBean.setBizOrderNo(fundTransferorOder.getFundTransferorOrderNo());
        createBusinessOrderItemBean.setBizProductId(fundTransferorOder.getProductId());
        createBusinessOrderItemBean.setBizSysCode(BizSysCode.FTC_SYSTEM);
        createBusinessOrderItemBean.setBusinessOrderItemType(BusinessOrderItemType.TRANSFEROR);
        createBusinessOrderItemBean.setRegulated(false);
        createBusinessOrderItemBean.setMemberNo(fundTransferorOder.getMemberNo());
        createBusinessOrderItemBean.setBizProductName(productName);
        Map<String, Object> extMap = new HashMap<>();
        if (!StringUtils.isEmpty(fundTransferorOder.getExtField())) {
        	extMap = JSON.parseObject(fundTransferorOder.getExtField());
        }
    	extMap.put("transferFee", fundTransferorOder.getTransferorFee().toString());
    	createBusinessOrderItemBean.setExtField(JSON.toJSONString(extMap));
        createBusinessOrderItemBean.setDiscountAmount(BigDecimal.ZERO);
        createBusinessOrderItemBean.setBizOrderStatus(fundTransferorOder.getStatus());
        request.setCreateBusinessOrderItem(createBusinessOrderItemBean);

        CreateOrderItemResponse resp = orderManagementFacade.createBusinessOrderItem(request);
        if (!resp.isSuccess() || resp.getResult() == null) {
            throw new BusinessException(FTCRespCode.ERR_FUND_OFC_CALL_CREATE.getMessage(), 
            		FTCRespCode.ERR_FUND_OFC_CALL_CREATE.getCode());
        }
        return resp.getResult().getBusinessOrderItemNo();
	}

	@Override
	public void updateBusinessOrderItemAsync(FundTransferorOrder fundTransferorOder, String productClearAccount) {
        mqMessageSender.sendBusinessOrderItemMsg(buildBusinessOrderItemMessage(fundTransferorOder, productClearAccount));
	}

	@Override
	public MQBusinessOrderItemMessage buildBusinessOrderItemMessage(
			FundTransferorOrder fundTransferorOder, String productClearAccount) {
		MQBusinessOrderItemMessage mqBusinessOrderItemMessage = new MQBusinessOrderItemMessage();
        mqBusinessOrderItemMessage.setAmount(fundTransferorOder.getFundTransferAmount());
        mqBusinessOrderItemMessage.setTrackingNo(UUIDGenerator.gen());
        mqBusinessOrderItemMessage.setBizOrderNo(fundTransferorOder.getFundTransferorOrderNo());
        mqBusinessOrderItemMessage.setBusinessOrderItemNo(fundTransferorOder.getBusinessOrderItemNo());
        mqBusinessOrderItemMessage.setBizOrderStatus(fundTransferorOder.getStatus());
        Map<String, Object> extMap = new HashMap<>();
        if (!StringUtils.isEmpty(fundTransferorOder.getExtField())) {
        	extMap = JSON.parseObject(fundTransferorOder.getExtField());
        }
    	extMap.put("transferFee", fundTransferorOder.getTransferorFee().toString());
    	mqBusinessOrderItemMessage.setExtField(JSON.toJSONString(extMap));
    	
//    	// 出款方式
//    	if (!StringUtils.isEmpty(productClearAccount)) {
//	    	List<TransactionPaymentOptionBean> paymentList = new ArrayList<>();
//	    	TransactionPaymentOptionBean payment = new TransactionPaymentOptionBean();
//	    	payment.setPaymentInstrumentType(PaymentInstrumentType.MEMBER_BALANCE);
//	        payment.setAmount(fundTransferorOder.getFundTransferAmount());
//	        //借贷账户
//	        payment.setCreditMemberNo(fundTransferorOder.getMemberNo());
//	        payment.setDebitMemberNo(productClearAccount);
//	        paymentList.add(payment);
//	        mqBusinessOrderItemMessage.setPaymentMethods(JSON.toJSONString(paymentList));
//    	}
        
		return mqBusinessOrderItemMessage;
	}

	@Override
	public String createBusinessOrderItem(FundTransfereeOrder fundTransfereeOder, String productName) {
		CreateOrderItemRequest request = new CreateOrderItemRequest();
        CreateBusinessOrderItemBean createBusinessOrderItemBean = new CreateBusinessOrderItemBean();
        createBusinessOrderItemBean.setTrackingNo(UUIDGenerator.gen());
        createBusinessOrderItemBean.setAmount(fundTransfereeOder.getFundTransferAmount());
        createBusinessOrderItemBean.setBizChannel(fundTransfereeOder.getBizChannel());
        createBusinessOrderItemBean.setBizOrderNo(fundTransfereeOder.getFundTransfereeOrderNo());
        createBusinessOrderItemBean.setBizProductId(fundTransfereeOder.getMktProductId());
        createBusinessOrderItemBean.setBizSysCode(BizSysCode.FTC_SYSTEM);
        createBusinessOrderItemBean.setBusinessOrderItemType(BusinessOrderItemType.TRANSFEREE);
        createBusinessOrderItemBean.setRegulated(false);
        createBusinessOrderItemBean.setPaymentMethods(fundTransfereeOder.getPayMethod());
        createBusinessOrderItemBean.setCurrency(fundTransfereeOder.getCurrencyType());
        createBusinessOrderItemBean.setMemberNo(fundTransfereeOder.getMemberNo());
        createBusinessOrderItemBean.setBizProductName(productName);
//        Map<String, Object> extMap = new HashMap<>();
//        if (!StringUtils.isEmpty(fundTransfereeOder.getExtField())) {
//        	extMap = JSON.parseObject(fundTransfereeOder.getExtField());
//        }
//    	extMap.put("transferFee", fundTransfereeOder.getFee().toString());
//        createBusinessOrderItemBean.setExtField(JSON.toJSONString(extMap));
        createBusinessOrderItemBean.setDiscountAmount(fundTransfereeOder.getDiscountAmount());
        createBusinessOrderItemBean.setBizOrderStatus(fundTransfereeOder.getStatus());
        request.setCreateBusinessOrderItem(createBusinessOrderItemBean);

        CreateOrderItemResponse resp = orderManagementFacade.createBusinessOrderItem(request);
        if (!resp.isSuccess() || resp.getResult() == null) {
            throw new BusinessException(FTCRespCode.ERR_FUND_OFC_CALL_CREATE.getMessage(), 
            		FTCRespCode.ERR_FUND_OFC_CALL_CREATE.getCode());
        }
        return resp.getResult().getBusinessOrderItemNo();
	}

	@Override
	public void updateBusinessOrderItemAsync(FundTransfereeOrder fundTransfereeOder) {
        mqMessageSender.sendBusinessOrderItemMsg(buildBusinessOrderItemMessage(fundTransfereeOder));
	}

	@Override
	public void batchUpdateBusinessOrderItemAsync(List<MQBusinessOrderItemMessage> businessOrderItemList) {
		if (CollectionUtils.isEmpty(businessOrderItemList)) return;
	
		for (MQBusinessOrderItemMessage msg : businessOrderItemList) {
			mqMessageSender.sendBusinessOrderItemMsg(msg);
		}
	}

	@Override
	public MQBusinessOrderItemMessage buildBusinessOrderItemMessage(
			FundTransfereeOrder fundTransfereeOder) {
		MQBusinessOrderItemMessage mqBusinessOrderItemMessage = new MQBusinessOrderItemMessage();
        mqBusinessOrderItemMessage.setTrackingNo(UUIDGenerator.gen());
        mqBusinessOrderItemMessage.setBizOrderNo(fundTransfereeOder.getFundTransfereeOrderNo());
        mqBusinessOrderItemMessage.setBusinessOrderItemNo(fundTransfereeOder.getBusinessOrderItemNo());
        mqBusinessOrderItemMessage.setAmount(fundTransfereeOder.getFundTransferAmount());
        mqBusinessOrderItemMessage.setDiscountAmount(mqBusinessOrderItemMessage.getDiscountAmount());
        mqBusinessOrderItemMessage.setBizOrderStatus(fundTransfereeOder.getStatus());
        mqBusinessOrderItemMessage.setPaymentMethods(fundTransfereeOder.getPayMethod());
        mqBusinessOrderItemMessage.setCurrency(fundTransfereeOder.getCurrencyType());
        Map<String, Object> extMap = new HashMap<>();
        if (!StringUtils.isEmpty(fundTransfereeOder.getExtField())) {
        	extMap = JSON.parseObject(fundTransfereeOder.getExtField());
        }
    	extMap.put("transferFee", fundTransfereeOder.getFee().toString());
    	mqBusinessOrderItemMessage.setExtField(JSON.toJSONString(extMap));
    	if (!StringUtil.isEmpty(fundTransfereeOder.getRemark())) {
    		mqBusinessOrderItemMessage.setReasonCode(fundTransfereeOder.getRemark());
    	}
        
		return mqBusinessOrderItemMessage;
	}

}
