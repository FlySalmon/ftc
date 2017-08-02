package com.eif.ftc.integration.impl.risk;

import com.eif.fis.facade.dto.common.TagRules;
import com.eif.framework.mq.DefaultRMQProducer;
import com.eif.framework.mq.common.MessageWrapper;
import com.eif.ftc.dal.model.FundTransOrder;
import com.eif.ftc.dal.model.FundTransfereeOrder;
import com.eif.ftc.dal.model.FundTransferorOrder;
import com.eif.ftc.facade.code.FTCRespCode;
import com.eif.ftc.facade.fund.trans.enumeration.FundTransType;
import com.eif.ftc.integration.mq.MQMessageSender;
import com.eif.ftc.integration.risk.RiskIntService;
import com.eif.ftc.integration.util.ResponseMapper;
import com.eif.ftc.util.exception.BusinessException;
import com.eif.risk.facade.ErrorCountFacade;
import com.eif.risk.facade.QuotaStatFacade;
import com.eif.risk.facade.RiskCheckFacade;
import com.eif.risk.facade.bean.AllUserLockBean;
import com.eif.risk.facade.bean.FinTransBean;
import com.eif.risk.facade.bean.TransDataBean;
import com.eif.risk.facade.constant.MQTopicName;
import com.eif.risk.facade.constant.RiskCheckResult;
import com.eif.risk.facade.request.*;
import com.eif.risk.facade.response.*;
import com.eif.transcore.facade.dto.enumeration.TransCode;

import ma.glasnost.orika.MapperFacade;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;

/**
 * Created by bohan on 1/20/16.
 */
@Service
public class RiskIntServiceImpl implements RiskIntService {

    private static Logger logger = LoggerFactory.getLogger(RiskIntServiceImpl.class);
    @Resource(name = "riskCheckFacade")
    RiskCheckFacade riskCheckFacade;

    @Autowired
    MQMessageSender mqMessageSender;

    @Autowired
    MapperFacade mapperFacade;

    @Resource(name = "quotaStatFacade")
    QuotaStatFacade quotaStatFacade;

    @Resource(name = "errorCountFacade")
    ErrorCountFacade errorCountFacade;

    @Resource(name = "defaultRMQProducer")
    private DefaultRMQProducer defaultRMQProducer;


    @Override
    public void riskCheckTransIng(FundTransOrder fundTransOrder, String checkpoint, String deviceInfo, String devId, String ip) {
        RiskCheckReq riskCheckReq = new RiskCheckReq();
        riskCheckReq.setCheckPoint(checkpoint);
        riskCheckReq.setFinTransData(getFinTransBeanFromTransOrder(fundTransOrder, deviceInfo, devId, ip));
        RiskCheckResp riskCheckResp = null;
        try {
            riskCheckResp = riskCheckFacade.riskCheck(riskCheckReq);
        }
        catch (Exception e) {
            logger.error("risk call is failed! Exception is", e);
        }

        if (riskCheckResp != null && riskCheckResp.isSuccess()) {
            if (riskCheckResp.getRcResult().equals(RiskCheckResult.REJECT)) {
                throw new BusinessException(FTCRespCode.ERR_FUND_RISK_REJECTED.getMessage(),  FTCRespCode.ERR_FUND_RISK_REJECTED.getCode());
            }
        }
        else if (riskCheckResp !=null && !riskCheckResp.isSuccess()){
            ResponseMapper.wrapBusinessException(riskCheckResp.getRespCode(), riskCheckResp.getMsg());
        }
        else {
            // TODO: 风控出错, 暂不处理
        }
    }

    @Override
    public void riskCheckTransPost(FundTransOrder fundTransOrder, String deviceInfo, String devId, String ip) {
        mqMessageSender.sendRiskCPMsg(getFinTransBeanFromTransOrder(fundTransOrder, deviceInfo, devId, ip));
    }

    private FinTransBean getFinTransBeanFromTransOrder(FundTransOrder fundTransOrder, String deviceInfo, String devId, String ip) {
        FinTransBean finTransBean = new FinTransBean();
        finTransBean.setProductId(fundTransOrder.getProductId());
        finTransBean.setBizChannel(fundTransOrder.getBizChannel());
        finTransBean.setContractNo(fundTransOrder.getContractNo());
        finTransBean.setCurrencyType(fundTransOrder.getCurrencyType());
        finTransBean.setDeviceInfo(deviceInfo);
        finTransBean.setDevId(devId);
        finTransBean.setDiscountAmount(fundTransOrder.getDiscountAmount());
        finTransBean.setExtField(fundTransOrder.getExtField());
        finTransBean.setFundTransAmount(fundTransOrder.getFundTransAmount());
        if (fundTransOrder.getTransTime() != null) {
            finTransBean.setTransTime(fundTransOrder.getTransTime());
        }
        else {
            finTransBean.setTransTime(fundTransOrder.getUpdateTime());
        }
        finTransBean.setRemark(fundTransOrder.getRemark());
        finTransBean.setPayMethod(fundTransOrder.getPayMethod());
        finTransBean.setIp(ip);
        finTransBean.setStatus(fundTransOrder.getStatus());
        finTransBean.setMemberNo(fundTransOrder.getMemberNo());
        finTransBean.setFundTransShares(fundTransOrder.getFundTransShares());
        finTransBean.setTranscoreTransNo(fundTransOrder.getTranscoreTransNo());
        finTransBean.setFundTransType(fundTransOrder.getFundTransType());
        finTransBean.setFundTransOrderNo(fundTransOrder.getFundTransOrderNo());

        return finTransBean;
    }

    @Override
    public void checkAndAddDayStat(FundTransOrder order) {
        CheckAndAddDayStatReq req = new CheckAndAddDayStatReq();
        FinTransBean finTransBean = new FinTransBean();
        mapperFacade.map(order, finTransBean);
        if (finTransBean.getTransTime() == null) {
            finTransBean.setTransTime(order.getCreateTime());
        }
        req.setFinTransBean(finTransBean);
        CheckAndAddDayStatResp resp = quotaStatFacade.checkAndAddDayStat(req);
        if (!resp.isSuccess()) {
            ResponseMapper.wrapBusinessException(resp.getRespCode(), resp.getMsg());
        }
    }

    @Override
    public void checkDayStat(FundTransOrder order) {
        CheckDayStatReq req = new CheckDayStatReq();
        FinTransBean finTransBean = new FinTransBean();
        mapperFacade.map(order, finTransBean);
        if (finTransBean.getTransTime() == null) {
            finTransBean.setTransTime(order.getCreateTime());
        }
        req.setFinTransBean(finTransBean);
        CheckDayStatResp resp = quotaStatFacade.checkDayStat(req);
        if (!resp.isSuccess()) {
            ResponseMapper.wrapBusinessException(resp.getRespCode(), resp.getMsg());
        }
    }

    @Override
    public void addDayStat(FundTransOrder order, TagRules tagRule) {
        AddDayStatReq req = new AddDayStatReq();
        FinTransBean finTransBean = new FinTransBean();
        mapperFacade.map(order, finTransBean);
        if (finTransBean.getTransTime() == null) {
            finTransBean.setTransTime(order.getCreateTime());
        }
        if (tagRule != null && tagRule.getTagId() != null) {
        	finTransBean.setTagId(tagRule.getTagId());
        }
        req.setFinTransBean(finTransBean);
        AddDayStatResp resp = quotaStatFacade.addDayStat(req);
        if (!resp.isSuccess()) {
            ResponseMapper.wrapBusinessException(resp.getRespCode(), resp.getMsg());
        }
    }

    public void addDayStatAsync(FundTransOrder transOrder, TagRules tagRule) {
        FinTransBean finTransBean = new FinTransBean();
        mapperFacade.map(transOrder, finTransBean);
        if (finTransBean.getTransTime() == null) {
            finTransBean.setTransTime(transOrder.getCreateTime());
        }
        if (tagRule != null && tagRule.getTagId() != null) {
            finTransBean.setTagId(tagRule.getTagId());
        }

        MessageWrapper msg = new MessageWrapper(MQTopicName.FIN_TXN_QUOTA_INCREASE, null, transOrder.getFundTransOrderNo(), finTransBean);

        try {
            defaultRMQProducer.sendWithRetry(msg);
        } catch (Exception e) {
            logger.error("send FIN_TXN_QUOTA_INCREASE message err, exception is", e);
        }
    }

    @Override
    public void deductDayStat(FundTransOrder order, TagRules tagRule) {
        DeductDayStatReq req = new DeductDayStatReq();
        FinTransBean finTransBean = new FinTransBean();
        mapperFacade.map(order, finTransBean);
        if (finTransBean.getTransTime() == null) {
            finTransBean.setTransTime(order.getCreateTime());
        }
        if (tagRule != null && tagRule.getTagId() != null) {
        	finTransBean.setTagId(tagRule.getTagId());
        }
        req.setFinTransBean(finTransBean);
        DeductDayStatResp resp = quotaStatFacade.deductDayStat(req);
        if (!resp.isSuccess()) {
            ResponseMapper.wrapBusinessException(resp.getRespCode(), resp.getMsg());
        }
    }

    public void deductDayStatAsync(FundTransOrder transOrder, TagRules tagRule) {
        FinTransBean finTransBean = new FinTransBean();
        mapperFacade.map(transOrder, finTransBean);
        if (finTransBean.getTransTime() == null) {
            finTransBean.setTransTime(transOrder.getCreateTime());
        }
        if (tagRule != null && tagRule.getTagId() != null) {
            finTransBean.setTagId(tagRule.getTagId());
        }

        MessageWrapper msg = new MessageWrapper(MQTopicName.FIN_TXN_QUOTA_DEDUCT, null, transOrder.getFundTransOrderNo(), finTransBean);

        try {
            defaultRMQProducer.sendWithRetry(msg);
        } catch (Exception e) {
            logger.error("send FIN_TXN_QUOTA_INCREASE message err, exception is", e);
        }
    }


    @Override
    public QueryDayStatResp queryDayAmount(Long productId, String memberNo, 
    		int fundTransType, Date queryDate, TagRules tagRule) {
        QueryDayStatReq req = new QueryDayStatReq();
        req.setPeriod(queryDate);
        req.setFundTransType(fundTransType);
        req.setProductId(productId);
        req.setMemberNo(memberNo);
        if (tagRule != null && tagRule.getTagId() != null) {
        	req.setTagId(tagRule.getTagId());
        }

        QueryDayStatResp resp = quotaStatFacade.queryDayStat(req);

        if (!resp.isSuccess()) {
            ResponseMapper.wrapBusinessException(resp.getRespCode(), resp.getMsg());
        }

        return resp;
    }


    @Override
    public QueryDayStatByProdResp queryTodayStatByProd(FundTransOrder order)
    {
        QueryDayStatByProdReq req = new QueryDayStatByProdReq();
        req.setFundTransType(order.getFundTransType());
        req.setProductId(order.getProductId());
        QueryDayStatByProdResp resp =  quotaStatFacade.queryDayStatByProd(req);

        if (!resp.isSuccess()) {
            ResponseMapper.wrapBusinessException(resp.getRespCode(), resp.getMsg());
        }
        return resp;
    }

    @Override
    public QueryDayStatByProdResp queryTodayStatByProd(int fundTransType, Long productId) {
        QueryDayStatByProdReq req = new QueryDayStatByProdReq();
        req.setFundTransType(fundTransType);
        req.setProductId(productId);
        QueryDayStatByProdResp resp =  quotaStatFacade.queryDayStatByProd(req);

        if (!resp.isSuccess()) {
            ResponseMapper.wrapBusinessException(resp.getRespCode(), resp.getMsg());
        }
        return resp;
    }

	@Override
	public AllUserLockBean getRiskUserLock(String memberNo) {
		GetUserLockReq request = new GetUserLockReq();
		request.setMemberNo(memberNo);
		GetUserLockResp resp = errorCountFacade.getUserLocks(request);
        if (!resp.isSuccess()) {
            ResponseMapper.wrapBusinessException(resp.getRespCode(), resp.getMsg());
        }
        
		return resp.getAllUserLockBean();
	}

	@Override
	public void transferorRiskCheck(FundTransferorOrder transferorOrder, String checkpoint, String deviceInfo,
			String devId, String ip) {
		RiskCheckReq riskCheckReq = new RiskCheckReq();
        riskCheckReq.setCheckPoint(checkpoint);
        riskCheckReq.setTransDataBean(getRiskTransDataBean(transferorOrder, deviceInfo, devId, ip));
        RiskCheckResp riskCheckResp = null;
        try {
            riskCheckResp = riskCheckFacade.riskCheck(riskCheckReq);
        } catch (Exception e) {
            logger.error("risk call is failed! Exception is", e);
        }

        if (riskCheckResp != null && riskCheckResp.isSuccess()) {
            if (riskCheckResp.getRcResult().equals(RiskCheckResult.REJECT)) {
                throw new BusinessException(FTCRespCode.ERR_FUND_RISK_REJECTED.getMessage(), 
                		FTCRespCode.ERR_FUND_RISK_REJECTED.getCode());
            }
        } else if (riskCheckResp !=null && !riskCheckResp.isSuccess()){
            ResponseMapper.wrapBusinessException(riskCheckResp.getRespCode(), riskCheckResp.getMsg());
        }
	}

	@Override
	public void transfereeRiskCheck(FundTransfereeOrder transfereeOrder, String checkpoint, String deviceInfo,
			String devId, String ip) {
		RiskCheckReq riskCheckReq = new RiskCheckReq();
        riskCheckReq.setCheckPoint(checkpoint);
        riskCheckReq.setFinTransData(getRiskFinTransBean(transfereeOrder, deviceInfo, devId, ip));
        RiskCheckResp riskCheckResp = null;
        try {
            riskCheckResp = riskCheckFacade.riskCheck(riskCheckReq);
        } catch (Exception e) {
            logger.error("risk call is failed! Exception is", e);
        }

        if (riskCheckResp != null && riskCheckResp.isSuccess()) {
            if (riskCheckResp.getRcResult().equals(RiskCheckResult.REJECT)) {
                throw new BusinessException(FTCRespCode.ERR_FUND_RISK_REJECTED.getMessage(), 
                		FTCRespCode.ERR_FUND_RISK_REJECTED.getCode());
            }
        } else if (riskCheckResp !=null && !riskCheckResp.isSuccess()){
            ResponseMapper.wrapBusinessException(riskCheckResp.getRespCode(), riskCheckResp.getMsg());
        }
	}

    private TransDataBean getRiskTransDataBean(FundTransferorOrder transferorOrder, 
    		String deviceInfo, String devId, String ip) {
        TransDataBean transDataBean = new TransDataBean();
        transDataBean.setOrderNo(transferorOrder.getFundTransferorOrderNo());
        transDataBean.setBizChannel(transferorOrder.getBizChannel());
        transDataBean.setTotalAmount(transferorOrder.getFundTransferAmount());
        transDataBean.setTransTime(new Date());
        transDataBean.setMemberNo(transferorOrder.getMemberNo());
        transDataBean.setTransCode(TransCode.TRANSFEREE);
        transDataBean.setIp(ip);
        transDataBean.setDevId(devId);
        transDataBean.setDeviceInfo(deviceInfo);

        return transDataBean;
    }

    private FinTransBean getRiskFinTransBean(FundTransfereeOrder transfereeOrder, 
    		String deviceInfo, String devId, String ip) {
        FinTransBean finTransBean = new FinTransBean();
        finTransBean.setProductId(transfereeOrder.getMktProductId());
        finTransBean.setBizChannel(transfereeOrder.getBizChannel());
        finTransBean.setContractNo(transfereeOrder.getContractNo());
        finTransBean.setCurrencyType(transfereeOrder.getCurrencyType());
        finTransBean.setDiscountAmount(transfereeOrder.getDiscountAmount());
        finTransBean.setExtField(transfereeOrder.getExtField());
        finTransBean.setFundTransAmount(transfereeOrder.getFundTransferAmount());
        finTransBean.setTransTime(new Date());
        finTransBean.setRemark(transfereeOrder.getRemark());
        finTransBean.setPayMethod(transfereeOrder.getPayMethod());
        finTransBean.setStatus(transfereeOrder.getStatus());
        finTransBean.setMemberNo(transfereeOrder.getMemberNo());
        finTransBean.setTranscoreTransNo(transfereeOrder.getTranscoreTransNo());
        finTransBean.setFundTransType(FundTransType.TRANSFEREE);
        finTransBean.setFundTransOrderNo(transfereeOrder.getFundTransfereeOrderNo());
        finTransBean.setIp(ip);
        finTransBean.setDevId(devId);
        finTransBean.setDeviceInfo(deviceInfo);

        return finTransBean;
    }

}
