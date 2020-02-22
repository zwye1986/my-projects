package com.venada.efinance.business.impl;

import com.venada.efinance.business.InviteBenefitBusiness;
import com.venada.efinance.common.exception.BusinessException;
import com.venada.efinance.common.util.PaginationMore;
import com.venada.efinance.pojo.InviteBenefit;
import com.venada.efinance.service.InviteBenefitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * 
 * @author hepei
 *
 */
@Service(value="inviteBenefitBusinessImp")
public class InviteBenefitBusinessImp implements InviteBenefitBusiness {

	@Autowired
	private InviteBenefitService inviteBenefitServiceImpl;


	@Override
	public void addInviteBenefit(InviteBenefit inviteBenefit)
			throws BusinessException {
		inviteBenefitServiceImpl.saveObject("insertInviteBenefit", inviteBenefit);
	}

	@Override
	public List<InviteBenefit> queryInviteBenefit(Map<String, Object> condition,
			PaginationMore page) throws BusinessException {
		page.setTotalRows(getInviteBenefitCount(condition));
		page.repaginate();
		return inviteBenefitServiceImpl.selectList("queryInviteBenefit", condition, page);
	}

	@Override
	public Integer getInviteBenefitCount(Map<String, Object> condition)throws BusinessException {
		return (Integer) inviteBenefitServiceImpl.getObject("getInviteBenefitCount", condition);
	}

	@Override
	public BigDecimal getInviteBenefitTotal(Map<String, Object> condition)
			throws BusinessException {
		return (BigDecimal) inviteBenefitServiceImpl.getObject("getInviteBenefitTotal", condition);
	}

	@Override
	public List<InviteBenefit> queryInviteBenefitByPhone(
			Map<String, Object> condition) throws BusinessException {
		return inviteBenefitServiceImpl.findObjects("queryInviteBenefitByPhone", condition);
	}
	
}
