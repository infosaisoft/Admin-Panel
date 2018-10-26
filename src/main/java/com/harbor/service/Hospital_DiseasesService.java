package com.harbor.service;

import java.util.List;

import com.harbor.dto.Hospital_Diseases_Dto;

public interface Hospital_DiseasesService {

	public String registrationHospital_Diseases(Hospital_Diseases_Dto dto);

	public List<Hospital_Diseases_Dto> fetchall();

	public Hospital_Diseases_Dto fetchbysno(long sno);

	public String removeHospital_Diseases(long sno);

	public String modifyHospital_Diseases(Hospital_Diseases_Dto dto);

}
