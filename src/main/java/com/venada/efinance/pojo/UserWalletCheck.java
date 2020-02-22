package com.venada.efinance.pojo;

import java.math.BigDecimal;

/**
 * 检查用户余额是否正确实体类
 * 
 * @author hepei
 * 
 */
public class UserWalletCheck {

	private User user;

	private String mobileNumber;

	private BigDecimal recharge;

	private BigDecimal withdraw;

	private BigDecimal invite;
	
	private BigDecimal gapprice;
	
	
	

	public BigDecimal getGapprice() {
		return gapprice;
	}

	public void setGapprice(BigDecimal gapprice) {
		this.gapprice = gapprice;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	public BigDecimal getRecharge() {
		return recharge;
	}

	public void setRecharge(BigDecimal recharge) {
		this.recharge = recharge;
	}

	public BigDecimal getWithdraw() {
		return withdraw;
	}

	public void setWithdraw(BigDecimal withdraw) {
		this.withdraw = withdraw;
	}

	public BigDecimal getInvite() {
		return invite;
	}

	public void setInvite(BigDecimal invite) {
		this.invite = invite;
	}

	public BigDecimal getSignbenefit() {
		return signbenefit;
	}

	public void setSignbenefit(BigDecimal signbenefit) {
		this.signbenefit = signbenefit;
	}

	public BigDecimal getOngoing_task() {
		return ongoing_task;
	}

	public void setOngoing_task(BigDecimal ongoing_task) {
		this.ongoing_task = ongoing_task;
	}

	public BigDecimal getRebate() {
		return rebate;
	}

	public void setRebate(BigDecimal rebate) {
		this.rebate = rebate;
	}

	public BigDecimal getFee_deduction() {
		return fee_deduction;
	}

	public void setFee_deduction(BigDecimal fee_deduction) {
		this.fee_deduction = fee_deduction;
	}

	public BigDecimal getWallet() {
		return wallet;
	}

	public void setWallet(BigDecimal wallet) {
		this.wallet = wallet;
	}

	private BigDecimal signbenefit;
	
	private BigDecimal ongoing_task;

	private BigDecimal rebate;

	private BigDecimal fee_deduction;

	private BigDecimal wallet;
	
	
	
	
}