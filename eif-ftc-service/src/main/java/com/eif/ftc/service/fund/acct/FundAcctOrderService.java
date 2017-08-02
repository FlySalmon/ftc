package com.eif.ftc.service.fund.acct;

import com.eif.ftc.service.bean.FundAccountBean;

/**
 * Created by bohan on 1/2/16.
 */
public interface FundAcctOrderService {
    FundAccountBean openFundAccount(int bizChannel, String memberNo, Long productID);
}
