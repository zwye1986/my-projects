package com.venada.efinance.business;

import com.venada.efinance.common.exception.BusinessException;
import com.venada.efinance.pojo.OperationLog;

public interface OperationLogBusiness {
	
	public int addOperationLog(OperationLog log) throws BusinessException ;

}
