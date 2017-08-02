package com.eif.ftc.facade.fund.amc.dto.response;

import com.eif.framework.common.utils.pkg.BaseDTO;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by Matt on 16/5/3.
 */
public class OfflineAssetInfo extends BaseDTO {

    private static final long serialVersionUID = -4312275854756933441L;

    private String fundOfflineDetailUuid;

    private BigDecimal fundTotalAmount;

    private BigDecimal totalProfit;

    private Date createTime;

    private Date updateTime;

    private BigDecimal bonusTotalAmount;

    private BigDecimal profitTotalAmount;

    private Date settlementTime;

    private Integer hasSettlement;

    private BigDecimal settlementCapital;

    private String customerPhone;

    private String customerName;

    private String customerCardno;

    private String productName;

    private Date inceptionDate;

    private Date dueDate;

    private Integer offlineMark;

    private String memberNo;

    private Long productId;

    /**
     * @return 会员号
     * @occurs required
     */
    public String getMemberNo() {
        return memberNo;
    }

    public void setMemberNo(String memberNo) {
        this.memberNo = memberNo;
    }


    /**
     * @return 产品Id
     * @occurs required
     */
    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }


    /**
     * @return 线下资产编号
     * @occurs required
     */
    public String getFundOfflineDetailUuid() {
        return fundOfflineDetailUuid;
    }

    public void setFundOfflineDetailUuid(String fundOfflineDetailUuid) {
        this.fundOfflineDetailUuid = fundOfflineDetailUuid;
    }

    /**
     * @return 线下资产总金额
     * @occurs required
     */
    public BigDecimal getFundTotalAmount() {
        return fundTotalAmount;
    }

    public void setFundTotalAmount(BigDecimal fundTotalAmount) {
        this.fundTotalAmount = fundTotalAmount;
    }


    /**
     * @return 线下资产收益
     * @occurs required
     */
    public BigDecimal getTotalProfit() {
        return totalProfit;
    }

    public void setTotalProfit(BigDecimal totalProfit) {
        this.totalProfit = totalProfit;
    }


    /**
     * @return 创建时间
     * @occurs required
     */
    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }


    /**
     * @return 更新时间
     * @occurs required
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }


    /**
     * @return 总红利收益
     * @occurs required
     */
    public BigDecimal getBonusTotalAmount() {
        return bonusTotalAmount;
    }

    public void setBonusTotalAmount(BigDecimal bonusTotalAmount) {
        this.bonusTotalAmount = bonusTotalAmount;
    }


    /**
     * @return 加息收益
     * @occurs required
     */
    public BigDecimal getProfitTotalAmount() {
        return profitTotalAmount;
    }

    public void setProfitTotalAmount(BigDecimal profitTotalAmount) {
        this.profitTotalAmount = profitTotalAmount;
    }


    /**
     * @return 清盘时间
     * @occurs required
     */
    public Date getSettlementTime() {
        return settlementTime;
    }

    public void setSettlementTime(Date settlementTime) {
        this.settlementTime = settlementTime;
    }


    /**
     * @return 是否清盘
     * @occurs required
     */
    public Integer getHasSettlement() {
        return hasSettlement;
    }

    public void setHasSettlement(Integer hasSettlement) {
        this.hasSettlement = hasSettlement;
    }

    /**
     * @return 投资本金
     * @occurs required
     */
    public BigDecimal getSettlementCapital() {
        return settlementCapital;
    }

    public void setSettlementCapital(BigDecimal settlementCapital) {
        this.settlementCapital = settlementCapital;
    }


    /**
     * @return 客户手机号
     * @occurs required
     */
    public String getCustomerPhone() {
        return customerPhone;
    }

    public void setCustomerPhone(String customerPhone) {
        this.customerPhone = customerPhone;
    }


    /**
     * @return 客户名称
     * @occurs required
     */
    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }


    /**
     * @return 客户卡号
     * @occurs required
     */
    public String getCustomerCardno() {
        return customerCardno;
    }

    public void setCustomerCardno(String customerCardno) {
        this.customerCardno = customerCardno;
    }


    /**
     * @return 产品名称
     * @occurs required
     */
    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }


    /**
     * @return 到期时间
     * @occurs required
     */
    public Date getInceptionDate() {
        return inceptionDate;
    }

    public void setInceptionDate(Date inceptionDate) {
        this.inceptionDate = inceptionDate;
    }


    /**
     * @return 成立时间
     * @occurs required
     */
    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }


    /**
     * @return 线下备注
     * @occurs required
     */
    public Integer getOfflineMark() {
        return offlineMark;
    }

    public void setOfflineMark(Integer offlineMark) {
        this.offlineMark = offlineMark;
    }
}
