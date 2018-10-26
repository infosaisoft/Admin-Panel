package com.harbor.dao;

import java.util.List;

import com.harbor.bo.TariffBo;

public interface TariffsDao {
	
	public int insertTraiffs(TariffBo tfbo);
	
	public List<TariffBo> getAllTariff(long hid);	
	
	public int deleteTariff(long tariff_id);
	
	public int addTariffService(TariffBo tsbo);
	
	public List<TariffBo> getAllRates(long hid);
	
	public int deleteRate(long rate_id);
	
}
