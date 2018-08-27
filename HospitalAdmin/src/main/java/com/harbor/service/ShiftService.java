package com.harbor.service;

import java.util.List;

import com.harbor.dto.ShiftDto;

public interface ShiftService {
	
	public String insertShift(ShiftDto shiftdto);
	public List<ShiftDto> fetchAllShifts(String hid);
	public String removeShift(String shift_id);
	
}
