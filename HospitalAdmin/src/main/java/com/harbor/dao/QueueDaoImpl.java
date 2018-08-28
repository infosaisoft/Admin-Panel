package com.harbor.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.harbor.bo.QueueBo;

@Repository
public class QueueDaoImpl implements QueueDao {
	
	private static final String INSERT_QUEUE="INSERT INTO queue_management(queue_id,dpt_name,room_name,doc_name) VALUES(?,?,?,?)";

	@Autowired
	JdbcTemplate jt;
	@Override
	public int insertQueue(QueueBo queuebo) {
		int count=0;
		count=jt.update(INSERT_QUEUE, queuebo.getQueue_id(),queuebo.getDpt_name(),queuebo.getRoom_name(),queuebo.getDoc_name());
		return count;
	}

}
