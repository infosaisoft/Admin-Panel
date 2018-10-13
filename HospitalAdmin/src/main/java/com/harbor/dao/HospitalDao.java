package com.harbor.dao;

import com.harbor.bo.HospitalBo;

public interface HospitalDao {
	
	public HospitalBo gethospital(long hid);
	
	public HospitalBo getHosptialById(long hid);
	
	
	public int updateHospital(HospitalBo hbo);

}
