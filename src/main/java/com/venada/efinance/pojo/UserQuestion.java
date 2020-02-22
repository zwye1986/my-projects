package com.venada.efinance.pojo;

import com.venada.efinance.common.pojo.BaseEntity;

public class UserQuestion extends BaseEntity {
	private static final long serialVersionUID = -9154052735407534030L;
	private String name;
	private String userid;
	private String questionId;
	private String answer;

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getQuestionId() {
		return questionId;
	}

	public void setQuestionId(String questionId) {
		this.questionId = questionId;
	}

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
}
