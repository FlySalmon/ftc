package com.eif.ftc.service.test.prepare;

import com.eif.fis.facade.dto.common.TagRules;
import com.eif.framework.common.utils.code.CommonRspCode;
import com.eif.ftc.dal.model.FundTransOrder;
import com.eif.ftc.integration.ofc.OfcIntService;
import com.eif.ftc.integration.risk.RiskIntService;
import com.eif.ofc.facade.dto.bean.CreateBusinessOrderItemResult;
import com.eif.ofc.facade.response.CreateOrderItemResponse;
import com.eif.risk.facade.bean.AllUserLockBean;
import com.eif.risk.facade.constant.CheckPointList;
import com.eif.risk.facade.constant.RiskMemberStatus;
import com.eif.risk.facade.response.QueryDayStatByProdResp;
import com.eif.risk.facade.response.QueryDayStatResp;
import com.eif.risk.facade.response.RiskCheckResp;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by bohan on 10/7/16.
 */
public class RiskDataPrepare {

    public static QueryDayStatByProdResp buildBaseQueryDayStatByProd() {
        QueryDayStatByProdResp resp = new QueryDayStatByProdResp();
        resp.setRespCode(CommonRspCode.SUCCESS.getCode());
        resp.setMsg(CommonRspCode.SUCCESS.getMessage());
        resp.setDayCount(0);
        resp.setDayAmount(BigDecimal.ZERO);

        return resp;
    }

    public static QueryDayStatResp buildBaseQueryDayAmount() {
        QueryDayStatResp resp = new QueryDayStatResp();
        resp.setRespCode(CommonRspCode.SUCCESS.getCode());
        resp.setMsg(CommonRspCode.SUCCESS.getMessage());
        resp.setDayAmount(BigDecimal.ZERO);
        resp.setDayCount(0);
        resp.setTagTotalAmount(BigDecimal.ZERO);
        resp.setTagTotalCount(0);
        resp.setTotalAmount(BigDecimal.ZERO);
        resp.setTotalCount(0);
        return resp;
    }

    public static void buildRiskCheckTransIngForSuccess(RiskIntService riskIntService) {
        Mockito.doNothing().when(riskIntService).riskCheckTransIng(Mockito.any(FundTransOrder.class), Mockito.anyString(),Mockito.anyString(),Mockito.anyString(),Mockito.anyString());
    }

    public static void buildQueryDayAmountForSuccess(RiskIntService riskIntService) {
        QueryDayStatResp resp = new QueryDayStatResp();
        resp.setRespCode(CommonRspCode.SUCCESS.getCode());
        resp.setMsg(CommonRspCode.SUCCESS.getMessage());
        resp.setDayAmount(BigDecimal.ZERO);
        resp.setDayCount(0);
        resp.setTagTotalAmount(BigDecimal.ZERO);
        resp.setTagTotalCount(0);
        resp.setTotalAmount(BigDecimal.ZERO);
        resp.setTotalCount(0);

        Mockito.doReturn(resp).when(riskIntService).queryDayAmount(Mockito.anyLong(), Mockito.anyString(), Mockito.anyInt(), Mockito.any(java.util.Date.class), Mockito.any(TagRules.class));
    }

    public static void buildQueryTodayStatByProdForSuccess(RiskIntService riskIntService) {
        QueryDayStatByProdResp resp = new QueryDayStatByProdResp();
        resp.setRespCode(CommonRspCode.SUCCESS.getCode());
        resp.setMsg(CommonRspCode.SUCCESS.getMessage());
        resp.setDayCount(0);
        resp.setDayAmount(BigDecimal.ZERO);
        Mockito.doReturn(resp).when(riskIntService).queryTodayStatByProd(Mockito.any(FundTransOrder.class));
        Mockito.doReturn(resp).when(riskIntService).queryTodayStatByProd(Mockito.anyInt(), Mockito.anyLong());
    }

    public static void buildAddDayStatForSuccess(RiskIntService riskIntService) {
        Mockito.doNothing().when(riskIntService).addDayStat(Mockito.any(FundTransOrder.class), Mockito.any(TagRules.class));
    }

    public static void buildDeductDayStatForSuccess(RiskIntService riskIntService) {
        Mockito.doNothing().when(riskIntService).deductDayStat(Mockito.any(FundTransOrder.class), Mockito.any(TagRules.class));
    }

    public static void buildGetRiskUserLockForSuccess(RiskIntService riskIntService) {
        AllUserLockBean allUserLockBean = new AllUserLockBean();
        allUserLockBean.setTransLock(RiskMemberStatus.PERMITTED);
        Mockito.doReturn(allUserLockBean).when(riskIntService).getRiskUserLock(Mockito.anyString());
    }



    public static void buildGetRiskUserLock(RiskIntService riskIntService)
    {
        AllUserLockBean bean = new AllUserLockBean();
        bean.setTransLock(RiskMemberStatus.PERMITTED);
        Mockito.doReturn(bean).when(riskIntService).getRiskUserLock(Mockito.anyString());
    }

    public static void buildDeductDayStat(RiskIntService riskIntService) {
        Mockito.doNothing().when(riskIntService).deductDayStat(Mockito.any(FundTransOrder.class), Mockito.any(TagRules.class));
    }

}
