package com.harbor.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.harbor.bo.DepartmentBo;
import com.harbor.common.CustomIdGenerator;
import com.harbor.dao.DepartmentDao;
import com.harbor.dto.DepartmentDto;

@Service
public class DepartmentServiceImpl implements DepartmentService {

	@Autowired
	DepartmentDao dptDao;
	public String registrationDepartment(DepartmentDto deptdto) {
		DepartmentBo dptbo=null;
		long dptid;
		int count=0;
		//copy dto to bo
		dptbo=new DepartmentBo();	

		BeanUtils.copyProperties(deptdto, dptbo);
	
		//use dao
		count=dptDao.insertDepartment(dptbo);
		
		if(count==0) {
			return "not insert";
		}
		return "insert";
	}
	
	
	
	@Override
	public List<DepartmentDto> featchAllDepartment(long hid) {
		List<DepartmentDto>listdto=new ArrayList<>();
		List<DepartmentBo>listbo=null;
		
		//use dao
		listbo=dptDao.getAllDepartment(hid);
		
		listbo.forEach(bo->{
			
			DepartmentDto dptdto=new DepartmentDto();
			
			BeanUtils.copyProperties(bo, dptdto);
			
			listdto.add(dptdto);
			
		});
		return listdto;
	}
	
	
	@Override
	public String removeDept(long dpt_id) {
	      int count=0;
	      
	      //use dao
	      count=dptDao.deleteDapartment(dpt_id);
	      if(count==0) {
	    	  return "fail";
	      }
		return "delete";
	}

}


