package com.eif.ftc.facade.fund.amc.request;

import com.eif.framework.common.utils.pkg.BaseRequest;
import com.eif.ftc.facade.fund.amc.dto.request.FundAccountMemberNoBean;

/**
 * Created by Matt on 16/1/19.
 */
public class ForbiddenAccountTransactionRequest extends BaseRequest {

    private static final long serialVersionUID = 5995633241659133565L;

    private FundAccountMemberNoBean fundAccountMemberNoBean;

    public FundAccountMemberNoBean getFundAccountMemberNoBean() {
        return fundAccountMemberNoBean;
    }

    /**
     * @param fundAccountMemberNoBean 会员bean
     * @occurs required
     */
    public void setFundAccountMemberNoBean(FundAccountMemberNoBean fundAccountMemberNoBean) {
        this.fundAccountMemberNoBean = fundAccountMemberNoBean;
    }
}
