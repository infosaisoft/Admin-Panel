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

import com.harbor.bo.HospitalBo;
import com.harbor.bo.UserBo;

@Repository
public class DocAvailDaoImpl implements DocAvailDao {

	public static final String GETDOCTOR = "SELECT  `fname`,`lname`  FROM hospital_admin WHERE role='doctor' AND hid=?";

	@Autowired
	JdbcTemplate jt;

	@Override
	public List<UserBo> getAllDoctor(String hid) {
		List<UserBo> listbo = null;

		listbo = jt.query(GETDOCTOR, new ResultSetExtractor<List<UserBo>>() {

			@Override
			public List<UserBo> extractData(ResultSet rs) throws SQLException, DataAccessException {
				List<UserBo> listbo = new ArrayList<>();

				UserBo bo = null;

				while (rs.next()) {

					bo = new UserBo();
					bo.setFname(rs.getString(1));
					bo.setLname(rs.getString(2));
					listbo.add(bo);

				}

				return listbo;
			}

		}, hid);

		return listbo;
	}

}
