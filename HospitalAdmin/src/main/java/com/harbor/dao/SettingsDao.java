package com.harbor.dao;

import com.harbor.bo.SettingsBo;

public interface SettingsDao {
	
	public int insertSettings(SettingsBo setbo);
	public SettingsBo fetchSettings(long hid);
	
}
