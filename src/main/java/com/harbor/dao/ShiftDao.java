package com.harbor.dao;

import java.sql.SQLException;
import java.util.List;

import com.harbor.bo.ShiftBo;
import com.harbor.common.SpringException;

public interface ShiftDao {
	
	public int insertShift(ShiftBo shiftbo)throws SpringException,SQLException;
	public List<ShiftBo> getAllShifts(long hid);
	public int deleteShift(String shift_id);
	
	

}
