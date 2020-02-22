package com.venada.efinance.pojo;

import com.venada.efinance.common.pojo.BaseEntity;

import java.util.Date;

public class ConvertCredits extends BaseEntity {

	/**
	 */
	private static final long serialVersionUID = 1L;
	private String userId;
	private Date actionTime;
	private Long expendCredits;
	private String creditsGoodsId;
	private Integer num;
	private Integer status;
	private String content;
	private int actionType;
	private User user;
	private String creditsGoodsName;
	private String remark;
	private String mobileNumber;

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

	public int getActionType() {
		return actionType;
	}

	public void setActionType(int actionType) {
		this.actionType = actionType;
	}

	public Long getExpendCredits() {
		return expendCredits;
	}

	public void setExpendCredits(Long expendCredits) {
		this.expendCredits = expendCredits;
	}

	public String getCreditsGoodsId() {
		return creditsGoodsId;
	}

	public void setCreditsGoodsId(String creditsGoodsId) {
		this.creditsGoodsId = creditsGoodsId;
	}

	public Integer getNum() {
		return num;
	}

	public void setNum(Integer num) {
		this.num = num;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getCreditsGoodsName() {
		return creditsGoodsName;
	}

	public void setCreditsGoodsName(String creditsGoodsName) {
		this.creditsGoodsName = creditsGoodsName;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	
}
