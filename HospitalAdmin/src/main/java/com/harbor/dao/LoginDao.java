package com.harbor.dao;

import com.harbor.bo.LoginBo;
import com.harbor.bo.UserBo;

public interface LoginDao {
	public int loginUser(LoginBo loginbo);
	public int updateLogoutTime(String session_id);
	
	public UserBo getUserDetalis(String username);
}
