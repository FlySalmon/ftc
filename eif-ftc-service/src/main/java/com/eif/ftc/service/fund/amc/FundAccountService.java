package com.eif.ftc.service.fund.amc;
import com.eif.ftc.dal.model.MemberFundAccount;
import com.eif.ftc.service.bean.FundAccountBean;
import com.eif.ftc.service.bean.AddFundAccountBean;
import com.eif.ftc.service.bean.FundAccountConfirmBean;
import com.eif.ftc.facade.fund.amc.dto.request.FundAccountMemberNoBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Matt on 15/12/18.
 */

public interface FundAccountService {
    Boolean fundAccountForbiddenTransaction(FundAccountMemberNoBean fundAccountMemberNoBean);
    Boolean fundAccountActiveTransaction(FundAccountMemberNoBean fundAccountMemberNoBean);
    FundAccountBean addFundAccount(AddFundAccountBean addFundAccountBean);
    FundAccountBean getFundAccountByMemberNo(String memberNo);
}
