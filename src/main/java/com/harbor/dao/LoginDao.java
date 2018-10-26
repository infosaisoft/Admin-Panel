package com.harbor.dao;

import com.harbor.bo.LoginBo;

public interface LoginDao {
	public int loginUser(LoginBo loginbo);
	public int updateLogoutTime(String session_id);
}
