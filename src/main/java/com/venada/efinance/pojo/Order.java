package com.venada.efinance.pojo;

import com.venada.efinance.common.util.Constant;

import java.util.Date;


public class Order extends Goods {
	
	private Long id;

	private Object priceConvert;
	
	private Object 	totalPriceConvert ;
	
	private Object paymentConvert;

	private String attentionName;

	private String attentionAddress;

	private String postCode;

	private String mobilePhone;

	private String liveCity;

	private String liveProvince;

	private String liveArea;
	
	private Integer isNegotiate;
	
	private String mobileNumberBuy;
	
	private Integer tradeStatus;
	
	private Integer dealStatus;
	
	private String remark;
	
	private Date cancelTime;
	
	private String serialNumber;
	



	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public Object getPriceConvert() {
		return priceConvert;
	}

	
	public Object getTotalPriceConvert() {
		return totalPriceConvert;
	}

	public void setTotalPriceConvert(Object totalPriceConvert) {
		if(String.valueOf(totalPriceConvert).equals("面议")){
			this.setTotalPrice(0d);
			this.setIsNegotiate(Constant.IsNegotiate);
		}else{
			this.setTotalPrice(Double.valueOf(totalPriceConvert.toString()));
			this.setIsNegotiate(Constant.IsNotNegotiate);
		}
	}

	public Object getPaymentConvert() {
		return paymentConvert;
	}

	public void setPaymentConvert(Object paymentConvert) {
		if(String.valueOf(paymentConvert).equals("面议")){
			this.setPayment(0d);
		}else{
			this.setPayment(Double.valueOf(paymentConvert.toString()));
		}
	}

	public void setPriceConvert(Object priceConvert) {
		if(String.valueOf(priceConvert).equals("面议")){
			this.setPrice(0d); 
		}else{
			this.setPrice(Double.valueOf(priceConvert.toString()));
		}
	}

	
	public String getAttentionName() {
		return attentionName;
	}

	public void setAttentionName(String attentionName) {
		this.attentionName = attentionName;
	}

	public String getAttentionAddress() {
		return attentionAddress;
	}

	public void setAttentionAddress(String attentionAddress) {
		this.attentionAddress = attentionAddress;
	}

	public String getPostCode() {
		return postCode;
	}

	public void setPostCode(String postCode) {
		this.postCode = postCode;
	}

	public String getMobilePhone() {
		return mobilePhone;
	}

	public void setMobilePhone(String mobilePhone) {
		this.mobilePhone = mobilePhone;
	}

	public String getLiveCity() {
		return liveCity;
	}

	public void setLiveCity(String liveCity) {
		this.liveCity = liveCity;
	}

	public String getLiveProvince() {
		return liveProvince;
	}

	public void setLiveProvince(String liveProvince) {
		this.liveProvince = liveProvince;
	}

	public String getLiveArea() {
		return liveArea;
	}

	public void setLiveArea(String liveArea) {
		this.liveArea = liveArea;
	}


	public Integer getIsNegotiate() {
		return isNegotiate;
	}


	public void setIsNegotiate(Integer isNegotiate) {
		this.isNegotiate = isNegotiate;
	}




	public String getMobileNumberBuy() {
		return mobileNumberBuy;
	}


	public void setMobileNumberBuy(String mobileNumberBuy) {
		this.mobileNumberBuy = mobileNumberBuy;
	}


	public Integer getTradeStatus() {
		return tradeStatus;
	}


	public void setTradeStatus(Integer tradeStatus) {
		this.tradeStatus = tradeStatus;
	}


	public Integer getDealStatus() {
		return dealStatus;
	}


	public void setDealStatus(Integer dealStatus) {
		this.dealStatus = dealStatus;
	}


	public String getRemark() {
		return remark;
	}


	public void setRemark(String remark) {
		this.remark = remark;
	}


	public Date getCancelTime() {
		return cancelTime;
	}


	public void setCancelTime(Date cancelTime) {
		this.cancelTime = cancelTime;
	}


	public String getSerialNumber() {
		return serialNumber;
	}


	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
	}



	
	
}
