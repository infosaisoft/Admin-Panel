package com.harbor.command;

public class ShiftCommand {
	
	private String shift_name;
	private String start_time;
	private String end_time;
	private String slot_duration;
	private String max_patient;
		
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
	
}
