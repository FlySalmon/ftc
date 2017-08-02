package com.eif.ftc.service.test.amc.impl;

import com.eif.ftc.dal.bean.FundDetailUpdateBean;
import com.eif.ftc.dal.bean.FundTotalUpdateBean;
import com.eif.ftc.dal.bean.MemberAssetUpdateBean;
import com.eif.ftc.dal.dao.*;
import com.eif.ftc.facade.code.FTCRespCode;
import com.eif.ftc.facade.fund.amc.constant.AlterationStatus;
import com.eif.ftc.integration.fis.FisIntService;
import com.eif.ftc.service.bean.*;
import com.eif.ftc.service.fund.amc.FundDetailAlterationService;
import com.eif.ftc.service.test.amc.FundDetailAlterationTestService;
import com.eif.ftc.util.SequenceGenerator;
import com.eif.ftc.util.exception.BusinessException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Matt on 15/12/21.
 */
@Service("fundDetailAlterationTestServiceImpl")
public class FundDetailAlterationTestServiceImpl implements FundDetailAlterationTestService {

    private final static Logger logger = LoggerFactory.getLogger(FundDetailAlterationTestServiceImpl.class);

    @Autowired
    MemberAssetMapper memberAssetMapper;

    @Autowired
    FundTotalMapper fundTotalMapper;

    @Autowired
    FundDetailMapper fundDetailMapper;

    @Autowired
    FundDetailAlterationMapper fundDetailAlterationMapper;

    //活期申购确认
    @Transactional(rollbackFor={RuntimeException.class, Exception.class})
    public Integer updateFundByPurchaseConfirmList(List<FundPurchaseConfirmBean> fundPurchaseCfmBeanList) {
        if (CollectionUtils.isEmpty(fundPurchaseCfmBeanList)) return 0;

        Date currentDate = new Date();
        List<String> detailAlterationNeedUpdate = new ArrayList<String>();

        for(FundPurchaseConfirmBean fundPurchaseConfirmBean : fundPurchaseCfmBeanList) {
            if (fundPurchaseConfirmBean.getFtcOrderNo() == null) {
                throw new BusinessException(FTCRespCode.ERR_FUND_AMC_FTC_ORDER_NO_ISNULL.getMessage(),
                        FTCRespCode.ERR_FUND_AMC_FTC_ORDER_NO_ISNULL.getCode());
            }
            detailAlterationNeedUpdate.add(fundPurchaseConfirmBean.getFtcOrderNo());
        }

        if(detailAlterationNeedUpdate.size() > 0) {
            List<FundDetailUpdateBean> fundDetailUpdateBeen = fundDetailAlterationMapper.selectFundDetailByFTCOrderNoList(detailAlterationNeedUpdate,AlterationStatus.TA_NOTCONFIRM);
            List<FundTotalUpdateBean> fundTotalUpdateBeen = fundDetailAlterationMapper.selectFundTotalByFTCOrderNoList(detailAlterationNeedUpdate,AlterationStatus.TA_NOTCONFIRM);
            List<MemberAssetUpdateBean> memberAssetUpdateBeen = fundDetailAlterationMapper.selectMemberAssetByFTCOrderNoList(detailAlterationNeedUpdate,AlterationStatus.TA_NOTCONFIRM);

            if(fundDetailUpdateBeen.size() > 0) {
                memberAssetMapper.updateMemberAssetByPurchaseConfirm(memberAssetUpdateBeen);
            }
            if(fundTotalUpdateBeen.size() > 0) {
                fundTotalMapper.updateFundTotalByPurchaseConfirm(fundTotalUpdateBeen);
            }
            if(fundDetailUpdateBeen.size() > 0) {
                fundDetailMapper.updateFundDetailByPurchaseConfirm(fundDetailUpdateBeen);
            }
            fundDetailAlterationMapper.updateFundDetailAlternationStatus(detailAlterationNeedUpdate,AlterationStatus.TA_CONFIRM);
        }

        return fundPurchaseCfmBeanList.size();
    }

}
