package com.eif.ftc.service.trans;

import com.eif.ftc.dal.model.FundTransOrder;
import com.eif.transcore.facade.mq.MQTransInfoBean;

/**
 * Created by bohan on 9/21/16.
 */
public interface FundChargeService {

    void chargeSuccess(MQTransInfoBean message, FundTransOrder targetOrder);
    void chargeFailed(MQTransInfoBean message, FundTransOrder targetOrder);

}

