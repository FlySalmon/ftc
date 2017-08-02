package com.eif.ftc.facade.fund.amc.request;

import com.eif.framework.common.utils.pkg.page.PageableRequest;

import java.util.Date;

/**
 * Created by Matt on 2016/12/8.
 */
public class FundBonusPageableRequest extends PageableRequest {

    private static final long serialVersionUID = -669028438288492877L;

    private String memberNo;

    private Long productId;

    private Date beginTime;

    private Date endTime;


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


    public Date getBeginTime() {
        return beginTime;
    }

    /**
     * @param beginTime 开始时间
     * @occurs optional
     */
    public void setBeginTime(Date beginTime) {
        this.beginTime = beginTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    /**
     * @param endTime 结束时间
     * @occurs optional
     */
    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }
}