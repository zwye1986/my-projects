package com.venada.efinance.pojo;

import com.venada.efinance.common.pojo.BaseEntity;
/**
 * 
 * @author xupei
 *
 */
public class GameType extends BaseEntity {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String typeName;
	private int category;

	

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	public int getCategory() {
		return category;
	}

	public void setCategory(int category) {
		this.category = category;
	}
	
	

}
