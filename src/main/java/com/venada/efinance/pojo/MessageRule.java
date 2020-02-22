package com.venada.efinance.pojo;

import java.util.Date;

/**
 * 
 * @author hepei
 * 
 */
public class MessageRule {

	private String id;
	private String userid;
	private String mobileNumber;
	private Date createtime;
	private String startTime;
	private String endTime;
	private Integer switchTag;

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

	public Date getCreatetime() {
		return createtime;
	}

	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public Integer getSwitchTag() {
		return switchTag;
	}

	public void setSwitchTag(Integer switchTag) {
		this.switchTag = switchTag;
	}

}
