package com.eif.ftc.integration.setting;

import java.util.List;

import com.eif.setting.facade.service.dto.CusTransRequestDto;

/**
 * @description Setting操作接口
 * @author jiangweifeng
 *
 */
public interface SettingIntegrationService {

	/**
	 * 创建结算单
	 * @param cusTransRequestDto
	 */
	public void createSettingOrderAsync(CusTransRequestDto cusTransRequestDto);
	
	/**
	 * 批量创建结算单
	 * @param cusTransRequestList
	 */
	public void createSettingOrderAsync(List<CusTransRequestDto> cusTransRequestDtoList);
	
}
