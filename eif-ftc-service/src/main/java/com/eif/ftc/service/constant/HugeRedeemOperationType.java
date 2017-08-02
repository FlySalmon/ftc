package com.eif.ftc.service.constant;

/**
 * Created by Matt on 16/2/15.
 */
public enum HugeRedeemOperationType {

    REDEEM_CONTINUE(1),			//继续赎回
    REFUSE_REDEEM(2), 			//拒绝赎回
    SUSPEND_BY_HUGE_REDEEM(3), 			//大额挂起
    HUGE_REDEEM_AMOUNT_LIMIT(4); 	//赎回限额

    private int value;

    private HugeRedeemOperationType(int value) {
        this.value = value;
    }

    public int getValue() {
        return this.value;
    }

}
