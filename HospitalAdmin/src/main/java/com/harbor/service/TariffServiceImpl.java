package com.harbor.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.harbor.bo.TariffBo;
import com.harbor.common.CustomIdGenerator;
import com.harbor.dao.TariffsDao;
import com.harbor.dto.TariffDto;

@Service
public class TariffServiceImpl implements TariffService {

	@Autowired
	TariffsDao tardao;

	@Override
	public String regTraiff(TariffDto tariffdto) {
		TariffBo bo = null;
		int count = 0;
		String tariff_id = null;
		tariff_id = String.valueOf(CustomIdGenerator.getID());
		tariff_id = "TID-" + tariff_id;
		tariffdto.setTariff_id(tariff_id);

		// copy dto to bo
		bo = new TariffBo();
		bo.setHid(tariffdto.getHid());
		BeanUtils.copyProperties(tariffdto, bo);
		// use dao
		count = tardao.insertTraiffs(bo);

		if (count == 0) {
			return "fail";
		}
		return "success";
	}
	
	@Override
	public List<TariffDto> featchAll(String hid) {
		List<TariffDto>listdto=new ArrayList<>();
		List<TariffBo>listbo=null;
		
		//use dao
		listbo=tardao.getAllTariff(hid);
		
		listbo.forEach(bo->{
			
			TariffDto dto=new TariffDto();
			BeanUtils.copyProperties(bo, dto);
			listdto.add(dto);
			
		});
				
		return listdto;
	}
	
	
	@Override
	public String removeTariff(String tariff_id) {
		
		int count=0;
		
		//use dao
		count=tardao.deleteTariff(tariff_id);
		if(count==0) {
			return "fail";
		}
		return "success";
	}

}
