package com.eif.ftc.service.trans.action.impl;

import com.eif.framework.common.utils.code.CommonRspCode;
import com.eif.ftc.dal.dao.FundTransOrderMapper;
import com.eif.ftc.dal.model.FundTransOrder;
import com.eif.ftc.dal.model.FundTransOrderExample;
import com.eif.ftc.facade.fund.trans.enumeration.FundTransOrderStatus;
import com.eif.ftc.integration.ofc.OfcIntService;
import com.eif.ftc.service.trans.action.FundSettingsActionService;
import com.eif.ftc.service.trans.data.FundTransDataService;
import com.eif.ftc.util.exception.BusinessException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by bohan on 9/29/16.
 */
@Service("fundSettingsActionService")
public class FundSettingsActionServiceImpl implements FundSettingsActionService {

    @Autowired
    FundTransOrderMapper fundTransOrderMapper;

    @Autowired
    FundTransDataService fundTransDataService;

    @Resource
    OfcIntService ofcIntService;

    public void updateRefundFundTransOrder(Date curDate, FundTransOrder transOrder, String productName) {
        // 业务单更新, 不记录出款单
        FundTransOrder targetOrder = new FundTransOrder();
        targetOrder.setUpdateTime(curDate);
        targetOrder.setStatus(transOrder.getStatus());

        //退款成功更新退款成功时间
        if (transOrder.getStatus() == FundTransOrderStatus.REFUND_SUC) {
            targetOrder.setRefundTime(curDate);
            targetOrder.setFinishTime(curDate);
        }
        FundTransOrderExample example = new FundTransOrderExample();
        FundTransOrderExample.Criteria criteria = example.createCriteria();
        criteria.andIdEqualTo(transOrder.getId());
        List<Integer> statusList = new ArrayList<>();
        statusList.add(FundTransOrderStatus.REFUNDING);
        statusList.add(FundTransOrderStatus.REFUND_FAILED);
        criteria.andStatusIn(statusList);
        int effectedRow = fundTransOrderMapper.updateByExampleSelective(targetOrder, example);
        if (effectedRow == 1) {
            fundTransDataService.createFundTransOrderStatusInfo(curDate, transOrder.getStatus(), transOrder.getId());
        }
        else {
            throw new BusinessException(CommonRspCode.DB_ERROR.getMessage(), CommonRspCode.DB_ERROR.getCode());
        }

        ofcIntService.updateBusinessOrderItemStatusAsync(transOrder, transOrder.getStatus(), productName);
    }
}
