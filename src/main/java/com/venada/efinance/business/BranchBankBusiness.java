package com.venada.efinance.business;

import com.venada.efinance.common.exception.BusinessException;
import com.venada.efinance.pojo.BranchBank;

import java.util.List;
import java.util.Map;

public interface BranchBankBusiness {
	public List<BranchBank> queryBranchBankByCityidAndBankid(Map<String,Integer> map) throws BusinessException;
}
