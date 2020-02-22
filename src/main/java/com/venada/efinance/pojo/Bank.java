package com.venada.efinance.pojo;

public class Bank {
	public int bankId;
	public String bankName;
	public String shortName;
	public String indexChar;
	public int isHot;
	public int getBankId() {
		return bankId;
	}
	public void setBankId(int bankId) {
		this.bankId = bankId;
	}
	public String getBankName() {
		return bankName;
	}
	public void setBankName(String bankName) {
		this.bankName = bankName;
	}
	public String getShortName() {
		return shortName;
	}
	public void setShortName(String shortName) {
		this.shortName = shortName;
	}
	public int getIsHot() {
		return isHot;
	}
	public void setIsHot(int isHot) {
		this.isHot = isHot;
	}
	public String getIndexChar() {
		return indexChar;
	}
	public void setIndexChar(String indexChar) {
		this.indexChar = indexChar;
	}
	
}
