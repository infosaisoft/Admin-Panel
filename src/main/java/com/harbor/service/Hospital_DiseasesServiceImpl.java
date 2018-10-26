package com.harbor.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.harbor.bo.Hospital_DiseasesBo;
import com.harbor.common.CustomIdGenerator;
import com.harbor.dao.Hospital_DiseasesDao;
import com.harbor.dto.Hospital_Diseases_Dto;

@Service
public class Hospital_DiseasesServiceImpl implements Hospital_DiseasesService{
	
	@Autowired
	Hospital_DiseasesDao dao;
	
	@Override
	public String registrationHospital_Diseases(Hospital_Diseases_Dto dto) {
		Hospital_DiseasesBo bo = null;
		String hdis_id = null;
		int count = 0;
		
		bo = new Hospital_DiseasesBo();
		// convert dto to bo
		BeanUtils.copyProperties(dto, bo);

		// use Dao
		count = dao.insertHospital(bo);

		if (count == 0) {
			return "Failed";
		} else {
			return "Success";
		}
	}

	@Override
	public List<Hospital_Diseases_Dto> fetchall() {
		List<Hospital_DiseasesBo> listbo = null;

		List<Hospital_Diseases_Dto> listdto = new ArrayList<Hospital_Diseases_Dto>();
		listbo = dao.getAllHospitals();
		listbo.forEach(bo -> {
			Hospital_Diseases_Dto dto = new Hospital_Diseases_Dto();
			BeanUtils.copyProperties(bo, dto);
			listdto.add(dto);
		});
		return listdto;
	}

	@Override
	public Hospital_Diseases_Dto fetchbysno(long sno) {
		Hospital_DiseasesBo bo = null;
		Hospital_Diseases_Dto dto = null;

		// use dao
		bo = dao.getHospitalsBysno(sno);
		// convert bo to dto
		dto = new Hospital_Diseases_Dto();
		BeanUtils.copyProperties(bo, dto);
		return dto;
	}

	@Override
	public String removeHospital_Diseases(long sno) {
		int count = 0;
		// use dao
		count = dao.deleteHospital(sno);
		if (count == 0) {
			return sno + "hospital was  not delete";
		}
		return sno + "hospital was   delete";
	}

	@Override
	public String modifyHospital_Diseases(Hospital_Diseases_Dto dto) {
		Hospital_DiseasesBo bo = null;
		int count = 0;
		// copy dto to bo
		bo = new Hospital_DiseasesBo();
		BeanUtils.copyProperties(dto, bo);
		count = dao.UpdateHospital(bo);
		if (count == 0) {
			return "not update";
		}
		return "update sucesfull";
	}

}
