package com.eif.ftc.facade.fund.amc.dto.response;

import com.eif.framework.common.utils.pkg.BaseDTO;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by Matt on 15/12/29.
 */
public class BonusBean extends BaseDTO {

    private static final long serialVersionUID = -4917718424471329562L;

    private Long productId;

    private Date bonusDate;

    private BigDecimal bonusAmount;

    private BigDecimal profitAmount;

    /**
     * @return 产品号
     * @occurs required
     */
    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    /**
     * @return 红利日期
     * @occurs required
     */
    public Date getBonusDate() {
        return bonusDate;
    }

    public void setBonusDate(Date bonusDate) {
        this.bonusDate = bonusDate;
    }

    /**
     * @return 红利金额
     * @occurs required
     */
    public BigDecimal getBonusAmount() {
        return bonusAmount;
    }

    public void setBonusAmount(BigDecimal bonusAmount) {
        this.bonusAmount = bonusAmount;
    }


    /**
     * @return 贴息金额
     * @occurs optional
     */
    public BigDecimal getProfitAmount() {
        return profitAmount;
    }

    public void setProfitAmount(BigDecimal profitAmount) {
        this.profitAmount = profitAmount;
    }
}
