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
	
	@Autowired
	private JdbcTemplate jt;
	
	@Override
	public int insertTraiffs(TariffBo tfbo) {
	int count=0;
	
	count=jt.update(INSERT_TRAIFFS, tfbo.getTariff_id(),tfbo.getHid(),tfbo.getTariff_name());
		
		return count;
	}

	
	
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
	
	@Override
	public int deleteTariff(String tariff_id) {
	
		int count=0;
		count=jt.update(DELETE_TRAIFFS, tariff_id);
		return count;
	}
	
}
