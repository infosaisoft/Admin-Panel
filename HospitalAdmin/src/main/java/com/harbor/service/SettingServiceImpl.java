package com.harbor.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.harbor.bo.SettingsBo;
import com.harbor.common.CustomIdGenerator;
import com.harbor.dao.SettingsDao;
import com.harbor.dto.SettingsDto;

@Service
public class SettingServiceImpl implements SettingService {

	@Autowired
	SettingsDao sdao;
	
	@Override
	public String insertSetting(SettingsDto setdto) {
		
		SettingsBo setbo = null;
		Long setid;
		int count = 0;
		
		// copy dto to bo
		setbo = new SettingsBo();
		setid = CustomIdGenerator.getID();
		String set_id = String.valueOf(setid);
		set_id = "SET-"+set_id;
		
		BeanUtils.copyProperties(setdto, setbo);
		setbo.setSettings_id(set_id);
		
		// Use DAO
		count = sdao.insertSettings(setbo);
		
		if(count==0) {
			return "failed";
		}
		return "success";
		
	}

	@Override
	public SettingsDto fetchSet(String hid) {
		
		SettingsDto setdto = null;
		SettingsBo setbo = null;
		
		//use DAo
		setbo = sdao.fetchSettings(hid);
		
		// copy bo to dto
		setdto = new SettingsDto();
		BeanUtils.copyProperties(setbo, setdto);
				
		return setdto;
	}

}
