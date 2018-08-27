package com.harbor.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.harbor.bo.ShiftBo;
import com.harbor.common.CustomIdGenerator;
import com.harbor.dao.ShiftDao;
import com.harbor.dto.ShiftDto;

@Service
public class ShiftServiceImpl implements ShiftService {
	
	@Autowired
	ShiftDao shiftdao;
	
	@Override
	public String insertShift(ShiftDto shiftdto) {
		
		ShiftBo shtbo = null;		
		Long shtid;
		int count = 0;
		
		// copy dto to bo
		shtbo = new ShiftBo();
		shtid = CustomIdGenerator.getID();
		String sht_id = String.valueOf(shtid);
		sht_id = "SHD-"+sht_id;
		BeanUtils.copyProperties(shiftdto, shtbo);
		shtbo.setShift_id(sht_id);
		
		
		// Use Dao
		count = shiftdao.insertShift(shtbo);
		
		if(count==0) {
			return "failed";
		}
		return "success";
		
	}

	@Override
	public List<ShiftDto> fetchAllShifts(String hid) {
		
		List<ShiftDto> shiftdto = new ArrayList<>();
		List<ShiftBo> shiftbo = null;
		
		System.out.println("service::"+hid);
		// use dao
		shiftbo = shiftdao.getAllShifts(hid);
		
		shiftbo.forEach(bo->{
		      	
			ShiftDto sdto = new ShiftDto();
			BeanUtils.copyProperties(bo, sdto);
			bo.setHid(sdto.getHid());
			shiftdto.add(sdto);
			
		});		
		
		return shiftdto;
	}

	@Override
	public String removeShift(String shift_id) {
		
		int count=0;
		
		//use dao
		count=shiftdao.deleteShift(shift_id);
		
		if(count==0) {			
			return "failed";
		}	
		
		return "delete successfull";
	}

}
