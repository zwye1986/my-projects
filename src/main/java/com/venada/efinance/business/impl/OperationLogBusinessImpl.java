package com.venada.efinance.business.impl;

import com.venada.efinance.business.OperationLogBusiness;
import com.venada.efinance.common.exception.BusinessException;
import com.venada.efinance.common.exception.ServiceException;
import com.venada.efinance.pojo.OperationLog;
import com.venada.efinance.service.OperationLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class OperationLogBusinessImpl implements OperationLogBusiness{
	
	@Autowired
	private OperationLogService operationLogService ;

	public int addOperationLog(OperationLog log) throws BusinessException {
		try {
			log.setId(UUID.randomUUID().toString());
			return operationLogService.saveObject("insertOperationLog", log);
		} catch (ServiceException e) {
			throw new BusinessException("0002", new String[] {"addOperationLog()方法出错！\t", e.getMessage() });
		}
	}

}
