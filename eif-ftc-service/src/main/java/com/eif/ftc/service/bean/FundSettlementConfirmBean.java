package com.eif.ftc.service.bean;

import com.eif.framework.common.utils.pkg.BaseDTO;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Created by Matt on 15/12/25.
 */
public class FundSettlementConfirmBean {


    private Long productId;

    private String taAccount;

    private String transactionAccount;

    private BigDecimal fundShare;

    private BigDecimal fundAmount;

    private BigDecimal fundProfit;

    public Long getProductId() {
        return productId;
    }

    /**
     * @param productId 产品ID
     * @occurs required
     */
    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public String getTaAccount() {
        return taAccount;
    }

    /**
     * @param taAccount 投资人TA账号
     * @occurs required
     */
    public void setTaAccount(String taAccount) {
        this.taAccount = taAccount;
    }

    public String getTransactionAccount() {
        return transactionAccount;
    }

    /**
     * @param transactionAccount 交易平台交易账号
     * @occurs required
     */
    public void setTransactionAccount(String transactionAccount) {
        this.transactionAccount = transactionAccount;
    }

    public BigDecimal getFundShare() {
        return fundShare;
    }

    /**
     * @param fundShare 清盘份额
     * @occurs required
     */
    public void setFundShare(BigDecimal fundShare) {
        this.fundShare = fundShare;
    }

    public BigDecimal getFundAmount() {
        return fundAmount;
    }

    /**
     * @param fundAmount 清盘金额
     * @occurs required
     */
    public void setFundAmount(BigDecimal fundAmount) {
        this.fundAmount = fundAmount;
    }

    public BigDecimal getFundProfit() {
        return fundProfit;
    }

    /**
     * @param fundProfit 加息券总收益
     * @occurs optional
     */
    public void setFundProfit(BigDecimal fundProfit) {
        this.fundProfit = fundProfit;
    }
}
