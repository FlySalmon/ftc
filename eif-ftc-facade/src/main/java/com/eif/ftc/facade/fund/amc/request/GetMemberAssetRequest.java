package com.eif.ftc.facade.fund.amc.request;

import com.eif.framework.common.utils.pkg.BaseRequest;

import java.util.List;

/**
 * Created by Matt on 2017/4/24.
 */
public class GetMemberAssetRequest extends BaseRequest {

    private static final long serialVersionUID = -3275513692892891732L;

    private List<Integer> productCloseType;

    private String memberNo;

    public List<Integer> getProductCloseType() {
        return productCloseType;
    }

    /**
     * @param productCloseType 产品封闭类型
     * @occurs required
     */
    public void setProductCloseType(List<Integer> productCloseType) {
        this.productCloseType = productCloseType;
    }

    public String getMemberNo() {
        return memberNo;
    }

    /**
     * @param memberNo 用户号
     * @occurs required
     */
    public void setMemberNo(String memberNo) {
        this.memberNo = memberNo;
    }
}