package com.harbor.dao;

import java.util.List;

import com.harbor.bo.DoctorAvaliblityBo;

public interface DoctorAvaliblityDao {

	public int insertAvaliblity(DoctorAvaliblityBo docbo);
	
	public List<DoctorAvaliblityBo> getAllAvaliblity(String hid);
	
	
	public int deleteAvaliblity(String avail_id);
}
