package com.harbor.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.harbor.bo.UserBo;

@Repository
public class UserDaoImpl implements UserDao {

	private static final String GETUSERS="SELECT *   FROM  hospital_staff t1 JOIN  users t2 ON t2.`id` = t1.`user_id` WHERE hospital_id=?";
	private static final String INSERTUSER ="INSERT INTO hospital_staff (name,nick_name,gender,address,contact,photo,last_login,creation,hospital_id) VALUES (?,?,?,?,?,?,?,?,?)";

	private static final String INSERT_QUERY_USERS="INSERT INTO USERS(USERNAME,PASSWORD,ROLE) VALUES(?,?,?)";
	
	private static final String INSERT_QUERY_DOCTOR="INSERT INTO DOCTORS (ADDRESS,created,gender,name,photo) VALUES(?,?,?,?,?)";
	
	private static final String CHACKUSER ="SELECT COUNT(*) FROM  users WHERE username=?";

	private static final String DELETEUSER ="DELETE  FROM hospital_staff WHERE id=? ";

	private static final String COUNTTOTALRECORD ="SELECT COUNT(*) FROM hospital_admin ";

	private static String GETALLUSER="SELECT * FROM  hospital_admin  limit";

	
	private static final String GetUserDetalisByID="SELECT * FROM hospital_staff t1  INNER JOIN users t2 ON t2.`id` = t1.`user_id` WHERE t1.id=?";

	private static final String UDATEUSERDETALIS = "UPDATE hospital_staff SET fname=?,role=?,nick_name=?,gender=?,address=?,contact=?,photo=? WHERE id=?";

	@Autowired
	JdbcTemplate jt;

	@Override
	public List<UserBo> getUser(long hid) {
		List<UserBo> listuserbo = null;

		listuserbo = jt.query(GETUSERS, new ResultSetExtractor<List<UserBo>>() {

			@Override
			public List<UserBo> extractData(ResultSet rs) throws SQLException, DataAccessException {
				List<UserBo> userbo = new ArrayList<>();
				UserBo bo = null;
				while (rs.next()) {

					bo = new UserBo();
					bo.setAdmin_id(rs.getLong(1));
					bo.setFname(rs.getString(2));
					bo.setNick_name(rs.getString(3));
					bo.setGender(rs.getInt(4));
					bo.setAddress(rs.getString(5));
					bo.setContact(rs.getString(6));
					bo.setPhoto(rs.getString(8));
					bo.setLast_login(rs.getString(9));
					bo.setRole(rs.getString(15));
					userbo.add(bo);
				}

				return userbo;
			}

		}, hid);

		return listuserbo;
	}

	@Override
	public int insertUser(UserBo userbo) {
		int count = 0;
		int uname = jt.queryForObject(CHACKUSER, Integer.class, userbo.getUsername());
		if (uname == 0) {
		         System.out.println("userdao::"+userbo.getRole());
		          if(userbo.getRole().contains("doctor")) {
		        	  count=jt.update(INSERT_QUERY_DOCTOR, userbo.getAddress(),new Date(),userbo.getGender(),userbo.getFname(),userbo.getPhoto());
		          }
		          else{
			count = jt.update(INSERTUSER, userbo.getFname(), userbo.getNick_name(),
					userbo.getGender(), userbo.getAddress(), userbo.getContact(),userbo.getPhoto(), new Date(), new Date(),userbo.getHid());
		          }
			      jt.update(INSERT_QUERY_USERS, userbo.getUsername(),userbo.getPassword(),userbo.getRole());
		}
		return count;
	}

	@Override
	public int deleteUsert(long admin_id) {
		int count = 0;

		count = jt.update(DELETEUSER, admin_id);

		return count;
	}

	@Override
	public List<UserBo> reportdata(int startpos, int pagesize) {
		PagedListHolder<UserBo> page = new PagedListHolder<>();

		GETALLUSER = GETALLUSER + startpos + "," + pagesize;
		List<UserBo> listbo = null;

		listbo = jt.query(GETALLUSER, new ResultSetExtractor<List<UserBo>>() {

			@Override
			public List<UserBo> extractData(ResultSet rs) throws SQLException, DataAccessException {
				List<UserBo> listbo = new ArrayList<>();
				UserBo userbo = null;
				while (rs.next()) {
					userbo = new UserBo();
					userbo.setAdmin_id(rs.getLong(1));
					userbo.setFname(rs.getString(3));
					userbo.setLname(rs.getString(4));
					userbo.setUsername(rs.getString(5));
					userbo.setPassword(rs.getString(6));
					userbo.setRole(rs.getString(7));
					userbo.setNick_name(rs.getString(8));
					userbo.setGender(rs.getInt(9));
					userbo.setAddress(rs.getString(10));
					userbo.setContact(rs.getString(11));
					userbo.setLast_login(rs.getString(12));
					userbo.setPhoto(rs.getString(13));
					userbo.setCreation_date(rs.getDate(14));
					listbo.add(userbo);

				}
				return listbo;
			}

		}, startpos, pagesize);
		return listbo;
	}

	@Override
	public long totalRecordsCount() {
		long totalrecord = 0;

		totalrecord = jt.queryForObject(COUNTTOTALRECORD, Long.class);
		return totalrecord;
	}

	@Override
	public UserBo getUserboById(long admin_id) {
		UserBo userbo = null;
		System.out.println("dao:" + admin_id);

		userbo = jt.queryForObject(GetUserDetalisByID, new RowMapper<UserBo>() {

			@Override
			public UserBo mapRow(ResultSet rs, int index) throws SQLException {

				UserBo userbo = new UserBo();

				userbo.setFname(rs.getString(1));
				userbo.setGender(rs.getInt(4));
				userbo.setAddress(rs.getString(5));
				userbo.setContact(rs.getString(6));
				userbo.setPhoto(rs.getString(8));
				userbo.setRole(rs.getString(15));

			
				
				return userbo;
			}

		}, admin_id);
		return userbo;
	}

	@Override
	public int updateUserBoById(UserBo bo) {
		int count = 0;

		count = jt.update(UDATEUSERDETALIS,bo.getFname(), bo.getLname(), bo.getRole(),
				bo.getNick_name(), bo.getGender(), bo.getAddress(),bo.getContact(), bo.getPhoto(),bo.getAdmin_id());
		return count;
	}
}
