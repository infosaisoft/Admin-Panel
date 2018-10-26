package com.harbor.service;

import java.util.List;

import com.harbor.dto.LoginHistoryDto;

public interface LoginHistoryService {
	
	public List<LoginHistoryDto> fetchLoginHistory(String admin_id);
	
}
