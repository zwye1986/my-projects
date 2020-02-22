package com.venada.efinance.business;

import com.venada.efinance.common.exception.BusinessException;
import com.venada.efinance.pojo.AmountOption;

import java.util.List;

public interface AmountOptionBusiness {
	
	public List<AmountOption> getAmountOptions() throws BusinessException ;
	
	public int addAmountOption(AmountOption entity) throws BusinessException ;
	
	public int updateAmountOption(AmountOption entity) throws BusinessException ;
	
	public int deleteAmountOption(String... id) throws BusinessException ;
	
	public int saveAmountOption(AmountOption entity) throws BusinessException ;
	
	public AmountOption getAmountOptionById(String id) throws BusinessException ;

}
