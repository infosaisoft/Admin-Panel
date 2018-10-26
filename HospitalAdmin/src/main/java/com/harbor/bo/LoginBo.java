package com.harbor.bo;

import java.util.Date;

public class LoginBo {
	
	private String lid;
	private String username;
	private String password;
	private long admin_id;
	private long hid;
	private String session_id;
	private Date login_time;


	public long getHid() {
	return hid;
	}
	public void setHid(long hid) {
	this.hid = hid;
	}
	public long getAdmin_id() {
	return admin_id;
	}
	public void setAdmin_id(long admin_id) {
	this.admin_id = admin_id;
	}
	public String getUsername() {
	return username;
	}
	public void setUsername(String username) {
	this.username = username;
	}
	public String getPassword() {
	return password;
	}
	public void setPassword(String password) {
	this.password = password;
	}
	public String getSession_id() {
	return session_id;
	}
	public void setSession_id(String session_id) {
	this.session_id = session_id;
	}
	public String getLid() {
	return lid;
	}
	public void setLid(String lid) {
	this.lid = lid;
	}
	public Date getLogin_time() {
	return login_time;
	}
	public void setLogin_time(Date login_time) {
	this.login_time = login_time;
	}
	
}
