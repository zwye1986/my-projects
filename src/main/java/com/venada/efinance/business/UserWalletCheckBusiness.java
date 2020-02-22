package com.venada.efinance.business;

import com.venada.efinance.common.exception.BusinessException;
import com.venada.efinance.common.util.PaginationMore;
import com.venada.efinance.pojo.UserWalletCheck;

import java.util.List;
import java.util.Map;

public interface UserWalletCheckBusiness {
	
	public List<UserWalletCheck> getAllUserWalletCheck(Map<String,Object> condition,PaginationMore page) throws BusinessException ;
	
	public int getAllUserWalletCheckCount(Map<String,Object> condition) throws BusinessException ;

}
