package com.eif.ftc.facade.fund.trans.request;

import com.eif.framework.common.utils.pkg.BaseRequest;

import java.math.BigDecimal;

/**
 * Created by bohan on 1/21/16.
 */
public class CommonBuyFundLimitJustifyRequest extends BaseRequest {

 	private static final long serialVersionUID = 1L;
	
	private long productID;

    private BigDecimal fundTransAmount;

    private String memberNo;

    public long getProductID() {
        return productID;
    }

    /**
     * @occurs required
     * @param productID 产品ID
     */
    public void setProductID(long productID) {
        this.productID = productID;
    }

    public BigDecimal getFundTransAmount() {
        return fundTransAmount;
    }


    /**
     * @occurs optional
     * @param fundTransAmount 交易金额
     */
    public void setFundTransAmount(BigDecimal fundTransAmount) {
        this.fundTransAmount = fundTransAmount;
    }

    public String getMemberNo() {
        return memberNo;
    }


    /**
     * @occurs optional
     * @param memberNo 用户编号
     */
    public void setMemberNo(String memberNo) {
        this.memberNo = memberNo;
    }
}
