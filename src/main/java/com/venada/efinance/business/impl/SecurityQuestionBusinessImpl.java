package com.venada.efinance.business.impl;

import com.venada.efinance.business.SecurityQuestionBusiness;
import com.venada.efinance.common.exception.BusinessException;
import com.venada.efinance.common.exception.ServiceException;
import com.venada.efinance.pojo.SecurityQuestion;
import com.venada.efinance.service.SecurityQuestionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
public class SecurityQuestionBusinessImpl implements SecurityQuestionBusiness {
	private static final Logger logger = LoggerFactory.getLogger(SecurityQuestionBusinessImpl.class);

	@Autowired
	private SecurityQuestionService securityQuestionService;

	@Transactional
	public void addSecurityQuestion(SecurityQuestion entity) throws BusinessException {
		try {
			entity.setId(UUID.randomUUID().toString());
			securityQuestionService.saveObject("addSecurityQuestion", entity);
		} catch (ServiceException e) {
			logger.error(e.getMessage());
			throw new BusinessException("0002", new String[] {"addSecurityQuestion()方法出错！\t", e.getMessage() });
		}

	}

	@Transactional
	public int deleteSecurityQuestion(String id) throws BusinessException {
		try {
			return securityQuestionService.deleteObject("delSecurityQuestionById", id);
		} catch (ServiceException e) {
			logger.error(e.getMessage());
			throw new BusinessException("0002", new String[] {"deleteSecurityQuestion()方法出错！\t", e.getMessage() });
		}
	}

	public SecurityQuestion getSecurityQuestionById(String id) throws BusinessException {
		try {
			return (SecurityQuestion) securityQuestionService.getObject("selectSecurityQuestionById", id);
		} catch (ServiceException e) {
			logger.error(e.getMessage());
			throw new BusinessException("0002", new String[] {"getSecurityQuestionById()方法出错！\t", e.getMessage() });
		}
	}

	public List<SecurityQuestion> getAll() throws BusinessException {
		List<SecurityQuestion> list = null;
		try {
			list = securityQuestionService.findObjects("selectListByCondition", null); 
		} catch (ServiceException e) {
			e.printStackTrace();
			throw new BusinessException("0002", new String[] {"getAll()方法出错！\t", e.getMessage() });
		}
		return list;
	}

	@Transactional
	public int updateSecurityQuestion(SecurityQuestion entity) throws BusinessException {
		try {
			return securityQuestionService.updateObject("updateSecurityQuestion", entity);
		} catch (ServiceException e) {
			logger.error(e.getMessage());
			throw new BusinessException("0002", new String[] {"updateSecurityQuestion()方法出错！\t", e.getMessage() });
		}
	}

}
