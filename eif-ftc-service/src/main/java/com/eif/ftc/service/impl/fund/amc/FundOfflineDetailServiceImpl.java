package com.eif.ftc.service.impl.fund.amc;

import com.alibaba.rocketmq.common.protocol.ResponseCode;
import com.eif.ftc.dal.dao.FundOfflineDetailMapper;
import com.eif.ftc.dal.model.FundOfflineDetail;
import com.eif.ftc.facade.code.FTCRespCode;
import com.eif.ftc.facade.fund.amc.dto.request.FundOfflineAssetBean;
import com.eif.ftc.facade.fund.amc.dto.response.OfflineAssetInfo;
import com.eif.ftc.service.constant.AMCOrderType;
import com.eif.ftc.service.constant.AssetType;
import com.eif.ftc.service.constant.FundSettlementStatus;
import com.eif.ftc.service.constant.SoftDeleted;
import com.eif.ftc.service.fund.amc.FundOfflineDetailService;
import com.eif.ftc.util.SequenceGenerator;
import com.eif.ftc.util.exception.BusinessException;
import ma.glasnost.orika.MapperFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Matt on 16/5/3.
 */
@Service("fundOfflineDetailService")
public class FundOfflineDetailServiceImpl implements FundOfflineDetailService{
    @Autowired
    private FundOfflineDetailMapper fundOfflineDetailMapper;

    @Autowired
    private MapperFacade mapperFacade;

    @Resource
    SequenceGenerator sequenceGenerator;


    @Override
    public void addOfflineAsset(FundOfflineAssetBean fundOfflineAssetBean)
    {
        FundOfflineDetail fundOfflineDetail = mapperFacade.map(fundOfflineAssetBean,FundOfflineDetail.class);
        fundOfflineDetail.setFundOfflineDetailUuid(sequenceGenerator.amcNoGen(AMCOrderType.FUND_OFFLINE_DETAIL));

        fundOfflineDetail.setCreateTime(new Date());
        fundOfflineDetail.setUpdateTime(new Date());
        fundOfflineDetail.setHasSettlement(FundSettlementStatus.FUNDNOTSETTLED);
        fundOfflineDetail.setFundTotalAmount(fundOfflineAssetBean.getSettlementCapital());
        fundOfflineDetail.setOfflineMark(AssetType.OFFLINE);
        fundOfflineDetail.setSoftDeleted(SoftDeleted.NOTDELETED);

        try {
            fundOfflineDetailMapper.insert(fundOfflineDetail);
        }catch(DuplicateKeyException ex)
        {
            throw new BusinessException(FTCRespCode.ERR_FUND_TRANS_BUY_OFFLINE_TOKEN_DUPLICATE.getMessage(),FTCRespCode.ERR_FUND_TRANS_BUY_OFFLINE_TOKEN_DUPLICATE.getCode());
        }
    }

    @Override
    public List<OfflineAssetInfo> queryOfflineAssetByMemberNo(String memberNO)
    {
        List<OfflineAssetInfo> offlineAssetInfos;
        List<FundOfflineDetail> fundOfflineDetails = fundOfflineDetailMapper.queryOfflineAssetByMemberNO(memberNO,SoftDeleted.NOTDELETED);
        if(fundOfflineDetails != null)
        {
            offlineAssetInfos = mapperFacade.mapAsList(fundOfflineDetails,OfflineAssetInfo.class);
        }
        else {
            offlineAssetInfos = new ArrayList<OfflineAssetInfo>();
        }
        return offlineAssetInfos;
    }

    @Override
    @Transactional
    public void settlementOfflineAsset(String offlineAssetUuid)
    {
        FundOfflineDetail detail = fundOfflineDetailMapper.queryOfflineAssetByUuid(offlineAssetUuid,SoftDeleted.NOTDELETED);
        if(detail == null)
            throw new BusinessException(FTCRespCode.ERR_FUND_TRANS_OFFLINE_SETTLEMENT_NOT_FUND.getMessage(),
                    FTCRespCode.ERR_FUND_TRANS_OFFLINE_SETTLEMENT_NOT_FUND.getCode());
        else
        {
            detail.setUpdateTime(new Date());
            detail.setSettlementTime(new Date());
            detail.setHasSettlement(FundSettlementStatus.FUNDSETTLED);
            fundOfflineDetailMapper.updateByPrimaryKey(detail);
        }
    }
}
