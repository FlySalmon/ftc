package com.eif.ftc.facade.fund.amc.request;

import com.eif.framework.common.utils.pkg.BaseRequest;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by Matt on 16/5/3.
 */
public class AddOfflineAssetRequest extends BaseRequest {

    private static final long serialVersionUID = 1850588485413544584L;

    private String memberNo;

    private Long productId;

    private BigDecimal settlementCapital;

    private BigDecimal totalProfit;

    private BigDecimal bonusAmount;

    private BigDecimal profitAmount;

    private String customerPhone;

    private String customerName;

    private String customerCardNo;

    private String productName;

    private String offlineCode;

    private Date inceptionDate;

    private Date dueDate;

    public String getMemberNo() {
        return memberNo;
    }


    /**
     * @param memberNo 会员号
     * @occurs required
     */
    public void setMemberNo(String memberNo) {
        this.memberNo = memberNo;
    }

    public Long getProductId() {
        return productId;
    }


    /**
     * @param productId 用户产品号
     * @occurs required
     */
    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public BigDecimal getSettlementCapital() {
        return settlementCapital;
    }


    /**
     * @param settlementCapital 投资本金
     * @occurs required
     */
    public void setSettlementCapital(BigDecimal settlementCapital) {
        this.settlementCapital = settlementCapital;
    }

    public BigDecimal getTotalProfit() {
        return totalProfit;
    }

    /**
     * @param totalProfit 总收益
     * @occurs required
     */
    public void setTotalProfit(BigDecimal totalProfit) {
        this.totalProfit = totalProfit;
    }

    public BigDecimal getBonusAmount() {
        return bonusAmount;
    }


    /**
     * @param bonusAmount 预期收益
     * @occurs required
     */
    public void setBonusAmount(BigDecimal bonusAmount) {
        this.bonusAmount = bonusAmount;
    }

    public BigDecimal getProfitAmount() {
        return profitAmount;
    }


    /**
     * @param profitAmount 加息收益
     * @occurs required
     */
    public void setProfitAmount(BigDecimal profitAmount) {
        this.profitAmount = profitAmount;
    }

    public String getOfflineCode() {
        return offlineCode;
    }


    /**
     * @param offlineCode 幂等键
     * @occurs required
     */
    public void setOfflineCode(String offlineCode) {
        this.offlineCode = offlineCode;
    }

    public String getCustomerPhone() {
        return customerPhone;
    }


    /**
     * @param customerPhone 客户手机号
     * @occurs required
     */
    public void setCustomerPhone(String customerPhone) {
        this.customerPhone = customerPhone;
    }

    public String getCustomerName() {
        return customerName;
    }


    /**
     * @param customerName 客户姓名
     * @occurs required
     */
    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerCardNo() {
        return customerCardNo;
    }

    /**
     * @param customerCardNo 银行卡号
     * @occurs required
     */
    public void setCustomerCardNo(String customerCardNo) {
        this.customerCardNo = customerCardNo;
    }

    public String getProductName() {
        return productName;
    }


    /**
     * @param productName 产品名称
     * @occurs required
     */
    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Date getInceptionDate() {
        return inceptionDate;
    }


    /**
     * @param inceptionDate  成立时间
     * @occurs required
     */
    public void setInceptionDate(Date inceptionDate) {
        this.inceptionDate = inceptionDate;
    }



    public Date getDueDate() {
        return dueDate;
    }



    /**
     * @param dueDate 成立时间
     * @occurs required
     */
    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }
}
