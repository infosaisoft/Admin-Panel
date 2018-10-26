package com.harbor.service;

import java.sql.SQLException;
import java.util.List;

import com.harbor.common.SpringException;
import com.harbor.dto.ShiftDto;

public interface ShiftService {
	
	public String insertShift(ShiftDto shiftdto)throws SpringException,SQLException;
	public List<ShiftDto> fetchAllShifts(long hid);
	public String removeShift(String shift_id);
	
}
