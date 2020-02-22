package com.venada.efinance.pojo;

import com.venada.efinance.common.pojo.BaseEntity;

import java.math.BigDecimal;

/**
 * 用户电子钱包实体类
 * 
 * @author yma
 * 
 */
@SuppressWarnings("serial")
public class UserWallet extends BaseEntity {

	/**
	 * 金额
	 */
	private BigDecimal amount;

	/**
	 * 钱包状态
	 */
	private String status;

	/**
	 * 用户ID
	 */
	private String userId;

	public BigDecimal getAmount() {
		if(amount != null){
			amount = amount.setScale(2,BigDecimal.ROUND_DOWN);
		}
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

}