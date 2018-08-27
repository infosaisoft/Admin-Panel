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

@Repository
public class DepartmentDaoImpl implements DepartmentDao {

	private static final String INSERT_DEPT_QUERY = "INSERT INTO departments (dpt_id,dpt_name,hid,dpt_location) VALUES(?,?,?,?)";

	//private static final String GET_DEPARTMENT_QUERY = "SELECT dpt_id,dpt_name,hid,dpt_location FROM departments";

	private static final String DEPARTMENT_DELETE_QUERY = "DELETE  FROM departments WHERE dpt_id=? ";

	private static final String GET_DEPARTMENT_QUERY = "SELECT dpt_id,dpt_name,dpt_location FROM departments WHERE hid=?";
	@Autowired
	JdbcTemplate jt;

	@Override
	public int insertDepartment(DepartmentBo deptbo) {
		int count = 0;
		count = jt.update(INSERT_DEPT_QUERY, deptbo.getDpt_id(), deptbo.getDpt_name(), deptbo.getHid(),
				deptbo.getDpt_location());
		return count;
	}

	@Override
	public List<DepartmentBo> getAllDepartment(String hid) {
		List<DepartmentBo> listbo = null;

		listbo = jt.query(GET_DEPARTMENT_QUERY, new ResultSetExtractor<List<DepartmentBo>>() {

			public List<DepartmentBo> extractData(ResultSet rs) throws SQLException, DataAccessException {
				List<DepartmentBo> listbo = new ArrayList<>();

				DepartmentBo bo = null;
				while (rs.next()) {
					bo = new DepartmentBo();
					bo.setDpt_id(rs.getString(1));
					bo.setDpt_name(rs.getString(2));
					bo.setDpt_location(rs.getString(3));

					listbo.add(bo);

				}
				return listbo;
			}

		}, hid);
		return listbo;
	}

	@Override
	public int deleteDapartment(String dpt_id) {
		int count=0;
		count=jt.update(DEPARTMENT_DELETE_QUERY, dpt_id);
		return count;
	}

}
