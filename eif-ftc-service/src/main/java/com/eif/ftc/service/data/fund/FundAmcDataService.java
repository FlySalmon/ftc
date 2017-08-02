package com.eif.ftc.service.data.fund;

import com.eif.fis.facade.dto.ftc.ProdTaTransInfo;
import com.eif.ftc.dal.model.FundTransOrder;
import com.eif.ftc.service.bean.FundAccountBean;
import com.eif.ftc.service.bean.FundCutDifferenceBean;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by bohan on 1/12/16.
 */
public interface FundAmcDataService { //amc 方法简便封装, 同时可封装小事务

    void redeemAsset(Date curDate, String transOrderNo, String memberNo, Long productID,
                             BigDecimal transAmount,Integer closeType);
    void processAssetAdd(FundTransOrder targetOrder,BigDecimal exceptedProfitAmount,Boolean isGroupOnType,int closeType,ProdTaTransInfo prodTaTransInfo);

}
