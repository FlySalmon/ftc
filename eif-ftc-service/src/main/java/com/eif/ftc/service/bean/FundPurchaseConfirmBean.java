package com.eif.ftc.service.bean;

import com.eif.framework.common.utils.pkg.BaseDTO;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by Matt on 15/12/18.
 */
public class FundPurchaseConfirmBean {

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
     *  单据状态,所属表字段为t_amc_fund_detail_alteration.alteration_status
     */
    private Integer alterationStatus;

    public BigDecimal getFundShare() {
        return fundShare;
    }

    /**
     * @param fundShare 申购份额
     * @occurs required
     */
    public void setFundShare(BigDecimal fundShare) {
        this.fundShare = fundShare;
    }

    public String getFtcOrderNo() {
        return ftcOrderNo;
    }

    /**
     * @param ftcOrderNo 申购单号(原始申购单号)
     * @occurs required
     */
    public void setFtcOrderNo(String ftcOrderNo) {
        this.ftcOrderNo = ftcOrderNo;
    }


    public Date getFtcOrderCreateTime() {
        return ftcOrderCreateTime;
    }

    /**
     * @param ftcOrderCreateTime 申购单创建时间
     * @occurs required
     */
    public void setFtcOrderCreateTime(Date ftcOrderCreateTime) {
        this.ftcOrderCreateTime = ftcOrderCreateTime;
    }

    public Integer getAlterationStatus() {
        return alterationStatus;
    }

    /**
     * @param alterationStatus 申购确认状态 1-未确认,2-确认,3-取消
     * @occurs required
     */
    public void setAlterationStatus(Integer alterationStatus) {
        this.alterationStatus = alterationStatus;
    }
}
