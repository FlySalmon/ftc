package com.eif.ftc.service.fund.amc;


import com.eif.fis.facade.dto.ftc.ProdTaTransInfo;
import com.eif.ftc.service.bean.*;

import javax.annotation.ParametersAreNonnullByDefault;
import java.util.ArrayList;

/**
 * Created by Matt on 15/12/21.
 */
public interface FundDetailAlterationService {
    Boolean addFundDetailAlterationBySubscriptionRecord(@ParametersAreNonnullByDefault FundSubscriptionBean fundSubscriptionBean, Boolean isGroupOnType, ProdTaTransInfo prodTaTransInfo,Integer closeType);
    Boolean addFundDetailAlterationByPurchaseRecord(@ParametersAreNonnullByDefault FundPurchaseBean fundPurchaseBean,Boolean isGroupOnType,Integer closeType,ProdTaTransInfo prodTaTransInfo);
    Boolean addFundDetailAlterationByRedemptionRecord(@ParametersAreNonnullByDefault FundRedemptionBean fundRedemptionBean,Integer closeType);
}
