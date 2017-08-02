package com.eif.ftc.integration.impl.transcore;


import com.eif.framework.common.utils.code.CommonRspCode;
import com.eif.framework.common.utils.constant.BizSysCode;
import com.eif.framework.common.utils.constant.CurrencyISOCode;
import com.eif.framework.mq.DefaultRMQProducer;
import com.eif.framework.mq.common.MessageWrapper;
import com.eif.ftc.dal.model.FundTransOrder;
import com.eif.ftc.dal.model.FundTransfereeOrder;
import com.eif.ftc.dal.model.FundTransferorOrder;
import com.eif.ftc.facade.code.FTCRespCode;
import com.eif.ftc.facade.fund.trans.enumeration.FundTransType;
import com.eif.ftc.integration.transcore.TranscoreIntService;
import com.eif.ftc.integration.util.ResponseMapper;
import com.eif.ftc.util.exception.BusinessException;
import com.eif.ftc.util.uuid.UUIDGenerator;
import com.eif.paycore.facade.dto.enumeration.PaymentInstrumentType;
import com.eif.transcore.facade.TransactionFacade;
import com.eif.transcore.facade.dto.bean.*;
import com.eif.transcore.facade.dto.enumeration.CommissionFeeType;
import com.eif.transcore.facade.dto.enumeration.SmsStrategy;
import com.eif.transcore.facade.dto.enumeration.TransAttribute;
import com.eif.transcore.facade.dto.enumeration.TransCode;
import com.eif.transcore.facade.dto.enumeration.TransactionStatus;
import com.eif.transcore.facade.mq.MQTransConstants;
import com.eif.transcore.facade.request.*;
import com.eif.transcore.facade.response.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;

import java.util.List;

/**
 * Created by bohan on 1/11/16.
 */
@Service("transcoreIntService")
public class TranscoreIntServiceImpl implements TranscoreIntService {

    @Resource(name = "transactionFacade")
    private TransactionFacade transactionFacade;

    @Resource(name = "defaultRMQProducer")
    private DefaultRMQProducer defaultRMQProducer;

    private static Logger logger = LoggerFactory.getLogger(TranscoreIntServiceImpl.class);

    public CreateTransResponse createTransCoreOrder(FundTransOrder transOrder, String transCode, List<TransactionPaymentOptionBean> paymentOptions, String transAttri,String productName) {
        return createTransCoreOrder(transOrder, transCode, paymentOptions, transAttri, null, null, null,productName);
    }

    public CreateTransResponse createTransCoreOrder(FundTransOrder transOrder, String transCode,
                                                    List<TransactionPaymentOptionBean> paymentOptions,
                                                    String transAttri, String extField, String refTransNo,
                                                    RoutePaymentProviderInfoResponse paymentRoutingResp,String productName) {
        CreateTransRequest transRequest = buildCreateTransRequest(transOrder, transCode,
                paymentOptions,
                transAttri, extField, refTransNo,
                paymentRoutingResp,productName);

        CreateTransResponse createTransResp = transactionFacade.createTrans(transRequest);
        if (!createTransResp.isSuccess()) {
            ResponseMapper.wrapBusinessException(createTransResp.getRespCode(), createTransResp.getMsg());
        }

        return createTransResp;
    }

    public void reportTranEvent(ReportTransEventRequest transEventReq) {
        ReportTransEventResponse reportEventResp = transactionFacade.reportTranEvent(transEventReq);
        if (!reportEventResp.getRespCode().equals(CommonRspCode.SUCCESS.getCode())) {
            ResponseMapper.wrapBusinessException(reportEventResp.getRespCode(), reportEventResp.getMsg());
        }
    }

    public void closeTransaction(String transCoreOrderNo) {
        CloseTransactionRequest closeTransactionRequest = new CloseTransactionRequest();
        closeTransactionRequest.setTransNo(transCoreOrderNo);
        CloseTransactionResponse resp = transactionFacade.closeTransaction(closeTransactionRequest);

        if (!resp.isSuccess()) {
            ResponseMapper.wrapBusinessException(resp.getRespCode(), resp.getMsg());
        }
    }

    public List<TransactionDTO> getAuthPendingRechargeOrder(String fundTransOrderNo) {
        GetTransDtosByBizOrderNoRequest getTransDtosByBizOrderNoRequest = new GetTransDtosByBizOrderNoRequest();
        getTransDtosByBizOrderNoRequest.setBizOrderNo(fundTransOrderNo);
        getTransDtosByBizOrderNoRequest.setTransCode(TransCode.CHARGE);
        getTransDtosByBizOrderNoRequest.setTransStatus(TransactionStatus.AUTH_PENDING);

        GetTransDtosResponse resp = transactionFacade.getTransDtosByBizOrderNo(getTransDtosByBizOrderNoRequest);
        if (!resp.isSuccess() || resp.getTransDtos() == null) {
            throw new BusinessException(FTCRespCode.ERR_FUND_TRANSCORE_GET.getMessage(), FTCRespCode.ERR_FUND_TRANSCORE_GET.getCode());
        }

        return resp.getTransDtos();
    }

    public ResumePayTransResponse resumePayTrans(ResumePayTransResquest request) {
        ResumePayTransResponse resp = transactionFacade.resumePayTrans(request);

        if (!resp.isSuccess()) {
            ResponseMapper.wrapBusinessException(resp.getRespCode(), resp.getMsg());
        }

        return resp;
    }

    @Override
    public RoutePaymentProviderInfoResponse paymentRouting(RoutePaymentProviderInfoRequest request) {
        RoutePaymentProviderInfoResponse resp = transactionFacade.routePaymentProviderInfo(request);
        if (!resp.isSuccess()) {
            ResponseMapper.wrapBusinessException(resp.getRespCode(), resp.getMsg());
        }

        return resp;
    }

    public void createTransAsync(FundTransOrder transOrder, String transCode, List<TransactionPaymentOptionBean> paymentOptions, String transAttri,String productName) {
        createTransAsync(transOrder, transCode, paymentOptions, transAttri, null, null, null,productName);
    }

    public void createTransAsync(FundTransOrder transOrder, String transCode,
                                 List<TransactionPaymentOptionBean> paymentOptions,
                                 String transAttri, String extField, String refTransNo,
                                 RoutePaymentProviderInfoResponse paymentRoutingResp,String productName) {

        CreateTransRequest transRequest = buildCreateTransRequest(transOrder, transCode,
                paymentOptions,
                transAttri, extField, refTransNo,
                paymentRoutingResp,productName);

        MessageWrapper msg = new MessageWrapper(MQTransConstants.TOPIC_CREATE_TRANS_REQUEST, null, transOrder.getFundTransOrderNo(), transRequest);

        try {
            defaultRMQProducer.sendWithRetry(msg);
        } catch (Exception e) {
            logger.error("send TOPIC_CREATE_TRANS_REQUEST message err, exception is", e);
        }
    }


    private CreateTransRequest buildCreateTransRequest(FundTransOrder transOrder, String transCode,
                                                       List<TransactionPaymentOptionBean> paymentOptions,
                                                       String transAttri, String extField, String refTransNo,
                                                       RoutePaymentProviderInfoResponse paymentRoutingResp,String productName) {
        CreateTransRequest transRequest = new CreateTransRequest();
        transRequest.setOperatorNo(transOrder.getOperatorNo());
        CreateTransDTO dto = new CreateTransDTO();
        dto.setActAmount(transOrder.getFundTransAmount());
        dto.setBizChannel(transOrder.getBizChannel());
        dto.setCurrency(CurrencyISOCode.CHINA);
        dto.setIsRegulated(transOrder.getIsAdjusted());
        dto.setMemberNo(transOrder.getMemberNo());
        dto.setTotalAmount(transOrder.getFundTransAmount());
        dto.setTransCode(transCode);
        dto.setTransTime(transOrder.getUpdateTime());
        dto.setTransAttribute(transAttri);
        dto.setTrackingNo(UUIDGenerator.gen());
        dto.setRefTransNo(refTransNo);
        dto.setOrderNo(transOrder.getBusinessOrderItemNo());
        dto.setBizOrderNo(transOrder.getFundTransOrderNo());
        dto.setBizSysCode(BizSysCode.FTC_SYSTEM);
        dto.setBizProductNo(transOrder.getProductId().toString());
        dto.setBizProductName(productName);
        dto.setExtField(extField);
        dto.setBizTransType(transOrder.getFundTransType());

        //预路由结果信息设置
        if (paymentRoutingResp != null) {
            transRequest.setSmsStrategy(paymentRoutingResp.getSmsStrategy());
            for (TransactionPaymentOptionBean paymentOption : paymentOptions) {
                if (paymentOption.getPaymentInstrumentType() == PaymentInstrumentType.DCP) {
                    paymentOption.setProviderNo(paymentRoutingResp.getProviderNo());
                }
            }
        }
        dto.setPaymentOptions(paymentOptions);
        transRequest.setTransDto(dto);

        return transRequest;
    }

	@Override
	public void createTranscoreOrderAsync(CreateTransDTO createTransBean,
			SmsStrategy smsStrategy, String operatorNo) {
		CreateTransRequest transRequest = new CreateTransRequest();
		transRequest.setSmsStrategy(smsStrategy);
		transRequest.setTransDto(createTransBean);
		transRequest.setOperatorNo(operatorNo);
		
		MessageWrapper msg = new MessageWrapper(MQTransConstants.TOPIC_CREATE_TRANS_REQUEST, 
				null, createTransBean.getBizOrderNo(), transRequest);
        try {
            defaultRMQProducer.sendWithRetry(msg);
        } catch (Exception e) {
            logger.error("send TOPIC_CREATE_TRANS_REQUEST message err, exception is", e);
        }
	}

	@Override
    public CreateTransResponse createTransCoreOrder(FundTransfereeOrder transfereeOrder, 
										    		String transCode, String transAttri, 
										    		List<TransactionPaymentOptionBean> paymentOptions,
										            String productName, String extField, 
										            RoutePaymentProviderInfoResponse paymentRoutingResp) {
        CreateTransRequest transRequest = new CreateTransRequest();
        CreateTransDTO transDTO = buildTranscoreCreateTransDTO(transfereeOrder, 
        		transCode, transAttri, paymentOptions, productName, extField, null, paymentRoutingResp);
        transRequest.setTransDto(transDTO);
        transRequest.setOperatorNo(transfereeOrder.getOperatorNo());
        
        CreateTransResponse createTransResp = transactionFacade.createTrans(transRequest);
        if (!createTransResp.isSuccess()) {
            ResponseMapper.wrapBusinessException(createTransResp.getRespCode(), createTransResp.getMsg());
        }

        return createTransResp;
    }

	@Override
	public CreateTransDTO buildTranscoreCreateTransDTO(FundTransfereeOrder transfereeOrder, String transCode,
			String transAttri, List<TransactionPaymentOptionBean> paymentOptions, String productName, String extField, 
			String refTransNo, RoutePaymentProviderInfoResponse paymentRoutingResp) {
		CreateTransDTO transDTO = new CreateTransDTO();
        transDTO.setActAmount(transfereeOrder.getFundTransferAmount());
        transDTO.setBizChannel(transfereeOrder.getBizChannel());
        transDTO.setCurrency(CurrencyISOCode.CHINA);
        transDTO.setIsRegulated(false);
        transDTO.setMemberNo(transfereeOrder.getMemberNo());
        transDTO.setTotalAmount(transfereeOrder.getFundTransferAmount());
        transDTO.setTransCode(transCode);
        transDTO.setTransTime(transfereeOrder.getUpdateTime());
        transDTO.setTransAttribute(transAttri);
        transDTO.setTrackingNo(UUIDGenerator.gen());
        transDTO.setRefTransNo(refTransNo);
        transDTO.setOrderNo(transfereeOrder.getBusinessOrderItemNo());
        transDTO.setBizOrderNo(transfereeOrder.getFundTransfereeOrderNo());
        transDTO.setBizSysCode(BizSysCode.FTC_SYSTEM);
        transDTO.setBizProductNo(transfereeOrder.getMktProductId().toString());
        transDTO.setBizProductName(productName);
        transDTO.setExtField(extField);
        transDTO.setBizTransType(FundTransType.TRANSFEREE);

        // 手续费
        if (transAttri.equals(TransAttribute.FUND)) {
        	transDTO.setCommissionFeeType(CommissionFeeType.NORMAL);
        	transDTO.setPayeeCommissionFee(transfereeOrder.getFee());
        }
        
        // 预路由结果信息设置
        if (paymentRoutingResp != null) {
            for (TransactionPaymentOptionBean paymentOption : paymentOptions) {
                if (paymentOption.getPaymentInstrumentType() == PaymentInstrumentType.DCP) {
                    paymentOption.setProviderNo(paymentRoutingResp.getProviderNo());
                }
            }
        }
        transDTO.setPaymentOptions(paymentOptions);
        
		return transDTO;
	}

	@Override
	public CreateTransDTO buildTranscoreCreateTransDTO(FundTransferorOrder transferorOrder, 
			String transCode, String transAttri, 
			List<TransactionPaymentOptionBean> paymentOptions,
			String productName, String extField) {
		CreateTransDTO transDTO = new CreateTransDTO();
        transDTO.setActAmount(transferorOrder.getFundTransferAmount());
        transDTO.setBizChannel(transferorOrder.getBizChannel());
        transDTO.setCurrency(CurrencyISOCode.CHINA);
        transDTO.setIsRegulated(false);
        transDTO.setMemberNo(transferorOrder.getMemberNo());
        transDTO.setTotalAmount(transferorOrder.getFundTransferAmount());
        transDTO.setTransCode(transCode);
        transDTO.setTransTime(transferorOrder.getUpdateTime());
        transDTO.setTransAttribute(transAttri);
        transDTO.setTrackingNo(UUIDGenerator.gen());
//        transDTO.setRefTransNo("");
        transDTO.setOrderNo(transferorOrder.getBusinessOrderItemNo());
        transDTO.setBizOrderNo(transferorOrder.getFundTransferorOrderNo());
        transDTO.setBizSysCode(BizSysCode.FTC_SYSTEM);
        transDTO.setBizProductNo(transferorOrder.getMktProductId().toString());
        transDTO.setBizProductName(productName);
        transDTO.setExtField(extField);
        transDTO.setBizTransType(FundTransType.TRANSFEREE);

        transDTO.setPaymentOptions(paymentOptions);

        // 手续费
    	transDTO.setCommissionFeeType(CommissionFeeType.NORMAL);
    	transDTO.setPayeeCommissionFee(transferorOrder.getTransferorFee());
        
		return transDTO;
	}
	
	@Override
	public void batchReportTransEventAsync(int businessTransType, List<ReportTransEventBean> reportTransEventBeanList) {
		if (CollectionUtils.isEmpty(reportTransEventBeanList)) 
			return;
		
		BatchReportTransEventRequest batchReportTransEventRequest = new BatchReportTransEventRequest();
		batchReportTransEventRequest.setBusiType(businessTransType);
        batchReportTransEventRequest.setTransEvents(reportTransEventBeanList);
        
        MessageWrapper msg = new MessageWrapper(MQTransConstants.TOPIC_TRANS_BATCH_REPORT_EVENT_REQUEST,// topic
                null,// tag
                null,// key
                batchReportTransEventRequest);
        try {
            defaultRMQProducer.sendWithRetry(msg);
        } catch (Throwable e) {
            logger.error("send report transcore message err, exception is", e);
        }
	}

}
