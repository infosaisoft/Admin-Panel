package com.harbor.service;


import java.util.List;

import com.harbor.dto.TariffDto;

public interface TariffService {
	public String regTraiff(TariffDto tariffdto);
	
	public List<TariffDto> featchAll(String hid);
	
	public String removeTariff(String tariff_id);
	
	public String addTariff(TariffDto tdto);
	
	public List<TariffDto> featchAllRates(String hid);
	
	public String removeRate(String rate_id);
}
