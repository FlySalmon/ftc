package com.eif.ftc.facade.amc;

import com.eif.framework.common.utils.pkg.BaseResponse;
import com.eif.framework.common.utils.pkg.page.PageableResponse;
import com.eif.ftc.facade.fund.amc.dto.response.BonusBean;
import com.eif.ftc.facade.fund.amc.request.*;
import com.eif.ftc.facade.fund.amc.response.*;

/**
 * @tag 资产相关接口
 */
public interface AMCFacade {

	/**
	 * @brief 禁止账户交易
	 * @param forbiddenAccountTransactionRequest 户资禁止交易请求
	 * @return 禁止结果
	 * @author Matt
	 * @version 1.0.0
	 * @memo init add api
     */
	@Deprecated
	BaseResponse fundAccountForbiddenTransaction(ForbiddenAccountTransactionRequest forbiddenAccountTransactionRequest);

	/**
	 * @brief 激活账户交易
	 * @param activeAccountTransactionRequest 户资开放交易请求
	 * @return 激活结果
	 * @author Matt
	 * @version 1.0.0
	 * @memo init add api
	 */
	@Deprecated
	BaseResponse fundAccountActiveTransaction(ActiveAccountTransactionRequest activeAccountTransactionRequest);

	/**
	 * @brief 查询当前用户的总资产(MTP用)
	 * @param memberAssetRequest 用户资产信息请求
	 * @return 用户总资产
	 * @author Matt
	 * @version 1.0.0
	 * @memo init add api
	 */
	//收益
	MemberAssetResponse getMemberAsset(MemberAssetRequest memberAssetRequest);

	/**
	 * @brief 查询当前用户的所有基金(MTP用)
	 * @param fundAssetListRequest 用户基金列表信息请求
	 * @return 用户所有的基金
	 * @author Matt
	 * @version 1.0.0
	 * @memo init add api
	 */
	//收益
	FundAssetListResponse getFundAssetList(FundAssetListRequest fundAssetListRequest);

	/**
	 * @brief 查询某只基金的资产收益(MTP用)
	 * @param fundBonusListRequest 用户基金收益记录请求
	 * @return 用户某只基金的收益明细记录
	 * @author Matt
	 * @version 1.0.0
	 * @memo init add api
	 */
	@Deprecated
	FundBonusListResponse getFundBonusList(FundBonusListRequest fundBonusListRequest);

	/**
	 * @brief 查询当前用户的某只基金总资产
	 * @param fundTotalAssetRequest 用户与资产号请求
	 * @return 用户某只基金资产与明细
	 * @author Matt
	 * @version 1.0.0
	 * @memo init add api
	 */
	//收益
	FundTotalAssetResponse getFundTotalAsset(FundTotalAssetRequest fundTotalAssetRequest);

	/**
	 * @brief 查询当前用户的基金资产账户与资产信息
	 * @param memberAccountAndAssetRequest 用户与资产号请求
	 * @return 用户总资产,基金资产与账户信息
	 * @author Matt
	 * @version 1.0.0
	 * @memo init add api
	 */
	MemberAccountAndAssetResponse getMemberAccAndAsset(MemberAccountAndAssetRequest memberAccountAndAssetRequest);

	/**
	 * @brief 查询当前用户基金账户是否被锁定
	 * @param queryMemberAccStatusRequest 用户会员号
	 * @return 用户账户状态
	 * @author Matt
	 * @version 1.0.0
	 * @memo init add api
	 */
	MemberAccStatusResponse queryMemberAccStatus(QueryMemberAccStatusRequest queryMemberAccStatusRequest);


	/**
	 * @brief 查询当前用户活期资产与账户信息
	 * @param assetAndFundInfoRequest 用户会员号与产品号
	 * @return 用户账户与资产状态信息
	 * @author Matt
	 * @version 1.0.0
	 * @memo init add api
	 */
	AssetAndFundInfoResponse getAssetInfoAndFundInfo(AssetAndFundInfoRequest assetAndFundInfoRequest);


	/**
	 * @brief 新增某一用户线下资产
	 * @param addOfflineAssetRequest 线下资产增加请求
	 * @return 返回结果
	 * @author Matt
	 * @version 1.0.0
	 * @memo init add api
	 */
	BaseResponse addOfflineAsset(AddOfflineAssetRequest addOfflineAssetRequest);



	/**
	 * @brief 清盘线下资产
	 * @param settlementOfflineAssetRequest 线下资产清盘请求
	 * @return 返回结果
	 * @author Matt
	 * @version 1.0.0
	 * @memo init add api
	 */
	BaseResponse settlementOfflineAsset(SettlementOfflineAssetRequest settlementOfflineAssetRequest);




	/**
	 * @brief 查询线下资产
	 * @param queryOfflineAssetRequest 线下资产增加请求
	 * @return 返回结果
	 * @author Matt
	 * @version 1.0.0
	 * @memo init add api
	 */
	OfflineAssetResponse queryOfflineAsset(QueryOfflineAssetRequest queryOfflineAssetRequest);



	/**
	 * @brief 分页获取红利信息(活期封闭期会展示贴息)
	 * @param fundBonusPageableRequest 请求某会员某产品的红利记录
	 * @return 返回结果
	 * @author Matt
	 * @version 1.0.1
	 * @memo init add api
	 */
	PageableResponse<BonusBean> getFundBonusByPageable(FundBonusPageableRequest fundBonusPageableRequest);


	/**
	 * @brief 根据用户id与closeType查询资产信息
	 * @param getMemberAssetRequest 查询请求
	 * @return 返回结果
	 * @author Matt
	 * @version 1.0.0
	 * @memo init add api
	 */
	MemberAssetByCloseTypeResponse queryMemberAssetByCloseType(GetMemberAssetRequest getMemberAssetRequest);


}
