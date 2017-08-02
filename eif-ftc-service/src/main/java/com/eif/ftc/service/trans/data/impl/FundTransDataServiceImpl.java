package com.eif.ftc.service.trans.data.impl;

import com.eif.framework.common.utils.code.CommonRspCode;
import com.eif.ftc.dal.dao.FundTransOrderExceptionMapper;
import com.eif.ftc.dal.dao.FundTransOrderStatusInfoMapper;
import com.eif.ftc.dal.model.FundTransOrderException;
import com.eif.ftc.dal.model.FundTransOrderStatusInfo;
import com.eif.ftc.service.fund.amc.FundAccountService;
import com.eif.ftc.service.trans.data.FundTransDataService;
import com.eif.ftc.util.exception.BusinessException;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * Created by bohan on 9/21/16.
 */
@Service("fundTransDataService")
public class FundTransDataServiceImpl implements FundTransDataService {

    @Autowired
    FundTransOrderStatusInfoMapper fundTransOrderStatusInfoMapper;

    @Autowired
    FundTransOrderExceptionMapper fundTransOrderExceptionMapper;

    @Autowired
    FundAccountService fundAccountService;

    public void createFundTransOrderStatusInfo(Date curDate, int status, Long orderID) {
        createFundTransOrderStatusInfo(curDate, status, orderID, null, null);
    }

    public void createFundTransOrderStatusInfo(Date curDate, int status, Long orderID, String remark, String operNo) {
        FundTransOrderStatusInfo fundTransOrderStatusInfo = new FundTransOrderStatusInfo();
        fundTransOrderStatusInfo.setStatus(status);
        fundTransOrderStatusInfo.setUpdateTime(curDate);
        fundTransOrderStatusInfo.setOrderId(orderID);
        fundTransOrderStatusInfo.setRemark(remark);
        fundTransOrderStatusInfo.setOperatorNo(operNo);
        fundTransOrderStatusInfoMapper.insertSelective(fundTransOrderStatusInfo);
    }

    public void logFundTransOrderException(String orderNo, BusinessException ex, Date curDate) {
        FundTransOrderException fundTransOrderException = new FundTransOrderException();
        fundTransOrderException.setFundTransOrderNo(orderNo);
        fundTransOrderException.setErrCode(ex.getCode());
        if (!StringUtils.isEmpty(ex.getMessage())) {
            fundTransOrderException.setErrMsg(ex.getMsg());
        }
        else {
            fundTransOrderException.setErrMsg("");
        }
        fundTransOrderException.setCreateTime(curDate);
        fundTransOrderExceptionMapper.insertSelective(fundTransOrderException);
    }

    public void logFundTransOrderException(String orderNo, Throwable ex, Date curDate) {
        if (ex instanceof BusinessException) {
            logFundTransOrderException(orderNo, (BusinessException)ex, curDate);
        }
        else {
            FundTransOrderException fundTransOrderException = new FundTransOrderException();
            fundTransOrderException.setFundTransOrderNo(orderNo);
            fundTransOrderException.setErrCode(CommonRspCode.SYS_ERROR.getCode());
            if (!StringUtils.isEmpty(ex.getMessage())) {
                fundTransOrderException.setErrMsg(ex.getMessage());
            }
            else {
                fundTransOrderException.setErrMsg("");
            }

            fundTransOrderException.setCreateTime(curDate);
            fundTransOrderExceptionMapper.insertSelective(fundTransOrderException);
        }
    }
}
