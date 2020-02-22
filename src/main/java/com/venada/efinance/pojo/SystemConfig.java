package com.venada.efinance.pojo;

public class SystemConfig {

	private String paramName;
	private String paramValue;
	private String paramId;
	private String paramDesc;

	public String getParamDesc() {
		return paramDesc;
	}

	public void setParamDesc(String paramDesc) {
		this.paramDesc = paramDesc;
	}

	public String getParamName() {
		return paramName;
	}

	/**
	 * Sets the paramName.
	 * 
	 * @param paramName
	 *            the paramName to set
	 */
	public void setParamName(String paramName) {
		this.paramName = paramName;
	}

	public String getParamValue() {
		return paramValue;
	}

	/**
	 * Sets the paramValue.
	 * 
	 * @param paramValue
	 *            the paramValue to set
	 */
	public void setParamValue(String paramValue) {
		this.paramValue = paramValue;
	}

	public String getParamId() {
		return paramId;
	}

	/**
	 * Sets the paramId.
	 * 
	 * @param paramId
	 *            the paramId to set
	 */
	public void setParamId(String paramId) {
		this.paramId = paramId;
	}
}
