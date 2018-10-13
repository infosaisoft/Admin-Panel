package com.harbor.dao;

import java.util.List;

import com.harbor.bo.DoctorAvaliblityBo;
import com.harbor.bo.SettingsBo;
import com.harbor.bo.ShiftBo;

public interface DoctorAvaliblityDao {

	public int insertAvaliblity(DoctorAvaliblityBo docbo);
	
	public List<DoctorAvaliblityBo> getAllAvaliblity(long hid);
	
	public int deleteAvaliblity(String avail_id);
	
	public ShiftBo getShiftByName(long hid,String shift_name);
	
	
	public SettingsBo getDurationPatient(long hid);
}
