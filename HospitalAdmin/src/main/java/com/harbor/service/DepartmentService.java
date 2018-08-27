package com.harbor.service;

import java.util.List;

import com.harbor.dto.DepartmentDto;

public interface DepartmentService {
	
	public String registrationDepartment(DepartmentDto deptdto);
	
	//public List<DepartmentDto> featchAllDepartment();
	
	public String removeDept(String dpt_id);

	public List<DepartmentDto> featchAllDepartment(String hid);


}
