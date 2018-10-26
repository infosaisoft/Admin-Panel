package com.harbor.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Repository;

import com.harbor.bo.DepartmentBo;
import com.harbor.bo.ShiftBo;
import com.harbor.common.SpringException;

@Repository
public class ShiftDaoImpl implements ShiftDao {
	
	private static final String INSERTSHIFT = "INSERT INTO shifts (id, hospital_id, name, start_time, end_time) values (?,?,?,?,?)";
	private static final String GETSHIFTS = "SELECT id, name, start_time, end_time FROM shifts WHERE hospital_id=?";
	private static final String DELETESHIFT = "DELETE  FROM `shifts` WHERE id=?";
	
	@Autowired
	JdbcTemplate jt;
	
	@Override
	public int insertShift(ShiftBo shiftbo)throws SpringException,SQLException {
		
		int count = 0;
		
		count = jt.update(INSERTSHIFT, shiftbo.getShift_id(), shiftbo.getHid(), shiftbo.getShift_name(), shiftbo.getStart_time(), shiftbo.getEnd_time());
		
		return count;
	}

	@Override
	public List<ShiftBo> getAllShifts(long hid) {
		List<ShiftBo> listshift = null;
		System.out.println("dao== "+hid);
		
		listshift = jt.query(GETSHIFTS, new ResultSetExtractor<List<ShiftBo>>() {

			@Override
			public List<ShiftBo> extractData(ResultSet rs) throws SQLException, DataAccessException {
				
				List<ShiftBo> listshift = new ArrayList<>();
				
				ShiftBo sbo = null;
				
				while(rs.next()) {
				
					sbo = new ShiftBo();
					sbo.setShift_id(rs.getLong(1));
					sbo.setShift_name(rs.getString(2));
					sbo.setStart_time(rs.getTime(3));
					sbo.setEnd_time(rs.getTime(4));
					   
					listshift.add(sbo);
				}
				
				return listshift;
			}
			
		}, hid);
		
		return listshift;
	}

	@Override
	public int deleteShift(String shift_id) {
		
		int count=0;
		
		count=jt.update(DELETESHIFT, shift_id);
	
		return count;
		
	}

}
