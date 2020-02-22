package com.venada.efinance.pojo;

import java.math.BigDecimal;
import java.util.Date;

public class OpponentData {
	private Integer id;
	
	private Date actiontime;
	
	private String telephone;
	
	private BigDecimal money;
	
	private Integer dotype;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getActiontime() {
		return actiontime;
	}

	public void setActiontime(Date actiontime) {
		this.actiontime = actiontime;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public BigDecimal getMoney() {
		return money;
	}

	public void setMoney(BigDecimal money) {
		this.money = money;
	}

	public Integer getDotype() {
		return dotype;
	}

	public void setDotype(Integer dotype) {
		this.dotype = dotype;
	}

	
	
	

}
