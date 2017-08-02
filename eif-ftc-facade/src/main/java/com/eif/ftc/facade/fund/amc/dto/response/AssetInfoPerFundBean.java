package com.eif.ftc.facade.fund.amc.dto.response;

import com.eif.framework.common.utils.pkg.BaseDTO;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by Matt on 16/2/15.
 */
public class AssetInfoPerFundBean extends BaseDTO {


    private static final long serialVersionUID = 932185833020090625L;

    private Long productId;

    private Integer fundStatus;

    private BigDecimal totalAsset;

    private BigDecimal frozenAsset;

    private BigDecimal confirmedAsset;

    private BigDecimal unConfirmedAsset;

    private BigDecimal yesterdayProfit;

    private Date profitDate;


    /**
     * @return 用户产品编号
     * @occurs required
     */
    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    /**
     * @return 状态,已清盘,持有中
     * @occurs required
     */
    public Integer getFundStatus() {
        return fundStatus;
    }

    public void setFundStatus(Integer fundStatus) {
        this.fundStatus = fundStatus;
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


    /**
     * @return 昨日收益
     * @occurs required
     */
    public BigDecimal getYesterdayProfit() {
        return yesterdayProfit;
    }

    public void setYesterdayProfit(BigDecimal yesterdayProfit) {
        this.yesterdayProfit = yesterdayProfit;
    }

    /**
     * @return 最新红利分配日期
     * @occurs required
     */
    public Date getProfitDate() {
        return profitDate;
    }

    public void setProfitDate(Date profitDate) {
        this.profitDate = profitDate;
    }
}
