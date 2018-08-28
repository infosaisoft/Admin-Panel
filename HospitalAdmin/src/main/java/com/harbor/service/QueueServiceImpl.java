package com.harbor.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.harbor.bo.QueueBo;
import com.harbor.common.CustomIdGenerator;
import com.harbor.dao.QueueDao;
import com.harbor.dto.QueueDto;

@Service
public class QueueServiceImpl implements QueueService {

	@Autowired
	QueueDao queuedao;

	@Override
	public String registrationQueue(QueueDto queuedto) {
		QueueBo queuebo = null;
		String queue_id = null;
		int count = 0;

		queue_id = String.valueOf(CustomIdGenerator.getID());
		queue_id = "QUE-" + queue_id;
		queuedto.setQueue_id(queue_id);
		// copy dto to bo
		queuebo = new QueueBo();
		BeanUtils.copyProperties(queuedto, queuebo);

		// use dao
		count = queuedao.insertQueue(queuebo);
		if (count == 0) {
			return "fail";

		}

		return "success";
	}

}
