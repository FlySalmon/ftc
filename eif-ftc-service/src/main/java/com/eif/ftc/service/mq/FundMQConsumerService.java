package com.eif.ftc.service.mq;

import com.eif.inspection.facade.mq.MQInsHandleDataInfo;
import com.eif.setting.facade.request.CusTransResultRequest;
import com.eif.setting.facade.service.dto.CusTransResponseDto;
import com.eif.transcore.facade.mq.MQTransInfoBean;
import com.eif.transcore.facade.response.CreateTransResponse;

import java.util.List;

/**
 * Created by bohan on 9/29/16.
 */
public interface FundMQConsumerService {

	/**
	 * 交易单创建结果处理
	 * @param message
	 */
    void consumeTransCoreCreateMessage(CreateTransResponse message);
    
    void consumeTransCoreStatusMessage(MQTransInfoBean message);
    void consumeSettingsMessage(CusTransResultRequest message);
    void consumeSettingCreateOrderResponse(List<CusTransResponseDto> cusTransResponseDtoList);

    void consumeInspectionInventoryDiffMessage(MQInsHandleDataInfo message);
}
