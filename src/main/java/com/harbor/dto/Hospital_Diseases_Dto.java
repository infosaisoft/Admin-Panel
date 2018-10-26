package com.harbor.dto;

public class Hospital_Diseases_Dto {

	

	private long  id;
	private long hid;
	private String name;
	private String description;
	private String imges;
	private String videos;
	private String documents;
	
	
	
	public long getId() {
		return id;
	}
	public long getHid() {
		return hid;
	}
	public String getName() {
		return name;
	}
	public String getDescription() {
		return description;
	}
	public String getImges() {
		return imges;
	}
	public String getVideos() {
		return videos;
	}
	public String getDocuments() {
		return documents;
	}
	public void setId(long id) {
		this.id = id;
	}
	public void setHid(long hid) {
		this.hid = hid;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public void setImges(String imges) {
		this.imges = imges;
	}
	public void setVideos(String videos) {
		this.videos = videos;
	}
	public void setDocuments(String documents) {
		this.documents = documents;
	}
	

}
