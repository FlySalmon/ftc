package com.eif.ftc.service.trans;

import com.eif.framework.pagination.pagehelper.PageInfo;
import com.eif.ftc.dal.model.FundTransOrder;

import java.util.Date;

/**
 * Created by bohan on 9/29/16.
 */
public interface FundOrderExpiredService {
    void orderExpiredScan();
    PageInfo<FundTransOrder> processByPage(int pageNum, int pageSize, Date curDate, boolean isCount);
}
