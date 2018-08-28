package com.harbor.service;

import java.util.List;

import com.harbor.dto.DoctorAvaliblityDto;

public interface DoctorAvalibityService {

	public String registration(DoctorAvaliblityDto docdto);
	
	public List<DoctorAvaliblityDto> featchAllAppotiment(String hid);
	
	
	public String remooveAvaliblity(String avail_id);
}
