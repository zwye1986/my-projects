package com.venada.efinance.weixin.message.req;

/**
 * 文本消息
 * 
 * @author hepei
 * @date 2014-7-09
 */
public class TextMessage extends BaseMessage {
	// 消息内容
	private String Content;

	public String getContent() {
		return Content;
	}

	public void setContent(String content) {
		Content = content;
	}
}
