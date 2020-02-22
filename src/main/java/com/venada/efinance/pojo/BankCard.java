package com.venada.efinance.pojo;

import com.venada.efinance.common.pojo.BaseEntity;
import com.venada.efinance.enumtype.CardTypeEnum;

import java.util.Date;

public class BankCard extends BaseEntity  {
	
	private static final long serialVersionUID = 1L;
	private String bankName;
	private String cardNumber ;
	private int bankProvinceid;
	private int bankCityid;
	private int bankid ;
	private String branchBankName;
	private int status ;
	
	private CardTypeEnum cardType ;
	
	private Date bindTime ;
	
	private String bindUserId ;
	
	private Integer isDefault ;
	
	private int subBank;
	
	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public int getBankProvinceid() {
		return bankProvinceid;
	}

	public void setBankProvinceid(int bankProvinceid) {
		this.bankProvinceid = bankProvinceid;
	}

	public int getBankCityid() {
		return bankCityid;
	}

	public void setBankCityid(int bankCityid) {
		this.bankCityid = bankCityid;
	}

	public int getBankid() {
		return bankid;
	}

	public void setBankid(int bankid) {
		this.bankid = bankid;
	}

	public int getSubBank() {
		return subBank;
	}

	public void setSubBank(int subBank) {
		this.subBank = subBank;
	}

	public String getCardNumber() {
		return cardNumber;
	}

	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}


	public int getCardType() {
		return cardType.getIndex();
	}

	@SuppressWarnings("static-access")
	public void setCardType(int index) {
		this.cardType = cardType.getEnum(index);
	}

	public Date getBindTime() {
		return bindTime;
	}

	public void setBindTime(Date bindTime) {
		this.bindTime = bindTime;
	}

	public String getBindUserId() {
		return bindUserId;
	}

	public void setBindUserId(String bindUserId) {
		this.bindUserId = bindUserId;
	}

	public Integer getIsDefault() {
		return isDefault;
	}

	public void setIsDefault(Integer isDefault) {
		this.isDefault = isDefault;
	}

	public String getBranchBankName() {
		return branchBankName;
	}

	public void setBranchBankName(String branchBankName) {
		this.branchBankName = branchBankName;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	
}
