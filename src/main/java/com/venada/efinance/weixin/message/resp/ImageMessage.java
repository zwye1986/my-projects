package com.venada.efinance.weixin.message.resp;

/**
 * 图片消息
 * 
 * @author hepei
 * @date 2014-7-09
 */
public class ImageMessage extends BaseMessage {
	// 图片
	private Image Image;

	public Image getImage() {
		return Image;
	}

	public void setImage(Image image) {
		Image = image;
	}
}
