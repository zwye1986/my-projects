package com.venada.efinance.business.impl;

import com.venada.efinance.business.BranchBankBusiness;
import com.venada.efinance.common.exception.BusinessException;
import com.venada.efinance.pojo.BranchBank;
import com.venada.efinance.service.BranchBankService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
@Service
public class BranchBankBusinessImpl implements BranchBankBusiness {
	@Autowired
	private BranchBankService branchBankService;
	@Override
	public List<BranchBank> queryBranchBankByCityidAndBankid(
			Map<String, Integer> map) throws BusinessException {
		return branchBankService.findObjects("queryBranchBankByCityidAndBankid", map);
	}

}
