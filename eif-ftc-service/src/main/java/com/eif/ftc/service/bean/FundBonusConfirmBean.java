package com.eif.ftc.service.bean;

import com.eif.framework.common.utils.pkg.BaseDTO;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by Matt on 15/12/18.
 */
public class FundBonusConfirmBean {

    private Long productId;

    private String taAccountId;

    private String transactionAccount;

    private BigDecimal fundBonusAmount;

    private BigDecimal bonusSwitchShare;

    private Integer bonusDelieverType;

    private String ftcOrderNo;

    private Date delieveryDate;


    /**
     *  FTC单号创建时间,所属表字段为t_amc_fund_detail_alteration.ftc_order_create_time
     */
    private Date ftcOrderCreateTime;



    public Long getProductId() {
        return productId;
    }

    /**
     * @param productId 产品id
     * @occurs required
     */
    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public BigDecimal getFundBonusAmount() {
        return fundBonusAmount;
    }

    /**
     * @param fundBonusAmount 红利金额
     * @occurs required
     */
    public void setFundBonusAmount(BigDecimal fundBonusAmount) {
        this.fundBonusAmount = fundBonusAmount;
    }

    public String getFtcOrderNo() {
        return ftcOrderNo;
    }

    /**
     * @param ftcOrderNo 红利单号
     * @occurs required
     */
    public void setFtcOrderNo(String ftcOrderNo) {
        this.ftcOrderNo = ftcOrderNo;
    }

    public Date getFtcOrderCreateTime() {
        return ftcOrderCreateTime;
    }
    /**
     * @param ftcOrderCreateTime 红利单创建时间
     * @occurs required
     */
    public void setFtcOrderCreateTime(Date ftcOrderCreateTime) {
        this.ftcOrderCreateTime = ftcOrderCreateTime;
    }


    public String getTaAccountId() {
        return taAccountId;
    }

    /**
     * @param taAccountId TA账户号
     * @occurs required
     */
    public void setTaAccountId(String taAccountId) {
        this.taAccountId = taAccountId;
    }

    public String getTransactionAccount() {
        return transactionAccount;
    }


    /**
     * @param transactionAccount 交易平台账号
     * @occurs required
     */
    public void setTransactionAccount(String transactionAccount) {
        this.transactionAccount = transactionAccount;
    }

    public BigDecimal getBonusSwitchShare() {
        return bonusSwitchShare;
    }


    /**
     * @param bonusSwitchShare 如果转投,转投份额
     * @occurs required
     */
    public void setBonusSwitchShare(BigDecimal bonusSwitchShare) {
        this.bonusSwitchShare = bonusSwitchShare;
    }

    public Integer getBonusDelieverType() {
        return bonusDelieverType;
    }

    /**
     * @param bonusDelieverType 发放方式 1-转投,2-现金
     * @occurs required
     */
    public void setBonusDelieverType(Integer bonusDelieverType) {
        this.bonusDelieverType = bonusDelieverType;
    }


    public Date getDelieveryDate() {
        return delieveryDate;
    }


    /**
     * @param delieveryDate 红利发放时间
     * @occurs required
     */
    public void setDelieveryDate(Date delieveryDate) {
        this.delieveryDate = delieveryDate;
    }
}
