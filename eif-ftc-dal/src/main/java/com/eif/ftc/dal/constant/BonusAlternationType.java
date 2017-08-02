package com.eif.ftc.dal.constant;

/**
 * Created by Matt on 2016/10/19.
 */
public enum BonusAlternationType {
    //红利类型
    OPEN(0),			//活期
    HALF_OPEN(1), 		//活包定（不参与展示与计算）
    CLOSE(2),		//定期
    CLOSE_OPEN(3);  //活期封闭期（不参与展示与计算）


    private Integer value;

    private BonusAlternationType(Integer value) {
        this.value = value;
    }

    public Integer getValue() {
        return this.value;
    }
}
