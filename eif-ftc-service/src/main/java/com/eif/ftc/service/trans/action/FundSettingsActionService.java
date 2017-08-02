package com.eif.ftc.service.trans.action;

import com.eif.ftc.dal.model.FundTransOrder;

import java.util.Date;

/**
 * Created by bohan on 9/29/16.
 */
public interface FundSettingsActionService {
    void updateRefundFundTransOrder(Date curDate, FundTransOrder transOrder, String productName);
}
