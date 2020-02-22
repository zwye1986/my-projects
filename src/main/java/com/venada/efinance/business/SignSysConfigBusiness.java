package com.venada.efinance.business;

import java.math.BigDecimal;
import java.util.List;

import com.venada.efinance.common.exception.BusinessException;
import com.venada.efinance.pojo.SignSysConfig;
import com.venada.efinance.pojo.User;


public interface SignSysConfigBusiness {
	
	
	public List<SignSysConfig> listSignSysConfig() throws BusinessException ;
	
	public BigDecimal getSignBenfitByUser(BigDecimal amount)throws BusinessException ;
	
	public BigDecimal getAllAmountByUser(User user)throws BusinessException ;
}
