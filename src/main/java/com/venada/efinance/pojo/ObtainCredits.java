package com.venada.efinance.pojo;

import com.venada.efinance.common.pojo.BaseEntity;

import java.util.Date;

public class ObtainCredits extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String userId;
	private Date actionTime;
	private Long credits;
	private int actionType;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public Date getActionTime() {
		return actionTime;
	}

	public void setActionTime(Date actionTime) {
		this.actionTime = actionTime;
	}

	public Long getCredits() {
		return credits;
	}

	public void setCredits(Long credits) {
		this.credits = credits;
	}

	public int getActionType() {
		return actionType;
	}

	public void setActionType(int actionType) {
		this.actionType = actionType;
	}

}
