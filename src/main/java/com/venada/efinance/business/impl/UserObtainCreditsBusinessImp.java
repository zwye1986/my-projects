package com.venada.efinance.business.impl;

import com.venada.efinance.business.UserObtainCreditsBusiness;
import com.venada.efinance.common.exception.BusinessException;
import com.venada.efinance.common.util.PaginationMore;
import com.venada.efinance.pojo.ObtainCredits;
import com.venada.efinance.service.UserObtainCreditsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * 
 * @author hepei
 * 
 */
@Service(value = "userObtainCreditsBusinessImp")
public class UserObtainCreditsBusinessImp implements UserObtainCreditsBusiness {

	@Autowired
	private UserObtainCreditsService userObtainCreditsServiceImpl;

	

	@Override
	public List<ObtainCredits> queryObtainCredits(
			Map<String, Object> condition, PaginationMore page)
			throws BusinessException {
		page.setTotalRows(queryAllObtainCreditsCount(condition));
		page.repaginate();
		return userObtainCreditsServiceImpl.selectList("queryAllObtainCredits",
				condition, page);

	}

	@Override
	public void addObtainCredits(ObtainCredits obtainCredits)
			throws BusinessException {
		userObtainCreditsServiceImpl.saveObject("addObtainCredits", obtainCredits);
	}

	@Override
	public ObtainCredits queryObtainCreditsById(Map<String, Object> condition)
			throws BusinessException {
		return (ObtainCredits) userObtainCreditsServiceImpl.getObject("queryObtainCreditsById",
				condition);
	}

	@Override
	public void updateObtainCreditsById(ObtainCredits obtainCredits)
			throws BusinessException {
		userObtainCreditsServiceImpl.updateObject("updateObtainCreditsById", obtainCredits);
	}

	

	@Override
	public void deleteObtainCreditsById(int id) throws BusinessException {
		userObtainCreditsServiceImpl.deleteObject("deleteObtainCredits", id);
	}

	@Override
	public List<ObtainCredits> queryAllObtainCreditsByMobileNumber(
			Map<String, Object> condition) throws BusinessException {
		return userObtainCreditsServiceImpl.findObjects(
				"queryAllObtainCreditsByMobileNumber", condition);
	}

	@Override
	public int queryAllObtainCreditsCount(Map<String, Object> condition)
			throws BusinessException {
		return (Integer) userObtainCreditsServiceImpl.getObject(
				"queryAllObtainCreditsCount", condition);
	}

	

}
