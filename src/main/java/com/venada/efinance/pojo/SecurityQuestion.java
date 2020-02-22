package com.venada.efinance.pojo;

import com.venada.efinance.common.pojo.BaseEntity;

/**
 * 开启安全中心设置问题实体类
 * @author yma
 *
 */
public class SecurityQuestion extends BaseEntity {

	private static final long serialVersionUID = 7703981678898689227L;

	/**
	 * 问题名称
	 */
	private String name;

	/**
	 * 是否启用，默认为启用
	 */
	private Integer isOpen = 0;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getIsOpen() {
		return isOpen;
	}

	public void setIsOpen(Integer isOpen) {
		this.isOpen = isOpen;
	}

}
