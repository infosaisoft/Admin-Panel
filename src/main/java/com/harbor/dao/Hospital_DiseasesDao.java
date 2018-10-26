package com.harbor.dao;

import java.util.List;

import com.harbor.bo.Hospital_DiseasesBo;

public interface Hospital_DiseasesDao {
	
	public int insertHospital(Hospital_DiseasesBo bo);
	
	public List<Hospital_DiseasesBo> getAllHospitals();

	public Hospital_DiseasesBo getHospitalsBysno(long sno);

	public int deleteHospital(long sno);

	public int UpdateHospital(Hospital_DiseasesBo bo);

}
