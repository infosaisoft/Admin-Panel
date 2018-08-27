package com.harbor.service;

import com.harbor.dto.SettingsDto;

public interface SettingService {
	
	public String insertSetting(SettingsDto setdto);
	public SettingsDto fetchSet(String hid);
	
}
