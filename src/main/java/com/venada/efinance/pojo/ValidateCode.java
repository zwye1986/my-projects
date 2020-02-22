package com.venada.efinance.pojo;

import com.venada.efinance.common.pojo.BaseEntity;

import java.util.Date;

public class ValidateCode extends BaseEntity{

	private static final long serialVersionUID = -3217489550277629645L;
	
	private String mobileNumber;
	private String code;
	private Date sendTime;
	private Date invalidTime;
	public String getMobileNumber() {
		return mobileNumber;
	}
	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public Date getSendTime() {
		return sendTime;
	}
	public void setSendTime(Date sendTime) {
		this.sendTime = sendTime;
	}
	public Date getInvalidTime() {
		return invalidTime;
	}
	public void setInvalidTime(Date invalidTime) {
		this.invalidTime = invalidTime;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
}
