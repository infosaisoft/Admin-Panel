package com.harbor.service;

import com.harbor.dto.LoginDto;

public interface LoginService {
	public String verifyUser(LoginDto logindto);
	public String modifyLogoutTime(String session_id);
}
