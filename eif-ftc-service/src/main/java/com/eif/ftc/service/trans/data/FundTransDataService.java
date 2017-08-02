package com.eif.ftc.service.trans.data;

import com.eif.ftc.facade.fund.trans.enumeration.FundTransOrderStatus;
import com.eif.ftc.util.exception.BusinessException;

import java.util.Date;

/**
 * Created by bohan on 9/21/16.
 */
public interface FundTransDataService {
    void createFundTransOrderStatusInfo(Date curDate, int status, Long orderID, String remark, String operNo);
    void createFundTransOrderStatusInfo(Date curDate, int status, Long orderID);
    void logFundTransOrderException(String orderNo, BusinessException ex, Date curDate);
    void logFundTransOrderException(String orderNo, Throwable ex, Date curDate);
}
