package com.venada.efinance.pojo;

import java.util.Date;

public class Project {
	private String id;
	private String name;
	private String nameDesc;
	private Date endTime;
	private double goalMoney;
	private String projectDesc;
	private String explanation;
	private String type;
	private String addr;
	private Date createTime;
	private int days;
	private Date daysDate;
	private int endDays;
	private int status; // 0是有效 1是无效
	private String missRate;
	private String successRate;
	private byte[] projectPic;
	private int scale;
	private double sumAmount;
	private int persons;   

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNameDesc() {
		return nameDesc;
	}

	public void setNameDesc(String nameDesc) {
		this.nameDesc = nameDesc;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public double getGoalMoney() {
		return goalMoney;
	}

	public void setGoalMoney(double goalMoney) {
		this.goalMoney = goalMoney;
	}

	public String getProjectDesc() {
		return projectDesc;
	}

	public void setProjectDesc(String projectDesc) {
		this.projectDesc = projectDesc;
	}

	public String getExplanation() {
		return explanation;
	}

	public void setExplanation(String explanation) {
		this.explanation = explanation;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public byte[] getProjectPic() {
		return projectPic;
	}

	public void setProjectPic(byte[] projectPic) {
		this.projectPic = projectPic;
	}

	public String getAddr() {
		return addr;
	}

	public void setAddr(String addr) {
		this.addr = addr;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public int getDays() {
		return days;
	}

	public void setDays(int days) {
		this.days = days;
	}

	public int getEndDays() {
		return endDays;
	}

	public void setEndDays(int endDays) {
		this.endDays = endDays;
	}

	public int getScale() {
		return scale;
	}

	public void setScale(int scale) {
		this.scale = scale;
	}

	public double getSumAmount() {
		return sumAmount;
	}

	public void setSumAmount(double sumAmount) {
		this.sumAmount = sumAmount;
	}

	public int getPersons() {
		return persons;
	}

	public void setPersons(int persons) {
		this.persons = persons;
	}

	public Date getDaysDate() {
		return daysDate;
	}

	public void setDaysDate(Date daysDate) {
		this.daysDate = daysDate;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getMissRate() {
		return missRate;
	}

	public void setMissRate(String missRate) {
		this.missRate = missRate;
	}

	public String getSuccessRate() {
		return successRate;
	}

	public void setSuccessRate(String successRate) {
		this.successRate = successRate;
	}

}
