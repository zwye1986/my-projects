package com.venada.efinance.pojo;

import com.venada.efinance.common.pojo.BaseEntity;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 充值流水号实体类
 * 
 * @author yma
 * 
 */
public class WithdrawalRecord extends BaseEntity {

	private static final long serialVersionUID = 2394647458458145803L;
	
	private String bankCardId;

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
	 * IP地址
	 */
	private String ipAddress;

	/**
	 * 提现金额
	 */
	private BigDecimal amount;

	private BigDecimal balance;

	private String description;

	/**
	 * 普通用户提现付款日
	 */
	private Date cashDay;

	/**
	 * VIP用户提现付款日
	 */
	private Date vipCashDay;
	
	private Date withdrawalSuccessTime;

	private String userId;

	private String cardNumber;
	private String userName;
	private String bankProvinceid;
	private String bankCityid;
	private String bankid;
	private String subBank;
	private String bankName;
	private String province;
	private String subBankName;
	private String cityName;
	private String mobileNumber;
	private int cardStatus;

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
		if(amount != null){
			amount = amount.setScale(2,BigDecimal.ROUND_DOWN);
		}
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public BigDecimal getBalance() {
		if(balance != null){
			balance = balance.setScale(2,BigDecimal.ROUND_DOWN);
		}
		return balance;
	}

	public void setBalance(BigDecimal balance) {
		this.balance = balance;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getCardNumber() {
		return cardNumber;
	}

	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getBankProvinceid() {
		return bankProvinceid;
	}

	public void setBankProvinceid(String bankProvinceid) {
		this.bankProvinceid = bankProvinceid;
	}

	public String getBankCityid() {
		return bankCityid;
	}

	public void setBankCityid(String bankCityid) {
		this.bankCityid = bankCityid;
	}

	public String getBankid() {
		return bankid;
	}

	public void setBankid(String bankid) {
		this.bankid = bankid;
	}

	public String getSubBank() {
		return subBank;
	}

	public void setSubBank(String subBank) {
		this.subBank = subBank;
	}

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getSubBankName() {
		return subBankName;
	}

	public void setSubBankName(String subBankName) {
		this.subBankName = subBankName;
	}

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	public String getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	public int getCardStatus() {
		return cardStatus;
	}

	public void setCardStatus(int cardStatus) {
		this.cardStatus = cardStatus;
	}

	public Date getVipCashDay() {
		return vipCashDay;
	}

	public void setVipCashDay(Date vipCashDay) {
		this.vipCashDay = vipCashDay;
	}

	public Date getCashDay() {
		return cashDay;
	}

	public void setCashDay(Date cashDay) {
		this.cashDay = cashDay;
	}

	public String getBankCardId() {
		return bankCardId;
	}

	public void setBankCardId(String bankCardId) {
		this.bankCardId = bankCardId;
	}

	public Date getWithdrawalSuccessTime() {
		return withdrawalSuccessTime;
	}

	public void setWithdrawalSuccessTime(Date withdrawalSuccessTime) {
		this.withdrawalSuccessTime = withdrawalSuccessTime;
	}
	

}