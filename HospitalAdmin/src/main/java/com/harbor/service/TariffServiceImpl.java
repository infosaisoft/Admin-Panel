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

	// insert Tariffs
	@Override
	public String regTraiff(TariffDto tariffdto) {
		TariffBo bo = null;
		int count = 0;

		// copy dto to bo
		bo = new TariffBo();
		
		BeanUtils.copyProperties(tariffdto, bo);
		// use dao
		count = tardao.insertTraiffs(bo);

		if (count == 0) {
			return "fail";
		}
		return "success";
	}
	
	// Get All Tariffs
	@Override
	public List<TariffDto> featchAll(long hid) {
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
	
	// delete tariffs
	@Override
	public String removeTariff(long tariff_id) {
		
		int count=0;
		
		//use dao
		count=tardao.deleteTariff(tariff_id);
		if(count==0) {
			return "failed";
		}
		return "success";
	}
	
	
	// Add Tariff Rates
	@Override
	public String addTariff(TariffDto tdto) {
		
		TariffBo bo = null;
		int count = 0;
		
		
		// copy DTO to BO
		bo = new TariffBo();
		bo.setHid(tdto.getHid());
		BeanUtils.copyProperties(tdto, bo);
		
		// use DAO
		count = tardao.addTariffService(bo);
		
		if(count == 0) {
			return "failed";
		}else {
			return "success";
		}
		
	}
	
	
	// Get All Rates
	@Override
	public List<TariffDto> featchAllRates(long hid) {
		List<TariffDto>ratelistdto=new ArrayList<>();
		List<TariffBo>ratelistbo=null;
		
		//use dao
		ratelistbo=tardao.getAllRates(hid);
		
		ratelistbo.forEach(bo->{
			
			TariffDto dto=new TariffDto();
			BeanUtils.copyProperties(bo, dto);
			ratelistdto.add(dto);
			
		});
				
		return ratelistdto;
	}

	
	// Delete Rate
	@Override
	public String removeRate(long rate_id) {
		int count=0;		
		//use dao
		count=tardao.deleteRate(rate_id);
		if(count==0) {
			return "failed";
		}
		return "success";
	}

}
