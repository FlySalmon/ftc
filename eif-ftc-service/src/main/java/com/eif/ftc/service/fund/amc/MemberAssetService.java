package com.eif.ftc.service.fund.amc;

import java.math.BigDecimal;
import java.util.List;

import com.eif.ftc.facade.fund.amc.dto.request.*;
import com.eif.ftc.facade.fund.amc.dto.response.*;
import com.eif.ftc.facade.fund.amc.response.FundBonusListResponse;
import com.eif.ftc.facade.fund.amc.response.MemberAssetByCloseTypeResponse;

/**
 * Created by Matt on 15/12/29.
 */
public interface MemberAssetService {
    FundTotalAssetBean getFundTotalAsset(FundTotalAssetRequestBean bean);

    MemberTotalAssetBean getMemberTotalAsset(MemberAssetQueryBean bean);

    FundAssetListBean getFundAssetList(FundAssetListQueryBean fundAssetListQueryBean);

    @Deprecated
    FundBonusListResponse getFundBonusList(FundBonusListQueryBean bean);

    MemberAccountAndAssetBean getMemberAccAndAsset(MemberAssetQueryBean memberAssetQueryBean);

    Integer getMemAccStatus(MemberAssetQueryBean memberAssetQueryBean);
    
    /**
     * 冻结用户产品资产份额
     * @param memberNo
     * @param productId
     */
    void freezeMemberProductAsset(String memberNo, Long productId);

    /**
     * 解冻用户产品资产份额
     * @param memberNo
     * @param productId
     */
    void unfreezeMemberProductAsset(String memberNo, Long productId);

    /**
     * 解冻并扣减用户产品资产份额
     * @param memberNo
     * @param productId
     */
    void unfreezeAndDeductMemberProductAsset(String memberNo, Long productId,String transferorOrderNo);
    
    /**
     * 新增用户二级市场产品资产份额
     * @param memberNo
     * @param mktProductId
     * @param principalAmount
     * @param expectProfit
     */
    void createMemberProductAsset(String memberNo, Long mktProductId, 
    		BigDecimal principalAmount, BigDecimal expectProfit,String transfereeOrderNo);

    void queryMemberAssetByCloseType(MemberAssetByCloseTypeResponse response, String memberNo, List<Integer> productCloseType);
}
