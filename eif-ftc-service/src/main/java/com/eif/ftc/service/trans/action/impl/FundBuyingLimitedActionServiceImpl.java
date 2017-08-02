package com.eif.ftc.service.trans.action.impl;

import com.eif.fis.facade.constant.ProductCloseType;
import com.eif.fis.facade.constant.WhiteListType;
import com.eif.fis.facade.dto.common.TagRules;
import com.eif.fis.facade.response.ftc.QueryProdInvenInfoResp;
import com.eif.fis.facade.response.ftc.QueryProdTransInfoV2Resp;
import com.eif.framework.common.utils.money.Money;
import com.eif.framework.pagination.pagehelper.PageHelper;
import com.eif.ftc.dal.dao.FundTransOrderMapper;
import com.eif.ftc.dal.model.FundTransOrderExample;
import com.eif.ftc.facade.code.FTCRespCode;
import com.eif.ftc.facade.fund.trans.enumeration.FundTransOrderStatus;
import com.eif.ftc.facade.fund.trans.enumeration.FundTransType;
import com.eif.ftc.integration.fis.FisIntService;
import com.eif.ftc.integration.member.MemberIntService;
import com.eif.ftc.integration.risk.RiskIntService;
import com.eif.ftc.service.trans.action.FundBuyingLimitedActionService;
import com.eif.ftc.util.MoneyUtil;
import com.eif.ftc.util.exception.BusinessException;
import com.eif.member.facade.pkg.response.QueryMemberInfoResponse;
import com.eif.risk.facade.response.QueryDayStatByProdResp;
import com.eif.risk.facade.response.QueryDayStatResp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by bohan on 9/21/16.
 */
@Service("fundBuyingLimitedActionService")
public class FundBuyingLimitedActionServiceImpl implements FundBuyingLimitedActionService {

    @Resource
    MemberIntService memberIntService;

    @Resource
    RiskIntService riskIntService;

    @Resource
    FisIntService fisIntService;

    @Autowired
    FundTransOrderMapper fundTransOrderMapper;

    // 创建订单限额限制
    public void doCreateOrderLimitedCheck(Date curDate, BigDecimal fundTransAmount, String memberNo,
                                          Long productId) {

        QueryProdTransInfoV2Resp queryProdTransInfoResp = fisIntService
                .queryProdTransInfoV2(productId);
        QueryMemberInfoResponse memberResp = memberIntService.queryMembersInfoByMemberNo(memberNo);

        // 基础限制判断
        doBaseLimitedCheck(curDate, memberResp, queryProdTransInfoResp);

        // 金额限额判断
        doBaseProdQuotaLimitedCheck(curDate, fundTransAmount, memberResp, queryProdTransInfoResp);
    }

    // 创建订单限额限制
    public void doCreateOrderLimitedCheck(Date curDate, BigDecimal fundTransAmount, QueryMemberInfoResponse memberResp,
                                          QueryProdTransInfoV2Resp queryProdTransInfoResp) {
        // 基础限制判断
        doBaseLimitedCheck(curDate, memberResp, queryProdTransInfoResp);

        // 金额限额判断
        doBaseProdQuotaLimitedCheck(curDate, fundTransAmount, memberResp, queryProdTransInfoResp);
    }

    // 一般支付限额限制
    public void doPrePayOrderLimitedCheck(Date curDate, BigDecimal fundTransAmount, QueryMemberInfoResponse memberResp,
                                          QueryProdTransInfoV2Resp queryProdTransInfoResp) {
        // 基础限制判断
        doBaseLimitedCheck(curDate, memberResp, queryProdTransInfoResp);

        // 金额产品限额判断
        doBaseProdQuotaLimitedCheck(curDate, fundTransAmount, memberResp, queryProdTransInfoResp);

        // 风控限额非并发判断
        doRiskQuotaLimitedCheck(curDate, fundTransAmount, memberResp, queryProdTransInfoResp);

        // 产品库存非并发判断
        doProdInvenLimitedCheck(fundTransAmount, memberResp, queryProdTransInfoResp);
    }

    // 真实支付前限额限制
    public void doPayingOrderLimitedCheck(Date curDate, BigDecimal fundTransAmount, QueryMemberInfoResponse memberResp,
                                          QueryProdTransInfoV2Resp queryProdTransInfoResp) {
        // 基础限制判断
        doBaseLimitedCheck(curDate, memberResp, queryProdTransInfoResp);

        // 金额产品限额判断
        doBaseProdQuotaLimitedCheck(curDate, fundTransAmount, memberResp, queryProdTransInfoResp);

        // 风控限额非并发判断
        doRiskQuotaLimitedCheck(curDate, fundTransAmount, memberResp, queryProdTransInfoResp);
    }


    public void doProdInvenLimitedCheck(BigDecimal fundTransAmount, QueryMemberInfoResponse memberResp,
                                        QueryProdTransInfoV2Resp queryProdTransInfoResp) {
        // 金额
        BigDecimal sharesOrAmount = fundTransAmount;

        // 定期产品判断库存情况
        if (queryProdTransInfoResp.getCloseType().equals(ProductCloseType.CLOSE)) {
            Long productId = queryProdTransInfoResp.getNormalProdTransInfo().getId();


            QueryProdInvenInfoResp queryProdInvenInfoResp = fisIntService.queryProdInvenInfo(productId);

            // 可售金额判断
            if (queryProdInvenInfoResp.getSellableAmount().compareTo(sharesOrAmount) == -1) {
                throw new BusinessException(FTCRespCode.ERR_INCORRECT_FROZEN_AMOUNT.getMessage(), FTCRespCode.ERR_INCORRECT_FROZEN_AMOUNT.getCode());
            }

            if (queryProdInvenInfoResp.getAvaliableHeadCount() == 0) {
                // 定期产品200人限制, 判断是否已经买过
                FundTransOrderExample queryExample = new FundTransOrderExample();
                FundTransOrderExample.Criteria queryCriteria = queryExample.createCriteria();
                List<Integer> statusList = new ArrayList<>();
                statusList.add(FundTransOrderStatus.PAY_SUC);
                statusList.add(FundTransOrderStatus.TA_TRANS_PROCESSING);
                statusList.add(FundTransOrderStatus.TA_TRANS_SUC);
                statusList.add(FundTransOrderStatus.TA_TRANS_CFM);
                queryCriteria.andStatusIn(statusList);
                queryCriteria.andProductIdEqualTo(productId);
                queryCriteria.andMemberNoEqualTo(memberResp.getUserInfoBean().getMemberNo());
                queryCriteria.andFundTransTypeEqualTo(FundTransType.SUBSCRIBING);

                // 取第一个
                PageHelper.startPage(1, 1, false);
                // 没买过并且是定期产品
                if (fundTransOrderMapper.selectByExample(queryExample).size() == 0) {
                    throw new BusinessException(FTCRespCode.ERR_INCORRECT_FROZEN_AMOUNT.getMessage(), FTCRespCode.ERR_INCORRECT_FROZEN_AMOUNT.getCode());
                }
            }
        }
    }

    public void doRiskQuotaLimitedCheck(Date curDate, BigDecimal fundTransAmount, QueryMemberInfoResponse memberResp,
                                        QueryProdTransInfoV2Resp queryProdTransInfoResp) {
        BigDecimal sharesOrAmount;

        if (fundTransAmount.compareTo(new BigDecimal(-1)) != 0) {
            // 金额, 默认不支持share份额
            sharesOrAmount = fundTransAmount;


            int fundTransType = FundTransType.SUBSCRIBING;
            if (queryProdTransInfoResp.getCloseType() != ProductCloseType.CLOSE) {
                // 活期
                fundTransType = FundTransType.PURCHASING;
            }

            // 获取流量信息
            Long productId;
            TagRules tagRules;
            BigDecimal perUdbLimitAmt;
            BigDecimal limitBuyAmt;
            BigDecimal dailyMaxBuyAmt;

            if(queryProdTransInfoResp.getCloseType().equals(ProductCloseType.CLOSE)) {
                dailyMaxBuyAmt = queryProdTransInfoResp.getNormalProdTransInfo().getDailyMaxBuyAmt();
                productId = queryProdTransInfoResp.getNormalProdTransInfo().getId();
                tagRules = queryProdTransInfoResp.getNormalProdTransInfo().getTagRules();
                perUdbLimitAmt = queryProdTransInfoResp.getNormalProdTransInfo().getPerUdbLimitAmt();
                limitBuyAmt = queryProdTransInfoResp.getNormalProdTransInfo().getLimitBuyAmt();
            }else {
                dailyMaxBuyAmt = queryProdTransInfoResp.getCurrentProdTransInfo().getDailyMaxBuyAmt();
                productId = queryProdTransInfoResp.getCurrentProdTransInfo().getId();
                tagRules = queryProdTransInfoResp.getCurrentProdTransInfo().getTagRules();
                perUdbLimitAmt = queryProdTransInfoResp.getCurrentProdTransInfo().getPerUdbLimitAmt();
                limitBuyAmt = queryProdTransInfoResp.getCurrentProdTransInfo().getLimitBuyAmt();
            }


            QueryDayStatResp quotaDayStat = riskIntService.queryDayAmount(
                    productId, memberResp.getUserInfoBean().getMemberNo(), fundTransType, curDate, tagRules);

            // 根据tag判断总限额
            if (tagRules != null && tagRules.getTagId() != null && tagRules.getPerUserBuyLimit().compareTo(new BigDecimal(-1)) != 0) {
                if (quotaDayStat.getTagTotalAmount().add(sharesOrAmount).compareTo(tagRules.getPerUserBuyLimit()) > 0) {
                    throw new BusinessException(FTCRespCode.ERR_FUND_TRANS_BUY_RULE_LIMIT_AMT.getMessage(),
                            FTCRespCode.ERR_FUND_TRANS_BUY_RULE_LIMIT_AMT.getCode());
                }
            }

            // 单户单日购买最高金额/份额：用户购买产品的单日最高限额
            if (perUdbLimitAmt.compareTo(new BigDecimal(-1)) != 0) {
                if (quotaDayStat.getDayAmount() == null) {
                    quotaDayStat = riskIntService.queryDayAmount(
                            productId, memberResp.getUserInfoBean().getMemberNo(), fundTransType, curDate,
                            tagRules);
                }
                if (quotaDayStat.getDayAmount().add(sharesOrAmount).compareTo(perUdbLimitAmt) == 1) {
                    throw new BusinessException(FTCRespCode.ERR_FUND_TRANS_BUY_PER_UDB_LIMIT_AMT.getMessage(),
                            FTCRespCode.ERR_FUND_TRANS_BUY_PER_UDB_LIMIT_AMT.getCode());
                }

            }

            // 单人可购买的产品总共最高限额
            if (limitBuyAmt.compareTo(new BigDecimal(-1)) != 0) {
                if (quotaDayStat.getTotalAmount() == null) {
                    quotaDayStat = riskIntService.queryDayAmount(
                            productId, memberResp.getUserInfoBean().getMemberNo(), fundTransType, curDate,
                            tagRules);
                }
                if (quotaDayStat.getTotalAmount().add(sharesOrAmount).compareTo(limitBuyAmt) == 1) {
                    throw new BusinessException(FTCRespCode.ERR_FUND_TRANS_BUY_LIMIT_AMT.getMessage(),
                            FTCRespCode.ERR_FUND_TRANS_BUY_LIMIT_AMT.getCode());
                }
            }

            // 判断是否超过今日产品的总限额
            QueryDayStatByProdResp resp = riskIntService.queryTodayStatByProd(fundTransType, productId);
            if (dailyMaxBuyAmt.compareTo(new BigDecimal(-1)) != 0) {
                if (resp.getDayAmount().add(sharesOrAmount).compareTo(dailyMaxBuyAmt) == 1)
                    throw new BusinessException(FTCRespCode.ERR_FUND_TRANS_BUY_TOTAL_DAY_LIMIT_AMT.getMessage(),
                            FTCRespCode.ERR_FUND_TRANS_BUY_TOTAL_DAY_LIMIT_AMT.getCode());
            }
        }
    }


    private void doBaseProdQuotaLimitedCheck(Date curDate, BigDecimal fundTransAmount, QueryMemberInfoResponse memberResp, QueryProdTransInfoV2Resp queryProdTransInfoResp) {
        BigDecimal sharesOrAmount;

        if (fundTransAmount.compareTo(new BigDecimal("-1")) != 0) {
            // 金额, 默认不支持share份额
            sharesOrAmount = fundTransAmount;

            BigDecimal minBuyAmt;
            BigDecimal maxBuyAmt;
            BigDecimal perIncAmt;
            BigDecimal perUdbLimitAmt;
            BigDecimal limitBuyAmt;

            if(queryProdTransInfoResp.getCloseType().equals(ProductCloseType.CLOSE)) {
                minBuyAmt = queryProdTransInfoResp.getNormalProdTransInfo().getMinBuyAmt();
                maxBuyAmt = queryProdTransInfoResp.getNormalProdTransInfo().getMaxBuyAmt();
                perIncAmt = queryProdTransInfoResp.getNormalProdTransInfo().getPerIncAmt();
                perUdbLimitAmt = queryProdTransInfoResp.getNormalProdTransInfo().getPerUdbLimitAmt();
                limitBuyAmt = queryProdTransInfoResp.getNormalProdTransInfo().getLimitBuyAmt();
            }else {
                minBuyAmt = queryProdTransInfoResp.getCurrentProdTransInfo().getMinBuyAmt();
                maxBuyAmt = queryProdTransInfoResp.getCurrentProdTransInfo().getMaxBuyAmt();
                perIncAmt = queryProdTransInfoResp.getCurrentProdTransInfo().getPerIncAmt();
                perUdbLimitAmt = queryProdTransInfoResp.getCurrentProdTransInfo().getPerUdbLimitAmt();
                limitBuyAmt = queryProdTransInfoResp.getCurrentProdTransInfo().getLimitBuyAmt();
            }


            // 小于单笔最小购买限额
            if (sharesOrAmount.compareTo(minBuyAmt) == -1 && minBuyAmt.compareTo(new BigDecimal("-1")) != 0) {
                throw new BusinessException(FTCRespCode.ERR_FUND_TRANS_MIN_BUY_AMT_EXCEEDED.getMessage(),
                        FTCRespCode.ERR_FUND_TRANS_MIN_BUY_AMT_EXCEEDED.getCode());
            }

            // 超过单笔限额
            if (sharesOrAmount.compareTo(maxBuyAmt) == 1 && maxBuyAmt.compareTo(new BigDecimal("-1")) != 0) {
                throw new BusinessException(FTCRespCode.ERR_FUND_TRANS_MAX_BUY_AMT_EXCEEDED.getMessage(),
                        FTCRespCode.ERR_FUND_TRANS_MAX_BUY_AMT_EXCEEDED.getCode());
            }

            // 不符合阶梯规则
            if (!Money.isIntegerValue(MoneyUtil.divide(sharesOrAmount, perIncAmt)) && perIncAmt.compareTo(new BigDecimal("-1")) != 0) {
                throw new BusinessException(FTCRespCode.ERR_FUND_TRANS_BUY_INC_AMT.getMessage(),
                        FTCRespCode.ERR_FUND_TRANS_BUY_INC_AMT.getCode());
            }

            // 单户单日购买最高金额/份额：用户购买产品的单日最高限额 - 用单笔先来判断
            if (perUdbLimitAmt.compareTo(new BigDecimal("-1")) != 0) {
                if (sharesOrAmount.compareTo(perUdbLimitAmt) == 1) {
                    throw new BusinessException(FTCRespCode.ERR_FUND_TRANS_BUY_PER_UDB_LIMIT_AMT.getMessage(),
                            FTCRespCode.ERR_FUND_TRANS_BUY_PER_UDB_LIMIT_AMT.getCode());
                }
            }

            // 单人可购买的产品总共最高限额 - 用单笔先来判断
            if (limitBuyAmt.compareTo(new BigDecimal("-1")) != 0) {
                if (sharesOrAmount.compareTo(limitBuyAmt) == 1) {
                    throw new BusinessException(FTCRespCode.ERR_FUND_TRANS_BUY_LIMIT_AMT.getMessage(),
                            FTCRespCode.ERR_FUND_TRANS_BUY_LIMIT_AMT.getCode());
                }
            }
        }
    }

    private void doBaseLimitedCheck(Date curDate, QueryMemberInfoResponse memberResp,
                            QueryProdTransInfoV2Resp queryProdTransInfoResp) {
        // 新手产品限制判断
        Boolean isNovicePacks;
        Integer whiteListType;
        Integer whiteListGroupId;
        Boolean isEmployeeExclusive;
        Date transBeginTime;
        Date transEndTime;
        if(queryProdTransInfoResp.getCloseType().equals(ProductCloseType.CLOSE))
        {
            isNovicePacks = queryProdTransInfoResp.getNormalProdTransInfo().getIsNovicePacks();
            whiteListType = queryProdTransInfoResp.getNormalProdTransInfo().getWhiteListType();
            whiteListGroupId = queryProdTransInfoResp.getNormalProdTransInfo().getWhiteListGroupId();
            isEmployeeExclusive = queryProdTransInfoResp.getNormalProdTransInfo().getIsEmployeeExclusive();
            transBeginTime = queryProdTransInfoResp.getNormalProdTransInfo().getTransBeginTime();
            transEndTime = queryProdTransInfoResp.getNormalProdTransInfo().getTransEndTime();
        }
        else {
            isNovicePacks = queryProdTransInfoResp.getCurrentProdTransInfo().getIsNovicePacks();
            whiteListType = queryProdTransInfoResp.getCurrentProdTransInfo().getWhiteListType();
            whiteListGroupId = queryProdTransInfoResp.getCurrentProdTransInfo().getWhiteListGroupId();
            isEmployeeExclusive = queryProdTransInfoResp.getCurrentProdTransInfo().getIsEmployeeExclusive();
            transBeginTime = queryProdTransInfoResp.getCurrentProdTransInfo().getTransBeginTime();
            transEndTime = queryProdTransInfoResp.getCurrentProdTransInfo().getTransEndTime();
        }




        if (isNovicePacks) {
            if (memberResp.getUserInfoBean().getIsRookie() != 1) {
                throw new BusinessException(FTCRespCode.ERR_FUND_TRANS_BUY_ROOKIE_LIMIT.getMessage(),
                        FTCRespCode.ERR_FUND_TRANS_BUY_ROOKIE_LIMIT.getCode());
            }
        }

        //新白名单产品判断
        if (! whiteListType.equals(WhiteListType.NO_WHITE_LIST)) {
            List<String> whiteList = memberIntService.queryWhiteList(memberResp.getUserInfoBean().getMemberNo());
            if (!whiteList.contains(whiteListGroupId.toString())) {
                throw new BusinessException(FTCRespCode.ERR_FUND_TRANS_WHITE_GROUP_LIMIT.getMessage(),
                        FTCRespCode.ERR_FUND_TRANS_WHITE_GROUP_LIMIT.getCode());
            }
        }

        // 员工产品判断
        if (isEmployeeExclusive) {
            if (memberResp.getUserInfoBean().getIsStaff() != 1) {
                throw new BusinessException(FTCRespCode.ERR_FUND_TRANS_BUY_STAFF_LIMIT.getMessage(),
                        FTCRespCode.ERR_FUND_TRANS_BUY_STAFF_LIMIT.getCode());
            }
        }

        // 定期,活包定产品募集时间判断
        if (queryProdTransInfoResp.getCloseType() == ProductCloseType.CLOSE || queryProdTransInfoResp.getCloseType().equals(ProductCloseType.HALF_OPEN)) {
            if (curDate.before(transBeginTime)
                    || curDate.after(transEndTime)) {
                throw new BusinessException(FTCRespCode.ERR_FUND_TRANS_BUY_TIME_EXCEEDED.getMessage(),
                        FTCRespCode.ERR_FUND_TRANS_BUY_TIME_EXCEEDED.getCode());
            }
        }


        //活包定产品所属日期判断
//        if(queryProdTransInfoResp.getCloseType().equals(ProductCloseType.HALF_OPEN))
//        {
//            Date productDate = queryProdTransInfoResp.getCurrentProdTransInfo().getProductDate();
//            if(curDate.before(productDate) || curDate.after(productDate))
//                throw new BusinessException(FTCRespCode.ERR_FUND_TRANS_BUY_TIME_EXCEEDED.getMessage(),
//                        FTCRespCode.ERR_FUND_TRANS_BUY_TIME_EXCEEDED.getCode());
//        }
    }
}
