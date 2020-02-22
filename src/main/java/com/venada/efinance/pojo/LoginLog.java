package com.venada.efinance.pojo;

import java.util.Date;

public class LoginLog {
	private String id;
	private String userid;
	private String mobileNumber;
	private Date loginTime;
	private String ip;
	/**
	 * 登录客户端类型
	 * 1:浏览器
	 * 2:手机客户端
	 */
	private int client;
	private Integer count;
	private Date lastestTime;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getMobileNumber() {
		return mobileNumber;
	}
	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}
	public Date getLoginTime() {
		return loginTime;
	}
	public void setLoginTime(Date loginTime) {
		this.loginTime = loginTime;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public int getClient() {
		return client;
	}
	public void setClient(int client) {
		this.client = client;
	}
	public Integer getCount() {
		return count;
	}
	public void setCount(Integer count) {
		this.count = count;
	}
	public Date getLastestTime() {
		return lastestTime;
	}
	public void setLastestTime(Date lastestTime) {
		this.lastestTime = lastestTime;
	}
	
}
