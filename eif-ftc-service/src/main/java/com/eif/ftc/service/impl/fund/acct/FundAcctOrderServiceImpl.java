package com.eif.ftc.service.impl.fund.acct;


import com.eif.framework.concurrent.lock.RedisConcurrentLock;
import com.eif.ftc.dal.dao.FundAcctOrderMapper;
import com.eif.ftc.dal.dao.FundAcctOrderStatusInfoMapper;
import com.eif.ftc.dal.model.FundAcctOrder;
import com.eif.ftc.dal.model.FundAcctOrderStatusInfo;
import com.eif.ftc.facade.fund.acct.enumeration.FundAcctOperType;
import com.eif.ftc.facade.fund.acct.enumeration.FundAcctOrderStatus;
import com.eif.ftc.service.bean.FundAccountBean;
import com.eif.ftc.service.bean.AddFundAccountBean;
import com.eif.ftc.facade.fund.trans.enumeration.FundInteractType;
import com.eif.ftc.service.fund.acct.FundAcctOrderService;
import com.eif.ftc.service.fund.amc.FundAccountService;
import com.eif.ftc.util.SequenceGenerator;
import com.eif.ftc.util.uuid.UUIDGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;

/**
 * Created by bohan on 1/2/16.
 */
@Service("fundAcctOrderService")
public class FundAcctOrderServiceImpl implements FundAcctOrderService {

    @Autowired
    FundAcctOrderMapper fundAcctOrderMapper;

    @Autowired
    FundAcctOrderStatusInfoMapper fundAcctOrderStatusInfoMapper;

    @Autowired
    FundAccountService fundAccountService;

    @Resource
    SequenceGenerator sequenceGenerator;

    @Resource
    RedisConcurrentLock redisConcurrentLock;

    static int KEY_EXPIRED_TIME_IN_SECOND = 10;

    public FundAccountBean openFundAccount(int bizChannel, String memberNo, Long productID) {
        // 之前无资产账号，开始创建
        //控制并发
        redisConcurrentLock.acquire(memberNo.concat(String.valueOf(bizChannel)), KEY_EXPIRED_TIME_IN_SECOND);
        try{
            String fundAcctOrderNo = sequenceGenerator.acctOrderGen(FundAcctOperType.OPEN_ACCOUNT);

            AddFundAccountBean afaBean = new AddFundAccountBean();
            afaBean.setMemberNo(memberNo);
            afaBean.setFtcCreateAccNo(fundAcctOrderNo);
            FundAccountBean fundAccountBean = fundAccountService.getFundAccountByMemberNo(memberNo);

            if (fundAccountBean == null)
            {
                fundAccountBean = fundAccountService.addFundAccount(afaBean);
                createFundAcctOrder(bizChannel, memberNo, productID, new Date(), fundAcctOrderNo, fundAccountBean, FundAcctOrderStatus.ACCOUNT_PROCESS_SUC);
                return fundAccountBean;
            }
            else{
                return fundAccountBean;
            }
        }finally {
            redisConcurrentLock.release(memberNo.concat(String.valueOf(bizChannel)));
        }

    }

    private String createFundAcctOrder(int bizChannel, String memberNo, Long productID, Date curDate, String fundAcctOrderNo, FundAccountBean fundAccountBean, int acctStatus) {
        // 创建基金账户单
        FundAcctOrder acctOrder = new FundAcctOrder();
        acctOrder.setBizChannel(bizChannel);
        acctOrder.setCreateTime(curDate);
        acctOrder.setUpdateTime(curDate);
        acctOrder.setFundAcctOperType(FundAcctOperType.OPEN_ACCOUNT);
        acctOrder.setFundInteractType(FundInteractType.FILE);
        acctOrder.setMemberNo(memberNo);
        acctOrder.setProductId(productID);
        acctOrder.setStatus(acctStatus);
        acctOrder.setTrackingNo(UUIDGenerator.gen());
        acctOrder.setTransTime(curDate);
        acctOrder.setAssetAccountNo(fundAccountBean.getFundAccountNo());
        acctOrder.setFundTransAcctNo(fundAccountBean.getTransactionAccount());
        acctOrder.setFundAcctOrderNo(fundAcctOrderNo);
        fundAcctOrderMapper.insertSelective(acctOrder);

        // 创建基金账户状态单
        FundAcctOrderStatusInfo fundAcctStatusInfo = new FundAcctOrderStatusInfo();
        fundAcctStatusInfo.setOrderId(acctOrder.getId());
        fundAcctStatusInfo.setStatus(acctStatus);
        fundAcctStatusInfo.setUpdateTime(curDate);

        fundAcctOrderStatusInfoMapper.insertSelective(fundAcctStatusInfo);

        return acctOrder.getFundAcctOrderNo();
    }
}
