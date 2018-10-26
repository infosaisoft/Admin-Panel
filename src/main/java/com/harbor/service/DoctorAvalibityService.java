package com.harbor.service;

import java.util.List;

import com.harbor.dto.DoctorAvaliblityDto;
import com.harbor.dto.SettingsDto;
import com.harbor.dto.ShiftDto;
import com.harbor.dto.SlotDto;

public interface DoctorAvalibityService {

	public String registration(DoctorAvaliblityDto docdto);
	
	public List<DoctorAvaliblityDto> featchAllAppotiment(long hid);
	
	
	public String remooveAvaliblity(String avail_id);
	
	
	public ShiftDto viewShifByName(long hid,String shift_name);
	
	
	
	public String regisrationSlot(SlotDto slotdto);
	
	
}
