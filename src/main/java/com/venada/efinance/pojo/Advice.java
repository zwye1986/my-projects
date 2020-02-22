package com.venada.efinance.pojo;

import com.venada.efinance.common.pojo.BaseEntity;

import java.util.Date;

public class Advice extends BaseEntity {
	private static final long serialVersionUID = 4810699298845949676L;
	private String name;
	private String contact;
	private String advice;
	private String replyContent;
	private Date  replytime;
	private String replyer;
	private Integer replayStatus;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getContact() {
		return contact;
	}
	public void setContact(String contact) {
		this.contact = contact;
	}
	public String getAdvice() {
		return advice;
	}
	public void setAdvice(String advice) {
		this.advice = advice;
	}
	public String getReplyContent() {
		return replyContent;
	}
	public void setReplyContent(String replyContent) {
		this.replyContent = replyContent;
	}
	public Date getReplytime() {
		return replytime;
	}
	public void setReplytime(Date replytime) {
		this.replytime = replytime;
	}
	public String getReplyer() {
		return replyer;
	}
	public void setReplyer(String replyer) {
		this.replyer = replyer;
	}
	public Integer getReplayStatus() {
		return replayStatus;
	}
	public void setReplayStatus(Integer replayStatus) {
		this.replayStatus = replayStatus;
	}
	
	
}
