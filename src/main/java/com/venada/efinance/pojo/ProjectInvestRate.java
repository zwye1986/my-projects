package com.venada.efinance.pojo;

import java.math.BigDecimal;

public class ProjectInvestRate {

	private String id;
	private Integer interval;
	private BigDecimal successRate;
	private BigDecimal missRate;
	private String rate_name;
	private String content;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Integer getInterval() {
		return interval;
	}

	public void setInterval(Integer interval) {
		this.interval = interval;
	}

	public BigDecimal getSuccessRate() {
		return successRate;
	}

	public void setSuccessRate(BigDecimal successRate) {
		this.successRate = successRate;
	}

	public BigDecimal getMissRate() {
		return missRate;
	}

	public void setMissRate(BigDecimal missRate) {
		this.missRate = missRate;
	}

	public String getRate_name() {
		return rate_name;
	}

	public void setRate_name(String rate_name) {
		this.rate_name = rate_name;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

}
