package com.eif.ftc.integration.setting.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.eif.ftc.integration.mq.MQMessageSender;
import com.eif.ftc.integration.setting.SettingIntegrationService;
import com.eif.setting.facade.request.BatchCusTransRequest;
import com.eif.setting.facade.service.dto.CusTransRequestDto;

/**
 * @description Setting操作接口实现类
 * @author jiangweifeng
 *
 */
@Service("settingIntService")
public class SettingIntegrationServiceImpl implements SettingIntegrationService {

    @Autowired
    private MQMessageSender mqMessageSender;
	
	@Override
	public void createSettingOrderAsync(CusTransRequestDto cusTransRequestDto) {
		List<CusTransRequestDto> cusTransRequestDtoList = new ArrayList<>();
		cusTransRequestDtoList.add(cusTransRequestDto);
		createSettingOrderAsync(cusTransRequestDtoList);
	}

	@Override
	public void createSettingOrderAsync(List<CusTransRequestDto> cusTransRequestDtoList) {
		if (CollectionUtils.isEmpty(cusTransRequestDtoList)) 
			return;
		
		BatchCusTransRequest request = new BatchCusTransRequest();
		request.setCusTransRequestDtoList(cusTransRequestDtoList);
		mqMessageSender.asyncCreateSettingOrder(request);
	}

}
