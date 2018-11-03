package com.harbor.service;

import java.util.Map;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowire;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.harbor.bo.LoginBo;
import com.harbor.bo.UserBo;
import com.harbor.common.CustomIdGenerator;
import com.harbor.dao.LoginDao;
import com.harbor.dto.LoginDto;

@Service
public class LoginServiceImpl implements LoginService {
	
	

	@Autowired
	LoginDao logindao;
	@Autowired
	 BCryptPasswordEncoder encode;

	public String verifyUser(LoginDto logindto) {

		int count = 0;
		LoginBo loginbo = null;
		String userbo=null;
		boolean flage=false;
		
		System.out.println("password::"+logindto.getPassword());
		
		// copy dto to bo
		loginbo = new LoginBo();
		
		BeanUtils.copyProperties(logindto, loginbo);
		
	   userbo=logindao.getUserDetalis(logindto.getUsername());
		//BCryptPasswordEncoder encode=new BCryptPasswordEncoder()
		     flage= encode.matches(loginbo.getPassword(),userbo);
		     System.out.println("ssss"+flage);
		      if(flage) {
		    	  
		    	  count = logindao.loginUser(loginbo);
		  		long admin_id = loginbo.getAdmin_id();
		  		
		  		 
		  		logindto.setAdmin_id(admin_id);
		  		System.out.println(logindto.getAdmin_id());
		  		logindto.setHid(loginbo.getHid());
		  		return "success";
		      }       
					return "failed";
				}
		  		
	
		// Use DAO
		

	

	@Override
	public String modifyLogoutTime(String session_id) {
		int count = 0;

		// use dao
		count = logindao.updateLogoutTime(session_id);
		if (count == 0) {
			return "update";
		}
		return "not update";
	}

}
