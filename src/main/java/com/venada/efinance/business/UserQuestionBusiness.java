package com.venada.efinance.business;

import com.venada.efinance.common.exception.BusinessException;
import com.venada.efinance.pojo.UserQuestion;

public interface UserQuestionBusiness {
	public void saveUserQuestion(UserQuestion userQuestion) throws BusinessException;
	public UserQuestion getAnswerByUserid(String userid) throws BusinessException;
}
