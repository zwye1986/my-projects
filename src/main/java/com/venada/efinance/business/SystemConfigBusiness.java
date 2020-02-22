package com.venada.efinance.business;

import com.venada.efinance.common.exception.BusinessException;
import com.venada.efinance.common.util.PaginationMore;
import com.venada.efinance.pojo.SystemConfig;

import java.util.List;
import java.util.Map;


public interface SystemConfigBusiness {
	
	public int saveSystemConfig(SystemConfig obj) throws  BusinessException;
	
	public SystemConfig getSystemConfig(String id) throws BusinessException ;
	
	public int updateSystemConfig(SystemConfig obj) throws BusinessException ;
	
	public List<SystemConfig> getSystemConfigAll(PaginationMore page,Map<String,Object> map) throws BusinessException ;
	
	public int deleteSystemConfig(String... ids) throws BusinessException ;
	
	public int getSystemConfigAllCount(Map<String,Object> map) throws BusinessException ;

}
