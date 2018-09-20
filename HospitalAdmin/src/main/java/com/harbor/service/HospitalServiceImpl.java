package com.harbor.service;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.harbor.bo.HospitalBo;
import com.harbor.dao.HospitalDao;
import com.harbor.dto.HospitalDto;

@Service
public class HospitalServiceImpl implements HospitalService {

	@Autowired
	HospitalDao hdao;

	private static final String DOWNLOADFLODER = "D:\\Hospital-Admin\\Hospital-Admin\\HospitalAdmin\\src\\main\\webapp\\assets\\images\\hospital";

	@Override
	public HospitalDto featchHospitalInfo(String hid) {
		HospitalBo hbo = null;
		HospitalDto hdto = null;
		// use dao
		hbo = hdao.gethospital(hid);

		// copy hbo to hdto
		hdto = new HospitalDto();
		BeanUtils.copyProperties(hbo, hdto);
		return hdto;
	}

	@Override
	public List<String> getAllFile() {
		File folder = null;
		folder = new File(DOWNLOADFLODER);

		File files[] = null;
		List<String> list = null;

		// get all file
		if (folder.isDirectory()) {
			files = folder.listFiles();
		}
		// copy file to collection list
		list = new ArrayList<>();
		for (File fname : files) {
			if (!fname.isDirectory()) {
				list.add(fname.getName());
			}
		}
		return list;
	}

	
	
	@Override
	public HospitalDto featchRecordBYId(String hid) {
		HospitalDto hdto=null;
		HospitalBo hbo=new HospitalBo();
		
		//use dao
		hbo=hdao.gethospital(hid);
		
		//copy bo to dto
		hdto=new HospitalDto();
		BeanUtils.copyProperties(hbo, hdto);
	
		return hdto;
	}
	
	@Override
	public String modifyHospital(HospitalDto hdto) {
		HospitalBo bo=null;
		int count=0;
		//copy bo to dto
		bo=new HospitalBo();
		BeanUtils.copyProperties(hdto, bo);
		
		//use dao
		count=hdao.updateHospital(bo);
		
		if(count==0) {
			return "fail";
		}
		
		return "success";
	}
}
