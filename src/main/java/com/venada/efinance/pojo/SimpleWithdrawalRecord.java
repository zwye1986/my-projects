package com.venada.efinance.pojo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@SuppressWarnings("serial")
public class SimpleWithdrawalRecord implements Serializable {
	/**
	 * 流水号
	 */
	private String serialNumber;

	/**
	 * 提现时间
	 */
	private Date dateTime;

	/**
	 * 提现状态，
	 */
	private String status;

	/**
	 * 提现金额
	 */
	private BigDecimal amount;

	/**
	 * 银行卡号
	 */
	private String cardNumber;

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

	public BigDecimal getAmount() {
		return amount.setScale(2);
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public String getCardNumber() {
		return cardNumber;
	}

	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}
}
