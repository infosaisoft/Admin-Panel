package com.harbor.command;

public class QueueCommand {
	
	
	private  String room_name;
	private  String doc_name;
	private String dpt_name;
	
	private  long  queue_id;
	

	
	
	
	public String getRoom_name() {
		return room_name;
	}
	public void setRoom_name(String room_name) {
		this.room_name = room_name;
	}
	
	public long getQueue_id() {
		return queue_id;
	}
	public void setQueue_id(long queue_id) {
		this.queue_id = queue_id;
	}
	public String getDoc_name() {
		return doc_name;
	}
	public void setDoc_name(String doc_name) {
		this.doc_name = doc_name;
	}
	public String getDpt_name() {
		return dpt_name;
	}
	public void setDpt_name(String dpt_name) {
		this.dpt_name = dpt_name;
	}
	


}
