package com.harbor.service;

import java.util.List;

import com.harbor.dto.QueueDto;

public interface QueueService {
	
	public String registrationQueue(QueueDto queuedto);
	
	public List<QueueDto> featchAllQueue(String hid);
	
	public String deleteQueue(String queue_id);

}
