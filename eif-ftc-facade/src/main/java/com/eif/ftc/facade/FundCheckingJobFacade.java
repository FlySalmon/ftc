package com.eif.ftc.facade;

import com.eif.framework.common.utils.pkg.page.PageableResponse;
import com.eif.ftc.facade.dto.FundClearingOrderBean;
import com.eif.ftc.facade.dto.FundTransOrderBean;
import com.eif.ftc.facade.fund.job.request.GetFundTransOrderListForCheckingRequest;
import com.eif.ftc.facade.fund.job.request.GetFundTransOrdersForCheckingPageableRequest;
import com.eif.ftc.facade.fund.job.request.QueryByFundClearingOrderNosRequest;
import com.eif.ftc.facade.fund.job.response.GetFundTransOrderListForCheckingResponse;
import com.eif.ftc.facade.fund.job.response.QueryByFundClearingOrderNosResponse;
import com.eif.ftc.facade.fund.oper.request.FundClearingOrderRequest;

/**
 * @tag FTC-JOB接口
 */
public interface FundCheckingJobFacade {

   /**
    * @brief 根据时间分页获取业务单
    * @param request 分页获取业务单请求
    * @return 业务单信息
    * @author Matt
    * @version 1.0.0
    * @memo init add api
    */
   PageableResponse<FundTransOrderBean> getFundTransOrdersForCheckingByPage(GetFundTransOrdersForCheckingPageableRequest request);


   /**
    * @brief 批量获取业务单
    * @param request 批量获取业务单请求
    * @return 业务单列表
    * @author Matt
    * @version 1.0.0
    * @memo init add api
    */
   GetFundTransOrderListForCheckingResponse getFundTransOrderListForChecking(GetFundTransOrderListForCheckingRequest request);

   /**
    * @brief 分页获取交易成功的业务单
    * @param request 获取交易成功业务单请求
    * @return 业务单列表
    * @author Matt
    * @version 1.0.0
    * @memo init add api
    */
   PageableResponse<FundTransOrderBean> getSucceedFundTransOrdersForCheckingByPage(GetFundTransOrdersForCheckingPageableRequest request);

   /**
    * @brief 批量获取清盘业务
    * @param request 批量获取清盘业务单请求
    * @return 清盘单列表
    * @author Matt
    * @version 1.0.0
    * @memo init add api
    */
   QueryByFundClearingOrderNosResponse getFundClearingOrderByOrderNosForChecking(QueryByFundClearingOrderNosRequest request);

   /**
    * @brief 批量获取清盘业务单
    * @param request 用户与资产号请求
    * @return 清盘单列表
    * @author Matt
    * @version 1.0.0
    * @memo init add api
    */
   PageableResponse<FundClearingOrderBean> getFundClearingOrdersByPageForChecking(FundClearingOrderRequest request);
   
}
