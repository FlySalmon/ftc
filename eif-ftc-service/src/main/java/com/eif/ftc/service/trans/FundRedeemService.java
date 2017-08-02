package com.eif.ftc.service.trans;

import com.eif.ftc.facade.fund.trans.request.RedeemFundRequest;
import com.eif.ftc.facade.fund.trans.response.RedeemFundResponse;
import com.eif.ftc.service.constant.HugeRedeemOperationType;

/**
 * Created by bohan on 9/21/16.
 */
public interface FundRedeemService {

    void redeemFund(RedeemFundRequest redeemFundRequest, RedeemFundResponse resp);

}