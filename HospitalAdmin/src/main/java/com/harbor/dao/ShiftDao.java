package com.harbor.dao;

import java.util.List;

import com.harbor.bo.ShiftBo;

public interface ShiftDao {
	
	public int insertShift(ShiftBo shiftbo);
	public List<ShiftBo> getAllShifts(String hid);
	public int deleteShift(String shift_id);
	
}
