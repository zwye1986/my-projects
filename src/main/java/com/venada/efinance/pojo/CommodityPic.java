package com.venada.efinance.pojo;

import java.util.Date;




/***
 * 
 * @author xupei
 * 
 */
public class CommodityPic {
	/**
	 * 
	 */
	

	private String id;

	private String commodityId; 

	private String name;
	
	private byte[] pic;
	
	private byte[] smallPic;
	
	private byte[] mediumPic;
	
	private Date addTime;
	


	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	
	public Date getAddTime() {
		return addTime;
	}

	public void setAddTime(Date addTime) {
		this.addTime = addTime;
	}

	public String getCommodityId() {
		return commodityId;
	}

	public void setCommodityId(String commodityId) {
		this.commodityId = commodityId;
	}

	public byte[] getPic() {
		return pic;
	}

	public void setPic(byte[] pic) {
		this.pic = pic;
	}

	public byte[] getSmallPic() {
		return smallPic;
	}

	public void setSmallPic(byte[] smallPic) {
		this.smallPic = smallPic;
	}

	public byte[] getMediumPic() {
		return mediumPic;
	}

	public void setMediumPic(byte[] mediumPic) {
		this.mediumPic = mediumPic;
	}







}
