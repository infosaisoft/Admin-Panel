package com.harbor.dao;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.harbor.bo.LoginBo;
import com.harbor.common.CustomIdGenerator;

@Repository
public class LoginDaoImpl implements LoginDao {

	private static final String LOGINUSER = "SELECT COUNT(*) FROM  USERS WHERE username=? AND password=?";

//	private static final String UPDATELOGINTIME = "UPDATE hospital_admin SET last_login=? WHERE admin_id=?";
	private static final String GET_USER="SELECT id FROM  USERS WHERE username=? AND password=?";
	private static final String GET_HOSPITAL="SELECT hospital_id FROM hospital_staff  WHERE user_id=?";

	// history table query
	private static final String INSERT_HISTORY="INSERT INTO login_history(lid,session_id,hid,admin_id,login_time,logout_time) VALUES(?,?,?,?,?,?)";

	private static final String UPDATE_HISTORY="UPDATE login_history SET logout_time=? WHERE session_id=?";

	@Autowired
	JdbcTemplate jt;

	public int loginUser(LoginBo loginbo) 
	   {
		int count = 0;
		int h1 = 0;
		CustomIdGenerator cg = null;
		long user_id=0,hospital_id=0;
		cg = new CustomIdGenerator();
		count = jt.queryForObject(LOGINUSER, Integer.class, loginbo.getUsername(), loginbo.getPassword());
        if(count==1) {
			user_id=jt.queryForObject(GET_USER,Long.class,loginbo.getUsername(),loginbo.getPassword());
			hospital_id = jt.queryForObject(GET_HOSPITAL,Long.class,user_id);
			/*int up = jt.update(UPDATELOGINTIME, new Date(), adminid);
			loginbo.setAdmin_id(adminid);
			loginbo.setHid(hid);
			h1 = jt.update(INSERT_HISTORY, loginbo.getLid(), loginbo.getSession_id(), loginbo.getHid(), adminid,
					loginbo.getLogin_time(), null);

*/	            
			  System.out.println(user_id);
                               
                            loginbo.setAdmin_id(user_id);
                   			loginbo.setHid(hospital_id);
                   			
		}

		return count;
	}

	@Override
	public int updateLogoutTime(String session_id) {
		/*int count = 0;
		count = jt.update(UPDATE_HISTORY, new Date(), session_id);*/
		return 0;
	}

}
