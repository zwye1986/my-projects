package com.venada.efinance.business.impl;

import com.venada.efinance.business.DealDetailBusiness;
import com.venada.efinance.common.exception.BusinessException;
import com.venada.efinance.pojo.DealDetail;
import com.venada.efinance.service.DealDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Map;

@Service
public class DealDetailBusinessImpl implements DealDetailBusiness {
	@Autowired
	private DealDetailService dealDetailService;

	public BigDecimal getDealDetailAmountByUserId(String userid) throws BusinessException {
		BigDecimal bigDecimal = (BigDecimal)dealDetailService.getObject("getDealDetailAmountByUserId", userid);
		if(bigDecimal != null){
			bigDecimal = bigDecimal.setScale(2,BigDecimal.ROUND_DOWN);
		}
		
		return bigDecimal;
	}

	public int addDealDetail(DealDetail entity) throws BusinessException {
		return dealDetailService.saveObject("addDealDetail", entity);
	}

	@Override
	public BigDecimal getUserDealdetailAmountByDetailtype(
			Map<String, Object> conditions) throws BusinessException {
		return (BigDecimal) dealDetailService.getObject("getUserDealdetailAmountByDetailtype", conditions);
	}

	@Override
	public BigDecimal countTotalWithdrawalFeeForSvip(String userid)
			throws BusinessException {
		return (BigDecimal) dealDetailService.getObject("countTotalWithdrawalFeeForSvip", userid);
	}

}
