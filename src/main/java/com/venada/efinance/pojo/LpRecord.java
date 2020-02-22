package com.venada.efinance.pojo;

import java.util.Date;



/***
 * 
 * @author xupei
 * 
 */
public class LpRecord {
	/**
	 * 
	 */
	

	private String id;
	private int category; // 类别：为以后自增其他活动做预留   1:代表注册送10纳币的活动

	private String userId; // 游戏描述

	private Date addTime; // 游戏名称

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public int getCategory() {
		return category;
	}

	public void setCategory(int category) {
		this.category = category;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public Date getAddTime() {
		return addTime;
	}

	public void setAddTime(Date addTime) {
		this.addTime = addTime;
	}


}
