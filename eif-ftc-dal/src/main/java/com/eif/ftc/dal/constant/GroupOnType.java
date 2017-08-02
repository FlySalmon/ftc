package com.eif.ftc.dal.constant;

/**
 * Created by Matt on 2016/11/11.
 */
public enum GroupOnType {

    //团购产品类型
    NOTGROUPON(Byte.valueOf("0")),            //非团购产品
    GROUPON(Byte.valueOf("1"));        //团购产品


    private Byte value;

    private GroupOnType(Byte value) {
        this.value = value;
    }

    public Byte getValue() {
        return this.value;
    }
}
