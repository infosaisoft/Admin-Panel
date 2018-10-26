package com.harbor.dao;

import java.util.List;

import com.harbor.bo.QueueBo;

public interface QueueDao {
	
	public int insertQueue(QueueBo queuebo);
	
	public List<QueueBo> getAllQueue(long hid);
	public int deleteQueue(long queue_id);
	

}
