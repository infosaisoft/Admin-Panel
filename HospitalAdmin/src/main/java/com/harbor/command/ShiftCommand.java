package com.harbor.command;

import java.util.Date;

import javax.validation.Constraint;

import org.hibernate.validator.constraints.UniqueElements;

import com.harbor.commons.UniqueCoupon;

public class ShiftCommand {
	
	private String shift_name;
	private String start_time;
	private String end_time;
	
	private String slot_duration;
	private String max_patient;
	
	private String doc_name;
	private long dpt_id;

	private String dpt_name;
	
	
	private Date date;
	
	public String getShift_name() {
		return shift_name;
	}
	public void setShift_name(String shift_name) {
		this.shift_name = shift_name;
	}
	public String getStart_time() {
		return start_time;
	}
	public void setStart_time(String start_time) {
		this.start_time = start_time;
	}
	public String getEnd_time() {
		return end_time;
	}
	public void setEnd_time(String end_time) {
		this.end_time = end_time;
	}
	
	public String getSlot_duration() {
		return slot_duration;
	}
	public void setSlot_duration(String slot_duration) {
		this.slot_duration = slot_duration;
	}
	public String getMax_patient() {
		return max_patient;
	}
	public void setMax_patient(String max_patient) {
		this.max_patient = max_patient;
	}
	
	public String getDoc_name() {
		return doc_name;
	}
	public void setDoc_name(String doc_name) {
		this.doc_name = doc_name;
	}
	
	
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
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
	
	
}
