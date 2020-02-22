package com.venada.efinance.pojo;

import java.math.BigDecimal;
import java.util.Date;

public class SignIn {

	private String id;
	private String mobilePhone;
	private Date signdatetime;
	private BigDecimal signbenefit;
	private Integer newSignCount;
	private String content;
	private String signday;
	private Date signDate;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	public String getMobilePhone() {
		return mobilePhone;
	}
	public void setMobilePhone(String mobilePhone) {
		this.mobilePhone = mobilePhone;
	}
	public Date getSigndatetime() {
		return signdatetime;
	}
	public void setSigndatetime(Date signdatetime) {
		this.signdatetime = signdatetime;
	}
	public BigDecimal getSignbenefit() {
		return signbenefit;
	}
	public void setSignbenefit(BigDecimal signbenefit) {
		this.signbenefit = signbenefit.setScale(2, BigDecimal.ROUND_DOWN);
	}
	public Integer getNewSignCount() {
		return newSignCount;
	}
	public void setNewSignCount(Integer newSignCount) {
		this.newSignCount = newSignCount;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getSignday() {
		return signday;
	}
	public void setSignday(String signday) {
		this.signday = signday;
	}
	public Date getSignDate() {
		return signDate;
	}
	public void setSignDate(Date signDate) {
		this.signDate = signDate;
	}
	
	
}
