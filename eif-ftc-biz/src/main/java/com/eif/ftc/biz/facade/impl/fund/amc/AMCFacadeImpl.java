package com.eif.ftc.biz.facade.impl.fund.amc;

import com.eif.framework.common.utils.code.CommonRspCode;
import com.eif.framework.common.utils.pkg.BaseResponse;
import com.eif.framework.common.utils.pkg.page.PageableResponse;
import com.eif.ftc.facade.amc.AMCFacade;
import com.eif.ftc.facade.code.FTCRespCode;
import com.eif.ftc.facade.fund.amc.dto.request.*;
import com.eif.ftc.facade.fund.amc.dto.response.*;
import com.eif.ftc.facade.fund.amc.request.*;
import com.eif.ftc.facade.fund.amc.response.*;
import com.eif.ftc.service.bean.FundAccountBean;
import com.eif.ftc.service.fund.amc.FundAccountService;
import com.eif.ftc.service.fund.amc.FundBonusService;
import com.eif.ftc.service.fund.amc.FundOfflineDetailService;
import com.eif.ftc.service.fund.amc.MemberAssetService;
import ma.glasnost.orika.MapperFacade;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by Matt on 16/1/11.
 */
public class AMCFacadeImpl implements AMCFacade {
    @Autowired
    private FundAccountService fundAccountService;

    @Autowired
    private MemberAssetService memberAssetService;

    @Autowired
    private FundOfflineDetailService fundOfflineDetailService;

    @Autowired
    private FundBonusService fundBonusService;

    @Autowired
    private MapperFacade mapperFacade;

    @Override
    public BaseResponse fundAccountForbiddenTransaction(ForbiddenAccountTransactionRequest forbiddenAccountTransactionRequest) {
        BaseResponse response = new BaseResponse();
        if (forbiddenAccountTransactionRequest == null) {
            response.setRespCode(CommonRspCode.VERIFY_FAIL.getCode());
            response.setMsg(CommonRspCode.VERIFY_FAIL.getMessage());
            return response;
        }
        FundAccountMemberNoBean fundAccountMemberNoBean = forbiddenAccountTransactionRequest.getFundAccountMemberNoBean();
        if (fundAccountMemberNoBean == null || fundAccountMemberNoBean.getMemberNO() == null) {
            response.setRespCode(CommonRspCode.VERIFY_FAIL.getCode());
            response.setMsg(CommonRspCode.VERIFY_FAIL.getMessage());
            return response;
        }

        Boolean result = fundAccountService.fundAccountForbiddenTransaction(fundAccountMemberNoBean);
        if (result) {
            response.setRespCode(CommonRspCode.SUCCESS.getCode());
            response.setMsg(CommonRspCode.SUCCESS.getMessage());
            return response;
        } else {
            response.setRespCode(CommonRspCode.ERROR.getCode());
            response.setMsg(CommonRspCode.ERROR.getCode());
            return response;
        }

    }

    @Override
    public BaseResponse fundAccountActiveTransaction(ActiveAccountTransactionRequest activeAccountTransactionRequest) {
        BaseResponse response = new BaseResponse();
        if (activeAccountTransactionRequest == null) {
            response.setRespCode(CommonRspCode.VERIFY_FAIL.getCode());
            response.setMsg(CommonRspCode.VERIFY_FAIL.getMessage());
            return response;
        }
        FundAccountMemberNoBean fundAccountMemberNoBean = activeAccountTransactionRequest.getFundAccountMemberNoBean();
        if (fundAccountMemberNoBean == null || fundAccountMemberNoBean.getMemberNO() == null) {
            response.setRespCode(CommonRspCode.VERIFY_FAIL.getCode());
            response.setMsg(CommonRspCode.VERIFY_FAIL.getMessage());
            return response;
        }

        Boolean result = fundAccountService.fundAccountActiveTransaction(fundAccountMemberNoBean);
        if (result) {
            response.setRespCode(CommonRspCode.SUCCESS.getCode());
            response.setMsg(CommonRspCode.SUCCESS.getMessage());
            return response;
        } else {
            response.setRespCode(CommonRspCode.ERROR.getCode());
            response.setMsg(CommonRspCode.ERROR.getCode());
            return response;
        }
    }

    @Override
    public MemberAssetResponse getMemberAsset(MemberAssetRequest memberAssetRequest) {
        MemberAssetResponse response = new MemberAssetResponse();
        if (memberAssetRequest == null) {
            response.setRespCode(CommonRspCode.VERIFY_FAIL.getCode());
            response.setMsg(CommonRspCode.VERIFY_FAIL.getMessage());
            return response;
        }


        MemberAssetQueryBean bean = memberAssetRequest.getMemberAssetQueryBean();


        if (bean == null || bean.getMemberNO() == null) {
            response.setRespCode(CommonRspCode.VERIFY_FAIL.getCode());
            response.setMsg(CommonRspCode.VERIFY_FAIL.getMessage());
            return response;
        }


        MemberTotalAssetBean result = memberAssetService.getMemberTotalAsset(bean);

        if (result != null) {
            response.setRespCode(CommonRspCode.SUCCESS.getCode());
            response.setMsg(CommonRspCode.SUCCESS.getMessage());
            response.setMemberTotalAssetBean(result);
            return response;
        } else {
            response.setRespCode(CommonRspCode.ERROR.getCode());
            response.setMsg(CommonRspCode.ERROR.getCode());
            return response;
        }

    }

    @Override
    public FundAssetListResponse getFundAssetList(FundAssetListRequest fundAssetListRequest) {
        FundAssetListResponse response = new FundAssetListResponse();
        if (fundAssetListRequest == null) {
            response.setRespCode(CommonRspCode.VERIFY_FAIL.getCode());
            response.setMsg(CommonRspCode.VERIFY_FAIL.getMessage());
            return response;
        }

        FundAssetListQueryBean bean = fundAssetListRequest.getFundAssetListQueryBean();


        if (bean == null || bean.getMemberNO() == null) {
            response.setRespCode(CommonRspCode.VERIFY_FAIL.getCode());
            response.setMsg(CommonRspCode.VERIFY_FAIL.getMessage());
            return response;
        }
        FundAssetListBean result = memberAssetService.getFundAssetList(bean);
        if (result != null) {
            response.setRespCode(CommonRspCode.SUCCESS.getCode());
            response.setMsg(CommonRspCode.SUCCESS.getMessage());
            response.setFundAssetListBean(result);
            return response;
        } else {
            response.setRespCode(CommonRspCode.ERROR.getCode());
            response.setMsg(CommonRspCode.ERROR.getCode());
            return response;
        }

    }

    @Override
    public FundBonusListResponse getFundBonusList(FundBonusListRequest fundBonusListRequest) {
        FundBonusListResponse response = new FundBonusListResponse();

        if (fundBonusListRequest == null) {
            response.setRespCode(CommonRspCode.VERIFY_FAIL.getCode());
            response.setMsg(CommonRspCode.VERIFY_FAIL.getMessage());
            return response;
        }

        FundBonusListQueryBean bean = fundBonusListRequest.getFundBonusListQueryBean();
        if (bean == null
                || bean.getMemberNO() == null
                || bean.getEndDate() == null
                || bean.getStartDate() == null
                || bean.getProductId() == null) {
            response.setRespCode(CommonRspCode.VERIFY_FAIL.getCode());
            response.setMsg(CommonRspCode.VERIFY_FAIL.getMessage());
            return response;
        }
        response = memberAssetService.getFundBonusList(bean);
        response.setRespCode(CommonRspCode.SUCCESS.getCode());
        response.setMsg(CommonRspCode.SUCCESS.getMessage());
        return response;
    }

    @Override
    public FundTotalAssetResponse getFundTotalAsset(FundTotalAssetRequest fundTotalAssetRequest) {
        FundTotalAssetResponse response = new FundTotalAssetResponse();
        if (fundTotalAssetRequest == null) {
            response.setRespCode(CommonRspCode.VERIFY_FAIL.getCode());
            response.setMsg(CommonRspCode.VERIFY_FAIL.getMessage());
            return response;
        }

        FundTotalAssetRequestBean bean = fundTotalAssetRequest.getFundTotalAssetRequestBean();


        if (bean == null || bean.getMemberNo() == null || bean.getProductId() == null) {
            response.setRespCode(CommonRspCode.VERIFY_FAIL.getCode());
            response.setMsg(CommonRspCode.VERIFY_FAIL.getMessage());
            return response;
        }

        FundTotalAssetBean result = memberAssetService.getFundTotalAsset(bean);
        if (result != null) {
            response.setRespCode(CommonRspCode.SUCCESS.getCode());
            response.setMsg(CommonRspCode.SUCCESS.getMessage());
            response.setFundTotalAssetBean(result);
            return response;
        } else {
            response.setRespCode(CommonRspCode.ERROR.getCode());
            response.setMsg(CommonRspCode.ERROR.getCode());
            return response;
        }
    }

    @Override
    public MemberAccountAndAssetResponse getMemberAccAndAsset(MemberAccountAndAssetRequest memberAccountAndAssetRequest) {
        MemberAccountAndAssetResponse memberAccountAndAssetResponse = new MemberAccountAndAssetResponse();

        if (memberAccountAndAssetRequest == null) {
            memberAccountAndAssetResponse.setRespCode(CommonRspCode.VERIFY_FAIL.getCode());
            memberAccountAndAssetResponse.setMsg(CommonRspCode.VERIFY_FAIL.getMessage());
            return memberAccountAndAssetResponse;
        }

        MemberAssetQueryBean memberAssetQueryBean = memberAccountAndAssetRequest.getMemberAssetQueryBean();

        if (memberAssetQueryBean == null || memberAssetQueryBean.getMemberNO() == null) {
            memberAccountAndAssetResponse.setRespCode(CommonRspCode.VERIFY_FAIL.getCode());
            memberAccountAndAssetResponse.setMsg(CommonRspCode.VERIFY_FAIL.getMessage());
            return memberAccountAndAssetResponse;
        }


        MemberAccountAndAssetBean memberAccountAndAssetBean = memberAssetService.getMemberAccAndAsset(memberAccountAndAssetRequest.getMemberAssetQueryBean());


        if (memberAccountAndAssetBean != null) {
            memberAccountAndAssetResponse.setMemberAccountAndAssetBean(memberAccountAndAssetBean);
            memberAccountAndAssetResponse.setRespCode(CommonRspCode.SUCCESS.getCode());
            memberAccountAndAssetResponse.setMsg(CommonRspCode.SUCCESS.getMessage());
        } else {
            memberAccountAndAssetResponse.setRespCode(CommonRspCode.ERROR.getCode());
            memberAccountAndAssetResponse.setMsg(CommonRspCode.ERROR.getMessage());
        }

        return memberAccountAndAssetResponse;
    }

    @Override
    public MemberAccStatusResponse queryMemberAccStatus(QueryMemberAccStatusRequest queryMemberAccStatusRequest) {
        MemberAccStatusResponse response = new MemberAccStatusResponse();

        if (queryMemberAccStatusRequest == null) {
            response.setRespCode(CommonRspCode.VERIFY_FAIL.getCode());
            response.setMsg(CommonRspCode.VERIFY_FAIL.getMessage());
            return response;
        }

        MemberAssetQueryBean bean = queryMemberAccStatusRequest.getMemberAssetQueryBean();
        if (bean == null || bean.getMemberNO() == null) {
            response.setRespCode(CommonRspCode.VERIFY_FAIL.getCode());
            response.setMsg(CommonRspCode.VERIFY_FAIL.getMessage());
            return response;
        }

        response.setStatus(memberAssetService.getMemAccStatus(bean));
        response.setMsg(CommonRspCode.SUCCESS.getMessage());
        response.setRespCode(CommonRspCode.SUCCESS.getCode());
        return response;
    }

    @Override
    public AssetAndFundInfoResponse getAssetInfoAndFundInfo(AssetAndFundInfoRequest assetAndFundInfoRequest) {
        AssetAndFundInfoResponse response = new AssetAndFundInfoResponse();
        if (assetAndFundInfoRequest == null || assetAndFundInfoRequest.getMemberNo() == null || assetAndFundInfoRequest.getProductId() == null) {
            response.setRespCode(CommonRspCode.VERIFY_FAIL.getCode());
            response.setMsg(CommonRspCode.VERIFY_FAIL.getMessage());
            return response;
        }
        FundAccountBean fundAccountBean = fundAccountService.getFundAccountByMemberNo(assetAndFundInfoRequest.getMemberNo());
        if (fundAccountBean == null) {
            response.setRespCode(FTCRespCode.ERR_FUND_ACCT_NOT_FOUND.getCode());
            response.setMsg(FTCRespCode.ERR_FUND_ACCT_NOT_FOUND.getMessage());
            return response;
        }

        FundTotalAssetRequestBean requestBean = new FundTotalAssetRequestBean();
        requestBean.setProductId(assetAndFundInfoRequest.getProductId());
        requestBean.setMemberNo(assetAndFundInfoRequest.getMemberNo());
        FundTotalAssetBean fundTotalAssetBean = memberAssetService.getFundTotalAsset(requestBean);


        response.setAccountNo(fundAccountBean.getFundAccountNo());
        response.settAAccount(fundAccountBean.getTaAccountId());
        response.setTransactionAccount(fundAccountBean.getTransactionAccount());
        response.setConfirmedAddAmout(fundTotalAssetBean.getConfirmedAmount());
        response.setTotalAmount(fundTotalAssetBean.getTotalAmount());
        response.setUnConfirmedAddAmount(fundTotalAssetBean.getUnconfirmedAmount());
        response.setUnConfirmedSubAmount(fundTotalAssetBean.getUnconfirmedSubAmount());
        response.setTotalBonusAmount(fundTotalAssetBean.getTotalProfit());
        response.setMsg(CommonRspCode.SUCCESS.getMessage());
        response.setRespCode(CommonRspCode.SUCCESS.getCode());
        return response;
    }

    @Override
    public BaseResponse addOfflineAsset(AddOfflineAssetRequest addOfflineAssetRequest) {
        BaseResponse response = new BaseResponse();
        if (addOfflineAssetRequest == null
                || addOfflineAssetRequest.getBonusAmount() == null
                || addOfflineAssetRequest.getCustomerCardNo() == null
                || addOfflineAssetRequest.getCustomerName() == null
                || addOfflineAssetRequest.getCustomerPhone() == null
                || addOfflineAssetRequest.getMemberNo() == null
                || addOfflineAssetRequest.getOfflineCode() == null
                || addOfflineAssetRequest.getProductId() == null
                || addOfflineAssetRequest.getProductName() == null
                || addOfflineAssetRequest.getProfitAmount() == null
                || addOfflineAssetRequest.getSettlementCapital() == null
                || addOfflineAssetRequest.getTotalProfit() == null
                || addOfflineAssetRequest.getInceptionDate() == null
                || addOfflineAssetRequest.getDueDate() == null) {
            response.setRespCode(CommonRspCode.PARAM_ERROR.getCode());
            response.setMsg(CommonRspCode.PARAM_ERROR.getMessage());
            return response;
        }


        FundOfflineAssetBean fundOfflineAssetBean = mapperFacade.map(addOfflineAssetRequest, FundOfflineAssetBean.class);
        fundOfflineDetailService.addOfflineAsset(fundOfflineAssetBean);
        response.setMsg(CommonRspCode.SUCCESS.getMessage());
        response.setRespCode(CommonRspCode.SUCCESS.getCode());
        return response;
    }

    @Override
    public BaseResponse settlementOfflineAsset(SettlementOfflineAssetRequest settlementOfflineAssetRequest) {
        BaseResponse response = new BaseResponse();
        if (settlementOfflineAssetRequest == null
                || settlementOfflineAssetRequest.getOfflineAssetUUID() == null) {
            response.setRespCode(CommonRspCode.PARAM_ERROR.getCode());
            response.setMsg(CommonRspCode.PARAM_ERROR.getMessage());
            return response;
        }
        fundOfflineDetailService.settlementOfflineAsset(settlementOfflineAssetRequest.getOfflineAssetUUID());
        response.setMsg(CommonRspCode.SUCCESS.getMessage());
        response.setRespCode(CommonRspCode.SUCCESS.getCode());
        return response;
    }

    @Override
    public OfflineAssetResponse queryOfflineAsset(QueryOfflineAssetRequest queryOfflineAssetRequest) {
        OfflineAssetResponse response = new OfflineAssetResponse();
        if (queryOfflineAssetRequest == null
                || queryOfflineAssetRequest.getMemberNo() == null) {
            response.setRespCode(CommonRspCode.PARAM_ERROR.getCode());
            response.setMsg(CommonRspCode.PARAM_ERROR.getMessage());
            return response;
        }
        List<OfflineAssetInfo> offlineAssetInfos = fundOfflineDetailService.queryOfflineAssetByMemberNo(queryOfflineAssetRequest.getMemberNo());
        response.setOfflineAssetInfos(offlineAssetInfos);
        response.setRespCode(CommonRspCode.SUCCESS.getCode());
        response.setMsg(CommonRspCode.SUCCESS.getMessage());
        return response;
    }


    @Override
    public PageableResponse<BonusBean> getFundBonusByPageable(FundBonusPageableRequest fundBonusPageableRequest) {

        PageableResponse<BonusBean> response = new PageableResponse<BonusBean>();
        if (fundBonusPageableRequest == null
                || fundBonusPageableRequest.getMemberNo() == null
                || fundBonusPageableRequest.getProductId() == null) {
            response.setRespCode(CommonRspCode.PARAM_ERROR.getCode());
            response.setMsg(CommonRspCode.PARAM_ERROR.getMessage());
            return response;
        }
        response = fundBonusService.getFundBonusList(fundBonusPageableRequest);
        response.setRespCode(CommonRspCode.SUCCESS.getCode());
        response.setMsg(CommonRspCode.SUCCESS.getMessage());
        return response;

    }

    @Override
    public MemberAssetByCloseTypeResponse queryMemberAssetByCloseType(GetMemberAssetRequest getMemberAssetRequest) {
        MemberAssetByCloseTypeResponse response = new MemberAssetByCloseTypeResponse();
        if (getMemberAssetRequest == null
                || getMemberAssetRequest.getMemberNo() == null
                || getMemberAssetRequest.getProductCloseType() == null) {
            response.setRespCode(CommonRspCode.PARAM_ERROR.getCode());
            response.setMsg(CommonRspCode.PARAM_ERROR.getMessage());
            return response;
        }
        memberAssetService.queryMemberAssetByCloseType(response,getMemberAssetRequest.getMemberNo(),getMemberAssetRequest.getProductCloseType());
        return response;
    }


}
