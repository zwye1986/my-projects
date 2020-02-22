package com.venada.efinance.business;

import com.venada.efinance.common.exception.BusinessException;
import com.venada.efinance.common.util.PaginationMore;
import com.venada.efinance.pojo.LoginLog;

import java.util.List;
import java.util.Map;

public interface LoginLogBusiness {
	public List<LoginLog> querySvipLoginLog(Map<String,Object> condition,PaginationMore page) throws BusinessException;
	
}
