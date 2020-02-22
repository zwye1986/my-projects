package com.venada.efinance.pojo;

import java.math.BigDecimal;

public class ProjectInvest {

	private String id;
	private String project_id;
	private BigDecimal amount;
	private Integer support_num;
	private String content;
	private Integer orderbyId;
    private BigDecimal benefit_amount;
    private BigDecimal miss_benefit_amount;
    private String invest_name;
    private Integer already_support_num;
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getProject_id() {
		return project_id;
	}

	public void setProject_id(String project_id) {
		this.project_id = project_id;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public Integer getSupport_num() {
		return support_num;
	}

	public void setSupport_num(Integer support_num) {
		this.support_num = support_num;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}


	public Integer getOrderbyId() {
		return orderbyId;
	}

	public void setOrderbyId(Integer orderbyId) {
		this.orderbyId = orderbyId;
	}

	public BigDecimal getBenefit_amount() {
		return benefit_amount;
	}

	public void setBenefit_amount(BigDecimal benefit_amount) {
		this.benefit_amount = benefit_amount;
	}

	public BigDecimal getMiss_benefit_amount() {
		return miss_benefit_amount;
	}

	public void setMiss_benefit_amount(BigDecimal miss_benefit_amount) {
		this.miss_benefit_amount = miss_benefit_amount;
	}

	public String getInvest_name() {
		return invest_name;
	}

	public void setInvest_name(String invest_name) {
		this.invest_name = invest_name;
	}

	public Integer getAlready_support_num() {
		if(already_support_num==null){
			already_support_num=0;
		}
		return already_support_num;
	}

	public void setAlready_support_num(Integer already_support_num) {
		this.already_support_num = already_support_num;
	}
	

}
