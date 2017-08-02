package com.eif.ftc.facade.fund.amc.dto.request;

import com.eif.framework.common.utils.pkg.BaseDTO;

import java.io.Serializable;

/**
 * Created by Matt on 15/12/18.
 */
public class FundTotalAssetRequestBean extends BaseDTO {

    private static final long serialVersionUID = -9031134030684363821L;

    private String memberNo;

    private Long productId;

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
}
