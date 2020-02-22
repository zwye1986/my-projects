package com.venada.efinance.weixin.message.resp;

/**
 * 语音消息
 * 
 * @author hepei
 * @date 2014-7-09
 */
public class VoiceMessage extends BaseMessage {
	// 语音
	private Voice Voice;

	public Voice getVoice() {
		return Voice;
	}

	public void setVoice(Voice voice) {
		Voice = voice;
	}
}
