package com.venada.efinance.pojo;

import com.venada.efinance.common.pojo.BaseEntity;


public class AdvertiseImag extends BaseEntity {
	private static final long serialVersionUID = 7298899845015573365L;
	private byte[] file;
	private String advertiseId;
	public byte[] getFile() {
		return file;
	}
	public void setFile(byte[] file) {
		this.file = file;
	}
	public String getAdvertiseId() {
		return advertiseId;
	}
	public void setAdvertiseId(String advertiseId) {
		this.advertiseId = advertiseId;
	}
	
	
	
}
