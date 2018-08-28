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

import com.harbor.bo.QueueBo;

@Repository
public class QueueDaoImpl implements QueueDao {
	
	private static final String INSERT_QUEUE="INSERT INTO queue_management(queue_id,hid,dpt_name,room_name,doc_name) VALUES(?,?,?,?,?)";

	private  static final String GET_ALL_QUEUE="SELECT queue_id,dpt_name,room_name,doc_name FROM  queue_management WHERE hid=?";
	private static final String DELETE_QUEUE="DELETE FROM queue_management WHERE queue_id=?";
	@Autowired
	JdbcTemplate jt;
	@Override
	public int insertQueue(QueueBo queuebo) {
		int count=0;
		count=jt.update(INSERT_QUEUE, queuebo.getQueue_id(),queuebo.getHid(),queuebo.getDpt_name(),queuebo.getRoom_name(),queuebo.getDoc_name());
		return count;
	}

	
	@Override
	public List<QueueBo> getAllQueue(String hid) {
		List<QueueBo>listbo=null;
		
		listbo=jt.query(GET_ALL_QUEUE, new ResultSetExtractor<List<QueueBo>>() {

			@Override
			public List<QueueBo> extractData(ResultSet rs) throws SQLException, DataAccessException {
			List<QueueBo>listbo=new ArrayList<>();
			QueueBo bo=null;
			
			 while(rs.next()) {
				 bo=new  QueueBo();
				 bo.setQueue_id(rs.getString(1));
				 bo.setDpt_name(rs.getString(2));
				 bo.setRoom_name(rs.getString(3));
				 bo.setDoc_name(rs.getString(4));
				 listbo.add(bo);
			 }
			
				return listbo;
			}
			
			
		}, hid);
		return listbo;
	}
	
	@Override
	public int deleteQueue(String queue_id) {
		int count=0;
		
		count=jt.update(DELETE_QUEUE, queue_id);
		return count;
	}
}
