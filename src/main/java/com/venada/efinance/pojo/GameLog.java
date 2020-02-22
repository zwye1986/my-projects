package com.venada.efinance.pojo;

import com.venada.efinance.common.pojo.BaseEntity;

/**
 * 
 * @author xupei
 * 
 */
public class GameLog extends BaseEntity {

	private static final long serialVersionUID = 1L;
	private String relationId;

	public String getRelationId() {
		return relationId;
	}

	public void setRelationId(String relationId) {
		this.relationId = relationId;
	}

}
