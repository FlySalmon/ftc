package com.eif.ftc.facade.fund.amc.dto.request;

import com.eif.framework.common.utils.pkg.BaseDTO;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by Matt on 16/1/6.
 */
public class FundBonusListQueryBean extends BaseDTO {

    private static final long serialVersionUID = -81612825384307970L;

    private String memberNO;

    private Long productId;

    private Date startDate;


    private Date endDate;


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


    public Date getStartDate() {
        return startDate;
    }

    /**
     * @param startDate 开始时间
     * @occurs required
     */
    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    /**
     * @param endDate 结束时间
     * @occurs required
     */
    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }
}
