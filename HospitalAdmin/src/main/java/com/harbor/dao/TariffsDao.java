package com.harbor.dao;

import java.util.List;

import com.harbor.bo.TariffBo;

public interface TariffsDao {
	
	public int insertTraiffs(TariffBo tfbo);
	
	public List<TariffBo> getAllTariff(String hid);
	
	
	public int deleteTariff(String tariff_id);

}
