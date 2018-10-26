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

import com.harbor.bo.Hospital_Dos_DontsBo;

@Repository
public class Hospital_Dos_DontsDaoImpl implements Hospital_Dos_DontsDao {

	private static final String Insert_query = "insert into hospital_dos_donts (hospital_id,name,description,images,videos,document) values (?,?,?,?,?,?) ";

	private static final String get_all = "select id,hospital_id,name,description,images,videos,document from hospital_dos_donts";

	private static final String get_by_srno = "select id,hospital_id,name,description,images,videos,document from hospital_dos_donts where id = ?";

	private static final String update_hospital_dos_donts = "update hospital_dos_donts set hospital_id=?, name=?, description=?,images=?, videos=? ,document=? where id = ?";

	private static final String delete_query = "delete from hospital_dos_donts where id = ?";

	@Autowired
	JdbcTemplate jt;

	@Override
	public int insertHospital_Dos_Donts(Hospital_Dos_DontsBo bo) {
		int count = 0;
		count = jt.update(Insert_query,bo.getHid(), bo.getName(), bo.getDescription(), bo.getImages(),bo.getVideos(),bo.getDocument());
		return count;
	}

	@Override
	public List<Hospital_Dos_DontsBo> selectAll() {

		List<Hospital_Dos_DontsBo> listbo = null;
		listbo = jt.query(get_all, new ResultSetExtractor<List<Hospital_Dos_DontsBo>>() {

			@Override
			public List<Hospital_Dos_DontsBo> extractData(ResultSet rs) throws SQLException, DataAccessException {

				Hospital_Dos_DontsBo bo = null;
				List<Hospital_Dos_DontsBo> listbo = new ArrayList<>();
				while (rs.next()) {
					bo = new Hospital_Dos_DontsBo();
					bo.setId(rs.getLong(1));
					bo.setHid(rs.getLong(2));
					bo.setName(rs.getString(3));
					bo.setDescription(rs.getString(4));
					bo.setImages(rs.getString(5));
					bo.setVideos(rs.getString(6));
					bo.setDocument(rs.getString(7));

					listbo.add(bo);
				}

				return listbo;
			}

		});

		return listbo;
	}

	@Override
	public Hospital_Dos_DontsBo getBySno(long id) {
		Hospital_Dos_DontsBo bo = null;

		bo = jt.queryForObject(get_by_srno, new Hospital_Dos_DontsMapper(), id);

		return bo;
	}

	private class Hospital_Dos_DontsMapper implements org.springframework.jdbc.core.RowMapper<Hospital_Dos_DontsBo> {

		@Override
		public Hospital_Dos_DontsBo mapRow(ResultSet rs, int index) throws SQLException {
			Hospital_Dos_DontsBo bo = null;
			bo=new Hospital_Dos_DontsBo();
			bo.setId(rs.getLong(1));
			bo.setHid(rs.getLong(2));
			bo.setName(rs.getString(3));
			bo.setDescription(rs.getString(4));
			bo.setImages(rs.getString(5));
			bo.setVideos(rs.getString(6));
			bo.setDocument(rs.getString(7));

			return bo;
		}

	}

	@Override
	public int updateHospital_Dos_Donts(Hospital_Dos_DontsBo bo) {
		int count = 0;
		count = jt.update(update_hospital_dos_donts, bo.getHid(), bo.getName(), bo.getDescription(),
				bo.getImages(), bo.getVideos(),bo.getDocument(), bo.getId());
		return count;
	}

	@Override
	public int deleteHospital_Dos_DontsBo(long id) {
		int count = 0;
		count = jt.update(delete_query, id);
		return count;
	}

}
