package com.eif.ftc.biz.facade.impl.fund.job;

import com.eif.framework.common.utils.code.CommonRspCode;
import com.eif.framework.common.utils.pkg.page.PageableResponse;
import com.eif.ftc.facade.FundCheckingJobFacade;
import com.eif.ftc.facade.dto.FundClearingOrderBean;
import com.eif.ftc.facade.dto.FundTransOrderBean;
import com.eif.ftc.facade.fund.job.request.GetFundTransOrderListForCheckingRequest;
import com.eif.ftc.facade.fund.job.request.GetFundTransOrdersForCheckingPageableRequest;
import com.eif.ftc.facade.fund.job.request.QueryByFundClearingOrderNosRequest;
import com.eif.ftc.facade.fund.job.response.GetFundTransOrderListForCheckingResponse;
import com.eif.ftc.facade.fund.job.response.QueryByFundClearingOrderNosResponse;
import com.eif.ftc.facade.fund.oper.request.FundClearingOrderRequest;
import com.eif.ftc.service.trans.FundQueryService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by bohan on 2/15/16.
 */
public class FundCheckingJobFacadeImpl implements FundCheckingJobFacade {

    @Autowired
    FundQueryService fundQueryService;

    @Override
    public PageableResponse<FundTransOrderBean> getFundTransOrdersForCheckingByPage(GetFundTransOrdersForCheckingPageableRequest request) {
       PageableResponse<FundTransOrderBean> response = fundQueryService.getFundTransOrdersForCheckingByPage(request);
       response.setRespCode(CommonRspCode.SUCCESS.getCode());
       response.setMsg(CommonRspCode.SUCCESS.getMessage());

        return response;
    }

    @Override
    public GetFundTransOrderListForCheckingResponse getFundTransOrderListForChecking(GetFundTransOrderListForCheckingRequest request) {
        GetFundTransOrderListForCheckingResponse response = new GetFundTransOrderListForCheckingResponse();
        response.setFundTransOrderList(fundQueryService.getFundTransOrderListForChecking(request));
        response.setRespCode(CommonRspCode.SUCCESS.getCode());
        response.setMsg(CommonRspCode.SUCCESS.getMessage());

        return response;
    }

    @Override
    public PageableResponse<FundTransOrderBean> getSucceedFundTransOrdersForCheckingByPage(GetFundTransOrdersForCheckingPageableRequest request) {
        PageableResponse<FundTransOrderBean> response = fundQueryService.getSucceedFundTransOrdersForCheckingByPage(request);
        response.setRespCode(CommonRspCode.SUCCESS.getCode());
        response.setMsg(CommonRspCode.SUCCESS.getMessage());

        return response;
    }

	@Override
	public QueryByFundClearingOrderNosResponse getFundClearingOrderByOrderNosForChecking(
			QueryByFundClearingOrderNosRequest request) {
		QueryByFundClearingOrderNosResponse response = new QueryByFundClearingOrderNosResponse();
        response.setFundClearingOrderBeanList(
        		fundQueryService.getByFundClearingOrderNos(
        				request.getFundClearingOrderNoList(),
        				true)
        		);
        response.setRespCode(CommonRspCode.SUCCESS.getCode());
        response.setMsg(CommonRspCode.SUCCESS.getMessage());

        return response;
	}

	@Override
	public PageableResponse<FundClearingOrderBean> getFundClearingOrdersByPageForChecking(
			FundClearingOrderRequest request) {
		PageableResponse<FundClearingOrderBean> response = fundQueryService.getFundClearingOrdersByPage(request, true);
        response.setRespCode(CommonRspCode.SUCCESS.getCode());
        response.setMsg(CommonRspCode.SUCCESS.getMessage());

        return response;
	}

}
