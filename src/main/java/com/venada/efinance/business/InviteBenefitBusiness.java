package com.venada.efinance.business;

import com.venada.efinance.common.exception.BusinessException;
import com.venada.efinance.common.util.PaginationMore;
import com.venada.efinance.pojo.InviteBenefit;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public interface InviteBenefitBusiness {

	public void addInviteBenefit(InviteBenefit inviteBenefit)
			throws BusinessException;

	public List<InviteBenefit> queryInviteBenefit(Map<String, Object> condition,
			PaginationMore page) throws BusinessException;

	public Integer getInviteBenefitCount(Map<String, Object> condition)throws BusinessException;
	
	
	public BigDecimal getInviteBenefitTotal(Map<String, Object> condition)throws BusinessException;

	public List<InviteBenefit> queryInviteBenefitByPhone(Map<String, Object> condition) throws BusinessException;

	
}
