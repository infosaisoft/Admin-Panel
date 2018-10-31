package com.harbor.command;

import org.springframework.web.multipart.MultipartFile;

public class Hospital_Diseases_Command {



	private long id;
	private long hid;
	private String name;
	private String description;
	private MultipartFile images;
	private MultipartFile videos;
	private MultipartFile documents;
	
	
	
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
	public MultipartFile getImages() {
		return images;
	}
	public MultipartFile getVideos() {
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
	public void setImages(MultipartFile images) {
		this.images = images;
	}
	public void setVideos(MultipartFile videos) {
		this.videos = videos;
	}
	public MultipartFile getDocuments() {
		return documents;
	}
	public void setDocuments(MultipartFile documents) {
		this.documents = documents;
	}
	

}
