package com.harbor.dao;

import java.util.List;

import com.harbor.bo.LoginHistoryBo;

public interface LoginHistoryDao {
	
	public List<LoginHistoryBo> getLoginHistory(String admin_id);
	
}
