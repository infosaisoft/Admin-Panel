package com.harbor.service;


import java.util.List;

import com.harbor.dto.TariffDto;

public interface TariffService {
	public String regTraiff(TariffDto tariffdto);
	
	public List<TariffDto> featchAll(String hid);
	
	public String removeTariff(String tariff_id);
}
