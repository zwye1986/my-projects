package com.venada.efinance.pojo;

import java.util.Date;


/**
 * 已销户用户表
 * 
 * @author zhangwy
 * 
 */
public class UserHis extends User {
	private static final long serialVersionUID = -7784112446646576180L;
	private String userid;
	private Date distroyTime;
	private String reason;
	public Date getDistroyTime() {
		return distroyTime;
	}
	public void setDistroyTime(Date distroyTime) {
		this.distroyTime = distroyTime;
	}
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	
	
}
