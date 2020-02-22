package com.venada.efinance.pojo;


import com.venada.efinance.common.pojo.BaseEntity;

import java.util.Arrays;
import java.util.Date;

public class UserDetail extends BaseEntity{
	private static final long serialVersionUID = -8689038881936859173L;
	private String userid;
	private String mobileNumber;
	private String email;
	private String idCard;
	private int gender;
	private byte[] photo;
	private Date birthday;
	private int isMarried;
	private String incomeScope;
	private int eduLevel;
	private int liveProvince;
	private int liveCity;
	private int liveArea;
	private int hometownProvince;
	private int hometownCity;
	private int hometownArea;
	private String graduated;
	private String profession;
	private String hobbies;
	private String remark;
	private String qq;
	
	private String address;
	
	public String getGraduated() {
		return graduated;
	}
	public void setGraduated(String graduated) {
		this.graduated = graduated;
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
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getIdCard() {
		return idCard;
	}
	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}
	public int getGender() {
		return gender;
	}
	public void setGender(int gender) {
		this.gender = gender;
	}
	public byte[] getPhoto() {
		return photo;
	}
	public void setPhoto(byte[] photo) {
		this.photo = photo;
	}
	public Date getBirthday() {
		return birthday;
	}
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	public int getIsMarried() {
		return isMarried;
	}
	public void setIsMarried(int isMarried) {
		this.isMarried = isMarried;
	}
	
	public int getLiveProvince() {
		return liveProvince;
	}
	public String getIncomeScope() {
		return incomeScope;
	}
	public void setIncomeScope(String incomeScope) {
		this.incomeScope = incomeScope;
	}
	public int getEduLevel() {
		return eduLevel;
	}
	public void setEduLevel(int eduLevel) {
		this.eduLevel = eduLevel;
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
	public int getHometownProvince() {
		return hometownProvince;
	}
	public void setHometownProvince(int hometownProvince) {
		this.hometownProvince = hometownProvince;
	}
	public int getHometownCity() {
		return hometownCity;
	}
	public void setHometownCity(int hometownCity) {
		this.hometownCity = hometownCity;
	}
	public int getHometownArea() {
		return hometownArea;
	}
	public void setHometownArea(int hometownArea) {
		this.hometownArea = hometownArea;
	}
	public String getProfession() {
		return profession;
	}
	public void setProfession(String profession) {
		this.profession = profession;
	}
	public String getHobbies() {
		return hobbies;
	}
	public void setHobbies(String hobbies) {
		this.hobbies = hobbies;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getQq() {
		return qq;
	}
	public void setQq(String qq) {
		this.qq = qq;
	}
	@Override
	public String toString() {
		return "UserDetail [userid=" + userid + ", mobileNumber="
				+ mobileNumber + ", email=" + email + ", idCard=" + idCard
				+ ", gender=" + gender + ", photo=" + Arrays.toString(photo)
				+ ", birthday=" + birthday + ", isMarried=" + isMarried
				+ ", incomeScope=" + incomeScope + ", eduLevel=" + eduLevel
				+ ", liveProvince=" + liveProvince + ", liveCity=" + liveCity
				+ ", liveArea=" + liveArea + ", hometownProvince="
				+ hometownProvince + ", hometownCity=" + hometownCity
				+ ", hometownArea=" + hometownArea + ", graduated=" + graduated
				+ ", profession=" + profession + ", hobbies=" + hobbies
				+ ", remark=" + remark + ", qq=" + qq + ", address=" + address
				+ "]";
	}
	
}
