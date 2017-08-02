package com.eif.ftc.service.impl.fund.amc;

import com.eif.framework.common.utils.code.CommonRspCode;
import com.eif.framework.common.utils.pkg.page.PageableResponse;
import com.eif.framework.pagination.pagehelper.Page;
import com.eif.framework.pagination.pagehelper.PageHelper;
import com.eif.ftc.dal.constant.BonusAlternationType;
import com.eif.ftc.dal.constant.ProfitAlternationType;
import com.eif.ftc.dal.dao.*;
import com.eif.ftc.dal.model.*;
import com.eif.ftc.facade.fund.amc.dto.response.BonusBean;
import com.eif.ftc.facade.fund.amc.request.FundBonusPageableRequest;
import com.eif.ftc.service.fund.amc.FundBonusService;
import ma.glasnost.orika.MapperFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.*;

/**
 * Created by Matt on 2016/12/8.
 */
@Service("fundBonusService")
public class FundBonusServiceImpl implements FundBonusService {

    @Autowired
    FundBonusDetailAlterationMapper fundBonusDetailAlterationMapper;

    @Autowired
    MemberFundAccountMapper memberFundAccountMapper;

    @Autowired
    FundTotalMapper fundTotalMapper;

    @Autowired
    FundDetailMapper fundDetailMapper;

    @Autowired
    MapperFacade mapperFacade;

    @Autowired
    FundProfitAlterationMapper fundProfitAlterationMapper;

    @Override
    public PageableResponse<BonusBean> getFundBonusList(FundBonusPageableRequest fundBonusPageableRequest) {

        PageableResponse<BonusBean> resp = new PageableResponse<>();
        MemberFundAccount memberFundAccount = memberFundAccountMapper.selectByMemberNo(fundBonusPageableRequest.getMemberNo());
        if (memberFundAccount == null) {
            resp.setRespCode(CommonRspCode.SUCCESS.getCode());
            resp.setMsg(CommonRspCode.SUCCESS.getMessage());
            return resp;
        }
        FundTotal fundTotal = fundTotalMapper.selectByFundAccountNo(memberFundAccount.getFundAccUuid());
        FundDetail fundDetail = fundDetailMapper.selectByTotalUUIDAndProductId(fundTotal.getFundTotalUuid(), fundBonusPageableRequest.getProductId());
        if (fundDetail == null) {
            resp.setRespCode(CommonRspCode.SUCCESS.getCode());
            resp.setMsg(CommonRspCode.SUCCESS.getMessage());
            return resp;
        }


        FundBonusDetailAlterationExample example = new FundBonusDetailAlterationExample();
        FundBonusDetailAlterationExample.Criteria criteria = example.createCriteria();
        example.setOrderByClause("bonus_time DESC");
        criteria.andFundDetailUuidEqualTo(fundDetail.getFundDetailUuid());
//        criteria.andBonusTypeBetween(BonusAlternationType.OPEN.getValue(), BonusAlternationType.CLOSE.getValue());
        List<Integer> bonusTypeList = new ArrayList<Integer>();
        bonusTypeList.add(BonusAlternationType.OPEN.getValue());
        bonusTypeList.add(BonusAlternationType.CLOSE.getValue());
        criteria.andBonusTypeIn(bonusTypeList);
        if (fundBonusPageableRequest.getBeginTime() != null) {
            criteria.andBonusTimeGreaterThanOrEqualTo(fundBonusPageableRequest.getBeginTime());
        }
        if (fundBonusPageableRequest.getEndTime() != null) {
            criteria.andBonusTimeLessThanOrEqualTo(fundBonusPageableRequest.getEndTime());
        }
        PageHelper.startPage(fundBonusPageableRequest.getPageNum(), fundBonusPageableRequest.getPageSize(), fundBonusPageableRequest.isCalCount());
        Page<FundBonusDetailAlteration> fundBonusDetailAlterations = (Page<FundBonusDetailAlteration>) fundBonusDetailAlterationMapper.selectByExample(example);

        List<Date> profitDateList = new ArrayList<>();
        for (FundBonusDetailAlteration bonusBean : fundBonusDetailAlterations) {
            profitDateList.add(bonusBean.getBonusTime());
        }

        Map<String, FundProfitAlteration> fundProfitsMap = new HashMap<>();
        if (profitDateList.size() > 0) {
            FundProfitAlterationExample fundProfitAlterationExample = new FundProfitAlterationExample();
            FundProfitAlterationExample.Criteria fundProfitAlterationCriteria = fundProfitAlterationExample.createCriteria();
            fundProfitAlterationCriteria.andFundDetailUuidEqualTo(fundDetail.getFundDetailUuid());
            fundProfitAlterationCriteria.andProfitAddTimeIn(profitDateList);
            fundProfitAlterationCriteria.andProfitTypeEqualTo(ProfitAlternationType.COTOP.getValue());
            List<FundProfitAlteration> fundProfitAlterations = fundProfitAlterationMapper.selectByExample(fundProfitAlterationExample);
            for (FundProfitAlteration bean : fundProfitAlterations) {
                String key = bean.getProfitAddTime().toString();
                if (fundProfitsMap.get(key) == null)
                    fundProfitsMap.put(key, bean);
                else
                    fundProfitsMap.put(key, null);
            }
        }


        resp.initFromPage(fundBonusDetailAlterations);
        List<BonusBean> bonusBeans = mapperFacade.mapAsList(fundBonusDetailAlterations, BonusBean.class);
        for (BonusBean bean : bonusBeans) {
            bean.setProductId(fundBonusPageableRequest.getProductId());
            String key = bean.getBonusDate().toString();
            if (fundProfitsMap.get(key) != null)
                bean.setProfitAmount(fundProfitsMap.get(key).getProfitAmount());
            else
                bean.setProfitAmount(BigDecimal.ZERO);
        }
        resp.setList(bonusBeans);
        return resp;

    }
}
