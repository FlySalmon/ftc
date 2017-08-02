package com.eif.ftc.service.trans.action;

import com.eif.ftc.dal.model.FundTransOrder;
import com.eif.ftc.service.bean.FundAccountBean;
import com.eif.member.facade.pkg.response.QueryMemberInfoResponse;

import java.util.Date;

/**
 * Created by bohan on 9/22/16.
 */
public interface FundBuyingCreateActionService {
    void riskPreCheck(FundTransOrder contextFundTransOrder, String deviceInfo, String devId, String ip, String productName);
}
