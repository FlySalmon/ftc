package com.eif.ftc.service.bean;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by Matt on 15/12/18.
 */
public class FundSubscriptionConfirmBean {

    /**
     *  该只基金的份额,所属表字段为t_amc_fund_detail_alteration.fund_share
     */
    private BigDecimal fundShare;

    /**
     *  金融交易系统单号,所属表字段为t_amc_fund_detail_alteration.ftc_order_no
     */
    private String ftcOrderNo;

    /**
     *  FTC单号创建时间,所属表字段为t_amc_fund_detail_alteration.ftc_order_create_time
     */
    private Date ftcOrderCreateTime;

    /**
     * 团购奖励金额
     */
    private BigDecimal grouponBonus;
    

    /**
     *  单据状态,所属表字段为t_amc_fund_detail_alteration.alteration_status
     */
    private Integer alterationStatus;

    public BigDecimal getFundShare() {
        return fundShare;
    }

    /**
     * @param fundShare 认购份额
     * @occurs required
     */
    public void setFundShare(BigDecimal fundShare) {
        this.fundShare = fundShare;
    }

    public String getFtcOrderNo() {
        return ftcOrderNo;
    }

    /**
     * @param ftcOrderNo 认购单号(原始认购单号)
     * @occurs required
     */
    public void setFtcOrderNo(String ftcOrderNo) {
        this.ftcOrderNo = ftcOrderNo;
    }

    public Date getFtcOrderCreateTime() {
        return ftcOrderCreateTime;
    }

    /**
     * @param ftcOrderCreateTime ftc认购单创建时间
     * @occurs required
     */
    public void setFtcOrderCreateTime(Date ftcOrderCreateTime) {
        this.ftcOrderCreateTime = ftcOrderCreateTime;
    }

    public Integer getAlterationStatus() {
        return alterationStatus;
    }

    /**
     * @param alterationStatus 确认状态 1-TA未确认,2-TA确认,3-TA取消
     * @occurs required
     */
    public void setAlterationStatus(Integer alterationStatus) {
        this.alterationStatus = alterationStatus;
    }

	public BigDecimal getGrouponBonus() {
		return grouponBonus;
	}

	public void setGrouponBonus(BigDecimal grouponBonus) {
		this.grouponBonus = grouponBonus;
	}
}
