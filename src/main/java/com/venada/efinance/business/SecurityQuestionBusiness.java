package com.venada.efinance.business;

import com.venada.efinance.common.exception.BusinessException;
import com.venada.efinance.pojo.SecurityQuestion;

import java.util.List;

public interface SecurityQuestionBusiness {
	
	public void addSecurityQuestion(SecurityQuestion entity	) throws BusinessException ;
	
	public int deleteSecurityQuestion(String id) throws BusinessException ;
	
	public SecurityQuestion getSecurityQuestionById(String id ) throws BusinessException ;
	
	public List<SecurityQuestion> getAll() throws BusinessException ;
	
	public int updateSecurityQuestion(SecurityQuestion entity) throws BusinessException ;

}
