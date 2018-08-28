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

import com.harbor.bo.DoctorAvaliblityBo;

@Repository
public class DoctorAvalibityImpl implements DoctorAvaliblityDao {
	
	private static final String INSERT_AVALIBLITY="INSERT INTO doctor_availability (avail_id,hid,doc_name,mon,tue,wed,thu,fri,sat,sun) VALUES(?,?,?,?,?,?,?,?,?,?)";
	
	private static final String GET_ALL_="select avail_id,doc_name,mon,tue,wed,thu,fri,sat,sun from doctor_availability where hid=?";
	
	private static final String DELETE_AVALBLITY="DELETE  from  doctor_availability where avail_id=?";
	
	
	@Autowired
	JdbcTemplate jt;

	@Override
	public int insertAvaliblity(DoctorAvaliblityBo docbo) {
		int count=0;
		
		count=jt.update(INSERT_AVALIBLITY, docbo.getAvail_id(),docbo.getHid(),docbo.getDoc_name(),docbo.getMon(),docbo.getTue(),docbo.getWed(),docbo.getThu(),docbo.getFri(),docbo.getSat(),docbo.getSun());

		
		return count;
	}
	
	@Override
	public List<DoctorAvaliblityBo> getAllAvaliblity(String hid) {
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
					bo.setMon(rs.getString(3));
					bo.setTue(rs.getString(4));
					bo.setWed(rs.getString(5));
					bo.setThu(rs.getString(6));
					bo.setFri(rs.getString(7));
					bo.setSat(rs.getString(8));
					bo.setSun(rs.getString(9));
					
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

}
