package com.eif.ftc.service.impl.fund.amc;

import com.eif.fis.facade.constant.ProductCloseType;
import com.eif.fis.facade.dto.ftc.ProdTaTransInfo;
import com.eif.ftc.dal.constant.GroupOnType;
import com.eif.ftc.dal.dao.*;
import com.eif.ftc.dal.model.*;
import com.eif.ftc.facade.code.FTCRespCode;
import com.eif.ftc.facade.fund.amc.constant.AlterationStatus;
import com.eif.ftc.facade.fund.amc.constant.AlterationType;
import com.eif.ftc.facade.fund.amc.constant.ExchangeType;
import com.eif.ftc.integration.fis.FisIntService;
import com.eif.ftc.service.bean.FundDetailAlterationDealBean;
import com.eif.ftc.service.bean.FundPurchaseBean;
import com.eif.ftc.service.bean.FundRedemptionBean;
import com.eif.ftc.service.bean.FundSubscriptionBean;
import com.eif.ftc.service.constant.AMCOrderType;
import com.eif.ftc.service.constant.BussinessAlterationType;
import com.eif.ftc.service.constant.FundSettlementStatus;
import com.eif.ftc.service.fund.amc.FundDetailAlterationService;
import com.eif.ftc.util.MoneyUtil;
import com.eif.ftc.util.SequenceGenerator;
import com.eif.ftc.util.exception.BusinessException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by Matt on 15/12/21.
 */
@Service("fundDetailAlterationService")
public class FundDetailAlterationServiceImpl implements FundDetailAlterationService {

    private final static Logger logger = LoggerFactory.getLogger(FundDetailAlterationServiceImpl.class);


    @Resource
    SequenceGenerator sequenceGenerator;

    @Autowired
    MemberFundAccountMapper memberFundAccountMapper;

    @Autowired
    MemberAssetMapper memberAssetMapper;

    @Autowired
    FundTotalMapper fundTotalMapper;

    @Autowired
    FundDetailMapper fundDetailMapper;

    @Autowired
    FundDetailAlterationMapper fundDetailAlterationMapper;

    @Resource
    FisIntService fisIntService;

    @Autowired
    FundBonusDetailAlterationMapper fundBonusDetailAlterationMapper;


    @Transactional(rollbackFor = {RuntimeException.class, Exception.class})
    @Override
    //定期认购
    public Boolean addFundDetailAlterationBySubscriptionRecord(FundSubscriptionBean fundSubscriptionBean,Boolean isGroupOnType,ProdTaTransInfo prodTaTransInfo,Integer closeType) {
        if (!closeType.equals(ProductCloseType.CLOSE)) {
            //产品不是定期,不能进行认购
            throw new BusinessException(FTCRespCode.ERR_FUND_OPERATION_NOT_ILLEGUE.getMessage(), FTCRespCode.ERR_FUND_OPERATION_NOT_ILLEGUE.getCode());
        }


        MemberFundAccount currentAccount = memberFundAccountMapper.selectByMemberNo(fundSubscriptionBean.getMemberNo());
        if (currentAccount == null)
            throw new BusinessException(FTCRespCode.ERR_FUND_ACCT_NOT_FOUND.getMessage(),
                    FTCRespCode.ERR_FUND_ACCT_NOT_FOUND.getCode());
        MemberAsset currentMemberAsset = memberAssetMapper.selectForUpdateByMemberNoAndChannel(currentAccount.getMemberNo(), currentAccount.getChannelNo());
        FundTotal currentFundTotal = fundTotalMapper.selectByFundAccountNo(currentAccount.getFundAccUuid());
        FundDetail fundDetail = fundDetailMapper.selectByTotalUUIDAndProductId(currentFundTotal.getFundTotalUuid(), fundSubscriptionBean.getProductId());


        //初始化Bean
        FundDetailAlterationDealBean fundDetailAlterationDealBean = new FundDetailAlterationDealBean();
        fundDetailAlterationDealBean.setFtcOrderCreateTime(fundSubscriptionBean.getFtcOrderCreateTime());
        fundDetailAlterationDealBean.setFtcOrderNo(fundSubscriptionBean.getFtcOrderNo());
        fundDetailAlterationDealBean.setFundAmount(fundSubscriptionBean.getFundAmount());
        //定期产品,不计算份额,全部以金额为准
        fundDetailAlterationDealBean.setFundShare(BigDecimal.ZERO);
        fundDetailAlterationDealBean.setOrderType(BussinessAlterationType.SUBSCRIPTION);
        fundDetailAlterationDealBean.setExpectProfitAmount(fundSubscriptionBean.getExpectProfitAmount());

        if (fundDetail == null) {
            //初始化某个基金的数据
            fundDetail = initFundDetail(currentFundTotal,fundSubscriptionBean.getProductId(),closeType);

            //更新基金数据
            fundDetail = updateFundDetail(fundDetail,fundDetailAlterationDealBean,isGroupOnType,prodTaTransInfo,closeType);

            try {
                fundDetailMapper.insert(fundDetail);
            }catch (DuplicateKeyException ex) {
                logger.info("duplicate key exp info is " + ex.toString());
                FundDetail fundDetailresult = fundDetailMapper.selectByTotalUUIDAndProductIdForUpdate(currentFundTotal.getFundTotalUuid(), fundSubscriptionBean.getProductId());
                if (fundDetailresult == null) {
                    logger.info("Exception, holly crap!");
                }
                else {
                    fundDetail = updateFundDetail(fundDetailresult, fundDetailAlterationDealBean,isGroupOnType,prodTaTransInfo,closeType);
                    fundDetailMapper.updateByPrimaryKey(fundDetail);
                }

            }

        } else {

            //更新基金数据
            fundDetail = fundDetailMapper.selectByFundDetailUUID(fundDetail.getFundDetailUuid());
            fundDetail = updateFundDetail(fundDetail, fundDetailAlterationDealBean,isGroupOnType,prodTaTransInfo,closeType);
            fundDetailMapper.updateByPrimaryKey(fundDetail);
        }

        //初始化用户基金明细变动
        FundDetailAlteration fundDetailAlteration = initFundDetailAlteration(currentFundTotal, fundDetail, fundDetailAlterationDealBean);
        //更新用户基金明细变动
        fundDetailAlteration = updateFundDetailAlteration(fundDetailAlteration, fundDetailAlterationDealBean);
        fundDetailAlterationMapper.insert(fundDetailAlteration);


        //更新基金总资产
        currentFundTotal = updateFundTotal(currentFundTotal, fundDetailAlterationDealBean);
        fundTotalMapper.updateByPrimaryKey(currentFundTotal);


        //更新用户总资产
        currentMemberAsset = updateMemberAsset(currentMemberAsset, fundDetailAlterationDealBean);
        memberAssetMapper.updateByPrimaryKey(currentMemberAsset);

        return true;

    }

    @Transactional(rollbackFor = {RuntimeException.class, Exception.class})
    @Override
    //活期申购,活包定申购,活期封闭期申购
    public Boolean addFundDetailAlterationByPurchaseRecord(FundPurchaseBean fundPurchaseBean,Boolean isGroupOnType,Integer closeType,ProdTaTransInfo prodTaTransInfo) {


        if (closeType.equals(ProductCloseType.CLOSE)) {
            //不是活期产品,不能申购
            throw new BusinessException(FTCRespCode.ERR_FUND_OPERATION_NOT_ILLEGUE.getMessage(), FTCRespCode.ERR_FUND_OPERATION_NOT_ILLEGUE.getCode());
        }

        MemberFundAccount currentAccount = memberFundAccountMapper.selectByMemberNo(fundPurchaseBean.getMemberNo());
        if (currentAccount == null)
            throw new BusinessException(FTCRespCode.ERR_FUND_ACCT_NOT_FOUND.getMessage(),
                    FTCRespCode.ERR_FUND_ACCT_NOT_FOUND.getCode());
        MemberAsset currentMemberAsset = memberAssetMapper.selectForUpdateByMemberNoAndChannel(currentAccount.getMemberNo(), currentAccount.getChannelNo());
        FundTotal currentFundTotal = fundTotalMapper.selectByFundAccountNo(currentAccount.getFundAccUuid());

        //解决死锁问题
        FundDetail fundDetail = fundDetailMapper.selectByTotalUUIDAndProductId(currentFundTotal.getFundTotalUuid(), fundPurchaseBean.getProductId());


        BigDecimal fundShare = fundPurchaseBean.getFundAmount();

        //初始化Bean
        FundDetailAlterationDealBean fundDetailAlterationDealBean = new FundDetailAlterationDealBean();
        fundDetailAlterationDealBean.setFtcOrderCreateTime(fundPurchaseBean.getFtcOrderCreateTime());
        fundDetailAlterationDealBean.setFtcOrderNo(fundPurchaseBean.getFtcOrderNo());
        fundDetailAlterationDealBean.setFundAmount(fundPurchaseBean.getFundAmount());
        fundDetailAlterationDealBean.setFundShare(fundShare);
        fundDetailAlterationDealBean.setOrderType(BussinessAlterationType.PURCHASE);
        fundDetailAlterationDealBean.setExpectProfitAmount(fundPurchaseBean.getExpectProfitAmount());

        if (fundDetail == null) {
            //初始化某个基金的数据
            fundDetail = initFundDetail(currentFundTotal, fundPurchaseBean.getProductId(),closeType);

            //更新基金数据
            fundDetail = updateFundDetail(fundDetail, fundDetailAlterationDealBean,isGroupOnType,prodTaTransInfo,closeType);

            try {
                fundDetailMapper.insert(fundDetail);
            } catch (DuplicateKeyException ex) {
                logger.info("duplicate key exp info is " + ex.toString());
                FundDetail fundDetailresult = fundDetailMapper.selectByTotalUUIDAndProductIdForUpdate(currentFundTotal.getFundTotalUuid(), fundPurchaseBean.getProductId());
                if (fundDetailresult == null) {
                    logger.info("Exception, holly crap!");
                }
                else {
                    fundDetail = updateFundDetail(fundDetailresult, fundDetailAlterationDealBean,isGroupOnType,prodTaTransInfo,closeType);
                    fundDetailMapper.updateByPrimaryKey(fundDetail);
                }
            }
            fundDetailMapper.updateByPrimaryKey(fundDetail);
        } else {
            //更新基金数据
            fundDetail = fundDetailMapper.selectByFundDetailUUID(fundDetail.getFundDetailUuid());
            fundDetail = updateFundDetail(fundDetail, fundDetailAlterationDealBean,isGroupOnType,prodTaTransInfo,closeType);
            fundDetailMapper.updateByPrimaryKey(fundDetail);
        }

        //初始化用户基金明细变动
        FundDetailAlteration fundDetailAlteration = initFundDetailAlteration(currentFundTotal, fundDetail, fundDetailAlterationDealBean);
        //更新用户基金明细变动
        fundDetailAlteration = updateFundDetailAlteration(fundDetailAlteration, fundDetailAlterationDealBean);
        fundDetailAlterationMapper.insert(fundDetailAlteration);


        //更新基金总资产
        currentFundTotal = updateFundTotal(currentFundTotal, fundDetailAlterationDealBean);
        fundTotalMapper.updateByPrimaryKey(currentFundTotal);


        //更新用户总资产
        currentMemberAsset = updateMemberAsset(currentMemberAsset, fundDetailAlterationDealBean);
        memberAssetMapper.updateByPrimaryKey(currentMemberAsset);

        return true;
    }

    @Transactional(rollbackFor = {RuntimeException.class, Exception.class})
    @Override
    public Boolean addFundDetailAlterationByRedemptionRecord(FundRedemptionBean fundRedemptionBean,Integer closeType) {

        if (!closeType.equals(ProductCloseType.OPEN)) {
            //产品不是活期,不能赎回
            throw new BusinessException(FTCRespCode.ERR_FUND_OPERATION_NOT_ILLEGUE.getMessage(), FTCRespCode.ERR_FUND_OPERATION_NOT_ILLEGUE.getCode());
        }

        MemberFundAccount currentAccount = memberFundAccountMapper.selectByMemberNo(fundRedemptionBean.getMemberNo());
        if (currentAccount == null)
            throw new BusinessException(FTCRespCode.ERR_FUND_ACCT_NOT_FOUND.getMessage(),
                    FTCRespCode.ERR_FUND_ACCT_NOT_FOUND.getCode());
        MemberAsset currentMemberAsset = memberAssetMapper.selectForUpdateByMemberNoAndChannel(currentAccount.getMemberNo(), currentAccount.getChannelNo());
        FundTotal currentFundTotal = fundTotalMapper.selectByFundAccountNo(currentAccount.getFundAccUuid());
        FundDetail fundDetail = fundDetailMapper.selectByTotalUUIDAndProductId(currentFundTotal.getFundTotalUuid(), fundRedemptionBean.getProductId());

        if (fundDetail == null)
            throw new BusinessException(FTCRespCode.ERR_FUND_AMC_NOT_FOUND.getMessage(),
                    FTCRespCode.ERR_FUND_AMC_NOT_FOUND.getCode());
        fundDetail = fundDetailMapper.selectByTotalUUIDAndProductIdForUpdate(currentFundTotal.getFundTotalUuid(), fundRedemptionBean.getProductId());

        BigDecimal fundShare = fundRedemptionBean.getFundAmount();

        //初始化Bean
        FundDetailAlterationDealBean fundDetailAlterationDealBean = new FundDetailAlterationDealBean();
        fundDetailAlterationDealBean.setFtcOrderCreateTime(fundRedemptionBean.getFtcOrderCreateTime());
        fundDetailAlterationDealBean.setFtcOrderNo(fundRedemptionBean.getFtcOrderNo());
        fundDetailAlterationDealBean.setFundAmount(fundRedemptionBean.getFundAmount());
        fundDetailAlterationDealBean.setFundShare(fundShare);
        fundDetailAlterationDealBean.setOrderType(BussinessAlterationType.REDEMPTION);

        fundDetail = updateFundDetail(fundDetail, fundDetailAlterationDealBean,Boolean.FALSE,null,ProductCloseType.OPEN);

        fundDetailMapper.updateByPrimaryKey(fundDetail);

        //初始化用户基金明细变动
        FundDetailAlteration fundDetailAlteration = initFundDetailAlteration(currentFundTotal, fundDetail, fundDetailAlterationDealBean);
        //更新用户基金明细变动
        fundDetailAlteration = updateFundDetailAlteration(fundDetailAlteration, fundDetailAlterationDealBean);
        fundDetailAlterationMapper.insert(fundDetailAlteration);


        //更新基金总资产
        currentFundTotal = updateFundTotal(currentFundTotal, fundDetailAlterationDealBean);
        fundTotalMapper.updateByPrimaryKey(currentFundTotal);


        //更新用户总资产
        currentMemberAsset = updateMemberAsset(currentMemberAsset, fundDetailAlterationDealBean);
        memberAssetMapper.updateByPrimaryKey(currentMemberAsset);

        return true;
    }


    //没有该基金的记录时增加基金记录,初始化所有的份额为0
    private FundDetail initFundDetail(FundTotal fundTotal,Long productId,int closeType) {
        FundDetail newFundDetail = new FundDetail();
        newFundDetail.setFundTotalUuid(fundTotal.getFundTotalUuid());
        newFundDetail.setFundDetailUuid(sequenceGenerator.amcNoGen(AMCOrderType.FUND_DETAIL));
        newFundDetail.setProductId(productId);
        newFundDetail.setFundTotalId(fundTotal.getId());
        newFundDetail.setTotalProfit(BigDecimal.ZERO);
        newFundDetail.setFundTotalAmount(BigDecimal.ZERO);
        newFundDetail.setFundTotalShare(BigDecimal.ZERO);
        newFundDetail.setYesterdayProfit(BigDecimal.ZERO);
        newFundDetail.setFrozenAmount(BigDecimal.ZERO);
        newFundDetail.setFrozenShare(BigDecimal.ZERO);
        newFundDetail.setConfirmedAddAmount(BigDecimal.ZERO);
        newFundDetail.setConfirmedAddShare(BigDecimal.ZERO);
        newFundDetail.setUnconfirmedAddAmount(BigDecimal.ZERO);
        newFundDetail.setUnconfirmedAddShare(BigDecimal.ZERO);
        newFundDetail.setConfirmedSubAmount(BigDecimal.ZERO);
        newFundDetail.setConfirmedSubShare(BigDecimal.ZERO);
        newFundDetail.setUnconfirmedSubAmount(BigDecimal.ZERO);
        newFundDetail.setUnconfirmedSubShare(BigDecimal.ZERO);
        newFundDetail.setBonusTotalAmount(BigDecimal.ZERO);
        newFundDetail.setProfitTotalAmount(BigDecimal.ZERO);
        newFundDetail.setHasSettlement(FundSettlementStatus.FUNDNOTSETTLED);
        newFundDetail.setSettlementTime(new Date());
        newFundDetail.setExpectBonusAmount(BigDecimal.ZERO);
        newFundDetail.setExpectProfitAmount(BigDecimal.ZERO);
        newFundDetail.setSettlementCapital(BigDecimal.ZERO);
        newFundDetail.setCreateTime(new Date());
        newFundDetail.setUpdateTime(new Date());
        newFundDetail.setGrouponBonus(BigDecimal.ZERO);
        newFundDetail.setProductCloseType(closeType);
        newFundDetail.setHalfOpenBonusAmount(BigDecimal.ZERO);
        newFundDetail.setGrouponType(GroupOnType.NOTGROUPON.getValue());
        newFundDetail.setExchangeStatus(ExchangeType.NORMAL.getValue());
        return newFundDetail;
    }

    //创建变动明细记录,初始化数据为0
    private FundDetailAlteration initFundDetailAlteration(FundTotal currentFundTotal, FundDetail newFundDetail, FundDetailAlterationDealBean fundDetailAlterationDealBean) {
        FundDetailAlteration fundDetailAlteration = new FundDetailAlteration();
        fundDetailAlteration.setFundTotalUuid(currentFundTotal.getFundTotalUuid());
        fundDetailAlteration.setDetailAlterationUuid(sequenceGenerator.amcNoGen(AMCOrderType.FUND_DETAIL_ALTERATION));
        fundDetailAlteration.setFtcOrderCreateTime(fundDetailAlterationDealBean.getFtcOrderCreateTime());
        fundDetailAlteration.setFtcOrderNo(fundDetailAlterationDealBean.getFtcOrderNo());
        fundDetailAlteration.setFundAmount(BigDecimal.ZERO);
        fundDetailAlteration.setFundShare(BigDecimal.ZERO);
        fundDetailAlteration.setFundDetailId(newFundDetail.getId());
        fundDetailAlteration.setFundDetailUuid(newFundDetail.getFundDetailUuid());
        fundDetailAlteration.setFundTotalId(currentFundTotal.getId());
        fundDetailAlteration.setAlterationStatus(AlterationStatus.TA_NOTCONFIRM);
        fundDetailAlteration.setCreateTime(new Date());
        fundDetailAlteration.setUpdateTime(new Date());
        switch (fundDetailAlterationDealBean.getOrderType().intValue()) {
            case BussinessAlterationType.SUBSCRIPTION:
                fundDetailAlteration.setAlterationType(AlterationType.ADD);
                break;
            case BussinessAlterationType.PURCHASE:
                fundDetailAlteration.setAlterationType(AlterationType.ADD);
                break;
            case BussinessAlterationType.REDEMPTION:
                fundDetailAlteration.setAlterationType(AlterationType.SUB);
                break;
            default:
                throw new BusinessException(FTCRespCode.ERR_FUND_OPERATION_NOT_ILLEGUE.getMessage(),
                        FTCRespCode.ERR_FUND_OPERATION_NOT_ILLEGUE.getCode());

        }
        return fundDetailAlteration;
    }

    //根据业务,更新Alteration
    private FundDetailAlteration updateFundDetailAlteration(FundDetailAlteration fundDetailAlteration, FundDetailAlterationDealBean fundDetailAlterationDealBean) {
        fundDetailAlteration.setUpdateTime(new Date());
        switch (fundDetailAlterationDealBean.getOrderType().intValue()) {
            case BussinessAlterationType.SUBSCRIPTION:
                fundDetailAlteration.setFundAmount(fundDetailAlterationDealBean.getFundAmount());
                break;
            case BussinessAlterationType.PURCHASE:
                fundDetailAlteration.setFundAmount(fundDetailAlterationDealBean.getFundAmount());
                break;
            case BussinessAlterationType.REDEMPTION:
                fundDetailAlteration.setFundAmount(fundDetailAlterationDealBean.getFundAmount());
                break;
            default:
                throw new BusinessException(FTCRespCode.ERR_FUND_OPERATION_NOT_ILLEGUE.getMessage(),
                        FTCRespCode.ERR_FUND_OPERATION_NOT_ILLEGUE.getCode());
        }
        return fundDetailAlteration;
    }

    //根据业务更新FundDetail

    private FundDetail updateFundDetail(FundDetail fundDetail, FundDetailAlterationDealBean fundDetailAlterationDealBean, Boolean isGroupOnType, ProdTaTransInfo prodTaTransInfo,int closeType) {
        fundDetail.setUpdateTime(new Date());

        switch (fundDetailAlterationDealBean.getOrderType().intValue()) {
            case BussinessAlterationType.PURCHASE:
                fundDetail.setUnconfirmedAddAmount(fundDetail.getUnconfirmedAddAmount().add(fundDetailAlterationDealBean.getFundAmount()));
                fundDetail.setFundTotalAmount(fundDetail.getFundTotalAmount().add(fundDetailAlterationDealBean.getFundAmount()));
                if(closeType == ProductCloseType.HALF_OPEN || closeType == ProductCloseType.CLOSE_OPEN) {
                    fundDetail.setSettlementCapital(fundDetail.getSettlementCapital().add(fundDetailAlterationDealBean.getFundAmount()));
                    fundDetail.setExpectBonusAmount(calculateExpectedBonusAmount(fundDetail.getUnconfirmedAddAmount(), prodTaTransInfo));
                }
                if(isGroupOnType){
                    fundDetail.setGrouponType(GroupOnType.GROUPON.getValue());
                }
                break;
            case BussinessAlterationType.SUBSCRIPTION:
                fundDetail.setUnconfirmedAddAmount(fundDetail.getUnconfirmedAddAmount().add(fundDetailAlterationDealBean.getFundAmount()));
                fundDetail.setFundTotalAmount(fundDetail.getFundTotalAmount().add(fundDetailAlterationDealBean.getFundAmount()));
                fundDetail.setSettlementCapital(fundDetail.getSettlementCapital().add(fundDetailAlterationDealBean.getFundAmount()));
                fundDetail.setExpectProfitAmount(fundDetail.getExpectProfitAmount().add(fundDetailAlterationDealBean.getExpectProfitAmount()));
                fundDetail.setExpectBonusAmount(calculateExpectedBonusAmount(fundDetail.getUnconfirmedAddAmount(),prodTaTransInfo));
                if(isGroupOnType){
                    fundDetail.setGrouponType(GroupOnType.GROUPON.getValue());
                }
                break;
            case BussinessAlterationType.REDEMPTION:
                fundDetail.setUnconfirmedSubAmount(fundDetail.getUnconfirmedSubAmount().add(fundDetailAlterationDealBean.getFundAmount()));
                fundDetail.setConfirmedAddAmount(fundDetail.getConfirmedAddAmount().subtract(fundDetailAlterationDealBean.getFundAmount()));

                //判断confirm_add与Total是否小于0

                if (fundDetail.getConfirmedAddAmount().compareTo(new BigDecimal(0)) == -1
                                || fundDetail.getFundTotalAmount().compareTo(new BigDecimal(0)) == -1)
                    throw new BusinessException(FTCRespCode.ERR_FUND_AMC_CALL_TOTAL_FUND_NOT_ENOUGTH.getMessage(),
                            FTCRespCode.ERR_FUND_AMC_CALL_TOTAL_FUND_NOT_ENOUGTH.getCode());
                break;
            default:
                throw new BusinessException(FTCRespCode.ERR_FUND_OPERATION_NOT_ILLEGUE.getMessage(),
                        FTCRespCode.ERR_FUND_OPERATION_NOT_ILLEGUE.getCode());
        }
        return fundDetail;

    }

    //根据业务,更新FundTotal
    private FundTotal updateFundTotal(FundTotal fundTotal, FundDetailAlterationDealBean fundDetailAlterationDealBean) {
        fundTotal.setUpdateTime(new Date());
        switch (fundDetailAlterationDealBean.getOrderType().intValue()) {
            case BussinessAlterationType.SUBSCRIPTION:
                fundTotal.setTotalAsset(fundTotal.getTotalAsset().add(fundDetailAlterationDealBean.getFundAmount()));
                fundTotal.setUnconfirmedAddAmount(fundTotal.getUnconfirmedAddAmount().add(fundDetailAlterationDealBean.getFundAmount()));
                break;
            case BussinessAlterationType.PURCHASE:
                fundTotal.setTotalAsset(fundTotal.getTotalAsset().add(fundDetailAlterationDealBean.getFundAmount()));
                fundTotal.setUnconfirmedAddAmount(fundTotal.getUnconfirmedAddAmount().add(fundDetailAlterationDealBean.getFundAmount()));
                break;
            case BussinessAlterationType.REDEMPTION:
                fundTotal.setUnconfirmedSubAmount(fundTotal.getUnconfirmedSubAmount().add(fundDetailAlterationDealBean.getFundAmount()));
                fundTotal.setConfirmedAddAmount(fundTotal.getConfirmedAddAmount().subtract(fundDetailAlterationDealBean.getFundAmount()));
                if(fundTotal.getTotalAsset().compareTo(BigDecimal.ZERO) == -1
                    || fundTotal.getConfirmedAddAmount().compareTo(BigDecimal.ZERO) == -1)
                    throw new BusinessException(FTCRespCode.ERR_FUND_AMC_CALL_TOTAL_FUND_NOT_ENOUGTH.getMessage(),
                            FTCRespCode.ERR_FUND_AMC_CALL_TOTAL_FUND_NOT_ENOUGTH.getCode());
                break;
            default:
                throw new BusinessException(FTCRespCode.ERR_FUND_OPERATION_NOT_ILLEGUE.getMessage(),
                        FTCRespCode.ERR_FUND_OPERATION_NOT_ILLEGUE.getCode());
        }

        return fundTotal;
    }

    //根据业务,更新MemAsset
    private MemberAsset updateMemberAsset(MemberAsset memberAsset, FundDetailAlterationDealBean fundDetailAlterationDealBean) {
        memberAsset.setUpdateTime(new Date());
        switch (fundDetailAlterationDealBean.getOrderType().intValue()) {
            case BussinessAlterationType.SUBSCRIPTION:
                memberAsset.setTotalAsset(memberAsset.getTotalAsset().add(fundDetailAlterationDealBean.getFundAmount()));
                memberAsset.setUnconfirmedAddAmount(memberAsset.getUnconfirmedAddAmount().add(fundDetailAlterationDealBean.getFundAmount()));
                break;
            case BussinessAlterationType.PURCHASE:
                memberAsset.setTotalAsset(memberAsset.getTotalAsset().add(fundDetailAlterationDealBean.getFundAmount()));
                memberAsset.setUnconfirmedAddAmount(memberAsset.getUnconfirmedAddAmount().add(fundDetailAlterationDealBean.getFundAmount()));
                break;
            case BussinessAlterationType.REDEMPTION:
                memberAsset.setUnconfirmedSubAmount(memberAsset.getUnconfirmedSubAmount().add(fundDetailAlterationDealBean.getFundAmount()));
                memberAsset.setConfirmedAddAmount(memberAsset.getConfirmedAddAmount().subtract(fundDetailAlterationDealBean.getFundAmount()));
                if(memberAsset.getTotalAsset().compareTo(BigDecimal.ZERO) == -1
                    || memberAsset.getConfirmedAddAmount().compareTo(BigDecimal.ZERO) == -1)
                    throw new BusinessException(FTCRespCode.ERR_FUND_AMC_CALL_TOTAL_FUND_NOT_ENOUGTH.getMessage(),
                            FTCRespCode.ERR_FUND_AMC_CALL_TOTAL_FUND_NOT_ENOUGTH.getCode());
                break;
            default:
                throw new BusinessException(FTCRespCode.ERR_FUND_OPERATION_NOT_ILLEGUE.getMessage(),
                        FTCRespCode.ERR_FUND_OPERATION_NOT_ILLEGUE.getCode());
        }

        return memberAsset;
    }


    //计算定期产品预期红利收益
    private BigDecimal calculateExpectedBonusAmount(BigDecimal amount,ProdTaTransInfo prodTaTransInfo) {
        BigDecimal result = MoneyUtil.divide(amount.multiply(prodTaTransInfo.getAnnualYieldRate()).multiply(new BigDecimal(prodTaTransInfo.getProductLimit())), new BigDecimal(prodTaTransInfo.getPerYearDate()));
        result = result.setScale(2, BigDecimal.ROUND_DOWN);
        return result;
    }


}
