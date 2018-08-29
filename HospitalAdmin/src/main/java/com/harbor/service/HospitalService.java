package com.harbor.service;


import java.util.List;

import com.harbor.dto.HospitalDto;

public interface HospitalService {
	
	public HospitalDto featchHospitalInfo(String hid);
	public List<String>getAllFile();
	
	public HospitalDto featchRecordBYId(String hid);
	
	public String modifyHospital(HospitalDto hdto);

}
