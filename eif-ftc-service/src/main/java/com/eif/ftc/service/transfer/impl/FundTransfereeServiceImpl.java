package com.eif.ftc.service.transfer.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.eif.contract.facade.response.ftc.AssignorTransfereeContractResp;
import com.eif.contract.facade.response.ftc.InsertContractResp;
import com.eif.fis.facade.dto.ftc.ProdInfo;
import com.eif.framework.common.utils.constant.CurrencyISOCode;
import com.eif.framework.concurrent.lock.RedisConcurrentLock;
import com.eif.ftc.dal.dao.FundTransfereeOrderMapper;
import com.eif.ftc.dal.dao.FundTransferorOrderMapper;
import com.eif.ftc.dal.model.FundTransfereeOrder;
import com.eif.ftc.dal.model.FundTransferorOrder;
import com.eif.ftc.facade.fund.trans.enumeration.FundTransType;
import com.eif.ftc.facade.fund.transfer.enumeration.FundTransfereeOrderStatus;
import com.eif.ftc.facade.fund.transfer.enumeration.FundTransferorOrderStatus;
import com.eif.ftc.facade.fund.transfer.request.CreateTransfereeOrderRequest;
import com.eif.ftc.facade.fund.transfer.request.PayTransfereeOrderRequest;
import com.eif.ftc.facade.fund.transfer.request.ResumePayTransfereeOrderRequest;
import com.eif.ftc.facade.fund.transfer.response.CreateTransfereeOrderResponse;
import com.eif.ftc.facade.fund.transfer.response.PayTransfereeOrderResponse;
import com.eif.ftc.facade.fund.transfer.response.ResumePayTransfereeOrderResponse;
import com.eif.ftc.integration.fis.FisIntService;
import com.eif.ftc.integration.member.MemberIntService;
import com.eif.ftc.integration.ofc.OfcIntService;
import com.eif.ftc.integration.risk.RiskIntService;
import com.eif.ftc.service.bean.FundAccountBean;
import com.eif.ftc.service.fund.acct.FundAcctOrderService;
import com.eif.ftc.service.transfer.FundTransfereeService;
import com.eif.ftc.service.transfer.action.FundTransferOrderActionService;
import com.eif.ftc.service.transfer.action.PayTransfereeOrderActionService;
import com.eif.ftc.service.transfer.action.TransferApplyActionService;
import com.eif.ftc.util.SequenceGenerator;
import com.eif.ftc.util.constant.TransCoreConstant;
import com.eif.ftc.util.exception.BusinessException;
import com.eif.risk.facade.constant.CheckPointList;
import com.eif.setting.facade.request.CusTransResultRequest;
import com.eif.transcore.facade.dto.bean.TransactionPaymentOptionBean;
import com.eif.transcore.facade.dto.enumeration.SmsStrategy;
import com.eif.transcore.facade.dto.enumeration.TransCode;
import com.eif.transcore.facade.dto.enumeration.TransactionStatus;
import com.eif.transcore.facade.mq.MQTransInfoBean;
import com.eif.transcore.facade.response.BatchReportTransEventResponse;
import com.eif.transcore.facade.response.CreateTransResponse;
import com.eif.transcore.facade.response.RoutePaymentProviderInfoResponse;
import com.lts.core.commons.utils.DateUtils;

import ma.glasnost.orika.MapperFacade;
import tk.mybatis.mapper.util.StringUtil;

@Service("fundTransfereeService")
public class FundTransfereeServiceImpl implements FundTransfereeService {

	private static Logger logger = LoggerFactory.getLogger(FundTransfereeServiceImpl.class);

	@Autowired
    private TransferApplyActionService transferApplyActionService;
	
    @Autowired
    private PayTransfereeOrderActionService payTransfereeOrderActionService;

    @Autowired
    private FundTransferOrderActionService fundTransferOrderActionService;
    
    @Resource
    private MemberIntService memberIntService;

    @Resource
    private OfcIntService ofcIntService;

    @Resource
    private FisIntService fisIntService;

    @Resource
    private RiskIntService riskIntService;

    @Autowired
    private MapperFacade mapperFacade;

    @Autowired
    private FundTransfereeOrderMapper fundTransfereeOrderMapper;

    @Autowired
    private FundTransferorOrderMapper fundTransferorOrderMapper;

    @Autowired
    private FundAcctOrderService fundAcctOrderService;

    @Resource
    private SequenceGenerator sequenceGenerator;

    @Resource
    private RedisConcurrentLock redisConcurrentLock;

    public final static int KEY_EXPIRED_TIME_IN_SECOND = 30 * 60; // 30分钟

	@Override
	public void createTransfereeOrder(CreateTransfereeOrderRequest request, CreateTransfereeOrderResponse response) {
		// 生成受让交易单
        Date curDate = DateUtils.now();
		FundTransfereeOrder transfereeOrder = new FundTransfereeOrder();
		mapperFacade.map(request, transfereeOrder);
		transfereeOrder.setStatus(FundTransfereeOrderStatus.NEED_PAY);
		transfereeOrder.setCurrencyType(CurrencyISOCode.CHINA);
		transfereeOrder.setCreateTime(curDate);
		transfereeOrder.setUpdateTime(curDate);
		// 生成受让单号
		String transfereeOrderOrderNo = sequenceGenerator.transOrderGen(FundTransType.TRANSFEREE);
		transfereeOrder.setFundTransfereeOrderNo(transfereeOrderOrderNo);
		
		// 资产账户状态
		FundAccountBean fundAccountBean = transferApplyActionService.checkAndGetMemberAccountInfo(transfereeOrder.getMemberNo());
		if (fundAccountBean != null) {//更新用户资产账户号
			transfereeOrder.setAssetAccountNo(fundAccountBean.getFundAccountNo());
			transfereeOrder.setTransAccountNo(fundAccountBean.getTransactionAccount());
		}
		
		//获取产品信息
		ProdInfo product = fisIntService.queryProductCommonInfo(transfereeOrder.getMktProductId());
        // 并发控制
        redisConcurrentLock.acquire(request.getTrackingNo(), KEY_EXPIRED_TIME_IN_SECOND);
        try {
    		// 校验用户风控状态
    		try {
    			riskIntService.transfereeRiskCheck(transfereeOrder, 
    					CheckPointList.USER_FT_ORDER_PRE.getCheckPoint(), 
    					request.getDeviceInfo(), request.getDevId(), request.getIp());
    		} catch (BusinessException e) {
    			// 记录风控失败订单
    			transfereeOrder.setStatus(FundTransfereeOrderStatus.REJECTED_BY_RISK);
    			fundTransferOrderActionService.insertFundTransfereeOrder(transfereeOrder);
    			
    			throw e;
    		}

			if (StringUtil.isEmpty(transfereeOrder.getAssetAccountNo())) {//未开户
				FundAccountBean resultBean = fundAcctOrderService.openFundAccount(
						transfereeOrder.getBizChannel(), transfereeOrder.getMemberNo(), transfereeOrder.getMktProductId());
				transfereeOrder.setAssetAccountNo(resultBean.getFundAccountNo());
				transfereeOrder.setTransAccountNo(resultBean.getTransactionAccount());
			}
			
			try {
				// 创建受让业务单
				String businessOrderItemNo = ofcIntService.createBusinessOrderItem(
						transfereeOrder, product.getProductName());
				transfereeOrder.setBusinessOrderItemNo(businessOrderItemNo);
			} catch (Exception e) {// 超时处理
				transfereeOrder.setStatus(FundTransfereeOrderStatus.TRANS_FAILED);
	    		ofcIntService.updateBusinessOrderItemAsync(transfereeOrder);
	    		
	    		throw e;
	    	}
			
			//插入受让单
			fundTransferOrderActionService.insertFundTransfereeOrder(transfereeOrder);
        } finally {
        	redisConcurrentLock.release(request.getTrackingNo());
        }
		
		response.setBusinessOrderItemNo(transfereeOrder.getBusinessOrderItemNo());
		response.setFundTransfereeOrderNo(transfereeOrder.getFundTransfereeOrderNo());
	}

	@Override
	public void payTransfereeOrder(PayTransfereeOrderRequest request, PayTransfereeOrderResponse response) {
		// 获取受让单
		FundTransfereeOrder queryOrder1 = new FundTransfereeOrder();
		queryOrder1.setFundTransfereeOrderNo(request.getFundTransfereeOrderNo());
		FundTransfereeOrder transfereeOrder = fundTransfereeOrderMapper.selectOne(queryOrder1);

		// 获取产品信息
		ProdInfo product = fisIntService.queryProductCommonInfo(transfereeOrder.getMktProductId());
		
		// 校验订单信息
		FundTransferorOrder transferorOrder = new FundTransferorOrder();
		int expectTransfereeOrderStatus = FundTransfereeOrderStatus.NEED_PAY;
		int expectTransferorOrderStatus = FundTransferorOrderStatus.NEED_TRANSFER;
		payTransfereeOrderActionService.getAndCheckTransferOrders(transfereeOrder, expectTransfereeOrderStatus,
				transferorOrder, expectTransferorOrderStatus, product);

    	// 更新支付方式
		if (!StringUtil.isEmpty(request.getPayMethod())) {
			transfereeOrder.setPayMethod(request.getPayMethod());
		}
		transfereeOrder.setDiscountAmount(request.getDiscountAmount());
		
		// 透传交易核心信息, 后续在mq回调中传回
        Map<String, Object> extField = new HashMap<>();
        extField.put(TransCoreConstant.EXPECTED_PROFIT_AMOUNT, 
        		transferorOrder.getOriginalAssetTotalValue().subtract(
        				transferorOrder.getFundTransferAmount()));//预期收益 = 资产总价值 - 定价价格
        extField.put(TransCoreConstant.TRANS_DEV_ID, request.getDevId());
        extField.put(TransCoreConstant.TRANS_IP, request.getIp());
        extField.put(TransCoreConstant.TRANS_DEV_INFO, request.getDeviceInfo());

        // 如果包含代扣支付，则需要进行预路由
        List<TransactionPaymentOptionBean> paymentOptions = JSON.parseArray(
        		transfereeOrder.getPayMethod(), TransactionPaymentOptionBean.class);
        RoutePaymentProviderInfoResponse paymentRoutingResp = null;
        if (payTransfereeOrderActionService.checkDCPPaymentOption(paymentOptions)) {
            // 进行支付预路由处理
            paymentRoutingResp = payTransfereeOrderActionService.doPaymentRouting(paymentOptions, request.getSmsControl(),transfereeOrder.getMemberNo());
        }
		
		// 并发控制
        redisConcurrentLock.acquire(transfereeOrder.getFundTransfereeOrderNo() + expectTransfereeOrderStatus, KEY_EXPIRED_TIME_IN_SECOND);
        try {
	        // 不需要OTP验证，直接支付
	        if (paymentRoutingResp == null || paymentRoutingResp.getSmsStrategy().equals(SmsStrategy.NEVER_SEND_SMS)) {
	        	// 校验用户风控状态,在真实支付的时候来验证风控
        		payTransfereeOrderActionService.checkUserRiskStatus(transfereeOrder, 
        				request.getDeviceInfo(), request.getDevId(), request.getIp());
        		
	        	// 更新转让订单信息（状态）
	        	fundTransferOrderActionService.updateFundTransferorOrder(transferorOrder, FundTransferorOrderStatus.TRANSFERING);
	        	try {
	        		// 直接发起支付
		        	payTransfereeOrderActionService.payTransfereeOrderWithoutOTP(transfereeOrder, 
		        			paymentRoutingResp, paymentOptions, 
		        			product.getProductName(), product.getOrderExpireTime(), 
		        			JSON.toJSONString(extField));
	        	} catch (Exception ex) {
	    			// 回滚转让单状态
	        		fundTransferOrderActionService.updateFundTransferorOrder(transferorOrder, expectTransferorOrderStatus);
	        		
	        		// 更新受让单状态
	        		fundTransferOrderActionService.updateFundTransfereeOrder(transfereeOrder, FundTransfereeOrderStatus.PAY_FAILED);
	    	        ofcIntService.updateBusinessOrderItemAsync(transfereeOrder);
	        		throw ex;
	        	}
	        } 
	        // 需要OTP验证
	        else {
	        	String paymentNo = payTransfereeOrderActionService.payTransfereeOrderWithOTP(transfereeOrder, 
	        			paymentRoutingResp, paymentOptions, product.getProductName(), JSON.toJSONString(extField));
	        	response.setPaymentNo(paymentNo);
	        }
		} finally {
        	redisConcurrentLock.release(transfereeOrder.getFundTransfereeOrderNo() + expectTransfereeOrderStatus);
        }
        
        response.setBusinessOrderItemNo(transfereeOrder.getBusinessOrderItemNo());
        response.setFundTransfereeOrderNo(transfereeOrder.getFundTransfereeOrderNo());
        response.setTransNo(transfereeOrder.getTranscoreTransNo());
        response.setTransStatus(transfereeOrder.getStatus());
	}

	@Override
	public void resumePayTransfereeOrder(ResumePayTransfereeOrderRequest request,
			ResumePayTransfereeOrderResponse response) {
		// 获取受让单
		FundTransfereeOrder queryOrder = new FundTransfereeOrder();
		queryOrder.setFundTransfereeOrderNo(request.getFundTransfereeOrderNo());
		FundTransfereeOrder transfereeOrder = fundTransfereeOrderMapper.selectOne(queryOrder);

		// 获取产品信息
		ProdInfo product = fisIntService.queryProductCommonInfo(transfereeOrder.getMktProductId());
		
		// 校验订单信息
		FundTransferorOrder transferorOrder = new FundTransferorOrder();
		int expectTransfereeOrderStatus = FundTransfereeOrderStatus.AUTH_PENDING;
		int expectTransferorOrderStatus = FundTransferorOrderStatus.NEED_TRANSFER;
		payTransfereeOrderActionService.getAndCheckTransferOrders(transfereeOrder, expectTransfereeOrderStatus,
				transferorOrder, expectTransferorOrderStatus, product);

		// 校验用户风控状态,在真实支付的时候来验证风控
		payTransfereeOrderActionService.checkUserRiskStatus(transfereeOrder, 
				request.getDeviceInfo(), request.getDevId(), request.getIp());
		
		// 并发控制
        redisConcurrentLock.acquire(transfereeOrder.getFundTransfereeOrderNo() + expectTransfereeOrderStatus, KEY_EXPIRED_TIME_IN_SECOND);
        try {
        	// 更新转让订单信息（状态）
        	fundTransferOrderActionService.updateFundTransferorOrder(transferorOrder, FundTransferorOrderStatus.TRANSFERING);
        	
        	// resume pay
        	payTransfereeOrderActionService.resumePayTransfereeOrder(transfereeOrder,
        			product.getProductName(), product.getOrderExpireTime(), request.getPin());
        } catch (Exception ex) {
			// 回滚转让单状态
        	fundTransferOrderActionService.updateFundTransfereeOrder(transfereeOrder, expectTransferorOrderStatus);
        	
        	// 更新受让单状态
    		fundTransferOrderActionService.updateFundTransfereeOrder(transfereeOrder, FundTransfereeOrderStatus.PAY_FAILED);
	        ofcIntService.updateBusinessOrderItemAsync(transfereeOrder);
        	throw ex;
        } finally {
        	redisConcurrentLock.release(transfereeOrder.getFundTransfereeOrderNo() + expectTransfereeOrderStatus);
        }
        
        response.setTransNo(transfereeOrder.getTranscoreTransNo());
        response.setTransStatus(TransactionStatus.SETTLING);
	}

	@Override
	public void handleCreateTranscoreMessage(CreateTransResponse createTransResp) {
		if (createTransResp.getTransCode().equals(TransCode.ASSIGNMENT)) {
			// 获取并校验转让单
			FundTransferorOrder queryOrder = new FundTransferorOrder();
			queryOrder.setFundTransferorOrderNo(createTransResp.getBizOrderNo());
			FundTransferorOrder transferorOrder = fundTransferorOrderMapper.selectOne(queryOrder);
			try {
				fundTransferOrderActionService.checkTransferorOrderStatus(transferorOrder, FundTransferorOrderStatus.TRANSFERING);
			} catch (BusinessException be) {
				logger.error("handleCreateTranscoreMessage - checkTransferorOrderStatus failed", be);
				return;
			}
			
			// 获取并校验受让单
			FundTransfereeOrder queryOrder1 = new FundTransfereeOrder();
			queryOrder1.setRefFundTransferorOrderNo(transferorOrder.getFundTransferorOrderNo());
			queryOrder1.setStatus(FundTransfereeOrderStatus.PAY_SUC);
			FundTransfereeOrder transfereeOrder = fundTransfereeOrderMapper.selectOne(queryOrder1);
			try {
				fundTransferOrderActionService.checkTransfereeOrderStatus(transfereeOrder, FundTransfereeOrderStatus.PAY_SUC);
			} catch (BusinessException be) {
				logger.error("handleCreateTranscoreMessage - checkTransfereeOrderStatus failed", be);
				return;
			}
//			if (StringUtil.isEmpty(transfereeOrder.getTransferTransNo())) {//未处理
				// 创建交易单结果处理
				payTransfereeOrderActionService.handleCreateTranscoreMessage(
						createTransResp, transferorOrder, transfereeOrder);
//			}
		}
	}

	@Override
	public void handleTranscoreStatusMessage(MQTransInfoBean transStatusInfo) {
		if (transStatusInfo.getTransCode().equals(TransCode.TRANSFEREE)) {//支付结果
			// 获取受让单
			FundTransfereeOrder queryOrder = new FundTransfereeOrder();
			queryOrder.setFundTransfereeOrderNo(transStatusInfo.getBizOrderNo());
			FundTransfereeOrder transfereeOrder = fundTransfereeOrderMapper.selectOne(queryOrder);
			
			//获取产品信息
			ProdInfo product = fisIntService.queryProductCommonInfo(transfereeOrder.getMktProductId());
			payTransfereeOrderActionService.handleTranscoreStatusMessage(
					transStatusInfo, transfereeOrder, product);
		}
	}

	@Override
	public void handleRefundResultMessage(CusTransResultRequest refundResp) {
		// 获取受让单
		FundTransfereeOrder queryOrder = new FundTransfereeOrder();
		queryOrder.setFundTransfereeOrderNo(refundResp.getBizOrderNo());
		FundTransfereeOrder transfereeOrder = fundTransfereeOrderMapper.selectOne(queryOrder);
		try {
			fundTransferOrderActionService.checkTransfereeOrderStatus(transfereeOrder, FundTransfereeOrderStatus.REFUNDING);
		} catch (BusinessException be) {
			logger.error("handleRefundResultMessage - checkTransfereeOrderStatus failed", be);
			return;
		}
			
		payTransfereeOrderActionService.handleRefundResultMessage(refundResp, transfereeOrder);
	}
	
	@Override
	public void handleSignContractResult(InsertContractResp signContractResp) {
		// 获取受让单
		FundTransfereeOrder queryOrder = new FundTransfereeOrder();
		queryOrder.setFundTransfereeOrderNo(signContractResp.getOrderId());
		FundTransfereeOrder transfereeOrder = fundTransfereeOrderMapper.selectOne(queryOrder);
//		fundTransferOrderActionService.checkTransfereeOrderStatus(transfereeOrder, FundTransfereeOrderStatus.PAY_SUC);
		
		payTransfereeOrderActionService.handleSignContractResult(signContractResp, transfereeOrder);
	}

	@Override
	public void handleTranscoreReportEventResult(BatchReportTransEventResponse batchReportEventReuslt) {
		if (!batchReportEventReuslt.isSuccess()) {
			throw new BusinessException(batchReportEventReuslt.getMsg(), batchReportEventReuslt.getRespCode());
		}
		
		// 获取受让单
		FundTransfereeOrder queryOrder = new FundTransfereeOrder();
		queryOrder.setTransferTransNo(batchReportEventReuslt.getReportResults().get(0).getTransNo());
		FundTransfereeOrder transfereeOrder = fundTransfereeOrderMapper.selectOne(queryOrder);
		try {
			fundTransferOrderActionService.checkTransfereeOrderStatus(transfereeOrder, FundTransfereeOrderStatus.PAY_SUC);
		} catch (BusinessException be) {
			logger.error("handleTranscoreReportEventResult - checkTransfereeOrderStatus failed", be);
			return;
		}
		
		payTransfereeOrderActionService.handleTranscoreReportResult(
				batchReportEventReuslt.getReportResults().get(0), transfereeOrder);
	}

	@Override
	public void handleSignTransferAgreementResult(AssignorTransfereeContractResp signAgreementResp) {
		if (!signAgreementResp.isSuccess()) {
			throw new BusinessException(signAgreementResp.getMsg(), signAgreementResp.getRespCode());
		}
	}

}
