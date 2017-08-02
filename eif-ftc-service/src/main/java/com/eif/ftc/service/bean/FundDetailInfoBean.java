package com.eif.ftc.service.bean;

import com.eif.framework.common.utils.pkg.BaseDTO;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by Matt on 16/1/6.
 */
public class FundDetailInfoBean {

    private static final long serialVersionUID = -4002338377251644780L;

    private BigDecimal totalAmount;

    private Date createDate;

    /**
     * @return 总金额
     * @occurs required
     */
    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }

    /**
     * @return 创建日期
     * @occurs required
     */
    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }
}