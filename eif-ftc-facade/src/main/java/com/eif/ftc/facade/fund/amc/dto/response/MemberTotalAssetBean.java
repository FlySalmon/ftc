package com.eif.ftc.facade.fund.amc.dto.response;

import com.eif.framework.common.utils.pkg.BaseDTO;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;

/**
 * Created by Matt on 15/12/29.
 */
public class MemberTotalAssetBean extends BaseDTO {

    private static final long serialVersionUID = 7427400648756479485L;

    private BigDecimal totalAmount;

    private BigDecimal yesterdayProfit;

    private BigDecimal totalProfit;

    private ArrayList<FundAssetInfoBean> fundAssetInfoBeen = new ArrayList<FundAssetInfoBean>();

    /**
     * @return 总资产
     * @occurs required
     */
    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
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
     * @return 总收益
     * @occurs required
     */
    public BigDecimal getTotalProfit() {
        return totalProfit;
    }

    public void setTotalProfit(BigDecimal totalProfit) {
        this.totalProfit = totalProfit;
    }

    /**
     * @return 购买的产品列表及信息
     * @occurs required
     */
    public ArrayList<FundAssetInfoBean> getFundAssetInfoBeen() {
        return fundAssetInfoBeen;
    }

    public void setFundAssetInfoBeen(ArrayList<FundAssetInfoBean> fundAssetInfoBeen) {
        this.fundAssetInfoBeen = fundAssetInfoBeen;
    }
}
