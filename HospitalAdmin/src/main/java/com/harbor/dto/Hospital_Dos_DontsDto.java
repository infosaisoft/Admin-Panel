package com.harbor.dto;

public class Hospital_Dos_DontsDto {

	private long id;
	private long hid;
	private String name;
	private String description;
	private String images;
	private String videos;
	
	private String document;
	
	
	
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
	public String getImages() {
		return images;
	}
	public String getVideos() {
		return videos;
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
	public void setImages(String images) {
		this.images = images;
	}
	public void setVideos(String videos) {
		this.videos = videos;
	}
	public String getDocument() {
		return document;
	}
	public void setDocument(String document) {
		this.document = document;
	}
	

	

}
