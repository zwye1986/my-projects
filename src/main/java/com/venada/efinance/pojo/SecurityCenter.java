package com.venada.efinance.pojo;

import com.venada.efinance.common.pojo.BaseEntity;

import java.util.Date;

public class SecurityCenter extends BaseEntity {

	private static final long serialVersionUID = -2236506837620018654L;
	
	private String password ;
	
	private String mail ;
	
	private String mobile ;
	
	private String question ;
	
	private String answer ;
	
	private String userId ;
	
	private Date expiryDate ;
	
	private int isAutoRenew ;
	
	private int isOpen ;
	
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public Date getExpiryDate() {
		return expiryDate;
	}

	public void setExpiryDate(Date expiryDate) {
		this.expiryDate = expiryDate;
	}

	public int getIsAutoRenew() {
		return isAutoRenew;
	}

	public void setIsAutoRenew(int isAutoRenew) {
		this.isAutoRenew = isAutoRenew;
	}

	public int getIsOpen() {
		return isOpen;
	}

	public void setIsOpen(int isOpen) {
		this.isOpen = isOpen;
	}

}
