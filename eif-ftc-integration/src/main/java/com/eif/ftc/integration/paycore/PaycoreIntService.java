package com.eif.ftc.integration.paycore;

import com.eif.paycore.facade.dto.bean.PaymentInstrumentBean;
import com.eif.paycore.facade.response.GetPaymentInstrumentsResponse;

/**
 * Created by bohan on 1/11/16.
 */
public interface PaycoreIntService {
    GetPaymentInstrumentsResponse getPaymentInstruments(String memberNo, int memberNoType);
    PaymentInstrumentBean getDCPPaymentInstrument(String memberNo, int memberNoType);
}
