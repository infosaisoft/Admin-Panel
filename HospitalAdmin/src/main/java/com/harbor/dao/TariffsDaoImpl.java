package com.harbor.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Repository;

import com.harbor.bo.TariffBo;

@Repository
public class TariffsDaoImpl implements TariffsDao {

	private static final String INSERT_TRAIFFS="INSERT INTO tariffs (tariff_id,hid,tariff_name) VALUES (?,?,?)";
	
	private static final String GETALLtRAIFF="SELECT tariff_id,tariff_name FROM tariffs where hid=?";
	
	private static final String DELETE_TRAIFFS= "DELETE FROM  tariffs WHERE tariff_id=?";
	
	private static final String INSERT_TARIFF_SERV="INSERT INTO rate_charts (rate_id,hid,tariff_name,service_name,service_category,rates,doctor_name,department,is_mandatory) VALUES (?,?,?,?,?,?,?,?,?)";
	
	private static final String GETALLRATES="SELECT rate_id,tariff_name,service_name,service_category,rates,doctor_name,department,is_mandatory FROM rate_charts where hid=?";
	
	private static final String DELETE_RATE= "DELETE FROM  rate_charts WHERE rate_id=?";
	
	@Autowired
	private JdbcTemplate jt;
	
	// insert Tariff
	@Override
	public int insertTraiffs(TariffBo tfbo) {
		int count=0;	
		count=jt.update(INSERT_TRAIFFS, tfbo.getTariff_id(),tfbo.getHid(),tfbo.getTariff_name());		
		return count;
	}

	// Get All Tariffs
	@Override
	public List<TariffBo> getAllTariff(String hid) {
	List<TariffBo>listbo=null;
	
	listbo=jt.query(GETALLtRAIFF,new ResultSetExtractor<List<TariffBo>>() {

		@Override
		public List<TariffBo> extractData(ResultSet rs) throws SQLException, DataAccessException {
			List<TariffBo>listbo=new ArrayList<>();
			TariffBo bo=null;
			while(rs.next()) {
				bo=new TariffBo();
				bo.setTariff_id(rs.getString(1));
				bo.setTariff_name(rs.getString(2));
				
				listbo.add(bo);
			}
			return listbo;
		}
		
		
		
	},hid);
		return listbo;
	}
	
	
	// Delete Tariff
	@Override
	public int deleteTariff(String tariff_id) {
	
		int count=0;
		count=jt.update(DELETE_TRAIFFS, tariff_id);
		return count;
	}


	// Add Tariff Rates 
	@Override
	public int addTariffService(TariffBo tsbo) {
		int count=0;	
		count=jt.update(INSERT_TARIFF_SERV, tsbo.getRate_id(), tsbo.getHid(), tsbo.getTariff_name(), tsbo.getService_name(), tsbo.getService_category(), tsbo.getRates(), tsbo.getDoctor_name(), tsbo.getDepartment(), tsbo.getIs_mandatory());		
		return count;
	}


	// Get All Rates
	@Override
	public List<TariffBo> getAllRates(String hid) {
		
		List<TariffBo> ratelistbo = null;
		
		ratelistbo = jt.query(GETALLRATES, new ResultSetExtractor<List<TariffBo>>() {

			@Override
			public List<TariffBo> extractData(ResultSet rs) throws SQLException, DataAccessException {
				List<TariffBo>ratelistbo=new ArrayList<>();
				TariffBo bo=null;
				while(rs.next()) {
					bo=new TariffBo();
					bo.setRate_id(rs.getString(1));
					bo.setTariff_name(rs.getString(2));
					bo.setService_name(rs.getString(3));
					bo.setService_category(rs.getString(4));
					bo.setRates(rs.getString(5));
					bo.setDoctor_name(rs.getString(6));
					bo.setDepartment(rs.getString(7));
					bo.setIs_mandatory(rs.getBoolean(8));
					
					ratelistbo.add(bo);
				}
				return ratelistbo;
			}
			
		}, hid);
		
		return ratelistbo;
	}

	@Override
	public int deleteRate(String rate_id) {
		int count=0;
		count=jt.update(DELETE_RATE, rate_id);
		return count;
	}
	
}
