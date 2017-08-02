package com.eif.ftc.facade;

import com.eif.framework.common.utils.pkg.BaseResponse;

/**
 * @tag 超时扫描接口
 */
public interface FundOrderScanJobFacade {
    /**
     * @brief 订单超时扫描
     * @tag JOB使用
     * @return
     */
    BaseResponse orderExpiredScan();
    
    /**
     * @brief 二级市场订单超时扫描
     * @tag JOB使用
     * @return
     */
    BaseResponse transferOrderExpiredScan();
    
}
