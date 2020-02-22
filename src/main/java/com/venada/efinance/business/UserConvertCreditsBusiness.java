package com.venada.efinance.business;

import com.venada.efinance.common.exception.BusinessException;
import com.venada.efinance.common.util.PaginationMore;
import com.venada.efinance.pojo.Commodity;
import com.venada.efinance.pojo.ConvertCredits;
import com.venada.efinance.pojo.User;

import java.util.List;
import java.util.Map;

public interface UserConvertCreditsBusiness {
	
	public List<ConvertCredits> queryConvertCredits(Map<String,Object> condition,PaginationMore page)throws BusinessException;
	
	public void addConvertCredits(ConvertCredits convertCredits) throws BusinessException;
	
	public ConvertCredits queryConvertCreditsById(Map<String,Object> condition)throws BusinessException;

	public void updateConvertCreditsById(ConvertCredits convertCredits)throws BusinessException;

	public void deleteConvertCreditsById(int id) throws BusinessException;
	
	public int exchange(User user,Commodity commodity,String mobilePhone) throws BusinessException;
	
	public List<ConvertCredits> queryConvertCreditsByMobilePhone(Map<String,Object> condition)throws BusinessException;

	public  int queryAllConvertCreditsCount(Map<String, Object> condition)
			throws BusinessException;
	
	public int conSumeCredits(String userid,int credits) throws BusinessException;
}
