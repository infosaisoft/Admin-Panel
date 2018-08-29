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

import com.harbor.bo.LoginHistoryBo;

@Repository
public class LoginHistoryDaoImpl implements LoginHistoryDao {
	
	private static final String GET_LOGIN_HISTORY = "SELECT lid, session_id, hid, login_time, logout_time FROM login_history WHERE admin_id=? ORDER BY login_time DESC";
	
	@Autowired
	JdbcTemplate jt;
	
	@Override
	public List<LoginHistoryBo> getLoginHistory(String admin_id) {
		
		List<LoginHistoryBo> listbo = null;
		
		listbo = jt.query(GET_LOGIN_HISTORY, new ResultSetExtractor<List<LoginHistoryBo>>() {

			@Override
			public List<LoginHistoryBo> extractData(ResultSet rs) throws SQLException, DataAccessException {
				
				List<LoginHistoryBo> lhbo = new ArrayList<>();
				
				LoginHistoryBo bo = null;
				
				while (rs.next()) {
					bo = new LoginHistoryBo();
					bo.setLid(rs.getString(1));
					bo.setSession_id(rs.getString(2));
					bo.setAdmin_id(rs.getString(3));
					bo.setLogin_time(rs.getString(4));
					bo.setLogout_time(rs.getString(5));
					
					lhbo.add(bo);
				}
				
				return lhbo;
			}
			
		}, admin_id);
		
		return listbo;
	}

}
