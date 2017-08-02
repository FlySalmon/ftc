package com.eif.ftc.integration.impl.market;

import com.eif.fis.facade.constant.ProductCloseType;
import com.eif.fis.facade.dto.ftc.ProdInfo;
import com.eif.fis.facade.response.ftc.QueryProdTransInfoV2Resp;
import com.eif.framework.mq.DefaultRMQProducer;
import com.eif.framework.mq.common.MessageWrapper;
import com.eif.ftc.dal.model.FundTransOrder;
import com.eif.ftc.dal.model.FundTransfereeOrder;
import com.eif.ftc.facade.code.FTCRespCode;
import com.eif.ftc.integration.market.MarketIntService;
import com.eif.ftc.integration.util.ResponseMapper;
import com.eif.ftc.util.exception.BusinessException;
import com.eif.market.facade.biz.MKTCouponUseFacade;
import com.eif.market.facade.biz.MKTGrouponFacade;
import com.eif.market.facade.code.MarketRspCode;
import com.eif.market.facade.dto.CalcUserCouponBean;
import com.eif.market.facade.dto.groupon.UnfreezeInventoryResult;
import com.eif.market.facade.mq.MemberInvestBean;
import com.eif.market.facade.mq.topic.MarketMQTopic;
import com.eif.market.facade.request.couponuse.CalcUserCouponListReq;
import com.eif.market.facade.request.groupon.*;
import com.eif.market.facade.response.couponuse.CalcUserCouponListResp;
import com.eif.market.facade.response.groupon.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by bohan on 1/11/16.
 */
@Service("marketIntService")
public class MarketIntServiceImpl implements MarketIntService {

    private static Logger logger = LoggerFactory.getLogger(MarketIntServiceImpl.class);

    @Resource
    private MKTCouponUseFacade mktCouponUseFacade;

    @Resource
    private MKTGrouponFacade mktGrouponFacade;

    @Resource(name = "defaultRMQProducer")
    private DefaultRMQProducer defaultRMQProducer;


    public CalcUserCouponListResp calcUserCouponList(List<CalcUserCouponBean> calcUserCouponBeans) {
        CalcUserCouponListReq couponRequest = new CalcUserCouponListReq();
        couponRequest.setCalcUserCouponBeans(calcUserCouponBeans);
        CalcUserCouponListResp couponResp = mktCouponUseFacade.calcUserCouponList(couponRequest);
        if (!couponResp.isSuccess()) {
            logger.error("query coupon discount from market failed, errmsg: " + couponResp.getMsg());
            throw new BusinessException(couponResp.getMsg(), couponResp.getRespCode());
        }

        return couponResp;
    }

    public CalcGrouponProfitForUserResp calcGrouponProfitForUser(String memberNo ,List<Long> productIds)
    {
        CalcGrouponProfitForUserReq request = new CalcGrouponProfitForUserReq();
        request.setMemberNo(memberNo);
        request.setUserProductIds(productIds);
        CalcGrouponProfitForUserResp resp = mktGrouponFacade.calcGrouponProfitForUser(request);
        if(!resp.isSuccess())
        {
            logger.error("query coupon discount from market failed, errmsg: " + resp.getMsg());
            throw new BusinessException(resp.getMsg(), resp.getRespCode());
        }
        return  resp;
    }

    public void freezeGrouponInventory(FreezeGrouponInventoryReq request) {
        FreezeGrouponInventoryResp resp = mktGrouponFacade.freezeGrouponInventory(request);
        if (!resp.isSuccess()) {
            if (resp.getRespCode().equals(MarketRspCode.ERR_GROUPON_USER_JOINED_GROUP_NOT_EXIST.getCode())) {
                ResponseMapper.wrapBusinessException(resp.getRespCode(), resp.getMsg());
            }
            else {
                throw new BusinessException(FTCRespCode.ERR_FUND_MKT_FREEZE_GROUPON.getMessage(), FTCRespCode.ERR_FUND_MKT_FREEZE_GROUPON.getCode());
            }
        }
    }

    public Map<String, String> batchUnfreezeAndReturnGrouponInventory(List<String> tokenList) {
        Map<String, String> failedTokenMap = new HashMap<>();
        BatchUnfreezeAndReturnGrouponInventoryReq req = new BatchUnfreezeAndReturnGrouponInventoryReq();
        req.setTokens(tokenList);
        BatchUnfreezeAndReturnGrouponInventoryResp  resp = mktGrouponFacade.batchUnfreezeAndReturnGrouponInventory(req);
        if (resp.isSuccess()) {
            for (UnfreezeInventoryResult result : resp.getResults()) {
                if (!result.isUnfreezeSuccess()) {
                    failedTokenMap.put(result.getToken(), result.getFailureCode());
                }
            }
        }
        else {
            throw new BusinessException(FTCRespCode.ERR_FUND_MKT_BATCH_UNFREEZE_GROUPON.getMessage(), FTCRespCode.ERR_FUND_MKT_BATCH_UNFREEZE_GROUPON.getCode());
        }
        return failedTokenMap;
    }

    public Map<String, String> batchUnfreezeAndDeductGrouponInventory(List<String> tokenList) {
        Map<String, String> failedTokenMap = new HashMap<>();
        BatchUnfreezeAndDeductGrouponInventoryReq req = new BatchUnfreezeAndDeductGrouponInventoryReq();
        req.setTokens(tokenList);
        BatchUnfreezeAndDeductGrouponInventoryResp  resp = mktGrouponFacade.batchUnfreezeAndDeductGrouponInventory(req);
        if (resp.isSuccess()) {
            for (UnfreezeInventoryResult result : resp.getResults()) {
                if (!result.isUnfreezeSuccess()) {
                    failedTokenMap.put(result.getToken(), result.getFailureCode());
                }
            }
        }
        else {
            throw new BusinessException(FTCRespCode.ERR_FUND_MKT_BATCH_DEDUCT_GROUPON.getMessage(), FTCRespCode.ERR_FUND_MKT_BATCH_DEDUCT_GROUPON.getCode());
        }
        return failedTokenMap;
    }

    public void unfreezeAndReturnGrouponInventory(String token) {
        UnfreezeAndReturnGrouponInventoryReq req = new UnfreezeAndReturnGrouponInventoryReq();
        req.setToken(token);
        UnfreezeAndReturnGrouponInventoryResp resp = mktGrouponFacade.unfreezeAndReturnGrouponInventory(req);
        if (!resp.isSuccess()) {
            throw new BusinessException(FTCRespCode.ERR_FUND_MKT_UNFREEZE_GROUPON.getMessage(), FTCRespCode.ERR_FUND_MKT_UNFREEZE_GROUPON.getCode());
        }
    }

    public void unfreezeAndDeductGrouponInventory(String token) {
        UnfreezeAndDeductGrouponInventoryReq req = new UnfreezeAndDeductGrouponInventoryReq();
        req.setToken(token);
        UnfreezeAndDeductGrouponInventoryResp resp = mktGrouponFacade.unfreezeAndDeductGrouponInventory(req);
        if (!resp.isSuccess()) {
            throw new BusinessException(FTCRespCode.ERR_FUND_MKT_DEDUCT_GROUPON.getMessage(), FTCRespCode.ERR_FUND_MKT_DEDUCT_GROUPON.getCode());
        }
    }

    public void unfreezeAndDeductGrouponInventoryAsync(FundTransOrder transOrder, String token) {
        UnfreezeAndDeductGrouponInventoryReq req = new UnfreezeAndDeductGrouponInventoryReq();
        req.setToken(token);
        MessageWrapper msg = new MessageWrapper(MarketMQTopic.TOPIC_GROUPON_INVENTORY_UNFREEZE_DEDUCT_REQ, null, transOrder.getFundTransOrderNo(), req);
        try {
            defaultRMQProducer.sendWithRetry(msg);
        } catch (Exception e) {
            logger.error("send TOPIC_GROUPON_INVENTORY_UNFREEZE_DEDUCT_REQ message err, exception is", e);
        }
    }

    public void unfreezeAndReturnGrouponInventoryAsync(FundTransOrder transOrder, String token) {
        UnfreezeAndReturnGrouponInventoryReq req = new UnfreezeAndReturnGrouponInventoryReq();
        req.setToken(token);
        MessageWrapper msg = new MessageWrapper(MarketMQTopic.TOPIC_GROUPON_INVENTORY_UNFREEZE_RETURN_REQ, null, transOrder.getFundTransOrderNo(), req);
        try {
            defaultRMQProducer.sendWithRetry(msg);
        } catch (Exception e) {
            logger.error("send TOPIC_GROUPON_INVENTORY_UNFREEZE_RETURN_REQ message err, exception is", e);
        }
    }

	@Override
	public void sendMemberInvestment(FundTransOrder transOrder, QueryProdTransInfoV2Resp queryProdTransInfoResp) {
		String productName;
		Integer perYearDate;
		Integer productLimit;
		if (queryProdTransInfoResp.getCloseType() == ProductCloseType.CLOSE) {
			productName = queryProdTransInfoResp.getNormalProdTransInfo().getProductName();
			perYearDate = queryProdTransInfoResp.getNormalProdTransInfo().getPerYearDate();
			productLimit = queryProdTransInfoResp.getNormalProdTransInfo().getProductLimit();
		} else {
			productName = queryProdTransInfoResp.getCurrentProdTransInfo().getProductName();
			perYearDate = queryProdTransInfoResp.getCurrentProdTransInfo().getPerYearDate();
			productLimit = queryProdTransInfoResp.getCurrentProdTransInfo().getCloseDays();
		}
        
		MemberInvestBean memberInvestment = new MemberInvestBean();
		memberInvestment.setMemberNo(transOrder.getMemberNo());
		memberInvestment.setOrderNo(transOrder.getFundTransOrderNo());
		memberInvestment.setProdId(transOrder.getProductId());
		memberInvestment.setProdName(productName);
		memberInvestment.setCloseType(queryProdTransInfoResp.getCloseType());
		memberInvestment.setMarketLevel(queryProdTransInfoResp.getMarketLevel());
		memberInvestment.setPerYearDate(perYearDate);
		memberInvestment.setProductLimit(productLimit);
		memberInvestment.setAmount(transOrder.getFundTransAmount());
		memberInvestment.setDiscountAmount(transOrder.getDiscountAmount());
		memberInvestment.setOrderTime(transOrder.getCreateTime());
		memberInvestment.setPayMethod(transOrder.getPayMethod());
		memberInvestment.setInvestTime(transOrder.getTransTime());
		MessageWrapper msg = new MessageWrapper(MarketMQTopic.TOPIC_NOTIFY_MEMBER_INVEST,
				null, transOrder.getFundTransOrderNo(), memberInvestment);
        try {
            defaultRMQProducer.sendWithRetry(msg);
        } catch (Exception e) {
            logger.error("send TOPIC_NOTIFY_MEMBER_INVEST message err, exception is", e);
        }
	}

	@Override
	public void sendMemberInvestment(FundTransfereeOrder transfereeOrder, ProdInfo product) {
		MemberInvestBean memberInvestment = new MemberInvestBean();
		memberInvestment.setMemberNo(transfereeOrder.getMemberNo());
		memberInvestment.setOrderNo(transfereeOrder.getFundTransfereeOrderNo());
		memberInvestment.setProdId(transfereeOrder.getMktProductId());
		memberInvestment.setProdName(product.getProductName());
		memberInvestment.setCloseType(product.getCloseType());
		memberInvestment.setMarketLevel(product.getMarketLevel());
		memberInvestment.setPerYearDate(product.getPerYearDate());
		memberInvestment.setProductLimit(product.getProductLimit());
		memberInvestment.setAmount(transfereeOrder.getFundTransferAmount());
		memberInvestment.setDiscountAmount(transfereeOrder.getDiscountAmount());
		memberInvestment.setOrderTime(transfereeOrder.getCreateTime());
		memberInvestment.setPayMethod(transfereeOrder.getPayMethod());
		memberInvestment.setInvestTime(transfereeOrder.getPayTime());
		MessageWrapper msg = new MessageWrapper(MarketMQTopic.TOPIC_NOTIFY_MEMBER_INVEST,
				null, transfereeOrder.getFundTransfereeOrderNo(), memberInvestment);
        try {
            defaultRMQProducer.sendWithRetry(msg);
        } catch (Exception e) {
            logger.error("send secMkt TOPIC_NOTIFY_MEMBER_INVEST message err, exception is", e);
        }
	}

}
