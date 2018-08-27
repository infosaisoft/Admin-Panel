package com.harbor.dao;

import java.util.List;

import com.harbor.bo.UserBo;

public interface UserDao {
	
	public List<UserBo> getUser(String hid);
	
	public int insertUser(UserBo userbo);
	
	public int deleteUsert(String admin_id);
	
	public long totalRecordsCount();
	
	public List<UserBo> reportdata(int startpos,int pagesize);
	
	
	public UserBo getUserboById(String admin_id);
	
	public int updateUserBoById(UserBo bo);

}
