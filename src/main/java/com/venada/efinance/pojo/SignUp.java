package com.venada.efinance.pojo;

import java.util.Date;

public class SignUp {
	private String id;
	private int liveProvince;
	private int liveCity;
	private int liveArea;
	private String liveaddress; 
	private String mobileNumber;
	private String question;
	private String nickName;
	private Date createtime;
	private int status;
	private String userid;
    private String name;
    private byte[] photo;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public int getLiveProvince() {
		return liveProvince;
	}
	public void setLiveProvince(int liveProvince) {
		this.liveProvince = liveProvince;
	}
	public int getLiveCity() {
		return liveCity;
	}
	public void setLiveCity(int liveCity) {
		this.liveCity = liveCity;
	}
	public int getLiveArea() {
		return liveArea;
	}
	public void setLiveArea(int liveArea) {
		this.liveArea = liveArea;
	}
	public String getLiveaddress() {
		return liveaddress;
	}
	public void setLiveaddress(String liveaddress) {
		this.liveaddress = liveaddress;
	}
	public String getMobileNumber() {
		return mobileNumber;
	}
	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}
	public String getQuestion() {
		return question;
	}
	public void setQuestion(String question) {
		this.question = question;
	}
	public String getNickName() {
		return nickName;
	}
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	public Date getCreatetime() {
		return createtime;
	}
	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public byte[] getPhoto() {
		return photo;
	}
	public void setPhoto(byte[] photo) {
		this.photo = photo;
	}
	
}
