package com.venada.efinance.pojo;

import java.util.Date;

public class News {
	private int id;
	private String newsTitle;
	private Date addTime;
	private String content;
	private String newsRef;
	private String newsLink;
	private int readCount;
	private String type;
	private String name; // 类别中文名称
	private String summary; // 摘要
	private String author;
	private int isImp;//是否高亮
	private String myAddTime; // 存放日期时间的字符型，当页面用json传递时用到
	private String myAddTimeDetail;
	
	private int priority;


	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getAddTime() {
		return addTime;
	}

	public void setAddTime(Date addTime) {
		this.addTime = addTime;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getNewsRef() {
		return newsRef;
	}

	public void setNewsRef(String newsRef) {
		this.newsRef = newsRef;
	}

	public String getNewsLink() {
		return newsLink;
	}

	public void setNewsLink(String newsLink) {
		this.newsLink = newsLink;
	}

	public int getReadCount() {
		return readCount;
	}

	public void setReadCount(int readCount) {
		this.readCount = readCount;
	}

	public String getNewsTitle() {
		return newsTitle;
	}

	public void setNewsTitle(String newsTitle) {
		this.newsTitle = newsTitle;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public String getMyAddTime() {
		return myAddTime;
	}

	public void setMyAddTime(String myAddTime) {
		this.myAddTime = myAddTime;
	}

	public String getMyAddTimeDetail() {
		return myAddTimeDetail;
	}

	public void setMyAddTimeDetail(String myAddTimeDetail) {
		this.myAddTimeDetail = myAddTimeDetail;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public int getPriority() {
		return priority;
	}

	public void setPriority(int priority) {
		this.priority = priority;
	}

	public int getIsImp() {
		return isImp;
	}

	public void setIsImp(int isImp) {
		this.isImp = isImp;
	}
	
}
