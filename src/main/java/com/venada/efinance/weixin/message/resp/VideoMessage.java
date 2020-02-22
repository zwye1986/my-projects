package com.venada.efinance.weixin.message.resp;

/**
 * 视频消息
 * 
 * @author hepei
 * @date 2014-7-09
 */
public class VideoMessage extends BaseMessage {
	// 视频
	private Video Video;

	public Video getVideo() {
		return Video;
	}

	public void setVideo(Video video) {
		Video = video;
	}
}
