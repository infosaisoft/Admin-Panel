package com.harbor.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.harbor.bo.Hospital_Dos_DontsBo;

import com.harbor.dao.Hospital_Dos_DontsDao;
import com.harbor.dto.Hospital_Dos_DontsDto;

@Service
public class Hospital_Dos_DontsServiceImpl implements Hospital_Dos_DontsService {

	@Autowired
	Hospital_Dos_DontsDao dao;

	@Override
	public String registrationHospital_Dos_Donts(Hospital_Dos_DontsDto dto) {
		Hospital_Dos_DontsBo bo = null;
		
		int count = 0;
		
		bo = new Hospital_Dos_DontsBo();
		// convert dto to bo
		BeanUtils.copyProperties(dto, bo);

		// use Dao
		count = dao.insertHospital_Dos_Donts(bo);

		if (count == 0) {
			return "Failed";
		} else {
			return "Success";
		}
	}

	@Override
	public List<Hospital_Dos_DontsDto> fetchall() {
		List<Hospital_Dos_DontsBo> listbo = null;

		List<Hospital_Dos_DontsDto> listdto = new ArrayList<Hospital_Dos_DontsDto>();
		listbo = dao.selectAll();
		listbo.forEach(bo -> {
			Hospital_Dos_DontsDto dto = new Hospital_Dos_DontsDto();
			BeanUtils.copyProperties(bo, dto);
			listdto.add(dto);
		});
		return listdto;
	}

	@Override
	public Hospital_Dos_DontsDto fetchbysno(long id) {
		Hospital_Dos_DontsBo bo = null;
		Hospital_Dos_DontsDto dto = null;

		// use dao
		bo = dao.getBySno(id);
		// convert bo to dto
		dto = new Hospital_Dos_DontsDto();
		BeanUtils.copyProperties(bo, dto);
		return dto;
	}

	@Override
	public String removeHospital_Dos_Donts(long id) {
		int count = 0;
		// use dao
		count = dao.deleteHospital_Dos_DontsBo(id);
		if (count == 0) {
			return id + "hospital was not delete";
		}
		return id + "hospital was delete";
	}

	@Override
	public String modifyHospital_Dos_Donts(Hospital_Dos_DontsDto dto) {
		Hospital_Dos_DontsBo bo = null;
		int count = 0;
		// copy dto to bo
		bo = new Hospital_Dos_DontsBo();
		BeanUtils.copyProperties(dto, bo);
		count = dao.updateHospital_Dos_Donts(bo);
		if (count == 0) {
			return "not update";
		}
		return "update sucesfull";
	}
}
