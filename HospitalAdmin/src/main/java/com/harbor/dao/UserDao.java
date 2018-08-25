package com.harbor.dao;

import java.util.List;

import com.harbor.bo.UserBo;

public interface UserDao {
	
	public List<UserBo> getUser(String hid);
	
	public int insertUser(UserBo userbo);
	
	public int deleteUsert(String admin_id);

}
