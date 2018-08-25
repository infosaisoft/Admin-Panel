package com.harbor.service;


import java.util.List;

import com.harbor.dto.UserDto;

public interface UserService {
	public List<UserDto> getUser(String hid);
	public String insertUser(UserDto userdto);
	
	public String removeUser(String admin_id);
}
