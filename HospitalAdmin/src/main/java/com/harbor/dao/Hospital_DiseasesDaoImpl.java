package com.harbor.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.harbor.bo.Hospital_DiseasesBo;

@Repository
public class Hospital_DiseasesDaoImpl implements Hospital_DiseasesDao {

	private static final String Insert_query = "insert into hospital_diseases (hospital_id,name,description,images,videos) values (?,?,?,?,?)";

	private static final String get_all = "select id, hospital_id,name,description,images,videos from hospital_diseases ";

	private static final String get_by_sno = "select * from hospital_diseases where id=?";

	private static final String update_hospital_diseases = "update hospital_diseases set hospital_id=?,name = ?,description = ?,images = ?,videos = ? where id = ?";

	private static final String delete_query = "delete from hospital_diseases where hospital_id = ?";

	@Autowired
	JdbcTemplate jt;

	@Override
	public int insertHospital(Hospital_DiseasesBo bo) {

		int count = 0;
		count = jt.update(Insert_query, bo.getHid(),bo.getName(), bo.getDescription(), bo.getImges(),bo.getVideos());
		return count;
	}

	@Override
	public List<Hospital_DiseasesBo> getAllHospitals() {
		List<Hospital_DiseasesBo> listbo = null;
		listbo = jt.query(get_all, new ResultSetExtractor<List<Hospital_DiseasesBo>>() {

			@Override
			public List<Hospital_DiseasesBo> extractData(ResultSet rs) throws SQLException, DataAccessException {

				Hospital_DiseasesBo bo = null;
				List<Hospital_DiseasesBo> listbo = new ArrayList<>();
				while (rs.next()) {
					bo = new Hospital_DiseasesBo();
					bo.setId(rs.getLong(1));
					bo.setHid(rs.getLong(2));
					bo.setName(rs.getString(3));
					bo.setDescription(rs.getString(4));
					bo.setImges(rs.getString(5));
					bo.setVideos(rs.getString(6));

					listbo.add(bo);
				}

				return listbo;
			}

		});

		return listbo;
	}

	@Override
	public Hospital_DiseasesBo getHospitalsBysno(long sno) {
		Hospital_DiseasesBo bo = null;          
		System.out.println("dieaseo::");
		System.out.println(sno);
  
		bo = jt.queryForObject(get_by_sno, new RowMapper<Hospital_DiseasesBo>() {

			@Override
			public Hospital_DiseasesBo mapRow(ResultSet rs, int index) throws SQLException {
				Hospital_DiseasesBo bo=new Hospital_DiseasesBo();
				
				bo.setId(rs.getLong(1));
				bo.setHid(rs.getLong(2));
				bo.setName(rs.getString(3));
				bo.setDescription(rs.getString(4));
				bo.setImges(rs.getString(5));
				bo.setVideos(rs.getString(6));


				return bo;
			}
			
		}, sno);

		return bo;
	}

	@Override
	public int deleteHospital(long sno) {
		int count = 0;
		count = jt.update(delete_query, sno);
		return count;
	}

	@Override
	public int UpdateHospital(Hospital_DiseasesBo bo) {
		int count = 0;
		count = jt.update(update_hospital_diseases, bo.getHid(),bo.getName(), bo.getImges(), bo.getDescription(),
				bo.getVideos(),bo.getId());
		return count;
	}

}