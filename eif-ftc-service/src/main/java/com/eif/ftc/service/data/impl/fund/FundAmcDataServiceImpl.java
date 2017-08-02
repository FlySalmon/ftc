package com.eif.ftc.service.data.impl.fund;
import com.alibaba.fastjson.JSON;
import com.eif.fis.facade.dto.ftc.ProdTaTransInfo;
import com.eif.ftc.dal.model.FundTransOrder;
import com.eif.ftc.facade.code.FTCRespCode;
import com.eif.ftc.facade.fund.amc.constant.FundAcctStatus;
import com.eif.ftc.facade.fund.trans.enumeration.FundTransType;
import com.eif.ftc.integration.risk.RiskIntService;
import com.eif.ftc.service.bean.FundAccountBean;
import com.eif.ftc.service.bean.FundPurchaseBean;
import com.eif.ftc.service.bean.FundRedemptionBean;
import com.eif.ftc.service.bean.FundSubscriptionBean;
import com.eif.ftc.service.data.fund.FundAmcDataService;
import com.eif.ftc.service.fund.amc.FundAccountService;
import com.eif.ftc.service.fund.amc.FundDetailAlterationService;
import com.eif.ftc.util.exception.BusinessException;
import com.eif.risk.facade.constant.RiskMemberStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by bohan on 1/12/16.
 */
@Service("fundAmcDataService")
public class FundAmcDataServiceImpl implements FundAmcDataService {

    private static Logger logger = LoggerFactory.getLogger(FundAmcDataServiceImpl.class);
    @Autowired
    FundDetailAlterationService fundDetailAlterationService;

    @Autowired
    FundAccountService fundAccountService;

    @Resource
    RiskIntService riskIntService;

    @Override
    public void redeemAsset(Date curDate, String transOrderNo, String memberNo, Long productID,
                            BigDecimal transAmount,Integer closeType) {
        // 资产中心调用扣减
        FundRedemptionBean fundRedemptionBean = new FundRedemptionBean();
        fundRedemptionBean.setFtcOrderCreateTime(curDate);
        fundRedemptionBean.setFtcOrderNo(transOrderNo);
        fundRedemptionBean.setMemberNo(memberNo);
        fundRedemptionBean.setProductId(productID);
        fundRedemptionBean.setFundAmount(transAmount);

        Boolean result = fundDetailAlterationService.addFundDetailAlterationByRedemptionRecord(fundRedemptionBean,closeType);

        if (!result) {
            throw new BusinessException(FTCRespCode.ERR_FUND_AMC_CALL_REDEEM.getMessage(),
                    FTCRespCode.ERR_FUND_AMC_CALL_REDEEM.getCode());
        }
    }

    @Override
    public void processAssetAdd(FundTransOrder targetOrder, BigDecimal exceptedProfitAmount, Boolean isGroupOnType,int closeType,ProdTaTransInfo prodTaTransInfo) {
        if (targetOrder.getFundTransType().equals(FundTransType.PURCHASING)) {
            amcPurchase(targetOrder, exceptedProfitAmount, isGroupOnType, closeType,prodTaTransInfo);
        } else {
            amcSubscribe(targetOrder, exceptedProfitAmount, isGroupOnType, closeType,prodTaTransInfo);
        }
    }

    public FundAccountBean justifyAccountStatus(FundTransOrder transOrder, String memberNo, boolean isReedem) {
        FundAccountBean resultBean = fundAccountService.getFundAccountByMemberNo(memberNo);
        if (resultBean == null)
            return null;
        int fundAcctStatus = resultBean.getAccountStatus();
        transOrder.setAssetAccountNo(resultBean.getFundAccountNo());
        transOrder.setRefFundAccountNo(resultBean.getTaAccountId());
        transOrder.setTransAccountNo(resultBean.getTransactionAccount());

        if (fundAcctStatus == FundAcctStatus.FROZEN || fundAcctStatus == FundAcctStatus.TA_FAILED
                || fundAcctStatus == FundAcctStatus.TA_FINISH) {
            throw new BusinessException(String.format("基金资产账号状态错误, 状态为: %s", fundAcctStatus),
                    FTCRespCode.ERR_FUND_ACCT_STATUS.getCode());
        }

        if (riskIntService.getRiskUserLock(memberNo).getTransLock() == RiskMemberStatus.FORBIDDEN) {
            throw new BusinessException(FTCRespCode.ERR_FUND_ACCT_TRANS_FORBIDDEN.getMessage(),
                    FTCRespCode.ERR_FUND_ACCT_TRANS_FORBIDDEN.getCode());
        }
        return resultBean;
    }


    public void amcPurchase(FundTransOrder targetOrder, BigDecimal exceptedProfitAmount,Boolean isGroupOnType,Integer closeType,ProdTaTransInfo prodTaTransInfo) {
        FundPurchaseBean fundPurchaseBean = new FundPurchaseBean();
        fundPurchaseBean.setFtcOrderNo(targetOrder.getFundTransOrderNo());
        fundPurchaseBean.setFundAmount(targetOrder.getFundTransAmount());
        fundPurchaseBean.setMemberNo(targetOrder.getMemberNo());
        fundPurchaseBean.setProductId(targetOrder.getProductId());
        fundPurchaseBean.setFtcOrderCreateTime(targetOrder.getCreateTime());
        fundPurchaseBean.setExpectProfitAmount(exceptedProfitAmount);

        logger.warn("FundPurchaseBean is " + JSON.toJSONString(fundPurchaseBean));

        Boolean result = fundDetailAlterationService.addFundDetailAlterationByPurchaseRecord(fundPurchaseBean,isGroupOnType,closeType,prodTaTransInfo);

        if (!result) {
            throw new BusinessException(FTCRespCode.ERR_FUND_AMC_PURCHASE_RECORD_CREATE_FAILURE.getMessage(),
                    FTCRespCode.ERR_FUND_AMC_PURCHASE_RECORD_CREATE_FAILURE.getCode());
        }
    }


    public void amcSubscribe(FundTransOrder targetOrder, BigDecimal exceptedProfitAmount, Boolean isGroupOnType, Integer closeType, ProdTaTransInfo prodTaTransInfo) {
        FundSubscriptionBean subBean = new FundSubscriptionBean();
        subBean.setFtcOrderNo(targetOrder.getFundTransOrderNo());
        subBean.setFundAmount(targetOrder.getFundTransAmount());
        subBean.setMemberNo(targetOrder.getMemberNo());
        subBean.setProductId(targetOrder.getProductId());
        subBean.setFtcOrderCreateTime(targetOrder.getCreateTime());
        subBean.setExpectProfitAmount(exceptedProfitAmount);

        Boolean result = fundDetailAlterationService.addFundDetailAlterationBySubscriptionRecord(subBean,isGroupOnType,prodTaTransInfo,closeType);
        if (!result) {
            throw new BusinessException(FTCRespCode.ERR_FUND_AMC_SUBSCRIPTION_RECORD_CREATE_FAILURE.getMessage(),
                    FTCRespCode.ERR_FUND_AMC_SUBSCRIPTION_RECORD_CREATE_FAILURE.getCode());
        }
    }

}
