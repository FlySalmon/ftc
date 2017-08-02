package com.eif.ftc.facade.fund.amc.request;

import com.eif.framework.common.utils.pkg.BaseRequest;

import java.math.BigDecimal;

/**
 * Created by Matt on 16/4/20.
 */
public class AssetAndFundInfoRequest extends BaseRequest {

    private static final long serialVersionUID = 4050328970714181496L;

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
     * @param productId 用户产品Id
     * @occurs required
     */
    public void setProductId(Long productId) {
        this.productId = productId;
    }
}
