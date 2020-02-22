package com.venada.efinance.business.impl;

import com.venada.efinance.business.LoginLogBusiness;
import com.venada.efinance.common.exception.BusinessException;
import com.venada.efinance.common.util.PaginationMore;
import com.venada.efinance.pojo.LoginLog;
import com.venada.efinance.service.LoginLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
@Service
public class LoginLogBusinessImpl implements LoginLogBusiness {
	@Autowired
	private LoginLogService loginLogService;

	@Override
	public List<LoginLog> querySvipLoginLog(Map<String, Object> condition,PaginationMore page)
			throws BusinessException {
		page.setTotalRows(querySvipLoginLogCount(condition));
		page.repaginate();
		return loginLogService.selectList("querySvipLoginLog", condition, page);
	}
	
	private Integer querySvipLoginLogCount(Map<String,Object> condition){
		return (Integer) loginLogService.getObject("querySvipLoginLogCount", condition);
	}

}
