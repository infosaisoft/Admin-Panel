package com.harbor.dto;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

public class DoctorAvaliblityDto {
	public String avail_id;
	public long hid;
	public long doc_id;
    private String doc_name;
    
    private  Date slot_date;
    
    private long dpt_id;
    private String dpt_name;
	
	 private String shift_name;
	private Date date;

	public String getAvail_id() {
		return avail_id;
	}

	public void setAvail_id(String avail_id) {
		this.avail_id = avail_id;
	}

	public long getHid() {
		return hid;
	}

	public void setHid(long hid) {
		this.hid = hid;
	}




	public String getShift_name() {
		return shift_name;
	}

	public void setShift_name(String shift_name) {
		this.shift_name = shift_name;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public long getDoc_id() {
		return doc_id;
	}

	public void setDoc_id(long doc_id) {
		this.doc_id = doc_id;
	}

	public String getDoc_name() {
		return doc_name;
	}

	public void setDoc_name(String doc_name) {
		this.doc_name = doc_name;
	}

	public long getDpt_id() {
		return dpt_id;
	}

	public void setDpt_id(long dpt_id) {
		this.dpt_id = dpt_id;
	}

	public String getDpt_name() {
		return dpt_name;
	}

	public void setDpt_name(String dpt_name) {
		this.dpt_name = dpt_name;
	}

	public Date getSlot_date() {
		return slot_date;
	}

	public void setSlot_date(Date slot_date) {
		this.slot_date = slot_date;
	}

}
