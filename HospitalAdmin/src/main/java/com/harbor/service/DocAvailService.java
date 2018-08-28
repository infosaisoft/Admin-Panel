package com.harbor.service;

import java.util.List;

import com.harbor.dto.UserDto;

public interface DocAvailService {

	public List<UserDto> featchRole(String hid);
}
