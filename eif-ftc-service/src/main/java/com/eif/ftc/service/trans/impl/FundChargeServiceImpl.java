package com.eif.ftc.service.trans.impl;

import com.alibaba.fastjson.JSON;
import com.eif.fis.facade.constant.ProductCloseType;
import com.eif.fis.facade.dto.common.TagRules;
import com.eif.fis.facade.response.ftc.QueryProdTransInfoV2Resp;
import com.eif.fis.facade.dto.ftc.ProdTaTransInfo;
import com.eif.framework.common.utils.code.CommonRspCode;
import com.eif.framework.common.utils.code.RspCode;
import com.eif.framework.concurrent.lock.RedisConcurrentLock;
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
import com.eif.ftc.integration.risk.RiskIntService;
import com.eif.ftc.integration.transcore.TranscoreIntService;
import com.eif.ftc.integration.util.ResponseMapper;
import com.eif.ftc.service.data.fund.FundAmcDataService;
import com.eif.ftc.service.trans.FundChargeService;
import com.eif.ftc.service.trans.action.FundBuyingPayActionService;
import com.eif.ftc.service.trans.action.FundChargeActionService;
import com.eif.ftc.service.trans.action.FundCommTransActionService;
import com.eif.ftc.service.trans.data.FundTransDataService;
import com.eif.ftc.util.constant.TransCoreConstant;
import com.eif.ftc.util.exception.BusinessException;
import com.eif.member.facade.pkg.dto.responseDTO.UserInfoBean;
import com.eif.member.facade.pkg.response.QueryMemberInfoResponse;
import com.eif.transcore.facade.dto.bean.TransactionPaymentOptionBean;
import com.eif.transcore.facade.dto.enumeration.TransAttribute;
import com.eif.transcore.facade.dto.enumeration.TransCode;
import com.eif.transcore.facade.mq.MQTransInfoBean;
import com.lts.core.commons.utils.DateUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.*;

/**
 * Created by bohan on 9/21/16.
 */
@Service("fundChargeService")
public class FundChargeServiceImpl implements FundChargeService {

    static final Logger logger = LoggerFactory.getLogger(FundChargeServiceImpl.class);

    @Autowired
    MQMessageSender mqMessageSender;

    @Autowired
    FundBuyingPayActionService fundBuyingPayActionService;

    @Autowired
    FundCommTransActionService fundCommTransActionService;

    @Autowired
    FundAmcDataService fundAmcDataService;

    @Resource
    FisIntService fisIntService;

    @Resource
    MemberIntService memberIntService;

    @Resource
    TranscoreIntService transcoreIntService;

    @Resource
    OfcIntService ofcIntService;

    @Resource
    RiskIntService riskIntService;

    @Resource
    GoutongIntService goutongIntService;

    @Autowired
    FundTransOrderMapper fundTransOrderMapper;

    @Autowired
    FundTransDataService fundTransDataService;

    @Autowired
    FundChargeActionService fundChargeActionService;

    @Resource
    RedisConcurrentLock redisConcurrentLock;

    static int KEY_EXPIRED_TIME_IN_SECOND = 30 * 60; // 30分钟

    public void chargeSuccess(MQTransInfoBean message, FundTransOrder targetOrder) {
    	//获取产品信息
        QueryProdTransInfoV2Resp queryProdTransInfoResp = fisIntService.queryProdTransInfoV2(targetOrder.getProductId());
        String productName;
        int closeType = queryProdTransInfoResp.getCloseType();
        Long groupBuyId = Long.valueOf(-1);
        TagRules tagRules;
        Date transMaxDate = DateUtils.now();
        if(closeType == ProductCloseType.CLOSE) {
            productName = queryProdTransInfoResp.getNormalProdTransInfo().getProductName();
            groupBuyId = queryProdTransInfoResp.getNormalProdTransInfo().getGroupBuyId();
            tagRules = queryProdTransInfoResp.getNormalProdTransInfo().getTagRules();
        } else{
            productName = queryProdTransInfoResp.getCurrentProdTransInfo().getProductName();
            tagRules = queryProdTransInfoResp.getCurrentProdTransInfo().getTagRules();
            if((closeType == ProductCloseType.HALF_OPEN) || (closeType == ProductCloseType.CLOSE_OPEN))
            {
                transMaxDate = queryProdTransInfoResp.getCurrentProdTransInfo().getTransEndTime();
            }
        }
        
        //获取会员信息
//    	MemberIdentityBean member = memberIntService.getMemberByMemberNo(targetOrder.getMemberNo());
        QueryMemberInfoResponse memberInfoResponse = memberIntService.queryMembersInfoByMemberNo(targetOrder.getMemberNo());
        UserInfoBean member = memberInfoResponse.getUserInfoBean();

    	//设置交易流水号
    	targetOrder.setTranscoreTransNo(message.getTransNo());

        // 状态判断
        if((targetOrder.getStatus() == FundTransOrderStatus.CLOSE_BY_EXPIRY)) {
            fundChargeActionService.doRefund(targetOrder,productName, member.getVerifiedMobile());
            return;
        } else if (targetOrder.getStatus() != FundTransOrderStatus.PAYING) {
            logger.warn("Status of Fund Trans Order " + targetOrder.getFundTransOrderNo() + " is not match, current status is " + targetOrder.getStatus());
            return;
        }

        // 获取需要的用户, 产品信息
        Date curDate = DateUtils.now();


        ProdTaTransInfo prodTaTransInfo = fisIntService
                .queryProdTaTransInfo(targetOrder.getProductId());

        Boolean isGroupOnProduct = Boolean.FALSE;
        if (! groupBuyId.equals(Long.valueOf("-1"))) {
            isGroupOnProduct = Boolean.TRUE;
        }

        // 并发控制
        if(!redisConcurrentLock.tryAcquire(targetOrder.getFundTransOrderNo() + FundTransOrderStatus.PAYING, KEY_EXPIRED_TIME_IN_SECOND)) {
            logger.warn("Fund Trans Order " + targetOrder.getFundTransOrderNo() + " conflicts");
            return;
        }

        try {
            // 业务单更新状态
            int paySuccStatus = FundTransOrderStatus.PAY_SUC;
            //认购并付款成功的单据,单独处理逻辑,将交易单状态直接设置为基金结果已得知
            if (targetOrder.getFundTransType().equals(FundTransType.SUBSCRIBING)) {
                paySuccStatus = FundTransOrderStatus.TA_TRANS_CFM;
            }

            // 解析风控和收益信息
            Map<String, Object> extFieldMap = JSON.parseObject(message.getExtField());
            String deviceInfo = null;
            if (extFieldMap.containsKey(TransCoreConstant.TRANS_DEV_INFO)) {
                deviceInfo = extFieldMap.get(TransCoreConstant.TRANS_DEV_INFO).toString();
            }

            String devId = null;
            if (extFieldMap.containsKey(TransCoreConstant.TRANS_DEV_ID)) {
                devId = extFieldMap.get(TransCoreConstant.TRANS_DEV_ID).toString();
            }

            String ip = null;
            if (extFieldMap.containsKey(TransCoreConstant.TRANS_IP)) {
                ip = extFieldMap.get(TransCoreConstant.TRANS_IP).toString();
            }

            BigDecimal expectedAdditionalProfit = BigDecimal.ZERO;
            if (extFieldMap.containsKey(TransCoreConstant.EXPECTED_PROFIT_AMOUNT)) {
                expectedAdditionalProfit = new BigDecimal(extFieldMap.get(TransCoreConstant.EXPECTED_PROFIT_AMOUNT).toString());
            }

            if (!StringUtils.isEmpty(targetOrder.getFrozenCode())) {
                // 这里来防止并发, 通过后才能走后面
                // 真实扣减, 并判断是否需要退款

                try {
                    fundChargeActionService.unfreezeAndDeductProdInventory(closeType,targetOrder.getFrozenCode());

                } catch (BusinessException be) {
                    //令牌已经被解冻
                    if (!be.getCode().equals(FTCRespCode.ERR_UNFREEZE_AS_FAIL_ALREADY.getCode())) {
                        // 出错归还库存
                        fundChargeActionService.compensateProdInventory(closeType,targetOrder);

                        // 如果是团购产品, 失败解冻团购库存
                        if (groupBuyId != -1) {
                            fundBuyingPayActionService.freezeGrouponInventoryCompensable(targetOrder);
                        }

                        //归还流量
                        fundBuyingPayActionService.addDayQuotaCompensable(targetOrder, tagRules);

                        //重置新手
                        fundBuyingPayActionService.updateRookieCompensable(targetOrder.getMemberNo());
                    }
                    // 做退款处理
                    fundChargeActionService.doRefund(targetOrder, productName, member.getVerifiedMobile());
                    return;
                }
            }

            // 更新业务单, 记录充值的交易单
//            targetOrder.setTranscoreTransNo(message.getTransNo());


            if(curDate.after(transMaxDate))
            {
                curDate = transMaxDate;
            }
            targetOrder.setTransTime(curDate);
            targetOrder.setStatus(paySuccStatus);

            // 同样行锁这里也再一层防止并发(未冻结的情况), 通过后才能走后面
            FundTransOrder updateOrder = new FundTransOrder();
            updateOrder.setTranscoreTransNo(message.getTransNo());
            updateOrder.setTransTime(curDate);
            updateOrder.setStatus(paySuccStatus);
            FundTransOrderExample example = new FundTransOrderExample();
            FundTransOrderExample.Criteria criteria = example.createCriteria();
            criteria.andFundTransOrderNoEqualTo(targetOrder.getFundTransOrderNo());
            criteria.andStatusEqualTo(FundTransOrderStatus.PAYING);
            if (0 == fundTransOrderMapper.updateByExampleSelective(updateOrder, example)) {
                FundTransOrder newOrder = fundTransOrderMapper.selectByPrimaryKey(targetOrder.getId());
                logger.warn("order already changed, fundTransOrderNo is " + targetOrder.getFundTransOrderNo() +
                        ", status is " + newOrder.getStatus());

                // 如果不是定期, 同时已经过期关闭, 则退款; 定期已经在前面退款了
                if (newOrder.getStatus() == FundTransOrderStatus.CLOSE_BY_EXPIRY &&
                        !queryProdTransInfoResp.getCloseType().equals(ProductCloseType.CLOSE)) {
                    fundChargeActionService.doRefund(targetOrder, productName, member.getVerifiedMobile());
                }
                return;
            }

            // 如果是团购产品
            if (groupBuyId != -1) {
                fundChargeActionService.unfreezeAndDeductGrouponInventory(targetOrder);
            }

            List<TransactionPaymentOptionBean> paymentOptions = null;
            paymentOptions = JSON.parseArray(
                    targetOrder.getPayMethod(), TransactionPaymentOptionBean.class);


            // 异步调用交易核心申购

            // amc增加
            transcoreIntService.createTransAsync(targetOrder, TransCode.SUBSCRIBE, paymentOptions, TransAttribute.FUND,productName);
            fundAmcDataService.processAssetAdd(targetOrder,expectedAdditionalProfit,isGroupOnProduct,closeType,prodTaTransInfo);

            // 异步事后风控通知, 添加ip等
            riskIntService.riskCheckTransPost(targetOrder, deviceInfo, devId, ip);

            // 异步OFC支付成功更新
            ofcIntService.updateBusinessOrderItemStatusAsync(targetOrder, FundTransOrderStatus.PAY_SUC, productName);

            // 投资成功通知用户
            fundChargeActionService.notifyCustomerWhenInvestSuc(targetOrder, closeType, productName, 
            		member.getVerifiedMobile());

            // 通知market
            fundChargeActionService.notifyMarketWhenInvestSuc(targetOrder, queryProdTransInfoResp);
        } catch (Exception e) {
            // mq不重试, 只记录问题
            logger.error("chargeSuccess failed, exception is", e);
            List<Integer> prevStatusList = new ArrayList<>();
            prevStatusList.add(FundTransOrderStatus.PAYING);
            fundBuyingPayActionService.updateFundTransOrderForException(targetOrder, curDate, e, prevStatusList, productName);
        }
        finally {
            // 幂等结束
            redisConcurrentLock.release(targetOrder.getFundTransOrderNo() + FundTransOrderStatus.PAYING);
        }


    }

    public void chargeFailed(MQTransInfoBean message, FundTransOrder targetOrder) {

        // 状态判断
        if (targetOrder.getStatus() != FundTransOrderStatus.PAYING) {
            logger.warn("Status of Fund Trans Order " + targetOrder.getFundTransOrderNo() + " is not match, current status is " + targetOrder.getStatus());
            return;
        }

        // 获取需要的用户, 产品信息
        Date curDate = DateUtils.now();
        QueryProdTransInfoV2Resp queryProdTransInfoResp = fisIntService
                .queryProdTransInfoV2(targetOrder.getProductId());

        String productName;
        TagRules tagRules;
        Long groupBuyId = Long.valueOf(-1);
        if(queryProdTransInfoResp.getCloseType().equals(ProductCloseType.CLOSE))
        {
            productName = queryProdTransInfoResp.getNormalProdTransInfo().getProductName();
            tagRules = queryProdTransInfoResp.getNormalProdTransInfo().getTagRules();
            groupBuyId = queryProdTransInfoResp.getNormalProdTransInfo().getGroupBuyId();
        }else
        {
            productName = queryProdTransInfoResp.getCurrentProdTransInfo().getProductName();
            tagRules = queryProdTransInfoResp.getCurrentProdTransInfo().getTagRules();
        }

        // 并发控制
        if(!redisConcurrentLock.tryAcquire(targetOrder.getFundTransOrderNo() + FundTransOrderStatus.PAYING, KEY_EXPIRED_TIME_IN_SECOND)) {
            logger.warn("Fund Trans Order " + targetOrder.getFundTransOrderNo() + " conflicts");
            return;
        }

        try {
            // 更新业务单, 记录充值的交易单
            targetOrder.setTranscoreTransNo(message.getTransNo());
            targetOrder.setFinishTime(curDate);
            targetOrder.setStatus(FundTransOrderStatus.PAY_FAILED);

            FundTransOrder updateOrder = new FundTransOrder();
            updateOrder.setTranscoreTransNo(message.getTransNo());
            updateOrder.setFinishTime(curDate);
            updateOrder.setStatus(FundTransOrderStatus.PAY_FAILED);
            FundTransOrderExample example = new FundTransOrderExample();
            FundTransOrderExample.Criteria criteria = example.createCriteria();
            criteria.andFundTransOrderNoEqualTo(targetOrder.getFundTransOrderNo());
            criteria.andStatusEqualTo(FundTransOrderStatus.PAYING);
            if (0 == fundTransOrderMapper.updateByExampleSelective(updateOrder, example)) {
                throw new BusinessException(CommonRspCode.DB_ERROR.getMessage(), CommonRspCode.DB_ERROR.getCode());
            }

            // 减新手状态, TODO异步
            fundBuyingPayActionService.updateRookieCompensable(targetOrder.getMemberNo());

            // 异步解冻库存
            fundBuyingPayActionService.freezeProdInventoryCompensable(targetOrder, targetOrder.getFrozenCode(),queryProdTransInfoResp.getCloseType().intValue());

            // 如果是团购产品, 失败解冻团购库存
            if (groupBuyId != -1) {
                fundBuyingPayActionService.freezeGrouponInventoryCompensable(targetOrder);
            }

            // 异步回补限额
            fundBuyingPayActionService.addDayQuotaCompensable(targetOrder, tagRules);

            String reasonCode = null;
            if (!StringUtils.isEmpty(message.getReasonCode())) {
                RspCode rspCode = ResponseMapper.wrapBusinessException(message.getReasonCode(), "", false);
                if (rspCode != null) {
                    reasonCode = rspCode.getCode();
                    fundTransDataService.logFundTransOrderException(targetOrder.getFundTransOrderNo(), new BusinessException(rspCode.getMessage(), rspCode.getCode()), curDate);
                }
            }
            //OFC更新
            ofcIntService.updateBusinessOrderItemStatusAsync(targetOrder, FundTransOrderStatus.PAY_FAILED, productName, reasonCode);


        } catch (Exception e) {
            // mq不重试, 只记录
            logger.error("chargeSuccess failed, exception is", e);
            List<Integer> prevStatusList = new ArrayList<>();
            prevStatusList.add(FundTransOrderStatus.PAYING);
            fundBuyingPayActionService.updateFundTransOrderForException(targetOrder, curDate, e, prevStatusList, productName);
        }
        finally {
            redisConcurrentLock.release(targetOrder.getFundTransOrderNo() + FundTransOrderStatus.PAYING);
        }

    }
}
