package com.venada.efinance.pojo;

import com.venada.efinance.enumtype.DealType;

import java.math.BigDecimal;
import java.util.Date;
/**
 * 交易明细
 * @author zhangwy
 *
 */
public class TransactionDetails {
	private String userid;
	/**
	 * 交易时间
	 */
	private Date time;
	/**
	 * 交易描述
	 */
	private String description;
	
	private BigDecimal amount;
	/**
	 * 交易类型
	 * 1、收入
	 * 2、支出
	 */
	private int type;
	/**
	 * 交易状态
	 *  0:成功
	 *  1：失败
	 *  2：正在处理
	 */
	private int status;
	
	/**
	 * 余额
	 */
	private BigDecimal balance;
	
	private DealType dealType;
	
	private String serialNumber;
	
	private Date cashDay;
	
	private Date vipCashDay;
	
	public int getDealType() {
		return dealType.getIndex();
	}
	public void setDealType(int dealType) {
		this.dealType = DealType.getEnum(dealType);
	}
	public Date getTime() {
		return time;
	}
	public void setTime(Date time) {
		this.time = time;
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
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public BigDecimal getAmount() {
		return amount;
	}
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getSerialNumber() {
		return serialNumber;
	}
	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
	}
	public Date getCashDay() {
		return cashDay;
	}
	public void setCashDay(Date cashDay) {
		this.cashDay = cashDay;
	}
	public Date getVipCashDay() {
		return vipCashDay;
	}
	public void setVipCashDay(Date vipCashDay) {
		this.vipCashDay = vipCashDay;
	}
}