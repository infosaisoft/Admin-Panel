package com.harbor.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.harbor.bo.UserBo;
import com.harbor.dao.DocAvailDao;
import com.harbor.dto.UserDto;

@Service
public class DocAvailServiceImpl implements DocAvailService {
	
	@Autowired
	DocAvailDao docdao;
	

	@Override
	public List<UserDto> featchRole(String hid) {
	
		List<UserDto>listdto=new ArrayList<>();
		List<UserBo>listbo=null;
		
		//use service
		listbo=docdao.getAllDoctor(hid);
		
		listbo.forEach(bo->{
			UserDto dto=new UserDto();
			BeanUtils.copyProperties(bo, dto);
			System.out.println("doc service:::"+dto.getFname());
			listdto.add(dto);
		});
		return listdto;
	}

}
