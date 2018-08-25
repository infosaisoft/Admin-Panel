package com.harbor.dao;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.harbor.bo.LoginBo;
import com.harbor.common.CustomIdGenerator;

@Repository
public class LoginDaoImpl implements LoginDao {

	private static final String LOGINUSER = "SELECT COUNT(*) FROM hospital_admin WHERE username=? AND password=?";
	
	private static final String UPDATELOGINTIME="UPDATE hospital_admin SET last_login=? WHERE admin_id=?"; 

	private static final String GETADMINID = "SELECT admin_id FROM hospital_admin  WHERE username=? AND password=?";
	private static final String GETHID = "SELECT hid FROM hospital_admin  WHERE username=? AND password=?";

	@Autowired
	JdbcTemplate jt;

	public int loginUser(LoginBo loginbo) {
		int count = 0;
		CustomIdGenerator cg=null;
		String adminid = null, hid = null;
		cg=new CustomIdGenerator();
		String pass=cg.generateHash(loginbo.getPassword());
		count = jt.queryForObject(LOGINUSER, Integer.class, loginbo.getUsername(), pass);

		if (count == 1) {
			adminid = jt.queryForObject(GETADMINID, String.class, loginbo.getUsername(), pass);
			hid = jt.queryForObject(GETHID, String.class, loginbo.getUsername(), pass);
			   int up  = jt.update(UPDATELOGINTIME, new Date(),adminid);
			loginbo.setAdmin_id(adminid);
			loginbo.setHid(hid);

		}

		System.out.println(loginbo.getAdmin_id());
		return count;
	}

}
