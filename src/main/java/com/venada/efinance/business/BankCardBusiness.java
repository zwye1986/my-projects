package com.venada.efinance.business;

import com.venada.efinance.common.exception.BusinessException;
import com.venada.efinance.pojo.BankCard;

import java.util.List;
import java.util.Map;

public interface BankCardBusiness {
	
	public List<BankCard> getAllByUserId(String userId) throws BusinessException ;
	
	public void saveBankCard (BankCard bankCard) throws BusinessException ;
	
	public BankCard getBankCardById(String id) throws BusinessException ;
	
	public int delUserCardById(Map<String,String> map) throws BusinessException ;

    public List<BankCard> getBankCardByConditions(Map<String,Object> conditions) throws BusinessException ;

}
