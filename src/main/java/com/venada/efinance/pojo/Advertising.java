package com.venada.efinance.pojo;

import com.venada.efinance.common.pojo.BaseEntity;


public class Advertising extends BaseEntity{
	private static final long serialVersionUID = 8815905049597454826L;
	private String name;
	private String width;
	private String height;
	private Integer location;
	private String href;
	private String remark;
    private Integer index;
    private String adv_position;
	/**
	 * @author zhangwy
	 * 广告投放的媒体类型 1:网页 2:android 客户端 3:ios客户端
	 */
	private int type;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getHref() {
		return href;
	}
	public void setHref(String href) {
		this.href = href;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getWidth() {
		return width;
	}
	public void setWidth(String width) {
		this.width = width;
	}
	public String getHeight() {
		return height;
	}
	public void setHeight(String height) {
		this.height = height;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}

    public void setLocation(Integer location) {
        this.location = location;
    }

    public Integer getIndex() {
        return index;
    }

    public void setIndex(Integer index) {
        this.index = index;
    }

    public Integer getLocation() {
        return location;
    }

    public String getAdv_position() {
        return adv_position;
    }

    public void setAdv_position(String adv_position) {
        this.adv_position = adv_position;
    }
}
