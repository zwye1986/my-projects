package com.venada.efinance.pojo;

import java.util.Date;
import java.util.List;

public class Resource {

	private Integer id;

	private String type;

	private String value;

	private String modelName;

	private Integer parentId;
	
	private String createBy;
	
	private Date createTime;
	
	private Date lastUpdateTime;
	
	private String lastUpdateBy;

	private List<Role> resouceRole;
	
	private Resource parentMenu;

	private List<Resource> children;
	
	private boolean checked;

	public List<Role> getResouceRole() {
		return resouceRole;
	}

	public void setResouceRole(List<Role> resouceRole) {
		this.resouceRole = resouceRole;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getModelName() {
		return modelName;
	}

	public void setModelName(String modelName) {
		this.modelName = modelName;
	}

	public Integer getParentId() {
		return parentId;
	}

	public void setParentId(Integer parentId) {
		this.parentId = parentId;
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

	public Resource getParentMenu() {
		return parentMenu;
	}

	public void setParentMenu(Resource parentMenu) {
		this.parentMenu = parentMenu;
	}

	public List<Resource> getChildren() {
		return children;
	}

	public void setChildren(List<Resource> children) {
		this.children = children;
	}

	public boolean isChecked() {
		return checked;
	}

	public void setChecked(boolean checked) {
		this.checked = checked;
	}

	
	
	
}
