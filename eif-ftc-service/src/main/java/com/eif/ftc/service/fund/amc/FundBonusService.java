package com.eif.ftc.service.fund.amc;

import com.eif.framework.common.utils.pkg.page.PageableResponse;
import com.eif.ftc.facade.fund.amc.dto.response.BonusBean;
import com.eif.ftc.facade.fund.amc.request.FundBonusPageableRequest;

/**
 * Created by Matt on 2016/12/8.
 */
public interface FundBonusService {
    PageableResponse<BonusBean> getFundBonusList(FundBonusPageableRequest fundBonusPageableRequest);
}
