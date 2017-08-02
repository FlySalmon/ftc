package com.eif.ftc.integration.fis.bean;

import java.util.Date;

/**
 * Created by Matt on 15/12/21.
 */
public class QueryProductNetWorthBean {
    private Long productNo;
    private Date valueDate;

    public Long getProductNo() {
        return productNo;
    }

    public void setProductNo(Long productNo) {
        this.productNo = productNo;
    }

    public Date getValueDate() {
        return valueDate;
    }

    public void setValueDate(Date valueDate) {
        this.valueDate = valueDate;
    }
}
