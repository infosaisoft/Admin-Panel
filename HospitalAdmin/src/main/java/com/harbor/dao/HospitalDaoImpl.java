package com.harbor.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.harbor.bo.HospitalBo;

@Repository
public class HospitalDaoImpl implements HospitalDao {
	
	private static final String GETHOSPITALINFO="SELECT hid,name,address,city,state,pincode,contact,reg_number,logo,creation_date FROM hospitals WHERE HID=?";


	@Autowired
	JdbcTemplate jt;
	
	@Override
	public HospitalBo gethospital(String hid) {
	HospitalBo hbo=null;
	hbo=jt.queryForObject(GETHOSPITALINFO, new RowMapper<HospitalBo>() {

		@Override
		public HospitalBo mapRow(ResultSet rs, int index) throws SQLException {
			
			HospitalBo bo=null;
			
			//get all record
			bo=new HospitalBo();
			bo.setHid(rs.getString(1));
			bo.setName(rs.getString(2));
			bo.setAddress(rs.getString(3));
			bo.setCity(rs.getString(4));
			bo.setState(rs.getString(5));
			bo.setPincode(rs.getString(6));
			bo.setContact(rs.getString(7));
			bo.setReg_number(rs.getString(8));
			bo.setLogo(rs.getString(9));
			bo.setCreation_date(rs.getDate(10));
			return bo;
		}
		
		
		
		
	}, hid);
		return hbo;
	}

}
