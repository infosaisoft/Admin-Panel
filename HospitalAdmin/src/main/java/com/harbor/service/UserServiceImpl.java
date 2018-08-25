package com.harbor.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.harbor.bo.UserBo;
import com.harbor.common.CustomIdGenerator;
import com.harbor.dao.UserDao;
import com.harbor.dto.UserDto;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	UserDao userdao;
	
	@Override
	public List<UserDto> getUser(String hid) {
		
		List<UserBo> listuserbo = null;
		List<UserDto> listuserdto = new ArrayList<>();		
		
		//use dao
		listuserbo = userdao.getUser(hid);
		listuserbo.forEach(userbo->{
			
			UserDto dto=new UserDto();
			
			BeanUtils.copyProperties(userbo, dto);
			 listuserdto.add(dto);
		});
		
		  
		
		return listuserdto;
	}

	@Override
	public String insertUser(UserDto userdto) {
		int count = 0;
		String uid = null;
		CustomIdGenerator cg=null;
		String password=null;
		uid = String.valueOf(CustomIdGenerator.getID());
		uid = "AID-"+uid;
		
		cg=new CustomIdGenerator();
		password=cg.generateHash(userdto.getPassword());
		
		// copy dto to bo
		
		UserBo userbo = new UserBo();
		BeanUtils.copyProperties(userdto, userbo);
		userbo.setAdmin_id(uid);
		userbo.setPassword(password);
		
		// use DAO
		
		count = userdao.insertUser(userbo);
		if(count == 0) {
			return "failed";
		}		
		return "success";
	}

	
	
	@Override
	public String removeUser(String admin_id) {
		int count=0;
		
		//use dao
		count=userdao.deleteUsert(admin_id);
		
		if(count==0) {
			
			return "not delete";
		}
		
		
		return "delete";
	}
}
