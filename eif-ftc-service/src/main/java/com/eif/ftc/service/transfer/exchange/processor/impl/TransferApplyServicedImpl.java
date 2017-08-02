package com.eif.ftc.service.transfer.exchange.processor.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.http.conn.ConnectTimeoutException;
import org.apache.http.protocol.HTTP;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.dubbo.common.utils.CollectionUtils;
import com.alibaba.fastjson.JSON;
import com.eif.ftc.dal.dao.FundTransferApplyToExchangeMapper;
import com.eif.ftc.dal.dao.FundTransferApplyToExchangeStatusInfoMapper;
import com.eif.ftc.dal.dao.FundTransferorOrderMapper;
import com.eif.ftc.dal.model.FundTransferApplyToExchange;
import com.eif.ftc.dal.model.FundTransferApplyToExchangeStatusInfo;
import com.eif.ftc.dal.model.FundTransfereeOrder;
import com.eif.ftc.dal.model.FundTransferorOrder;
import com.eif.ftc.facade.code.FTCRespCode;
import com.eif.ftc.facade.fund.transfer.enumeration.FundTransferExchangeStatus;
import com.eif.ftc.integration.member.MemberIntService;
import com.eif.ftc.service.transfer.exchange.ExchangeConfig;
import com.eif.ftc.service.transfer.exchange.bean.req.TransferApplicationBean;
import com.eif.ftc.service.transfer.exchange.bean.req.TransferBean;
import com.eif.ftc.service.transfer.exchange.bean.req.TransferIntentBean;
import com.eif.ftc.service.transfer.exchange.bean.req.TransferPairingsBean;
import com.eif.ftc.service.transfer.exchange.bean.req.TransfereeBean;
import com.eif.ftc.service.transfer.exchange.bean.req.TransferorBean;
import com.eif.ftc.service.transfer.exchange.bean.resp.TransferApplyResultBean;
import com.eif.ftc.service.transfer.exchange.bean.resp.TransferStatusCode;
import com.eif.ftc.service.transfer.exchange.processor.TransferApplyService;
import com.eif.ftc.util.AuthSignatrue;
import com.eif.ftc.util.DateTimeUtils;
import com.eif.ftc.util.HttpUtils;
import com.eif.ftc.util.MoneyUtil;
import com.eif.ftc.util.exception.BusinessException;
import com.eif.ftc.util.uuid.UUIDGenerator;
import com.eif.member.facade.pkg.dto.responseDTO.MemberIdentityBean;
import com.github.knightliao.apollo.utils.time.DateUtils;

@Service("transferApplyService")
public class TransferApplyServicedImpl implements TransferApplyService {

	private static Logger logger = LoggerFactory.getLogger(TransferApplyServicedImpl.class);

//	private static Map<String, String> exchange_code_map = new HashMap<>();
//	static {
//		exchange_code_map.put("普惠金融交易中心", "puhui");
//		exchange_code_map.put("广东金融高新区股权交易中心有限公司", "guangjiaosuo");
//	}
	
	@Autowired
	private ExchangeConfig exchangeConfig;
	
    @Resource
    private MemberIntService memberIntService;

    @Autowired
    private FundTransferorOrderMapper fundTransferorOrderMapper;
    
    @Autowired
    private FundTransferApplyToExchangeMapper fundTransferApplyToExchangeMapper;
    
    @Autowired
    private FundTransferApplyToExchangeStatusInfoMapper fundTransferApplyToExchangeStatusInfoMapper;
    
	@Override
	public void applyTransfer(String exchangeCode, String exchangeProdNo, FundTransfereeOrder transfereeOrder) {
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
		// 获取原始转让单
		queryOrder.setFundTransferorOrderNo(transfereeOrder.getRefOriginFundTransferorOrderNo());
		FundTransferorOrder originTransferorOrder = fundTransferorOrderMapper.selectOne(queryOrder);
		if (originTransferorOrder == null) {
			logger.error("cannot find transferorOrder by refOriginFundTransferorOrderNo: " +
					transfereeOrder.getRefOriginFundTransferorOrderNo());
			throw new BusinessException(FTCRespCode.ERR_FUND_TRANSFEROR_ORDER_NOT_EXIST.getMessage(), 
					FTCRespCode.ERR_FUND_TRANSFEROR_ORDER_NOT_EXIST.getCode());
		}
				
		//获取会员信息
		Set<String> memberNos = new HashSet<>();
		memberNos.add(transferorOrder.getMemberNo());
		memberNos.add(transfereeOrder.getMemberNo());
		Map<String, MemberIdentityBean> memberMap = memberIntService.getMemberByMemberNos(memberNos);
		if (memberMap.size() < 2) {
			logger.error("cannot find member info by memberNos: " + JSON.toJSONString(memberNos));
			throw new BusinessException(FTCRespCode.ERR_FUND_QUERY_MEMBER_NOT_EXIST.getMessage(), 
					FTCRespCode.ERR_FUND_QUERY_MEMBER_NOT_EXIST.getCode());
		}

		//生成请求
		TransferApplicationBean application = getTransferApplication(exchangeCode, exchangeProdNo,
				transferorOrder, transfereeOrder, originTransferorOrder, memberMap);
		//插入数据库
		FundTransferApplyToExchange apply = insertApplication(application, 
				originTransferorOrder.getProductId(), transfereeOrder.getMktProductId(),
				transferorOrder.getMemberNo(), transfereeOrder.getMemberNo(), 
				transfereeOrder.getPayTime());
		
		//发送请求
		TransferApplyResultBean result = null;
		try {
			result = sendApplyWithRetry(application);
			if (result.getStatus_code() != TransferStatusCode.SUCCESS) {
				logger.error("apply to exchange failed, transfereeOrderNo:{}, result:{}",
						transfereeOrder.getFundTransfereeOrderNo(), JSON.toJSONString(result));
				throw new BusinessException(FTCRespCode.ERR_FUND_TRANSFER_EXCHANGE_FAIL.getMessage(),
						FTCRespCode.ERR_FUND_TRANSFER_EXCHANGE_FAIL.getCode());
			}
		} catch (Exception e) {
			//更新失败结果
			updateApplication(apply, FundTransferExchangeStatus.TRANSFER_FAIL, result);
			throw e;
		}

		//更新成功结果
		updateApplication(apply, FundTransferExchangeStatus.TRANSFER_APPLY_SUCC, result);
	}
	
	/**
	 * 生成交易所转让申请
	 * @param exchangeCode
	 * @param exchangeProdNos
	 * @param transferorOrder
	 * @param transfereeOrder
	 * @param originTransferorOrder
	 * @param memberMap
	 * @return
	 */
	private TransferApplicationBean getTransferApplication(String exchangeCode, String exchangeProdNo,
			FundTransferorOrder transferorOrder,
			FundTransfereeOrder transfereeOrder, 
			FundTransferorOrder originTransferorOrder,
			Map<String, MemberIdentityBean> memberMap) {
		TransferApplicationBean application = new TransferApplicationBean();
		application.setExchange_code(exchangeCode);
		application.setProduct_code(exchangeProdNo);//originTransferorOrder.getProductId().toString());
		application.setOp_id(UUIDGenerator.gen());
		application.setExecuted_at(DateUtils.formatDate(transfereeOrder.getPayTime(), "yyyy-MM-dd'T'HH:mm:ss:SSZ"));
		//转让申请信息
		TransferBean transfer = new TransferBean();
		transfer.setApply_id(transferorOrder.getFundTransferorOrderNo());
		transfer.setAmount(originTransferorOrder.getFundTransferPrincipal().toBigInteger());
		transfer.setPrice(transfereeOrder.getFundTransferAmount().setScale(
    			MoneyUtil.DISPLAY_ACCURACY, BigDecimal.ROUND_DOWN).toString());
		transfer.setAnnual_rate(transferorOrder.getAnnualYield().toString());
		//转让人信息
		TransferorBean transferor = new TransferorBean();
		transferor.setName(memberMap.get(transferorOrder.getMemberNo()).getRealName());
		transferor.setIdentity_id(memberMap.get(transferorOrder.getMemberNo()).getCertificationNo());
		transfer.setTransferor(transferor);
		//设置申请信息
		application.setTransfer(transfer);
		
		//
		TransferPairingsBean pair = new TransferPairingsBean();
		//受让人信息
		TransfereeBean transferee = new TransfereeBean();
		transferee.setName(memberMap.get(transfereeOrder.getMemberNo()).getRealName());
		transferee.setIdentity_id(memberMap.get(transfereeOrder.getMemberNo()).getCertificationNo());
		String riskLevel = memberMap.get(transfereeOrder.getMemberNo()).getRiskLevel();
		if (riskLevel == null || riskLevel.isEmpty()) {
			riskLevel = "ML";
		}
		transferee.setRisk_level(riskLevel);
		pair.setTransferee(transferee);
		//受让信息
	    TransferIntentBean transferIntent = new TransferIntentBean();
	    transferIntent.setApply_id(transferorOrder.getFundTransferorOrderNo());
	    transferIntent.setIntent_id(transfereeOrder.getFundTransfereeOrderNo());
	    transferIntent.setAmount(originTransferorOrder.getFundTransferPrincipal().toBigInteger());
	    transferIntent.setPrice(transfereeOrder.getFundTransferAmount().setScale(
    			MoneyUtil.DISPLAY_ACCURACY, BigDecimal.ROUND_DOWN).toString());
	    transferIntent.setFee(transfereeOrder.getFee().setScale(
    			MoneyUtil.DISPLAY_ACCURACY, BigDecimal.ROUND_DOWN).toString());
		pair.setTransfer_intent(transferIntent);

		List<TransferPairingsBean> pairs = new ArrayList<>();
		pairs.add(pair);
		//设置受让信息
		application.setTransfer_pairings(pairs);
		
		return application;
	}

	/**
	 * 发送请求
	 * @param application
	 * @return
	 */
	private TransferApplyResultBean sendApplyWithRetry(TransferApplicationBean application) {
		int count = 0;
		TransferApplyResultBean result = null;
		while (true) {
			try {
				result = sendApply(application);
			} catch (BusinessException be) {
				if (be.getCode().equals(FTCRespCode.ERR_FUND_TRANSFER_EXCHANGE_TIMEOUT.getCode())) {
					if (count++ < 2) {
						continue;
					}
				}
				
				throw be;
			}
			break;
		}
		
		return result;
	}

	/**
	 * 发送转让请求
	 * @param application
	 * @return
	 */
	private TransferApplyResultBean sendApply(TransferApplicationBean application) {
		String gmtDate = DateTimeUtils.getHttpHeaderDate(new Date());
		String url = exchangeConfig.getTransferDomain() + exchangeConfig.getApplyUrl();
		
		String signature = "";
		try {
			Map<String, String> headers = new HashMap<>();
			signature = AuthSignatrue.getSignatrue(gmtDate.getBytes(AuthSignatrue.ENCODING),
					exchangeConfig.getAccessKey().getBytes(AuthSignatrue.ENCODING));
			String authToken = exchangeConfig.getAccessId() + ":" + signature;
			headers.put(HTTP.DATE_HEADER, gmtDate);
			headers.put("Authorization", authToken);
			
			String resp = HttpUtils.post(url, JSON.toJSONString(application), headers);
			return JSON.parseObject(resp, TransferApplyResultBean.class);
		} catch (ConnectTimeoutException e1) {
			logger.error("connect to server timeout, url: " + url, e1);
			throw new BusinessException(FTCRespCode.ERR_FUND_TRANSFER_EXCHANGE_TIMEOUT.getMessage(),
					FTCRespCode.ERR_FUND_TRANSFER_EXCHANGE_TIMEOUT.getCode());
		} catch (Exception e) {
			logger.error("send apply to exchange error, date: {}, signature: {}", gmtDate, signature, e);
			throw new BusinessException(FTCRespCode.ERR_FUND_TRANSFER_EXCHANGE_FAIL.getMessage(),
					FTCRespCode.ERR_FUND_TRANSFER_EXCHANGE_FAIL.getCode());
		}
		
	}

	/**
	 * 插入并返回id
	 * @param application
	 * @param productId
	 * @param mktProductId
	 * @param memberNoFrom
	 * @param memberNoTo
	 * @param transferTime
	 * @return
	 */
	private FundTransferApplyToExchange insertApplication(TransferApplicationBean application, 
			Long productId, Long mktProductId,
			String memberNoFrom, String memberNoTo, Date transferTime) {
		FundTransferApplyToExchange apply = new FundTransferApplyToExchange();
		apply.setFundTransfereeOrderNo(application.getTransfer_pairings().get(0).getTransfer_intent().getIntent_id());
		apply.setFundTransferorOrderNo(application.getTransfer().getApply_id());
		apply.setOpId(application.getOp_id());
		apply.setExchangeProdNo(application.getProduct_code());
		apply.setProductId(productId);
		apply.setMktProductId(mktProductId);
		apply.setMemberNoFrom(memberNoFrom);
		apply.setMemberNoTo(memberNoTo);
		apply.setStatus(FundTransferExchangeStatus.TRANSFERING);
		apply.setTransferTime(transferTime);
		apply.setCreateTime(new Date());
		apply.setUpdateTime(new Date());
		fundTransferApplyToExchangeMapper.insertSelective(apply);
		
		return apply;
	}

	@Override
	public void updateApplication(FundTransferApplyToExchange apply, byte status, TransferApplyResultBean result) {
		FundTransferApplyToExchange updateApply = new FundTransferApplyToExchange();
		updateApply.setId(apply.getId());
		updateApply.setStatus(status);
		updateApply.setUpdateTime(new Date());
		if (result != null) {
			updateApply.setExchangeExecutionTime(result.getExecuted_at());
			if (result.getExecution_response() != null) {
				if (result.getExecution_response().getTransfer_pairings() != null) {
					updateApply.setExchangeMatchingId(result.getExecution_response().getTransfer_pairings().getTransfer_pairing_id());
					if (!CollectionUtils.isEmpty(result.getExecution_response().getTransfer_pairings().getIntents())) {
						updateApply.setExchangeTransfereeId(result.getExecution_response().getTransfer_pairings().getIntents().get(0).getResponse().getTransac_id());
					}
				}
				updateApply.setExchangeTransferorId(result.getExecution_response().getTransfer().getTransfer_id());
			}
		}
		fundTransferApplyToExchangeMapper.updateByPrimaryKeySelective(updateApply);
		
		FundTransferApplyToExchangeStatusInfo statusInfo = new FundTransferApplyToExchangeStatusInfo();
		statusInfo.setOrderId(apply.getId());
		statusInfo.setStatus(status);
		statusInfo.setUpdateTime(new Date());
		fundTransferApplyToExchangeStatusInfoMapper.insertSelective(statusInfo);
	}

	@Override
	public FundTransferApplyToExchange queryApplication(String transfereeOrderNo, byte status) {
		FundTransferApplyToExchange apply = new FundTransferApplyToExchange();
		apply.setFundTransfereeOrderNo(transfereeOrderNo);
		apply.setStatus(status);
		return fundTransferApplyToExchangeMapper.selectOne(apply);
	}
	
}
