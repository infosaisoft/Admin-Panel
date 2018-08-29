package com.harbor.dao;

import com.harbor.bo.HospitalBo;

public interface HospitalDao {
	
	public HospitalBo gethospital(String hid);
	
	public HospitalBo getHosptialById(String hid);
	
	
	public int updateHospital(HospitalBo hbo);

}
