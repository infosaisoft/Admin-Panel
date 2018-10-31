package com.harbor.service;

import java.util.List;

import com.harbor.dto.Hospital_Dos_DontsDto;

public interface Hospital_Dos_DontsService {
	
	public String registrationHospital_Dos_Donts(Hospital_Dos_DontsDto dto);

	public List<Hospital_Dos_DontsDto> fetchall();

	public Hospital_Dos_DontsDto fetchbysno(long id);

	public String removeHospital_Dos_Donts(long id);

	public String modifyHospital_Dos_Donts(Hospital_Dos_DontsDto dto);

}
