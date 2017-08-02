package com.eif.ftc.service.impl.fund.amc;


import com.eif.fis.facade.constant.ProductCloseType;
import com.eif.framework.common.utils.code.CommonRspCode;
import com.eif.ftc.dal.constant.BonusAlternationType;
import com.eif.ftc.dal.constant.GroupOnType;
import com.eif.ftc.dal.constant.ProfitAlternationType;
import com.eif.ftc.dal.dao.*;
import com.eif.ftc.dal.model.*;
import com.eif.ftc.facade.code.FTCRespCode;
import com.eif.ftc.facade.fund.amc.constant.AMCSystemDefination;
import com.eif.ftc.facade.fund.amc.constant.AlterationStatus;
import com.eif.ftc.facade.fund.amc.constant.AlterationType;
import com.eif.ftc.facade.fund.amc.constant.ExchangeType;
import com.eif.ftc.facade.fund.amc.dto.MemberAssetBean;
import com.eif.ftc.facade.fund.amc.dto.request.FundAssetListQueryBean;
import com.eif.ftc.facade.fund.amc.dto.request.FundBonusListQueryBean;
import com.eif.ftc.facade.fund.amc.dto.request.FundTotalAssetRequestBean;
import com.eif.ftc.facade.fund.amc.dto.request.MemberAssetQueryBean;
import com.eif.ftc.facade.fund.amc.dto.response.*;
import com.eif.ftc.facade.fund.amc.response.FundBonusListResponse;
import com.eif.ftc.facade.fund.amc.response.MemberAssetByCloseTypeResponse;
import com.eif.ftc.integration.market.MarketIntService;
import com.eif.ftc.service.constant.AMCOrderType;
import com.eif.ftc.service.constant.FundSettlementStatus;
import com.eif.ftc.service.fund.amc.MemberAssetService;
import com.eif.ftc.util.DateTimeUtils;
import com.eif.ftc.util.SequenceGenerator;
import com.eif.ftc.util.exception.BusinessException;
import com.eif.market.facade.response.groupon.CalcGrouponProfitForUserResp;
import ma.glasnost.orika.MapperFacade;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.*;

/**
 * Created by Matt on 15/12/29.
 */
@Service("memberAssetService")
public class MemberAssetServiceImpl implements MemberAssetService {

    private final static Logger logger = LoggerFactory.getLogger(MemberAssetServiceImpl.class);
    @Autowired
    private MemberAssetMapper memberAssetMapper;

    @Autowired
    private FundTotalMapper fundTotalMapper;

    @Autowired
    private FundDetailMapper fundDetailMapper;

    @Autowired
    private MemberFundAccountMapper memberFundAccountMapper;

    @Autowired
    private FundDetailAlterationMapper fundDetailAlterationMapper;

    @Autowired
    private FundBonusDetailAlterationMapper fundBonusDetailAlterationMapper;

    @Autowired
    private FundProfitAlterationMapper fundProfitAlterationMapper;

    @Resource
    private MarketIntService marketIntService;

    @Autowired
    private MapperFacade mapperFacade;

    @Resource
    SequenceGenerator sequenceGenerator;

    public FundTotalAssetBean getFundTotalAsset(FundTotalAssetRequestBean bean) {
        MemberFundAccount memberFundAccount = memberFundAccountMapper.selectByMemberNo(bean.getMemberNo());
        FundTotalAssetBean result = new FundTotalAssetBean();
        if (memberFundAccount == null)
            throw new BusinessException(FTCRespCode.ERR_FUND_ACCT_NOT_FOUND.getMessage(),
                    FTCRespCode.ERR_FUND_ACCT_NOT_FOUND.getCode());
        FundTotal fundTotal = fundTotalMapper.selectByFundAccountNo(memberFundAccount.getFundAccUuid());
        FundDetail fundDetail = fundDetailMapper.selectByTotalUUIDAndProductId(fundTotal.getFundTotalUuid(), bean.getProductId());
        if (fundDetail == null)
            throw new BusinessException(FTCRespCode.ERR_FUND_AMC_NOT_FOUND.getMessage(),
                    FTCRespCode.ERR_FUND_AMC_NOT_FOUND.getCode());

        if (fundDetail.getGrouponBonus().compareTo(BigDecimal.ZERO) == 1 || fundDetail.getGrouponType().equals(GroupOnType.NOTGROUPON.getValue())) {
            result.setGrouponAmount(fundDetail.getGrouponBonus());
        } else {
            List<Long> productIds = new ArrayList<>();
            productIds.add(bean.getProductId());
            CalcGrouponProfitForUserResp resp = marketIntService.calcGrouponProfitForUser(bean.getMemberNo(), productIds);
            result.setGrouponAmount(resp.getUserProductProfits().get(bean.getProductId()));
        }

        result.setSettlementCapital(fundDetail.getSettlementCapital());
        result.setSettlementDate(fundDetail.getSettlementTime());
        result.setExchangeStatus(fundDetail.getExchangeStatus());
        result.setTotalAmount(fundDetail.getFundTotalAmount());
        result.setActiveAmount(fundDetail.getFundTotalAmount().subtract(fundDetail.getFrozenAmount()));
        result.setConfirmedAmount(fundDetail.getConfirmedAddAmount());
        result.setUnconfirmedAmount(fundDetail.getUnconfirmedAddAmount());
        result.setFrozenAmount(fundDetail.getFrozenAmount());
        result.setExpectProfitAmount(fundDetail.getExpectProfitAmount());
        result.setExpectBonusAmount(fundDetail.getExpectBonusAmount());
        result.setUnconfirmedSubAmount(fundDetail.getUnconfirmedSubAmount());

        List<String> fundDetailUuids = new ArrayList<>();
        fundDetailUuids.add(fundDetail.getFundDetailUuid());
        Map<String, BigDecimal> yesterdayProfitMap = caculateYesterdayIncome(fundDetailUuids);
        result.setYesterdayProfit(yesterdayProfitMap.get(fundDetail.getFundDetailUuid()) != null ? yesterdayProfitMap.get(fundDetail.getFundDetailUuid()) : BigDecimal.ZERO);

        Map<String, BigDecimal> totalProfitMap = caculateTotalIncome(fundDetailUuids);
        result.setTotalProfit(totalProfitMap.get(fundDetail.getFundDetailUuid()) != null ? totalProfitMap.get(fundDetail.getFundDetailUuid()) : BigDecimal.ZERO);
        return result;
    }

    public MemberTotalAssetBean getMemberTotalAsset(MemberAssetQueryBean bean) {
        MemberTotalAssetBean result = new MemberTotalAssetBean();
        MemberAsset currentMemberAsset = memberAssetMapper.selectByMemberNoAndChannel(bean.getMemberNO(), AMCSystemDefination.CHANNELNO);
        if (currentMemberAsset == null)
            throw new BusinessException(FTCRespCode.ERR_FUND_ACCT_NOT_FOUND.getMessage(),
                    FTCRespCode.ERR_FUND_ACCT_NOT_FOUND.getCode());
        MemberFundAccount memberFundAccount = memberFundAccountMapper.selectByMemberNo(bean.getMemberNO());
        if (memberFundAccount == null) {
            throw new BusinessException(FTCRespCode.ERR_FUND_ACCT_NOT_FOUND.getMessage(),
                    FTCRespCode.ERR_FUND_ACCT_NOT_FOUND.getCode());
        }
        FundTotal fundTotal = fundTotalMapper.selectByFundAccountNo(memberFundAccount.getFundAccUuid());
        if (fundTotal == null) {
            throw new BusinessException(FTCRespCode.ERR_FUND_ACCT_NOT_FOUND.getMessage(),
                    FTCRespCode.ERR_FUND_ACCT_NOT_FOUND.getCode());
        }
        result.setTotalAmount(currentMemberAsset.getTotalAsset());
        result.setTotalProfit(getMemberAssetTotalIncome(fundTotal));
        result.setYesterdayProfit(getMemberAssetYesterdayIncome(fundTotal));

        //当前用户的基金信息:
        ArrayList<FundDetail> fundDetailArrayList = fundDetailMapper.selectFundListByTotalUUID(fundTotal.getFundTotalUuid());

        ArrayList<FundAssetInfoBean> fundAssetInfoBeen = new ArrayList<FundAssetInfoBean>();
        if (fundDetailArrayList != null) {

            List<Long> groupOnProductIds = new ArrayList<Long>();
            List<String> fundDetailUuids = new ArrayList<String>();
            for (FundDetail fundDetail : fundDetailArrayList) {
                FundAssetInfoBean fundAssetInfoBean = new FundAssetInfoBean();
                fundAssetInfoBean.setTotalProfit(fundDetail.getTotalProfit());
                fundAssetInfoBean.setTotalAmount(fundDetail.getFundTotalAmount());
                fundAssetInfoBean.setProductId(fundDetail.getProductId());
                fundAssetInfoBean.setExpectProfitAmount(fundDetail.getExpectProfitAmount());
                fundAssetInfoBean.setExpectBonusAmount(fundDetail.getExpectBonusAmount());
                fundAssetInfoBean.setSettlementCapital(fundDetail.getSettlementCapital());
                fundAssetInfoBean.setSettlementDate(fundDetail.getSettlementTime());
                fundAssetInfoBean.setHasSettlement(fundDetail.getHasSettlement());
                fundAssetInfoBean.setFundDetailUuid(fundDetail.getFundDetailUuid());
                fundDetailUuids.add(fundDetail.getFundDetailUuid());
                if (fundDetail.getGrouponBonus().compareTo(BigDecimal.ZERO) == 1 || fundDetail.getGrouponType().equals(GroupOnType.NOTGROUPON.getValue())) {
                    fundAssetInfoBean.setGrouponAmount(fundDetail.getGrouponBonus());
                } else {
                    groupOnProductIds.add(fundDetail.getProductId());
                    fundAssetInfoBean.setGrouponAmount(BigDecimal.ZERO);
                }
                fundAssetInfoBeen.add(fundAssetInfoBean);
            }

            Map<String, BigDecimal> detailMapYesterdayProfit = caculateYesterdayIncome(fundDetailUuids);
            Map<String, BigDecimal> detailMapTotalProfit = caculateTotalIncome(fundDetailUuids);

            CalcGrouponProfitForUserResp resp = null;

            if (groupOnProductIds.size() > 0) {
                resp = marketIntService.calcGrouponProfitForUser(memberFundAccount.getMemberNo(), groupOnProductIds);
            }


            for (FundAssetInfoBean fundAssetInfoBean : fundAssetInfoBeen) {
                BigDecimal yesterDayProit = detailMapYesterdayProfit.get(fundAssetInfoBean.getFundDetailUuid());
                if (yesterDayProit == null) {
                    fundAssetInfoBean.setYesterdayProfit(BigDecimal.ZERO);
                } else {
                    fundAssetInfoBean.setYesterdayProfit(yesterDayProit);
                }

                BigDecimal totalProfit = detailMapTotalProfit.get(fundAssetInfoBean.getFundDetailUuid());

                if (totalProfit == null) {
                    fundAssetInfoBean.setTotalProfit(BigDecimal.ZERO);
                } else {
                    fundAssetInfoBean.setTotalProfit(totalProfit);
                }


                if (fundAssetInfoBean.getGrouponAmount().compareTo(BigDecimal.ZERO) == 1) {

                } else {
                    if (resp != null && resp.getUserProductProfits() != null) {
                        if (resp.getUserProductProfits().get(fundAssetInfoBean.getProductId()) != null)
                            fundAssetInfoBean.setGrouponAmount(resp.getUserProductProfits().get(fundAssetInfoBean.getProductId()));
                        else
                            fundAssetInfoBean.setGrouponAmount(BigDecimal.ZERO);
                    } else {
                        fundAssetInfoBean.setGrouponAmount(BigDecimal.ZERO);
                    }
                }
            }


        }

        result.setFundAssetInfoBeen(fundAssetInfoBeen);
        return result;
    }

    public FundAssetListBean getFundAssetList(FundAssetListQueryBean fundAssetListQueryBean) {
        FundAssetListBean result = new FundAssetListBean();
        MemberFundAccount memberFundAccount = memberFundAccountMapper.selectByMemberNo(fundAssetListQueryBean.getMemberNO());
        if (memberFundAccount == null)
            throw new BusinessException(FTCRespCode.ERR_FUND_ACCT_NOT_FOUND.getMessage(),
                    FTCRespCode.ERR_FUND_ACCT_NOT_FOUND.getCode());
        FundTotal fundTotal = fundTotalMapper.selectByFundAccountNo(memberFundAccount.getFundAccUuid());
        ArrayList<FundDetail> fundDetailArrayList = fundDetailMapper.selectFundListByTotalUUID(fundTotal.getFundTotalUuid());
        ArrayList<FundAssetInfoBean> finishFunds = new ArrayList<FundAssetInfoBean>();
        ArrayList<FundAssetInfoBean> handleFunds = new ArrayList<FundAssetInfoBean>();


        if (fundDetailArrayList != null && fundDetailArrayList.size() > 0) {


            List<FundDetail> fundDetails = new ArrayList<FundDetail>();
            List<String> fundDetailUuids = new ArrayList<>();
            for (FundDetail fundDetail : fundDetailArrayList) {
                if (fundDetail.getGrouponBonus().compareTo(BigDecimal.ZERO) == 1 || fundDetail.getGrouponType().equals(GroupOnType.NOTGROUPON.getValue())) {
//                    fundAssetInfoBean.setGrouponAmount(fundDetail.getGrouponBonus());
                } else {
                    fundDetails.add(fundDetail);
                }
                fundDetailUuids.add(fundDetail.getFundDetailUuid());
            }

            Map<Long, BigDecimal> groupOnInfo = caculateGroupOnBonus(memberFundAccount.getMemberNo(), fundDetails);
            Map<String, BigDecimal> totalProfitMap = caculateTotalIncome(fundDetailUuids);
            Map<String, BigDecimal> yesterdayIncomeMap = caculateYesterdayIncome(fundDetailUuids);
            for (FundDetail detail : fundDetailArrayList) {
                FundAssetInfoBean assetInfoBean = new FundAssetInfoBean();
                assetInfoBean.setProductId(detail.getProductId());
                assetInfoBean.setTotalAmount(detail.getFundTotalAmount());
                assetInfoBean.setTotalProfit(totalProfitMap.get(detail.getFundDetailUuid()) != null ? totalProfitMap.get(detail.getFundDetailUuid()) : BigDecimal.ZERO);
                assetInfoBean.setExpectProfitAmount(detail.getExpectProfitAmount());
                assetInfoBean.setExpectBonusAmount(detail.getExpectBonusAmount());
                assetInfoBean.setSettlementCapital(detail.getSettlementCapital());
                assetInfoBean.setSettlementDate(detail.getSettlementTime());
                assetInfoBean.setHasSettlement(detail.getHasSettlement());
                assetInfoBean.setExchangeStatus(detail.getExchangeStatus());
                assetInfoBean.setFundDetailUuid(detail.getFundDetailUuid());
                assetInfoBean.setYesterdayProfit(yesterdayIncomeMap.get(detail.getFundDetailUuid()) != null ? yesterdayIncomeMap.get(detail.getFundDetailUuid()) : BigDecimal.ZERO);

                if (detail.getGrouponBonus().compareTo(BigDecimal.ZERO) == 1 || detail.getGrouponType().equals(GroupOnType.NOTGROUPON.getValue())) {
                    assetInfoBean.setGrouponAmount(detail.getGrouponBonus());
                } else {
                    BigDecimal groupOnBonus = BigDecimal.ZERO;
                    if (groupOnInfo != null) {
                        BigDecimal bonus = groupOnInfo.get(assetInfoBean.getProductId());
                        if (bonus != null)
                            groupOnBonus = bonus;
                    }
                    assetInfoBean.setGrouponAmount(groupOnBonus);
                }


                if (detail.getHasSettlement() == FundSettlementStatus.FUNDNOTSETTLED) {
                    handleFunds.add(assetInfoBean);
                } else
                    finishFunds.add(assetInfoBean);
            }


        }
        result.setFinishFunds(finishFunds);
        result.setHandleFunds(handleFunds);
        return result;
    }


    @Transactional
    public FundBonusListResponse getFundBonusList(FundBonusListQueryBean bean) {
        FundBonusListResponse resp = new FundBonusListResponse();
        ArrayList<BonusBean> result = new ArrayList<BonusBean>();
//        MemberFundAccount memberFundAccount = memberFundAccountMapper.selectByMemberNo(bean.getMemberNO());
//        if (memberFundAccount == null)
//            throw new BusinessException(FTCRespCode.ERR_FUND_ACCT_NOT_FOUND.getMessage(),
//                    FTCRespCode.ERR_FUND_ACCT_NOT_FOUND.getCode());
//        FundTotal fundTotal = fundTotalMapper.selectByFundAccountNo(memberFundAccount.getFundAccUuid());
//        FundDetail fundDetail = fundDetailMapper.selectByTotalUUIDAndProductId(fundTotal.getFundTotalUuid(), bean.getProductId());
//        if (fundDetail == null)
//            throw new BusinessException(FTCRespCode.ERR_FUND_AMC_NOT_FOUND.getMessage(),
//                    FTCRespCode.ERR_FUND_AMC_NOT_FOUND.getCode());
//        ArrayList<FundBonusDetailAlteration> fundBonusDetailAlterations = fundBonusDetailAlterationMapper.selectBonusListByDetailUUID(fundDetail.getFundDetailUuid(), bean.getStartDate(), bean.getEndDate());
//        Integer totalCount = fundBonusDetailAlterationMapper.selectBonusCountDetailUUID(fundDetail.getFundDetailUuid());
//        if (totalCount == null) {
//            totalCount = 0;
//        }
//
//        if (fundBonusDetailAlterations != null) {
//            for (FundBonusDetailAlteration fundBonusDetailAlteration : fundBonusDetailAlterations) {
//                BonusBean bonusBean = new BonusBean();
//                bonusBean.setBonusAmount(fundBonusDetailAlteration.getFundBonusAmount());
//                bonusBean.setBonusDate(fundBonusDetailAlteration.getBonusTime());
//                bonusBean.setProductId(fundDetail.getProductId());
//                result.add(bonusBean);
//            }
//        }
        resp.setBonusBeen(result);
        resp.setTotalRecords(0);

        return resp;
    }


    @Override
    public MemberAccountAndAssetBean getMemberAccAndAsset(MemberAssetQueryBean memberAssetQueryBean) {
        MemberAccountAndAssetBean memberAccountAndAssetBean = new MemberAccountAndAssetBean();
        MemberFundAccount memberFundAccount = memberFundAccountMapper.selectByMemberNo(memberAssetQueryBean.getMemberNO());
        if (memberFundAccount == null)
            throw new BusinessException(FTCRespCode.ERR_FUND_ACCT_NOT_FOUND.getMessage(),
                    FTCRespCode.ERR_FUND_ACCT_NOT_FOUND.getCode());

        MemberAsset currentMemberAsset = memberAssetMapper.selectByMemberNoAndChannel(memberAssetQueryBean.getMemberNO(), AMCSystemDefination.CHANNELNO);
        FundTotal fundTotal = fundTotalMapper.selectByFundAccountNo(memberFundAccount.getFundAccUuid());
        ArrayList<FundDetail> fundDetails = fundDetailMapper.selectFundListByTotalUUID(fundTotal.getFundTotalUuid());
        AssetAccountAndTotalAssetBean assetAccountAndTotalAssetBean = new AssetAccountAndTotalAssetBean();
        assetAccountAndTotalAssetBean.setConfirmedAsset(currentMemberAsset.getConfirmedAddAmount());
        assetAccountAndTotalAssetBean.setFrozenAsset(currentMemberAsset.getFrozenAmount());
        assetAccountAndTotalAssetBean.setMemberNo(memberAssetQueryBean.getMemberNO());
        assetAccountAndTotalAssetBean.setStatus(memberFundAccount.getAccountStatus());
        assetAccountAndTotalAssetBean.setTaAccount(memberFundAccount.getTaAccount());
        assetAccountAndTotalAssetBean.setTotalAsset(currentMemberAsset.getTotalAsset());
        assetAccountAndTotalAssetBean.setTransActionAccount(memberFundAccount.getTransactionAccount());
        assetAccountAndTotalAssetBean.setUnConfirmedAsset(currentMemberAsset.getUnconfirmedAddAmount());
        memberAccountAndAssetBean.setAssetAccountAndTotalAssetBean(assetAccountAndTotalAssetBean);

        ArrayList<AssetInfoPerFundBean> assetInfoPerFundBeen = new ArrayList<AssetInfoPerFundBean>();
        if (fundDetails != null) {
            for (FundDetail fundDetail : fundDetails) {
                AssetInfoPerFundBean assetInfoPerFundBean = new AssetInfoPerFundBean();
                assetInfoPerFundBean.setConfirmedAsset(fundDetail.getConfirmedAddAmount());
                assetInfoPerFundBean.setUnConfirmedAsset(fundDetail.getUnconfirmedAddAmount());
                assetInfoPerFundBean.setTotalAsset(fundDetail.getFundTotalAmount());
                assetInfoPerFundBean.setFrozenAsset(fundDetail.getFrozenAmount());
                assetInfoPerFundBean.setFundStatus(fundDetail.getHasSettlement());
                assetInfoPerFundBean.setUnConfirmedAsset(fundDetail.getUnconfirmedAddAmount());
                assetInfoPerFundBean.setProductId(fundDetail.getProductId());
                assetInfoPerFundBean.setYesterdayProfit(fundDetail.getYesterdayProfit());
                assetInfoPerFundBean.setProfitDate(fundDetail.getUpdateTime());
                assetInfoPerFundBeen.add(assetInfoPerFundBean);
            }
        }

        memberAccountAndAssetBean.setAssetInfoPerFundBeen(assetInfoPerFundBeen);

        return memberAccountAndAssetBean;
    }

    @Override
    public Integer getMemAccStatus(MemberAssetQueryBean memberAssetQueryBean) {
        MemberFundAccount memberFundAccount = memberFundAccountMapper.selectByMemberNo(memberAssetQueryBean.getMemberNO());
        if (memberFundAccount == null)
            throw new BusinessException(FTCRespCode.ERR_FUND_ACCT_NOT_FOUND.getMessage(),
                    FTCRespCode.ERR_FUND_ACCT_NOT_FOUND.getCode());
        else
            return memberFundAccount.getAccountRiskStatus();
    }


    private Map<Long, BigDecimal> caculateGroupOnBonus(String memberNo, List<FundDetail> fundDetails) {
        List<Long> grouponProductIds = new ArrayList<Long>();
        for (FundDetail fundDetail : fundDetails) {
            if (fundDetail.getGrouponType().equals(GroupOnType.GROUPON.getValue())) {
                grouponProductIds.add(fundDetail.getProductId());
            }
        }


        if (grouponProductIds.size() > 0) {
            CalcGrouponProfitForUserResp grouponProfitForUserResp = marketIntService.calcGrouponProfitForUser(memberNo, grouponProductIds);
            if (grouponProfitForUserResp.getUserProductProfits() != null)
                return grouponProfitForUserResp.getUserProductProfits();
        }
        return null;


    }

    @Override
    //冻结资产，状态变更为转让中
    public void freezeMemberProductAsset(String memberNo, Long productId) {
        MemberFundAccount memberFundAccount = memberFundAccountMapper.selectByMemberNo(memberNo);
        if (memberFundAccount == null)
            throw new BusinessException(FTCRespCode.ERR_FUND_ACCT_NOT_FOUND.getMessage(),
                    FTCRespCode.ERR_FUND_ACCT_NOT_FOUND.getCode());
        FundTotal fundTotal = fundTotalMapper.selectInfoByFundAccountNo(memberFundAccount.getFundAccUuid());
        FundDetail fundDetail = fundDetailMapper.selectByTotalUUIDAndProductId(fundTotal.getFundTotalUuid(), productId);
        if (fundDetail == null)
            throw new BusinessException(FTCRespCode.ERR_FUND_AMC_NOT_FOUND.getMessage(),
                    FTCRespCode.ERR_FUND_AMC_NOT_FOUND.getCode());
        fundDetail = fundDetailMapper.selectByTotalUUIDAndProductIdForUpdate(fundTotal.getFundTotalUuid(), productId);
        fundDetail.setExchangeStatus(ExchangeType.EXCHANGING.getValue());
        fundDetail.setUpdateTime(new Date());
        fundDetailMapper.updateByPrimaryKey(fundDetail);
    }

    @Override
    //解冻资产
    public void unfreezeMemberProductAsset(String memberNo, Long productId) {
        MemberFundAccount memberFundAccount = memberFundAccountMapper.selectByMemberNo(memberNo);
        if (memberFundAccount == null)
            throw new BusinessException(FTCRespCode.ERR_FUND_ACCT_NOT_FOUND.getMessage(),
                    FTCRespCode.ERR_FUND_ACCT_NOT_FOUND.getCode());
        FundTotal fundTotal = fundTotalMapper.selectInfoByFundAccountNo(memberFundAccount.getFundAccUuid());
        FundDetail fundDetail = fundDetailMapper.selectByTotalUUIDAndProductId(fundTotal.getFundTotalUuid(), productId);
        if (fundDetail == null)
            throw new BusinessException(FTCRespCode.ERR_FUND_AMC_NOT_FOUND.getMessage(),
                    FTCRespCode.ERR_FUND_AMC_NOT_FOUND.getCode());
        fundDetail = fundDetailMapper.selectByTotalUUIDAndProductIdForUpdate(fundTotal.getFundTotalUuid(), productId);
        fundDetail.setExchangeStatus(ExchangeType.NORMAL.getValue());
        fundDetail.setUpdateTime(new Date());
        fundDetailMapper.updateByPrimaryKey(fundDetail);

    }

    @Override
    public void unfreezeAndDeductMemberProductAsset(String memberNo, Long productId, String transferorOrderNo) {

        MemberFundAccount currentAccount = memberFundAccountMapper.selectByMemberNo(memberNo);
        if (currentAccount == null)
            throw new BusinessException(FTCRespCode.ERR_FUND_ACCT_NOT_FOUND.getMessage(),
                    FTCRespCode.ERR_FUND_ACCT_NOT_FOUND.getCode());
        MemberAsset currentMemberAsset = memberAssetMapper.selectForUpdateByMemberNoAndChannel(currentAccount.getMemberNo(), currentAccount.getChannelNo());
        FundTotal currentFundTotal = fundTotalMapper.selectByFundAccountNo(currentAccount.getFundAccUuid());
        FundDetail fundDetail = fundDetailMapper.selectByTotalUUIDAndProductId(currentFundTotal.getFundTotalUuid(), productId);

        if (fundDetail == null)
            throw new BusinessException(FTCRespCode.ERR_FUND_AMC_NOT_FOUND.getMessage(),
                    FTCRespCode.ERR_FUND_AMC_NOT_FOUND.getCode());
        fundDetail = fundDetailMapper.selectByTotalUUIDAndProductIdForUpdate(currentFundTotal.getFundTotalUuid(), productId);

        BigDecimal fundAmount = fundDetail.getConfirmedAddAmount().add(fundDetail.getExpectBonusAmount()).add(fundDetail.getGrouponBonus()).add(fundDetail.getExpectProfitAmount());

        fundDetail.setFundTotalAmount(BigDecimal.ZERO);
        fundDetail.setConfirmedAddAmount(BigDecimal.ZERO);
        fundDetail.setHasSettlement(FundSettlementStatus.FUNDSETTLED);
        fundDetail.setExchangeStatus(ExchangeType.EXCHANGED.getValue());//转让完成
        fundDetail.setUpdateTime(new Date());
//        fundDetailMapper.updateByPrimaryKey(fundDetail);

        FundDetailExample example = new FundDetailExample();
        FundDetailExample.Criteria criteria = example.createCriteria();
        criteria.andIdEqualTo(fundDetail.getId());
        criteria.andExchangeStatusEqualTo(ExchangeType.EXCHANGING.getValue());//转让中
        fundDetailMapper.updateByExampleSelective(fundDetail, example);

        FundDetailAlteration fundDetailAlteration = new FundDetailAlteration();
        fundDetailAlteration.setFundTotalUuid(currentFundTotal.getFundTotalUuid());
        fundDetailAlteration.setDetailAlterationUuid(sequenceGenerator.amcNoGen(AMCOrderType.FUND_DETAIL_ALTERATION));
        fundDetailAlteration.setFtcOrderCreateTime(new Date());
        fundDetailAlteration.setFtcOrderNo(transferorOrderNo);
        fundDetailAlteration.setFundAmount(fundAmount);
        fundDetailAlteration.setFundShare(BigDecimal.ZERO);
        fundDetailAlteration.setFundDetailId(fundDetail.getId());
        fundDetailAlteration.setFundDetailUuid(fundDetail.getFundDetailUuid());
        fundDetailAlteration.setFundTotalId(currentFundTotal.getId());
        fundDetailAlteration.setAlterationStatus(AlterationStatus.TA_CONFIRM);
        fundDetailAlteration.setCreateTime(new Date());
        fundDetailAlteration.setUpdateTime(new Date());
        fundDetailAlteration.setAlterationType(AlterationType.SUB);
        fundDetailAlterationMapper.insert(fundDetailAlteration);

        currentFundTotal.setUpdateTime(new Date());
        currentFundTotal.setTotalAsset(currentFundTotal.getTotalAsset().subtract(fundDetail.getSettlementCapital()));
        currentFundTotal.setConfirmedAddAmount(currentFundTotal.getConfirmedAddAmount().subtract(fundDetail.getSettlementCapital()));
        fundTotalMapper.updateByPrimaryKey(currentFundTotal);

        currentMemberAsset.setUpdateTime(new Date());
        currentMemberAsset.setTotalAsset(currentMemberAsset.getTotalAsset().subtract(fundDetail.getSettlementCapital()));
        currentMemberAsset.setConfirmedAddAmount(currentMemberAsset.getConfirmedAddAmount().subtract(fundDetail.getSettlementCapital()));
        memberAssetMapper.updateByPrimaryKey(currentMemberAsset);
    }

    @Override
    public void createMemberProductAsset(String memberNo, Long mktProductId,
                                         BigDecimal principalAmount, BigDecimal expectProfit, String transfereeOrderNo) {
        MemberFundAccount currentAccount = memberFundAccountMapper.selectByMemberNo(memberNo);
        if (currentAccount == null)
            throw new BusinessException(FTCRespCode.ERR_FUND_ACCT_NOT_FOUND.getMessage(),
                    FTCRespCode.ERR_FUND_ACCT_NOT_FOUND.getCode());
        MemberAsset currentMemberAsset = memberAssetMapper.selectForUpdateByMemberNoAndChannel(currentAccount.getMemberNo(), currentAccount.getChannelNo());
        FundTotal currentFundTotal = fundTotalMapper.selectByFundAccountNo(currentAccount.getFundAccUuid());
        FundDetail fundDetail = fundDetailMapper.selectByTotalUUIDAndProductId(currentFundTotal.getFundTotalUuid(), mktProductId);

        if (fundDetail == null) {
            //初始化某个基金的数据
            fundDetail = new FundDetail();
            fundDetail.setFundTotalUuid(currentFundTotal.getFundTotalUuid());
            fundDetail.setFundDetailUuid(sequenceGenerator.amcNoGen(AMCOrderType.FUND_DETAIL));
            fundDetail.setProductId(mktProductId);
            fundDetail.setFundTotalId(currentFundTotal.getId());
            fundDetail.setTotalProfit(BigDecimal.ZERO);
            fundDetail.setFundTotalAmount(principalAmount);
            fundDetail.setFundTotalShare(BigDecimal.ZERO);
            fundDetail.setYesterdayProfit(BigDecimal.ZERO);
            fundDetail.setFrozenAmount(BigDecimal.ZERO);
            fundDetail.setFrozenShare(BigDecimal.ZERO);
            fundDetail.setConfirmedAddAmount(principalAmount);
            fundDetail.setConfirmedAddShare(BigDecimal.ZERO);
            fundDetail.setUnconfirmedAddAmount(BigDecimal.ZERO);
            fundDetail.setUnconfirmedAddShare(BigDecimal.ZERO);
            fundDetail.setConfirmedSubAmount(BigDecimal.ZERO);
            fundDetail.setConfirmedSubShare(BigDecimal.ZERO);
            fundDetail.setUnconfirmedSubAmount(BigDecimal.ZERO);
            fundDetail.setUnconfirmedSubShare(BigDecimal.ZERO);
            fundDetail.setBonusTotalAmount(BigDecimal.ZERO);
            fundDetail.setProfitTotalAmount(BigDecimal.ZERO);
            fundDetail.setHasSettlement(FundSettlementStatus.FUNDNOTSETTLED);
            fundDetail.setSettlementTime(new Date());
            fundDetail.setExpectBonusAmount(expectProfit);
            fundDetail.setExpectProfitAmount(BigDecimal.ZERO);
            fundDetail.setSettlementCapital(principalAmount);
            fundDetail.setCreateTime(new Date());
            fundDetail.setUpdateTime(new Date());
            fundDetail.setGrouponBonus(BigDecimal.ZERO);
            fundDetail.setProductCloseType(ProductCloseType.CLOSE);
            fundDetail.setHalfOpenBonusAmount(BigDecimal.ZERO);
            fundDetail.setGrouponType(GroupOnType.NOTGROUPON.getValue());
            fundDetail.setExchangeStatus(ExchangeType.NORMAL.getValue());
            try {
                fundDetailMapper.insert(fundDetail);
            } catch (DuplicateKeyException ex) {
                logger.info("duplicate key exp info is " + ex.toString());
                throw new BusinessException(FTCRespCode.ERR_FUND_ACCT.getMessage(), FTCRespCode.ERR_FUND_ACCT.getCode());
            }
        } else {
            logger.info("already exist memberAsser!!!");
            throw new BusinessException(FTCRespCode.ERR_FUND_ACCT.getMessage(), FTCRespCode.ERR_FUND_ACCT.getCode());
        }


        fundDetail = fundDetailMapper.selectInfoByFundDetailUUID(fundDetail.getFundDetailUuid());

        FundDetailAlteration fundDetailAlteration = new FundDetailAlteration();
        fundDetailAlteration.setFundTotalUuid(currentFundTotal.getFundTotalUuid());
        fundDetailAlteration.setDetailAlterationUuid(sequenceGenerator.amcNoGen(AMCOrderType.FUND_DETAIL_ALTERATION));
        fundDetailAlteration.setFtcOrderCreateTime(new Date());
        fundDetailAlteration.setFtcOrderNo(transfereeOrderNo);
        fundDetailAlteration.setFundAmount(principalAmount);
        fundDetailAlteration.setFundShare(BigDecimal.ZERO);
        fundDetailAlteration.setFundDetailId(fundDetail.getId());
        fundDetailAlteration.setFundDetailUuid(fundDetail.getFundDetailUuid());
        fundDetailAlteration.setFundTotalId(currentFundTotal.getId());
        fundDetailAlteration.setAlterationStatus(AlterationStatus.TA_CONFIRM);
        fundDetailAlteration.setCreateTime(new Date());
        fundDetailAlteration.setUpdateTime(new Date());
        fundDetailAlteration.setAlterationType(AlterationType.ADD);
        fundDetailAlterationMapper.insert(fundDetailAlteration);

        currentFundTotal.setTotalAsset(currentFundTotal.getTotalAsset().add(principalAmount));
        currentFundTotal.setConfirmedAddAmount(currentFundTotal.getConfirmedAddAmount().add(principalAmount));
        currentFundTotal.setUpdateTime(new Date());
        fundTotalMapper.updateByPrimaryKey(currentFundTotal);

        currentMemberAsset.setUpdateTime(new Date());
        currentMemberAsset.setTotalAsset(currentMemberAsset.getTotalAsset().add(principalAmount));
        currentMemberAsset.setConfirmedAddAmount(currentMemberAsset.getConfirmedAddAmount().add(principalAmount));
        memberAssetMapper.updateByPrimaryKey(currentMemberAsset);


    }

    @Override
    public void queryMemberAssetByCloseType(MemberAssetByCloseTypeResponse response, String memberNo, List<Integer> productCloseType) {
        MemberFundAccountExample memberFundAccountExample = new MemberFundAccountExample();
        MemberFundAccountExample.Criteria memberFundAccountCriteria = memberFundAccountExample.createCriteria();
        memberFundAccountCriteria.andMemberNoEqualTo(memberNo);
        List<MemberFundAccount> memberFundAccounts = memberFundAccountMapper.selectByExample(memberFundAccountExample);
        if (memberFundAccounts == null || memberFundAccounts.size() != 1) {
            throw new BusinessException(FTCRespCode.ERR_FUND_ACCT_NOT_FOUND.getMessage(), FTCRespCode.ERR_FUND_ACCT_NOT_FOUND.getCode());
        }

        FundTotalExample fundTotalExample = new FundTotalExample();
        FundTotalExample.Criteria fundTotalCriteria = fundTotalExample.createCriteria();
        fundTotalCriteria.andFundAccUuidEqualTo(memberFundAccounts.get(0).getFundAccUuid());
        List<FundTotal> fundTotals = fundTotalMapper.selectByExample(fundTotalExample);

        if (fundTotals == null || fundTotals.size() != 1) {
            throw new BusinessException(FTCRespCode.ERR_FUND_ACCT_NOT_FOUND.getMessage(), FTCRespCode.ERR_FUND_ACCT_NOT_FOUND.getCode());
        }


        FundDetailExample fundDetailExample = new FundDetailExample();
        FundDetailExample.Criteria criteria = fundDetailExample.createCriteria();
        criteria.andFundTotalUuidEqualTo(fundTotals.get(0).getFundTotalUuid());
        criteria.andProductCloseTypeIn(productCloseType);
        List<FundDetail> fundDetails = fundDetailMapper.selectByExample(fundDetailExample);
        List<MemberAssetBean> memberAssetBeans = new ArrayList<>();
        List<String> fundDetailUuids = new ArrayList<>();
        if (fundDetails != null && fundDetails.size() > 0) {
            for (FundDetail fundDetail : fundDetails) {
                fundDetailUuids.add(fundDetail.getFundDetailUuid());
            }
        }

        if (fundDetailUuids.size() > 0) {
            Map<String, BigDecimal> yesterdayProfitMap = caculateYesterdayIncome(fundDetailUuids);
            Map<String, BigDecimal> totalBonusAmountMap = caculateTotalBonusAmount(fundDetailUuids);
            Map<String, BigDecimal> totalProfitAmountMap = caculateTotalProfitAmount(fundDetailUuids);
            for (FundDetail fundDetail : fundDetails) {
                if (yesterdayProfitMap.get(fundDetail.getFundDetailUuid()) != null) {
                    fundDetail.setYesterdayProfit(yesterdayProfitMap.get(fundDetail.getFundDetailUuid()));
                } else {
                    fundDetail.setYesterdayProfit(BigDecimal.ZERO);
                }

                if (totalBonusAmountMap.get(fundDetail.getFundDetailUuid()) != null) {
                    fundDetail.setBonusTotalAmount(totalBonusAmountMap.get(fundDetail.getFundDetailUuid()));
                } else {
                    fundDetail.setBonusTotalAmount(BigDecimal.ZERO);
                }

                if (totalProfitAmountMap.get(fundDetail.getFundDetailUuid()) != null) {
                    fundDetail.setProfitTotalAmount(totalProfitAmountMap.get(fundDetail.getFundDetailUuid()));
                } else {
                    fundDetail.setProfitTotalAmount(BigDecimal.ZERO);
                }
            }
        }

        if (fundDetails != null && fundDetails.size() > 0) {
            memberAssetBeans = mapperFacade.mapAsList(fundDetails, MemberAssetBean.class);
        }


        response.setMemberAssetBeanList(memberAssetBeans);
        response.setMsg(CommonRspCode.SUCCESS.getMessage());
        response.setRespCode(CommonRspCode.SUCCESS.getCode());
    }


    private BigDecimal getMemberAssetYesterdayIncome(FundTotal fundTotal) {
        BigDecimal result = BigDecimal.ZERO;
        FundBonusDetailAlterationExample example = new FundBonusDetailAlterationExample();
        FundBonusDetailAlterationExample.Criteria criteria = example.createCriteria();
        criteria.andFundTotalUuidEqualTo(fundTotal.getFundTotalUuid());
        criteria.andBonusTimeGreaterThanOrEqualTo(DateTimeUtils.getYesterDayBegin());
        criteria.andBonusTimeLessThan(DateTimeUtils.getYesterDayEnd());
        List<Integer> type = new ArrayList<Integer>();
        type.add(BonusAlternationType.OPEN.getValue());
        type.add(BonusAlternationType.CLOSE.getValue());
        criteria.andBonusTypeIn(type);
        List<FundBonusDetailAlteration> fundBonusDetailAlterations = fundBonusDetailAlterationMapper.selectByExample(example);

        if (fundBonusDetailAlterations != null) {
            for (FundBonusDetailAlteration fundBonusDetailAlteration : fundBonusDetailAlterations) {
                if (fundBonusDetailAlteration.getFundBonusAmount() != null)
                    result = result.add(fundBonusDetailAlteration.getFundBonusAmount());
            }
        }


        FundProfitAlterationExample fundProfitAlterationExample = new FundProfitAlterationExample();
        FundProfitAlterationExample.Criteria fundProfitAlterationCriteria = fundProfitAlterationExample.createCriteria();
        fundProfitAlterationCriteria.andFundTotalUuidEqualTo(fundTotal.getFundTotalUuid());
        List<Integer> profitTypes = new ArrayList<>();
        profitTypes.add(ProfitAlternationType.COUPON.getValue());
        profitTypes.add(ProfitAlternationType.GROUPON.getValue());
        profitTypes.add(ProfitAlternationType.COTOPGET.getValue());


        fundProfitAlterationCriteria.andProfitTypeIn(profitTypes);
        fundProfitAlterationCriteria.andCreateTimeGreaterThanOrEqualTo(DateTimeUtils.getYesterDayBegin());
        fundProfitAlterationCriteria.andCreateTimeLessThan(DateTimeUtils.getYesterDayEnd());
        List<FundProfitAlteration> fundProfitAlterations = fundProfitAlterationMapper.selectByExample(fundProfitAlterationExample);

        if (fundProfitAlterations != null) {
            for (FundProfitAlteration fundProfitAlteration : fundProfitAlterations) {
                if (fundProfitAlteration.getProfitAmount() != null)
                    result = result.add(fundProfitAlteration.getProfitAmount());
            }
        }
        return result;
    }

    private BigDecimal getMemberAssetTotalIncome(FundTotal fundTotal) {
        BigDecimal result = BigDecimal.ZERO;
        FundBonusDetailAlterationExample example = new FundBonusDetailAlterationExample();
        FundBonusDetailAlterationExample.Criteria criteria = example.createCriteria();
        criteria.andFundTotalUuidEqualTo(fundTotal.getFundTotalUuid());
        List<Integer> type = new ArrayList<Integer>();
        type.add(BonusAlternationType.OPEN.getValue());
        type.add(BonusAlternationType.CLOSE.getValue());
        criteria.andBonusTypeIn(type);
        List<FundBonusDetailAlteration> fundBonusDetailAlterations = fundBonusDetailAlterationMapper.selectByExample(example);

        if (fundBonusDetailAlterations != null) {
            for (FundBonusDetailAlteration fundBonusDetailAlteration : fundBonusDetailAlterations) {
                if (fundBonusDetailAlteration.getFundBonusAmount() != null)
                    result = result.add(fundBonusDetailAlteration.getFundBonusAmount());
            }
        }


        FundProfitAlterationExample fundProfitAlterationExample = new FundProfitAlterationExample();
        FundProfitAlterationExample.Criteria fundProfitAlterationCriteria = fundProfitAlterationExample.createCriteria();
        fundProfitAlterationCriteria.andFundTotalUuidEqualTo(fundTotal.getFundTotalUuid());
        List<Integer> profitTypes = new ArrayList<>();
        profitTypes.add(ProfitAlternationType.COUPON.getValue());
        profitTypes.add(ProfitAlternationType.GROUPON.getValue());
        profitTypes.add(ProfitAlternationType.COTOPGET.getValue());

        fundProfitAlterationCriteria.andProfitTypeIn(profitTypes);
        List<FundProfitAlteration> fundProfitAlterations = fundProfitAlterationMapper.selectByExample(fundProfitAlterationExample);

        if (fundProfitAlterations != null) {
            for (FundProfitAlteration fundProfitAlteration : fundProfitAlterations) {
                if (fundProfitAlteration.getProfitAmount() != null)
                    result = result.add(fundProfitAlteration.getProfitAmount());
            }
        }
        return result;
    }

    private Map<String, BigDecimal> caculateYesterdayIncome(List<String> fundDetailUuids) {
        Map<String, BigDecimal> result = new HashMap<String, BigDecimal>();
        if (fundDetailUuids == null || fundDetailUuids.size() < 1) {
            return result;
        } else {
            FundBonusDetailAlterationExample example = new FundBonusDetailAlterationExample();
            FundBonusDetailAlterationExample.Criteria criteria = example.createCriteria();
            criteria.andFundDetailUuidIn(fundDetailUuids);
            criteria.andBonusTimeGreaterThanOrEqualTo(DateTimeUtils.getYesterDayBegin());
            criteria.andBonusTimeLessThan(DateTimeUtils.getYesterDayEnd());

            List<Integer> type = new ArrayList<Integer>();
            type.add(BonusAlternationType.OPEN.getValue());
            type.add(BonusAlternationType.CLOSE.getValue());
            criteria.andBonusTypeIn(type);
            List<FundBonusDetailAlteration> fundBonusDetailAlterations = fundBonusDetailAlterationMapper.selectByExample(example);

            if (fundBonusDetailAlterations != null) {
                for (FundBonusDetailAlteration fundBonusDetailAlteration : fundBonusDetailAlterations) {
                    BigDecimal money = result.get(fundBonusDetailAlteration.getFundDetailUuid());
                    if (money == null) {
                        result.put(fundBonusDetailAlteration.getFundDetailUuid(), fundBonusDetailAlteration.getFundBonusAmount());
                    } else {
                        result.put(fundBonusDetailAlteration.getFundDetailUuid(), money.add(fundBonusDetailAlteration.getFundBonusAmount()));
                    }
                }
            }

            FundProfitAlterationExample fundProfitAlterationExample = new FundProfitAlterationExample();
            FundProfitAlterationExample.Criteria fundProfitAlterationCriteria = fundProfitAlterationExample.createCriteria();
            fundProfitAlterationCriteria.andFundDetailUuidIn(fundDetailUuids);
            List<Integer> profitTypes = new ArrayList<>();
            profitTypes.add(ProfitAlternationType.COUPON.getValue());
            profitTypes.add(ProfitAlternationType.GROUPON.getValue());
            profitTypes.add(ProfitAlternationType.COTOPGET.getValue());
            fundProfitAlterationCriteria.andProfitTypeIn(profitTypes);
            fundProfitAlterationCriteria.andProfitAddTimeGreaterThanOrEqualTo(DateTimeUtils.getYesterDayBegin());
            fundProfitAlterationCriteria.andProfitAddTimeLessThan(DateTimeUtils.getYesterDayEnd());
            List<FundProfitAlteration> fundProfitAlterations = fundProfitAlterationMapper.selectByExample(fundProfitAlterationExample);

            if (fundProfitAlterations != null) {
                for (FundProfitAlteration fundProfitAlteration : fundProfitAlterations) {
                    BigDecimal money = result.get(fundProfitAlteration.getFundDetailUuid());
                    if (money == null) {
                        result.put(fundProfitAlteration.getFundDetailUuid(), fundProfitAlteration.getProfitAmount());
                    } else {
                        result.put(fundProfitAlteration.getFundDetailUuid(), money.add(fundProfitAlteration.getProfitAmount()));
                    }
                }
            }
        }

        return result;
    }

    private Map<String, BigDecimal> caculateTotalIncome(List<String> fundDetailUuids) {
        Map<String, BigDecimal> result = new HashMap<String, BigDecimal>();
        if (fundDetailUuids == null || fundDetailUuids.size() < 1) {
            return result;
        } else {
            FundBonusDetailAlterationExample example = new FundBonusDetailAlterationExample();
            FundBonusDetailAlterationExample.Criteria criteria = example.createCriteria();
            criteria.andFundDetailUuidIn(fundDetailUuids);

            List<Integer> type = new ArrayList<Integer>();
            type.add(BonusAlternationType.OPEN.getValue());
            type.add(BonusAlternationType.CLOSE.getValue());
            criteria.andBonusTypeIn(type);
            List<FundBonusDetailAlteration> fundBonusDetailAlterations = fundBonusDetailAlterationMapper.selectByExample(example);

            if (fundBonusDetailAlterations != null) {
                for (FundBonusDetailAlteration fundBonusDetailAlteration : fundBonusDetailAlterations) {
                    BigDecimal money = result.get(fundBonusDetailAlteration.getFundDetailUuid());
                    if (money == null) {
                        result.put(fundBonusDetailAlteration.getFundDetailUuid(), fundBonusDetailAlteration.getFundBonusAmount());
                    } else {
                        result.put(fundBonusDetailAlteration.getFundDetailUuid(), money.add(fundBonusDetailAlteration.getFundBonusAmount()));
                    }
                }
            }

            FundProfitAlterationExample fundProfitAlterationExample = new FundProfitAlterationExample();
            FundProfitAlterationExample.Criteria fundProfitAlterationCriteria = fundProfitAlterationExample.createCriteria();
            fundProfitAlterationCriteria.andFundDetailUuidIn(fundDetailUuids);
            List<Integer> profitTypes = new ArrayList<>();
            profitTypes.add(ProfitAlternationType.COUPON.getValue());
            profitTypes.add(ProfitAlternationType.GROUPON.getValue());
            profitTypes.add(ProfitAlternationType.COTOPGET.getValue());
            fundProfitAlterationCriteria.andProfitTypeIn(profitTypes);
            List<FundProfitAlteration> fundProfitAlterations = fundProfitAlterationMapper.selectByExample(fundProfitAlterationExample);

            if (fundProfitAlterations != null) {
                for (FundProfitAlteration fundProfitAlteration : fundProfitAlterations) {
                    BigDecimal money = result.get(fundProfitAlteration.getFundDetailUuid());
                    if (money == null) {
                        result.put(fundProfitAlteration.getFundDetailUuid(), fundProfitAlteration.getProfitAmount());
                    } else {
                        result.put(fundProfitAlteration.getFundDetailUuid(), money.add(fundProfitAlteration.getProfitAmount()));
                    }
                }
            }
        }

        return result;
    }


    private Map<String, BigDecimal> caculateTotalBonusAmount(List<String> fundDetailUuids) {
        Map<String, BigDecimal> result = new HashMap<String, BigDecimal>();
        if (fundDetailUuids == null || fundDetailUuids.size() < 1) {
        } else {
            FundBonusDetailAlterationExample example = new FundBonusDetailAlterationExample();
            FundBonusDetailAlterationExample.Criteria criteria = example.createCriteria();
            criteria.andFundDetailUuidIn(fundDetailUuids);
            List<Integer> type = new ArrayList<Integer>();
            type.add(BonusAlternationType.OPEN.getValue());
            type.add(BonusAlternationType.CLOSE.getValue());
            criteria.andBonusTypeIn(type);
            List<FundBonusDetailAlteration> fundBonusDetailAlterations = fundBonusDetailAlterationMapper.selectByExample(example);

            if (fundBonusDetailAlterations != null) {
                for (FundBonusDetailAlteration fundBonusDetailAlteration : fundBonusDetailAlterations) {
                    BigDecimal money = result.get(fundBonusDetailAlteration.getFundDetailUuid());
                    if (money == null) {
                        result.put(fundBonusDetailAlteration.getFundDetailUuid(), fundBonusDetailAlteration.getFundBonusAmount());
                    } else {
                        result.put(fundBonusDetailAlteration.getFundDetailUuid(), money.add(fundBonusDetailAlteration.getFundBonusAmount()));
                    }
                }
            }
        }
        return result;
    }

    private Map<String, BigDecimal> caculateTotalProfitAmount(List<String> fundDetailUuids) {
        Map<String, BigDecimal> result = new HashMap<String, BigDecimal>();
        if (fundDetailUuids == null || fundDetailUuids.size() < 1) {
        } else {
            FundProfitAlterationExample fundProfitAlterationExample = new FundProfitAlterationExample();
            FundProfitAlterationExample.Criteria fundProfitAlterationCriteria = fundProfitAlterationExample.createCriteria();
            fundProfitAlterationCriteria.andFundDetailUuidIn(fundDetailUuids);
            List<Integer> profitTypes = new ArrayList<>();
            profitTypes.add(ProfitAlternationType.COUPON.getValue());
            profitTypes.add(ProfitAlternationType.GROUPON.getValue());
            profitTypes.add(ProfitAlternationType.COTOPGET.getValue());
            fundProfitAlterationCriteria.andProfitTypeIn(profitTypes);
            List<FundProfitAlteration> fundProfitAlterations = fundProfitAlterationMapper.selectByExample(fundProfitAlterationExample);

            if (fundProfitAlterations != null) {
                for (FundProfitAlteration fundProfitAlteration : fundProfitAlterations) {
                    BigDecimal money = result.get(fundProfitAlteration.getFundDetailUuid());
                    if (money == null) {
                        result.put(fundProfitAlteration.getFundDetailUuid(), fundProfitAlteration.getProfitAmount());
                    } else {
                        result.put(fundProfitAlteration.getFundDetailUuid(), money.add(fundProfitAlteration.getProfitAmount()));
                    }
                }
            }
        }

        return result;
    }
}