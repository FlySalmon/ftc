package com.eif.ftc.facade.fund.amc.constant;

/**
 * Created by Matt on 2016/12/13.
 */
public enum ExchangeType {

    //二级市场交易类型
    NORMAL(Byte.valueOf("0")),            //持有中
    EXCHANGING(Byte.valueOf("1")),       //转让中
    EXCHANGED(Byte.valueOf("2"));       //交易完成


    private Byte value;

    private ExchangeType(Byte value) {
        this.value = value;
    }

    public Byte getValue() {
        return this.value;
    }
}