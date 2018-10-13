package com.harbor.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.harbor.bo.ShiftBo;
import com.harbor.common.CustomIdGenerator;
import com.harbor.common.SpringException;
import com.harbor.dao.ShiftDao;
import com.harbor.dto.ShiftDto;

@Service
public class ShiftServiceImpl implements ShiftService {
	
	@Autowired
	ShiftDao shiftdao;
	
	@Override
	public String insertShift(ShiftDto shiftdto) throws SpringException,SQLException{
		
		ShiftBo shtbo = null;		

		int count = 0;
		
		// copy dto to bo
		shtbo = new ShiftBo();
		
		BeanUtils.copyProperties(shiftdto, shtbo);
	
		
		// Use Dao
		count = shiftdao.insertShift(shtbo);
		
		if(count==0) {
			return "failed";
		}
		return "success";
		
	}

	@Override
	public List<ShiftDto> fetchAllShifts(long hid) {
		
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
