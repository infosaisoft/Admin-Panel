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

import com.harbor.bo.DepartmentBo;
import com.harbor.bo.ShiftBo;

@Repository
public class ShiftDaoImpl implements ShiftDao {
	
	private static final String INSERTSHIFT = "INSERT INTO shifts (shifts_id, hid, shift_name, start_time, end_time) values (?,?,?,?,?)";
	private static final String GETSHIFTS = "SELECT shifts_id, shift_name, start_time, end_time FROM shifts WHERE hid=?";
	private static final String DELETESHIFT = "DELETE  FROM `shifts` WHERE `shifts_id`=?";
	
	@Autowired
	JdbcTemplate jt;
	
	@Override
	public int insertShift(ShiftBo shiftbo) {
		
		int count = 0;
		
		count = jt.update(INSERTSHIFT, shiftbo.getShift_id(), shiftbo.getHid(), shiftbo.getShift_name(), shiftbo.getStart_time(), shiftbo.getEnd_time());
		
		return count;
	}

	@Override
	public List<ShiftBo> getAllShifts(String hid) {
		List<ShiftBo> listshift = null;
		System.out.println("dao== "+hid);
		
		listshift = jt.query(GETSHIFTS, new ResultSetExtractor<List<ShiftBo>>() {

			@Override
			public List<ShiftBo> extractData(ResultSet rs) throws SQLException, DataAccessException {
				
				List<ShiftBo> listshift = new ArrayList<>();
				
				ShiftBo sbo = null;
				
				while(rs.next()) {
					sbo = new ShiftBo();
					sbo.setShift_id(rs.getString(1));
					sbo.setShift_name(rs.getString(2));
					sbo.setStart_time(rs.getString(3));
					sbo.setEnd_time(rs.getString(4));
					
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
