package com.eif.ftc.service.transfer.action.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import com.eif.contract.facade.dto.ftc.ContractBase;
import com.eif.contract.facade.dto.ftc.ContractFinancing;
import com.eif.contract.facade.request.ftc.InsertContractReq;
import com.eif.contract.facade.response.ftc.InsertContractResp;
import com.eif.fis.facade.constant.MarketLevel;
import com.eif.fis.facade.constant.TransStructureType;
import com.eif.fis.facade.dto.ftc.ProdInfo;
import com.eif.fis.facade.dto.ftc.ProdSummary;
import com.eif.fis.facade.dto.ftc.SecMrktProdTransInfo;
import com.eif.fis.facade.response.ftc.QueryProdTransInfoV2Resp;
import com.eif.ftc.dal.dao.FundTransOrderMapper;
import com.eif.ftc.dal.dao.FundTransfereeOrderMapper;
import com.eif.ftc.dal.dao.FundTransferorOrderMapper;
import com.eif.ftc.dal.model.FundTransOrder;
import com.eif.ftc.dal.model.FundTransferApplyToExchange;
import com.eif.ftc.dal.model.FundTransfereeOrder;
import com.eif.ftc.dal.model.FundTransferorOrder;
import com.eif.ftc.facade.code.FTCRespCode;
import com.eif.ftc.facade.fund.trans.enumeration.BusinessTransType;
import com.eif.ftc.facade.fund.trans.enumeration.FundTransOrderStatus;
import com.eif.ftc.facade.fund.trans.enumeration.SignContractBizCode;
import com.eif.ftc.facade.fund.transfer.enumeration.FundTransferExchangeStatus;
import com.eif.ftc.facade.fund.transfer.enumeration.FundTransfereeOrderStatus;
import com.eif.ftc.facade.fund.transfer.enumeration.FundTransferorOrderStatus;
import com.eif.ftc.integration.contract.ContractIntService;
import com.eif.ftc.integration.fis.FisIntService;
import com.eif.ftc.integration.goutong.GoutongIntService;
import com.eif.ftc.integration.member.MemberIntService;
import com.eif.ftc.integration.ofc.OfcIntService;
import com.eif.ftc.integration.risk.RiskIntService;
import com.eif.ftc.integration.transcore.TranscoreIntService;
import com.eif.ftc.service.bean.FundTransferAmountBean;
import com.eif.ftc.service.fund.amc.MemberAssetService;
import com.eif.ftc.service.transfer.FundTransferorService;
import com.eif.ftc.service.transfer.action.FundTransferChargeActionService;
import com.eif.ftc.service.transfer.action.FundTransferOrderActionService;
import com.eif.ftc.service.transfer.action.PayTransfereeOrderActionService;
import com.eif.ftc.service.transfer.exchange.processor.TransferApplyService;
import com.eif.ftc.util.MoneyUtil;
import com.eif.ftc.util.exception.BusinessException;
import com.eif.ftc.util.uuid.UUIDGenerator;
import com.eif.goutong.facade.constant.BusinessIDType;
import com.eif.goutong.facade.constant.ParamKeys;
import com.eif.member.facade.pkg.dto.responseDTO.MemberIdentityBean;
import com.eif.risk.facade.constant.CheckPointList;
import com.eif.setting.facade.request.CusTransResultRequest;
import com.eif.transcore.facade.dto.bean.CreateTransDTO;
import com.eif.transcore.facade.dto.bean.ReportTransEventBean;
import com.eif.transcore.facade.dto.bean.ReportTransEventResult;
import com.eif.transcore.facade.dto.bean.TransactionPaymentOptionBean;
import com.eif.transcore.facade.dto.enumeration.PaymentInstrumentType;
import com.eif.transcore.facade.dto.enumeration.SmsStrategy;
import com.eif.transcore.facade.dto.enumeration.TransAttribute;
import com.eif.transcore.facade.dto.enumeration.TransCode;
import com.eif.transcore.facade.dto.enumeration.TransactionEventType;
import com.eif.transcore.facade.dto.enumeration.TransactionStatus;
import com.eif.transcore.facade.mq.MQTransInfoBean;
import com.eif.transcore.facade.request.ResumePayTransResquest;
import com.eif.transcore.facade.request.RoutePaymentProviderInfoRequest;
import com.eif.transcore.facade.response.CreateTransResponse;
import com.eif.transcore.facade.response.ResumePayTransResponse;
import com.eif.transcore.facade.response.RoutePaymentProviderInfoResponse;
import com.lts.core.commons.utils.DateUtils;

import ma.glasnost.orika.MapperFacade;
import tk.mybatis.mapper.util.StringUtil;

@Service("payTransfereeOrderActionService")
public class PayTransfereeOrderActionServiceImpl implements PayTransfereeOrderActionService {

	private static Logger logger = LoggerFactory.getLogger(PayTransfereeOrderActionServiceImpl.class);

    @Resource
    private RiskIntService riskIntService;

    @Resource
    private OfcIntService ofcIntService;

    @Resource
    private FisIntService fisIntService;

    @Resource
    private MemberIntService memberIntService;

    @Resource
    private TranscoreIntService transcoreIntService;
    
    @Resource
    private ContractIntService contractIntService;

    @Resource
    private GoutongIntService goutongIntService;

    @Autowired
    private MemberAssetService memberAssetService;
    
    @Autowired
    private FundTransferorService fundTransferorService;
    
    @Autowired
    private FundTransferChargeActionService fundTransferChargeActionService;
    
    @Autowired
    private FundTransferOrderActionService fundTransferOrderActionService;

    @Autowired
    private TransferApplyService transferApplyService;
    
    @Autowired
    private FundTransfereeOrderMapper fundTransfereeOrderMapper;

    @Autowired
    private FundTransferorOrderMapper fundTransferorOrderMapper;

    @Autowired
    private FundTransOrderMapper fundTransOrderMapper;

    @Autowired
    private MapperFacade mapperFacade;
    
	@Override
	public void checkUserRiskStatus(FundTransfereeOrder transfereeOrder,
			String deviceInfo, String devId, String ip) {
		try {
			riskIntService.transfereeRiskCheck(transfereeOrder, 
					CheckPointList.USER_FIN_TRANS_ING.getCheckPoint(), deviceInfo, devId, ip);
		} catch (BusinessException e) {
			// 记录风控失败订单
			transfereeOrder.setUpdateTime(new Date());
			fundTransferOrderActionService.updateFundTransfereeOrder(transfereeOrder, FundTransfereeOrderStatus.REJECTED_BY_RISK);
			
			// 更新业务单
			ofcIntService.updateBusinessOrderItemAsync(transfereeOrder);
			throw e;
		}
	}

	@Override
	public void getAndCheckTransferOrders(FundTransfereeOrder transfereeOrder, int transfereeOrderStatus,
			FundTransferorOrder transferorOrder, int transferorOrderStatus, ProdInfo product) {
		// 校验受让单状态
		fundTransferOrderActionService.checkTransfereeOrderStatus(transfereeOrder, transfereeOrderStatus);
		
		// 校验二级市场产品是否创建成功
		if (transfereeOrder.getMktProductId().longValue() == TransferApplyActionServiceImpl.DEFAULT_MKT_PRODUCT_ID) {
			throw new BusinessException(FTCRespCode.ERR_FUND_TRANSFER_CREATE_PRODUCT.getMessage(),
					FTCRespCode.ERR_FUND_TRANSFER_CREATE_PRODUCT.getCode());
		}
		
		// 校验转让单状态
		FundTransferorOrder queryOrder = new FundTransferorOrder();
		queryOrder.setMktProductId(transfereeOrder.getMktProductId());
//		queryOrder.setStatus(transferorOrderStatus);
		FundTransferorOrder order = fundTransferorOrderMapper.selectOne(queryOrder);
		fundTransferOrderActionService.checkTransferorOrderStatus(order, transferorOrderStatus);

		// 转让人和受让人相同
		if (order.getMemberNo().equals(transfereeOrder.getMemberNo())) {
			throw new BusinessException(FTCRespCode.ERR_FUND_TRANSFER_SAME_MEMBER.getMessage(),
					FTCRespCode.ERR_FUND_TRANSFER_SAME_MEMBER.getCode());
		}
		// 转让单过期
		if (order.getExpiryTime().before(DateUtils.now())) {//此处只做判断，job扫单做后续处理
			throw new BusinessException(FTCRespCode.ERR_FUND_TRANSFEROR_CLOSE_BY_EXPIRED.getMessage(),
					FTCRespCode.ERR_FUND_TRANSFEROR_CLOSE_BY_EXPIRED.getCode());
		}
		
		mapperFacade.map(order, transferorOrder);
		
		// 校验转让定价等价格费用信息
		FundTransferAmountBean transferAmountBean = fundTransferorService.calculateFundTransferAmountInfo(
				transferorOrder, product, DateUtils.now());
		if (transfereeOrder.getFundTransferAmount().compareTo(transferAmountBean.getFundTransferAmount()) != 0) {
			throw new BusinessException(FTCRespCode.ERR_FUND_TRANSFER_AMOUNT_DIFFERENT.getMessage(),
					FTCRespCode.ERR_FUND_TRANSFER_AMOUNT_DIFFERENT.getCode());
		}
		
		// 更新转让定价信息
		transferorOrder.setDiscountAmount(transferAmountBean.getDiscountAmount());
		transferorOrder.setFundTransferAmount(transferAmountBean.getFundTransferAmount());
		transferorOrder.setFundTransferInterest(transferAmountBean.getFundTransferInterest());
		transferorOrder.setFundTransferPrincipal(transferAmountBean.getFundTransferPrincipal());
		transferorOrder.setOriginalAssetSurplusValue(transferAmountBean.getOriginalAssetSurplusValue());
		transferorOrder.setOriginalAssetTotalValue(transferAmountBean.getOriginalAssetTotalValue());
		transferorOrder.setTransferorFee(transferAmountBean.getTransferorFee());
		transferorOrder.setTransfereeFee(transferAmountBean.getTransfereeFee());
		// 更新受让单信息, 冗余字段代表transferorFee
		transfereeOrder.setFee(transferorOrder.getTransferorFee());
		transfereeOrder.setRefFundTransferorOrderNo(transferorOrder.getFundTransferorOrderNo());
		transfereeOrder.setTransferAgreementNo(transferorOrder.getTransferAgreementNo());

		// 设置最初发起转让的交易订单号
		ProdInfo fProduct = fisIntService.queryProductCommonInfo(transferorOrder.getProductId());
		if (fProduct.getMarketLevel() == MarketLevel.FIRST) {//一级市场，初次转让
			transfereeOrder.setRefOriginFundTransferorOrderNo(transferorOrder.getFundTransferorOrderNo());
		} else {//二级市场，再次转让
			FundTransfereeOrder queryOrder1 = new FundTransfereeOrder();
			queryOrder1.setMemberNo(transferorOrder.getMemberNo());
			queryOrder1.setMktProductId(transferorOrder.getProductId());
			queryOrder1.setStatus(FundTransfereeOrderStatus.TRANS_SUC);
			
			FundTransfereeOrder preTransfereeOrder = fundTransfereeOrderMapper.selectOne(queryOrder1);
			if (preTransfereeOrder != null) {
				transfereeOrder.setRefOriginFundTransferorOrderNo(preTransfereeOrder.getRefOriginFundTransferorOrderNo());
			} else {
				logger.error("can not find pre transferee order, memberNo: " + transferorOrder.getMemberNo()
						+ ", mktProductId: " + transferorOrder.getProductId());
	    		throw new BusinessException(FTCRespCode.ERR_FUND_TRANSFEREE_ORDER_NOT_EXIST.getMessage(),
	    				FTCRespCode.ERR_FUND_TRANSFEREE_ORDER_NOT_EXIST.getCode());
			}
		}
	}

	@Override
    public boolean checkDCPPaymentOption(List<TransactionPaymentOptionBean> paymentOptions) {
        boolean hasBankCardPayment = false;
        for (TransactionPaymentOptionBean paymentOption : paymentOptions) {
            if (PaymentInstrumentType.DCP == paymentOption.getPaymentInstrumentType().intValue()) {
                hasBankCardPayment = true;
                break;
            }
        }

        return hasBankCardPayment;
    }

	@Override
    public RoutePaymentProviderInfoResponse doPaymentRouting(
            List<TransactionPaymentOptionBean> paymentOptions, Integer smsSendControl,String memberNo) {
        //调用TC进行支付预路由
        RoutePaymentProviderInfoRequest paymentRoutingRequest = new RoutePaymentProviderInfoRequest();
        paymentRoutingRequest.setTransCode(TransCode.CHARGE);
        paymentRoutingRequest.setTransAttribute(TransAttribute.PAYMENT);
        paymentRoutingRequest.setPaymentOptions(paymentOptions);
        paymentRoutingRequest.setMemberNo(memberNo);
        if (smsSendControl.equals(SmsStrategy.AVOID_SEND_SMS.getValue())) {
            paymentRoutingRequest.setSmsStrategy(SmsStrategy.AVOID_SEND_SMS);
        } else {
            paymentRoutingRequest.setSmsStrategy(SmsStrategy.ALWAYS_SEND_SMS);
        }

        return transcoreIntService.paymentRouting(paymentRoutingRequest);
    }

	@Override
	public void payTransfereeOrderWithoutOTP(FundTransfereeOrder transfereeOrder, 
    		RoutePaymentProviderInfoResponse paymentRoutingResp,
            List<TransactionPaymentOptionBean> paymentOptions, 
            String productName, Integer orderExpireTime, String extField) {
		Date transTime = DateUtils.now();
    	String frozenToken = UUIDGenerator.gen();
    	try {
    		// 冻结产品库存
			fisIntService.freezeMktProductInventory(transfereeOrder.getMktProductId(), 
					transfereeOrder.getFundTransferAmount(), frozenToken, transTime);
			transfereeOrder.setFrozenToken(frozenToken);
			// 新手处理
			memberIntService.updateRookieStatusAndRetIsRookie(transfereeOrder.getMemberNo(), 1);
		} catch (Exception ex) {
//			if (!StringUtil.isEmpty(transfereeOrder.getFrozenToken())) {
				// 失败取消冻结库存
				fisIntService.unfreezeMktProductInventoryAsync(frozenToken);
//			}
			throw ex;
		}
		
		try {
	        // 设置超时时间
			transfereeOrder.setPayTime(transTime);
			transfereeOrder.setUpdateTime(transTime);
			transfereeOrder.setStatusExpiryTime(DateUtils.addMinute(transTime, (int) (orderExpireTime / 60)));
			// 更新受让单信息
			fundTransferOrderActionService.updateFundTransfereeOrder(transfereeOrder, FundTransfereeOrderStatus.PAYING);

	        // 异步发送交易核心受让扣款指令
	        CreateTransDTO transDTO = transcoreIntService.buildTranscoreCreateTransDTO(
	        		transfereeOrder, TransCode.TRANSFEREE, TransAttribute.PAYMENT, 
	        		paymentOptions, productName, extField, null, paymentRoutingResp);
	        SmsStrategy smsStrategy = (paymentRoutingResp == null) ? SmsStrategy.NEVER_SEND_SMS : paymentRoutingResp.getSmsStrategy();
	        transcoreIntService.createTranscoreOrderAsync(transDTO, 
	        		smsStrategy, transfereeOrder.getOperatorNo());
	        
	    	// 更新业务单信息
	        ofcIntService.updateBusinessOrderItemAsync(transfereeOrder);
    	} catch (Exception ex) {
    		// 解冻（取消）产品份额
    		fisIntService.unfreezeMktProductInventoryAsync(frozenToken);
    		// 回滚新手状态
    		memberIntService.updateRookieStatusAndRetIsRookie(transfereeOrder.getMemberNo(), -1);
    		throw ex;
    	}
	}

	@Override
	public String payTransfereeOrderWithOTP(FundTransfereeOrder transfereeOrder, 
    		RoutePaymentProviderInfoResponse paymentRoutingResp,
            List<TransactionPaymentOptionBean> paymentOptions, 
            String productName, String extField) {
		transfereeOrder.setUpdateTime(DateUtils.now());
		// 调用交易核心扣款
        CreateTransResponse transResponse = transcoreIntService.createTransCoreOrder(
        		transfereeOrder, TransCode.TRANSFEREE, TransAttribute.PAYMENT, 
        		paymentOptions, productName, extField, paymentRoutingResp);
        if (transResponse.getTransactionStatus() == TransactionStatus.AUTH_PENDING) {
            // 更新业务单信息, 由于auth_pending的逻辑不处理, 则不考虑同步异步前后问题
            transfereeOrder.setTranscoreTransNo(transResponse.getTransNo());
            fundTransferOrderActionService.updateFundTransfereeOrder(transfereeOrder, FundTransfereeOrderStatus.AUTH_PENDING);
            
            // 更新OFC业务订单
            ofcIntService.updateBusinessOrderItemAsync(transfereeOrder);

    		return transResponse.getAuthWaitingPaymentNo();
        } else {
            throw new BusinessException(FTCRespCode.ERR_FUND_TRANSCORE_STATUS.getMessage(),
                    FTCRespCode.ERR_FUND_TRANSCORE_STATUS.getCode());
        }
	}

	@Override
	public void resumePayTransfereeOrder(FundTransfereeOrder transfereeOrder,
			String productName, Integer orderExpireTime, String pin) {
		Date transTime = DateUtils.now();
		// 冻结产品库存
    	String frozenToken = UUIDGenerator.gen();
    	try {
    		// 冻结产品库存
			fisIntService.freezeMktProductInventory(transfereeOrder.getMktProductId(), 
					transfereeOrder.getFundTransferAmount(), frozenToken, transTime);
			transfereeOrder.setFrozenToken(frozenToken);
			
			// 新手处理
			memberIntService.updateRookieStatusAndRetIsRookie(transfereeOrder.getMemberNo(), 1);
		} catch (Exception ex) {
			// 失败取消冻结库存
			fisIntService.unfreezeMktProductInventoryAsync(frozenToken);
			throw ex;
		}
		
		try {
	        // 设置超时时间
			transfereeOrder.setPayTime(transTime);
			transfereeOrder.setUpdateTime(transTime);
			transfereeOrder.setStatusExpiryTime(DateUtils.addMinute(transTime, (int) (orderExpireTime / 60)));
			// 更新受让单信息
			fundTransferOrderActionService.updateFundTransfereeOrder(transfereeOrder, FundTransfereeOrderStatus.PAYING);

	        try {
		        // 调用交易核心的继续支付接口
		        ResumePayTransResquest resumePayTransResquest = new ResumePayTransResquest();
		        resumePayTransResquest.setTransNo(transfereeOrder.getTranscoreTransNo());
		        resumePayTransResquest.setPin(pin);
		        ResumePayTransResponse resp = transcoreIntService.resumePayTrans(resumePayTransResquest);

	            // 处理等待中
	            if (resp.getTransStatus() != TransactionStatus.SETTLING) {
	                throw new BusinessException(FTCRespCode.ERR_FUND_TRANSCORE_STATUS.getMessage(),
	                        FTCRespCode.ERR_FUND_TRANSCORE_STATUS.getCode());
	            }
	        } catch (Exception e) {
	            // 出错不处理, 记录log
	            logger.error("resumePayTransfereeOrder failed", e);
	        }
	        
	    	// 更新业务单信息
	        ofcIntService.updateBusinessOrderItemAsync(transfereeOrder);
    	} catch (Exception ex) {
    		// 解冻（取消）产品份额
    		fisIntService.unfreezeMktProductInventoryAsync(frozenToken);
    		// 回滚新手状态
    		memberIntService.updateRookieStatusAndRetIsRookie(transfereeOrder.getMemberNo(), -1);
    		throw ex;
    	}
	}

	@Override
	public void handleCreateTranscoreMessage(CreateTransResponse createTransResp,
		FundTransferorOrder transferorOrder, FundTransfereeOrder transfereeOrder) {
    	if (createTransResp.getTransactionStatus() != TransactionStatus.CREATED) {
    		logger.error("create transcore order failed, fundTransferorOrderNo: " + createTransResp.getBizOrderNo());
    		
    		// 支付成功，转账失败处理，需要人工干预处理
    		if (createTransResp.getTransCode().equals(TransCode.ASSIGNMENT)) {
    			logger.error("create transcore assignment order failed, fundTransferorOrderNo: " + createTransResp.getBizOrderNo());
    			throw new BusinessException(FTCRespCode.ERR_FUND_TRANSCORE_CALL_CREATE.getMessage(), 
    					FTCRespCode.ERR_FUND_TRANSCORE_CALL_CREATE.getCode());
    		}
    		
    		return;
    	}
	
    	// 成功
    	transfereeOrder.setTransferTransNo(createTransResp.getTransNo());	//转账单交易流水号
    	transferCreateSuccess(transferorOrder, transfereeOrder);
	}

	@Override
	public void handleTranscoreStatusMessage(MQTransInfoBean transStatusInfo,
		FundTransfereeOrder transfereeOrder, ProdInfo product) {
    	transfereeOrder.setTranscoreTransNo(transStatusInfo.getTransNo());	//受让单交易流水号
		if (transStatusInfo.getTransStatus() == TransactionStatus.SETTLE_FAILED) {//支付失败
			transfereeOrder.setRemark(transStatusInfo.getReasonCode());
			fundTransferChargeActionService.chargeFailed(transfereeOrder);
		} else if (transStatusInfo.getTransStatus() == TransactionStatus.SETTLED) {//支付成功
			fundTransferChargeActionService.chargeSuccess(transfereeOrder, product);
		}
	}

	@Override
	public void handleRefundResultMessage(CusTransResultRequest refundResp, 
			FundTransfereeOrder transfereeOrder) {
		Date curDate = DateUtils.now();
		int targetStatus = FundTransOrderStatus.REFUND_FAILED;
        if (refundResp.getTransStatus() == TransactionStatus.SETTLED) {
        	targetStatus = FundTransOrderStatus.REFUND_SUC;
        	transfereeOrder.setFinishTime(curDate);
        }
		
        fundTransferChargeActionService.handleRefundResult(transfereeOrder, targetStatus);
	}
	
	@Override
	@Transactional(rollbackFor={RuntimeException.class, Exception.class})
	public void handleTranscoreReportResult(ReportTransEventResult reportEventReuslt,
			FundTransfereeOrder transfereeOrder) {
		if (!reportEventReuslt.isProcessed()) {
			// 出错不处理, 记录log
            logger.error("transcore report event result failed, fundTransfereeOrderNo: " + transfereeOrder.getFundTransfereeOrderNo());
            throw new BusinessException(FTCRespCode.ERR_FUND_BATCH_TRANSCORE_CALL_REPORT_EVENT.getMessage(), 
					FTCRespCode.ERR_FUND_BATCH_TRANSCORE_CALL_REPORT_EVENT.getCode());
		} else {
			FundTransferorOrder queryOrder = new FundTransferorOrder();
			queryOrder.setFundTransferorOrderNo(transfereeOrder.getRefFundTransferorOrderNo());
			FundTransferorOrder transferorOrder = fundTransferorOrderMapper.selectOne(queryOrder);
			if (transferorOrder == null) {
				logger.error("cannot find transferor order by refTransferorOrderNo: " 
						+ transfereeOrder.getRefFundTransferorOrderNo());
				throw new BusinessException(FTCRespCode.ERR_FUND_TRANSFEROR_ORDER_NOT_EXIST.getMessage(), 
						FTCRespCode.ERR_FUND_TRANSFEROR_ORDER_NOT_EXIST.getCode());
			}
			if (transferorOrder.getStatus() == FundTransferorOrderStatus.TRANSFER_SUC) {
				logger.info("transfer was already finished, transferorOrderNo: " + transferorOrder.getFundTransferorOrderNo());
				return;
			}

			// 获取产品
			ProdInfo product = fisIntService.queryProductCommonInfo(transferorOrder.getProductId());
			//获取产品结构信息
			ProdSummary productMix = fisIntService.getCloseProductMix(transferorOrder.getProductId());
			// 获取会员信息
	    	Set<String> memberNos = new HashSet<>();
	    	memberNos.add(transferorOrder.getMemberNo());
	    	memberNos.add(transfereeOrder.getMemberNo());
	    	Map<String, MemberIdentityBean> memberMap = memberIntService.getMemberByMemberNos(memberNos);
			
	    	// 确认并扣减转让者资产，追加受让者资产份额
	    	confirmTranferAsset(transfereeOrder, transferorOrder);
	    	
			// 更新受让单信息，防止并发处理sign contract结果信息
			FundTransfereeOrder updateOrder = new FundTransfereeOrder();
			updateOrder.setId(transfereeOrder.getId());
			updateOrder.setFundTransfereeOrderNo(transfereeOrder.getFundTransfereeOrderNo());
			updateOrder.setStatus(transfereeOrder.getStatus());
			fundTransferOrderActionService.updateFundTransfereeOrder(updateOrder, FundTransfereeOrderStatus.TRANS_SUC);
			
			// 更新订单
			transfereeOrder.setUpdateTime(updateOrder.getUpdateTime());
			transfereeOrder.setStatus(updateOrder.getStatus());
			
			// 更新转让单信息
			transferorOrder.setStatus(FundTransferorOrderStatus.TRANSFERING);
			fundTransferOrderActionService.updateFundTransferorOrder(transferorOrder, FundTransferorOrderStatus.TRANSFER_SUC);
			
			if (productMix.getTransStructureType().equals(TransStructureType.EXCHANGE)) {
				//更新交易所转让申请记录单状态
				FundTransferApplyToExchange apply = transferApplyService.queryApplication(
						transfereeOrder.getFundTransfereeOrderNo(), FundTransferExchangeStatus.TRANSFER_APPLY_SUCC);
				transferApplyService.updateApplication(apply, FundTransferExchangeStatus.TRANSFER_SUCC, null);
			}

	    	// 更新OFC业务受让单信息
	        ofcIntService.updateBusinessOrderItemAsync(transfereeOrder);
	    	// 更新OFC业务转让单信息
	        ofcIntService.updateBusinessOrderItemAsync(transferorOrder, product.getClearAccount());
	        
	        // 发送短信、站内信
			notifyTransfereeMessage(transfereeOrder, product.getProductName(), memberMap.get(transfereeOrder.getMemberNo()));
			notifyTransferorMessage(transferorOrder, product.getProductName(), memberMap.get(transferorOrder.getMemberNo()));
		}
	}

	@Override
	public void handleSignContractResult(InsertContractResp signContractResp, 
			FundTransfereeOrder transfereeOrder) {
//    	// 获取转让单信息
//    	FundTransferorOrder queryOrder = new FundTransferorOrder();
//    	queryOrder.setFundTransferorOrderNo(transfereeOrder.getRefFundTransferorOrderNo());
//		FundTransferorOrder transferorOrder = fundTransferorOrderMapper.selectOne(queryOrder);
//		if (transferorOrder == null) {
//			logger.error("cannot find transferorOrder by orderNo: " + transfereeOrder.getRefFundTransferorOrderNo());
//    		throw new BusinessException(FTCRespCode.ERR_FUND_TRANSFEROR_ORDER_NOT_EXIST.getMessage(),
//    				FTCRespCode.ERR_FUND_TRANSFEROR_ORDER_NOT_EXIST.getCode());
//		}
		if (!StringUtil.isEmpty(transfereeOrder.getContractNo())) {
			logger.info("contractNo was already exsit, orderNo: " + transfereeOrder.getFundTransfereeOrderNo());
			return;
		}

    	// 获取会员信息
    	Set<String> memberNos = new HashSet<>();
    	memberNos.add(transfereeOrder.getMemberNo());
    	Map<String, MemberIdentityBean> memberMap = memberIntService.getMemberByMemberNos(memberNos);
    	if (!memberMap.containsKey(transfereeOrder.getMemberNo())) {
    		logger.error("cannot get member info by memberNo: " + transfereeOrder.getMemberNo());
    		throw new BusinessException(FTCRespCode.ERR_FUND_QUERY_MEMBER_NOT_EXIST.getMessage(),
    				FTCRespCode.ERR_FUND_QUERY_MEMBER_NOT_EXIST.getCode());
    	}
    	
		// 写入合同信息,防止并发处理tc report结果信息
		FundTransfereeOrder updateOrder = new FundTransfereeOrder();
		updateOrder.setId(transfereeOrder.getId());
		updateOrder.setFundTransfereeOrderNo(transfereeOrder.getFundTransfereeOrderNo());
		updateOrder.setContractNo(signContractResp.getContractSn());
		fundTransferOrderActionService.updateFundTransfereeOrder(updateOrder);

		// 更新订单信息
		transfereeOrder.setContractNo(signContractResp.getContractSn());
		
    	// 签转让协议
    	contractIntService.signTransfereeAgreementAsync(transfereeOrder, memberMap.get(transfereeOrder.getMemberNo()));
	}

	/**
	 * 转让转账创建成功处理
	 * @param transferorOrder
	 * @param transfereeOrder
	 */
	private void transferCreateSuccess(FundTransferorOrder transferorOrder, 
			FundTransfereeOrder transfereeOrder) {
    	// 获取会员信息
    	Set<String> memberNos = new HashSet<>();
    	memberNos.add(transfereeOrder.getMemberNo());
    	Map<String, MemberIdentityBean> memberMap = memberIntService.getMemberByMemberNos(memberNos);
    	if (!memberMap.containsKey(transfereeOrder.getMemberNo())) {
    		logger.error("cannot get member info by memberNo: " + transfereeOrder.getMemberNo());
    		throw new BusinessException(FTCRespCode.ERR_FUND_QUERY_MEMBER_NOT_EXIST.getMessage(),
    				FTCRespCode.ERR_FUND_QUERY_MEMBER_NOT_EXIST.getCode());
    	}
    	
    	// 终止转让者合同
		String contractNo = getTransferorContractNo(transferorOrder.getMemberNo(), transferorOrder.getProductId());
    	contractIntService.terminateContract(contractNo, transfereeOrder.getTransferAgreementNo());
    	
//    	// 确认并扣减转让者资产，追加受让者资产份额
//    	confirmTranferAsset(transfereeOrder, transferorOrder);
    	
		// 更新订单信息
    	fundTransferOrderActionService.updateFundTransfereeOrder(transfereeOrder, transfereeOrder.getStatus());

    	// 签受让者合同
    	contractIntService.signContractAsync(getContractRequest(transfereeOrder, transferorOrder, 
    			memberMap.get(transfereeOrder.getMemberNo())));

    	// 确认转账
    	List<ReportTransEventBean> reportEventList = new ArrayList<>();
    	ReportTransEventBean transEvent = new ReportTransEventBean();
    	transEvent.setTransNo(transfereeOrder.getTransferTransNo());
    	transEvent.setTransEventType(TransactionEventType.SETTLE);
    	reportEventList.add(transEvent);
    	transcoreIntService.batchReportTransEventAsync(BusinessTransType.TRANSFER_CONFIRM, reportEventList);
	}

	/**
	 * 确认并扣减转让者资产，追加受让者资产份额
	 * @param transfereeOrder
	 * @param transferorOrder
	 */
	@Transactional(rollbackFor={RuntimeException.class, Exception.class})
	private void confirmTranferAsset(FundTransfereeOrder transfereeOrder,
			FundTransferorOrder transferorOrder) {
		// 确认并扣减转让者资产
		memberAssetService.unfreezeAndDeductMemberProductAsset(transferorOrder.getMemberNo(), 
				transferorOrder.getProductId(),transferorOrder.getFundTransferorOrderNo());
		
		// 追加受让者资产份额
		memberAssetService.createMemberProductAsset(transfereeOrder.getMemberNo(), 
				transfereeOrder.getMktProductId(), 
				transfereeOrder.getFundTransferAmount(), 
				transferorOrder.getOriginalAssetTotalValue().subtract(transfereeOrder.getFundTransferAmount()),
				transfereeOrder.getFundTransfereeOrderNo());
	}
	
	/**
	 * 获取转让者合同号
	 * @param memberNo
	 * @param productId
	 * @return
	 */
	private String getTransferorContractNo(String memberNo, Long productId) {
		String contractNo = "";
		// 获取产品 
		ProdInfo product = fisIntService.queryProductCommonInfo(productId);
		if (product.getMarketLevel().equals(MarketLevel.SECOND)) { // 二级市场产品
			FundTransfereeOrder queryOrder = new FundTransfereeOrder();
			queryOrder.setMemberNo(memberNo);
			queryOrder.setMktProductId(productId);
			queryOrder.setStatus(FundTransfereeOrderStatus.TRANS_SUC);
			List<FundTransfereeOrder> transfereeOrderList = fundTransfereeOrderMapper.select(queryOrder);
			if (CollectionUtils.isEmpty(transfereeOrderList)) {
				logger.error("cannot find transfereeOrder by memberNo: " + memberNo +
						", productId: " + productId);
	    		throw new BusinessException(FTCRespCode.ERR_FUND_TRANSFEREE_ORDER_NOT_EXIST.getMessage(),
	    				FTCRespCode.ERR_FUND_TRANSFEREE_ORDER_NOT_EXIST.getCode());
			}
			contractNo = transfereeOrderList.get(0).getContractNo();
		} else {	// 一级市场产品
			FundTransOrder queryOrder = new FundTransOrder();
			queryOrder.setProductId(productId);
			queryOrder.setMemberNo(memberNo);
			queryOrder.setStatus(FundTransOrderStatus.TA_TRANS_SUC);
			List<FundTransOrder> transOrderList = fundTransOrderMapper.select(queryOrder);
			if (CollectionUtils.isEmpty(transOrderList)) {
				logger.error("cannot find transOrder by memberNo: " + memberNo +
						", productId: " + productId);
	    		throw new BusinessException(FTCRespCode.ERR_FUND_TRANS_ORDER_NOT_FOUND.getMessage(),
	    				FTCRespCode.ERR_FUND_TRANS_ORDER_NOT_FOUND.getCode());
			}
			contractNo = transOrderList.get(0).getContractNo();
		}
		
		return contractNo;
	}
	
	/**
	 * 生成合同
	 * @param transfereeOrder
	 * @param transferorOrder
	 * @param member
	 * @return
	 */
	private InsertContractReq getContractRequest(FundTransfereeOrder transfereeOrder,
			FundTransferorOrder transferorOrder, MemberIdentityBean member) {
		// 获取产品详细信息 
		QueryProdTransInfoV2Resp productResp = fisIntService.queryProdTransInfoV2(transfereeOrder.getMktProductId());
		SecMrktProdTransInfo product = productResp.getSecMrktProdTransInfo();
		
		Date curDate = DateUtils.now();
		InsertContractReq contractRequest = new InsertContractReq();
		contractRequest.setBusinessCode(SignContractBizCode.TRANSFEREE);
		contractRequest.setOrderId(transfereeOrder.getFundTransfereeOrderNo());
        ContractBase base = new ContractBase();
        base.setIdcardType(member.getCertificationType());
        base.setIdcardNum(member.getCertificationNo());
        base.setUserName(member.getRealName());
        base.setContractMoney(transfereeOrder.getFundTransferAmount());
        base.setProductId(transfereeOrder.getMktProductId());
        base.setMarketLevel(MarketLevel.SECOND);
        base.setUserId(transfereeOrder.getMemberNo());
        base.setUserAccount(member.getLoginAlias());
        base.setCloseType(product.getCloseType());
        base.setSubproductId(StringUtil.isEmpty(transferorOrder.getRefFundSubCode()) ? "" : transferorOrder.getRefFundSubCode());
        base.setSignTime(curDate);
        base.setEffectTime(curDate);
        base.setTotalAmount(transfereeOrder.getFundTransferAmount());//产品总金额
        base.setLapseTime(product.getDueDate());//产品到期日
		contractRequest.setContractBase(base);
        
        ContractFinancing finance = new ContractFinancing();
        finance.setCurrency(transfereeOrder.getCurrencyType());
        finance.setProductLimit(product.getProductLimit());	//投资期限、剩余天数
        finance.setCloseType(product.getCloseType());
        finance.setIsPartSellable(product.getIsPartSell());
        finance.setIsProfitReinvest(product.getIsProfitReinvest());
        finance.setIsSellable(product.getIsSellable());
        finance.setIsSubscribeSellable(product.getIsSubscribeSell());
        finance.setMinHoldPeriod(product.getMinHoldPeriod());
        finance.setEarredeeRate(product.getAnnualYieldRate());//
        finance.setOverdueRate(product.getOverdueRate());
        finance.setValueDate(product.getValueDate());
        finance.setCreateTime(curDate);
        finance.setAnnualYieldRate(product.getAnnualYieldRate());
		finance.setPerYearDate(product.getPerYearDate());	//当年天数
        finance.setDueDate(product.getDueDate());			//到期日
        finance.setMaturityYield(transferorOrder.getOriginalAssetTotalValue().subtract(transfereeOrder.getFundTransferAmount()));//到期收益
		contractRequest.setContractFinancing(finance);
			//收益 = 投资金额 * 预计年化收益 * 产品天数 / 年总天数
//    		BigDecimal intrest = MoneyUtil.calcInvestmentIncome(transfereeOrder.getFundTransferAmount(), 
//					product.getAnnualYieldRate(), product.getProductLimit(), product.getPerYearDate());
		
		return contractRequest;
	}
	
	/**
	 * 发送受让者短信、站内信
	 * @param transfereeOrder
	 * @param productName
	 * @param member
	 */
	private void notifyTransfereeMessage(FundTransfereeOrder transfereeOrder, 
			String productName, MemberIdentityBean member) {
    	if (member == null) {
    		logger.error("cannot get member info by memberNo: " + transfereeOrder.getMemberNo());
    		throw new BusinessException(FTCRespCode.ERR_FUND_QUERY_MEMBER_NOT_EXIST.getMessage(),
    				FTCRespCode.ERR_FUND_QUERY_MEMBER_NOT_EXIST.getCode());
    	}
    	
    	BigDecimal amount = transfereeOrder.getFundTransferAmount().setScale(
    			MoneyUtil.DISPLAY_ACCURACY, BigDecimal.ROUND_DOWN);
    	
    	// 受让者短信、站内信
    	Map<String, String> smsParams = new HashMap<>();
		smsParams.put(ParamKeys.PRODUCT_NAME.getName(), productName);
		smsParams.put(ParamKeys.AMOUNT.getName(), amount.toString());
//		List<String> inboxParams = new ArrayList<>();
//		inboxParams.add(productName);
//		inboxParams.add(amount.toString());
		
		// 发送受让者短信
		goutongIntService.sendSmsAsync(transfereeOrder.getBizChannel(), 
				BusinessIDType.PM_TRANSFEREE_ORDER_PAY_SUCCESS.getId(),
				smsParams, member.getVerifiedMobile());
		goutongIntService.sendInboxMessage(transfereeOrder.getBizChannel(), 
				BusinessIDType.PM_TRANSFEREE_ORDER_PAY_SUCCESS.getId(),
				smsParams, transfereeOrder.getMemberNo());
	}

	/**
	 * 发送转让者短信、站内信
	 * @param transferorOrder
	 * @param productName
	 * @param member
	 */
	private void notifyTransferorMessage(FundTransferorOrder transferorOrder, 
			String productName, MemberIdentityBean member) {
    	if (member == null) {
    		logger.error("cannot get member info by memberNo: " + transferorOrder.getMemberNo());
    		throw new BusinessException(FTCRespCode.ERR_FUND_QUERY_MEMBER_NOT_EXIST.getMessage(),
    				FTCRespCode.ERR_FUND_QUERY_MEMBER_NOT_EXIST.getCode());
    	}

    	BigDecimal amount = transferorOrder.getFundTransferAmount().setScale(
    			MoneyUtil.DISPLAY_ACCURACY, BigDecimal.ROUND_DOWN);
    	BigDecimal fee = transferorOrder.getTransferorFee().setScale(
    			MoneyUtil.DISPLAY_ACCURACY, BigDecimal.ROUND_DOWN);
    	// 转让者短信、站内信
    	Map<String, String> smsParams = new HashMap<>();
		smsParams.put(ParamKeys.PRODUCT_NAME.getName(), productName);
		smsParams.put(ParamKeys.AMOUNT.getName(), amount.toString());
		smsParams.put(ParamKeys.FEE.getName(), fee.toString());
//		List<String> inboxParams = new ArrayList<>();
//		inboxParams.add(productName);
//		inboxParams.add(amount.toString());
//		inboxParams.add(fee.toString());
		
		// 发送转让者短信
		goutongIntService.sendSmsAsync(transferorOrder.getBizChannel(), 
				BusinessIDType.PM_TRANSFER_ORDER_PAY_SUCCESS.getId(),
				smsParams, member.getVerifiedMobile());
		goutongIntService.sendInboxMessage(transferorOrder.getBizChannel(), 
				BusinessIDType.PM_TRANSFER_ORDER_PAY_SUCCESS.getId(),
				smsParams, transferorOrder.getMemberNo());
	}

}
