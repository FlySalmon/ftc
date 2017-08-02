package com.eif.ftc.facade.fund.amc.dto.response;

import com.eif.framework.common.utils.pkg.BaseDTO;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Created by Matt on 16/2/15.
 */
public class AssetAccountAndTotalAssetBean extends BaseDTO {

    private static final long serialVersionUID = 8355893933597111867L;

    private String memberNo;

    private String transActionAccount;

    private String taAccount;

    private Integer status;

    private BigDecimal totalAsset;

    private BigDecimal frozenAsset;

    private BigDecimal confirmedAsset;

    private BigDecimal unConfirmedAsset;


    /**
     * @return 会员编号
     * @occurs required
     */
    public String getMemberNo() {
        return memberNo;
    }


    public void setMemberNo(String memberNo) {
        this.memberNo = memberNo;
    }


    /**
     * @return 交易账号
     * @occurs required
     */
    public String getTransActionAccount() {
        return transActionAccount;
    }

    public void setTransActionAccount(String transActionAccount) {
        this.transActionAccount = transActionAccount;
    }


    /**
     * @return 基金账号
     * @occurs required
     */
    public String getTaAccount() {
        return taAccount;
    }

    public void setTaAccount(String taAccount) {
        this.taAccount = taAccount;
    }

    /**
     * @return 状态
     * @occurs required
     */
    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }


    /**
     * @return 总资产
     * @occurs required
     */
    public BigDecimal getTotalAsset() {
        return totalAsset;
    }

    public void setTotalAsset(BigDecimal totalAsset) {
        this.totalAsset = totalAsset;
    }

    /**
     * @return 冻结资产
     * @occurs required
     */
    public BigDecimal getFrozenAsset() {
        return frozenAsset;
    }

    public void setFrozenAsset(BigDecimal frozenAsset) {
        this.frozenAsset = frozenAsset;
    }

    /**
     * @return 已确认资产
     * @occurs required
     */
    public BigDecimal getConfirmedAsset() {
        return confirmedAsset;
    }

    public void setConfirmedAsset(BigDecimal confirmedAsset) {
        this.confirmedAsset = confirmedAsset;
    }

    /**
     * @return 未确认资产
     * @occurs required
     */
    public BigDecimal getUnConfirmedAsset() {
        return unConfirmedAsset;
    }

    public void setUnConfirmedAsset(BigDecimal unConfirmedAsset) {
        this.unConfirmedAsset = unConfirmedAsset;
    }
}
