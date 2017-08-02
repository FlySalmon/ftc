package com.eif.ftc.integration.impl.paycore;

import com.eif.framework.common.utils.code.CommonRspCode;
import com.eif.ftc.facade.code.FTCRespCode;
import com.eif.ftc.integration.paycore.PaycoreIntService;
import com.eif.ftc.util.exception.BusinessException;
import com.eif.paycore.facade.InstrumentFacade;
import com.eif.paycore.facade.dto.bean.PaymentInstrumentBean;
import com.eif.paycore.facade.dto.enumeration.GetPaymentInstrumentsFilter;
import com.eif.paycore.facade.dto.enumeration.MemberNoType;
import com.eif.paycore.facade.dto.enumeration.PaymentInstrumentType;
import com.eif.paycore.facade.request.BatchGetPaymentInstrumentsRequest;
import com.eif.paycore.facade.request.GetPaymentInstrumentsRequest;
import com.eif.paycore.facade.response.BatchGetPaymentInstrumentsResponse;
import com.eif.paycore.facade.response.GetPaymentInstrumentsResponse;
import com.eif.transcore.facade.dto.bean.TransactionPaymentOptionBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by bohan on 1/11/16.
 */
@Service("paycoreIntServiceImpl")
public class PaycoreIntServiceImpl implements PaycoreIntService {

    @Resource(name = "instrumentFacade")
    InstrumentFacade instrumentFacade;

    private static Logger logger = LoggerFactory.getLogger(PaycoreIntServiceImpl.class);

    public GetPaymentInstrumentsResponse getPaymentInstruments(String memberNo, int memberNoType) {
        GetPaymentInstrumentsRequest getPaymentInstrumentsRequest = new GetPaymentInstrumentsRequest();
        getPaymentInstrumentsRequest.setMemberNo(memberNo);
        getPaymentInstrumentsRequest.setMemberNoType(memberNoType);
        getPaymentInstrumentsRequest.setFilter(GetPaymentInstrumentsFilter.AVAILABLE_ONLY);
        GetPaymentInstrumentsResponse response = instrumentFacade.getPaymentInstruments(getPaymentInstrumentsRequest);
        if (!response.getRespCode().equals(CommonRspCode.SUCCESS.getCode())) {
            logger.error("query member payment instrument info failed, errmsg: " + response.getMsg());
            throw new BusinessException(response.getMsg(), response.getRespCode());
        }

        return response;
    }

    public PaymentInstrumentBean getDCPPaymentInstrument(String memberNo, int memberNoType) {
        GetPaymentInstrumentsResponse paymentInstrumentsResponse = getPaymentInstruments(memberNo, memberNoType);
        for (PaymentInstrumentBean paymentInstrumentBean : paymentInstrumentsResponse.getPaymentInstruments()) {
            if (paymentInstrumentBean.getPaymentInstrumentType() == PaymentInstrumentType.DCP) {
                return paymentInstrumentBean;
            }

        }
        throw new BusinessException(FTCRespCode.ERR_FUND_PC_GET_DCP.getMessage(), FTCRespCode.ERR_FUND_PC_GET_DCP.getCode());

    }

    public BatchGetPaymentInstrumentsResponse batchGetPaymentInstruments(List<String> memberNoList, int memberNoType, int filter) {
        BatchGetPaymentInstrumentsRequest request = new BatchGetPaymentInstrumentsRequest();
        request.setMemberNoList(memberNoList);
        request.setMemberNoType(MemberNoType.EIF);
        request.setFilter(GetPaymentInstrumentsFilter.AVAILABLE_ONLY);
        BatchGetPaymentInstrumentsResponse resp = instrumentFacade.batchGetPaymentInstruments(request);
        if (!resp.getRespCode().equals(CommonRspCode.SUCCESS.getCode())) {
            logger.error("query member payment instrument info failed, errmsg: " + resp.getMsg());
            throw new BusinessException(resp.getMsg(), resp.getRespCode());
        }

        return resp;
    }

}
