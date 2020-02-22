package com.venada.efinance.pojo;

public class PayInfo {

//	private static final String URL = "http://www.wowpower.cn";
//	private String merchantAccount = "sunwen@venada.com.cn";
	private static final String URL = "http://202.102.90.72:8888";
	private String merchantAccount = "sunwen_b2@venada.com.cn";
	private String appName = "蛙宝网";
	private String customerId;
	private String memo = "纳币充值";
	private String fundingType;
	private String trackingId;
	private String actionType = "PAY";
	private String currencyCode = "USD";
	private String cancelURL = URL + "/index.html";	
	private String returnURL = URL + "/user/account/manager";
	private String ipnNotificationURL = URL+"/notifyPay/ipn";
	private String amount = "0";

	public String getMerchantAccount() {
		return merchantAccount;
	}

	public void setMerchantAccount(String merchantAccount) {
		this.merchantAccount = merchantAccount;
	}

	public String getAppName() {
		return appName;
	}

	public void setAppName(String appName) {
		this.appName = appName;
	}

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public String getFundingType() {
		return fundingType;
	}

	public void setFundingType(String fundingType) {
		this.fundingType = fundingType;
	}

	public String getTrackingId() {
		return trackingId;
	}

	public void setTrackingId(String trackingId) {
		this.trackingId = trackingId;
	}

	public String getActionType() {
		return actionType;
	}

	public void setActionType(String actionType) {
		this.actionType = actionType;
	}

	public String getCurrencyCode() {
		return currencyCode;
	}

	public void setCurrencyCode(String currencyCode) {
		this.currencyCode = currencyCode;
	}

	public String getCancelURL() {
		return cancelURL;
	}

	public void setCancelURL(String cancelURL) {
		this.cancelURL = cancelURL;
	}

	public String getReturnURL() {
		return returnURL;
	}

	public void setReturnURL(String returnURL) {
		this.returnURL = returnURL;
	}

	public String getIpnNotificationURL() {
		return ipnNotificationURL;
	}

	public void setIpnNotificationURL(String ipnNotificationURL) {
		this.ipnNotificationURL = ipnNotificationURL;
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}
}
