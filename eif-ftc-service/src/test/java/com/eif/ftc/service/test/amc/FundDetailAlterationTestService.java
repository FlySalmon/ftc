package com.eif.ftc.service.test.amc;


import com.eif.ftc.service.bean.*;

import javax.annotation.ParametersAreNonnullByDefault;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Matt on 15/12/21.
 */
public interface FundDetailAlterationTestService {
    Integer updateFundByPurchaseConfirmList(List<FundPurchaseConfirmBean> fundPurchaseCfmBeanList);
}
