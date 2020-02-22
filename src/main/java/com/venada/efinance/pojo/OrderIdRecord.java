package com.venada.efinance.pojo;

import java.math.BigDecimal;
import java.util.Date;



public class OrderIdRecord extends Goods {
	
	private String  orderId;

	private BigDecimal amount;
	
	private Date createTime ;
	
	private String userId;

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}
	
    
	
	
}
