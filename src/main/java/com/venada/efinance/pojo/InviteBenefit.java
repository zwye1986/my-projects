package com.venada.efinance.pojo;

import java.math.BigDecimal;
import java.util.Date;

public class InviteBenefit {
	
	private String id;
	private String userid;
	private String inviteUserId;
	private BigDecimal benefit;
	private String content;
	private Date actiontime;
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
	public String getInviteUserId() {
		return inviteUserId;
	}
	public void setInviteUserId(String inviteUserId) {
		this.inviteUserId = inviteUserId;
	}
	public BigDecimal getBenefit() {
		return benefit;
	}
	public void setBenefit(BigDecimal benefit) {
		this.benefit = benefit;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Date getActiontime() {
		return actiontime;
	}
	public void setActiontime(Date actiontime) {
		this.actiontime = actiontime;
	}
	
	

}
