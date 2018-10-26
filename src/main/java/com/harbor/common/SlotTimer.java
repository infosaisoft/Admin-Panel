package com.harbor.common;

import java.sql.Time;
import java.util.Date;

import org.joda.time.DateTime;

public class SlotTimer {
	private Time star_time;
	private Time end_time;
	private String slot_duration;
	private String max_patient;
	private Date slotdate;
	
	
	
	
	public Time getStar_time() {
		return star_time;
	}
	public void setStar_time(Time star_time) {
		this.star_time = star_time;
	}
	public Time getEnd_time() {
		return end_time;
	}
	public void setEnd_time(Time end_time) {
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
	public Date getSlotdate() {
		return slotdate;
	}
	public void setSlotdate(Date slotdate) {
		this.slotdate = slotdate;
	}
	
	
}
