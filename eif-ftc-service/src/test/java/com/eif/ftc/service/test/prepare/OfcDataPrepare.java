package com.eif.ftc.service.test.prepare;

import com.eif.framework.common.utils.code.CommonRspCode;
import com.eif.ftc.dal.model.FundTransOrder;
import com.eif.ftc.integration.member.MemberIntService;
import com.eif.ftc.integration.ofc.OfcIntService;
import com.eif.member.facade.pkg.dto.responseDTO.UserInfoBean;
import com.eif.member.facade.pkg.response.QueryMemberInfoResponse;
import com.eif.ofc.facade.dto.bean.CreateBusinessOrderItemResult;
import com.eif.ofc.facade.response.CreateOrderItemResponse;
import org.mockito.Mockito;

/**
 * Created by bohan on 10/7/16.
 */
public class OfcDataPrepare {

    public static void buildCreateBusinessOrderItemForSuccess(OfcIntService ofcIntService) {
        CreateOrderItemResponse createOrderItemResponse = new CreateOrderItemResponse();
        createOrderItemResponse.setMsg(CommonRspCode.SUCCESS.getMessage());
        createOrderItemResponse.setRespCode(CommonRspCode.SUCCESS.getCode());
        CreateBusinessOrderItemResult createBusinessOrderItemResult = new CreateBusinessOrderItemResult();
        createBusinessOrderItemResult.setBusinessOrderItemNo("111111");
        createOrderItemResponse.setResult(createBusinessOrderItemResult);
        Mockito.doReturn(createOrderItemResponse).when(ofcIntService).createBusinessOrderItem(Mockito.any(FundTransOrder.class), Mockito.anyInt(),Mockito.anyString(),Mockito.anyString(),Mockito.anyString());
    }

}
