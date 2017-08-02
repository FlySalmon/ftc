package com.eif.ftc.service.bean;

import com.eif.framework.common.utils.pkg.BaseDTO;

import java.io.Serializable;

/**
 * Created by Matt on 16/1/6.
 */
public class FundDetailListQueryBean {

    private String memberNO;

    private Long productId;


    public String getMemberNO() {
        return memberNO;
    }

    /**
     * @param memberNO 会员号
     * @occurs required
     */
    public void setMemberNO(String memberNO) {
        this.memberNO = memberNO;
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
