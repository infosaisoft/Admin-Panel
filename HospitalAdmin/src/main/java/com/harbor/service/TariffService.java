package com.harbor.service;


import java.util.List;

import com.harbor.dto.TariffDto;

public interface TariffService {
	public String regTraiff(TariffDto tariffdto);
	
	public List<TariffDto> featchAll(long hid);
	
	public String removeTariff(long tariff_id);
	
	public String addTariff(TariffDto tdto);
	
	public List<TariffDto> featchAllRates(long hid);
	
	public String removeRate(long rate_id);
}
