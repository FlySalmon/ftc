package com.eif.ftc.biz.facade.impl.fund.transfer;

import org.springframework.beans.factory.annotation.Autowired;

import com.eif.framework.common.utils.code.CommonRspCode;
import com.eif.ftc.facade.FundTransferFacade;
import com.eif.ftc.facade.fund.transfer.request.*;
import com.eif.ftc.facade.fund.transfer.response.*;
import com.eif.ftc.service.transfer.FundTransfereeService;
import com.eif.ftc.service.transfer.FundTransferorService;
import com.eif.ftc.util.exception.BusinessException;

public class FundTransferFacadeImpl implements FundTransferFacade {

	@Autowired
    private FundTransferorService fundTransferorService;

    @Autowired
    private FundTransfereeService fundTransfereeService;

	@Override
	public CheckAssetTransactionRuleResponse checkAssetTransactionRule(CheckAssetTransactionRuleRequest request) {
		CheckAssetTransactionRuleResponse response = new CheckAssetTransactionRuleResponse();
		try {
			fundTransferorService.checkAssetTransactionRule(request);

			response.setRespCode(CommonRspCode.SUCCESS.getCode());
			response.setMsg(CommonRspCode.SUCCESS.getMessage());
		} catch (BusinessException be) {
			response.setRespCode(be.getCode());
			response.setMsg(be.getMessage());
		}
		
		return response;
	}

	@Override
	public CalculateTransferPriceAndFeeResponse calculateTransferPriceAndFee(
			CalculateTransferPriceAndFeeRequest request) {
		CalculateTransferPriceAndFeeResponse response = new CalculateTransferPriceAndFeeResponse();
		fundTransferorService.calculateTransferPriceAndFee(request, response);
		
		response.setRespCode(CommonRspCode.SUCCESS.getCode());
		response.setMsg(CommonRspCode.SUCCESS.getMessage());
		return response;
	}

	@Override
	public CreateTransferorOrderResponse createTransferorOrder(CreateTransferorOrderRequest request) {
		CreateTransferorOrderResponse response = new CreateTransferorOrderResponse();
		fundTransferorService.createTransferorOrder(request, response);
		
		response.setRespCode(CommonRspCode.SUCCESS.getCode());
		response.setMsg(CommonRspCode.SUCCESS.getMessage());
		return response;
	}

	@Override
	public CancelTransferorOrderResponse cancelTransferorOrder(CancelTransferorOrderRequest request) {
		CancelTransferorOrderResponse response = new CancelTransferorOrderResponse();
		try {
			fundTransferorService.cancelTransferorOrder(request);

			response.setRespCode(CommonRspCode.SUCCESS.getCode());
			response.setMsg(CommonRspCode.SUCCESS.getMessage());
		} catch (BusinessException be) {
			response.setRespCode(be.getCode());
			response.setMsg(be.getMessage());
		}
		
		return response;
	}

	@Override
	public CreateTransfereeOrderResponse createTransfereeOrder(CreateTransfereeOrderRequest request) {
		CreateTransfereeOrderResponse response = new CreateTransfereeOrderResponse();
		fundTransfereeService.createTransfereeOrder(request, response);
		
		response.setRespCode(CommonRspCode.SUCCESS.getCode());
		response.setMsg(CommonRspCode.SUCCESS.getMessage());
		return response;
	}

	@Override
	public PayTransfereeOrderResponse payTransfereeOrder(PayTransfereeOrderRequest request) {
		PayTransfereeOrderResponse response = new PayTransfereeOrderResponse();
		fundTransfereeService.payTransfereeOrder(request, response);
		
		response.setRespCode(CommonRspCode.SUCCESS.getCode());
		response.setMsg(CommonRspCode.SUCCESS.getMessage());
		return response;
	}

	@Override
	public ResumePayTransfereeOrderResponse resumePayTransfereeOrder(ResumePayTransfereeOrderRequest request) {
		ResumePayTransfereeOrderResponse response = new ResumePayTransfereeOrderResponse();
		fundTransfereeService.resumePayTransfereeOrder(request, response);
		
		response.setRespCode(CommonRspCode.SUCCESS.getCode());
		response.setMsg(CommonRspCode.SUCCESS.getMessage());
		return response;
	}

}
