package com.venada.efinance.weixin.message.req;

/**
 * 图片消息
 * 
 * @author hepei
 * @date 2014-7-09
 */
public class ImageMessage extends BaseMessage {
	// 图片链接
	private String PicUrl;

	public String getPicUrl() {
		return PicUrl;
	}

	public void setPicUrl(String picUrl) {
		PicUrl = picUrl;
	}
}
