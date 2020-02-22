package com.venada.efinance.pojo;

import com.venada.efinance.common.pojo.BaseEntity;
import com.venada.efinance.enumtype.DealType;

import java.math.BigDecimal;
import java.util.Date;

public class DealDetail extends BaseEntity {
	private static final long serialVersionUID = 4667582704517140552L;
	private String serialNumber;
	private Date dateTime;
	private DealType dealType;
	private String status;
	private String ipAddress;
	private BigDecimal amount;
	private String description;
	private BigDecimal balance;
	private String type;
	private String userId ;

	public String getSerialNumber() {
		return serialNumber;
	}

	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
	}

	public Date getDateTime() {
		return dateTime;
	}

	public void setDateTime(Date dateTime) {
		this.dateTime = dateTime;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getIpAddress() {
		return ipAddress;
	}

	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}


	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public BigDecimal getBalance() {
		return balance;
	}

	public void setBalance(BigDecimal balance) {
		this.balance = balance;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public int getDealType() {
		return dealType.getIndex();
	}

	public void setDealType(int index) {
		this.dealType = DealType.getEnum(index);
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

}
