package com.venada.efinance.business.impl;

import com.venada.efinance.business.UserQuestionBusiness;
import com.venada.efinance.common.exception.BusinessException;
import com.venada.efinance.pojo.UserQuestion;
import com.venada.efinance.service.UserQuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserQuestionBusinessImpl implements UserQuestionBusiness {
	@Autowired
	private UserQuestionService questionService;

	@Override
	public void saveUserQuestion(UserQuestion userQuestion)
			throws BusinessException {
		questionService.saveObject("saveUserQuestion", userQuestion);
	}

	@Override
	public UserQuestion getAnswerByUserid(String userid)
			throws BusinessException {
		return (UserQuestion) questionService.getObject("getAnswerByUserid", userid);
	}
}
