package com.harbor.bo;

public class SettingsBo {
	
	private String settings_id;
	private String hid;
	private String slot_duration;
	private String max_patient;
	
	public String getSettings_id() {
		return settings_id;
	}
	public void setSettings_id(String settings_id) {
		this.settings_id = settings_id;
	}
	public String getHid() {
		return hid;
	}
	public void setHid(String hid) {
		this.hid = hid;
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
