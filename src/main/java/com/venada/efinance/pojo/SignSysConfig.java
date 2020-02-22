package com.venada.efinance.pojo;

import java.math.BigDecimal;

public class SignSysConfig {

	private int id;
	
	private BigDecimal benefit;

	private BigDecimal assetBegin;

	private BigDecimal assetEnd;
	
	private int type;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public BigDecimal getBenefit() {
		return benefit;
	}

	public void setBenefit(BigDecimal benefit) {
		this.benefit = benefit;
	}

	public BigDecimal getAssetBegin() {
		return assetBegin;
	}

	public void setAssetBegin(BigDecimal assetBegin) {
		this.assetBegin = assetBegin;
	}

	public BigDecimal getAssetEnd() {
		return assetEnd;
	}

	public void setAssetEnd(BigDecimal assetEnd) {
		this.assetEnd = assetEnd;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}
	
	
	

}
