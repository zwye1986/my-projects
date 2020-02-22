package com.venada.efinance.business;

import com.venada.efinance.common.exception.BusinessException;
import com.venada.efinance.pojo.DealDetail;

import java.math.BigDecimal;
import java.util.Map;

public interface DealDetailBusiness {
	public BigDecimal getDealDetailAmountByUserId(String userid) throws BusinessException;
	
	public int addDealDetail(DealDetail entity) throws BusinessException ;
	
	public BigDecimal getUserDealdetailAmountByDetailtype(Map<String,Object> conditions) throws BusinessException ;
	
	public BigDecimal countTotalWithdrawalFeeForSvip(String userid) throws BusinessException ;
	
}
