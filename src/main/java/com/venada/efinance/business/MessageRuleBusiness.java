package com.venada.efinance.business;

import com.venada.efinance.common.exception.BusinessException;
import com.venada.efinance.common.util.PaginationMore;
import com.venada.efinance.pojo.MessageRule;
import com.venada.efinance.pojo.User;

import java.util.List;
import java.util.Map;

public interface MessageRuleBusiness {
	
	public void addMessageRule(MessageRule messageRule) throws BusinessException;

	public void updateMessageRuleById(MessageRule messageRule)throws BusinessException;

	public List<MessageRule> queryAllMessageRule(Map<String,Object> condition,PaginationMore page) throws BusinessException;
	
	public void deleteMessageRuleById(String id) throws BusinessException;
	
	public void updateMessageRule(Map<String,Object> params ) throws BusinessException;
	
	public MessageRule queryMessageRuleById(String id) throws BusinessException;

     public List<MessageRule> findMessageRule(String userid) throws BusinessException;
     
     public String getSendMobileNumber(User user) throws BusinessException;
     
     public boolean isSend(User user)  throws BusinessException;
}


