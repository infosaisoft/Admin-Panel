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

import com.harbor.bo.TariffBo;

@Repository
public class TariffsDaoImpl implements TariffsDao {

	private static final String INSERT_TRAIFFS = "INSERT INTO tariffs (name,hospital_id) VALUES (?,?)";

	private static final String GETALLtRAIFF = "SELECT id,name FROM tariffs where hospital_id=?";

	private static final String DELETE_TRAIFFS = "DELETE FROM  tariffs WHERE id=?";

	private static final String GETDPTID = "SELECT id FROM `doctor_departments` WHERE doctor_id=? AND department_id=?";

	private static final String INSERT_TARIFF_SERV = "INSERT INTO tariff_rates (id,tariff_id,service_name,service_category,rate,doctor_dept_id,is_mandatory) VALUES (?,?,?,?,?,?,?)";

	private static final String GETALLRATES = "SELECT tariff_rates.id,tariff_rates.service_name,tariff_rates.service_category,tariff_rates.rate,tariff_rates.doctor_dept_id,tariff_rates.is_mandatory,tariff_rates.`tariff_id` FROM tariff_rates,hospitals,tariffs WHERE tariffs.hospital_id=hospitals.id AND tariffs.id=tariff_rates.tariff_id AND hospitals.id=?";

	private static final String DELETE_RATE = "DELETE FROM  tariff_rates WHERE id=?";

	private static final String GET_TRAFFIC_NAME = "SELECT `name` FROM `tariffs` WHERE id=?";

	private static final String GET_DPT_DOC_ID = "SELECT doctor_departments.department_id,doctor_departments.doctor_id FROM doctor_departments WHERE doctor_departments.id=?";

	private static final String GET_DPT_DOC_NAME = "SELECT departments.name,doctors.name FROM departments,doctors,doctor_departments WHERE departments.id=doctor_departments.department_id AND doctor_departments.doctor_id=doctors.id AND  departments.id=? AND doctors.id=?";

	@Autowired
	private JdbcTemplate jt;

	// insert Tariff
	@Override
	public int insertTraiffs(TariffBo tfbo) {
		int count = 0;
		count = jt.update(INSERT_TRAIFFS, tfbo.getTariff_name(), tfbo.getHid());
		return count;
	}

	// Get All Tariffs
	@Override
	public List<TariffBo> getAllTariff(long hid) {
		List<TariffBo> listbo = null;

		listbo = jt.query(GETALLtRAIFF, new ResultSetExtractor<List<TariffBo>>() {

			@Override
			public List<TariffBo> extractData(ResultSet rs) throws SQLException, DataAccessException {
				List<TariffBo> listbo = new ArrayList<>();
				TariffBo bo = null;
				while (rs.next()) {
					bo = new TariffBo();
					bo.setTariff_id(rs.getLong(1));
					bo.setTariff_name(rs.getString(2));

					listbo.add(bo);
				}
				return listbo;
			}

		}, hid);
		return listbo;
	}

	// Delete Tariff
	@Override
	public int deleteTariff(long tariff_id) {

		int count = 0;
		count = jt.update(DELETE_TRAIFFS, tariff_id);
		return count;
	}

	// Add Tariff Rates
	@Override
	public int addTariffService(TariffBo tsbo) {
		int count = 0;
		long docdptid = 0;

		docdptid = jt.queryForObject(GETDPTID, Long.class, tsbo.getDoctor_name(), tsbo.getDepartment());
		System.out.println(docdptid);
		count = jt.update(INSERT_TARIFF_SERV, tsbo.getRate_id(), tsbo.getTariff_name(), tsbo.getService_name(),
				tsbo.getService_category(), tsbo.getRates(), docdptid, tsbo.getIs_mandatory());
		return count;
	}

	// Get All Rates
	@Override
	public List<TariffBo> getAllRates(long hid) {

		List<TariffBo> ratelistbo = null;

		ratelistbo = jt.query(GETALLRATES, new ResultSetExtractor<List<TariffBo>>() {

			@Override
			public List<TariffBo> extractData(ResultSet rs) throws SQLException, DataAccessException {
				List<TariffBo> ratelistbo = new ArrayList<>();
				TariffBo bo = null;
				List dpt_doc_id_list = null;
				List dpt_doc_name_list = null;
				long tariffid=0;
				TariffBo tarbo = new TariffBo();
				long docdptid = 0;

				while (rs.next()) {
					bo = new TariffBo();
					bo.setRate_id(rs.getLong(1));
					bo.setService_name(rs.getString(2));
					bo.setService_category(rs.getString(3));
					bo.setRates(rs.getString(4));
					docdptid = rs.getLong(5);

					jt.queryForObject(GET_DPT_DOC_ID, new RowMapper<Object>() {
						long dptid = 0, docid = 0;
						String dptname = null, docname = null;

						@Override
						public Object mapRow(ResultSet rs, int index) throws SQLException {
							dptid = rs.getLong(1);
							docid = rs.getLong(2);

							jt.queryForObject(GET_DPT_DOC_NAME, new RowMapper<Object>() {

								@Override
								public Object mapRow(ResultSet rs, int index) throws SQLException {
									dptname = rs.getString(1);
									docname = rs.getString(2);
									return null;
								}

							}, dptid, docid);

							tarbo.setDoctor_name(docname);
							tarbo.setDepartment(dptname);

							return null;
						}

					}, docdptid);

					System.out.println(dpt_doc_id_list);
					bo.setDepartment(tarbo.getDepartment());
					bo.setDoctor_name(tarbo.getDoctor_name());
					bo.setIs_mandatory(rs.getBoolean(6));
                     tariffid=rs.getLong(7);
                    String tariffname= jt.queryForObject(GET_TRAFFIC_NAME, String.class, tariffid);
                    bo.setTariff_name(tariffname);
                    ratelistbo.add(bo);
				}
				return ratelistbo;
			}

		}, hid);

		return ratelistbo;
	}

	@Override
	public int deleteRate(long rate_id) {
		int count = 0;
		count = jt.update(DELETE_RATE, rate_id);
		return count;
	}

}
