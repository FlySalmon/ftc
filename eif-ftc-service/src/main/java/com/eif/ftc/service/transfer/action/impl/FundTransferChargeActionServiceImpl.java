package com.eif.ftc.service.transfer.action.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eif.fis.facade.constant.TransStructureType;
import com.eif.fis.facade.dto.ftc.ProdInfo;
import com.eif.fis.facade.dto.ftc.ProdSummary;
import com.eif.framework.common.utils.code.CommonRspCode;
import com.eif.ftc.dal.dao.FundTransfereeOrderMapper;
import com.eif.ftc.dal.dao.FundTransferorOrderMapper;
import com.eif.ftc.dal.model.FundTransferApplyToExchange;
import com.eif.ftc.dal.model.FundTransfereeOrder;
import com.eif.ftc.dal.model.FundTransferorOrder;
import com.eif.ftc.facade.code.FTCRespCode;
import com.eif.ftc.facade.fund.trans.enumeration.FundTransType;
import com.eif.ftc.facade.fund.transfer.enumeration.FundTransferExchangeStatus;
import com.eif.ftc.facade.fund.transfer.enumeration.FundTransfereeOrderStatus;
import com.eif.ftc.facade.fund.transfer.enumeration.FundTransferorOrderStatus;
import com.eif.ftc.integration.fis.FisIntService;
import com.eif.ftc.integration.goutong.GoutongIntService;
import com.eif.ftc.integration.market.MarketIntService;
import com.eif.ftc.integration.member.MemberIntService;
import com.eif.ftc.integration.ofc.OfcIntService;
import com.eif.ftc.integration.setting.SettingIntegrationService;
import com.eif.ftc.integration.transcore.TranscoreIntService;
import com.eif.ftc.service.constant.PayPartnerNoType;
import com.eif.ftc.service.transfer.action.FundTransferChargeActionService;
import com.eif.ftc.service.transfer.action.FundTransferOrderActionService;
import com.eif.ftc.service.transfer.exchange.processor.TransferApplyService;
import com.eif.ftc.util.exception.BusinessException;
import com.eif.goutong.facade.constant.BusinessIDType;
import com.eif.goutong.facade.constant.ParamKeys;
import com.eif.member.facade.pkg.dto.responseDTO.MemberIdentityBean;
import com.eif.setting.facade.service.dto.CusTransRequestDto;
import com.eif.transcore.facade.dto.bean.CreateTransDTO;
import com.eif.transcore.facade.dto.bean.TransactionPaymentOptionBean;
import com.eif.transcore.facade.dto.enumeration.PaymentInstrumentType;
import com.eif.transcore.facade.dto.enumeration.TransAttribute;
import com.eif.transcore.facade.dto.enumeration.TransCode;
import com.lts.core.commons.utils.DateUtils;

@Service("fundTransferChargeActionService")
public class FundTransferChargeActionServiceImpl implements FundTransferChargeActionService {

	private static Logger logger = LoggerFactory.getLogger(FundTransferChargeActionServiceImpl.class);

    @Resource
    private OfcIntService ofcIntService;

    @Resource
    private FisIntService fisIntService;

    @Resource
    private MemberIntService memberIntService;

    @Resource
    private TranscoreIntService transcoreIntService;

    @Resource
    SettingIntegrationService settingIntService;

    @Resource
    MarketIntService marketIntService;

    @Resource
    GoutongIntService goutongIntService;

    @Autowired
    private FundTransferOrderActionService fundTransferOrderActionService;

    @Autowired
    private TransferApplyService transferApplyService;
    
    @Autowired
    private FundTransfereeOrderMapper fundTransfereeOrderMapper;

    @Autowired
    private FundTransferorOrderMapper fundTransferorOrderMapper;

	@Override
	public void chargeSuccess(FundTransfereeOrder transfereeOrder, ProdInfo product) {
		// 更新受让单状态
		FundTransfereeOrder updateOrder = new FundTransfereeOrder();
		updateOrder.setId(transfereeOrder.getId());
		updateOrder.setFundTransfereeOrderNo(transfereeOrder.getFundTransfereeOrderNo());
		updateOrder.setTranscoreTransNo(transfereeOrder.getTranscoreTransNo());
		updateOrder.setStatus(FundTransfereeOrderStatus.PAYING);//期望状态
		try {
			fundTransferOrderActionService.updateFundTransfereeOrder(updateOrder, FundTransfereeOrderStatus.PAY_SUC);
		} catch (BusinessException be) {
			// 并发控制
			FundTransfereeOrder queryOrder = new FundTransfereeOrder();
			queryOrder.setFundTransfereeOrderNo(transfereeOrder.getFundTransfereeOrderNo());
			FundTransfereeOrder order = fundTransfereeOrderMapper.selectOne(queryOrder);
			// 订单过期关闭直接退款
			if (order.getStatus() == FundTransfereeOrderStatus.CLOSE_BY_EXPIRY) {
				logger.error("Refund: transfereeOrder colsed by expiried, transfereeOrderNO: {}" 
						, transfereeOrder.getFundTransfereeOrderNo());
				
				order.setTranscoreTransNo(transfereeOrder.getTranscoreTransNo());
	            doRefund(order, product.getProductName());
			}
            return;
		}
		transfereeOrder.setStatus(FundTransfereeOrderStatus.PAY_SUC);
		transfereeOrder.setUpdateTime(updateOrder.getUpdateTime());
		
		//获取产品结构信息
		ProdSummary productMix = fisIntService.getCloseProductMix(transfereeOrder.getMktProductId());
		if (productMix.getTransStructureType().equals(TransStructureType.EXCHANGE)) {// 非定容交易所产品
			try {
				transferApplyService.applyTransfer(productMix.getExchangeCode(), 
						productMix.getExchangeProdNo(), transfereeOrder);
			} catch (Exception ex) {
				logger.error("Refund: sync transfer info to exchange failed, transfereeOrderNO: " 
						+ transfereeOrder.getFundTransfereeOrderNo(), ex);
				
				// 回滚扣减份额
				fisIntService.unfreezeMktProductInventoryAsync(transfereeOrder.getFrozenToken());
				
				try {
					// 回滚新手状态
					memberIntService.updateRookieStatusAndRetIsRookie(transfereeOrder.getMemberNo(), -1);
				} catch (Exception e) {
					logger.error("updateRookieStatusAndRetIsRookie error");
				}
				
				// 退款处理
	            doRefund(transfereeOrder, product.getProductName());
				return;
			}
		}
		
		try {
			// 确认并扣减产品份额
			fisIntService.unfreezeAndDeductMktProductInventory(transfereeOrder.getFrozenToken());
		} catch (Exception ex) {
			logger.error("Refund: unfreezeAndDeductMktProductInventory failed, transfereeOrderNO: {}" 
					, transfereeOrder.getFundTransfereeOrderNo());
			
			if (ex instanceof BusinessException) {
				if (((BusinessException) ex).getCode().equals(CommonRspCode.SYS_ERROR.getCode())) {
					// 超时失败补偿
					fisIntService.unfreezeAndDeductMktProductInventoryForCompensate(transfereeOrder.getFrozenToken());
				}
			}
			
			try {
				// 回滚新手状态
				memberIntService.updateRookieStatusAndRetIsRookie(transfereeOrder.getMemberNo(), -1);
			} catch (Exception e) {
				logger.error("updateRookieStatusAndRetIsRookie error");
			}

			if (productMix.getTransStructureType().equals(TransStructureType.EXCHANGE)) {// 非定容交易所产品
				//更新失败结果
				FundTransferApplyToExchange apply = transferApplyService.queryApplication(
						transfereeOrder.getFundTransfereeOrderNo(), FundTransferExchangeStatus.TRANSFER_APPLY_SUCC);
				transferApplyService.updateApplication(apply, FundTransferExchangeStatus.TRANSFER_FAIL, null);
			}
			
			// 退款处理
            doRefund(transfereeOrder, product.getProductName());
			return;
		}

		// 获取转让单
		FundTransferorOrder queryOrder = new FundTransferorOrder();
		queryOrder.setFundTransferorOrderNo(transfereeOrder.getRefFundTransferorOrderNo());
		FundTransferorOrder transferorOrder = fundTransferorOrderMapper.selectOne(queryOrder);
		if (transferorOrder == null) {
			logger.error("cannot find transferorOrder by refFundTransferorOrderNo: " +
					transfereeOrder.getRefFundTransferorOrderNo());
			throw new BusinessException(FTCRespCode.ERR_FUND_TRANSFEROR_ORDER_NOT_EXIST.getMessage(), 
					FTCRespCode.ERR_FUND_TRANSFEROR_ORDER_NOT_EXIST.getCode());
		}
		List<TransactionPaymentOptionBean> paymentOptions = new ArrayList<>();
		TransactionPaymentOptionBean paymentOption = new TransactionPaymentOptionBean();
		paymentOption.setAmount(transferorOrder.getFundTransferAmount());
		paymentOption.setPaymentInstrumentType(PaymentInstrumentType.MEMBER_BALANCE);
		paymentOption.setPayPartnerNo(PayPartnerNoType.EIF);
		paymentOptions.add(paymentOption);

        // 异步发送交易核心转账交易指令
        CreateTransDTO transDTO = transcoreIntService.buildTranscoreCreateTransDTO(
        		transferorOrder, TransCode.ASSIGNMENT, TransAttribute.FUND, 
        		paymentOptions, product.getProductName(), null);
        transDTO.setRefTransNo(transfereeOrder.getTranscoreTransNo()); //设置关联受让交易单号
		// 设置转账人信息（转账交易单传递转让人）
        transcoreIntService.createTranscoreOrderAsync(transDTO, null, transfereeOrder.getOperatorNo());
        
        // 投资成功通知market
        marketIntService.sendMemberInvestment(transfereeOrder, product);
	}

	@Override
	public void chargeFailed(FundTransfereeOrder transfereeOrder) {
		// 更新受让单状态
		int oldStatus = transfereeOrder.getStatus();
		FundTransfereeOrder updateOrder = new FundTransfereeOrder();
		updateOrder.setId(transfereeOrder.getId());
		updateOrder.setFundTransfereeOrderNo(transfereeOrder.getFundTransfereeOrderNo());
		updateOrder.setTranscoreTransNo(transfereeOrder.getTranscoreTransNo());
		if (oldStatus != FundTransfereeOrderStatus.PAYING &&
				oldStatus != FundTransfereeOrderStatus.NEED_PAY) {
			logger.warn("FundTransfereeOrder preStatus error, preStatus = " + oldStatus);
			return;
		}
		updateOrder.setStatus(oldStatus);//期望状态
		fundTransferOrderActionService.updateFundTransfereeOrder(updateOrder, FundTransfereeOrderStatus.PAY_FAILED);
		transfereeOrder.setStatus(FundTransfereeOrderStatus.PAY_FAILED);
		transfereeOrder.setUpdateTime(updateOrder.getUpdateTime());

		if (!StringUtils.isEmpty(transfereeOrder.getFrozenToken())) {
			// 解冻（取消）产品份额
			fisIntService.unfreezeMktProductInventoryAsync(transfereeOrder.getFrozenToken());
    		// 回滚新手状态
			memberIntService.updateRookieStatusAndRetIsRookie(transfereeOrder.getMemberNo(), -1);
		}
		
		if (oldStatus == FundTransfereeOrderStatus.PAYING) {//只有支付中才需要更新转让单状态
			// 更新转让单信息
			FundTransferorOrder queryOrder = new FundTransferorOrder();
			queryOrder.setFundTransferorOrderNo(transfereeOrder.getRefFundTransferorOrderNo());
			FundTransferorOrder transferorOrder = fundTransferorOrderMapper.selectOne(queryOrder);
			transferorOrder.setStatus(FundTransferorOrderStatus.TRANSFERING);//期望状态
			fundTransferOrderActionService.updateFundTransferorOrder(transferorOrder, FundTransferorOrderStatus.NEED_TRANSFER);
	
	    	// 更新OFC业务转让单信息
	        ofcIntService.updateBusinessOrderItemAsync(transferorOrder, null);
		}
        
    	// 更新OFC业务受让单信息
        ofcIntService.updateBusinessOrderItemAsync(transfereeOrder);
	}

	@Override
	public void doRefund(FundTransfereeOrder transfereeOrder, String productName) {
        //获取会员信息
    	MemberIdentityBean member = memberIntService.getMemberByMemberNo(transfereeOrder.getMemberNo());
    	if (member == null) {
    		logger.error("cannot get member info by memberNo: " + transfereeOrder.getMemberNo());
    		throw new BusinessException(FTCRespCode.ERR_FUND_QUERY_MEMBER_NOT_EXIST.getMessage(), 
    				FTCRespCode.ERR_FUND_QUERY_MEMBER_NOT_EXIST.getCode());
    	}

		Date curDate = DateUtils.now();
		int oldStatus = transfereeOrder.getStatus();
		
        // 更新受让单信息
 		fundTransferOrderActionService.updateFundTransfereeOrder(transfereeOrder, FundTransfereeOrderStatus.REFUNDING);
 		if (oldStatus == FundTransfereeOrderStatus.PAY_SUC) {
	 		// 更新转让单信息
			FundTransferorOrder queryOrder = new FundTransferorOrder();
			queryOrder.setFundTransferorOrderNo(transfereeOrder.getRefFundTransferorOrderNo());
			FundTransferorOrder transferorOrder = fundTransferorOrderMapper.selectOne(queryOrder);
			transferorOrder.setStatus(FundTransferorOrderStatus.TRANSFERING);//期望状态
	 		fundTransferOrderActionService.updateFundTransferorOrder(transferorOrder, FundTransferorOrderStatus.NEED_TRANSFER);

	     	// 更新OFC业务转让单信息
	        ofcIntService.updateBusinessOrderItemAsync(transferorOrder, null);
 		}
 		
        //创建退款单
        CusTransRequestDto cusTransRequestDto = new CusTransRequestDto();
        cusTransRequestDto.setOfcOrderNo(transfereeOrder.getBusinessOrderItemNo());
        cusTransRequestDto.setBizOrderNo(transfereeOrder.getFundTransfereeOrderNo());
        cusTransRequestDto.setTransCode(TransCode.REFUND);
        cusTransRequestDto.setBizTransType(FundTransType.TRANSFEREE);
        cusTransRequestDto.setMemNo(transfereeOrder.getMemberNo());
        cusTransRequestDto.setNeedAmount(transfereeOrder.getFundTransferAmount());
        cusTransRequestDto.setCurrency(transfereeOrder.getCurrencyType());
        cusTransRequestDto.setReTransNo(transfereeOrder.getTranscoreTransNo());
        cusTransRequestDto.setTransTime(curDate);
        cusTransRequestDto.setBizProductNo(transfereeOrder.getMktProductId());
        cusTransRequestDto.setPaymentMethods(transfereeOrder.getPayMethod());
        cusTransRequestDto.setTrackingNo(UUID.randomUUID().toString().replace("-", ""));
        cusTransRequestDto.setBizChannel(transfereeOrder.getBizChannel());
        settingIntService.createSettingOrderAsync(cusTransRequestDto);
        
     	// 更新OFC业务受让单信息
        ofcIntService.updateBusinessOrderItemAsync(transfereeOrder);
        
        //发送通知短信
        Map<String, String> params = new HashMap<>();
        params.put(ParamKeys.PRODUCT_NAME.getName(), productName);
        params.put(ParamKeys.AMOUNT.getName(), String.format("%.2f", transfereeOrder.getFundTransferAmount()));
        goutongIntService.sendSmsAsync(transfereeOrder.getBizChannel(), BusinessIDType.INVEST_REFUND_NOTICE.getId(), 
        		params, member.getVerifiedMobile());
	}

	@Override
	public void handleRefundResult(FundTransfereeOrder transfereeOrder, int targetStatus) {
		// 更新受让单状态
		fundTransferOrderActionService.updateFundTransfereeOrder(transfereeOrder, targetStatus);

     	// 更新OFC业务受让单信息
        ofcIntService.updateBusinessOrderItemAsync(transfereeOrder);
	}

}
