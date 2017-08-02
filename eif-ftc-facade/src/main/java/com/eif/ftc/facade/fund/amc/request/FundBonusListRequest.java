package com.eif.ftc.facade.fund.amc.request;

import com.eif.framework.common.utils.pkg.BaseRequest;
import com.eif.ftc.facade.fund.amc.dto.request.FundBonusListQueryBean;

/**
 * Created by Matt on 16/1/6.
 */
public class FundBonusListRequest extends BaseRequest {

    private static final long serialVersionUID = -4322875597604305138L;

    private FundBonusListQueryBean fundBonusListQueryBean;

    public FundBonusListQueryBean getFundBonusListQueryBean() {
        return fundBonusListQueryBean;
    }

    /**
     * @param fundBonusListQueryBean 用户请求某个基金红利Bean
     * @occurs required
     */

    public void setFundBonusListQueryBean(FundBonusListQueryBean fundBonusListQueryBean) {
        this.fundBonusListQueryBean = fundBonusListQueryBean;
    }
}
