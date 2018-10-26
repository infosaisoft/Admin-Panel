package com.harbor.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.harbor.bo.DoctorAvaliblityBo;
import com.harbor.bo.SettingsBo;
import com.harbor.bo.ShiftBo;

@Repository
public class DoctorAvalibityImpl implements DoctorAvaliblityDao {
	
	private static final String INSERT_AVALIBLITY="INSERT INTO doctor_availability (hospital_id,doctor_id,date,shift_name) VALUES(?,?,?,?)";
	
	private static final String GET_ALL_="select id,doctor_id,date ,shift_name from doctor_availability where hospital_id=?";
	
	private static final String DELETE_AVALBLITY="DELETE  from  doctor_availability where id=?";
	
	private static final String GET_SHIFT_QUERY_BY_NAME="SELECT start_time ,end_time FROM shifts WHERE hospital_id=? AND name=?";
	
	private static final String GET_DURATION_PATIENT_QUERY="SELECT slot_duration,max_patient FROM general_settings WHERE hospital_id=?";
	@Autowired
	JdbcTemplate jt;

	@Override
	public int insertAvaliblity(DoctorAvaliblityBo docbo) {
		int count=0;
		
		count=jt.update(INSERT_AVALIBLITY,docbo.getHid(),docbo.getDoc_name(),docbo.getDate(),docbo.getShift_name());
		
		return count;
	}
	
	@Override
	public List<DoctorAvaliblityBo> getAllAvaliblity(long hid) {
		List<DoctorAvaliblityBo>listbo=null;
		
		listbo=jt.query(GET_ALL_, new ResultSetExtractor<List<DoctorAvaliblityBo>>() {

			@Override
			public List<DoctorAvaliblityBo> extractData(ResultSet rs) throws SQLException, DataAccessException {
				List<DoctorAvaliblityBo>listbo=new ArrayList<>();
				DoctorAvaliblityBo bo=null;
				
				while(rs.next()) {
					bo=new DoctorAvaliblityBo();
					bo.setAvail_id(rs.getString(1));
					bo.setDoc_name(rs.getString(2));
					bo.setDate(rs.getDate(3));
					bo.setShift_name(rs.getString(4));
					
					listbo.add(bo);
					
				}
				return listbo;
			}
			
			
			
		}, hid);
		return listbo;
	}
	
	
	
	@Override
	public int deleteAvaliblity(String avail_id) {
	  int count=0;
	  count=jt.update(DELETE_AVALBLITY, avail_id);
		
		return count;
	}
	
	@Override
	public ShiftBo getShiftByName(long hid, String shift_name) {
	  ShiftBo bo=null;
	      
	  bo=jt.queryForObject(GET_SHIFT_QUERY_BY_NAME, new RowMapper<ShiftBo>() {

		@Override
		public ShiftBo mapRow(ResultSet rs, int index) throws SQLException {
			ShiftBo bo=new ShiftBo();
				bo.setStart_time(rs.getTime(1));
				bo.setEnd_time(rs.getTime(2));
				

			
			return bo;
		}
		  
		  
		  
	},hid,shift_name);
		return bo;
	}
	
	
	@Override
	public SettingsBo getDurationPatient(long hid) {
	   SettingsBo  bo=null;
	   
	   bo=jt.queryForObject(GET_DURATION_PATIENT_QUERY, new RowMapper<SettingsBo>() {

		@Override
		public SettingsBo mapRow(ResultSet rs, int index) throws SQLException {
			  SettingsBo bo=new SettingsBo();
			  
			  
				  bo.setSlot_duration(rs.getString(1));
				  bo.setMax_patient(rs.getString(2));
			  
			return bo;
		}
		   
		   
	}, hid);
		return bo;
	}

}
