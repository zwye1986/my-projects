package com.venada.efinance.business;

import com.venada.efinance.common.exception.BusinessException;
import com.venada.efinance.pojo.SecurityCenter;

import java.util.Map;

public interface SecurityCenterBusiness {
	
	public SecurityCenter getSecurityCenterByUserId(String userId) throws BusinessException ;
	
	public int addSecurityCenter(SecurityCenter entity) throws BusinessException ;
	
	public int updateSecurityCenter(SecurityCenter entity) throws BusinessException ;
	public int updateSecurityCenterWithNoPassword(SecurityCenter entity) throws BusinessException ;
	
	public boolean isOpen(String userId) throws BusinessException ;
	
	public Map<String,String> openSecurityCenter(int isAutoRenew) throws BusinessException ;
	
	public int isAutoRenew(String userId) throws BusinessException ;
	
	public void cancelAutoRenewByUserId(String userId) throws BusinessException;
}
