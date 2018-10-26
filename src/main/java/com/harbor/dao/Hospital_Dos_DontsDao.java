package com.harbor.dao;

import java.util.List;

import com.harbor.bo.Hospital_Dos_DontsBo;

public interface Hospital_Dos_DontsDao {

	public int insertHospital_Dos_Donts(Hospital_Dos_DontsBo bo);

	public List<Hospital_Dos_DontsBo> selectAll();

	public Hospital_Dos_DontsBo getBySno(long id);

	public int updateHospital_Dos_Donts(Hospital_Dos_DontsBo bo);

	public int deleteHospital_Dos_DontsBo(long id);

}
