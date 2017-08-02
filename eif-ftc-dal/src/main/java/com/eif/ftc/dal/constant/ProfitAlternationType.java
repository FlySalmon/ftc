package com.eif.ftc.dal.constant;

/**
 * Created by Matt on 2016/10/19.
 */
public enum ProfitAlternationType {
    //营销贴息类型
    COUPON(0),			//加息券
    GROUPON(1), 			//团购贴息
    PACKCTOP(2),		//活包定贴息
    COTOP(3),        //活期封闭期贴息（不参与展示与计算）
    COTOPGET(4);     //活期封闭期兑付贴息

    private Integer value;

    private ProfitAlternationType(Integer value) {
        this.value = value;
    }

    public Integer getValue() {
        return this.value;
    }
}
