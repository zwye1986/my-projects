package com.venada.efinance.business;

import com.venada.efinance.common.exception.BusinessException;
import com.venada.efinance.common.util.PaginationMore;
import com.venada.efinance.pojo.ObtainCredits;

import java.util.List;
import java.util.Map;

public interface UserObtainCreditsBusiness {
	
	public List<ObtainCredits> queryObtainCredits(Map<String,Object> condition,PaginationMore page)throws BusinessException;
	
	public List<ObtainCredits> queryAllObtainCreditsByMobileNumber(Map<String,Object> condition)throws BusinessException;

	public void addObtainCredits(ObtainCredits obtainCredits) throws BusinessException;
	
	public ObtainCredits queryObtainCreditsById(Map<String,Object> condition)throws BusinessException;

	public void updateObtainCreditsById(ObtainCredits obtainCredits)throws BusinessException;

	public void deleteObtainCreditsById(int id) throws BusinessException;
	
	public  int queryAllObtainCreditsCount(Map<String, Object> condition) throws BusinessException ;
	
}
