package com.eif.ftc.service.impl.fund.amc;


import com.eif.ftc.dal.dao.FundTotalMapper;
import com.eif.ftc.dal.dao.MemberAssetMapper;
import com.eif.ftc.dal.dao.MemberFundAccountMapper;
import com.eif.ftc.dal.model.FundTotal;
import com.eif.ftc.dal.model.MemberAsset;
import com.eif.ftc.dal.model.MemberFundAccount;
import com.eif.ftc.facade.code.FTCRespCode;
import com.eif.ftc.facade.fund.amc.constant.AMCSystemDefination;
import com.eif.ftc.facade.fund.amc.constant.FundAccountRiskStatus;
import com.eif.ftc.facade.fund.amc.constant.FundAcctStatus;
import com.eif.ftc.service.bean.FundAccountBean;
import com.eif.ftc.service.bean.AddFundAccountBean;
import com.eif.ftc.facade.fund.amc.dto.request.FundAccountMemberNoBean;
import com.eif.ftc.service.constant.AMCOrderType;
import com.eif.ftc.service.fund.amc.FundAccountService;
import com.eif.ftc.util.SequenceGenerator;
import com.eif.ftc.util.exception.BusinessException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by Matt on 15/12/18.
 */
@Service("amcFundAccountService")
public class FundAccountServiceImpl implements FundAccountService {

    @Resource
    SequenceGenerator sequenceGenerator;

    @Autowired
    MemberFundAccountMapper memberFundAccountMapper;

    @Autowired
    MemberAssetMapper memberAssetMapper;

    @Autowired
    FundTotalMapper fundTotalMapper;

    @Override
    public Boolean fundAccountForbiddenTransaction(FundAccountMemberNoBean fundAccountMemberNoBean) {
        MemberFundAccount memberFundAccount = memberFundAccountMapper.selectByMemberNo(fundAccountMemberNoBean.getMemberNO());
        if (memberFundAccount == null) {
            throw new BusinessException(FTCRespCode.ERR_FUND_ACCT_NOT_FOUND.getMessage(),
                    FTCRespCode.ERR_FUND_ACCT_NOT_FOUND.getCode());
        }

        memberFundAccount.setAccountRiskStatus(FundAccountRiskStatus.TRANSACTION_FORBIDDEN);
        memberFundAccount.setUpdateTime(new Date());
        memberFundAccountMapper.updateByPrimaryKey(memberFundAccount);
        return true;
    }


    @Override
    public Boolean fundAccountActiveTransaction(FundAccountMemberNoBean fundAccountMemberNoBean) {
        MemberFundAccount memberFundAccount = memberFundAccountMapper.selectByMemberNo(fundAccountMemberNoBean.getMemberNO());
        if (memberFundAccount == null) {
            throw new BusinessException(FTCRespCode.ERR_FUND_ACCT_NOT_FOUND.getMessage(),
                    FTCRespCode.ERR_FUND_ACCT_NOT_FOUND.getCode());
        }

        memberFundAccount.setAccountRiskStatus(FundAccountRiskStatus.TRANSACTION_NORMAL);
        memberFundAccount.setUpdateTime(new Date());
        memberFundAccountMapper.updateByPrimaryKey(memberFundAccount);
        return true;
    }


    @Transactional
    @Override
    public FundAccountBean addFundAccount(AddFundAccountBean addFundAccountBean) {

        //判断当前用户是否存在资产平台账号
        MemberFundAccount memberFundAccount = memberFundAccountMapper.selectByMemberNo(addFundAccountBean.getMemberNo());


        if (memberFundAccount != null)
            return generateFundAccountBean(memberFundAccount);

        //创建账户
        MemberFundAccount memAccount = initMemberFundAccount(addFundAccountBean);
        memberFundAccountMapper.insert(memAccount);

        //创建总资产信息
        MemberAsset memberAsset = initMemberAsset(memAccount);
        memberAssetMapper.insert(memberAsset);


        //创建基金资产信息
        FundTotal fundTotal = initFundTotal(memAccount, memberAsset);
        fundTotalMapper.insert(fundTotal);
        FundAccountBean result = generateFundAccountBean(memAccount);
        return result;
    }



    @Transactional
    @Override
    public FundAccountBean getFundAccountByMemberNo(String memberNo) {
        MemberFundAccount memberFundAccount = memberFundAccountMapper.selectByMemberNo(memberNo);

        if (memberFundAccount != null)
            return generateFundAccountBean(memberFundAccount);
        else
            return null;
    }

    private FundAccountBean generateFundAccountBean(MemberFundAccount memberFundAccount) {
        FundAccountBean result = new FundAccountBean();
        result.setAccountStatus(memberFundAccount.getAccountStatus());
        result.setFtcCreateAccountNo(memberFundAccount.getFtcCreateAccNo());
        result.setFundAccountNo(memberFundAccount.getFundAccUuid());
        result.setId(memberFundAccount.getId());
        result.setMemberNo(memberFundAccount.getMemberNo());
        result.setTaAccountId(memberFundAccount.getTaAccount());
        result.setTransactionAccount(memberFundAccount.getTransactionAccount());
        result.setAccountRiskStatus(memberFundAccount.getAccountRiskStatus());
        return result;

    }

    //初始化账户
    private MemberFundAccount initMemberFundAccount(AddFundAccountBean addFundAccountBean) {
        MemberFundAccount memAccount = new MemberFundAccount();
        memAccount.setAccountStatus(FundAcctStatus.TA_PENDING);
        memAccount.setChannelNo(AMCSystemDefination.CHANNELNO);
        memAccount.setTaCompanyNo(AMCSystemDefination.TACOMPANYNO);
        memAccount.setFtcCreateAccNo(addFundAccountBean.getFtcCreateAccNo());
        memAccount.setFtcCreateTime(new Date());
        memAccount.setFundAccUuid(sequenceGenerator.amcNoGen(AMCOrderType.FUND_ACC));
        memAccount.setMemberNo(addFundAccountBean.getMemberNo());
        memAccount.setTaAccount(" ");
        memAccount.setAccountRiskStatus(FundAccountRiskStatus.TRANSACTION_NORMAL);
        memAccount.setTransactionAccount(sequenceGenerator.transAccGen());
        memAccount.setCreateTime(new Date());
        memAccount.setUpdateTime(new Date());
        return memAccount;
    }

    //初始化总资产
    private MemberAsset initMemberAsset(MemberFundAccount memAccount) {
        MemberAsset memberAsset = new MemberAsset();
        memberAsset.setChannelNo(AMCSystemDefination.CHANNELNO);
        memberAsset.setMemberAssetUuid(sequenceGenerator.amcNoGen(AMCOrderType.MEM_ASSET));
        memberAsset.setMemberNo(memAccount.getMemberNo());
        memberAsset.setTotalAsset(BigDecimal.ZERO);
        memberAsset.setTotalProfit(BigDecimal.ZERO);
        memberAsset.setYesterdayProfit(BigDecimal.ZERO);
        memberAsset.setFrozenAmount(BigDecimal.ZERO);
        memberAsset.setFrozenShare(BigDecimal.ZERO);
        memberAsset.setConfirmedAddAmount(BigDecimal.ZERO);
        memberAsset.setConfirmedAddShare(BigDecimal.ZERO);
        memberAsset.setUnconfirmedAddAmount(BigDecimal.ZERO);
        memberAsset.setUnconfirmedAddShare(BigDecimal.ZERO);
        memberAsset.setConfirmedSubAmount(BigDecimal.ZERO);
        memberAsset.setConfirmedSubShare(BigDecimal.ZERO);
        memberAsset.setUnconfirmedSubAmount(BigDecimal.ZERO);
        memberAsset.setUnconfirmedSubShare(BigDecimal.ZERO);
        memberAsset.setUpdateTime(new Date());
        memberAsset.setCreateTime(new Date());
        return memberAsset;
    }

    //初始化基金金资产
    private FundTotal initFundTotal(MemberFundAccount memberFundAccount, MemberAsset memberAsset) {
        FundTotal fundTotal = new FundTotal();
        fundTotal.setYesterdayProfit(BigDecimal.ZERO);
        fundTotal.setTotalProfit(BigDecimal.ZERO);
        fundTotal.setTotalAsset(BigDecimal.ZERO);
        fundTotal.setFundAccId(memberFundAccount.getId());
        fundTotal.setFundAccUuid(memberFundAccount.getFundAccUuid());
        fundTotal.setFundTotalUuid(sequenceGenerator.amcNoGen(AMCOrderType.FUND_TOTAL));
        fundTotal.setMemberAssetId(memberAsset.getId());
        fundTotal.setMemberAssetUuid(memberAsset.getMemberAssetUuid());
        fundTotal.setFrozenAmount(BigDecimal.ZERO);
        fundTotal.setFrozenShare(BigDecimal.ZERO);
        fundTotal.setConfirmedAddAmount(BigDecimal.ZERO);
        fundTotal.setConfirmedAddShare(BigDecimal.ZERO);
        fundTotal.setUnconfirmedAddAmount(BigDecimal.ZERO);
        fundTotal.setUnconfirmedAddShare(BigDecimal.ZERO);
        fundTotal.setConfirmedSubAmount(BigDecimal.ZERO);
        fundTotal.setConfirmedSubShare(BigDecimal.ZERO);
        fundTotal.setUnconfirmedSubAmount(BigDecimal.ZERO);
        fundTotal.setUnconfirmedSubShare(BigDecimal.ZERO);
        fundTotal.setCreateTime(new Date());
        fundTotal.setUpdateTime(new Date());
        return fundTotal;
    }


}
