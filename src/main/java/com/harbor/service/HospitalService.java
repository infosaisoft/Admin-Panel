package com.harbor.service;


import java.util.List;

import com.harbor.dto.HospitalDto;

public interface HospitalService {
	
	public HospitalDto featchHospitalInfo(long hid);
	public List<String>getAllFile();
	
	public HospitalDto featchRecordBYId(long hid);
	
	public String modifyHospital(HospitalDto hdto);

}
