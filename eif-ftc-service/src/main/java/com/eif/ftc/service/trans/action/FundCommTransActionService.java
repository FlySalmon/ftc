package com.eif.ftc.service.trans.action;

import com.eif.fis.facade.response.ftc.QueryProdTransInfoV2Resp;
import com.eif.ftc.dal.model.FundTransOrder;
import com.eif.ftc.service.bean.FundAccountBean;
import com.eif.member.facade.pkg.response.QueryMemberInfoResponse;

import java.util.Date;

/**
 * Created by bohan on 9/26/16.
 */
public interface FundCommTransActionService {

    FundAccountBean checkAndBuildAmcAccount(FundTransOrder transOrder, QueryMemberInfoResponse memberInfoResponse);

    void buildForBusinessOrderItem(FundTransOrder contextFundTransOrder, String productName, String marketField,Integer closeType);

    void doCreateFundTransTransaction(FundTransOrder contextFundTransOrder, Date curDate, boolean isMemberExist, String outerSysNo, String outerOrderNo);
}
