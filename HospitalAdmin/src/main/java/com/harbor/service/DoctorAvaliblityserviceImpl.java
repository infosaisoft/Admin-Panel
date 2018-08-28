package com.harbor.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.harbor.bo.DoctorAvaliblityBo;
import com.harbor.common.CustomIdGenerator;
import com.harbor.dao.DoctorAvaliblityDao;
import com.harbor.dto.DoctorAvaliblityDto;

@Service
public class DoctorAvaliblityserviceImpl implements DoctorAvalibityService {

	@Autowired
	DoctorAvaliblityDao docdao;

	@Override
	public String registration(DoctorAvaliblityDto docdto) {
		DoctorAvaliblityBo docbo = null;
		int count = 0;
		String avail_id = null;

		avail_id = String.valueOf(CustomIdGenerator.getID());
		avail_id = "AVA-" + avail_id;

		docdto.setAvail_id(avail_id);

		// copy dto bo
		docbo = new DoctorAvaliblityBo();
		BeanUtils.copyProperties(docdto, docbo);

		// use dao
		count = docdao.insertAvaliblity(docbo);

		if (count == 0) {
			return "fail";
		}
		return "success";
	}

	@Override
	public List<DoctorAvaliblityDto> featchAllAppotiment(String hid) {
		List<DoctorAvaliblityDto> listdto = new ArrayList<>();
		List<DoctorAvaliblityBo> listbo = null;

		// use dao
		listbo = docdao.getAllAvaliblity(hid);

		listbo.forEach(bo -> {
			DoctorAvaliblityDto dto = new DoctorAvaliblityDto();

			BeanUtils.copyProperties(bo, dto);

			listdto.add(dto);

		});

		return listdto;
	}

	@Override
	public String remooveAvaliblity(String avail_id) {
		int count = 0;

		// use dao
		count = docdao.deleteAvaliblity(avail_id);

		if (count == 0) {
			return "fail";
		}

		return "success";
	}
}
