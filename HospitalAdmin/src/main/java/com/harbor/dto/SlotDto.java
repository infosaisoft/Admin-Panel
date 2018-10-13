package com.harbor.dto;

import java.util.Date;

public class SlotDto {
	private String slots_id;
	private  long hid;
	private long doc_id;
	private Date date;
	private Date start_time;
	private  Date end_time;
	private int slot_status;
	private long dpt_id;
	private Date created;
	
	
	
	public String getSlots_id() {
		return slots_id;
	}
	public void setSlots_id(String slots_id) {
		this.slots_id = slots_id;
	}
	public long getHid() {
		return hid;
	}
	public void setHid(long hid) {
		this.hid = hid;
	}
	public long getDoc_id() {
		return doc_id;
	}
	public void setDoc_id(long doc_id) {
		this.doc_id = doc_id;
	}
	
	
	public int getSlot_status() {
		return slot_status;
	}
	public void setSlot_status(int slot_status) {
		this.slot_status = slot_status;
	}
	public long getDpt_id() {
		return dpt_id;
	}
	public void setDpt_id(long dpt_id) {
		this.dpt_id = dpt_id;
	}
	public Date getCreated() {
		return created;
	}
	public void setCreated(Date created) {
		this.created = created;
	}
	public Date getStart_time() {
		return start_time;
	}
	public void setStart_time(Date start_time) {
		this.start_time = start_time;
	}
	public Date getEnd_time() {
		return end_time;
	}
	public void setEnd_time(Date end_time) {
		this.end_time = end_time;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	
	
	
}
