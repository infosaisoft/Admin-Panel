package com.harbor.dao;

import java.util.List;

import com.harbor.bo.DepartmentBo;

public interface DepartmentDao {
	
	public int insertDepartment(DepartmentBo deptbo);
	
	public List<DepartmentBo> getAllDepartment();
}
