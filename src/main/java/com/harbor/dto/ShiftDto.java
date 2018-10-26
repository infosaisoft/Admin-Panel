package com.harbor.dto;

import java.sql.Time;
import java.util.Date;

import org.joda.time.DateTime;

public class ShiftDto {
	
	private  String shift_name;
	private Time start_time;
	private Time end_time;
	private long hid;
	private long shift_id;
	
	private Date created;
	
	
	
	public String getShift_name() {
		return shift_name;
	}
	public void setShift_name(String shift_name) {
		this.shift_name = shift_name;
	}
	public Time getStart_time() {
		return start_time;
	}
	public void setStart_time(Time start_time) {
		this.start_time = start_time;
	}
	public Time getEnd_time() {
		return end_time;
	}
	public void setEnd_time(Time end_time) {
		this.end_time = end_time;
	}
	public long getHid() {
		return hid;
	}
	public void setHid(long hid) {
		this.hid = hid;
	}
	public long getShift_id() {
		return shift_id;
	}
	public void setShift_id(long shift_id) {
		this.shift_id = shift_id;
	}
	public Date getCreated() {
		return created;
	}
	public void setCreated(Date created) {
		this.created = created;
	}
	
		
	
	
	
	
	
}
