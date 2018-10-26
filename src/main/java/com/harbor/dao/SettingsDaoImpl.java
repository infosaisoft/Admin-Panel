package com.harbor.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.harbor.bo.SettingsBo;

@Repository
public class SettingsDaoImpl implements SettingsDao {
	
	private static final String INSERTSETT = "INSERT INTO general_settings (id, hospital_id, slot_duration, max_patient) values (?,?,?,?)";
	private static final String CHECKSETT = "SELECT COUNT(*) FROM general_settings WHERE hospital_id=?";
	private static final String FETCHSETT = "SELECT slot_duration, max_patient FROM general_settings WHERE hospital_id=?";
	private static final String UPDATESETT = "UPDATE general_settings SET slot_duration=?, max_patient=? WHERE hospital_id=?";
	
	@Autowired
	JdbcTemplate jt;
	
	@Override
	public int insertSettings(SettingsBo setbo) {
		
		int count = 0;
		int counthid = 0;
		counthid = jt.queryForObject(CHECKSETT, Integer.class, setbo.getHid());
		//System.out.println("hid dao == "+counthid);
		
		if(counthid == 0) {
			count = jt.update(INSERTSETT, setbo.getSettings_id(), setbo.getHid(), setbo.getSlot_duration(), setbo.getMax_patient());		
			return count;
		}else {
			count = jt.update(UPDATESETT, setbo.getSlot_duration(), setbo.getMax_patient(), setbo.getHid());		
			return count;
		}
		
	}

	@Override
	public SettingsBo fetchSettings(long hid) {
	      try {	
		SettingsBo sbo = null;
		
	
		sbo = jt.queryForObject(FETCHSETT, new RowMapper<SettingsBo>() {

			@Override
			public SettingsBo mapRow(ResultSet rs, int index) throws SQLException {
				
				SettingsBo setbo = new SettingsBo();
				setbo.setSlot_duration(rs.getString(1));
				setbo.setMax_patient(rs.getString(2));
				
				return setbo;
			}
			
		}, hid);
		
		
		
		
		return sbo;
	
	}
	
	catch(EmptyResultDataAccessException e) {
	   return null;	
	}
	}
}
