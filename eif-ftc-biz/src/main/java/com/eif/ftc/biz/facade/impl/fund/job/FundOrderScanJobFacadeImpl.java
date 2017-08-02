package com.eif.ftc.biz.facade.impl.fund.job;

import com.eif.framework.common.utils.code.CommonRspCode;
import com.eif.framework.common.utils.pkg.BaseResponse;
import com.eif.ftc.facade.FundOrderScanJobFacade;
import com.eif.ftc.service.trans.FundOrderExpiredService;
import com.eif.ftc.service.transfer.FundTransferOrderExpiredService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by bohan on 1/5/16.
 */
public class FundOrderScanJobFacadeImpl implements FundOrderScanJobFacade {

    @Autowired
    FundOrderExpiredService fundOrderExpiredService;

    @Autowired
    FundTransferOrderExpiredService fundTransferOrderExpiredService;

    @Override
    public BaseResponse orderExpiredScan() {
        BaseResponse response = new BaseResponse();
        fundOrderExpiredService.orderExpiredScan();
        response.setMsg(CommonRspCode.SUCCESS.getMessage());
        response.setRespCode(CommonRspCode.SUCCESS.getCode());

        return response;
    }

	@Override
	public BaseResponse transferOrderExpiredScan() {
		fundTransferOrderExpiredService.orderExpiredScan();

        BaseResponse response = new BaseResponse();
        response.setMsg(CommonRspCode.SUCCESS.getMessage());
        response.setRespCode(CommonRspCode.SUCCESS.getCode());
		return response;
	}
	
}
