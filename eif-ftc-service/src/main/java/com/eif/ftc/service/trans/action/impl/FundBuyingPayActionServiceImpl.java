package com.eif.ftc.service.trans.action.impl;

import com.eif.fis.facade.constant.ProductCloseType;
import com.eif.fis.facade.dto.common.TagRules;
import com.eif.fis.facade.request.ftc.FreezeCurrentProdInventoryReq;
import com.eif.fis.facade.request.ftc.FreezeProdInventoryReq;
import com.eif.fis.facade.request.ftc.UnfreezeCurrentProdInventoryReq;
import com.eif.fis.facade.request.ftc.UnfreezeProdInventoryReq;
import com.eif.fis.facade.response.ftc.QueryProdTransInfoV2Resp;
import com.eif.framework.common.utils.code.CommonRspCode;
import com.eif.framework.pagination.pagehelper.PageHelper;
import com.eif.ftc.dal.dao.FundTransOrderMapper;
import com.eif.ftc.dal.model.FundTransOrder;
import com.eif.ftc.dal.model.FundTransOrderExample;
import com.eif.ftc.facade.code.FTCRespCode;
import com.eif.ftc.facade.fund.trans.enumeration.FundTransOrderStatus;
import com.eif.ftc.facade.fund.trans.enumeration.FundTransType;
import com.eif.ftc.facade.fund.trans.response.ResumePayResponse;
import com.eif.ftc.integration.fis.FisIntService;
import com.eif.ftc.integration.market.MarketIntService;
import com.eif.ftc.integration.member.MemberIntService;
import com.eif.ftc.integration.ofc.OfcIntService;
import com.eif.ftc.integration.risk.RiskIntService;
import com.eif.ftc.integration.transcore.TranscoreIntService;
import com.eif.ftc.service.trans.action.FundBuyingLimitedActionService;
import com.eif.ftc.service.trans.action.FundBuyingPayActionService;
import com.eif.ftc.service.trans.data.FundTransDataService;
import com.eif.ftc.util.exception.BusinessException;
import com.eif.ftc.util.uuid.UUIDGenerator;
import com.eif.market.facade.request.groupon.FreezeGrouponInventoryReq;
import com.eif.member.facade.pkg.response.QueryMemberInfoResponse;
import com.eif.risk.facade.constant.CheckPointList;
import com.eif.transcore.facade.dto.bean.TransactionPaymentOptionBean;
import com.eif.transcore.facade.dto.enumeration.*;
import com.eif.transcore.facade.request.ResumePayTransResquest;
import com.eif.transcore.facade.request.RoutePaymentProviderInfoRequest;
import com.eif.transcore.facade.response.ResumePayTransResponse;
import com.eif.transcore.facade.response.RoutePaymentProviderInfoResponse;
import com.lts.core.commons.utils.DateUtils;
import ma.glasnost.orika.MapperFacade;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.util.StringUtil;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by bohan on 9/23/16.
 */
@Service("fundBuyingPayActionService")
public class FundBuyingPayActionServiceImpl implements FundBuyingPayActionService {

    static Logger logger = LoggerFactory.getLogger(FundBuyingPayActionServiceImpl.class);

    @Autowired
    FundTransOrderMapper fundTransOrderMapper;

    @Resource
    TranscoreIntService transcoreIntService;

    @Autowired
    FundTransDataService fundTransDataService;

    @Resource
    RiskIntService riskIntService;

    @Resource
    OfcIntService ofcIntService;

    @Resource
    FisIntService fisIntService;

    @Resource
    MemberIntService memberIntService;

    @Resource
    MarketIntService marketIntService;

    @Autowired
    MapperFacade mapperFacade;

    @Autowired
    FundBuyingLimitedActionService fundBuyingLimitedActionService;

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

    @Transactional
    public void updatePayFundTransOrderTransaction(FundTransOrder order, List<Integer> prevStatusList, Date curDate) {
        FundTransOrderExample example = new FundTransOrderExample();
        FundTransOrderExample.Criteria criteria = example.createCriteria();
        criteria.andIdEqualTo(order.getId());
        criteria.andStatusIn(prevStatusList);
        int effectedRow = fundTransOrderMapper.updateByExampleSelective(order, example);

        if (effectedRow == 0) {
            throw new BusinessException(CommonRspCode.DB_ERROR.getMessage(), CommonRspCode.DB_ERROR.getCode());
        }
        // 更新基金交易状态表
        fundTransDataService.createFundTransOrderStatusInfo(curDate, order.getStatus(), order.getId());
    }

    public void riskPayingCheck(FundTransOrder contextFundTransOrder, String deviceInfo, String devId, String ip, String productName) {
        // 事中风控控制
        riskIntService.riskCheckTransIng(contextFundTransOrder, CheckPointList.USER_FIN_TRANS_ING.getCheckPoint(), deviceInfo, devId, ip);
    }

    // 暂时先写死, Step 1
    public void updateRookie(String memberNo, boolean isNovicePacks) {
        // 成功, status +1, 代表非新手, 返回前值(0代表是新手)
        if (!memberIntService.updateRookieStatusAndRetIsRookie(memberNo, 1)) {
            if (isNovicePacks) {
                throw new BusinessException(FTCRespCode.ERR_FUND_TRANS_BUY_ROOKIE_LIMIT.getMessage(),
                        FTCRespCode.ERR_FUND_TRANS_BUY_ROOKIE_LIMIT.getCode());
            }
        }

    }

    public void updateRookieCompensable(String memberNo) {
        // 失败, status -1, 回退新手
        try {
            memberIntService.updateRookieStatusAndRetIsRookie(memberNo, -1);
        } catch (Exception e) {
            logger.warn("updateRookieCompensable failed! ", e);
            // 异步回滚, TODO, 需要改造
        }
    }

    // Step 2
    public void addDayQuota(FundTransOrder order, TagRules tagRules) {
        riskIntService.addDayStat(order, tagRules);
    }

    public void addDayQuotaCompensable(FundTransOrder order, TagRules tagRules) {
        try {
            // 先同步回滚
            riskIntService.deductDayStat(order, tagRules);
        } catch (Exception e) {
            logger.warn("addDayQuotaCompensable failed! ", e);
            // 异步回滚
            riskIntService.deductDayStatAsync(order, tagRules);
        }
    }


    // Step 3
    public void freezeProdInventory(int closeType, String frozenCode, FundTransOrder transOrder) {
        //产品活期判断, 定期才扣减

        if (closeType == ProductCloseType.CLOSE) {
            freezeNormalProdInventory(frozenCode, transOrder);
        } else if (closeType == ProductCloseType.HALF_OPEN || closeType == ProductCloseType.CLOSE_OPEN) {
            freezeCurrentProdInventory(frozenCode, transOrder);
        } else {
            logger.warn("closeType is not expected! closeType is : " + closeType + "orderNo is : " + transOrder.getFundTransOrderNo());
            return;
        }
    }

    //冻结解冻
    public void freezeProdInventoryCompensable(FundTransOrder transOrder, String frozenCode, int closeType) {
        if (closeType == ProductCloseType.CLOSE) {
            freezeNormalProdInventoryCompensable(frozenCode, transOrder);
        } else if (closeType == ProductCloseType.HALF_OPEN || closeType == ProductCloseType.CLOSE_OPEN) {
            freezeCurrentProdInventoryCompensable(frozenCode, transOrder);
        } else {
            logger.warn("closeType is not expected! closeType is : " + closeType + "orderNo is : " + transOrder.getFundTransOrderNo());
            return;
        }
    }

    public void freezeGrouponInventory(FundTransOrder transOrder, QueryProdTransInfoV2Resp queryProdTransInfoResp) {
        FreezeGrouponInventoryReq req = new FreezeGrouponInventoryReq();

        Long baseProductId;
        if (queryProdTransInfoResp.getCloseType().equals(ProductCloseType.CLOSE)) {
            baseProductId = queryProdTransInfoResp.getNormalProdTransInfo().getBaseProductId();
        } else {
            throw new BusinessException(FTCRespCode.ERR_FIS_UNKNOWN_PRODUCT_CLOSE_TYPE.getMessage(), FTCRespCode.ERR_FIS_UNKNOWN_PRODUCT_CLOSE_TYPE.getCode());
        }
        req.setBaseProductId(baseProductId);
        req.setFtcOrderNo(transOrder.getFundTransOrderNo());
        req.setInvestAmount(transOrder.getFundTransAmount());
        req.setMemberNo(transOrder.getMemberNo());
        req.setToken(transOrder.getFrozenCode());
        req.setUserProductId(transOrder.getProductId());

        marketIntService.freezeGrouponInventory(req);
    }

    public void freezeGrouponInventoryCompensable(FundTransOrder transOrder) {
        try {
            marketIntService.unfreezeAndReturnGrouponInventory(transOrder.getFrozenCode());
        } catch (Exception e) {
            logger.warn("freezeGrouponInventoryCompensable failed", e);
            marketIntService.unfreezeAndReturnGrouponInventoryAsync(transOrder, transOrder.getFrozenCode());
        }
    }


    public void resumePayTrans(String transNo, String pin, FundTransOrder transOrder, Date curDate, Long orderExpireTime, String productName) {
        // 不管出什么问题, 都更新成支付中, 依赖后续的mq或者job确定真实状态
        // 更新业务单信息
        transOrder.setStatus(FundTransOrderStatus.PAYING);
        transOrder.setCurStatusExpiryTime(DateUtils.addMinute(curDate, (int) (orderExpireTime / 60)));
        List<Integer> prevStatusList = new ArrayList<>();
        prevStatusList.add(FundTransOrderStatus.AUTH_PENDING);
        updatePayFundTransOrderTransaction(transOrder, prevStatusList, curDate);
        ofcIntService.updateBusinessOrderItemStatusAsync(transOrder, transOrder.getStatus(), productName, null);

        // 调用交易核心的继续支付接口
        ResumePayTransResquest resumePayTransResquest = new ResumePayTransResquest();
        resumePayTransResquest.setTransNo(transNo);
        resumePayTransResquest.setPin(pin);

        ResumePayTransResponse resumePayTransResponse = null;

        try {
            resumePayTransResponse = transcoreIntService.resumePayTrans(resumePayTransResquest);

            // 处理等待中
            if (resumePayTransResponse.getTransStatus() != TransactionStatus.SETTLING) {
                // 状态错误
                throw new BusinessException(FTCRespCode.ERR_FUND_TRANSCORE_STATUS.getMessage(),
                        FTCRespCode.ERR_FUND_TRANSCORE_STATUS.getCode());
            }
        } catch (Exception e) {
            // 出错不处理, 记录log
            logger.error("resumePayTrans failed", e);
        }
    }

    public void payWithoutOTP(FundTransOrder transOrder, RoutePaymentProviderInfoResponse paymentRoutingResp,
                              List<TransactionPaymentOptionBean> paymentOptions, Date curDate, Long orderExpireTime, String productName, String extField) {

        // 可能存在transcoreNO为空的情况, 后续更新
        // 不管出什么问题, 都更新成支付中, 依赖后续的mq或者job确定真实状态
        // 更新业务单信息
        transOrder.setStatus(FundTransOrderStatus.PAYING);
        transOrder.setCurStatusExpiryTime(DateUtils.addMinute(curDate, (int) (orderExpireTime / 60)));

        List<Integer> prevStatusList = new ArrayList<>();
        prevStatusList.add(FundTransOrderStatus.NEED_PAY);
        updatePayFundTransOrderTransaction(transOrder, prevStatusList, curDate);

        ofcIntService.updateBusinessOrderItemStatusAsync(transOrder, transOrder.getStatus(), productName, null);

        // 异步发送交易核心扣款指令
        transcoreIntService.createTransAsync(transOrder, TransCode.CHARGE, paymentOptions, TransAttribute.PAYMENT,
                extField, null, paymentRoutingResp, productName);

    }

    // 支付控制, 包括库存, 新手, 流量; 包含补偿功能
    public void payingControl(FundTransOrder transOrder, QueryProdTransInfoV2Resp queryProdTransInfoResp, QueryMemberInfoResponse memberResp,
                              Date curDate, int prevStatus, String deviceInfo, String devId, String ip) {


        String productName;
        Boolean isNovicePacks;
        TagRules tagRules;
        Long groupBuyId = Long.valueOf(-1);
        if (queryProdTransInfoResp.getCloseType().equals(ProductCloseType.CLOSE)) {
            productName = queryProdTransInfoResp.getNormalProdTransInfo().getProductName();
            isNovicePacks = queryProdTransInfoResp.getNormalProdTransInfo().getIsNovicePacks();
            tagRules = queryProdTransInfoResp.getNormalProdTransInfo().getTagRules();
            groupBuyId = queryProdTransInfoResp.getNormalProdTransInfo().getGroupBuyId();
        } else {
            productName = queryProdTransInfoResp.getCurrentProdTransInfo().getProductName();
            isNovicePacks = queryProdTransInfoResp.getCurrentProdTransInfo().getIsNovicePacks();
            tagRules = queryProdTransInfoResp.getCurrentProdTransInfo().getTagRules();
        }

        // 支付中风控判断
        riskPayingCheck(transOrder, deviceInfo, devId, ip, productName);

        // 支付中订单判断
        fundBuyingLimitedActionService.doPayingOrderLimitedCheck(curDate, transOrder.getFundTransAmount(), memberResp, queryProdTransInfoResp);

        // ---- 这里开始要控制回滚了, 以下调用顺序不能改变, 因为涉及回滚顺序 ---- //
        try {
            // 新手标示添加, 出错暂不回滚, TODO
            updateRookie(transOrder.getMemberNo(), isNovicePacks);

            try {
                // 加流量, 提供者并发控制
                addDayQuota(transOrder, tagRules);

                // 锁库存, 控制人数, 提供者并发控制
                String frozeCode = UUIDGenerator.gen();
                try {
                    // 同时设置frozencode到transorder中
                    freezeProdInventory(queryProdTransInfoResp.getCloseType(), frozeCode, transOrder);

                    // 如果有团购, 则锁定团购库存
                    if (queryProdTransInfoResp.getCloseType() == ProductCloseType.CLOSE &&
                            groupBuyId != -1) {
                        try {
                            // 复用fis的frozen code
                            freezeGrouponInventory(transOrder, queryProdTransInfoResp);
                        } catch (Exception e) {
                            // 回滚团购库存
                            freezeGrouponInventoryCompensable(transOrder);
                            throw e;
                        }
                    }

                } catch (Exception e) {
                    // 回滚库存
                    if (e instanceof BusinessException) {
                        BusinessException be = (BusinessException)e;
                        if (be.getCode().equals(FTCRespCode.ERR_INCORRECT_BIZ_CHANNEL.getCode()) ||
                                be.getCode().equals(FTCRespCode.ERR_INCORRECT_FROZEN_AMOUNT.getCode()) ||
                                be.getCode().equals(FTCRespCode.ERR_FROZEN_TOKEN_EXIST.getCode()) ||
                                be.getCode().equals(FTCRespCode.ERR_FROZEN_TOKEN_NOT_EXIST.getCode()) ||
                                be.getCode().equals(FTCRespCode.ERR_INCORRECT_UNFREEZE_AMOUNT.getCode()) ||
                                be.getCode().equals(FTCRespCode.ERR_PRODUCT_NOT_ON_SALE.getCode()) ||
                                be.getCode().equals(CommonRspCode.ACQUIRE_REDIS_LOCK_ERROR.getCode()) ||
                                be.getCode().equals(CommonRspCode.REDIS_LOCK_OP_ERROR.getCode())) {
                            throw e;
                        }
                    }

                    freezeProdInventoryCompensable(transOrder, frozeCode, queryProdTransInfoResp.getCloseType().intValue());
                    throw e;
                }
            } catch (Exception e) {
                // 回滚流量
                addDayQuotaCompensable(transOrder, tagRules);
                throw e;
            }
        } catch (Exception e) {
            // 回滚新手状态
            updateRookieCompensable(transOrder.getMemberNo());
            throw e;
        }
    }

    @Transactional
    public void updateFundTransOrderForException(FundTransOrder transOrder, Date curDate, Throwable throwable, List<Integer> prevStatusList, String productName) {

        transOrder.setStatus(FundTransOrderStatus.PAY_FAILED);
        transOrder.setFinishTime(curDate);

        String reasonCode = null;
        if (throwable instanceof BusinessException) {

            reasonCode = ((BusinessException) throwable).getCode();
            BusinessException be = (BusinessException) throwable;

            if (be.getCode().equals(FTCRespCode.ERR_FUND_RISK_REJECTED.getCode())) {
                transOrder.setStatus(FundTransOrderStatus.REJECTED_BY_RISK);
            }
        }

        fundTransDataService.logFundTransOrderException(transOrder.getFundTransOrderNo(), throwable, curDate);
        updatePayFundTransOrderTransaction(transOrder, prevStatusList, curDate);

        ofcIntService.updateBusinessOrderItemStatusAsync(transOrder, transOrder.getStatus(), productName, reasonCode);
    }

    //活包定产品库存冻结
    private void freezeCurrentProdInventory(String frozenCode, FundTransOrder transOrder) {
        FreezeCurrentProdInventoryReq freezeRequest = new FreezeCurrentProdInventoryReq();
        freezeRequest.setProductId(transOrder.getProductId());
        freezeRequest.setAmount(transOrder.getFundTransAmount());
        if (StringUtil.isEmpty(transOrder.getFrozenCode())) {
            freezeRequest.setToken(frozenCode);
        }
        try {
            fisIntService.freezeCurrentProdInventory(freezeRequest);
        } finally {
            // 设置冻结token
            transOrder.setFrozenCode(frozenCode);
            // 预先更新frozen code, 避免调用tc请求后, 异步先回来
            if (!StringUtils.isEmpty(transOrder.getFrozenCode())) {
                FundTransOrder updateTransOrder = new FundTransOrder();
                updateTransOrder.setId(transOrder.getId());
                updateTransOrder.setFrozenCode(transOrder.getFrozenCode());
                fundTransOrderMapper.updateByPrimaryKeySelective(updateTransOrder);
            }
        }

    }

    //定期产品库存冻结
    private void freezeNormalProdInventory(String frozenCode, FundTransOrder transOrder) {

        FreezeProdInventoryReq freezeRequest = new FreezeProdInventoryReq();

        // 冻结库存
        freezeRequest.setAmount(transOrder.getFundTransAmount());
        freezeRequest.setProductId(transOrder.getProductId());

        //不为空则表示已经锁过库存
        if (StringUtil.isEmpty(transOrder.getFrozenCode())) {
            freezeRequest.setToken(frozenCode);
        }

        // 定期产品200人限制, 判断是否已经买过

        FundTransOrderExample queryExample = new FundTransOrderExample();
        FundTransOrderExample.Criteria queryCriteria = queryExample.createCriteria();
        List<Integer> statusList = new ArrayList<>();
        statusList.add(FundTransOrderStatus.PAY_SUC);
        statusList.add(FundTransOrderStatus.TA_TRANS_PROCESSING);
        statusList.add(FundTransOrderStatus.TA_TRANS_SUC);
        statusList.add(FundTransOrderStatus.TA_TRANS_CFM);
        queryCriteria.andStatusIn(statusList);
        queryCriteria.andProductIdEqualTo(transOrder.getProductId());
        queryCriteria.andMemberNoEqualTo(transOrder.getMemberNo());
        queryCriteria.andFundTransTypeEqualTo(FundTransType.SUBSCRIBING);

        // 取第一个
        PageHelper.startPage(1, 1, false);
        // 是否已经买过并且是定期产品，买过则不扣人数,活包定也不扣人数
        if (fundTransOrderMapper.selectByExample(queryExample).size() > 0) {
            freezeRequest.setHeadCount(0);
        } else {
            freezeRequest.setHeadCount(1);
        }

        try {
            fisIntService.freezeProdInventory(freezeRequest);
        } finally {
            // 设置冻结token
            transOrder.setFrozenCode(frozenCode);

            // 预先更新frozen code, 避免调用tc请求后, 异步先回来
            if (!StringUtils.isEmpty(transOrder.getFrozenCode())) {
                FundTransOrder updateTransOrder = new FundTransOrder();
                updateTransOrder.setId(transOrder.getId());
                updateTransOrder.setFrozenCode(transOrder.getFrozenCode());
                fundTransOrderMapper.updateByPrimaryKeySelective(updateTransOrder);
            }
        }

    }

    //定期产品解冻
    private void freezeNormalProdInventoryCompensable(String frozenCode, FundTransOrder transOrder) {
        if (StringUtils.isEmpty(frozenCode)) {
            return;
        }

        UnfreezeProdInventoryReq unfreezeProdInventoryReq = new UnfreezeProdInventoryReq();
        try {
            // 同步回滚
            unfreezeProdInventoryReq.setToken(frozenCode);
            fisIntService.unfreezeProdInventory(unfreezeProdInventoryReq);
        } catch (Exception e) {
            logger.warn("freezeProdInventoryCompensable failed! ", e);
            // 异步回滚
            fisIntService.unfreezeProdInventoryAsync(transOrder, unfreezeProdInventoryReq);

        }
    }

    //活包定产品解冻
    private void freezeCurrentProdInventoryCompensable(String frozenCode, FundTransOrder transOrder) {
        if (StringUtils.isEmpty(frozenCode)) {
            return;
        }

        UnfreezeCurrentProdInventoryReq unfreezeCurrentProdInventoryReq = new UnfreezeCurrentProdInventoryReq();
        try {
            // 同步回滚
            unfreezeCurrentProdInventoryReq.setToken(frozenCode);
            fisIntService.unfreezeCurrentProdInventory(unfreezeCurrentProdInventoryReq);
        } catch (Exception e) {
            logger.warn("freezeCurrentProdInventoryCompensable failed! ", e);
            // 异步回滚
            // TO-DO
            fisIntService.unfreezeCurrentProdInventoryAsync(transOrder, unfreezeCurrentProdInventoryReq);

        }
    }
}


