package com.venada.efinance.pojo;

import java.util.Date;
import java.util.List;

public class Role {

	private Integer id;

	private String roleName;

	private String DESCRIPTION;

   private String createBy;
	
	private Date createTime;
	
	private Date lastUpdateTime;
	
	private String lastUpdateBy;
	
	private List<Resource> roleResources;

	public boolean isChecked() {
		return checked;
	}

	public void setChecked(boolean checked) {
		this.checked = checked;
	}

	private List<User> roleUsesrs;

	private String resSel;
	
	private boolean checked;
	
	
	public String getResSel() {
		return resSel;
	}

	public void setResSel(String resSel) {
		this.resSel = resSel;
	}

	public List<User> getRoleUsesrs() {
		return roleUsesrs;
	}

	public void setRoleUsesrs(List<User> roleUsesrs) {
		this.roleUsesrs = roleUsesrs;
	}

	public List<Resource> getRoleResources() {
		return roleResources;
	}

	public void setRoleResources(List<Resource> roleResources) {
		this.roleResources = roleResources;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getDESCRIPTION() {
		return DESCRIPTION;
	}

	public void setDESCRIPTION(String dESCRIPTION) {
		DESCRIPTION = dESCRIPTION;
	}

	public String getCreateBy() {
		return createBy;
	}

	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getLastUpdateTime() {
		return lastUpdateTime;
	}

	public void setLastUpdateTime(Date lastUpdateTime) {
		this.lastUpdateTime = lastUpdateTime;
	}

	public String getLastUpdateBy() {
		return lastUpdateBy;
	}

	public void setLastUpdateBy(String lastUpdateBy) {
		this.lastUpdateBy = lastUpdateBy;
	}
	
	

}
