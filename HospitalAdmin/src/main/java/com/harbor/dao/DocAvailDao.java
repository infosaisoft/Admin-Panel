package com.harbor.dao;

import java.util.List;

import com.harbor.bo.HospitalBo;
import com.harbor.bo.UserBo;

public interface DocAvailDao {

	public List<UserBo> getAllDoctor(long hid);
}
