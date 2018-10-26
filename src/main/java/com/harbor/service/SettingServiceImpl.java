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
		int count = 0;
		
		// copy dto to bo
		setbo = new SettingsBo();
	
		
		BeanUtils.copyProperties(setdto, setbo);
		
		// Use DAO
		count = sdao.insertSettings(setbo);
		
		if(count==0) {
			return "failed";
		}
		return "success";
		
	}

	@Override
	public SettingsDto fetchSet(long hid) {
		
		SettingsDto setdto = null;
		SettingsBo setbo = null;
		
		//use DAo
		setbo = sdao.fetchSettings(hid);
		
		   if(setbo==null) {
			   setbo=new SettingsBo();
			  setbo.setMax_patient("0");
			  setbo.setSlot_duration("0");
		   }
		
		// copy bo to dto
		setdto = new SettingsDto();
		BeanUtils.copyProperties(setbo, setdto);
				
		return setdto;
	}

}
