package com.eif.ftc.service.bean;

import com.eif.framework.common.utils.pkg.BaseDTO;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by Matt on 15/12/18.
 */
public class FundPurchaseBean {

    /**
     *  基金交易账号,所属表字段为t_amc_fund_detail_alteration.transaction_account
     */
    private String memberNo;


    /**
     *  用户产品ID,所属表字段为t_amc_fund_detail_alteration.product_id
     */
    private Long productId;


    /**
     *  该只基金的份额,所属表字段为t_amc_fund_detail_alteration.fund_share
     */
    private BigDecimal fundAmount;


    /**
     *  金融交易系统单号,所属表字段为t_amc_fund_detail_alteration.ftc_order_no
     */
    private String ftcOrderNo;

    /**
     *  FTC单号创建时间,所属表字段为t_amc_fund_detail_alteration.ftc_order_create_time
     */
    private Date ftcOrderCreateTime;



    /**
     *  该只基金的预期优惠收益(加息券)
     */
    private BigDecimal expectProfitAmount;

    public BigDecimal getExpectProfitAmount() {
        return expectProfitAmount;
    }

    /**
     * @param expectProfitAmount 预期加息收益
     * @occurs required
     */
    public void setExpectProfitAmount(BigDecimal expectProfitAmount) {
        this.expectProfitAmount = expectProfitAmount;
    }

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
     * @param productId 产品号
     * @occurs required
     */
    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public BigDecimal getFundAmount() {
        return fundAmount;
    }

    /**
     * @param fundAmount 申购金额
     * @occurs required
     */
    public void setFundAmount(BigDecimal fundAmount) {
        this.fundAmount = fundAmount;
    }

    public String getFtcOrderNo() {
        return ftcOrderNo;
    }

    /**
     * @param ftcOrderNo 申购单单号
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
}
