package com.harbor.dao;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.harbor.bo.SlotBo;

@Repository
public class SlotDaoImp implements SlotDao {

	private static final String INSERT_QUERY_SLOT="INSERT INTO SLOTS(doctor_id,date,start_time,end_time,department_id,created,status) VALUES(?,?,?,?,?,?,?)";
	
	@Autowired
	JdbcTemplate jt;
	
	@Override
	public int insertSlot(SlotBo slotbo) {
      int count=0;
      
     
      count=jt.update(INSERT_QUERY_SLOT,slotbo.getDoc_id(),slotbo.getDate(),slotbo.getStart_time(),slotbo.getEnd_time(),slotbo.getDpt_id(),new Date(),slotbo.getSlot_status());
		return count;
	}

}
